package com.example.ui.labs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.entities.LabResultEntity
import com.example.data.medical.BiologicalInterpretation
import com.example.data.medical.HepaticInput
import com.example.data.medical.InflamCoagInput
import com.example.data.medical.MedicalInterpreter
import com.example.data.medical.NfsInput
import com.example.data.medical.UeCreatInput
import com.example.data.medical.UrineDipstickInput
import com.example.data.repository.MedicalRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class NfsFormState(
  val hb: String = "13.5",
  val ht: String = "41.0",
  val gr: String = "4.7",
  val retic: String = "65",
  val gb: String = "7.2",
  val pnn: String = "4.5",
  val lympho: String = "2.1",
  val mono: String = "0.5",
  val eosino: String = "0.15",
  val baso: String = "0.05",
  val plt: String = "240",
  val vpm: String = "",
  val rdw: String = "",
  val interpretation: BiologicalInterpretation? = null
)

data class UeCreatFormState(
  val urea: String = "5.5",
  val creat: String = "85",
  val na: String = "140",
  val k: String = "4.2",
  val cl: String = "102",
  val hco3: String = "24",
  val age: String = "55",
  val weight: String = "70",
  val isFemale: Boolean = false,
  val interpretation: BiologicalInterpretation? = null
)

data class HepaticFormState(
  val alat: String = "28",
  val asat: String = "25",
  val pal: String = "80",
  val ggt: String = "22",
  val biliTotal: String = "12",
  val biliConj: String = "3",
  val lipase: String = "35",
  val interpretation: BiologicalInterpretation? = null
)

data class InflamCoagFormState(
  val crp: String = "3.2",
  val vs: String = "12",
  val pct: String = "0.05",
  val tp: String = "92",
  val inr: String = "1.0",
  val tcaRatio: String = "1.05",
  val dDimeres: String = "280",
  val fibrinogene: String = "3.1",
  val interpretation: BiologicalInterpretation? = null
)

data class UrineDipstickFormState(
  val leucocytes: String = "Négatif",
  val nitrites: String = "Négatif",
  val proteinurie: String = "Négatif",
  val hematurie: String = "Négatif",
  val cetones: String = "Négatif",
  val glucose: String = "Négatif",
  val interpretation: BiologicalInterpretation? = null
)

data class GlasgowState(
  val eyeScore: Int = 4,    // 1-4
  val verbalScore: Int = 5, // 1-5
  val motorScore: Int = 6,  // 1-6
  val interpretation: BiologicalInterpretation? = null
)

class LabsViewModel(private val repository: MedicalRepository) : ViewModel() {

  private val _nfsState = MutableStateFlow(NfsFormState())
  val nfsState: StateFlow<NfsFormState> = _nfsState.asStateFlow()

  private val _ueCreatState = MutableStateFlow(UeCreatFormState())
  val ueCreatState: StateFlow<UeCreatFormState> = _ueCreatState.asStateFlow()

  private val _hepaticState = MutableStateFlow(HepaticFormState())
  val hepaticState: StateFlow<HepaticFormState> = _hepaticState.asStateFlow()

  private val _inflamCoagState = MutableStateFlow(InflamCoagFormState())
  val inflamCoagState: StateFlow<InflamCoagFormState> = _inflamCoagState.asStateFlow()

  private val _urineState = MutableStateFlow(UrineDipstickFormState())
  val urineState: StateFlow<UrineDipstickFormState> = _urineState.asStateFlow()

  private val _glasgowState = MutableStateFlow(GlasgowState())
  val glasgowState: StateFlow<GlasgowState> = _glasgowState.asStateFlow()

  val allLabResults: StateFlow<List<LabResultEntity>> = repository.getAllLabResults()
    .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

  init {
    calculateNfs()
    calculateUeCreat()
    calculateHepatic()
    calculateInflamCoag()
    calculateUrine()
    calculateGlasgow()
  }

