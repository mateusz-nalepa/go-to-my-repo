package com.github.mateusznalepa.gotomyrepo.action

import com.github.mateusznalepa.gotomyrepo.resolver.CopyRemoteLocationResolver
import com.github.mateusznalepa.gotomyrepo.resolver.GitHubUrlResolver
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class GitHubCopeRemoteLocationPopupAction : AnAction() {

    override fun actionPerformed(event: AnActionEvent) {
        CopyRemoteLocationResolver(
            project = requireNotNull(event.project),
            event = event,
            resolveUrlFunction = { GitHubUrlResolver.resolveUrl(it) }
        )
            .copyRemoteFileLocationToClipboard()
    }

    override fun update(e: AnActionEvent) {
        val project = e.project
        e.presentation.isEnabledAndVisible = project != null
    }

}