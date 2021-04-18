package org.jetbrains.plugins.template.action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import javax.swing.Icon


class PopupDialogAction : AnAction {

    constructor()
    constructor(text: String?, description: String?, icon: Icon?) : super(text, description, icon)

    override fun actionPerformed(e: AnActionEvent) {
        // Using the event, create and show a dialog
        val currentProject = e.project
        val dlgMsg = StringBuilder("${e.presentation.text} Selected!")
        val dlgTitle = e.presentation.description
        // If an element is selected in the editor, add info about it.
        val nav = e.getData(CommonDataKeys.NAVIGATABLE)
        if (nav != null) {
            dlgMsg.append("Selected Element: $nav")
        }
        HodorRepoXD(requireNotNull(currentProject), e).copyRemoteFileLocationToClipboard()
//        Messages.showMessageDialog(currentProject, dlgMsg.toString(), dlgTitle, Messages.getInformationIcon())
    }

    override fun update(e: AnActionEvent) {
        // Set the availability based on whether a project is open
        val project = e.project
        e.presentation.isEnabledAndVisible = project != null
    }


}

//https://github.com/mateusz-nalepa/hello-world.git