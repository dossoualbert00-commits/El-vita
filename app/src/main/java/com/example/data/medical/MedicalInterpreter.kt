package com.example.data.medical

import java.util.Locale

data class VitalsInterpretation(
  val alertLevel: String, // "NORMAL", "MODERATE", "CRITICAL"
  val alertTitle: String,
  val explanations: List<String>,
  val precautions: List<String>,
  val computedMetrics: List<Pair<String, String>>,
  val systematicPoints: List<VitalSystematicAnalysis> = emptyList()
)

data class VitalSystematicAnalysis(
  val parameterName: String,
  val status: String, // "Normal", "Élevé", "Bas"
  val alertLevel: String, // "NORMAL", "MODERATE", "CRITICAL"
  val physiologicalSignificance: String,
  val physiopathology: String,
  val etiologies: List<String>,
  val clinicalSignsToSearch: List<String>,
  val usefulComplementaryExams: List<String>,
  val emergencyCare: String
)

data class NfsInput(
  val hb: Float?,                   // g/dL (norme 12 - 16 femme, 13 - 17 homme)
  val ht: Float? = null,            // % (norme 37 - 47 femme, 40 - 52 homme)
  val gr: Float? = null,            // T/L (norme 4.0 - 5.5)
  val reticulocytes: Float? = null, // G/L (norme 20 - 120 G/L)
  val gb: Float? = null,            // G/L (norme 4.0 - 10.0)
  val pnn: Float? = null,           // G/L (norme 1.5 - 7.0)
  val lympho: Float? = null,        // G/L (norme 1.5 - 4.0)
  val mono: Float? = null,          // G/L (norme 0.2 - 1.0)
  val eosino: Float? = null,        // G/L (norme 0.05 - 0.5)
  val baso: Float? = null,          // G/L (norme 0.01 - 0.1)
  val plaquettes: Float? = null,    // G/L (norme 150 - 400)
  val vpm: Float? = null,           // fL (norme 7 - 11)
  val rdw: Float? = null            // % (norme 11.5 - 14.5)
)

data class NfsClinicalExplanation(
  val significance: String,
  val affectedLines: String,
  val mechanismsAndCauses: List<String>,
  val clinicalSignsToSearch: List<String>,
  val recommendedExams: List<String>,
  val limitsAndCaveats: String
)

data class UeCreatInput(
  val urea: Float?,       // mmol/L (norme 2.5 - 7.5)
  val creat: Float?,      // µmol/L (norme 60 - 110)
  val na: Float?,         // mmol/L (norme 135 - 145)
  val k: Float?,          // mmol/L (norme 3.5 - 5.0)
  val cl: Float?,         // mmol/L (norme 98 - 106)
  val hco3: Float?,       // mmol/L (norme 22 - 28)
  val glycemia: Float? = null, // g/L (norme 0.70 - 1.10)
  val age: Int? = 50,
  val weightKg: Float? = 70f,
  val isFemale: Boolean = false
)

data class HepaticInput(
  val alat: Float?,       // UI/L (norme < 45)
  val asat: Float?,       // UI/L (norme < 35)
  val pal: Float?,        // UI/L (norme 40 - 130)
  val ggt: Float?,        // UI/L (norme < 50)
  val biliTotal: Float?,  // µmol/L (norme 5 - 21)
  val biliConj: Float?,   // µmol/L (norme < 5)
  val lipase: Float? = null // UI/L (norme < 60)
)

data class InflamCoagInput(
  val crp: Float?,        // mg/L (norme < 5)
  val vs: Float?,         // mm 1ère heure (norme < 15 homme, < 20 femme)
  val pct: Float?,        // ng/mL (norme < 0.1)
  val tp: Float?,         // % (norme 70 - 100)
  val inr: Float?,        // ratio (norme 0.8 - 1.2)
  val tcaRatio: Float?,   // ratio patient/témoin (norme 0.8 - 1.2)
  val fibrinogene: Float?, // g/L (norme 2 - 4)
  val dDimeres: Float?    // ng/mL (norme < 500)
)

data class UrineDipstickInput(
  val leucocytes: String, // "Négatif", "Trace", "1+", "2+", "3+"
  val nitrites: String,   // "Négatif", "Positif"
  val proteinurie: String, // "Négatif", "Trace", "1+ (0.3g)", "2+ (1g)", "3+ (3g)"
  val hematurie: String,  // "Négatif", "Trace", "1+", "2+", "3+"
  val cetones: String,    // "Négatif", "Trace", "1+", "2+", "3+"
  val glucose: String,    // "Négatif", "1+", "2+", "3+"
  val ph: Float? = 6.0f,
  val densite: Int? = 1015
)

data class BiologicalInterpretation(
  val alertLevel: String,
  val title: String,
  val summary: String,
  val details: List<String>,
  val clinicalPearls: List<String>,
  val computedMetrics: List<Pair<String, String>> = emptyList(),
  // Niveaux d'analyse structurés pour la NFS (et bilans biologiques)
  val generalAppreciation: String = "",
  val normalParameters: List<String> = emptyList(),
  val abnormalParameters: List<String> = emptyList(),
  val anomaliesAndSignificance: List<Pair<String, String>> = emptyList(),
  val associationsList: List<String> = emptyList(),
  val diagnosticOrientations: List<String> = emptyList(),
  val generalConclusion: String = "",
  val clinicalContextCaveat: String = "",
  val pedagogicalMechanisms: List<Pair<String, String>> = emptyList(),
  // Champs pour l'interprétation générale et la conclusion médicale professionnelle de la NFS
  val redLineSummary: String = "",
  val whiteLineSummary: String = "",
  val plateletLineSummary: String = "",
  val identifiedAssociations: List<String> = emptyList(),
  val professionalMedicalConclusion: String = "",
  val nfsExplanation: NfsClinicalExplanation? = null
)

object MedicalInterpreter {

