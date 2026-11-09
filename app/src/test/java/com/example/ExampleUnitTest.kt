package com.example

import com.example.data.medical.HepaticInput
import com.example.data.medical.InflamCoagInput
import com.example.data.medical.MedicalInterpreter
import com.example.data.medical.NfsInput
import com.example.data.medical.UeCreatInput
import com.example.data.medical.UrineDipstickInput
import com.example.data.model.SemiologyCategory
import com.example.data.model.SemiologyDataProvider
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ExampleUnitTest {

  @Test
  fun testSemiologyQuotasAndQuality() {
    val questions = SemiologyDataProvider.quizQuestions
    val sheets = SemiologyDataProvider.sheets

    // Verify overall total questions >= 90
    assertTrue("Total quiz questions must be >= 90, got: ${questions.size}", questions.size >= 90)

    // Verify quotas per apparatus
    val cardioQuestions = questions.filter { it.category == SemiologyCategory.CARDIOVASCULAR }
    val respQuestions = questions.filter { it.category == SemiologyCategory.RESPIRATORY }
    val digQuestions = questions.filter { it.category == SemiologyCategory.DIGESTIVE }
    val neuroQuestions = questions.filter { it.category == SemiologyCategory.NEUROLOGICAL }
    val uriQuestions = questions.filter { it.category == SemiologyCategory.URINARY }
    val rhumQuestions = questions.filter { it.category == SemiologyCategory.RHEUMATOLOGY }
    val dermQuestions = questions.filter { it.category == SemiologyCategory.DERMATOLOGY }
    val genQuestions = questions.filter { it.category == SemiologyCategory.GENERAL }

    assertTrue("Cardio must have >= 20 questions, got: ${cardioQuestions.size}", cardioQuestions.size >= 20)
    assertTrue("Resp must have >= 10 questions, got: ${respQuestions.size}", respQuestions.size >= 10)
    assertTrue("Digestif must have >= 10 questions, got: ${digQuestions.size}", digQuestions.size >= 10)
    assertTrue("Neuro must have >= 10 questions, got: ${neuroQuestions.size}", neuroQuestions.size >= 10)
    assertTrue("Urinaire must have >= 10 questions, got: ${uriQuestions.size}", uriQuestions.size >= 10)
    assertTrue("Rhumatologie must have >= 10 questions, got: ${rhumQuestions.size}", rhumQuestions.size >= 10)
    assertTrue("Dermatologie must have >= 10 questions, got: ${dermQuestions.size}", dermQuestions.size >= 10)
    assertTrue("Général must have >= 10 questions, got: ${genQuestions.size}", genQuestions.size >= 10)

    // Verify each question has exactly 4 options and wrongOptionsExplanations
    questions.forEach { q ->
      assertEquals("Question ${q.id} must have exactly 4 options", 4, q.options.size)
      assertTrue("Question ${q.id} has invalid correctOptionIndex", q.correctOptionIndex in 0..3)
      assertTrue("Question ${q.id} must have an explanation", q.explanation.isNotBlank())
      assertTrue("Question ${q.id} must explain why wrong answers are wrong", q.wrongOptionsExplanations.isNotEmpty())
    }

    // Verify sheets have the 14 mandatory sections filled
    sheets.forEach { s ->
      assertTrue("Sheet ${s.id} definition missing", s.definition.isNotBlank())
      assertTrue("Sheet ${s.id} physiopathology missing", s.physiopathology.isNotBlank())
      assertTrue("Sheet ${s.id} etiologies missing", s.etiologies.isNotEmpty())
      assertTrue("Sheet ${s.id} circumstances missing", s.circumstances.isNotBlank())
      assertTrue("Sheet ${s.id} anamnesis missing", s.anamnesis.isNotBlank())
      assertTrue("Sheet ${s.id} searchMethod missing", s.searchMethod.isNotBlank())
      assertTrue("Sheet ${s.id} physicalExam missing", s.physicalExam.isNotBlank())
      assertTrue("Sheet ${s.id} associatedSigns missing", s.associatedSigns.isNotEmpty())
      assertTrue("Sheet ${s.id} clinicalSignificance missing", s.clinicalSignificance.isNotBlank())
      assertTrue("Sheet ${s.id} differentialDiagnoses missing", s.differentialDiagnoses.isNotEmpty())
      assertTrue("Sheet ${s.id} complementaryExams missing", s.complementaryExams.isNotEmpty())
      assertTrue("Sheet ${s.id} gravitySigns missing", s.gravitySigns.isNotEmpty())
      assertTrue("Sheet ${s.id} pitfallsAndLimits missing", s.pitfallsAndLimits.isNotBlank())
      assertTrue("Sheet ${s.id} keyPoints missing", s.keyPoints.isNotEmpty())
    }
  }

  @Test
  fun testVitalsInterpretationNormal() {
    val result = MedicalInterpreter.interpretVitals(
      temp = 36.8f,
      pas = 120,
      pad = 80,
      fc = 72,
      fr = 16,
      spo2 = 98,
      glycemie = 0.95f,
      weight = 70f,
      height = 175f
    )
    assertEquals("NORMAL", result.alertLevel)
    assertTrue(result.computedMetrics.any { it.first.contains("PAM") })
    assertTrue(result.computedMetrics.any { it.first.contains("IMC") })
    assertTrue("Must have systematic points for vitals", result.systematicPoints.isNotEmpty())
  }

  @Test
  fun testVitalsInterpretationCriticalFeverAndHypotension() {
    val result = MedicalInterpreter.interpretVitals(
      temp = 39.5f,
      pas = 85,
      pad = 50,
      fc = 135,
      fr = 28,
      spo2 = 88,
      glycemie = 0.60f,
      weight = 65f,
      height = 170f
    )
    assertEquals("CRITICAL", result.alertLevel)
    assertTrue(result.precautions.isNotEmpty())
    assertTrue(result.systematicPoints.any { it.alertLevel == "CRITICAL" })
  }

  @Test
  fun testGlasgowScoreCalculation() {
    val gcsNormal = MedicalInterpreter.interpretGlasgow(4, 5, 6)
    assertEquals("NORMAL", gcsNormal.alertLevel)
    assertTrue(gcsNormal.title.contains("15 / 15"))

    val gcsCritical = MedicalInterpreter.interpretGlasgow(1, 1, 3)
    assertEquals("CRITICAL", gcsCritical.alertLevel)
    assertTrue(gcsCritical.title.contains("5 / 15"))
    assertTrue(gcsCritical.clinicalPearls.any { it.contains("intubation") })
  }

  @Test
  fun testNfsAnemiaAndPancytopenia() {
    val input = NfsInput(
      hb = 7.5f,
      ht = 24.0f,
      gr = 2.8f,
      reticulocytes = 30f,
      gb = 2.5f,
      pnn = 0.4f, // agranulocytose
      lympho = 1.5f,
      mono = 0.5f,
      eosino = 0.1f,
      baso = 0.05f,
      plaquettes = 12.0f // thrombopénie profonde
    )
    val result = MedicalInterpreter.interpretNfs(input)
    assertEquals("CRITICAL", result.alertLevel)
    assertTrue(result.details.any { it.contains("PANCYTOPENIE") || it.contains("PANCYTOPÉNIE") })
    assertTrue(result.details.any { it.contains("AGRANULOCYTOSE") })
  }

  @Test
  fun testHepaticCytolysisAndPancreatitis() {
    val input = HepaticInput(
      alat = 350f,
      asat = 280f,
      pal = 90f,
      ggt = 45f,
      biliTotal = 18f,
      biliConj = 4f,
      lipase = 240f
    )
    val result = MedicalInterpreter.interpretHepatic(input)
    assertEquals("CRITICAL", result.alertLevel)
    assertTrue(result.details.any { it.contains("CYTOLYSE") })
    assertTrue(result.details.any { it.contains("PANCRÉATITE") })
  }

  @Test
  fun testInflammationAndCoagulation() {
    val input = InflamCoagInput(
      crp = 180f,
      vs = 65f,
      pct = 3.5f,
      tp = 42f,
      inr = 2.4f,
      tcaRatio = 1.6f,
      fibrinogene = 1.2f,
      dDimeres = 3500f
    )
    val result = MedicalInterpreter.interpretInflamCoag(input)
    assertEquals("CRITICAL", result.alertLevel)
    assertTrue(result.details.any { it.contains("SEPSIS") })
    assertTrue(result.details.any { it.contains("TP") })
  }

  @Test
  fun testUrineDipstickInfection() {
    val input = UrineDipstickInput(
      leucocytes = "3+",
      nitrites = "Positif",
      proteinurie = "1+ (0.3 g/L)",
      hematurie = "Trace",
      cetones = "Négatif",
      glucose = "Négatif"
    )
    val result = MedicalInterpreter.interpretUrine(input)
    assertEquals("MODERATE", result.alertLevel)
    assertTrue(result.details.any { it.contains("INFECTION URINAIRE") })
  }

  @Test
  fun testObservationTextGeneration() {
    val text = MedicalInterpreter.generateObservationText(
      patientName = "M. Test",
      patientAge = 60,
      patientGender = "Homme",
      department = "Cardiologie",
      admissionReason = "Douleur thoracique",
      historyOfIllness = "Depuis 2 heures",
      pastMedical = "HTA",
      pastSurgical = "Néant",
      allergies = "Néant",
      lifestyle = "Tabac",
      generalExam = "Stable",
      cvExam = "B1 B2 réguliers",
      respExam = "MV normal",
      digestiveExam = "Souple",
      neuroExam = "Conscient",
      locomotorExam = "Libre",
      urinaryExam = "Normal",
      otherExams = "",
      complementaryExams = "ECG fait",
      clinicalSynthesis = "SCA probable",
      hypotheses = "Infarctus"
    )
    assertNotNull(text)
    assertTrue(text.contains("OBSERVATION MÉDICALE"))
    assertTrue(text.contains("M. Test"))
    assertTrue(text.contains("El Vita est un outil d’aide à l’observation"))
  }

  @Test
  fun testClinicalTrainingModulesCompleteness() {
    val anamnesisSkills = com.example.data.model.ClinicalTrainingDataProvider.anamnesisSkills
    assertEquals("Must have 14 anamnesis skills", 14, anamnesisSkills.size)
    anamnesisSkills.forEach { skill ->
      assertTrue("Skill ${skill.id} title missing", skill.title.isNotBlank())
      assertTrue("Skill ${skill.id} professionalPhrasings missing", skill.professionalPhrasings.isNotEmpty())
      assertTrue("Skill ${skill.id} errorsToAvoid missing", skill.errorsToAvoid.isNotEmpty())
      assertTrue("Skill ${skill.id} patient responses missing", skill.patientResponsesExamples.isNotEmpty())
      assertTrue("Skill ${skill.id} followUpQuestions missing", skill.followUpQuestions.isNotEmpty())
      assertEquals("Skill ${skill.id} exercise choices must have 4 options", 4, skill.practicalExerciseChoices.size)
      assertTrue("Skill ${skill.id} exercise correction missing", skill.practicalExerciseCorrection.isNotBlank())
    }

    val infoCategories = com.example.data.model.ClinicalTrainingDataProvider.infoCategoriesGuide
    assertEquals("Must have 9 info organization categories", 9, infoCategories.size)

    val exercises = com.example.data.model.ClinicalTrainingDataProvider.classificationExercises
    assertTrue("Must have classification exercises", exercises.size >= 4)

    val observationSkills = com.example.data.model.ClinicalTrainingDataProvider.observationSkills
    assertEquals("Must have 4 observation method skills", 4, observationSkills.size)

    val synthesisCases = com.example.data.model.ClinicalTrainingDataProvider.synthesisCases
    assertTrue("Must have clinical synthesis training cases", synthesisCases.size >= 2)
  }

  @Test
  fun testNfsDualLevelInterpretationStructure() {
    val input = NfsInput(
      hb = 9.2f, // Anémie
      ht = 21.0f, // VGM = 21 * 10 / 3.0 = 70 fL -> Microcytose
      gr = 3.0f,
      reticulocytes = 45f, // Arégénérative
      gb = 11.5f,
      pnn = 8.2f, // Polynucléose
      lympho = 2.0f,
      mono = 0.6f,
      eosino = 0.2f,
      baso = 0.05f,
      plaquettes = 520.0f // Thrombocytose réactionnelle
    )
    val result = MedicalInterpreter.interpretNfs(input)

    // Niveau A - Analyse Médicale Globale
    assertTrue("Normal parameters list should not be empty", result.normalParameters.isNotEmpty())
    assertTrue("Abnormal parameters list should contain Hb and VGM", result.abnormalParameters.any { it.contains("Hémoglobine") })
    assertTrue("Associations list should identify microcytic non-regenerative anemia with thrombocytosis", result.associationsList.isNotEmpty())
    assertTrue("Diagnostic orientations should suggest martial deficiency", result.diagnosticOrientations.any { it.contains("carence martiale") || it.contains("inflammatoire") })
    assertTrue("General conclusion should be formulated", result.generalConclusion.isNotBlank())
    assertTrue("Clinical context caveat must be present", result.clinicalContextCaveat.isNotBlank())

    // Niveau B - Analyse Pédagogique Approfondie
    assertTrue("Pedagogical mechanisms must include key physiological roles", result.pedagogicalMechanisms.size >= 5)
    assertTrue(result.pedagogicalMechanisms.any { it.first.contains("Volume Globulaire Moyen") })
    assertTrue(result.pedagogicalMechanisms.any { it.first.contains("Réticulocytes") })

    // Rubriques structurées de l'affichage final
    assertTrue("Red line summary must not be empty", result.redLineSummary.isNotBlank())
    assertTrue("White line summary must not be empty", result.whiteLineSummary.isNotBlank())
    assertTrue("Platelet line summary must not be empty", result.plateletLineSummary.isNotBlank())
    assertTrue("Professional conclusion must be present", result.professionalMedicalConclusion.isNotBlank())
    assertNotNull("Clinical explanation must be present", result.nfsExplanation)
  }

  @Test
  fun testNfsConclusionEntirelyNormal() {
    val input = NfsInput(
      hb = 14.0f,
      ht = 42.0f,
      gr = 4.6f,
      gb = 6.8f,
      pnn = 4.0f,
      lympho = 2.0f,
      mono = 0.5f,
      eosino = 0.15f,
      baso = 0.03f,
      plaquettes = 250.0f
    )
    val result = MedicalInterpreter.interpretNfs(input)
    assertEquals("NORMAL", result.alertLevel)
    assertTrue(result.professionalMedicalConclusion.contains("La NFS ne montre pas d'anomalie hématologique significative"))
    assertTrue(result.professionalMedicalConclusion.contains("trois lignées sanguines présentent des valeurs globalement dans les intervalles de référence"))
    assertTrue(result.identifiedAssociations.any { it.contains("les trois lignées sont dans les limites") || it.contains("Aucune association") })
  }

  @Test
  fun testNfsConclusionHyperleukocytosisIsolated() {
    val input = NfsInput(
      hb = 13.5f,
      ht = 41.0f,
      gr = 4.5f,
      gb = 14.5f,
      pnn = 11.0f, // Polynucléose neutrophile
      lympho = 2.5f,
      plaquettes = 260.0f
    )
    val result = MedicalInterpreter.interpretNfs(input)
    assertTrue(result.professionalMedicalConclusion.startsWith("La NFS a objectivé une hyperleucocytose"))
    assertTrue(result.professionalMedicalConclusion.contains("à prédominance neutrophile"))
    assertTrue(result.professionalMedicalConclusion.contains("conservation des lignées rouge et plaquettaire"))
  }

  @Test
  fun testNfsConclusionLeukopeniaIsolated() {
    val inputWithoutPnn = NfsInput(
      hb = 13.8f,
      ht = 41.5f,
      gr = 4.6f,
      gb = 2.8f,
      pnn = null,
      plaquettes = 240.0f
    )
    val result1 = MedicalInterpreter.interpretNfs(inputWithoutPnn)
    assertTrue(result1.professionalMedicalConclusion.contains("La NFS a objectivé une leucopénie, nécessitant une analyse de la formule leucocytaire"))

    val inputWithNeutropenia = NfsInput(
      hb = 13.8f,
      ht = 41.5f,
      gr = 4.6f,
      gb = 2.8f,
      pnn = 1.1f, // Neutropénie
      plaquettes = 240.0f
    )
    val result2 = MedicalInterpreter.interpretNfs(inputWithNeutropenia)
    assertTrue(result2.professionalMedicalConclusion.contains("leucopénie avec neutropénie"))
    assertTrue(result2.professionalMedicalConclusion.contains("conservation des lignées rouge et plaquettaire"))
  }

  @Test
  fun testNfsConclusionAnemiaIsolated() {
    val input = NfsInput(
      hb = 9.5f, // Anémie
      ht = 24.0f, // VGM = 75 (microcytaire)
      gr = 3.2f,
      gb = 6.2f,
      pnn = 3.8f,
      plaquettes = 230.0f
    )
    val result = MedicalInterpreter.interpretNfs(input)
    assertTrue(result.professionalMedicalConclusion.contains("La NFS a objectivé une anémie microcytaire"))
    assertTrue(result.professionalMedicalConclusion.contains("conservation des lignées blanche et plaquettaire"))
  }

  @Test
  fun testNfsConclusionThrombopeniaIsolated() {
    val input = NfsInput(
      hb = 13.5f,
      ht = 40.0f,
      gr = 4.5f,
      gb = 6.0f,
      pnn = 3.5f,
      plaquettes = 45.0f // Thrombopénie isolée
    )
    val result = MedicalInterpreter.interpretNfs(input)
    assertTrue(result.professionalMedicalConclusion.contains("La NFS a objectivé une thrombopénie isolée (45.0 G/L)"))
    assertTrue(result.professionalMedicalConclusion.contains("sans anomalie significative des autres lignées sanguines"))
  }

  @Test
  fun testNfsConclusionBicytopenia() {
    // Anémie + Thrombopénie (Lignée blanche préservée)
    val input = NfsInput(
      hb = 8.5f,
      ht = 26.0f,
      gr = 3.0f,
      gb = 6.5f, // Lignée blanche normale
      pnn = 3.9f,
      plaquettes = 40.0f // Thrombopénie
    )
    val result = MedicalInterpreter.interpretNfs(input)
    assertTrue(result.professionalMedicalConclusion.contains("bicytopénie associant une anémie"))
    assertTrue(result.professionalMedicalConclusion.contains("thrombopénie"))
    assertTrue(result.professionalMedicalConclusion.contains("conservation de la lignée blanche"))
  }

  @Test
  fun testNfsConclusionPancytopenia() {
    val input = NfsInput(
      hb = 7.0f,  // Anémie sévère
      ht = 21.0f,
      gr = 2.4f,
      gb = 2.1f,  // Leucopénie
      pnn = 0.8f,
      plaquettes = 18.0f // Thrombopénie
    )
    val result = MedicalInterpreter.interpretNfs(input)
    assertEquals("CRITICAL", result.alertLevel)
    assertTrue(result.professionalMedicalConclusion.contains("La NFS a objectivé une pancytopénie associant une anémie"))
    assertTrue(result.professionalMedicalConclusion.contains("une leucopénie"))
    assertTrue(result.professionalMedicalConclusion.contains("une thrombopénie"))
  }

  @Test
  fun testNfsConclusionCombinedAnemiaAndThrombocytosis() {
    val input = NfsInput(
      hb = 9.0f,
      ht = 22.0f, // VGM = 68.7 fL (microcytose)
      gr = 3.2f,
      gb = 7.0f,
      pnn = 4.2f,
      plaquettes = 540.0f // Thrombocytose réactionnelle
    )
    val result = MedicalInterpreter.interpretNfs(input)
    assertTrue(result.professionalMedicalConclusion.contains("La NFS a objectivé une anémie microcytaire"))
    assertTrue(result.professionalMedicalConclusion.contains("thrombocytose réactionnelle"))
    assertTrue(result.professionalMedicalConclusion.contains("conservation de la lignée blanche"))
  }
}
