package core.utils

import platform.UIKit.UIApplication
import platform.Foundation.NSURL

class IOSUrlLauncher : UrlLauncher {

    override fun openUrl(url: String) {
        UIApplication.sharedApplication.openURL(NSURL.URLWithString(url))
    }
}