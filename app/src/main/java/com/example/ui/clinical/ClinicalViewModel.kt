package com.example.ui.clinical

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.entities.ClinicalObservationEntity
import com.example.data.local.entities.ClinicalUpdateEntity
import com.example.data.local.entities.PatientEntity
import com.example.data.medical.MedicalInterpreter
import com.example.data.repository.MedicalRepository
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

data class ObservationFormState(
  // Patient
  val patientId: Long? = null,
  val patientName: String = "",
  val patientAge: String = "45",
  val patientGender: String = "Homme",
  val admissionDate: String = "02/11/2026",
  val department: String = "Médecine Interne",
  val roomNumber: String = "Chambre 101",

  // Sections
  val admissionReason: String = "",
  val historyOfIllness: String = "",
  val pastMedicalHistory: String = "",
  val pastSurgicalHistory: String = "",
  val familyHistory: String = "",
  val allergies: String = "",
  val lifestyle: String = "",
  val generalExam: String = "",
  val cardiovascularExam: String = "",
  val respiratoryExam: String = "",
  val digestiveExam: String = "",
  val neurologicalExam: String = "",
  val locomotorExam: String = "",
  val urinaryExam: String = "",
  val otherExams: String = "",
  val complementaryExams: String = "",
  val clinicalSynthesis: String = "",
  val diagnosticHypotheses: String = "",

  // Generated Text
  val generatedObservationText: String = "",
  val isDraftGenerated: Boolean = false,
  val isSavedSuccess: Boolean = false
)

data class UpdateFormState(
  val patientId: Long? = null,
  val patientName: String = "",
  val isNewPatientMode: Boolean = false,
  val newPatientName: String = "",
  val newPatientAge: String = "50",
  val newPatientGender: String = "Homme",
  val department: String = "Médecine Interne",
  val hospitalDay: String = "J2",
  val date: String = "02/11/2026",
  val dailyPoint: String = "",
  val generalState: String = "",
  val physicalExam: String = "",
  val newComplementaryExams: String = "",
  val clinicalConclusion: String = "",
  val treatmentAndPlan: String = "",
  val generatedUpdateText: String = "",
  val isDraftGenerated: Boolean = false,
  val isSavedSuccess: Boolean = false
)

data class MethodStep(
  val number: Int,
  val title: String,
  val whatToLookFor: String,
  val questionsToAsk: List<String>,
  val elementsToExamine: List<String>,
  val thingsToNote: List<String>,
  val pedagogicalTips: String,
  val phrasingExample: String
)

class ClinicalViewModel(private val repository: MedicalRepository) : ViewModel() {

  private val _obsForm = MutableStateFlow(ObservationFormState())
  val obsForm: StateFlow<ObservationFormState> = _obsForm.asStateFlow()

  private val _updateForm = MutableStateFlow(UpdateFormState())
  val updateForm: StateFlow<UpdateFormState> = _updateForm.asStateFlow()

  val patients: StateFlow<List<PatientEntity>> = repository.getAllPatients()
    .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

  val allObservations: StateFlow<List<ClinicalObservationEntity>> = repository.getAllObservations()
    .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

  val allUpdates: StateFlow<List<ClinicalUpdateEntity>> = repository.getAllUpdates()
    .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

