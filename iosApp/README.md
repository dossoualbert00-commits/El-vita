# EL VITA — Guide de Déploiement et d'Exportation Multiplateforme (Android & iOS)

Ce dossier contient la configuration native pour l'application **EL VITA** sur iPhone et iPad.

---

## 1. Architecture Multiplateforme

EL VITA repose sur une architecture multiplateforme :
- **Moteur Clinique & Médical :** 100% partagé (`MedicalInterpreter.kt`, `ClinicalTrainingData.kt`, `SemiologyDataProvider.kt`).
- **Interface Utilisateur :** 100% partagée via **Compose Multiplatform** (`ElVitaApp.kt`, `HomeScreen.kt`, `ClinicalScreen.kt`, `LabsScreen.kt`, `VitalsScreen.kt`, etc.).
- **Stockage Local Hors Connexion :** Interface `PreferenceStorage` (Android : `SharedPreferences` ; iOS : `NSUserDefaults`), et base SQLite locale autonome.
- **Zéro Cloud & Zéro Donnée Fictive :** Fonctionnement strictement hors connexion pour garantir la confidentialité médicale.

---

## 2. Comment Ouvrir et Compiler sur Mac / iPhone (Xcode)

### Prérequis
* Un Mac sous macOS Ventura (13.0+) ou Sonoma/Sequoia (14.0+).
* **Xcode 15.0+** installé depuis le Mac App Store.
* Git et Java 17+ (JDK) installés.

### Instructions pas-à-pas
1. **Télécharger le projet :**
   Exportez le projet sous forme d'archive ZIP depuis le menu d'AI Studio, ou clonez le dépôt GitHub.
2. **Ouvrir le projet dans Xcode :**
   Double-cliquez sur `iosApp/iosApp.xcodeproj` ou ouvrez-le via Xcode (`File > Open...`).
3. **Sélectionner votre simulateur ou iPhone :**
   Dans la barre supérieure d'Xcode, choisissez par exemple `iPhone 15 Pro` ou connectez votre véritable iPhone par câble USB.
4. **Signer l'application (Pour tester sur un véritable iPhone) :**
   - Cliquez sur le projet `EL VITA` dans la colonne de gauche.
   - Rendez-vous dans l'onglet **Signing & Capabilities**.
   - Cochez **Automatically manage signing** et sélectionnez votre compte Apple gratuit ou payant dans le menu **Team**.
5. **Lancer la compilation :**
   Pressez **Command + R** (ou cliquez sur le bouton ▶️ "Play").
   L'application EL VITA s'installe et se lance directement sur votre iPhone !

---

## 3. Publication sur l'App Store / TestFlight

1. Dans Xcode, sélectionnez la cible **Any iOS Device (arm64)**.
2. Allez dans le menu **Product > Archive**.
3. Une fois l'archive créée dans l'Organizer Xcode, cliquez sur **Distribute App** puis **TestFlight & App Store**.
4. Suivez l'assistant de soumission Apple.

---

## 4. Structure des Fichiers iOS

```
iosApp/
├── Configuration/
│   └── Config.xcconfig        # Identifiants bundle, versions, cibles iOS 15.0+
├── iosApp/
│   ├── iOSApp.swift           # Point d'entrée SwiftUI (@main)
│   ├── ContentView.swift      # Pont UIViewControllerRepresentable vers Compose
│   ├── Info.plist             # Métadonnées, orientation, affichage "EL VITA"
│   └── Assets.xcassets/       # Icone de l'application et ressources graphiques
└── iosApp.xcodeproj/
    └── project.pbxproj        # Configuration de projet Xcode native
```
