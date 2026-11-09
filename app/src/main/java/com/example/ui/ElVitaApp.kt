package com.example.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Biotech
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import com.example.ui.about.AboutElVitaScreen
import com.example.ui.clinical.ClinicalScreen
import com.example.ui.clinical.ClinicalViewModel
import com.example.ui.home.HomeScreen
import com.example.ui.home.HomeViewModel
import com.example.ui.labs.LabsScreen
import com.example.ui.labs.LabsViewModel
import com.example.ui.patients.PatientsScreen
import com.example.ui.patients.PatientsViewModel
import com.example.ui.profile.ProfileScreen
import com.example.ui.profile.ProfileViewModel
import com.example.ui.semiology.SemiologyScreen
import com.example.ui.semiology.SemiologyViewModel
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.vitals.VitalsScreen
import com.example.ui.vitals.VitalsViewModel

enum class AppScreen {
  MAIN_TABS,
  DOSSIERS_OVERLAY,
  PROFILE_OVERLAY,
  ABOUT_EL_VITA_OVERLAY
}

/**
 * Composant racine multiplateforme universel de l'application EL VITA.
 * Fonctionne identiquement sous Android (dans MainActivity) et sous iOS (dans MainViewController).
 */
@Composable
fun ElVitaApp(
  homeViewModel: HomeViewModel,
  clinicalViewModel: ClinicalViewModel,
  vitalsViewModel: VitalsViewModel,
  labsViewModel: LabsViewModel,
  semiologyViewModel: SemiologyViewModel,
  patientsViewModel: PatientsViewModel,
  profileViewModel: ProfileViewModel,
  userDarkMode: Boolean,
  onToggleDarkMode: (Boolean) -> Unit,
  enableBackHandler: Boolean = true
) {
  MyApplicationTheme(darkTheme = userDarkMode) {
    var currentScreen by remember { mutableStateOf(AppScreen.MAIN_TABS) }
    var currentTab by remember { mutableIntStateOf(0) }
    var clinicalSubTab by remember { mutableIntStateOf(0) }
    var vitalsSubTab by remember { mutableIntStateOf(0) }

    // Gestion du retour arrière physique / gestuel
    if (enableBackHandler) {
      if (currentScreen == AppScreen.ABOUT_EL_VITA_OVERLAY) {
        BackHandler {
          currentScreen = AppScreen.PROFILE_OVERLAY
        }
      } else if (currentScreen != AppScreen.MAIN_TABS) {
        BackHandler {
          currentScreen = AppScreen.MAIN_TABS
        }
      } else if (currentTab != 0) {
        BackHandler {
          currentTab = 0
        }
      }
    }

    Scaffold(
      modifier = Modifier.fillMaxSize(),
      bottomBar = {
        if (currentScreen == AppScreen.MAIN_TABS) {
          NavigationBar(
            modifier = Modifier.testTag("bottom_nav_bar")
          ) {
            NavigationBarItem(
              icon = { Icon(Icons.Default.Home, contentDescription = "Accueil") },
              label = { Text("Accueil", fontWeight = if (currentTab == 0) FontWeight.Bold else FontWeight.Normal) },
              selected = currentTab == 0,
              onClick = { currentTab = 0 },
              modifier = Modifier.testTag("nav_item_accueil")
            )
            NavigationBarItem(
              icon = { Icon(Icons.Default.Assignment, contentDescription = "Clinique") },
              label = { Text("Clinique", fontWeight = if (currentTab == 1) FontWeight.Bold else FontWeight.Normal) },
              selected = currentTab == 1,
              onClick = {
                currentTab = 1
                clinicalSubTab = 0
              },
              modifier = Modifier.testTag("nav_item_clinique")
            )
            NavigationBarItem(
              icon = { Icon(Icons.Default.Timeline, contentDescription = "Mesures") },
              label = { Text("Mesures", fontWeight = if (currentTab == 2) FontWeight.Bold else FontWeight.Normal) },
              selected = currentTab == 2,
              onClick = { currentTab = 2 },
              modifier = Modifier.testTag("nav_item_mesures")
            )
            NavigationBarItem(
              icon = { Icon(Icons.Default.Biotech, contentDescription = "Analyses") },
              label = { Text("Analyses", fontWeight = if (currentTab == 3) FontWeight.Bold else FontWeight.Normal) },
              selected = currentTab == 3,
              onClick = { currentTab = 3 },
              modifier = Modifier.testTag("nav_item_analyses")
            )
            NavigationBarItem(
              icon = { Icon(Icons.Default.MenuBook, contentDescription = "Sémiologie") },
              label = { Text("Sémiologie", fontWeight = if (currentTab == 4) FontWeight.Bold else FontWeight.Normal) },
              selected = currentTab == 4,
              onClick = { currentTab = 4 },
              modifier = Modifier.testTag("nav_item_semiologie")
            )
          }
        }
      }
    ) { innerPadding ->
      Box(modifier = Modifier.padding(innerPadding)) {
        when (currentScreen) {
          AppScreen.MAIN_TABS -> {
            when (currentTab) {
              0 -> HomeScreen(
                viewModel = homeViewModel,
                onNavigateToTab = { tabIndex -> currentTab = tabIndex },
                onOpenDossiers = { currentScreen = AppScreen.DOSSIERS_OVERLAY },
                onOpenProfile = { currentScreen = AppScreen.PROFILE_OVERLAY },
                onOpenNewVitals = {
                  currentTab = 2
                  vitalsSubTab = 0
                },
                onOpenNewObservation = {
                  currentTab = 1
                  clinicalSubTab = 0
                },
                onOpenNewUpdate = {
                  currentTab = 1
                  clinicalSubTab = 1
                }
              )
              1 -> ClinicalScreen(
                viewModel = clinicalViewModel,
                initialSubTab = clinicalSubTab
              )
              2 -> VitalsScreen(
                viewModel = vitalsViewModel,
                initialTab = vitalsSubTab
              )
              3 -> LabsScreen(
                viewModel = labsViewModel
              )
              4 -> SemiologyScreen(
                viewModel = semiologyViewModel
              )
            }
          }
          AppScreen.DOSSIERS_OVERLAY -> {
            PatientsScreen(
              viewModel = patientsViewModel,
              onNavigateToNewVitalForPatient = { patientId ->
                vitalsViewModel.onPatientSelected(patientId)
                currentScreen = AppScreen.MAIN_TABS
                currentTab = 2
              },
              onNavigateToNewObservationForPatient = { patientId ->
                val p = patientsViewModel.patientsList.value.find { it.id == patientId }
                if (p != null) clinicalViewModel.selectPatientForObservation(p)
                clinicalSubTab = 0
                currentScreen = AppScreen.MAIN_TABS
                currentTab = 1
              },
              onNavigateToNewUpdateForPatient = { patientId ->
                val p = patientsViewModel.patientsList.value.find { it.id == patientId }
                clinicalViewModel.setUpdatePatient(p)
                clinicalSubTab = 1
                currentScreen = AppScreen.MAIN_TABS
                currentTab = 1
              },
              onBack = { currentScreen = AppScreen.MAIN_TABS }
            )
          }
          AppScreen.PROFILE_OVERLAY -> {
            ProfileScreen(
              viewModel = profileViewModel,
              onBack = { currentScreen = AppScreen.MAIN_TABS },
              onToggleTheme = onToggleDarkMode,
              onNavigateToAbout = { currentScreen = AppScreen.ABOUT_EL_VITA_OVERLAY }
            )
          }
          AppScreen.ABOUT_EL_VITA_OVERLAY -> {
            AboutElVitaScreen(
              onBack = { currentScreen = AppScreen.PROFILE_OVERLAY }
            )
          }
        }
      }
    }
  }
}