  // 1. Interprétation complète des constantes vitales avec 8 points
  fun interpretVitals(
    temp: Float?,
    pas: Int?,
    pad: Int?,
    fc: Int?,
    fr: Int?,
    spo2: Int?,
    glycemie: Float?,
    weight: Float?,
    height: Float?
  ): VitalsInterpretation {
    val explanations = mutableListOf<String>()
    val precautions = mutableListOf<String>()
    val metrics = mutableListOf<Pair<String, String>>()
    val systematicPoints = mutableListOf<VitalSystematicAnalysis>()
    var maxSeverity = "NORMAL"

    fun raise(severity: String) {
      if (severity == "CRITICAL") maxSeverity = "CRITICAL"
      else if (severity == "MODERATE" && maxSeverity != "CRITICAL") maxSeverity = "MODERATE"
    }

    // Température
    if (temp != null) {
      val (status, sev, causes, signs, exams, cat) = when {
        temp < 35.0f -> Tuple6(
          "Bas (Hypothermie sévère)", "CRITICAL",
          listOf("Exposition prolongée au froid", "Intoxication éthylique aiguë", "Sepsis sévère du sujet âgé", "Coma myxœdémateux"),
          listOf("Bradypnée", "Frissons absents (stade terminal)", "Confusion, coma", "Allongement de l'onde J d'Osborn sur l'ECG"),
          listOf("ECG 12 dérivations", "Gazométrie artérielle", "Ionogramme sanguin", "Hémocultures"),
          "Réchauffement externe passif puis actif progressif (couvertures chauffantes, perfusion solutés tièdes à 38-40°C), surveillance ECG continue."
        )
        temp in 35.0f..35.9f -> Tuple6(
          "Bas (Hypothermie modérée)", "MODERATE",
          listOf("Environnement froid", "Sous-nutrition", "Médicaments sédatifs"),
          listOf("Pâleur", "Frissons vifs", "Polypnée réflexe"),
          listOf("Surveillance thermique horaire"),
          "Réchauffement externe passif, boissons chaudes si conscient, éliminer un sepsis torpide."
        )
        temp in 36.0f..37.5f -> Tuple6(
          "Normal (Normothermie)", "NORMAL",
          listOf("Équilibre physiologique thermorégulateur hypothalamique"),
          listOf("Confort thermique, absence de sueurs ou de frissons"),
          listOf("Aucun examen indiqué"),
          "Poursuite de la surveillance systématique."
        )
        temp in 37.6f..38.0f -> Tuple6(
          "Fébricule", "NORMAL",
          listOf("Début de réaction inflammatoire", "Déshydratation modérée", "Effort physique récent"),
          listOf("Légère tachycardie", "Sensation de chaleur cutanée"),
          listOf("Surveillance thermique à 4h d'intervalle"),
          "Hydratation orale, recontrôler la température à distance des repas."
        )
        temp in 38.1f..38.9f -> Tuple6(
          "Élevé (Fièvre modérée)", "MODERATE",
          listOf("Infection virale ou bactérienne aiguë", "Maladie inflammatoire", "Résorption hématome"),
          listOf("Tachycardie proportionnelle (10 bpm par degré > 37°C)", "Céphalées", "Myalgies"),
          listOf("NFS, CRP", "Bandelette urinaire", "Radiographie de thorax si toux"),
          "Antipyrétiques si inconfort (Paracétamol), hydratation abondante, recherche active du foyer infectieux."
        )
        else -> Tuple6(
          "Élevé (Hyperthermie franche >= 39°C)", "CRITICAL",
          listOf("Bactériémie / Sepsis", "Pneumonie franche", "Paludisme d'importation", "Méningite aiguë"),
          listOf("Frissons solennels", "Sueurs profuses", "Drapeaux rouges : purpura, raideur de nuque, marbrures"),
          listOf("Hémocultures systématiques aux pics fébriles", "ECBU", "NFS, CRP, Procalcitonine", "PL si signes méningés"),
          "Hémocultures immédiates avant toute antibiothérapie, refroidissement physique, réanimation si choc septique."
        )
      }
      raise(sev)
      explanations.add("Température : $temp°C — $status.")
      systematicPoints.add(
        VitalSystematicAnalysis(
          parameterName = "Température corporelle ($temp°C)",
          status = status,
          alertLevel = sev,
          physiologicalSignificance = "Reflet de l'équilibre entre thermogenèse et thermolyse contrôlé par le centre hypothalamique.",
          physiopathology = "Sécrétion de pyrogènes endogènes (IL-1, IL-6, TNF-alpha) stimulant la synthèse de prostaglandines E2 hypothalamiques qui élèvent le point de consigne thermique.",
          etiologies = causes,
          clinicalSignsToSearch = signs,
          usefulComplementaryExams = exams,
          emergencyCare = cat
        )
      )
    }

    // Pression Artérielle
    if (pas != null && pad != null) {
      val pam = (pas + 2 * pad) / 3f
      metrics.add("Pression Artérielle Moyenne (PAM)" to String.format(Locale.FRANCE, "%.1f mmHg", pam))
      metrics.add("Pression Pulsée (Différentielle)" to "${pas - pad} mmHg")

      val (status, sev, causes, signs, exams, cat) = when {
        pas < 90 || pam < 65f -> Tuple6(
          "Bas (Hypotension / Choc)", "CRITICAL",
          listOf("Choc hypovolémique (hémorragie, déshydratation)", "Choc septique (vasoplégie)", "Choc cardiogénique", "Choc anaphylactique"),
          listOf("Marbrures cutanées", "Temps de recoloration cutanée TRC > 3s", "Oligurie < 0.5 mL/kg/h", "Confusion"),
          listOf("Gazométrie artérielle avec lactates", "Hémoglobine en urgence", "Échographie cardiaque (ETT) pour guider le remplissage"),
          "Position déclive de Trendelenburg, pose de 2 voies veineuses périphériques de bon calibre (16G/18G), remplissage vasculaire par cristalloïdes isotoniques (500 mL en 15 min), appel réanimation."
        )
        pas in 90..139 && pad in 60..89 -> Tuple6(
          "Normal (Normotension)", "NORMAL",
          listOf("Tonus vasculaire et débit cardiaque adaptés"),
          listOf("Perfusion tissulaire optimale, diurèse conservée"),
          listOf("Aucun bilan indiqué"),
          "Surveillance de routine selon le protocole de service."
        )
        pas in 140..179 || pad in 90..109 -> Tuple6(
          "Élevé (Hypertension artérielle Grade 1-2)", "MODERATE",
          listOf("HTA essentielle décompensée", "Arrêt de traitement antihypertenseur", "Douleur aiguë ou stress intense", "Iatrogénie (AINS, corticoïdes)"),
          listOf("Céphalées occipitales matinales", "Acouphènes", "Vertiges"),
          listOf("ECG de repos", "Bandelette urinaire (protéinurie)", "Ionogramme et créatininémie"),
          "Mise au repos au calme 15-30 minutes, recontrôle tensionnel au brassard adapté, adaptation thérapeutique progressive sans baisse brutale."
        )
        else -> Tuple6(
          "Élevé (Urgence hypertensive potentielle PAS >= 180 ou PAD >= 110)", "CRITICAL",
          listOf("Crise hypertensive avec défaillance viscérale (AVC, OAP, dissection aortique, éclampsie)"),
          listOf("Douleur thoracique constrictive", "Dyspnée aiguë orthopnéique", "Déficit neurologique focal", "Flou visuel"),
          listOf("ECG 12 dérivations immédiat", "Scanner cérébral si signes neuro", "Troponine, ionogramme, créatinine"),
          "Rechercher une défaillance d'organe cible. Si urgence hypertensive vraie : hospitalisation en soins intensifs, traitement antihypertenseur IV titré (objectif baisse de 25% des chiffres en 2h)."
        )
      }
      raise(sev)
      explanations.add("Pression Artérielle : $pas/$pad mmHg — $status.")
      systematicPoints.add(
        VitalSystematicAnalysis(
          parameterName = "Pression Artérielle ($pas/$pad mmHg)",
          status = status,
          alertLevel = sev,
          physiologicalSignificance = "Force exercée par le sang circulant sur la paroi des gros vaisseaux artériels, dépendant du débit cardiaque et des résistances vasculaires périphériques.",
          physiopathology = "Régulation neuro-végétale (baroréflexe carotidien), rénale (système rénine-angiotensine-aldostérone) et hormonale (vasopressine, catécholamines).",
          etiologies = causes,
          clinicalSignsToSearch = signs,
          usefulComplementaryExams = exams,
          emergencyCare = cat
        )
      )
    }

    // Fréquence Cardiaque
    if (fc != null) {
      val (status, sev, causes, signs, exams, cat) = when {
        fc < 50 -> Tuple6(
          "Bas (Bradycardie sévère)", "CRITICAL",
          listOf("Bloc auriculo-ventriculaire de haut degré (BAV 2 Möbitz II ou BAV 3)", "Surdosage en bêtabloquant, digitalique ou anticalcique", "Infarctus du myocarde inférieur"),
          listOf("Vertiges, lipothymie", "Syncope à l'emporte-pièce (Stokes-Adams)", "Dyspnée d'effort", "Pouls lent régulier ou dissocié"),
          listOf("ECG 12 dérivations en extrême urgence", "Ionogramme (kaliémie)", "Troponine"),
          "Scope cardiaque continu, pose de voie veineuse, préparer Atropine 0.5-1 mg IV si mauvaise tolérance hémodynamique, matériel d'entraînement électrosystolique externe."
        )
        fc in 50..59 -> Tuple6(
          "Bas (Bradycardie modérée)", "NORMAL",
          listOf("Bradycardie sinusale physiologique du sujet entraîné/sportif", "Tonus vagal élevé", "Hypothyroïdie"),
          listOf("Parfaite tolérance clinique au repos"),
          listOf("ECG si symptomatique"),
          "Surveillance clinique simple."
        )
        fc in 60..100 -> Tuple6(
          "Normal (Normocardie)", "NORMAL",
          listOf("Automatisme sinusal régulier physiologique"),
          listOf("Pouls bien frappé, synchrone des bruits du cœur"),
          listOf("Aucun"),
          "Surveillance de routine."
        )
        fc in 101..129 -> Tuple6(
          "Élevé (Tachycardie modérée)", "MODERATE",
          listOf("Réactionnelle : fièvre, douleur, anémie, hypovolémie, déshydratation, anxiété, sevrage alcoolique", "Trouble du rythme supraventriculaire débutant (Fibrillation atriale)"),
          listOf("Palpitations", "Sensation d'oppression", "Recherche de signes de déshydratation"),
          listOf("ECG 12 dérivations", "NFS (éliminer anémie)", "Ionogramme"),
          "Traiter la cause sous-jacente (antalgiques, réhydratation, antipyrétiques), tracé ECG pour documenter le rythme."
        )
        else -> Tuple6(
          "Élevé (Tachycardie sévère >= 130 bpm)", "CRITICAL",
          listOf("Tachycardie ventriculaire (TV)", "Tachycardie supraventriculaire rapide (FA rapide, flutter 2:1)", "Choc septique ou hémorragique décompensé"),
          listOf("Hypotension artérielle", "Sueurs, pâleur", "Angor fonctionnel", "Galop B3"),
          listOf("ECG 12 dérivations immédiat", "Échocardiographie", "Gazométrie artérielle"),
          "Monitorage ECG continu, voie veineuse, oxygène, si instabilité hémodynamique (PAS < 90, angor, OAP) : préparer cardioversion électrique d'urgence."
        )
      }
      raise(sev)
      explanations.add("Fréquence Cardiaque : $fc bpm — $status.")
      systematicPoints.add(
        VitalSystematicAnalysis(
          parameterName = "Fréquence Cardiaque ($fc bpm)",
          status = status,
          alertLevel = sev,
          physiologicalSignificance = "Nombre de cycles de contraction ventriculaire par minute initiés par le nœud sinusal de Keith et Flack.",
          physiopathology = "Modulée en temps réel par la balance sympatho-vagale pour adapter le débit cardiaque (Débit = Fréquence x Volume d'éjection systolique).",
          etiologies = causes,
          clinicalSignsToSearch = signs,
          usefulComplementaryExams = exams,
          emergencyCare = cat
        )
      )
    }

    // Fréquence Respiratoire & SpO2
    if (fr != null) {
      val (status, sev, causes, signs, exams, cat) = when {
        fr < 10 -> Tuple6(
          "Bas (Bradypnée sévère)", "CRITICAL",
          listOf("Intoxication aiguë aux morphiniques, opioïdes ou benzodiazépines", "Hypertension intracrânienne majeure", "Coma profond"),
          listOf("Myosis serré aréactif en tête d'épingle (overdose opiacée)", "Cyanose", "Troubles de vigilance"),
          listOf("Gazométrie artérielle (hypercapnie sévère, acidose respiratoire)", "Toxiques urinaires"),
          "Libération des voies aériennes, ventilation au ballon auto-remplisseur (BAVU), Naloxone (Narcan) IV titré si suspicion d'intoxication morphinique."
        )
        fr in 10..20 -> Tuple6(
          "Normal (Eupnée)", "NORMAL",
          listOf("Commande respiratoire bulbaire normale et mécanique ventilatoire intacte"),
          listOf("Respiration calme, symétrique, sans bruit adventice"),
          listOf("Aucun"),
          "Surveillance standard."
        )
        fr in 21..27 -> Tuple6(
          "Élevé (Polypnée modérée)", "MODERATE",
          listOf("Infection broncho-pulmonaire", "Compensation respiratoire d'une acidose métabolique (dyspnée de Kussmaul)", "Embolie pulmonaire"),
          listOf("Tirage sus-claviculaire débutant", "Battement des ailes du nez", "Anxiété"),
          listOf("Radiographie thoracique", "Gazométrie artérielle", "SpO2"),
          "Rechercher le foyer infectieux pulmonaire, oxygénothérapie titrée si désaturation, gazométrie."
        )
        else -> Tuple6(
          "Élevé (Polypnée sévère >= 28/min)", "CRITICAL",
          listOf("Détresse respiratoire aiguë (OAP cardiogénique, SDRA, pneumothorax sous tension)", "Asthme aigu grave", "Embolie pulmonaire massive"),
          listOf("Balancement thoraco-abdominal paradoxal", "Tirage intercostal et sus-sternal intense", "Sueurs, cyanose, impossibilité de parler"),
          listOf("Gazométrie artérielle en extrême urgence", "Radiographie de thorax au lit", "D-Dimères ou angioscanner"),
          "Position assise stricte jambes pendantes, oxygène fort débit au masque haute concentration (12-15 L/min), préparer matériel d'intubation ou VNI, appel réanimation."
        )
      }
      raise(sev)
      explanations.add("Fréquence Respiratoire : $fr/min — $status.")
      systematicPoints.add(
        VitalSystematicAnalysis(
          parameterName = "Fréquence Respiratoire ($fr/min)",
          status = status,
          alertLevel = sev,
          physiologicalSignificance = "Fréquence de la ventilation alvéolaire assurant le renouvellement de l'O2 et l'élimination du CO2 produit par le métabolisme.",
          physiopathology = "Régulée par les chémorécepteurs centraux bulbaires (sensibles au pH et PaCO2) et périphériques aortico-carotidiens (PaO2).",
          etiologies = causes,
          clinicalSignsToSearch = signs,
          usefulComplementaryExams = exams,
          emergencyCare = cat
        )
      )
    }

    // SpO2
    if (spo2 != null) {
      val (status, sev, causes, signs, exams, cat) = when {
        spo2 >= 95 -> Tuple6(
          "Normal (Normoxémie)", "NORMAL",
          listOf("Hématose alvéolo-capillaire optimale"),
          listOf("Muqueuses roses, absence de cyanose"),
          listOf("Aucun"),
          "Poursuite de la surveillance."
        )
        spo2 in 90..94 -> Tuple6(
          "Bas (Désaturation modérée)", "MODERATE",
          listOf("BPCO en exacerbation", "Pneumonie infectieuse", "Atélectasie", "Œdème pulmonaire débutant"),
          listOf("Polypnée", "Cyanose distale des extrémités"),
          listOf("Gazométrie artérielle", "Radiographie thoracique"),
          "Oxygénothérapie nasale titrée (objectif 94-98% chez le sujet sans antécédent respiratoire, ou 88-92% chez le BPCO insuffisant respiratoire chronique)."
        )
        else -> Tuple6(
          "Bas (Hypoxémie sévère < 90%)", "CRITICAL",
          listOf("Inondation alvéolaire (OAP)", "Pneumonie bilatérale étendue", "SDRA", "Embolie pulmonaire grave"),
          listOf("Cyanose centrale péri-buccale et linguale", "Agitation anxieuse / encéphalopathie hypoxique", "Tirage respiratoire"),
          listOf("Gazométrie artérielle immédiate", "Angioscanner ou échocardiographie au lit"),
          "Oxygénothérapie à haut débit au masque à réserve (15 L/min), préparer l'assistance ventilatoire invasive ou non invasive (VNI/Optiflow)."
        )
      }
      raise(sev)
      explanations.add("SpO2 : $spo2% — $status.")
      systematicPoints.add(
        VitalSystematicAnalysis(
          parameterName = "Saturation en Oxygène (SpO2 $spo2%)",
          status = status,
          alertLevel = sev,
          physiologicalSignificance = "Fraction d'hémoglobine oxygénée par rapport à l'hémoglobine totale circulante mesurée par spectrophotométrie.",
          physiopathology = "Courbe de dissociation de l'oxyhémoglobine (courbe de Barcroft) en sigmoïde : en dessous de 90%, une faible baisse de PaO2 entraîne une chute brutale de la saturation.",
          etiologies = causes,
          clinicalSignsToSearch = signs,
          usefulComplementaryExams = exams,
          emergencyCare = cat
        )
      )
    }

    // Glycémie
    if (glycemie != null) {
      val (status, sev, causes, signs, exams, cat) = when {
        glycemie < 0.70f -> Tuple6(
          "Bas (Hypoglycémie aiguë)", "CRITICAL",
          listOf("Surdosage en insuline ou sulfamides hypoglycémiants", "Jeûne prolongé", "Insuffisance surrénale aiguë", "Hépatite fulminante"),
          listOf("Sueurs profuses froides", "Tremblements distaux", "Faim douloureuse", "Troubles visuels, confusion, coma hypoglycémique agité"),
          listOf("Glycémie veineuse de confirmation"),
          "Resucrage oral immédiat si conscient (3 morceaux de sucre ou 150 mL de jus de fruit). Si coma : 2 à 3 ampoules de G30% IV direct (ou Glucagon 1 mg IM/SC)."
        )
        glycemie in 0.70f..1.10f -> Tuple6(
          "Normal (Normoglycémie à jeun)", "NORMAL",
          listOf("Équilibre physiologique insuline/glucagon"),
          listOf("Absence de symptômes neuroglucopéniques ou adrénergiques"),
          listOf("Aucun"),
          "Surveillance habituelle."
        )
        glycemie in 1.11f..1.80f -> Tuple6(
          "Élevé (Intolérance glucidique ou post-prandiale)", "NORMAL",
          listOf("Repas récent riche en glucides", "Diabète débutant", "Stress aigu (cortisol)"),
          listOf("Asymptomatique le plus souvent"),
          listOf("Glycémie à jeun de contrôle, HbA1c"),
          "Régime alimentaire équilibré, dépistage de diabète à jeun."
        )
        else -> Tuple6(
          "Élevé (Hyperglycémie franche > 1.80 g/L)", "MODERATE",
          listOf("Diabète sucré décompensé", "Corticothérapie à forte dose", "Infection aiguë intercurrente chez le diabétique"),
          listOf("Syndrome polyuro-polydipsique (soif intense, diurèse abondante)", "Perte de poids", "Asthénie"),
          listOf("Bandelette urinaire (recherche de glycosurie et acétonurie)", "Gazométrie et ionogramme si cétonurie"),
          "Rechercher une cétonurie (acidocétose diabétique) : si cétones >= ++, transfert hospitalier d'urgence pour réhydratation et insulinothérapie IV."
        )
      }
      raise(sev)
      explanations.add("Glycémie : $glycemie g/dL — $status.")
      systematicPoints.add(
        VitalSystematicAnalysis(
          parameterName = "Glycémie Capillaire ($glycemie g/L)",
          status = status,
          alertLevel = sev,
          physiologicalSignificance = "Substrat énergétique primordial du cerveau, strictement régulé entre 0.70 et 1.10 g/L par les hormones pancréatiques.",
          physiopathology = "La chute de la glycémie stimule les catécholamines (signes adrénergiques) puis prive les neurones d'énergie (neuroglucopénie).",
          etiologies = causes,
          clinicalSignsToSearch = signs,
          usefulComplementaryExams = exams,
          emergencyCare = cat
        )
      )
    }

    // Poids & Taille -> IMC
    if (weight != null && height != null && height > 0) {
      val heightM = height / 100f
      val bmi = weight / (heightM * heightM)
      val bmiFormatted = String.format(Locale.FRANCE, "%.1f kg/m²", bmi)
      metrics.add("Indice de Masse Corporelle (IMC)" to bmiFormatted)

      val bmiCategory = when {
        bmi < 18.5f -> {
          raise("MODERATE")
          "Insuffisance pondérale / Maigreur (risque de dénutrition)"
        }
        bmi in 18.5f..24.9f -> "Corpulence normale"
        bmi in 25.0f..29.9f -> "Surpoids"
        bmi in 30.0f..34.9f -> {
          raise("MODERATE")
          "Obésité modérée (Classe 1)"
        }
        else -> {
          raise("MODERATE")
          "Obésité sévère"
        }
      }
      explanations.add("IMC : $bmiFormatted — $bmiCategory.")
    }

    // Collecter les précautions et conduites à tenir d'urgence
    systematicPoints.filter { it.alertLevel != "NORMAL" }.forEach { point ->
      precautions.add("${point.parameterName} : ${point.emergencyCare}")
    }

    val title = when (maxSeverity) {
      "CRITICAL" -> "Alerte Clinique Vitale Majeure"
      "MODERATE" -> "Anomalies Cliniques Modérées Détectées"
      else -> "Paramètres Physiologiques Stables"
    }

    return VitalsInterpretation(
      alertLevel = maxSeverity,
      alertTitle = title,
      explanations = explanations,
      precautions = precautions,
      computedMetrics = metrics,
      systematicPoints = systematicPoints
    )
  }

