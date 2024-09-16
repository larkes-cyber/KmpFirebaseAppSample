import SwiftUI
import FirebaseCore
import shared

@main
struct iOSApp: App {
    init(){
        FirebaseApp.configure()
        PlatformSDK().doInit(configuration: PlatformConfiguration())
    }
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
