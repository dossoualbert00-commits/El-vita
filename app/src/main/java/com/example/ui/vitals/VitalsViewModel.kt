package com.example.ui.vitals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.entities.PatientEntity
import com.example.data.local.entities.VitalEntity
import com.example.data.medical.MedicalInterpreter
import com.example.data.medical.VitalsInterpretation
import com.example.data.repository.MedicalRepository
import com.example.ui.components.ChartPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

enum class HistoryFilter(val label: String, val durationMillis: Long) {
  TODAY("Aujourd'hui", 86_400_000L),
  LAST_24H("24 heures", 86_400_000L),
  LAST_7D("7 jours", 7 * 86_400_000L),
  LAST_30D("30 jours", 30 * 86_400_000L),
  ALL("Tout", Long.MAX_VALUE)
}

data class VitalsUiState(
  val patients: List<PatientEntity> = emptyList(),
  val vitalsList: List<VitalEntity> = emptyList(),
  val selectedPatientId: Long? = null,
  val selectedFilter: HistoryFilter = HistoryFilter.LAST_7D,
  // Current Form inputs
  val tempInput: String = "",
  val pasInput: String = "",
  val padInput: String = "",
  val hrInput: String = "",
  val rrInput: String = "",
  val spo2Input: String = "",
  val glucoseInput: String = "",
  val weightInput: String = "",
  val heightInput: String = "",
  val commentInput: String = "",
  val currentInterpretation: VitalsInterpretation? = null,
  val isSavedSuccess: Boolean = false
)

class VitalsViewModel(private val repository: MedicalRepository) : ViewModel() {

  private val _state = MutableStateFlow(VitalsUiState())
  val state: StateFlow<VitalsUiState> = _state.asStateFlow()

  val allPatients: StateFlow<List<PatientEntity>> = repository.getAllPatients()
    .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

  val allVitals: StateFlow<List<VitalEntity>> = repository.getAllVitals()
    .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

  init {
    viewModelScope.launch {
      combine(allPatients, allVitals) { patients, vitals ->
        _state.value.copy(
          patients = patients,
          vitalsList = vitals
        )
      }.collect { updated ->
        _state.value = updated
      }
    }
  }

  fun onPatientSelected(patientId: Long?) {
    _state.value = _state.value.copy(selectedPatientId = patientId)
  }

  fun onFilterSelected(filter: HistoryFilter) {
    _state.value = _state.value.copy(selectedFilter = filter)
  }

  fun updateFormFields(
    temp: String = _state.value.tempInput,
    pas: String = _state.value.pasInput,
    pad: String = _state.value.padInput,
    hr: String = _state.value.hrInput,
    rr: String = _state.value.rrInput,
    spo2: String = _state.value.spo2Input,
    glucose: String = _state.value.glucoseInput,
    weight: String = _state.value.weightInput,
    height: String = _state.value.heightInput,
    comment: String = _state.value.commentInput
  ) {
    _state.value = _state.value.copy(
      tempInput = temp,
      pasInput = pas,
      padInput = pad,
      hrInput = hr,
      rrInput = rr,
      spo2Input = spo2,
      glucoseInput = glucose,
      weightInput = weight,
      heightInput = height,
      commentInput = comment,
      isSavedSuccess = false
    )
    evaluatePedagogicalInterpretation()
  }

  fun evaluatePedagogicalInterpretation() {
    val s = _state.value
    val t = s.tempInput.toFloatOrNull()
    val pas = s.pasInput.toIntOrNull()
    val pad = s.padInput.toIntOrNull()
    val fc = s.hrInput.toIntOrNull()
    val fr = s.rrInput.toIntOrNull()
    val spo2 = s.spo2Input.toIntOrNull()
    val gluc = s.glucoseInput.toFloatOrNull()
    val w = s.weightInput.toFloatOrNull()
    val h = s.heightInput.toFloatOrNull()

    if (t != null || pas != null || fc != null || fr != null || spo2 != null || gluc != null || w != null) {
      val interpretation = MedicalInterpreter.interpretVitals(
        temp = t,
        pas = pas,
        pad = pad,
        fc = fc,
        fr = fr,
        spo2 = spo2,
        glycemie = gluc,
        weight = w,
        height = h
      )
      _state.value = _state.value.copy(currentInterpretation = interpretation)
    } else {
      _state.value = _state.value.copy(currentInterpretation = null)
    }
  }

