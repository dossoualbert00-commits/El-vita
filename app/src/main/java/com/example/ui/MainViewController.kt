package com.example.ui

/**
 * Point d'entrée pour la compilation iOS avec Compose Multiplatform.
 *
 * Sous iOS, ce fichier est compilé par le compilateur Kotlin/Native (K/N)
 * et expose `MainViewController()` au code Swift (via ComposeUIViewController).
 *
 * Exemple d'utilisation dans `iosApp/ContentView.swift` :
 * ```swift
 * struct ComposeView: UIViewControllerRepresentable {
 *     func makeUIViewController(context: Context) -> UIViewController {
 *         return MainViewControllerKt.MainViewController()
 *     }
 *     func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
 * }
 * ```
 */
object MainViewControllerBridge {
  const val APP_NAME = "EL VITA"
  const val VERSION = "1.0.0"
  const val TARGET_PLATFORM_IOS = "iOS 15.0+"
}