  // 2. Interprétation enrichie de la NFS avec syndromes multivariés et Conclusion Hématologique Générale
  fun interpretNfs(input: NfsInput): BiologicalInterpretation {
    val details = mutableListOf<String>()
    val pearls = mutableListOf<String>()
    val metrics = mutableListOf<Pair<String, String>>()
    val normalParams = mutableListOf<String>()
    val abnormalParams = mutableListOf<String>()
    val anomaliesAndSignificance = mutableListOf<Pair<String, String>>()
    val associationsList = mutableListOf<String>()
    val diagnosticOrientations = mutableListOf<String>()
    val pedagogicalMechanisms = mutableListOf<Pair<String, String>>()
    val identifiedAssociations = mutableListOf<String>()
    var alert = "NORMAL"

    // ──────────────────────────────────────────────────────────────────────────
    // A. LIGNÉE ROUGE (Érythrocytaire)
    // ──────────────────────────────────────────────────────────────────────────
    var isAnemia = false
    var isSevereAnemia = false
    var isMicrocytic = false
    var isMacrocytic = false
    var isHypochromic = false
    var isPolyglobulia = false
    var vgmValue: Float? = null
    var ccmhValue: Float? = null
    var tcmhValue: Float? = null

    // Calcul des indices érythrocytaires fondamentaux
    if (input.gr != null && input.ht != null && input.gr > 0) {
      val vgm = (input.ht * 10) / input.gr
      vgmValue = vgm
      metrics.add("VGM" to String.format(Locale.FRANCE, "%.1f fL", vgm))
      if (vgm < 80f) isMicrocytic = true
      else if (vgm > 100f) isMacrocytic = true
    }

    if (input.gr != null && input.hb != null && input.gr > 0) {
      val tcmh = (input.hb * 10) / input.gr
      tcmhValue = tcmh
      metrics.add("TCMH" to String.format(Locale.FRANCE, "%.1f pg", tcmh))
    }

    if (input.hb != null && input.ht != null && input.ht > 0) {
      val ccmh = (input.hb * 100) / input.ht
      ccmhValue = ccmh
      metrics.add("CCMH" to String.format(Locale.FRANCE, "%.1f g/dL", ccmh))
      if (ccmh < 32f) isHypochromic = true
    }

    if (input.rdw != null) {
      metrics.add("RDW / IDR" to String.format(Locale.FRANCE, "%.1f %%", input.rdw))
    }

    // Analyse de l'Hémoglobine et de la masse érythrocytaire
    if (input.hb != null) {
      val hb = input.hb
      when {
        hb < 8.0f -> {
          alert = "CRITICAL"
          isAnemia = true
          isSevereAnemia = true
          abnormalParams.add("Hémoglobine : $hb g/dL [EFFONDREMENT SÉVÈRE] (norme 12 - 16 g/dL)")
          anomaliesAndSignificance.add("Anémie profonde sévère (Hb < 8 g/dL)" to "Risque imminent d'hypoxie myocardique et cérébrale. Urgence transfusionnelle selon tolérance clinique et antécédents.")
          details.add("Anémie profonde sévère (Hb $hb g/dL) : hypoxie tissulaire myocardique et cérébrale imminente.")
          pearls.add("Indication formelle de transfusion de culots globulaires selon tolérance clinique (seuil 7 g/dL chez le sujet jeune sans antécédents, 8 g/dL chez le coronarien stable, 10 g/dL en cas de SCA actif).")
        }
        hb < 12.0f -> {
          if (alert != "CRITICAL") alert = "MODERATE"
          isAnemia = true
          abnormalParams.add("Hémoglobine : $hb g/dL [DIMINUÉE] (norme 12 - 16 g/dL)")
          val qualif = when {
            isMicrocytic && isHypochromic -> "microcytaire hypochrome"
            isMicrocytic -> "microcytaire normochrome"
            isMacrocytic -> "macrocytaire"
            else -> "normocytaire normochrome"
          }
          anomaliesAndSignificance.add("Anémie $qualif (Hb $hb g/dL)" to "Défaut de transport d'oxygène par diminution de la masse érythrocytaire circulante.")
          details.add("Anémie $qualif (Hb $hb g/dL).")
        }
        hb > 17.5f || (input.ht != null && input.ht > 54f) || (input.gr != null && input.gr > 6.0f) -> {
          if (alert != "CRITICAL") alert = "MODERATE"
          isPolyglobulia = true
          abnormalParams.add("Hémoglobine : $hb g/dL [ÉLEVÉE] (norme 12 - 16 g/dL)")
          anomaliesAndSignificance.add("Polyglobulie / Érythrocytose (Hb > 17.5 g/dL)" to "Augmentation de la masse sanguine érythrocytaire avec risque de syndrome d'hyperviscosité et de thromboses.")
          details.add("Polyglobulie franche (Hb $hb g/dL) : risque de syndrome d'hyperviscosité sanguine et de thrombose.")
          pearls.add("Distinguer polyglobulie vraie (mesure de la masse sanguine isotopique, mutation JAK2 V617F dans la maladie de Vaquez) de la fausse polyglobulie par hémoconcentration (déshydratation).")
        }
        else -> {
          normalParams.add("Hémoglobine : $hb g/dL (norme 12 - 16 g/dL)")
          details.add("Lignée érythrocytaire : Hémoglobine normale ($hb g/dL).")
        }
      }
    }

    if (vgmValue != null) {
      val vgmStr = String.format(Locale.FRANCE, "%.1f", vgmValue)
      if (vgmValue < 80f) {
        abnormalParams.add("VGM : $vgmStr fL [MICROCYTOSE] (norme 80 - 100 fL)")
        anomaliesAndSignificance.add("Microcytose (VGM < 80 fL)" to "Anomalie de synthèse de l'hémoglobine : carence martiale (fréquente), anémie inflammatoire, thalassémie hétérozygote ou anémie sidéroblastique.")
      } else if (vgmValue > 100f) {
        abnormalParams.add("VGM : $vgmStr fL [MACROCYTOSE] (norme 80 - 100 fL)")
        anomaliesAndSignificance.add("Macrocytose (VGM > 100 fL)" to "Défaut de synthèse de l'ADN érythroblastique (carence B12/folates), éthylisme chronique, hypothyroïdie ou régénération médullaire intense.")
      } else {
        normalParams.add("VGM : $vgmStr fL (norme 80 - 100 fL)")
      }
    }

    if (input.rdw != null) {
      if (input.rdw > 14.5f) {
        abnormalParams.add("RDW / IDR : ${input.rdw} % [ANISOCYTOSE] (norme 11.5 - 14.5 %)")
        anomaliesAndSignificance.add("Anisocytose érythrocytaire" to "Hétérogénéité anormale de la taille des hématies, hautement évocatrice de carence martiale débutante ou de double population post-transfusionnelle.")
      } else {
        normalParams.add("RDW / IDR : ${input.rdw} % (norme 11.5 - 14.5 %)")
      }
    }

    var isRegenerative: Boolean? = null
    if (input.reticulocytes != null && isAnemia) {
      val retic = input.reticulocytes
      if (retic > 150f) {
        isRegenerative = true
        abnormalParams.add("Réticulocytes : $retic G/L [RÉGÉNÉRATIF] (norme 20 - 120 G/L)")
        anomaliesAndSignificance.add("Anémie régénérative (Rétic > 150 G/L)" to "La moelle osseuse répond activement par une érythropoïèse accélérée. Étiologies : hémolyse aiguë ou hémorragie aiguë récente.")
        details.add("Caractère RÉGÉNÉRATIF de l'anémie (Réticulocytes $retic G/L > 150 G/L) : la moelle osseuse réagit activement.")
      } else {
        isRegenerative = false
        abnormalParams.add("Réticulocytes : $retic G/L [ARÉGÉNÉRATIF] (norme 20 - 120 G/L)")
        anomaliesAndSignificance.add("Anémie arégénérative (Rétic < 120 G/L)" to "Défaut de production médullaire centrale (carence en fer, B12/folates, insuffisance rénale par déficit en EPO, inflammation chronique, myélodysplasie).")
        details.add("Caractère ARÉGÉNÉRATIF de l'anémie (Réticulocytes $retic G/L < 120 G/L) : défaut central de production médullaire.")
      }
    } else if (input.reticulocytes != null) {
      normalParams.add("Réticulocytes : ${input.reticulocytes} G/L (norme 20 - 120 G/L)")
    }

    // Caractérisation morphologique précise de l'anémie
    val qualifAnemie = when {
      isMicrocytic && isHypochromic -> "microcytaire hypochrome"
      isMicrocytic -> "microcytaire normochrome"
      isMacrocytic -> "macrocytaire"
      isHypochromic -> "normocytaire hypochrome"
      else -> "normocytaire normochrome"
    } + (if (isRegenerative == true) " régénérative" else if (isRegenerative == false) " arégénérative" else "")

    val redLineSummary = when {
      input.hb == null -> "Données de la lignée rouge non renseignées."
      isAnemia -> {
        val reticMention = if (isRegenerative != null) ", Rétic : ${input.reticulocytes} G/L (${if (isRegenerative == true) "régénérative" else "arégénérative"})" else ""
        val vgmText = if (vgmValue != null) ", VGM : ${String.format(Locale.FRANCE, "%.1f", vgmValue)} fL" else ""
        val rdwText = if (input.rdw != null) ", IDR : ${input.rdw} %" else ""
        "Anémie $qualifAnemie (Hb : ${input.hb} g/dL$vgmText$reticMention$rdwText)."
      }
      isPolyglobulia -> "Érythrocytose / Polyglobulie (Hb : ${input.hb} g/dL, Ht : ${input.ht ?: "-"} %, GR : ${input.gr ?: "-"} T/L)."
      else -> {
        val vgmText = if (vgmValue != null) " (VGM : ${String.format(Locale.FRANCE, "%.1f", vgmValue)} fL)" else ""
        "Lignée rouge normale (Hb : ${input.hb} g/dL, hématies et indices érythrocytaires dans les limites usuelles$vgmText)."
      }
    }

    // ──────────────────────────────────────────────────────────────────────────
    // B. LIGNÉE BLANCHE (Leucocytaire) — INTERPRÉTATION EN VALEURS ABSOLUES
    // ──────────────────────────────────────────────────────────────────────────
    var isLeukopenia = false
    var isHyperleukocytosis = false
    var isNeutropenia = false
    var isAgranulocytosis = false
    var isNeutrophilia = false
    var isLymphocytosis = false
    var isLymphopenia = false
    var isMonocytosis = false
    var isEosinophilia = false
    var isBasophilia = false

    if (input.gb != null) {
      val gb = input.gb
      when {
        gb > 10.0f -> {
          isHyperleukocytosis = true
          if (alert != "CRITICAL") alert = "MODERATE"
          abnormalParams.add("Globules Blancs : $gb G/L [HYPERLEUCOCYTOSE] (norme 4.0 - 10.0 G/L)")
          anomaliesAndSignificance.add("Hyperleucocytose ($gb G/L)" to "Augmentation globale des leucocytes circulants, à caractériser impérativement sur la formule leucocytaire en valeur absolue.")
          details.add("Hyperleucocytose à $gb G/L.")
        }
        gb < 4.0f -> {
          isLeukopenia = true
          if (alert != "CRITICAL") alert = "MODERATE"
          abnormalParams.add("Globules Blancs : $gb G/L [LEUCOPÉNIE] (norme 4.0 - 10.0 G/L)")
          anomaliesAndSignificance.add("Leucopénie ($gb G/L)" to "Diminution des globules blancs totaux. Origine virale fréquente, médicamenteuse toxique ou hématologique centrale.")
          details.add("Leucopénie modérée ($gb G/L).")
        }
        else -> {
          normalParams.add("Globules Blancs : $gb G/L (norme 4.0 - 10.0 G/L)")
          details.add("Leucocytes totaux dans les limites usuelles ($gb G/L).")
        }
      }
    }

    if (input.pnn != null) {
      val pnn = input.pnn
      when {
        pnn < 0.5f -> {
          alert = "CRITICAL"
          isAgranulocytosis = true
          isNeutropenia = true
          abnormalParams.add("PNN : $pnn G/L [AGRANULOCYTOSE SÉVÈRE] (norme 1.5 - 7.0 G/L)")
          anomaliesAndSignificance.add("Agranulocytose aiguë (PNN < 0.5 G/L)" to "URGENCE VITALE ABSOLUE. Risque majeur de choc septique foudroyant à germes opportunistes ou flore digestive.")
          details.add("AGRANULOCYTOSE SÉVÈRE (PNN < 0.5 G/L) : URGENCE VITALE ABSOLUE !")
          pearls.add("Isolement protecteur immédiat en chambre individuelle, arrêt immédiat de tout médicament potentiellement neutropéniant, antibiothérapie probabiliste à large spectre antipyocyanique sans délai au moindre pic fébrile >= 38.3°C.")
        }
        pnn < 1.5f -> {
          if (alert != "CRITICAL") alert = "MODERATE"
          isNeutropenia = true
          abnormalParams.add("PNN : $pnn G/L [NEUTROPÉNIE] (norme 1.5 - 7.0 G/L)")
          anomaliesAndSignificance.add("Neutropénie ($pnn G/L)" to "Baisse des défenses antibactériennes. Risque infectieux croissant.")
          details.add("Neutropénie modérée ($pnn G/L).")
        }
        pnn > 7.0f -> {
          isNeutrophilia = true
          if (alert != "CRITICAL") alert = "MODERATE"
          abnormalParams.add("PNN : $pnn G/L [POLYNUCLEOSE NEUTROPHILE] (norme 1.5 - 7.0 G/L)")
          anomaliesAndSignificance.add("Polynucléose neutrophile ($pnn G/L)" to "Réaction de défense aiguë : infection bactérienne pyogène, syndrome inflammatoire systémique, nécrose tissulaire (IDM, pancréatite) ou corticothérapie.")
          details.add("Polynucléose neutrophile franche ($pnn G/L).")
        }
        else -> normalParams.add("PNN : $pnn G/L (norme 1.5 - 7.0 G/L)")
      }
    }

    if (input.lympho != null) {
      val l = input.lympho
      if (l > 4.0f) {
        isLymphocytosis = true
        if (alert != "CRITICAL") alert = "MODERATE"
        abnormalParams.add("Lymphocytes : $l G/L [HYPERLYMPHOCYTOSE] (norme 1.5 - 4.0 G/L)")
        anomaliesAndSignificance.add("Hyperlymphocytose ($l G/L)" to "Étiologie virale fréquente (MNI, CMV, VIH primo-infection), coqueluche ou hémopathie lymphoïde (LLC chez le sujet âgé).")
      } else if (l < 1.0f) {
        isLymphopenia = true
        if (alert != "CRITICAL") alert = "MODERATE"
        abnormalParams.add("Lymphocytes : $l G/L [LYMPHOPÉNIE] (norme 1.5 - 4.0 G/L)")
        anomaliesAndSignificance.add("Lymphopénie ($l G/L)" to "Infection virale aiguë (Covid, grippe), corticothérapie, dénutrition ou déficit immunitaire.")
      } else {
        normalParams.add("Lymphocytes : $l G/L (norme 1.5 - 4.0 G/L)")
      }
    }

    if (input.mono != null) {
      val m = input.mono
      if (m > 1.0f) {
        isMonocytosis = true
        if (alert != "CRITICAL") alert = "MODERATE"
        abnormalParams.add("Monocytes : $m G/L [MONOCYTOSE] (norme 0.2 - 1.0 G/L)")
        anomaliesAndSignificance.add("Monocytose ($m G/L)" to "Phase de guérison infectieuse, tuberculose, endocardite, ou hémopathie (leucémie myélomonocytaire chronique LMMC).")
      } else {
        normalParams.add("Monocytes : $m G/L (norme 0.2 - 1.0 G/L)")
      }
    }

    if (input.eosino != null) {
      val eo = input.eosino
      if (eo > 0.5f) {
        isEosinophilia = true
        if (alert != "CRITICAL") alert = "MODERATE"
        abnormalParams.add("Éosinophiles : $eo G/L [HYPERÉOSINOPHILIE] (norme 0.05 - 0.5 G/L)")
        anomaliesAndSignificance.add("Hyperéosinophilie ($eo G/L)" to "Rechercher une helminthiase (parasitose), toxidermie médicamenteuse, terrain atopique ou vascularite.")
        details.add("Hyperéosinophilie ($eo G/L).")
      } else {
        normalParams.add("Éosinophiles : $eo G/L (norme 0.05 - 0.5 G/L)")
      }
    }

    if (input.baso != null) {
      val b = input.baso
      if (b > 0.1f) {
        isBasophilia = true
        if (alert != "CRITICAL") alert = "MODERATE"
        abnormalParams.add("Basophiles : $b G/L [BASOPHILIE] (norme 0.01 - 0.1 G/L)")
        anomaliesAndSignificance.add("Basophilie ($b G/L)" to "Signe d'alerte orientant vers un syndrome myéloprolifératif (notamment leucémie myéloïde chronique LMC) ou réaction d'hypersensibilité.")
      } else {
        normalParams.add("Basophiles : $b G/L (norme 0.01 - 0.1 G/L)")
      }
    }

    val whiteLineSummary = when {
      input.gb == null -> "Données de la lignée leucocytaire non renseignées."
      isAgranulocytosis -> "Agranulocytose aiguë sévère (PNN : ${input.pnn} G/L < 0.5 G/L) — Urgence vitale absolue."
      isHyperleukocytosis -> {
        val pred = when {
          isNeutrophilia -> "à prédominance neutrophile (PNN : ${input.pnn} G/L)"
          isLymphocytosis -> "à prédominance lymphocytaire (Lympho : ${input.lympho} G/L)"
          isMonocytosis -> "avec monocytose associée (Mono : ${input.mono} G/L)"
          isEosinophilia -> "avec hyperéosinophilie associée (Éosino : ${input.eosino} G/L)"
          else -> "globale sans prédominance nette spécifiée"
        }
        "Hyperleucocytose (${input.gb} G/L) $pred."
      }
      isLeukopenia -> {
        val neutroText = if (isNeutropenia) " avec neutropénie associée (${input.pnn} G/L de PNN)" else if (input.pnn == null) " nécessitant une analyse de la formule leucocytaire pour préciser la population concernée" else ""
        "Leucopénie (${input.gb} G/L)$neutroText."
      }
      isNeutropenia -> "Leucocytes totaux conservés (${input.gb} G/L) avec neutropénie modérée (PNN : ${input.pnn} G/L)."
      isNeutrophilia -> "Leucocytes totaux conservés (${input.gb} G/L) avec polynucléose neutrophile relative (PNN : ${input.pnn} G/L)."
      isLymphocytosis -> "Leucocytes totaux conservés (${input.gb} G/L) avec hyperlymphocytose (Lympho : ${input.lympho} G/L)."
      isEosinophilia -> "Leucocytes totaux conservés (${input.gb} G/L) avec hyperéosinophilie isolée (Éosino : ${input.eosino} G/L)."
      else -> "Lignée blanche normale (Leucocytes : ${input.gb} G/L, formule leucocytaire équilibrée en valeurs absolues)."
    }

    // ──────────────────────────────────────────────────────────────────────────
    // C. LIGNÉE THROMBOCYTAIRE (Plaquettes)
    // ──────────────────────────────────────────────────────────────────────────
    var isThrombopenia = false
    var isSevereThrombopenia = false
    var isThrombocytosis = false

    if (input.plaquettes != null) {
      val plt = input.plaquettes
      when {
        plt < 20f -> {
          alert = "CRITICAL"
          isThrombopenia = true
          isSevereThrombopenia = true
          abnormalParams.add("Plaquettes : $plt G/L [THROMBOPÉNIE PROFONDE] (norme 150 - 400 G/L)")
          anomaliesAndSignificance.add("Thrombopénie profonde (< 20 G/L)" to "URGENCE HÉMORRAGIQUE MAJEURE : risque de saignement spontané intracrânien ou digestif létal.")
          details.add("THROMBOPÉNIE PROFONDE SÉVÈRE (< 20 G/L) : RISQUE HÉMORRAGIQUE MAJEUR !")
          pearls.add("Rechercher immédiatement des bulles hémorragiques endobuccales ou un purpura nécrotique. Repos strict au lit, contre-indication absolue de toute injection IM et d'AINS/aspirine.")
        }
        plt < 150f -> {
          if (alert != "CRITICAL") alert = "MODERATE"
          isThrombopenia = true
          val grav = if (plt < 50f) "sévère" else if (plt < 100f) "modérée" else "légère"
          abnormalParams.add("Plaquettes : $plt G/L [THROMBOPÉNIE $grav.uppercase()] (norme 150 - 400 G/L)")
          anomaliesAndSignificance.add("Thrombopénie ($plt G/L)" to "Diminution du nombre de plaquettes. Risque hémorragique en cas de geste invasif ou traumatisme.")
          details.add("Thrombopénie $grav ($plt G/L).")
        }
        plt > 400f -> {
          if (alert != "CRITICAL") alert = "MODERATE"
          isThrombocytosis = true
          abnormalParams.add("Plaquettes : $plt G/L [THROMBOCYTOSE] (norme 150 - 400 G/L)")
          anomaliesAndSignificance.add("Thrombocytose ($plt G/L)" to "Élévation réactionnelle (carence martiale, syndrome inflammatoire, splénectomie) ou primitive myéloproliférative.")
          details.add("Thrombocytose ($plt G/L).")
        }
        else -> {
          normalParams.add("Plaquettes : $plt G/L (norme 150 - 400 G/L)")
          details.add("Numération plaquettaire normale ($plt G/L).")
        }
      }
    }

    if (input.vpm != null) {
      metrics.add("VPM" to String.format(Locale.FRANCE, "%.1f fL", input.vpm))
      if (input.vpm > 11.0f) {
        abnormalParams.add("VPM : ${input.vpm} fL [MACROTHROMBOCYTOSE] (norme 7.0 - 11.0 fL)")
        anomaliesAndSignificance.add("Macrothrombocytose (VPM > 11 fL)" to "Plaquettes de grande taille traduisant une régénération médullaire active (thrombopénie périphérique de destruction) ou une dystrophie plaquettaire.")
      } else {
        normalParams.add("VPM : ${input.vpm} fL (norme 7.0 - 11.0 fL)")
      }
    }

    val plateletLineSummary = when {
      input.plaquettes == null -> "Données de la numération plaquettaire non renseignées."
      isSevereThrombopenia -> "Thrombopénie profonde critique (${input.plaquettes} G/L) — Risque hémorragique vital immédiat."
      isThrombopenia -> {
        val vpmText = if (input.vpm != null) ", VPM : ${input.vpm} fL" else ""
        "Thrombopénie (${input.plaquettes} G/L$vpmText)."
      }
      isThrombocytosis -> {
        val vpmText = if (input.vpm != null) ", VPM : ${input.vpm} fL" else ""
        "Thrombocytose (${input.plaquettes} G/L$vpmText)."
      }
      else -> {
        val vpmText = if (input.vpm != null) " (VPM : ${input.vpm} fL)" else ""
        "Numération plaquettaire normale (${input.plaquettes} G/L$vpmText)."
      }
    }

    // ──────────────────────────────────────────────────────────────────────────
    // D. RECONNAISSANCE DES ASSOCIATIONS HÉMATOLOGIQUES MULTILIGNÉES
    // ──────────────────────────────────────────────────────────────────────────
    // Décompte strict des cytopénies sur les 3 lignées majeures
    val isWhiteLineCytopenia = isLeukopenia || isNeutropenia || isAgranulocytosis
    val isRedLineCytopenia = isAnemia
    val isPlateletLineCytopenia = isThrombopenia

    val cytopeniasCount = (if (isRedLineCytopenia) 1 else 0) +
        (if (isWhiteLineCytopenia) 1 else 0) +
        (if (isPlateletLineCytopenia) 1 else 0)

    val isWhiteLineNormal = !isLeukopenia && !isHyperleukocytosis && !isNeutropenia && !isNeutrophilia && !isAgranulocytosis
    val isRedLineNormal = !isAnemia && !isPolyglobulia
    val isPlateletLineNormal = !isThrombopenia && !isThrombocytosis

    // 1. Syndrome de Pancytopénie (diminution des 3 lignées)
    if (cytopeniasCount >= 3) {
      alert = "CRITICAL"
      val pancytoDesc = "SYNDROME DE PANCYTOPÉNIE : Diminution simultanée des 3 lignées sanguines principales (érythrocytaire, leucocytaire et plaquettaire)."
      details.add("⚠️ SYNDROME DE PANCYTOPÉNIE : Atteinte simultanée des 3 lignées hématopoïétiques (érythrocytaire, leucocytaire, plaquettaire).")
      associationsList.add("⚠️ $pancytoDesc Évoquer en priorité une insuffisance médullaire centrale (aplasie médullaire, myélodysplasie, leucémie aiguë) ou une destruction périphérique (hypersplénisme majeur, syndrome d'activation macrophagique).")
      identifiedAssociations.add("Pancytopénie touchant simultanément les trois lignées (rouge, blanche, plaquettaire)")
      diagnosticOrientations.add("Pancytopénie centrale : Aplasie médullaire, Myélodysplasie, Leucémie aiguë (Myélogramme et BOM indispensables)")
      diagnosticOrientations.add("Pancytopénie périphérique : Hypersplénisme (cirrhose, hypertension portale), Syndrome d'activation macrophagique (SAM)")
    }
    // 2. Syndrome de Bicytopénie (diminution de 2 lignées seulement)
    else if (cytopeniasCount == 2) {
      if (alert != "CRITICAL") alert = "MODERATE"
      details.add("⚠️ BICYTOPÉNIE : Atteinte combinée de deux lignées hématopoïétiques.")
      val (bicytoDesc, preservedLine) = when {
        isRedLineCytopenia && isPlateletLineCytopenia ->
          Pair("Bicytopénie associant une anémie et une thrombopénie", "avec conservation de la lignée blanche")
        isRedLineCytopenia && isWhiteLineCytopenia ->
          Pair("Bicytopénie associant une anémie et une leucopénie", "avec conservation de la lignée plaquettaire")
        else ->
          Pair("Bicytopénie associant une leucopénie et une thrombopénie", "avec conservation de la lignée rouge")
      }
      associationsList.add("⚠️ BICYTOPÉNIE : $bicytoDesc, $preservedLine. Nécessite une enquête hématologique rigoureuse avec examen du frottis sanguin.")
      identifiedAssociations.add("$bicytoDesc ($preservedLine)")
      diagnosticOrientations.add("Bicytopénie à explorer en milieu spécialisé : éliminer une hémopathie débutante, une carence sévère (B12/folates) ou une toxicité médicamenteuse.")
    }
    // 3. Anomalies Isolées (une seule lignée atteinte, les 2 autres conservées)
    else if (isAnemia && isWhiteLineNormal && isPlateletLineNormal) {
      identifiedAssociations.add("Anémie isolée, avec conservation des lignées blanche et plaquettaire")
    } else if (isHyperleukocytosis && isRedLineNormal && isPlateletLineNormal) {
      identifiedAssociations.add("Hyperleucocytose isolée, avec conservation des lignées rouge et plaquettaire")
    } else if (isLeukopenia && isRedLineNormal && isPlateletLineNormal) {
      identifiedAssociations.add("Leucopénie isolée, avec conservation des lignées rouge et plaquettaire")
    } else if (isThrombopenia && isRedLineNormal && isWhiteLineNormal) {
      identifiedAssociations.add("Thrombopénie isolée, sans anomalie significative des autres lignées sanguines")
      diagnosticOrientations.add("Thrombopénie isolée : Éliminer d'emblée une fausse thrombopénie à l'EDTA par contrôle sur tube citraté avec examen du frottis.")
      diagnosticOrientations.add("Étiologies : Purpura Thrombopénique Immunologique (PTI), thrombopénie médicamenteuse (héparine TIH), hépatopathie débutante.")
    } else if (isThrombocytosis && isRedLineNormal && isWhiteLineNormal) {
      identifiedAssociations.add("Thrombocytose isolée, avec conservation des lignées rouge et blanche")
    }

    // 4. Associations réactionnelles et complexes
    if (isAnemia && isMicrocytic && isThrombocytosis) {
      associationsList.add("ASSOCIATION ANÉMIE MICROCYTAIRE + THROMBOCYTOSE RÉACTIONNELLE : Profil hautement évocateur d'une Carence Martiale (saignement digestif occulte ou gynécologique) ou d'une inflammation.")
      identifiedAssociations.add("Anémie microcytaire associée à une thrombocytose réactionnelle")
      diagnosticOrientations.add("Carence martiale avérée : Doser Ferritine en priorité, rechercher l'origine du saignement (fibroscopie œso-gastro-duodénale et coloscopie).")
    } else if (isAnemia && isMicrocytic && !isThrombocytosis) {
      diagnosticOrientations.add("Anémie microcytaire : Doser Ferritine et CRP pour discriminer carence martiale (Ferritine basse) et anémie inflammatoire (Ferritine normale/haute, CRP élevée).")
      diagnosticOrientations.add("Si bilan martial normal sans inflammation : Électrophorèse de l'hémoglobine (Thalassémie mineure hétérozygote).")
    }

    if (isAnemia && isMacrocytic) {
      diagnosticOrientations.add("Anémie macrocytaire : Doser Vitamine B12 et Folates sériques/érythrocytaires (carence d'apport ou maladie de Biermer).")
      diagnosticOrientations.add("Rechercher un éthylisme chronique, une hypothyroïdie (TSH) ou un syndrome myélodysplasique chez le sujet âgé.")
    }

    if (isAnemia && isRegenerative == true) {
      diagnosticOrientations.add("Anémie régénérative aiguë : Bilan d'hémolyse (Haptoglobine effondrée, Bilirubine libre augmentée, LDH élevées, Test de Coombs direct).")
      diagnosticOrientations.add("Éliminer une hémorragie aiguë extériorisée ou interne (saignement digestif, hématome profond).")
    }

    if (isHyperleukocytosis && isNeutrophilia && isThrombocytosis) {
      associationsList.add("ASSOCIATION HYPERLEUCOCYTOSE À PNN + THROMBOCYTOSE : Profil évocateur d'un Syndrome Inflammatoire ou Infectieux Bactérien aigu.")
      identifiedAssociations.add("Hyperleucocytose à PNN associée à une thrombocytose (syndrome inflammatoire systémique)")
      diagnosticOrientations.add("Infection bactérienne suppurée aiguë (pulmonaire, urinaire, abdominale, cutanée).")
      diagnosticOrientations.add("Syndrome inflammatoire systémique sévère, nécrose tissulaire aiguë (infarctus du myocarde, pancréatite aiguë) ou corticothérapie.")
    } else if (isNeutrophilia && isHyperleukocytosis) {
      associationsList.add("ASSOCIATION HYPERLEUCOCYTOSE + POLYNUCLEOSE NEUTROPHILE : Profil de réaction inflammatoire ou infectieuse bactérienne aiguë.")
      diagnosticOrientations.add("Infection bactérienne suppurée aiguë ou réaction inflammatoire systémique.")
    }

    if (isAgranulocytosis) {
      diagnosticOrientations.add("Agranulocytose aiguë médicamenteuse toxique ou immuno-allergique (antithyroïdiens de synthèse, psychotropes, antibiotiques).")
      diagnosticOrientations.add("Aplasie médullaire aiguë ou hémopathie maligne.")
    }

    if (identifiedAssociations.isEmpty()) {
      if (isRedLineNormal && isWhiteLineNormal && isPlateletLineNormal) {
        identifiedAssociations.add("Aucune association pathologique : les trois lignées sont dans les limites usuelles de référence")
      } else {
        identifiedAssociations.add("Anomalies sanguines isolées sans association syndromique multilignée complexe")
      }
    }

    // ──────────────────────────────────────────────────────────────────────────
    // E. CONCLUSION ET INTERPRÉTATION GÉNÉRALE DE LA NFS (RÉDACTION MÉDICALE)
    // ──────────────────────────────────────────────────────────────────────────
    val professionalConclusion = when {
      // 1. Pancytopénie
      cytopeniasCount >= 3 -> {
        val gbStr = if (input.gb != null) "${input.gb} G/L" else "leucopénie non chiffrée"
        val pltStr = if (input.plaquettes != null) "${input.plaquettes} G/L" else "thrombopénie non chiffrée"
        "La NFS a objectivé une pancytopénie associant une anémie $qualifAnemie, une leucopénie ($gbStr) et une thrombopénie ($pltStr)."
      }

      // 2. Bicytopénie
      cytopeniasCount == 2 -> {
        when {
          isRedLineCytopenia && isPlateletLineCytopenia ->
            "La NFS a objectivé une bicytopénie associant une anémie $qualifAnemie et une thrombopénie (${input.plaquettes ?: "-"} G/L), avec conservation de la lignée blanche."
          isRedLineCytopenia && isWhiteLineCytopenia ->
            "La NFS a objectivé une bicytopénie associant une anémie $qualifAnemie et une leucopénie (${input.gb ?: "-"} G/L), avec conservation de la lignée plaquettaire."
          else ->
            "La NFS a objectivé une bicytopénie associant une leucopénie (${input.gb ?: "-"} G/L) et une thrombopénie (${input.plaquettes ?: "-"} G/L), avec conservation de la lignée rouge."
        }
      }

      // 3. Anémie microcytaire + Thrombocytose réactionnelle
      isAnemia && isMicrocytic && isThrombocytosis -> {
        val ccmhText = if (isHypochromic) "hypochrome" else "normochrome"
        "La NFS a objectivé une anémie microcytaire $ccmhText associée à une thrombocytose réactionnelle (${input.plaquettes} G/L), avec conservation de la lignée blanche."
      }

      // 4. Hyperleucocytose isolée
      isHyperleukocytosis && isRedLineNormal && isPlateletLineNormal -> {
        val pred = when {
          isNeutrophilia -> "à prédominance neutrophile"
          isLymphocytosis -> "à prédominance lymphocytaire"
          else -> ""
        }
        val predText = if (pred.isNotEmpty()) " $pred" else ""
        "La NFS a objectivé une hyperleucocytose$predText, avec conservation des lignées rouge et plaquettaire."
      }

      // 5. Leucopénie isolée
      isLeukopenia && isRedLineNormal && isPlateletLineNormal -> {
        if (input.pnn == null) {
          "La NFS a objectivé une leucopénie, nécessitant une analyse de la formule leucocytaire pour préciser la population cellulaire concernée."
        } else if (isNeutropenia) {
          "La NFS a objectivé une leucopénie avec neutropénie (${input.pnn} G/L de PNN), avec conservation des lignées rouge et plaquettaire."
        } else {
          "La NFS a objectivé une leucopénie (${input.gb} G/L), avec conservation des lignées rouge et plaquettaire."
        }
      }

      // 6. Anémie isolée
      isAnemia && isWhiteLineNormal && isPlateletLineNormal -> {
        "La NFS a objectivé une anémie $qualifAnemie, avec conservation des lignées blanche et plaquettaire."
      }

      // 7. Thrombopénie isolée
      isThrombopenia && isRedLineNormal && isWhiteLineNormal -> {
        "La NFS a objectivé une thrombopénie isolée (${input.plaquettes} G/L), sans anomalie significative des autres lignées sanguines."
      }

      // 8. Thrombocytose isolée
      isThrombocytosis && isRedLineNormal && isWhiteLineNormal -> {
        "La NFS a objectivé une thrombocytose isolée (${input.plaquettes} G/L), avec conservation des lignées rouge et blanche."
      }

      // 9. Polyglobulie
      isPolyglobulia && isWhiteLineNormal && isPlateletLineNormal -> {
        "La NFS a objectivé une érythrocytose / polyglobulie (Hb : ${input.hb} g/dL), avec conservation des lignées blanche et plaquettaire."
      }

      // 10. Agranulocytose sévère isolée
      isAgranulocytosis && isRedLineNormal && isPlateletLineNormal -> {
        "La NFS a objectivé une agranulocytose aiguë sévère (PNN : ${input.pnn} G/L < 0.5 G/L), avec conservation des lignées rouge et plaquettaire — Urgence vitale absolue."
      }

      // 11. Bilan normal
      isRedLineNormal && isWhiteLineNormal && isPlateletLineNormal -> {
        "La NFS ne montre pas d'anomalie hématologique significative. Les trois lignées sanguines présentent des valeurs globalement dans les intervalles de référence."
      }

      // 12. Autre combinaison
      else -> {
        val anomaliesList = mutableListOf<String>()
        if (isAnemia) anomaliesList.add("une anémie $qualifAnemie")
        if (isPolyglobulia) anomaliesList.add("une polyglobulie")
        if (isHyperleukocytosis) anomaliesList.add("une hyperleucocytose")
        if (isLeukopenia) anomaliesList.add("une leucopénie")
        if (isThrombopenia) anomaliesList.add("une thrombopénie")
        if (isThrombocytosis) anomaliesList.add("une thrombocytose")
        if (anomaliesList.isNotEmpty()) {
          "La NFS a objectivé ${anomaliesList.joinToString(" et ")}."
        } else {
          "La NFS présente des données incomplètes ou parcellaires ne permettant pas de conclure de manière définitive."
        }
      }
    }

    // ──────────────────────────────────────────────────────────────────────────
    // F. EXPLICATION CLINIQUE ET ÉLÉMENTS À APPROFONDIR
    // ──────────────────────────────────────────────────────────────────────────
    val clinicalSignificance = when {
      cytopeniasCount >= 3 ->
        "Atteinte sévère globale de l'hématopoïèse. Cumule un risque d'hypoxie anémique, un risque infectieux majeur (neutropénie) et un risque hémorragique (thrombopénie)."
      cytopeniasCount == 2 ->
        "Atteinte bicytopénique conjointe de deux compartiments sanguins distincts. Oriente vers une origine médullaire centrale ou une pathologie d'activation/destruction périphérique."
      isAnemia && isMicrocytic && isThrombocytosis ->
        "Profil classique de carence martiale : la baisse de la synthèse d'hémoglobine entraîne une microcytose, tandis que le manque de fer stimule la thrombopoïétine et engendre une thrombocytose réactionnelle."
      isAnemia ->
        "Diminution de la capacité de transport de l'oxygène aux tissus avec risque d'ischémie myocardique et de fatigue générale à l'effort."
      isHyperleukocytosis ->
        "Réponse biologique de défense innée ou acquise face à une agression aiguë (bactérienne, inflammatoire ou nécrotique) ou prolifération hématologique autonome."
      isLeukopenia ->
        "Baisse des défenses immunitaires de l'organisme exposant à un risque accru d'infections bactériennes, fongiques ou virales."
      isThrombopenia ->
        "Altération de l'hémostase primaire avec risque de saignements cutanéo-muqueux spontanés ou provoqués par un geste invasif."
      isThrombocytosis ->
        "Augmentation du nombre de plaquettes le plus souvent réactionnelle à un saignement, une inflammation ou une splénectomie, avec risque modéré de thrombose veineuse/artérielle si très élevée."
      else ->
        "Fonctionnement hématopoïétique équilibré. Transport d'oxygène, défenses leucocytaires et hémostase primaire dans les normes physiologiques."
    }

    val affectedLines = when {
      cytopeniasCount >= 3 -> "Atteinte simultanée des trois lignées : rouge, blanche et plaquettaire."
      cytopeniasCount == 2 -> {
        val affected = mutableListOf<String>()
        if (isRedLineCytopenia) affected.add("lignée rouge")
        if (isWhiteLineCytopenia) affected.add("lignée blanche")
        if (isPlateletLineCytopenia) affected.add("lignée plaquettaire")
        "Deux lignées atteintes (${affected.joinToString(" et ")}), avec conservation de la troisième lignée."
      }
      isAnemia -> "Lignée rouge atteinte de façon prédominante ; conservation des lignées blanche et plaquettaire."
      isHyperleukocytosis || isLeukopenia || isNeutropenia || isAgranulocytosis -> "Lignée blanche atteinte de façon prédominante ; conservation des lignées rouge et plaquettaire."
      isThrombopenia || isThrombocytosis -> "Lignée plaquettaire atteinte de façon prédominante ; conservation des lignées rouge et blanche."
      else -> "Aucune lignée hématopoïétique compromise (les trois lignées sont préservées)."
    }

    val mechanismsAndCauses = mutableListOf<String>()
    if (cytopeniasCount >= 3) {
      mechanismsAndCauses.add("Origine centrale (défaut de production médullaire) : Aplasie médullaire idiopathique ou toxique, Syndrome myélodysplasique (SMD), Leucémie aiguë, Envahissement métastatique de la moelle.")
      mechanismsAndCauses.add("Origine périphérique (destruction ou séquestration) : Hypersplénisme majeur sur cirrhose/hypertension portale, Syndrome d'activation macrophagique (SAM), Carence profonde en vitamine B12 (pseudo-leucémie).")
    } else if (cytopeniasCount == 2) {
      mechanismsAndCauses.add("Origine centrale : Début d'insuffisance médullaire, myélodysplasie, toxicité chimiothérapeutique ou médicamenteuse.")
      mechanismsAndCauses.add("Origine auto-immune / périphérique : Syndrome d'Evans (anémie hémolytique auto-immune + PTI), hypersplénisme modéré, lupus érythémateux disséminé.")
    } else if (isAnemia) {
      if (isMicrocytic) {
        mechanismsAndCauses.add("Carence martiale par saignement occulte chronique (digestif, gynécologique) ou malabsorption (maladie cœliaque).")
        mechanismsAndCauses.add("Anémie inflammatoire chronique (séquestration macrophagique du fer par l'hepcidine).")
        mechanismsAndCauses.add("Thalassémie mineure hétérozygote (anomalie génétique des chaînes de globine).")
      } else if (isMacrocytic) {
        mechanismsAndCauses.add("Carence en vitamine B12 (maladie de Biermer, gastrectomie) ou en acide folique (dénutrition, grossesse).")
        mechanismsAndCauses.add("Consommation chronique d'alcool, hypothyroïdie ou régénération médullaire intense post-hémorragie.")
      } else {
        mechanismsAndCauses.add("Hémolyse aiguë ou hémorragie aiguë récente si réticulocytes élevés.")
        mechanismsAndCauses.add("Insuffisance rénale chronique (déficit en érythropoïétine EPO) ou inflammation si réticulocytes bas.")
      }
    } else if (isHyperleukocytosis) {
      if (isNeutrophilia) {
        mechanismsAndCauses.add("Infection bactérienne pyogène aiguë (pulmonaire, urinaire, digestive, cutanée).")
        mechanismsAndCauses.add("Syndrome inflammatoire systémique sévère ou nécrose tissulaire aiguë (infarctus du myocarde, pancréatite aiguë).")
        mechanismsAndCauses.add("Effet iatrogène : corticothérapie générale, agonistes du G-CSF.")
      } else if (isLymphocytosis) {
        mechanismsAndCauses.add("Infection virale (mononucléose infectieuse MNI, CMV, primo-infection VIH, hépatite virale).")
        mechanismsAndCauses.add("Syndrome lymphoprolifératif chronique (Leucémie lymphoïde chronique LLC chez le sujet de plus de 60 ans).")
      } else {
        mechanismsAndCauses.add("Réaction leucémoïde bénigne face à un sepsis ou stress aigu.")
      }
    } else if (isLeukopenia) {
      mechanismsAndCauses.add("Origine virale aiguë transitoire (grippe, Covid-19, hépatite, arboviroses).")
      mechanismsAndCauses.add("Iatrogénie médicamenteuse toxique ou immuno-allergique (AINS, antibiotiques, antithyroïdiens).")
      mechanismsAndCauses.add("Origine hématologique centrale à surveiller si persistance.")
    } else if (isThrombopenia) {
      mechanismsAndCauses.add("Purpura Thrombopénique Immunologique (PTI) par auto-anticorps anti-plaquettes.")
      mechanismsAndCauses.add("Origine médicamenteuse (héparine TIH, antibiotiques, quinine).")
      mechanismsAndCauses.add("Consommation aiguë : Coagulation Intravasculaire Disséminée (CIVD), Microangiopathie Thrombotique (MAT).")
      mechanismsAndCauses.add("Fausse thrombopénie in vitro liée à l'anticoagulant EDTA (agrégats plaquettaires artificiels).")
    } else if (isThrombocytosis) {
      mechanismsAndCauses.add("Thrombocytose réactionnelle : carence martiale (très fréquente), syndrome inflammatoire ou infectieux, splénectomie.")
      mechanismsAndCauses.add("Thrombocytose primitive : syndrome myéloprolifératif (Thrombocytémie essentielle avec mutation JAK2 ou CALR).")
    } else {
      mechanismsAndCauses.add("Hématopoïèse physiologique normale : équilibre parfait entre production médullaire et clairance périphérique.")
    }

    val clinicalSignsToSearch = mutableListOf<String>()
    if (isAnemia || cytopeniasCount >= 2) {
      clinicalSignsToSearch.add("Syndrome anémique : pâleur cutanéo-muqueuse (conjonctives), asthénie, dyspnée d'effort, vertiges, tachycardie de repos.")
    }
    if (isThrombopenia || isSevereThrombopenia) {
      clinicalSignsToSearch.add("Syndrome hémorragique : purpura pétéchial ou ecchymotique déclive, gingivorragies, épistaxis, bulles hémorragiques endobuccales, rectorragies ou méléna.")
    }
    if (isLeukopenia || isNeutropenia || isAgranulocytosis || isHyperleukocytosis) {
      clinicalSignsToSearch.add("Syndrome infectieux : température corporelle (fièvre >= 38.3°C ou hypothermie < 36°C), frissons, point d'appel infectieux (toux, brûlures mictionnelles, plaie cutanée).")
    }
    if (cytopeniasCount >= 2 || isHyperleukocytosis) {
      clinicalSignsToSearch.add("Syndrome tumoral hématopoïétique : adénopathies périphériques (cervicales, axillaires, inguinales), splénomégalie, hépatomégalie.")
    }
    if (clinicalSignsToSearch.isEmpty()) {
      clinicalSignsToSearch.add("Examen clinique complet négatif : absence de pâleur, de purpura, de fièvre ou d'adénopathie périphérique.")
    }

    val recommendedExams = mutableListOf<String>()
    recommendedExams.add("Frottis sanguin sur lame avec examen morphologique au microscope : analyse cytologique indispensable pour vérifier la morphologie des hématies, contrôler les plaquettes et rechercher des cellules anormales (blastes, schizocytes).")
    if (isThrombopenia) {
      recommendedExams.add("Contrôle impératif de la numération plaquettaire sur tube citraté de sodium (pour éliminer formellement une fausse thrombopénie à l'EDTA).")
    }
    if (isAnemia) {
      recommendedExams.add("Dosage de la Ferritine sérique et CRP pour discriminer carence martiale et anémie inflammatoire.")
      if (input.reticulocytes == null) {
        recommendedExams.add("Numération des réticulocytes pour caractériser le caractère régénératif ou arégénératif de l'anémie.")
      }
      if (isMacrocytic) {
        recommendedExams.add("Dosage de la Vitamine B12 et des Folates sériques/érythrocytaires.")
      }
      if (isRegenerative == true) {
        recommendedExams.add("Bilan d'hémolyse : Haptoglobine sérique, Bilirubine libre, LDH, Test de Coombs direct érythrocytaire.")
      }
    }
    if (cytopeniasCount >= 2) {
      recommendedExams.add("Consultation hématologique spécialisée avec discussion d'un myélogramme (ponction médullaire) ou biopsie ostéo-médullaire (BOM).")
    }
    if (isHyperleukocytosis && isNeutrophilia) {
      recommendedExams.add("Bilan infectieux ciblé (hémocultures, ECBU, radiographie pulmonaire) et marqueurs inflammatoires (CRP, PCT).")
    }

    val limitsAndCaveats = "RAPPEL MÉDICAL FONDAMENTAL : L'hémogramme est un examen paraclinique d'orientation qui ne constitue jamais un diagnostic nosologique autonome. Les mécanismes évoqués sont des hypothèses physiopathologiques qui doivent obligatoirement être confrontées à l'interrogatoire, aux traitements reçus, à l'examen clinique au lit du malade et aux examens complémentaires ciblés."

    val explanation = NfsClinicalExplanation(
      significance = clinicalSignificance,
      affectedLines = affectedLines,
      mechanismsAndCauses = mechanismsAndCauses,
      clinicalSignsToSearch = clinicalSignsToSearch,
      recommendedExams = recommendedExams,
      limitsAndCaveats = limitsAndCaveats
    )

    // ──────────────────────────────────────────────────────────────────────────
    // G. APPRÉCIATION GÉNÉRALE ET CONCLUSION FINALE DU BILAN
    // ──────────────────────────────────────────────────────────────────────────
    val appreciation = when {
      alert == "CRITICAL" -> "Hémogramme pathologique présentant une ou plusieurs anomalies critiques engageant le pronostic vital immédiat."
      alert == "MODERATE" -> "Hémogramme pathologique présentant des perturbations significatives nécessitant une confrontation clinique et des examens ciblés."
      else -> "Hémogramme dans les limites usuelles de référence. Aucune anomalie quantitative décelée sur les lignées explorées."
    }

    // ──────────────────────────────────────────────────────────────────────────
    // H. ANALYSE PÉDAGOGIQUE — MÉCANISMES PHYSIOLOGIQUES DÉTAILLÉS (NIVEAU B)
    // ──────────────────────────────────────────────────────────────────────────
    pedagogicalMechanisms.add("Hémoglobine (Hb)" to "Protéine tétramérique intra-érythrocytaire contenant du fer, responsable du transport de l'oxygène des poumons vers les tissus. Sa baisse (< 12-13 g/dL) définit l'anémie ; son élévation (> 17.5 g/dL) définit la polyglobulie avec augmentation de la viscosité sanguine.")
    pedagogicalMechanisms.add("Volume Globulaire Moyen (VGM)" to "Reflète la taille moyenne des hématies (norme 80-100 fL). Un VGM < 80 fL (microcytose) traduit un défaut d'hémoglobinogenèse (manque de fer, anomalie des chaînes de globine). Un VGM > 100 fL (macrocytose) traduit un blocage de synthèse de l'ADN lors des divisions cellulaires (carence B12/folates, éthanol).")
    pedagogicalMechanisms.add("Réticulocytes" to "Jeunes hématies récemment sorties de la moelle osseuse (vie < 24-48h). Un taux > 150 G/L indique une moelle fonctionnelle qui compense une perte périphérique (hémolyse ou saignement). Un taux < 120 G/L en présence d'anémie signe une défaillance de production centrale médullaire.")
    pedagogicalMechanisms.add("Polynucléaires Neutrophiles (PNN)" to "Principale ligne de défense cellulaire innée contre les bactéries pyogènes et les champignons. Leur hausse (> 7.5 G/L) résulte d'une margination et d'une production accélérée médiée par les cytokines inflammatoires (G-CSF, IL-1, TNF). Leur effondrement (< 0.5 G/L) expose à des bactériémies opportunistes foudroyantes.")
    pedagogicalMechanisms.add("Plaquettes (Thrombocytes)" to "Fragments cytoplasmiques anucléés issus des mégacaryocytes médullaires, acteurs majeurs de l'hémostase primaire. Leur baisse (< 150 G/L) compromet la formation du clou plaquettaire ; leur élévation (> 450 G/L) est fréquemment une réaction médiée par l'interleukine 6 (IL-6) stimulant la thrombopoïétine lors d'une inflammation ou d'une carence martiale.")

    pearls.add("Règle d'or de l'hémogramme : L'interprétation de la formule leucocytaire doit TOUJOURS être faite en valeurs absolues (G/L ou /mm3) et jamais en pourcentages !")
    pearls.add("Un frottis sanguin sur lame avec examen morphologique au microscope est le meilleur examen complémentaire rapide devant toute anomalie de la NFS.")
    pearls.add("Toujours éliminer une fausse thrombopénie à l'EDTA en cas de découverte fortuite d'un chiffre bas de plaquettes chez un patient asymptomatique.")

    return BiologicalInterpretation(
      alertLevel = alert,
      title = "Analyse Complète de l'Hémogramme (NFS)",
      summary = appreciation,
      details = details,
      clinicalPearls = pearls,
      computedMetrics = metrics,
      generalAppreciation = appreciation,
      normalParameters = normalParams,
      abnormalParameters = abnormalParams,
      anomaliesAndSignificance = anomaliesAndSignificance,
      associationsList = associationsList,
      diagnosticOrientations = diagnosticOrientations,
      generalConclusion = professionalConclusion,
      clinicalContextCaveat = limitsAndCaveats,
      pedagogicalMechanisms = pedagogicalMechanisms,
      redLineSummary = redLineSummary,
      whiteLineSummary = whiteLineSummary,
      plateletLineSummary = plateletLineSummary,
      identifiedAssociations = identifiedAssociations,
      professionalMedicalConclusion = professionalConclusion,
      nfsExplanation = explanation
    )
  }