  // Guide Méthodologique de l'observation médicale
  val methodSteps: List<MethodStep> = listOf(
    MethodStep(
      number = 1,
      title = "Identification & Données Administratives",
      whatToLookFor = "Identité exacte, pseudonyme ou initiales, âge, profession, date et mode d'entrée (urgences ou programmée).",
      questionsToAsk = listOf("Quel est votre âge et votre profession habituelle ?", "Par quel biais êtes-vous arrivé à l'hôpital ?"),
      elementsToExamine = listOf("Vérifier le bracelet d'identité", "Recueillir la personne de confiance"),
      thingsToNote = listOf("Âge exact", "Service et date d'admission", "Statut d'autonomie antérieure"),
      pedagogicalTips = "L'âge et la profession orientent déjà vers certaines pathologies spécifiques (expositions toxiques, risques professionnels).",
      phrasingExample = "Patient de 58 ans, enseignant, admis le 02/11/2026 via le service des urgences pour prise en charge d'une dyspnée aiguë."
    ),
    MethodStep(
      number = 2,
      title = "Motif d'Admission",
      whatToLookFor = "Le maître-symptôme ou la plainte principale ayant motivé la consultation ou le recours aux soins.",
      questionsToAsk = listOf("Quelle est la raison principale de votre venue aujourd'hui ?", "Quel est le problème le plus gênant ?"),
      elementsToExamine = listOf("Identifier le symptôme cardinal"),
      thingsToNote = listOf("Formuler en termes médicaux précis sans préjuger du diagnostic"),
      pedagogicalTips = "Ne jamais confondre le motif d'admission (ex: douleur thoracique) avec une étiquette diagnostique prématurée (ex: infarctus).",
      phrasingExample = "Admis pour exploration d'une douleur thoracique rétro-sternale constrictive d'apparition récente."
    ),
    MethodStep(
      number = 3,
      title = "Histoire de la Maladie (HDM)",
      whatToLookFor = "Chronologie détaillée, mode d'installation (brutal, progressif), caractéristiques sémiologiques, facteurs déclenchants et traitements déjà reçus.",
      questionsToAsk = listOf(
        "Quand ont débuté exactement les symptômes ?",
        "Comment a débuté la douleur (coup de poignard, insidieuse) ?",
        "Avez-vous pris des médicaments avant de venir ?"
      ),
      elementsToExamine = listOf("Rechercher les signes d'accompagnement (sueurs, nausées, fièvre)"),
      thingsToNote = listOf("Cinétique d'évolution", "Efficacité des antalgiques", "Signes négatifs pertinents"),
      pedagogicalTips = "Toujours préciser les signes négatifs pertinents (ex: pas d'hémoptysie, pas de fièvre, pas de traumatisme).",
      phrasingExample = "L'histoire de la maladie remonte à 48 heures, marquée par l'installation brutale d'un point de côté basi-thoracique droit..."
    ),
    MethodStep(
      number = 4,
      title = "Antécédents & Traitements Personnels",
      whatToLookFor = "Antécédents médicaux, chirurgicaux, gynéco-obstétricaux, familiaux, toxiques (tabac en paquets-années, alcool) et ordonnance actuelle.",
      questionsToAsk = listOf("Avez-vous des maladies chroniques ?", "Avez-vous déjà été opéré ?", "Fumez-vous ? Avez-vous des allergies ?"),
      elementsToExamine = listOf("Examen des cicatrices abdominales/thoraciques", "Vérification des ordonnances apportées"),
      thingsToNote = listOf("Quantification du tabagisme en PA", "Allergies médicamenteuses majeures"),
      pedagogicalTips = "Toujours interroger précisément sur les allergies (nature exacte de la réaction : urticaire, choc anaphylactique vs intolérance digestive).",
      phrasingExample = "Antécédent d'HTA traitée par Amlodipine 5mg, appendicectomie dans l'enfance. Tabagisme sevré à 20 PA. Pas d'allergie connue."
    ),
    MethodStep(
      number = 5,
      title = "Examen Général & Constantes",
      whatToLookFor = "État général (score OMS/ECOG 0 à 4), faciès, conjonctives, signes de déshydratation, cyanose, ictère, sueurs, constantes vitales complètes.",
      questionsToAsk = listOf("Avez-vous perdu du poids récemment ?", "Comment est votre appétit ?"),
      elementsToExamine = listOf("Température, PA aux deux bras, FC, FR, SpO2, Glycémie, Poids/Taille"),
      thingsToNote = listOf("Score OMS", "Paramètres vitaux précis"),
      pedagogicalTips = "La fréquence respiratoire est la constante vitale la plus prédictive d'une dégradation clinique mais la plus souvent négligée !",
      phrasingExample = "Patient en bon état général (OMS 1), apyrétique (37.1°C), TA 128/78 mmHg, FC 74 bpm régulière, FR 16/min, SpO2 98% en air libre."
    ),
    MethodStep(
      number = 6,
      title = "Examen Physique Appareil par Appareil",
      whatToLookFor = "Examen méthodique, bilatéral et comparatif : Cardiovasculaire, Respiratoire, Digestif, Neurologique, Locomoteur, Urinaire.",
      questionsToAsk = listOf("Questions ciblées selon l'appareil exploré"),
      elementsToExamine = listOf(
        "CV : B1/B2, souffles, OMI, pouls périphériques, turgescence jugulaire",
        "Resp : Murmure vésiculaire, crépitants, sibilants, percussion",
        "Digestif : Palpation, orifices herniaires, bruits hydro-aériques",
        "Neuro : Conscience GCS, paires crâniennes, force, ROT, sensibilité"
      ),
      thingsToNote = listOf("Noter obligatoirement le signe négatif quand l'examen d'un appareil est normal"),
      pedagogicalTips = "Suivre la règle immuable de l'examen physique : Inspection → Palpation → Percussion → Auscultation.",
      phrasingExample = "Auscultation cardiaque : B1 et B2 bien perçus sans souffle surajouté. Auscultation pulmonaire : murmure vésiculaire symétrique..."
    ),
    MethodStep(
      number = 7,
      title = "Synthèse Clinique & Hypothèses Diagnostiques",
      whatToLookFor = "Regroupement des données recueillies en un ou plusieurs grands syndromes cliniques, discussion hiérarchisée des hypothèses.",
      questionsToAsk = listOf("Quel est le diagnostic le plus probable ?", "Quelles urgences vitales éliminer absolument ?"),
      elementsToExamine = listOf("Synthétiser en quelques lignes claires"),
      thingsToNote = listOf("Hypothèse principale argumentée", "Diagnostics différentiels et CAT immédiate"),
      pedagogicalTips = "Une bonne observation se termine toujours par une proposition claire de stratégie d'investigation paraclinique et thérapeutique.",
      phrasingExample = "En synthèse, patient de 58 ans présentant un syndrome coronarien aigu probable sans sus-décalage du segment ST..."
    )
  )

