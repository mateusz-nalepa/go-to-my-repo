package org.jetbrains.plugins.template.action

import com.intellij.notification.NotificationDisplayType
import com.intellij.notification.NotificationGroup
import com.intellij.notification.NotificationType
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.ide.CopyPasteManager
import com.intellij.openapi.project.Project
import com.intellij.util.ui.TextTransferable
import git4idea.GitUtil
import git4idea.repo.GitRepositoryManager
import org.jetbrains.plugins.template.resolver.GitHubUrlResolver

class HodorRepoXD(
    private val project: Project,
    private val event: AnActionEvent
) {

    private val notificationGroup = NotificationGroup(
        displayId = "Go To My Repo Notification Group",
        displayType = NotificationDisplayType.BALLOON,
        isLogByDefault = true
    )

    fun copyRemoteFileLocationToClipboard() {
        resolvePushUrl()
            .let { resolveFullPathRemoteLocation(it) }
            .also { addValueToClipboard(it) }
            .also { notifyUser() }
    }

    private fun resolvePushUrl(): String {
        return GitRepositoryManager
            .getInstance(project)
            .repositories[0]
            .remotes
            .first()
            .pushUrls
            .first()
    }

    private fun resolveFullPathRemoteLocation(pushUrl: String): String {
        return GitHubUrlResolver.resolveUrl(pushUrl, resolveRepositoryRootPath(), resolveLineNumber())
    }

    private fun resolveRepositoryRootPath(): String {
        val virtualFile = event.getData(CommonDataKeys.VIRTUAL_FILE)!!
        val gitRootPath = GitUtil.gitRootOrNull(virtualFile)?.path!!
        val actualFilePath = virtualFile.path

        return actualFilePath.substring(gitRootPath.length)
    }

    private fun resolveLineNumber(): Int {
        return event.getRequiredData(CommonDataKeys.EDITOR).caretModel.logicalPosition.line + 1
    }

    private fun addValueToClipboard(value: String) {
        CopyPasteManager
            .getInstance()
            .setContents(TextTransferable(value as CharSequence))
    }

    private fun notifyUser() {
        notificationGroup.createNotification("Remote location copied to clipboard", NotificationType.INFORMATION)
            .notify(project)
    }
}