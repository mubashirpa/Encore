import SwiftUI

@main
struct iOSApp: App {

    // TODO("Check working or not")
	init() {
		KoinUtilsKt.initKoin()
		NapierUtilsKt.initNapier()

	}

    // TODO("Check working or not")
	@UIApplicationDelegateAdaptor(AppDelegate.self) var appDelegate

	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}