  fun selectPatientForObservation(patient: PatientEntity) {
    _obsForm.value = _obsForm.value.copy(
      patientId = patient.id,
      patientName = patient.fullNameOrAlias,
      patientAge = patient.age.toString(),
      patientGender = patient.gender,
      department = patient.department,
      roomNumber = patient.roomNumber,
      admissionDate = patient.admissionDate
    )
  }

  fun updateObsFields(
    admissionReason: String = _obsForm.value.admissionReason,
    historyOfIllness: String = _obsForm.value.historyOfIllness,
    pastMedical: String = _obsForm.value.pastMedicalHistory,
    pastSurgical: String = _obsForm.value.pastSurgicalHistory,
    familyHistory: String = _obsForm.value.familyHistory,
    allergies: String = _obsForm.value.allergies,
    lifestyle: String = _obsForm.value.lifestyle,
    generalExam: String = _obsForm.value.generalExam,
    cvExam: String = _obsForm.value.cardiovascularExam,
    respExam: String = _obsForm.value.respiratoryExam,
    digestiveExam: String = _obsForm.value.digestiveExam,
    neuroExam: String = _obsForm.value.neurologicalExam,
    locomotorExam: String = _obsForm.value.locomotorExam,
    urinaryExam: String = _obsForm.value.urinaryExam,
    otherExams: String = _obsForm.value.otherExams,
    compExams: String = _obsForm.value.complementaryExams,
    synthesis: String = _obsForm.value.clinicalSynthesis,
    hypotheses: String = _obsForm.value.diagnosticHypotheses
  ) {
    _obsForm.value = _obsForm.value.copy(
      admissionReason = admissionReason,
      historyOfIllness = historyOfIllness,
      pastMedicalHistory = pastMedical,
      pastSurgicalHistory = pastSurgical,
      familyHistory = familyHistory,
      allergies = allergies,
      lifestyle = lifestyle,
      generalExam = generalExam,
      cardiovascularExam = cvExam,
      respiratoryExam = respExam,
      digestiveExam = digestiveExam,
      neurologicalExam = neuroExam,
      locomotorExam = locomotorExam,
      urinaryExam = urinaryExam,
      otherExams = otherExams,
      complementaryExams = compExams,
      clinicalSynthesis = synthesis,
      diagnosticHypotheses = hypotheses
    )
  }