  // NFS
  fun updateNfsField(
    hb: String = _nfsState.value.hb,
    ht: String = _nfsState.value.ht,
    gr: String = _nfsState.value.gr,
    retic: String = _nfsState.value.retic,
    gb: String = _nfsState.value.gb,
    pnn: String = _nfsState.value.pnn,
    lympho: String = _nfsState.value.lympho,
    mono: String = _nfsState.value.mono,
    eosino: String = _nfsState.value.eosino,
    baso: String = _nfsState.value.baso,
    plt: String = _nfsState.value.plt,
    vpm: String = _nfsState.value.vpm,
    rdw: String = _nfsState.value.rdw
  ) {
    _nfsState.value = _nfsState.value.copy(
      hb = hb, ht = ht, gr = gr, retic = retic, gb = gb, pnn = pnn,
      lympho = lympho, mono = mono, eosino = eosino, baso = baso, plt = plt,
      vpm = vpm, rdw = rdw
    )
    calculateNfs()
  }

  fun calculateNfs() {
    val s = _nfsState.value
    val input = NfsInput(
      hb = s.hb.toFloatOrNull(),
      ht = s.ht.toFloatOrNull(),
      gr = s.gr.toFloatOrNull(),
      reticulocytes = s.retic.toFloatOrNull(),
      gb = s.gb.toFloatOrNull(),
      pnn = s.pnn.toFloatOrNull(),
      lympho = s.lympho.toFloatOrNull(),
      mono = s.mono.toFloatOrNull(),
      eosino = s.eosino.toFloatOrNull(),
      baso = s.baso.toFloatOrNull(),
      plaquettes = s.plt.toFloatOrNull(),
      vpm = s.vpm.toFloatOrNull(),
      rdw = s.rdw.toFloatOrNull()
    )
    val interp = MedicalInterpreter.interpretNfs(input)
    _nfsState.value = _nfsState.value.copy(interpretation = interp)
  }

  // UECréat
  fun updateUeCreatField(
    urea: String = _ueCreatState.value.urea,
    creat: String = _ueCreatState.value.creat,
    na: String = _ueCreatState.value.na,
    k: String = _ueCreatState.value.k,
    cl: String = _ueCreatState.value.cl,
    hco3: String = _ueCreatState.value.hco3,
    age: String = _ueCreatState.value.age,
    weight: String = _ueCreatState.value.weight,
    isFemale: Boolean = _ueCreatState.value.isFemale
  ) {
    _ueCreatState.value = _ueCreatState.value.copy(
      urea = urea, creat = creat, na = na, k = k, cl = cl, hco3 = hco3,
      age = age, weight = weight, isFemale = isFemale
    )
    calculateUeCreat()
  }

  fun calculateUeCreat() {
    val s = _ueCreatState.value
    val input = UeCreatInput(
      urea = s.urea.toFloatOrNull(),
      creat = s.creat.toFloatOrNull(),
      na = s.na.toFloatOrNull(),
      k = s.k.toFloatOrNull(),
      cl = s.cl.toFloatOrNull(),
      hco3 = s.hco3.toFloatOrNull(),
      age = s.age.toIntOrNull() ?: 50,
      weightKg = s.weight.toFloatOrNull() ?: 70f,
      isFemale = s.isFemale
    )
    val interp = MedicalInterpreter.interpretUeCreat(input)
    _ueCreatState.value = _ueCreatState.value.copy(interpretation = interp)
  }

  // Bilan Hépatique
  fun updateHepaticField(
    alat: String = _hepaticState.value.alat,
    asat: String = _hepaticState.value.asat,
    pal: String = _hepaticState.value.pal,
    ggt: String = _hepaticState.value.ggt,
    biliTotal: String = _hepaticState.value.biliTotal,
    biliConj: String = _hepaticState.value.biliConj,
    lipase: String = _hepaticState.value.lipase
  ) {
    _hepaticState.value = _hepaticState.value.copy(
      alat = alat, asat = asat, pal = pal, ggt = ggt,
      biliTotal = biliTotal, biliConj = biliConj, lipase = lipase
    )
    calculateHepatic()
  }

  fun calculateHepatic() {
    val s = _hepaticState.value
    val input = HepaticInput(
      alat = s.alat.toFloatOrNull(),
      asat = s.asat.toFloatOrNull(),
      pal = s.pal.toFloatOrNull(),
      ggt = s.ggt.toFloatOrNull(),
      biliTotal = s.biliTotal.toFloatOrNull(),
      biliConj = s.biliConj.toFloatOrNull(),
      lipase = s.lipase.toFloatOrNull()
    )
    val interp = MedicalInterpreter.interpretHepatic(input)
    _hepaticState.value = _hepaticState.value.copy(interpretation = interp)
  }

