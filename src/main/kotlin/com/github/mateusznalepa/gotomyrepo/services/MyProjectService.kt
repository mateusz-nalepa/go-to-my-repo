package com.github.mateusznalepa.gotomyrepo.services

import com.github.mateusznalepa.gotomyrepo.MyBundle
import com.intellij.openapi.project.Project

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
