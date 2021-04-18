package org.jetbrains.plugins.template.resolver

interface RemoteUrlResolver {
    fun resolveUrl(pushUrl: String, repositoryRootPath: String): String
}

object GitHubUrlResolver : RemoteUrlResolver {
    override fun resolveUrl(pushUrl: String, repositoryRootPath: String): String {
        return "${pushUrl.substring(0, pushUrl.length - 4)}/blob/${resolveCurrentBranch()}$repositoryRootPath"
//       https://github.com/mateusz-nalepa/hello-world/blob/main/src/HelloWorld.java
    }

    private fun resolveCurrentBranch(): String {
        return "main"
    }
}