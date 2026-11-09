package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.lifecycleScope
import com.example.data.local.AppDatabase
import com.example.data.repository.MedicalRepository
import com.example.data.storage.AndroidSharedPrefsStorage
import com.example.ui.ElVitaApp
import com.example.ui.clinical.ClinicalViewModel
import com.example.ui.home.HomeViewModel
import com.example.ui.labs.LabsViewModel
import com.example.ui.patients.PatientsViewModel
import com.example.ui.profile.ProfileViewModel
import com.example.ui.semiology.SemiologyViewModel
import com.example.ui.vitals.VitalsViewModel

class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

    val database = AppDatabase.getDatabase(applicationContext, lifecycleScope)
    val repository = MedicalRepository(database)
    val prefStorage = AndroidSharedPrefsStorage(applicationContext)

    // ViewModels
    val homeViewModel = HomeViewModel(repository)
    val vitalsViewModel = VitalsViewModel(repository)
    val clinicalViewModel = ClinicalViewModel(repository)
    val labsViewModel = LabsViewModel(repository)
    val semiologyViewModel = SemiologyViewModel()
    val patientsViewModel = PatientsViewModel(repository)
    val profileViewModel = ProfileViewModel(prefStorage, repository, database)

    setContent {
      val systemInDark = isSystemInDarkTheme()
      val initialDark = prefStorage.getBoolean("app_dark_mode", systemInDark)
      var userDarkMode by remember { mutableStateOf(initialDark) }

      ElVitaApp(
        homeViewModel = homeViewModel,
        clinicalViewModel = clinicalViewModel,
        vitalsViewModel = vitalsViewModel,
        labsViewModel = labsViewModel,
        semiologyViewModel = semiologyViewModel,
        patientsViewModel = patientsViewModel,
        profileViewModel = profileViewModel,
        userDarkMode = userDarkMode,
        onToggleDarkMode = { dark ->
          userDarkMode = dark
          profileViewModel.toggleDarkMode(dark)
        },
        enableBackHandler = true
      )
    }
  }
}