  // 3. Interprétation UECréat et ionogramme
  fun interpretUeCreat(input: UeCreatInput): BiologicalInterpretation {
    val details = mutableListOf<String>()
    val pearls = mutableListOf<String>()
    val metrics = mutableListOf<Pair<String, String>>()
    var alert = "NORMAL"

    // Kaliémie
    if (input.k != null) {
      when {
        input.k < 3.0f -> {
          alert = "CRITICAL"
          details.add("Hypokaliémie sévère (${input.k} mmol/L) : risque d'arythmie ventriculaire létale (torsade de pointes) !")
          pearls.add("ECG 12 dérivations immédiat (onde U, sous-décalage ST, aplatissement de l'onde T). Recharge potassique IV lente sous surveillance scopique stricte.")
        }
        input.k in 3.0f..3.4f -> {
          if (alert != "CRITICAL") alert = "MODERATE"
          details.add("Hypokaliémie modérée (${input.k} mmol/L) : pertes digestives (vomissements, diarrhées) ou diurétiques hypokaliémiants.")
        }
        input.k in 3.5f..5.0f -> details.add("Kaliémie physiologique normale (${input.k} mmol/L).")
        input.k in 5.1f..5.9f -> {
          if (alert != "CRITICAL") alert = "MODERATE"
          details.add("Hyperkaliémie modérée (${input.k} mmol/L) : éliminer une fausse hyperkaliémie par hémolyse du tube de prélèvement.")
        }
        else -> {
          alert = "CRITICAL"
          details.add("HYPERKALIÉMIE SÉVÈRE (>= 6.0 mmol/L) : URGENCE VITALE IMMÉDIATE (risque d'asystolie et fibrillation ventriculaire) !")
          pearls.add("ECG immédiat (ondes T pointues symétriques, disparition de l'onde P, élargissement du QRS). Traitement : 1 ampoule de Gluconate de Calcium IVD en 2 min, Insuline rapide + G10%, Salbutamol en nébulisation, résines échangeuses.")
        }
      }
    }

    // Natrémie
    if (input.na != null) {
      when {
        input.na < 125f -> {
          alert = "CRITICAL"
          details.add("Hyponatrémie sévère (${input.na} mmol/L) : risque d'œdème cérébral aigu, convulsions et coma.")
          pearls.add("Attention au piège : Ne jamais corriger trop rapidement une hyponatrémie chronique ! Vitesse limite absolue : 8 à 10 mmol/L par 24h pour prévenir la myélinolyse centro-pontine.")
        }
        input.na in 125f..134f -> {
          if (alert != "CRITICAL") alert = "MODERATE"
          details.add("Hyponatrémie modérée (${input.na} mmol/L) : à évaluer selon la volémie extracellulaire (déshydratation, SIADH, insuffisance cardiaque).")
        }
        input.na in 135f..145f -> details.add("Natrémie normale (${input.na} mmol/L).")
        input.na > 150f -> {
          alert = "CRITICAL"
          details.add("Hypernatrémie sévère (> 150 mmol/L) : témoin formel d'une déshydratation intracellulaire par manque d'eau pure.")
        }
      }
    }

    // Créatinine & DFG (Cockcroft-Gault)
    if (input.creat != null) {
      val cr = input.creat
      if (input.age != null && input.weightKg != null && input.age > 0 && input.weightKg > 0) {
        var cl = ((140 - input.age) * input.weightKg) / (cr * 0.814f)
        if (input.isFemale) cl *= 0.85f
        val clStr = String.format(Locale.FRANCE, "%.1f mL/min", cl)
        metrics.add("Clairance Créatinine (Cockcroft)" to clStr)

        val stage = when {
          cl >= 90f -> "Stade 1 : Fonction rénale normale ou conservée"
          cl in 60f..89.9f -> "Stade 2 : Insuffisance rénale légère"
          cl in 30f..59.9f -> {
            if (alert != "CRITICAL") alert = "MODERATE"
            "Stade 3 : Insuffisance rénale modérée (adapter les posologies médicamenteuses)"
          }
          cl in 15f..29.9f -> {
            alert = "CRITICAL"
            "Stade 4 : Insuffisance rénale sévère (surveillance néphrologique rapprochée)"
          }
          else -> {
            alert = "CRITICAL"
            "Stade 5 : Insuffisance rénale terminale (préparation ou indication de dialyse)"
          }
        }
        details.add("Fonction rénale : DFG estimé à $clStr ($stage).")
      }
    }

    // Ratio Urée/Créatinine (distinction fonctionnelle vs organique)
    if (input.urea != null && input.creat != null && input.creat > 0) {
      val ratio = (input.urea * 1000f) / input.creat
      metrics.add("Ratio Urée / Créatinine" to String.format(Locale.FRANCE, "%.1f", ratio))
      if (ratio > 100f) {
        details.add("Rapport Urée/Créat élevé (> 100) : orientation très forte vers une insuffisance rénale aiguë FONCTIONNELLE (pré-rénale par hypoperfusion rénale, déshydratation ou bas débit).")
      } else if (ratio in 40f..80f && input.creat > 120f) {
        details.add("Rapport Urée/Créat intermédiaire (< 80) : orientation vers une atteinte rénale ORGANIQUE parenchymateuse (nécrose tubulaire aiguë, néphropathie glomérulaire).")
      }
    }

    // Trou anionique plasmatique
    if (input.na != null && input.cl != null && input.hco3 != null) {
      val ta = input.na - (input.cl + input.hco3)
      val taFormatted = String.format(Locale.FRANCE, "%.1f mmol/L", ta)
      metrics.add("Trou Anionique Plasmatique" to taFormatted)
      if (ta > 16f) {
        if (alert != "CRITICAL") alert = "MODERATE"
        details.add("Trou anionique élevé ($taFormatted, normale 12 ± 4) : signe une acidose métabolique à trou anionique augmenté (causes KUSSMAUL : Cétoacidose, Urémie rénale, Salicylés, Méthanol, Acidose Lactique, Ethylène glycol).")
      }
    }

    return BiologicalInterpretation(
      alertLevel = alert,
      title = "Interprétation Ionogramme, Équilibre Acido-Basique & Rein",
      summary = if (alert == "CRITICAL") "Anomalie hydro-électrolytique ou rénale critique." else if (alert == "MODERATE") "Déséquilibre ionique ou rénal à explorer." else "Ionogramme et fonction rénale dans les normes.",
      details = details,
      clinicalPearls = pearls,
      computedMetrics = metrics
    )
  }

