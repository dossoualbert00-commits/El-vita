package com.example.data.model.sheets

import com.example.data.model.SemiologyCategory
import com.example.data.model.SemiologySheet

object SemiologyGeneralSheets {

  val sheets: List<SemiologySheet> = listOf(
    // 1. Altération de l'état général (AEG)
    SemiologySheet(
      id = "gen_aeg_3a",
      name = "Altération de l'État Général (AEG) et les 3A",
      category = SemiologyCategory.GENERAL,
      subCategory = "Syndromes",
      definition = "Syndrome clinique traduisant le retentissement général d'une affection organique sévère sur l'organisme, défini par la triade des '3A' : Asthénie, Anorexie, Amaigrissement.",
      physiopathology = "Sécrétion prolongée de cytokines pro-inflammatoires (TNF-alpha ou cachectine, IL-1, IL-6, IFN-gamma) induisant un hypercatabolisme protéique musculaire, une anorexie hypothalamique centrale et une fonte adipeuse.",
      etiologies = listOf(
        "Cancers solides profonds (poumon, côlon, pancréas, estomac)",
        "Hémopathies malignes (lymphomes de Hodgkin et non hodgkiniens, myélome, leucémies)",
        "Infections chroniques torpides (tuberculose, infection par le VIH, endocardite d'Osler, abcès profond)",
        "Maladies auto-immunes et vascularites (maladie de Horton, lupus)",
        "Affections endocriniennes (insuffisance surrénale lente, hyperthyroïdie)",
        "Dépression sévère avec mélancolie du sujet âgé"
      ),
      circumstances = "Installation progressive sur plusieurs semaines ou mois, motivant une consultation pour perte d'autonomie ou fatigue insurmontable.",
      anamnesis = "Quantifier rigoureusement chaque élément : 1. Amaigrissement : chiffrer en kg et en % du poids de référence (grave si > 5% en 1 mois ou > 10% en 6 mois), vérifier la taille des vêtements. 2. Anorexie : sélective pour les viandes (cancer gastrique) ou globale. 3. Asthénie : physique, psychique, matinale ou permanente.",
      searchMethod = "Pesée sur balance étalonnée, mesure de la taille, calcul de l'Indice de Masse Corporelle (IMC), confrontation au carnet de santé ou aux ordonnances antérieures.",
      physicalExam = "Recherche d'une sarcopénie (fonte des fosses temporales, des interosseux et quadriceps). Examen somatique exhaustif : pâleur conjonctivale (anémie), aires ganglionnaires complètes, hépatosplénomégalie, toucher rectal, auscultation cardiaque (souffle nouveau), palpation mammaire et testiculaire.",
      associatedSigns = listOf(
        "Fièvre vespéro-nocturne prolongée",
        "Sueurs nocturnes profuses obligeant à changer de vêtement",
        "Prurit inexpliqué",
        "Douleurs osseuses fixes"
      ),
      clinicalSignificance = "Signal d'alarme clinique majeur imposant une enquête diagnostique méthodique sans retard pour éliminer un cancer ou une infection curable.",
      differentialDiagnoses = listOf(
        "Amaigrissement avec appétit conservé ou vorace (hyperthyroïdie, diabète sucré inaugural décompensé, malabsorption type maladie cœliaque)",
        "Asthénie isolée sans perte de poids (syndrome de fatigue chronique, anémie isolée)"
      ),
      complementaryExams = listOf(
        "Bilan biologique de débrouillage : NFS, CRP, VS, ionogramme sanguin, créatinine, urée",
        "Bilan hépatique complet, électrophorèse des protéines sériques (EPS), LDH, ferritinémie",
        "Sérologie VIH, TSH",
        "Radiographie thoracique et scanner thoraco-abdomino-pelvien (TAP)"
      ),
      gravitySigns = listOf(
        "Dénutrition sévère (IMC < 16 kg/m² ou perte de poids > 10% en 1 mois, albuminémie < 30 g/L)",
        "Syndrome confusionnel ou prostration",
        "Instabilité hémodynamique"
      ),
      pitfallsAndLimits = "Attribuer l'AEG chez le sujet âgé au simple vieillissement ou à une dépression sans avoir conduit un bilan organique rigoureux.",
      keyPoints = listOf(
        "Les 3A = Asthénie + Anorexie + Amaigrissement.",
        "Une perte de poids involontaire > 10% est pathologique jusqu'à preuve du contraire.",
        "Examen clinique complet de la tête aux pieds avec touchers pelviens systématiques."
      )
    ),

    // 2. Déshydratation intra et extracellulaire
    SemiologySheet(
      id = "gen_deshydratation",
      name = "Déshydratation Intracellulaire et Extracellulaire",
      category = SemiologyCategory.GENERAL,
      subCategory = "Signes physiques",
      definition = "Diminution du volume hydrique de l'organisme touchant soit le compartiment extracellulaire (DEC : hypovolémie plasmatique et interstitielle), soit le compartiment intracellulaire (DIC : hyperosmolarité plasmatique), soit les deux.",
      physiopathology = "DEC : perte iso-osmotique d'eau et de sodium (digestive, rénale ou cutanée) entraînant une contraction volémique. DIC : déficit d'eau pure supérieur au déficit sodé entraînant une hypernatrémie (> 145 mmol/L) et un gradient osmotique attirant l'eau hors des cellules.",
      etiologies = listOf(
        "DEC : Diarrhées aiguës profuses, vomissements incoercibles, abus de diurétiques, troisième secteur (occlusion, péritonite)",
        "DIC : Canicule, polypnée fébrile intense, absence d'accès à l'eau chez le sujet âgé grabataire ou le nourrisson, diabète insipide",
        "Globale : Gastro-entérite aiguë du nourrisson ou du sujet âgé, acidocétose diabétique"
      ),
      circumstances = "Épisodes caniculaires, épidémies hivernales de gastro-entérite, patient âgé dépendant en institution, traitement diurétique mal surveillé.",
      anamnesis = "Quantifier les pertes (nombre de selles liquides, vomissements), évaluer la sensation de soif (soif vive = DIC), quantifier la perte de poids récente (1 kg perdu = 1 L d'eau).",
      searchMethod = "Recherche méthodique des signes physiques spécifiques de chaque compartiment liquidien.",
      physicalExam = "Signes de DEC : Pli cutané persistant (pincement de la peau sous-claviculaire ou sur la cuisse), hypotension artérielle orthostatique puis de décubitus, tachycardie compensatrice, oligurie avec urines foncées, veines jugulaires plates. Signes de DIC : Soif intense, sécheresse de la muqueuse jugale et de la langue, hypotonie des globes oculaires, somnolence, confusion.",
      associatedSigns = listOf(
        "Perte de poids aiguë et brutale",
        "Fièvre d'origine centrale ou par diminution de la sudation",
        "Crampes musculaires et asthénie"
      ),
      clinicalSignificance = "Urgence diagnostique et réanimatoire pour prévenir l'état de choc hypovolémique et l'insuffisance rénale aiguë fonctionnelle.",
      differentialDiagnoses = listOf(
        "Pli cutané sénile par perte d'élastine chez le sujet âgé dénutri (tester le pli sous-claviculaire)",
        "Sécheresse buccale médicamenteuse par atropiniques sans déshydratation vraie"
      ),
      complementaryExams = listOf(
        "Ionogramme sanguin : natrémie normale dans la DEC pure, hypernatrémie > 145 mmol/L dans la DIC",
        "Hématocrite et protidémie (hémoconcentration dans la DEC)",
        "Urée et créatininémie (élévation avec rapport Urée/Créatinine > 100 signant le caractère fonctionnel)",
        "Ionogramme urinaire (Na urinaire < 20 mmol/L dans la DEC d'origine extrarénale)"
      ),
      gravitySigns = listOf(
        "Choc hypovolémique avec collapsus (PAS < 90 mmHg, marbrures, oligurie critique)",
        "Troubles neurologiques sévères (coma, convulsions par œdème cérébral lors des variations osmotiques rapides)"
      ),
      pitfallsAndLimits = "Une réhydratation trop rapide d'une hypernatrémie sévère peut provoquer un œdème cérébral mortel. La vitesse de correction ne doit pas dépasser 10-12 mmol/L par 24h.",
      keyPoints = listOf(
        "Pli cutané + hypotension + veines plates = Déshydratation extracellulaire (manque d'eau et de sel).",
        "Sécheresse muqueuse jugale + soif intense + hypernatrémie = Déshydratation intracellulaire (manque d'eau pure).",
        "Chaque kilogramme de poids perdu en phase aiguë correspond à 1 litre de liquide perdu."
      )
    )
  )
}