  fun updatePatientIdFields(
    name: String,
    age: String,
    gender: String,
    dept: String,
    room: String
  ) {
    _obsForm.value = _obsForm.value.copy(
      patientName = name,
      patientAge = age,
      patientGender = gender,
      department = dept,
      roomNumber = room
    )
  }

  // ══════════════════════════════════════════════════════
  // GÉNÉRER MON OBSERVATION
  // ══════════════════════════════════════════════════════
  fun generateObservation() {
    val f = _obsForm.value
    val text = MedicalInterpreter.generateObservationText(
      patientName = f.patientName.ifBlank { "Patient Anonyme" },
      patientAge = f.patientAge.toIntOrNull() ?: 45,
      patientGender = f.patientGender,
      department = f.department,
      admissionReason = f.admissionReason,
      historyOfIllness = f.historyOfIllness,
      pastMedical = f.pastMedicalHistory,
      pastSurgical = f.pastSurgicalHistory,
      allergies = f.allergies,
      lifestyle = f.lifestyle,
      generalExam = f.generalExam,
      cvExam = f.cardiovascularExam,
      respExam = f.respiratoryExam,
      digestiveExam = f.digestiveExam,
      neuroExam = f.neurologicalExam,
      locomotorExam = f.locomotorExam,
      urinaryExam = f.urinaryExam,
      otherExams = f.otherExams,
      complementaryExams = f.complementaryExams,
      clinicalSynthesis = f.clinicalSynthesis,
      hypotheses = f.diagnosticHypotheses
    )

    _obsForm.value = _obsForm.value.copy(
      generatedObservationText = text,
      isDraftGenerated = true
    )
  }

  fun updateGeneratedObservationText(text: String) {
    _obsForm.value = _obsForm.value.copy(generatedObservationText = text)
  }

  fun saveObservation(onComplete: () -> Unit) {
    viewModelScope.launch {
      val f = _obsForm.value
      var pId = f.patientId

      // If no existing patient was selected, create patient record first!
      if (pId == null) {
        val newPatient = PatientEntity(
          fullNameOrAlias = f.patientName.ifBlank { "Nouveau Patient (Observation)" },
          age = f.patientAge.toIntOrNull() ?: 50,
          gender = f.patientGender,
          admissionDate = f.admissionDate,
          department = f.department,
          roomNumber = f.roomNumber,
          primaryCondition = f.admissionReason
        )
        pId = repository.insertPatient(newPatient)
      }

      val obs = ClinicalObservationEntity(
        patientId = pId,
        admissionReason = f.admissionReason,
        historyOfIllness = f.historyOfIllness,
        pastMedicalHistory = f.pastMedicalHistory,
        pastSurgicalHistory = f.pastSurgicalHistory,
        familyHistory = f.familyHistory,
        allergies = f.allergies,
        lifestyle = f.lifestyle,
        generalExam = f.generalExam,
        cardiovascularExam = f.cardiovascularExam,
        respiratoryExam = f.respiratoryExam,
        digestiveExam = f.digestiveExam,
        neurologicalExam = f.neurologicalExam,
        locomotorExam = f.locomotorExam,
        urinaryExam = f.urinaryExam,
        otherExams = f.otherExams,
        complementaryExams = f.complementaryExams,
        clinicalSynthesis = f.clinicalSynthesis,
        diagnosticHypotheses = f.diagnosticHypotheses,
        generatedDraft = f.generatedObservationText,
        finalizedText = f.generatedObservationText,
        version = 1
      )
      repository.insertObservation(obs)
      _obsForm.value = _obsForm.value.copy(isSavedSuccess = true)
      onComplete()
    }
  }