  // 4. Interprétation du Bilan Hépatique
  fun interpretHepatic(input: HepaticInput): BiologicalInterpretation {
    val details = mutableListOf<String>()
    val pearls = mutableListOf<String>()
    val metrics = mutableListOf<Pair<String, String>>()
    var alert = "NORMAL"

    var hasCytolysis = false
    var hasCholestasis = false

    // Cytolyse
    if (input.alat != null && input.asat != null) {
      val deRitis = if (input.alat > 0) input.asat / input.alat else 0f
      metrics.add("Rapport De Ritis (ASAT / ALAT)" to String.format(Locale.FRANCE, "%.2f", deRitis))

      if (input.alat > 150f || input.asat > 150f) {
        alert = "CRITICAL"
        hasCytolysis = true
        details.add("CYTOLYSE HÉPATIQUE MAJEURE (> 3 à 5N) : atteinte aiguë des hépatocytes. Évoquer une hépatite virale aiguë (A, B, C, E), une hépatite médicamenteuse toxique (Paracétamol), une ischémie hépatique aiguë (foie de choc) ou une migration lithiasique brutale.")
        pearls.add("Doser impérativement le Taux de Prothrombine (TP) et le Facteur V : un TP < 50% signe une insuffisance hépatocellulaire aiguë grave (hépatite fulminante) imposant le transfert en réanimation hépatologique !")
      } else if (input.alat > 45f || input.asat > 35f) {
        if (alert != "CRITICAL") alert = "MODERATE"
        hasCytolysis = true
        val etio = if (deRitis > 2.0f) "prédominance ASAT (De Ritis > 2), très évocatrice d'hépatopathie alcoolique aiguë ou de cirrhose." else "prédominance ALAT, évocatrice de stéato-hépatite non alcoolique (NASH/MASH) ou hépatite virale chronique."
        details.add("Cytolyse hépatique modérée ($etio).")
      } else {
        details.add("Transaminases (ASAT/ALAT) physiologiques sans cytolyse.")
      }
    }

    // Cholestase
    if (input.pal != null && input.ggt != null) {
      if (input.pal > 200f || input.ggt > 100f) {
        if (alert != "CRITICAL") alert = "MODERATE"
        hasCholestasis = true
        details.add("SYNDROME DE CHOLESTASE BIOLOGIQUE (PAL et GGT augmentées) : obstacle sur les voies biliaires (lithiase de la VBP, cancer tête pancréas) ou cholestase intrahépatique (médicamenteuse, cirrhose biliaire primitive).")
        pearls.add("Une élévation isolée des GGT sans élévation des PAL n'est PAS un syndrome de cholestase : elle traduit une induction enzymatique (alcool, médicaments inducteurs type rifampicine, antiépileptiques) ou une stéatose.")
      }
    }

    // Bilirubine
    if (input.biliTotal != null) {
      if (input.biliTotal > 50f) {
        alert = "CRITICAL"
        details.add("Ictère franc clinique (Bilirubine totale ${input.biliTotal} µmol/L).")
        if (input.biliConj != null && input.biliConj > (input.biliTotal * 0.5f)) {
          details.add("Ictère à prédominance CONJUGUÉE (directe) : confirme l'obstacle biliaire ou l'atteinte hépato-cellulaire sévère. Échographie abdominale urgente pour explorer les voies biliaires !")
        } else {
          details.add("Ictère à prédominance LIBRE (non conjuguée) : évoquer une hémolyse aiguë ou la maladie bénigne de Gilbert (ictère de jeûne ou de fatigue).")
        }
      } else if (input.biliTotal > 21f) {
        if (alert != "CRITICAL") alert = "MODERATE"
        details.add("Hyperbilirubinémie modérée / subictère (${input.biliTotal} µmol/L).")
      }
    }

    // Lipase
    if (input.lipase != null) {
      if (input.lipase > 180f) {
        alert = "CRITICAL"
        details.add("LIPASÉMIE ÉLEVÉE (> 3N : ${input.lipase} UI/L) : DIAGNOSTIC DE PANCRÉATITE AIGUË AFFIRMÉ (associée à une douleur épigastrique transfixiante typique, aucun scanner n'est requis pour poser le diagnostic initial).")
        pearls.add("Le scanner abdominal injecté dans la pancréatite aiguë ne doit être réalisé qu'à 72-96h du début des douleurs pour évaluer la nécrose (score de Balthazar).")
      }
    }

    return BiologicalInterpretation(
      alertLevel = alert,
      title = "Interprétation du Bilan Hépatique & Pancréatique",
      summary = if (alert == "CRITICAL") "Atteinte hépatique ou pancréatique aiguë sévère." else if (hasCytolysis || hasCholestasis) "Syndrome hépatobiliaire présent." else "Bilan hépatique normal sans anomalie.",
      details = details,
      clinicalPearls = pearls,
      computedMetrics = metrics
    )
  }

