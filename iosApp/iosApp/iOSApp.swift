import SwiftUI

/**
 * Point d'entrée principal de l'application EL VITA sur iOS (iPhone et iPad).
 * Conçu pour fonctionner 100% hors connexion avec le même moteur clinique et la même interface.
 */
@main
struct iOSApp: App {
    var body: some Scene {
        WindowGroup {
            ContentView()
                .ignoresSafeArea(.all)
        }
    }
}
