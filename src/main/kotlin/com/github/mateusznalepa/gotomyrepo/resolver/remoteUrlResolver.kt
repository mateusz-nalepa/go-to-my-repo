package com.github.mateusznalepa.gotomyrepo.resolver

const val FOUR = 4

@Suppress("ParameterListWrapping")
data class RemoteUrlResolverParams(
    val pushUrl: String,
    val currentBranchName: String,
    val currentBranchRevision: String,
    val pathFromRepositoryRoot: String,
    val lineNumber: Int?
)

interface RemoteUrlResolver {
    fun resolveUrl(remoteUrlResolverParams: RemoteUrlResolverParams): String
}

object GitHubUrlResolver : RemoteUrlResolver {
    override fun resolveUrl(remoteUrlResolverParams: RemoteUrlResolverParams): String {
        val resolvedUrl =
            remoteUrlResolverParams.pushUrl.substring(0, remoteUrlResolverParams.pushUrl.length - FOUR) +
                "/blob" +
                "/${remoteUrlResolverParams.currentBranchName}" +
                remoteUrlResolverParams.pathFromRepositoryRoot

        return if (remoteUrlResolverParams.lineNumber != null) {
            "$resolvedUrl#L${remoteUrlResolverParams.lineNumber}"
        } else {
            resolvedUrl
        }
    }
}

object BitBucketUrlResolver : RemoteUrlResolver {
    override fun resolveUrl(remoteUrlResolverParams: RemoteUrlResolverParams): String {
        val pushUrl = remoteUrlResolverParams.pushUrl
        val init = pushUrl.substring(pushUrl.indexOf("@") + 1)
        val resolvedUrl =
            "https://${init.substring(0, init.length - FOUR)}" +
                "/src" +
                "/${remoteUrlResolverParams.currentBranchRevision}" + remoteUrlResolverParams.pathFromRepositoryRoot

        return if (remoteUrlResolverParams.lineNumber != null) {
            "$resolvedUrl#lines-${remoteUrlResolverParams.lineNumber}"
        } else {
            resolvedUrl
        }
    }
}
