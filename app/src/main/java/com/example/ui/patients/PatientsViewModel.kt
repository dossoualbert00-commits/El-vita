package com.example.ui.patients

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.entities.ClinicalObservationEntity
import com.example.data.local.entities.ClinicalUpdateEntity
import com.example.data.local.entities.LabResultEntity
import com.example.data.local.entities.PatientEntity
import com.example.data.local.entities.VitalEntity
import com.example.data.repository.MedicalRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class PatientDetailState(
  val patient: PatientEntity? = null,
  val observations: List<ClinicalObservationEntity> = emptyList(),
  val updates: List<ClinicalUpdateEntity> = emptyList(),
  val vitals: List<VitalEntity> = emptyList(),
  val labResults: List<LabResultEntity> = emptyList()
)

class PatientsViewModel(private val repository: MedicalRepository) : ViewModel() {

  private val _searchQuery = MutableStateFlow("")
  val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

  val patientsList: StateFlow<List<PatientEntity>> = _searchQuery
    .flatMapLatest { query -> repository.searchPatients(query) }
    .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

  private val _selectedPatientId = MutableStateFlow<Long?>(null)
  val selectedPatientId: StateFlow<Long?> = _selectedPatientId.asStateFlow()

  private val _patientDetail = MutableStateFlow(PatientDetailState())
  val patientDetail: StateFlow<PatientDetailState> = _patientDetail.asStateFlow()

  fun setSearchQuery(query: String) {
    _searchQuery.value = query
  }

  fun selectPatient(patientId: Long?) {
    _selectedPatientId.value = patientId
    if (patientId == null) {
      _patientDetail.value = PatientDetailState()
    } else {
      viewModelScope.launch {
        val patient = repository.getPatientById(patientId)
        repository.getObservationsForPatient(patientId).collect { obs ->
          repository.getUpdatesForPatient(patientId).collect { updates ->
            repository.getVitalsForPatient(patientId).collect { vitals ->
              repository.getLabResultsForPatient(patientId).collect { labs ->
                _patientDetail.value = PatientDetailState(
                  patient = patient,
                  observations = obs,
                  updates = updates,
                  vitals = vitals,
                  labResults = labs
                )
              }
            }
          }
        }
      }
    }
  }

  fun createNewPatient(
    name: String,
    age: Int,
    gender: String,
    dept: String,
    room: String,
    condition: String,
    onSuccess: (Long) -> Unit
  ) {
    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE)
    val patient = PatientEntity(
      fullNameOrAlias = name,
      age = age,
      gender = gender,
      admissionDate = sdf.format(Date()),
      department = dept,
      roomNumber = room,
      primaryCondition = condition
    )
    viewModelScope.launch {
      val id = repository.insertPatient(patient)
      selectPatient(id)
      onSuccess(id)
    }
  }

  fun deletePatient(patient: PatientEntity, onDeleted: () -> Unit) {
    viewModelScope.launch {
      repository.deletePatient(patient)
      selectPatient(null)
      onDeleted()
    }
  }
}
