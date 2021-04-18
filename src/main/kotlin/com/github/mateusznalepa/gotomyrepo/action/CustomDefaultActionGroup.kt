package com.github.mateusznalepa.gotomyrepo.action

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.DefaultActionGroup
import icons.SdkIcons

class CustomDefaultActionGroup : DefaultActionGroup() {


    override fun update(event: AnActionEvent) {
        // Enable/disable depending on whether user is editing
        val editor = event.getData(CommonDataKeys.EDITOR)
        event.presentation.isEnabled = editor != null
        // Take this opportunity to set an icon for the group.
        event.presentation.icon = SdkIcons.Sdk_default_icon
    }

}