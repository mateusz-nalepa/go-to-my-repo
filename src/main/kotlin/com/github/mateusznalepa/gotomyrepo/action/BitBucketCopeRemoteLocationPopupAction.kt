package com.github.mateusznalepa.gotomyrepo.action

import com.github.mateusznalepa.gotomyrepo.resolver.BitBucketUrlResolver
import com.github.mateusznalepa.gotomyrepo.resolver.CopyRemoteLocationResolver
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent


class BitBucketCopeRemoteLocationPopupAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        CopyRemoteLocationResolver(
            project = requireNotNull(e.project),
            event = e,
            resolveUrlFunction = { BitBucketUrlResolver.resolveUrl(it) }
        )
            .copyRemoteFileLocationToClipboard()
    }

    override fun update(e: AnActionEvent) {
        val project = e.project
        e.presentation.isEnabledAndVisible = project != null
    }

}