  // 5. Interprétation Inflammation & Coagulation
  fun interpretInflamCoag(input: InflamCoagInput): BiologicalInterpretation {
    val details = mutableListOf<String>()
    val pearls = mutableListOf<String>()
    val metrics = mutableListOf<Pair<String, String>>()
    var alert = "NORMAL"

    // Inflammation
    if (input.crp != null) {
      when {
        input.crp > 100f -> {
          alert = "CRITICAL"
          details.add("Syndrome inflammatoire biologique MAJEUR (CRP ${input.crp} mg/L) : forte probabilité d'infection bactérienne invasive sévère, sepsis ou poussée de vascularite systémique.")
        }
        input.crp > 10f -> {
          if (alert != "CRITICAL") alert = "MODERATE"
          details.add("Syndrome inflammatoire biologique franc (CRP ${input.crp} mg/L).")
        }
        else -> details.add("CRP normale (< 10 mg/L) : absence de syndrome inflammatoire systémique significatif.")
      }
    }

    // Procalcitonine
    if (input.pct != null) {
      if (input.pct > 2.0f) {
        alert = "CRITICAL"
        details.add("Procalcitonine très élevée (${input.pct} ng/mL) : signature d'un SEPSIS BACTÉRIEN sévère ou choc septique. Antibiothérapie urgente.")
      } else if (input.pct in 0.5f..2.0f) {
        if (alert != "CRITICAL") alert = "MODERATE"
        details.add("Procalcitonine modérément élevée (${input.pct} ng/mL) : infection bactérienne probable.")
      } else {
        details.add("Procalcitonine basse (< 0.5 ng/mL) : oriente plutôt vers une infection virale ou une inflammation non bactérienne.")
      }
    }

    // Coagulation (TP / INR / TCA)
    if (input.tp != null) {
      if (input.tp < 50f) {
        alert = "CRITICAL"
        details.add("BAISSE SÉVÈRE DU TP (${input.tp}%) : risque hémorragique spontané majeur !")
        pearls.add("Distinguer carence en vitamine K (TP bas, TCA allongé, Facteur V normal) d'une insuffisance hépatocellulaire sévère (TP bas et Facteur V bas).")
      } else if (input.tp < 70f) {
        if (alert != "CRITICAL") alert = "MODERATE"
        details.add("Baisse modérée du taux de prothrombine (${input.tp}%).")
      }
    }

    if (input.tcaRatio != null && input.tcaRatio > 1.3f) {
      if (alert != "CRITICAL") alert = "MODERATE"
      details.add("Allongement du TCA (ratio patient/témoin ${input.tcaRatio}) : anomalie de la voie endogène (traitement par héparine, déficit en facteurs VIII/IX/XI, ou anticoagulant lupique circulant).")
    }

    // D-Dimères
    if (input.dDimeres != null) {
      if (input.dDimeres > 500f) {
        if (alert != "CRITICAL") alert = "MODERATE"
        details.add("D-Dimères élevés (${input.dDimeres} ng/mL) : activation de la coagulation et de la fibrinolyse. Valeur prédictive négative élevée : des D-Dimères normaux éliminent une embolie pulmonaire ou une TVP chez un patient à probabilité clinique faible ou intermédiaire.")
      }
    }

    return BiologicalInterpretation(
      alertLevel = alert,
      title = "Interprétation Bilan Inflammatoire & Hémostase",
      summary = if (alert == "CRITICAL") "Anomalie inflammatoire sévère ou coagulopathie menaçante." else if (alert == "MODERATE") "Syndrome inflammatoire ou perturbation de coagulation." else "Bilan d'hémostase et inflammatoire équilibré.",
      details = details,
      clinicalPearls = pearls,
      computedMetrics = metrics
    )
  }