  // Bilan Inflammatoire & Coagulation
  fun updateInflamCoagField(
    crp: String = _inflamCoagState.value.crp,
    vs: String = _inflamCoagState.value.vs,
    pct: String = _inflamCoagState.value.pct,
    tp: String = _inflamCoagState.value.tp,
    inr: String = _inflamCoagState.value.inr,
    tcaRatio: String = _inflamCoagState.value.tcaRatio,
    dDimeres: String = _inflamCoagState.value.dDimeres,
    fibrinogene: String = _inflamCoagState.value.fibrinogene
  ) {
    _inflamCoagState.value = _inflamCoagState.value.copy(
      crp = crp, vs = vs, pct = pct, tp = tp, inr = inr,
      tcaRatio = tcaRatio, dDimeres = dDimeres, fibrinogene = fibrinogene
    )
    calculateInflamCoag()
  }

  fun calculateInflamCoag() {
    val s = _inflamCoagState.value
    val input = InflamCoagInput(
      crp = s.crp.toFloatOrNull(),
      vs = s.vs.toFloatOrNull(),
      pct = s.pct.toFloatOrNull(),
      tp = s.tp.toFloatOrNull(),
      inr = s.inr.toFloatOrNull(),
      tcaRatio = s.tcaRatio.toFloatOrNull(),
      dDimeres = s.dDimeres.toFloatOrNull(),
      fibrinogene = s.fibrinogene.toFloatOrNull()
    )
    val interp = MedicalInterpreter.interpretInflamCoag(input)
    _inflamCoagState.value = _inflamCoagState.value.copy(interpretation = interp)
  }

  // Bandelette Urinaire
  fun updateUrineField(
    leucocytes: String = _urineState.value.leucocytes,
    nitrites: String = _urineState.value.nitrites,
    proteinurie: String = _urineState.value.proteinurie,
    hematurie: String = _urineState.value.hematurie,
    cetones: String = _urineState.value.cetones,
    glucose: String = _urineState.value.glucose
  ) {
    _urineState.value = _urineState.value.copy(
      leucocytes = leucocytes, nitrites = nitrites, proteinurie = proteinurie,
      hematurie = hematurie, cetones = cetones, glucose = glucose
    )
    calculateUrine()
  }

  fun calculateUrine() {
    val s = _urineState.value
    val input = UrineDipstickInput(
      leucocytes = s.leucocytes,
      nitrites = s.nitrites,
      proteinurie = s.proteinurie,
      hematurie = s.hematurie,
      cetones = s.cetones,
      glucose = s.glucose
    )
    val interp = MedicalInterpreter.interpretUrine(input)
    _urineState.value = _urineState.value.copy(interpretation = interp)
  }

  // Glasgow
  fun setGlasgowEye(score: Int) {
    _glasgowState.value = _glasgowState.value.copy(eyeScore = score)
    calculateGlasgow()
  }

  fun setGlasgowVerbal(score: Int) {
    _glasgowState.value = _glasgowState.value.copy(verbalScore = score)
    calculateGlasgow()
  }

  fun setGlasgowMotor(score: Int) {
    _glasgowState.value = _glasgowState.value.copy(motorScore = score)
    calculateGlasgow()
  }

  private fun calculateGlasgow() {
    val s = _glasgowState.value
    val interp = MedicalInterpreter.interpretGlasgow(s.eyeScore, s.verbalScore, s.motorScore)
    _glasgowState.value = _glasgowState.value.copy(interpretation = interp)
  }

  fun saveResult(type: String, summary: String, interpretation: String, alertLevel: String, onDone: () -> Unit) {
    val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.FRANCE)
    val entity = LabResultEntity(
      patientId = null,
      testType = type,
      date = sdf.format(Date()),
      summaryTitle = summary,
      valuesMapJson = "",
      interpretationText = interpretation,
      alertLevel = alertLevel
    )
    viewModelScope.launch {
      repository.insertLabResult(entity)
      onDone()
    }
  }
}
