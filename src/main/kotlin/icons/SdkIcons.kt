package icons

import com.intellij.openapi.util.IconLoader

class SdkIcons {

    private constructor()

    companion object {
        @JvmField
        val Sdk_default_icon = IconLoader.getIcon("/icons/sdk_16.svg")

        @JvmField
        val BitBucket_Icon = IconLoader.getIcon("/icons/bb-16.svg")

        @JvmField
        val Github_Icon = IconLoader.getIcon("/icons/gh-16.svg")

        @JvmField
        val Hodor_Icon = IconLoader.getIcon("/icons/hodor-16.svg")
    }
}
