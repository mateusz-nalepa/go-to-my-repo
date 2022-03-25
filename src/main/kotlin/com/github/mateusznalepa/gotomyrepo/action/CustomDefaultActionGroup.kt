package com.github.mateusznalepa.gotomyrepo.action

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.DefaultActionGroup

class CustomDefaultActionGroup : DefaultActionGroup() {

    override fun update(event: AnActionEvent) {
        event.presentation.isEnabled = true
    }
}
