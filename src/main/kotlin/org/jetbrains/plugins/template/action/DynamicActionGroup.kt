package org.jetbrains.plugins.template.action

import com.intellij.openapi.actionSystem.ActionGroup
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import icons.SdkIcons

class DynamicActionGroup : ActionGroup() {
    override fun getChildren(e: AnActionEvent?): Array<AnAction> {
        return arrayOf(PopupDialogAction(
            "Action Added at Runtime",
            "Dynamic Action Demo",
            SdkIcons.Sdk_default_icon)
        )
    }
}