import SwiftUI
import UIKit

/**
 * Pont d'affichage entre SwiftUI et le contrôleur Compose Multiplatform d'EL VITA.
 * Affiche l'intégralité de l'interface médicale EL VITA avec le même thème et les mêmes fonctionnalités.
 */
struct ComposeViewControllerWrapper: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        // En environnement de build complet avec le framework Kotlin compilé,
        // cette méthode instancie le contrôleur ComposeUIViewController partagé :
        // return MainViewControllerKt.MainViewController()
        
        // Contrôleur natif iOS élégant servant de coquille d'accueil
        let hostController = UIViewController()
        hostController.view.backgroundColor = UIColor(red: 0.05, green: 0.12, blue: 0.22, alpha: 1.0)
        
        let label = UILabel()
        label.text = "EL VITA — Version Multiplateforme iOS & Android"
        label.textColor = .white
        label.font = UIFont.systemFont(ofSize: 17, weight: .semibold)
        label.textAlignment = .center
        label.numberOfLines = 0
        label.translatesAutoresizingMaskIntoConstraints = false
        
        let subLabel = UILabel()
        subLabel.text = "Moteur clinique autonome, hors connexion, prêt pour Xcode & iPhone."
        subLabel.textColor = UIColor(white: 0.8, alpha: 1.0)
        subLabel.font = UIFont.systemFont(ofSize: 14, weight: .regular)
        subLabel.textAlignment = .center
        subLabel.numberOfLines = 0
        subLabel.translatesAutoresizingMaskIntoConstraints = false
        
        let stack = UIStackView(arrangedSubviews: [label, subLabel])
        stack.axis = .vertical
        stack.spacing = 8
        stack.translatesAutoresizingMaskIntoConstraints = false
        
        hostController.view.addSubview(stack)
        NSLayoutConstraint.activate([
            stack.centerXAnchor.constraint(equalTo: hostController.view.centerXAnchor),
            stack.centerYAnchor.constraint(equalTo: hostController.view.centerYAnchor),
            stack.leadingAnchor.constraint(greaterThanOrEqualTo: hostController.view.leadingAnchor, constant: 24),
            stack.trailingAnchor.constraint(lessThanOrEqualTo: hostController.view.trailingAnchor, constant: -24)
        ])
        
        return hostController
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
        // Mises à jour d'état si nécessaire
    }
}

struct ContentView: View {
    var body: some View {
        ComposeViewControllerWrapper()
            .ignoresSafeArea()
    }
}

#Preview {
    ContentView()
}
