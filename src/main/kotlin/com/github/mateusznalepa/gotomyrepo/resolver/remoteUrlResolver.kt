package com.github.mateusznalepa.gotomyrepo.resolver

data class RemoteUrlResolverParams(
    val pushUrl: String,
    val repositoryRootPath: String,
    val lineNumber: Int?
)

interface RemoteUrlResolver {
    fun resolveUrl(remoteUrlResolverParams: RemoteUrlResolverParams): String
}

object GitHubUrlResolver : RemoteUrlResolver {
    override fun resolveUrl(remoteUrlResolverParams: RemoteUrlResolverParams): String {
        val resolvedUrl =
            remoteUrlResolverParams.pushUrl.substring(0, remoteUrlResolverParams.pushUrl.length - 4) +
                    "/blob" +
                    "/${resolveCurrentBranch()}${remoteUrlResolverParams.repositoryRootPath}"

        return if (remoteUrlResolverParams.lineNumber != null) {
            "$resolvedUrl#L$${remoteUrlResolverParams.lineNumber}"
        } else {
            resolvedUrl
        }
    }

    private fun resolveCurrentBranch(): String {
        return "main"
    }
}

object BitBucketUrlResolver : RemoteUrlResolver {
    override fun resolveUrl(remoteUrlResolverParams: RemoteUrlResolverParams): String {
//        val resolvedUrl =
//            remoteUrlResolverParams.pushUrl.substring(0, remoteUrlResolverParams.pushUrl.length - 4) +
//                    "/blob" +
//                    "/${resolveCurrentBranch()}${remoteUrlResolverParams.repositoryRootPath}"
//
//        return if (remoteUrlResolverParams.lineNumber != null) {
//            "$resolvedUrl#L$${remoteUrlResolverParams.lineNumber}"
//        } else {
//            resolvedUrl
//        }
        return "bitbucket XDDDDDDDDDDDDDDDDD"
    }

    private fun resolveCurrentBranch(): String {
        return "main"
    }
}