  fun saveVitals(onSuccess: () -> Unit) {
    val s = _state.value
    val interpretation = s.currentInterpretation ?: return

    val now = System.currentTimeMillis()
    val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.FRANCE)
    val formattedDate = sdf.format(Date(now))

    val t = s.tempInput.toFloatOrNull()
    val pas = s.pasInput.toIntOrNull()
    val pad = s.padInput.toIntOrNull()
    val fc = s.hrInput.toIntOrNull()
    val fr = s.rrInput.toIntOrNull()
    val spo2 = s.spo2Input.toIntOrNull()
    val gluc = s.glucoseInput.toFloatOrNull()
    val w = s.weightInput.toFloatOrNull()
    val h = s.heightInput.toFloatOrNull()
    val bmi = if (w != null && h != null && h > 0) w / ((h / 100f) * (h / 100f)) else null

    val vital = VitalEntity(
      patientId = s.selectedPatientId,
      temperature = t,
      systolicBp = pas,
      diastolicBp = pad,
      heartRate = fc,
      respiratoryRate = fr,
      oxygenSaturation = spo2,
      bloodGlucose = gluc,
      weight = w,
      height = h,
      bmi = bmi,
      comment = s.commentInput,
      alertLevel = interpretation.alertLevel,
      pedagogicalNote = interpretation.explanations.joinToString("\n"),
      precautions = interpretation.precautions.joinToString("\n"),
      timestamp = now,
      formattedDateTime = formattedDate
    )

    viewModelScope.launch {
      repository.insertVital(vital)
      _state.value = _state.value.copy(
        tempInput = "",
        pasInput = "",
        padInput = "",
        hrInput = "",
        rrInput = "",
        spo2Input = "",
        glucoseInput = "",
        weightInput = "",
        heightInput = "",
        commentInput = "",
        currentInterpretation = null,
        isSavedSuccess = true
      )
      onSuccess()
    }
  }

  fun deleteVital(vital: VitalEntity) {
    viewModelScope.launch {
      repository.deleteVital(vital)
    }
  }

  // Prepares chart series filtered by time
  fun getFilteredVitals(): List<VitalEntity> {
    val all = _state.value.vitalsList
    val pid = _state.value.selectedPatientId
    val duration = _state.value.selectedFilter.durationMillis
    val now = System.currentTimeMillis()
    val threshold = if (duration == Long.MAX_VALUE) 0L else now - duration

    return all.filter { v ->
      (pid == null || v.patientId == pid) && v.timestamp >= threshold
    }.sortedBy { it.timestamp }
  }

  fun getBpChartPoints(vitals: List<VitalEntity>): List<ChartPoint> {
    val sdf = SimpleDateFormat("dd/MM HH:mm", Locale.FRANCE)
    return vitals.filter { it.systolicBp != null }.map {
      ChartPoint(
        timestamp = it.timestamp,
        label = sdf.format(Date(it.timestamp)),
        value = it.systolicBp!!.toFloat(),
        secondaryValue = it.diastolicBp?.toFloat()
      )
    }
  }

  fun getHrChartPoints(vitals: List<VitalEntity>): List<ChartPoint> {
    val sdf = SimpleDateFormat("dd/MM HH:mm", Locale.FRANCE)
    return vitals.filter { it.heartRate != null }.map {
      ChartPoint(
        timestamp = it.timestamp,
        label = sdf.format(Date(it.timestamp)),
        value = it.heartRate!!.toFloat()
      )
    }
  }

  fun getTempChartPoints(vitals: List<VitalEntity>): List<ChartPoint> {
    val sdf = SimpleDateFormat("dd/MM HH:mm", Locale.FRANCE)
    return vitals.filter { it.temperature != null }.map {
      ChartPoint(
        timestamp = it.timestamp,
        label = sdf.format(Date(it.timestamp)),
        value = it.temperature!!
      )
    }
  }

  fun getSpo2ChartPoints(vitals: List<VitalEntity>): List<ChartPoint> {
    val sdf = SimpleDateFormat("dd/MM HH:mm", Locale.FRANCE)
    return vitals.filter { it.oxygenSaturation != null }.map {
      ChartPoint(
        timestamp = it.timestamp,
        label = sdf.format(Date(it.timestamp)),
        value = it.oxygenSaturation!!.toFloat()
      )
    }
  }
}