  // 6. Interprétation Bandelette Urinaire & ECBU
  fun interpretUrine(input: UrineDipstickInput): BiologicalInterpretation {
    val details = mutableListOf<String>()
    val pearls = mutableListOf<String>()
    var alert = "NORMAL"

    val hasLeuco = input.leucocytes != "Négatif"
    val hasNitrites = input.nitrites == "Positif"
    val hasProtein = input.proteinurie != "Négatif"
    val hasBlood = input.hematurie != "Négatif"
    val hasKetones = input.cetones != "Négatif"

    if (hasLeuco && hasNitrites) {
      if (alert != "CRITICAL") alert = "MODERATE"
      details.add("INFECTION URINAIRE BACTÉRIENNE HAUTEMENT PROBABLE (Leucocytes + et Nitrites +) : traduit la présence d'une pyurie et de bactéries sécrétant une nitrate réductase (famille des Entérobactéries, type Escherichia coli). Indication formelle d'ECBU.")
    } else if (hasLeuco && !hasNitrites) {
      details.add("Leucocyturie isolée sans nitrites : éliminer une infection à germe sans nitrate réductase (Staphylocoque, Entérocoque, Streptocoque B), une infection urinaire décapitée par antibiotiques, ou une tuberculose urogénitale.")
    }

    if (hasProtein) {
      if (input.proteinurie.contains("3g") || input.proteinurie.contains("3+")) {
        alert = "CRITICAL"
        details.add("Protéinurie massive (3+) : suspicion formelle de syndrome néphrotique. Dosage quantitatif de la protéinurie des 24h et albuminémie requis d'urgence.")
      } else {
        if (alert != "CRITICAL") alert = "MODERATE"
        details.add("Protéinurie détectée (${input.proteinurie}) : à quantifier en laboratoire par le ratio protéinurie/créatininurie sur échantillon matinal.")
      }
    }

    if (hasBlood) {
      if (alert != "CRITICAL") alert = "MODERATE"
      details.add("Hématurie à la bandelette (${input.hematurie}) : confirme la présence d'érythrocytes ou d'hémoglobine libre. Rechercher lithiase, tumeur urothéliale, infection ou glomérulonéphrite.")
    }

    if (hasKetones) {
      if (alert != "CRITICAL") alert = "MODERATE"
      details.add("Cétonurie détectée (${input.cetones}) : témoigne d'une cétose (jeûne prolongé ou acidocétose diabétique si associée à une glycosurie franche).")
    }

    pearls.add("Chez la femme non ménopausée sans symptôme, une bandelette Leucocytes - et Nitrites - possède une valeur prédictive négative de plus de 95% pour exclure une infection urinaire.")

    return BiologicalInterpretation(
      alertLevel = alert,
      title = "Interprétation Bandelette Urinaire & ECBU",
      summary = if (alert == "CRITICAL") "Anomalie urinaire majeure (protéinurie massive ou hématurie)." else if (hasLeuco || hasNitrites || hasBlood) "Anomalie dépistée à la bandelette urinaire." else "Bandelette urinaire normale sans anomalie.",
      details = details,
      clinicalPearls = pearls
    )
  }

