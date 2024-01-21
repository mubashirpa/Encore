import UIKit
import SwiftUI
import ComposeApp

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {
    func application(_ application: UIApplication,
                     open url: URL,
                     options: [UIApplicationOpenURLOptionsKey : Any] = [:] ) -> Bool {
                             // Handle the deep link URL
                             MainViewControllerKt.onDeepLink(url: url)
                             return true
                     }
}