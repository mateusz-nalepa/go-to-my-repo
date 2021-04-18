package com.github.mateusznalepa.gotomyrepo.action

import com.intellij.openapi.actionSystem.ActionGroup
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import icons.SdkIcons

class DynamicActionGroup : ActionGroup() {
    override fun getChildren(e: AnActionEvent?): Array<AnAction> {
        return arrayOf(
            PopupDialogAction(
                "Copy remote url location",
                "Dynamic Action Demo",
                SdkIcons.Sdk_default_icon
            ), PopupDialogAction(
                "Copy remote url location",
                "Dynamic Action Demo",
                SdkIcons.Sdk_default_icon
            )
        )
    }
}