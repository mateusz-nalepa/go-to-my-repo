package com.github.mateusznalepa.gotomyrepo.resolver

interface RemoteUrlResolver {
    fun resolveUrl(pushUrl: String, repositoryRootPath: String, resolveLineNumber: Int?): String
}

object GitHubUrlResolver : RemoteUrlResolver {
    override fun resolveUrl(pushUrl: String, repositoryRootPath: String, lineNumber: Int?): String {
        val resolvedUrl =
            "${pushUrl.substring(0, pushUrl.length - 4)}/blob/${resolveCurrentBranch()}$repositoryRootPath"

        return if (lineNumber != null) {
            "$resolvedUrl#L$lineNumber"
        } else {
            resolvedUrl
        }
//       https://github.com/mateusz-nalepa/hello-world/blob/main/src/HelloWorld.java
    }

    private fun resolveCurrentBranch(): String {
        return "main"
    }
}