  // 7. Interprétation Glasgow Coma Scale
  fun interpretGlasgow(e: Int, v: Int, m: Int): BiologicalInterpretation {
    val total = e + v + m
    val alertLevel = when {
      total <= 8 -> "CRITICAL"
      total in 9..12 -> "MODERATE"
      total in 13..14 -> "MODERATE"
      else -> "NORMAL"
    }

    val title = "Score de Glasgow (GCS) : $total / 15 (E$e V$v M$m)"
    val details = mutableListOf<String>()
    val pearls = mutableListOf<String>()

    when {
      total == 15 -> details.add("Conscience normale : patient vigile, orienté dans le temps et l'espace, répondant aux ordres simples.")
      total in 13..14 -> details.add("Altération légère de la vigilance ou confusion modérée.")
      total in 9..12 -> details.add("Altération modérée de la conscience / Somnolence pathologique. Surveillance horaire rapprochée et TDM cérébral recommandé.")
      total <= 8 -> {
        details.add("COMA SÉVÈRE / ALTÉRATION MAJEURE DE LA CONSCIENCE (GCS <= 8).")
        pearls.add("RÈGLE ABSOLUE : Tout score de Glasgow <= 8 impose une intubation trachéale en séquence rapide pour protéger les voies aériennes supérieures contre le risque d'inhalation bronchique !")
      }
    }

    return BiologicalInterpretation(
      alertLevel = alertLevel,
      title = title,
      summary = if (total <= 8) "Coma sévère - Signal d'alerte vital immédiat" else if (total < 15) "Altération neurologique nécessitant surveillance" else "État de conscience normal",
      details = details,
      clinicalPearls = pearls
    )
  }

  // Helper data class for tuple return
  private data class Tuple6<A, B, C, D, E, F>(
    val a: A, val b: B, val c: C, val d: D, val e: E, val f: F
  )

  // 8. Observation Médicale et Mise à Jour Quotidienne
  fun generateObservationText(
    patientName: String,
    patientAge: Int,
    patientGender: String,
    department: String,
    admissionReason: String,
    historyOfIllness: String,
    pastMedical: String,
    pastSurgical: String,
    allergies: String,
    lifestyle: String,
    generalExam: String,
    cvExam: String,
    respExam: String,
    digestiveExam: String,
    neuroExam: String,
    locomotorExam: String,
    urinaryExam: String,
    otherExams: String,
    complementaryExams: String,
    clinicalSynthesis: String,
    hypotheses: String
  ): String {
    val builder = StringBuilder()
    builder.append("OBSERVATION MÉDICALE CLINIQUE\n")
    builder.append("Service : $department\n")
    builder.append("Patient : $patientName, $patientAge ans, sexe $patientGender\n")
    builder.append("Date d'examen : Réalisée dans le cadre de l'apprentissage clinique El Vita\n\n")

    builder.append("1. MOTIF D'ADMISSION :\n")
    builder.append(if (admissionReason.isNotBlank()) admissionReason else "Non précisé.")
    builder.append("\n\n")

    builder.append("2. HISTOIRE DE LA MALADIE :\n")
    builder.append(if (historyOfIllness.isNotBlank()) historyOfIllness else "Non renseignée.")
    builder.append("\n\n")

    builder.append("3. TERRAIN ET ANTÉCÉDENTS :\n")
    builder.append("• Antécédents médicaux : ${if (pastMedical.isNotBlank()) pastMedical else "Aucun antécédent médical notable rapporté."}\n")
    builder.append("• Antécédents chirurgicaux : ${if (pastSurgical.isNotBlank()) pastSurgical else "Pas d'intervention chirurgicale antérieure."}\n")
    builder.append("• Allergies connues : ${if (allergies.isNotBlank()) allergies else "Pas d'allergie médicamenteuse ou alimentaire connue."}\n")
    builder.append("• Mode de vie & Toxiques : ${if (lifestyle.isNotBlank()) lifestyle else "Non fumeur, pas de consommation d'alcool."}\n\n")

    builder.append("4. EXAMEN GÉNÉRAL & CONSTANTES :\n")
    builder.append(if (generalExam.isNotBlank()) generalExam else "État général conservé, paramètres vitaux dans les limites acceptables.")
    builder.append("\n\n")

    builder.append("5. EXAMEN PHYSIQUE APPAREIL PAR APPAREIL :\n")
    builder.append("• Appareil Cardiovasculaire : ${if (cvExam.isNotBlank()) cvExam else "Bruits du cœur réguliers sans souffle surajouté. Pouls périphériques bien perçus. Pas de signe d'insuffisance cardiaque droite."}\n")
    builder.append("• Appareil Respiratoire : ${if (respExam.isNotBlank()) respExam else "Murmure vésiculaire symétrique sans bruit adventice surajouté. Eupnéique au repos."}\n")
    builder.append("• Appareil Digestif : ${if (digestiveExam.isNotBlank()) digestiveExam else "Abdomen souple, indolore, pas de masse palpable ni d'organomégalie. Bruits hydro-aériques présents."}\n")
    builder.append("• Système Neurologique : ${if (neuroExam.isNotBlank()) neuroExam else "Conscient, orienté, sans déficit sensitivo-moteur focal. Paires crâniennes intègres."}\n")
    builder.append("• Appareil Locomoteur : ${if (locomotorExam.isNotBlank()) locomotorExam else "Articulations libres, pas de déformation ni de signe inflammatoire."}\n")
    builder.append("• Appareil Urinaire & Autres : ${if (urinaryExam.isNotBlank()) urinaryExam else "Diurèse conservée, loges rénales indolores."}")
    if (otherExams.isNotBlank()) {
      builder.append("\n• Autres constatations : $otherExams")
    }
    builder.append("\n\n")

    builder.append("6. EXAMENS PARACLINIQUES COMPLÉMENTAIRES :\n")
    builder.append(if (complementaryExams.isNotBlank()) complementaryExams else "En attente des bilans biologiques et d'imagerie.")
    builder.append("\n\n")

    builder.append("7. SYNTHÈSE CLINIQUE & REGROUPEMENT SYNDROMIQUE :\n")
    builder.append(if (clinicalSynthesis.isNotBlank()) clinicalSynthesis else "En synthèse, tableau clinique recueilli nécessitant une corrélation paraclinique.")
    builder.append("\n\n")

    builder.append("8. HYPOTHÈSES DIAGNOSTIQUES & DISCUSSION PÉDAGOGIQUE :\n")
    builder.append(if (hypotheses.isNotBlank()) hypotheses else "Hypothèses en cours d'élaboration.")
    builder.append("\n\n")

    builder.append("──────────\n")
    builder.append("Document rédigé avec l'assistance pédagogique El Vita.\n")
    builder.append("Note légale : El Vita est un outil d’aide à l’observation et au raisonnement clinique. Il ne remplace ni le jugement médical ni l’enseignement.")

    return builder.toString()
  }

  fun generateDailyUpdateText(
    patientName: String,
    hospitalDay: String,
    date: String,
    department: String,
    dailyPoint: String,
    generalState: String,
    physicalExam: String,
    newExams: String,
    conclusion: String,
    treatmentPlan: String
  ): String {
    val builder = StringBuilder()
    builder.append("MISE À JOUR CLINIQUE QUOTIDIENNE ($hospitalDay)\n")
    builder.append("Patient : $patientName | Date : $date | Service : $department\n\n")

    builder.append("1. POINT CLINIQUE DU JOUR :\n")
    builder.append(if (dailyPoint.isNotBlank()) dailyPoint else "Évolution stable par rapport à la veille.")
    builder.append("\n\n")

    builder.append("2. ÉTAT GÉNÉRAL DU JOUR :\n")
    builder.append(if (generalState.isNotBlank()) generalState else "État général stationnaire.")
    builder.append("\n\n")

    builder.append("3. EXAMEN PHYSIQUE DU JOUR :\n")
    builder.append(if (physicalExam.isNotBlank()) physicalExam else "Examen somatique sans nouvelle anomalie détectée.")
    builder.append("\n\n")

    builder.append("4. NOUVEAUX EXAMENS COMPLÉMENTAIRES :\n")
    builder.append(if (newExams.isNotBlank()) newExams else "Pas de nouvel examen ce jour.")
    builder.append("\n\n")

    builder.append("5. CONCLUSION & ÉVOLUTION :\n")
    builder.append(if (conclusion.isNotBlank()) conclusion else "Évolution clinique favorable.")
    builder.append("\n\n")

    builder.append("6. TRAITEMENT EN COURS & CONDUITE À TENIR (CAT) :\n")
    builder.append(if (treatmentPlan.isNotBlank()) treatmentPlan else "Poursuite de la stratégie thérapeutique en cours.")
    builder.append("\n\n")

    builder.append("──────────\n")
    builder.append("Compte rendu quotidien enregistré dans le dossier El Vita.")
    return builder.toString()
  }
}
