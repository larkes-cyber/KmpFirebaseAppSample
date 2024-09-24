import SwiftUI
import FirebaseCore
import shared

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}


@main
struct iOSApp: App {
    init(){
        FirebaseApp.configure()
        PlatformSDK().doInit(configuration: PlatformConfiguration())
    }
	var body: some Scene {
		WindowGroup {
            ComposeView()
                    .ignoresSafeArea(.all)
		}
	}
}