  // ══════════════════════════════════════════════════════
  // MISE À JOUR CLINIQUE (VISITE QUOTIDIENNE)
  // ══════════════════════════════════════════════════════
  fun setUpdatePatient(patient: PatientEntity?) {
    if (patient != null) {
      _updateForm.value = _updateForm.value.copy(
        patientId = patient.id,
        patientName = patient.fullNameOrAlias,
        department = patient.department,
        isNewPatientMode = false
      )
    } else {
      _updateForm.value = _updateForm.value.copy(
        patientId = null,
        isNewPatientMode = true
      )
    }
  }

  fun updateDailyUpdateFields(
    hospitalDay: String = _updateForm.value.hospitalDay,
    date: String = _updateForm.value.date,
    dailyPoint: String = _updateForm.value.dailyPoint,
    generalState: String = _updateForm.value.generalState,
    physicalExam: String = _updateForm.value.physicalExam,
    newExams: String = _updateForm.value.newComplementaryExams,
    conclusion: String = _updateForm.value.clinicalConclusion,
    treatmentPlan: String = _updateForm.value.treatmentAndPlan,
    newPatientName: String = _updateForm.value.newPatientName,
    department: String = _updateForm.value.department
  ) {
    _updateForm.value = _updateForm.value.copy(
      hospitalDay = hospitalDay,
      date = date,
      dailyPoint = dailyPoint,
      generalState = generalState,
      physicalExam = physicalExam,
      newComplementaryExams = newExams,
      clinicalConclusion = conclusion,
      treatmentAndPlan = treatmentPlan,
      newPatientName = newPatientName,
      department = department
    )
  }

  fun generateDailyUpdateDraft() {
    val f = _updateForm.value
    val name = if (f.isNewPatientMode) f.newPatientName.ifBlank { "Patient (Visite)" } else f.patientName
    val text = MedicalInterpreter.generateDailyUpdateText(
      patientName = name,
      hospitalDay = f.hospitalDay,
      date = f.date,
      department = f.department,
      dailyPoint = f.dailyPoint,
      generalState = f.generalState,
      physicalExam = f.physicalExam,
      newExams = f.newComplementaryExams,
      conclusion = f.clinicalConclusion,
      treatmentPlan = f.treatmentAndPlan
    )
    _updateForm.value = _updateForm.value.copy(
      generatedUpdateText = text,
      isDraftGenerated = true
    )
  }

  fun updateGeneratedDailyUpdateText(text: String) {
    _updateForm.value = _updateForm.value.copy(generatedUpdateText = text)
  }

  fun saveDailyUpdate(onComplete: () -> Unit) {
    viewModelScope.launch {
      val f = _updateForm.value
      var pId = f.patientId

      if (pId == null || f.isNewPatientMode) {
        val newPatient = PatientEntity(
          fullNameOrAlias = f.newPatientName.ifBlank { "Patient Entrée Directe" },
          age = f.newPatientAge.toIntOrNull() ?: 50,
          gender = f.newPatientGender,
          admissionDate = f.date,
          department = f.department,
          roomNumber = "Chambre non assignée",
          primaryCondition = f.dailyPoint.take(50)
        )
        pId = repository.insertPatient(newPatient)
      }

      val updateEntity = ClinicalUpdateEntity(
        patientId = pId,
        hospitalDay = f.hospitalDay,
        date = f.date,
        dailyClinicalPoint = f.dailyPoint,
        generalState = f.generalState,
        physicalExamApparatus = f.physicalExam,
        newComplementaryExams = f.newComplementaryExams,
        clinicalConclusion = f.clinicalConclusion,
        treatmentAndPlan = f.treatmentAndPlan,
        generatedDraft = f.generatedUpdateText,
        finalizedText = f.generatedUpdateText
      )
      repository.insertUpdate(updateEntity)
      _updateForm.value = _updateForm.value.copy(isSavedSuccess = true)
      onComplete()
    }
  }
}
