package com.github.mateusznalepa.gotomyrepo.resolver

import com.intellij.notification.NotificationDisplayType
import com.intellij.notification.NotificationGroup
import com.intellij.notification.NotificationType
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.ide.CopyPasteManager
import com.intellij.openapi.project.Project
import com.intellij.util.ui.TextTransferable
import git4idea.GitUtil
import git4idea.repo.GitRepository
import git4idea.repo.GitRepositoryManager

class CopyRemoteLocationResolver(
    private val project: Project,
    private val event: AnActionEvent,
    private val resolveUrlFunction: (RemoteUrlResolverParams) -> String
) {

    private val notificationGroup = NotificationGroup(
        displayId = "Go To My Repo Notification Group",
        displayType = NotificationDisplayType.BALLOON,
        isLogByDefault = true
    )

    fun copyRemoteFileLocationToClipboard() {
        resolveFullPathRemoteLocation()
            .also { addValueToClipboard(it) }
            .also { notifyUser() }
    }

    private fun resolveFullPathRemoteLocation(): String {
        val gitRepository = gitRepository()
        val params = RemoteUrlResolverParams(
            pushUrl = gitRepository.remotes.first().pushUrls.first(),
            currentBranchName = gitRepository.currentBranchName!!,
            currentBranchRevision = gitRepository.currentRevision!!,
            pathFromRepositoryRoot = resolveRepositoryRootPath(),
            lineNumber = resolveLineNumber()
        )
        return resolveUrlFunction.invoke(params)
    }

    private fun gitRepository(): GitRepository {
        return GitRepositoryManager
            .getInstance(project)
            .repositories[0]
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
        notificationGroup
            .createNotification(
                content = "Remote location copied to clipboard",
                type = NotificationType.INFORMATION
            )
            .notify(project)
    }
}