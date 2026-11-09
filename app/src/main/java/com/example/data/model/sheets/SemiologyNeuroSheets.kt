package com.example.data.model.sheets

import com.example.data.model.SemiologyCategory
import com.example.data.model.SemiologySheet

object SemiologyNeuroSheets {

  val sheets: List<SemiologySheet> = listOf(
    // 1. Syndrome méningé
    SemiologySheet(
      id = "neuro_syndrome_meninge",
      name = "Syndrome Méningé (Raideur de Nuque, Kernig, Brudzinski)",
      category = SemiologyCategory.NEUROLOGICAL,
      subCategory = "Syndromes",
      definition = "Ensemble des symptômes et signes physiques traduisant l'irritation mécanique ou inflammatoire des leptoméninges (arachnoïde et pie-mère) et des racines nerveuses qui les traversent.",
      physiopathology = "L'inflammation infectieuse (méningite) ou le contact direct avec du sang (hémorragie sous-arachnoïdienne) stimule les terminaisons nociceptives méningées, déclenchant une contracture réflexe protectrice invincible des muscles spinaux et paravertébraux.",
      etiologies = listOf(
        "Méningites bactériennes purulentes (Méningocoque, Pneumocoque, Listeria monocytogenes)",
        "Méningites virales lymphocytaires (Entérovirus, HSV, VZV)",
        "Hémorragie sous-arachnoïdienne / rupture d'anévrisme cérébral (céphalée brutale 'en coup de tonnerre')",
        "Méningite tuberculeuse ou fongique (cryptococcose chez l'immunodéprimé)"
      ),
      circumstances = "Syndrome fébrile aigu associé à des céphalées intenses en casque, ou céphalée explosive brutale sans fièvre.",
      anamnesis = "Triade fonctionnelle méningée : 1. Céphalées violentes, diffuses, pulsatiles, avec photophobie et phonophobie, 2. Vomissements 'en jet', faciles, sans effort ni nausée préalable, 3. Constipation opiniâtre (signe inconstant).",
      searchMethod = "Patient détendu en décubitus dorsal strict sans oreiller. Mobilisation douce de la tête en flexion vers le sternum.",
      physicalExam = "1. Raideur de nuque : résistance invincible et douloureuse à la flexion passive antérieure de la tête (la rotation latérale reste libre). 2. Signe de Kernig : impossibilité d'étendre la jambe sur la cuisse fléchie à angle droit sans flexion réflexe du genou ou douleur lombaire vive. 3. Signe de Brudzinski : la flexion passive forcée de la nuque entraîne une flexion involontaire des deux genoux et hanches. 4. Attitude en 'chien de fusil' spontanée au lit.",
      associatedSigns = listOf("Hyperesthésie cutanée diffuse (le patient ne supporte pas l'effleurement du drap)", "Troubles de vigilance ou confusion", "PURPURA FÉBRILE (recherche systématique de taches nécrotiques : urgence vitale de purpura fulminans !)"),
      clinicalSignificance = "Urgence diagnostique absolue. Tout syndrome méningé fébrile est une méningite bactérienne jusqu'à preuve du contraire.",
      differentialDiagnoses = listOf(
        "Torticolis aigu ou contracture cervicale traumatique (mouvements latéraux et rotation bloqués également)",
        "Syndrome méningé du nourrisson (hypotonie, geignement, bombement de la fontanelle antérieure sans raideur)",
        "Méningisme (réaction méningée réflexe lors d'une pneumonie franche lobaire sans infection du LCR)"
      ),
      complementaryExams = listOf(
        "Ponction Lombaire (PL) en urgence : examen clé cytologique, biochimique (glycorachie / glycémie, protéinorachie) et bactériologique",
        "TDM cérébral préalable uniquement si signes de localisation neurologique, convulsions récentes ou GCS <= 11",
        "Hémocultures immédiates et bilan inflammatoire (NFS, CRP, procalcitonine)"
      ),
      gravitySigns = listOf("PURPURA FULMINANS : injection immédiate de Ceftriaxone IV/IM sans attendre la ponction lombaire !", "Coma avec score de Glasgow <= 8", "Signes de focalisation neurologique (engagement cérébral imminent)"),
      pitfallsAndLimits = "Chez le nourrisson et le grand vieillard, la raideur de nuque est souvent absente : le diagnostic repose sur l'altération du comportement, la somnolence inexpliquée ou l'hypotonie.",
      keyPoints = listOf(
        "Triade fonctionnelle : Céphalées en casque + Vomissements en jet + Photophobie.",
        "Signes physiques : Raideur de nuque + Signes de Kernig et de Brudzinski.",
        "Si Purpura fébrile associé : Ceftriaxone immédiate avant tout examen !"
      )
    ),

    // 2. Syndrome pyramidal
    SemiologySheet(
      id = "neuro_syndrome_pyramidal",
      name = "Syndrome Pyramidal et Signe de Babinski",
      category = SemiologyCategory.NEUROLOGICAL,
      subCategory = "Syndromes",
      definition = "Ensemble des signes cliniques résultant de l'interruption ou de l'atteinte anatomique de la voie cortico-spinale (voie motrice principale de la motricité volontaire).",
      physiopathology = "Lésion du premier motoneurone situé dans le cortex moteur primaire (aire 4) ou de ses axones descendant dans la capsule interne, le tronc cérébral ou la moelle épinière. Entraîne un déficit moteur associé à une libération des automatismes réflexes sous-jacents (perte du contrôle inhibiteur cortical).",
      etiologies = listOf(
        "Accident Vasculaire Cérébral (AVC ischémique ou hémorragique)",
        "Tumeurs cérébrales ou abcès cérébral",
        "Compression médullaire aiguë ou chronique (hernie discale, métastase épidurale, myélopathie cervicarthrosique)",
        "Sclérose en plaques (SEP) en poussée motrice",
        "Traumatisme crânio-encéphalique ou médullaire"
      ),
      circumstances = "Apparition brutale (AVC) ou progressive (tumeur, myélopathie) d'une faiblesse musculaire d'un membre ou d'un hémicorps.",
      anamnesis = "Recherche d'une lourdeur d'un membre, d'accrochage du pied à la marche (fauchage), de maladresse manuelle ou de troubles de la parole associés.",
      searchMethod = "Évaluation bilatérale et comparative de la force motrice, du tonus musculaire, des réflexes ostéo-tendineux et des réflexes cutanés.",
      physicalExam = "Association cardinale : 1. Déficit moteur prédominant sur les muscles extenseurs aux membres supérieurs et fléchisseurs aux membres inférieurs. 2. Hypertonie spastique élastique (cède en 'lame de canif'). 3. Hyperréflexie ostéo-tendineuse : réflexes vifs, polycinétiques, diffusés avec extension de la zone réflexogène. 4. SIGNE DE BABINSKI : extension lente et majestueuse du gros orteil à la stimulation du bord externe de la plante du pied. 5. Abolition des réflexes cutanés abdominaux.",
      associatedSigns = listOf("Clonus de la rotule et trépidation épileptoïde du pied", "Syncinésies (mouvements involontaires associés lors d'un effort controlatéral)", "Démarche spastique 'en fauchant'"),
      clinicalSignificance = "Le signe de Babinski affirme de façon formelle et irréfutable une atteinte organique du système pyramidal chez l'adulte.",
      differentialDiagnoses = listOf(
        "Syndrome neurogène périphérique (déficit flasque avec aréflexie et amyotrophie rapide)",
        "Déficit fonctionnel ou hystérique de conversion (pas de Babinski, réflexes symétriques normaux)",
        "Syndrome extrapyramidal parkinsonien (rigidité plastique en tuyau de plomb, pas de Babinski)"
      ),
      complementaryExams = listOf(
        "IRM cérébrale ou médullaire en urgence (évaluation lésionnelle haute résolution)",
        "Scanner cérébral sans injection (recherche d'hémorragie en phase aiguë d'AVC)",
        "Échodoppler des troncs supra-aortiques si suspicion d'ischémie artérielle"
      ),
      gravitySigns = listOf("AVC étendu en cours de constitution (indication de thrombolyse/thrombectomie dans les 4h30)", "Compression médullaire avec troubles sphinctériens (rétention d'urine : urgence neurochirurgicale de décompression)"),
      pitfallsAndLimits = "En phase aiguë et brutale d'une lésion pyramidale (phase de choc spinal ou cérébral), le tableau est initialement FLASQUE avec hypotonie et aréflexie ; la spasticité n'apparaît qu'après 2 à 3 semaines.",
      keyPoints = listOf(
        "Babinski = extension lente du gros orteil = signe pathognomonique.",
        "Hypertonie spastique élastique + Réflexes ostéo-tendineux vifs, polycinétiques, diffusés.",
        "Compression médullaire = urgence chirurgicale pour préserver la marche."
      )
    ),

    // 3. Syndrome cérébelleux
    SemiologySheet(
      id = "neuro_syndrome_cerebelleux",
      name = "Syndrome Cérébelleux (Ataxie, Dysmétrie, Tremblement)",
      category = SemiologyCategory.NEUROLOGICAL,
      subCategory = "Syndromes",
      definition = "Ensemble des signes résultant de l'atteinte du cervelet ou de ses voies de connexion, caractérisé par des troubles de la coordination motrice dans l'espace et le temps, sans déficit de la force musculaire.",
      physiopathology = "Le cervelet régule l'harmonie, le timing, la vitesse et la précision du mouvement volontaire ainsi que le maintien de l'équilibre. Sa lésion entraîne asynergie, dyschronométrie et dysmétrie.",
      etiologies = listOf(
        "Accident vasculaire cérébelleux (ischémie de l'artère cérébelleuse postéro-inférieure PICA)",
        "Sclérose en plaques (atteinte du tronc et pédoncules cérébelleux)",
        "Atrophie cérébelleuse alcoolique (prédomine sur le vermis et la statique)",
        "Tumeurs de la fosse cérébrale postérieure (médulloblastome, métastase)",
        "Syndromes paranéoplasiques (anticorps anti-Yo dans les cancers gynécologiques)"
      ),
      circumstances = "Troubles de la marche, chutes, maladresse progressive ou vertiges rotatoires aigus.",
      anamnesis = "Sensation d'ébriété à la marche ('comme si j'avais bu'), lâchage d'objets, modification de la voix (scandée) et de l'écriture (macrographie).",
      searchMethod = "Épreuves de coordination cinétique et étude de la station debout et de la marche.",
      physicalExam = "1. Cérébelleux cinétique : Dysmétrie / Hypermétrie à l'épreuve doigt-nez et talon-genou (dépassement de la cible), Adiadococinésie (incapacité à effectuer des mouvements alternatifs rapides comme les marionnettes), Tremblement d'intention et d'action en fin de geste. 2. Cérébelleux statique : Élargissement du polygone de sustentation, danse des tendons des jambiers antérieurs, marche titubante 'festonnante' pseudo-ébrieuse, signe de Romberg NÉGATIF (l'instabilité ne s'aggrave PAS à l'occlusion des yeux). 3. Baisse du tonus : réflexes pendulaires.",
      associatedSigns = listOf("Parole lente, scandée, explosive", "Nystagmus multidirectionnel", "Hypotonie passive avec épreuve du ballottement des mains"),
      clinicalSignificance = "Lésion de l'hémisphère cérébelleux (signes homolatéraux à la lésion) ou du vermis médian (instabilité axiale prédominante).",
      differentialDiagnoses = listOf(
        "Ataxie proprioceptive sensorielle (Romberg POSITIF : chute immédiate à la fermeture des yeux)",
        "Syndrome vestibulaire périphérique (vertige rotatoire intense avec déviation harmonieuse)",
        "Intoxication médicamenteuse ou alcoolique aiguë"
      ),
      complementaryExams = listOf(
        "IRM de la fosse cérébrale postérieure (examen de choix pour visualiser le cervelet)",
        "TDM cérébral si suspicion d'hématome cérébelleux aigu",
        "Ponction lombaire si suspicion de rhombencéphalite infectieuse"
      ),
      gravitySigns = listOf("Hématome cérébelleux ou œdème expansif comprimant le 4e ventricule (risque d'hydrocéphalie aiguë et d'engagement amygdalien)"),
      pitfallsAndLimits = "Ne jamais confondre le Romberg négatif du cérébelleux avec le Romberg positif de l'ataxie sensitive cordonale postérieure.",
      keyPoints = listOf(
        "Triade cinétique : Dysmétrie (épreuve doigt-nez) + Adiadococinésie (marionnettes) + Tremblement d'action.",
        "Ataxie statique : marche ébrieuse, polygone élargi, signe de Romberg NÉGATIF.",
        "Signes homolatéraux au côté de la lésion cérébelleuse."
      )
    ),

    // 4. Syndrome extrapyramidal parkinsonien
    SemiologySheet(
      id = "neuro_syndrome_parkinsonien",
      name = "Syndrome Parkinsonien (Triade Extrapyramidale)",
      category = SemiologyCategory.NEUROLOGICAL,
      subCategory = "Syndromes",
      definition = "Syndrome moteur sous-cortical résultant de la dégénérescence des neurones dopaminergiques de la voie nigro-striée (substance noire compacte vers le striatum).",
      physiopathology = "Déficit en dopamine striatale entraînant une désinhibition de la voie indirecte des ganglions de la base et une inhibition excessive du thalamus moteur.",
      etiologies = listOf(
        "Maladie de Parkinson idiopathique (cause la plus fréquente : asymétrique, sensible à la L-Dopa)",
        "Syndromes parkinsoniens iatrogènes / médicamenteux (neuroleptiques classiques, métoclopramide : symétriques)",
        "Atrophies multisystématisées (AMS)",
        "Paralysie supranucléaire progressive (PSP)",
        "Démence à corps de Lewy (avec hallucinations visuelles précoces)"
      ),
      circumstances = "Installation insidieuse d'une raideur musculaire, lenteur des gestes ou tremblement de la main au repos.",
      anamnesis = "Micrographie progressive, difficultés à se retourner dans le lit, perte du ballant d'un bras à la marche, voix monotone assourdie.",
      searchMethod = "Examen de la motricité au repos, mouvements alternatifs, recherche de la roue dentée par mobilisation passive.",
      physicalExam = "Triade parkinsonienne classique : 1. AKINÉSIE / BRADYKINÉSIE : lenteur d'initiation et de réalisation du geste, amimie faciale (faciès figé, clignement rare), perte du ballant automatique d'un bras à la marche. 2. HYPERTONIE RIGIDE PLASTIQUE : résistance permanente 'en tuyau de plomb' avec phénomène de la ROUE DENTÉE (cède par à-coups successifs, renforcée par la manœuvre de Froment au bras controlatéral). 3. TREMBLEMENT DE REPOS : lent (4-6 Hz), prédominant aux extrémités, 'émiettant' ou 'comptant la monnaie', disparaissant lors du mouvement volontaire.",
      associatedSigns = listOf("Instabilité posturale et chutes tardives", "Hypophonie", "Écriture microscopique", "Hypersialorrhée"),
      clinicalSignificance = "L'asymétrie initiale et l'excellente réponse à la L-Dopa sont les critères cardinaux en faveur d'une authentique Maladie de Parkinson.",
      differentialDiagnoses = listOf(
        "Tremblement essentiel (tremblement d'action et d'attitude, rapide 8-12 Hz, amélioré par l'alcool, sans akinésie)",
        "Hydrocéphalie à pression normale (triade de Hakim-Adams : troubles de la marche 'aimantée', incontinence, démence)",
        "Dépression sévère du sujet âgé (ralentissement psychomoteur sans roue dentée)"
      ),
      complementaryExams = listOf(
        "Diagnostic essentiellement CLINIQUE : aucun examen biologique ou radiologique n'est nécessaire dans les formes typiques",
        "IRM cérébrale recommandée pour éliminer une cause vasculaire ou expansive",
        "Scintigraphie cérébrale des transporteurs de la dopamine (DaT-scan) dans les formes douteuses"
      ),
      gravitySigns = listOf("Syndrome malin des neuroleptiques (rigidité extrême, hyperthermie majeure, rhabdomyolyse : urgence vitale !)", "Blocages moteurs sévères (freezing) avec chutes à répétition"),
      pitfallsAndLimits = "Un tremblement de repos n'est pas obligatoire pour affirmer le diagnostic : environ 30% des patients parkinsoniens ont une forme akinéto-rigide pure sans aucun tremblement !",
      keyPoints = listOf(
        "Triade clinique : Akinésie/Bradykinésie + Rigidité plastique en roue dentée + Tremblement de repos (4-6 Hz).",
        "Hypertonie plastique en tuyau de plomb majorée par la manœuvre de Froment.",
        "Toujours rechercher la prise de neuroleptiques ou d'anti-émétiques masqués."
      )
    )
  )
}
