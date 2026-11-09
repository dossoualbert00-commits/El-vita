package com.example.data.model.sheets

import com.example.data.model.SemiologyCategory
import com.example.data.model.SemiologySheet

object SemiologyUrinarySheets {

  val sheets: List<SemiologySheet> = listOf(
    // 1. Dysurie et Pollakiurie
    SemiologySheet(
      id = "uro_dysurie_pollakiurie",
      name = "Dysurie et Pollakiurie",
      category = SemiologyCategory.URINARY,
      subCategory = "Signes fonctionnels",
      definition = "La dysurie est une difficulté à l'évacuation vésicale se traduisant par un jet faible, haché, avec nécessité de poussée abdominale et miction prolongée. La pollakiurie est une fréquence exagérée des mictions diurnes (> 7) et/ou nocturnes (nycturie >= 1), pour de petits volumes.",
      physiopathology = "La dysurie résulte soit d'un obstacle sous-vésical (adénome prostatique, sténose urétrale, caillotage), soit d'une hypocontractilité du détrusor. La pollakiurie découle d'une hyperactivité détrusorienne, d'une diminution de la capacité vésicale fonctionnelle ou d'une irritation muqueuse.",
      etiologies = listOf(
        "Hypertrophie bénigne de prostate (HBP)",
        "Infection urinaire basse (cystite aiguë) et prostatite aiguë",
        "Cancer de prostate obstructif",
        "Sténose urétrale cicatricielle",
        "Lithiase vésicale ou urétérale basse",
        "Vessie neurogène (diabète, sclérose en plaques)"
      ),
      circumstances = "Bilan de troubles mictionnels chez l'homme de plus de 50 ans ou brûlures mictionnelles aiguës chez la femme jeune.",
      anamnesis = "Évaluer le score IPSS chez l'homme. Préciser le délai d'attente à la miction, la force du jet, la sensation de vidange incomplète, les gouttes retardataires, les brûlures mictionnelles et la nycturie.",
      searchMethod = "Interrogatoire dirigé méthodique avec calendrier mictionnel sur 48-72h. Palpation et percussion sus-pubienne systématiques pour éliminer une rétention.",
      physicalExam = "Palpation sus-pubienne (recherche d'une matité à convexité supérieure signant un globe vésical). Toucher rectal chez l'homme (taille, consistance souple ou pierreuse, sillon médian). Palpation des fosses lombaires et des orifices herniaires.",
      associatedSigns = listOf(
        "Brûlures mictionnelles intenses",
        "Impériosités mictionnelles (urgenturies)",
        "Fuites d'urines par regorgement",
        "Urines troubles ou malodorantes",
        "Hématurie macroscopique"
      ),
      clinicalSignificance = "La dysurie chronique expose au retentissement d'amont sur le haut appareil urinaire (urétéro-hydronéphrose bilatérale et insuffisance rénale obstructive).",
      differentialDiagnoses = listOf(
        "Polyurie vraie (volume des 24h > 3 L : diabète sucré, diabète insipide, potomanie)",
        "Incontinence urinaire d'effort pure (sans pollakiurie ni résidu)",
        "Pollakiurie émotionnelle ou de stress"
      ),
      complementaryExams = listOf(
        "Bandelette urinaire et ECBU systématiques",
        "Échographie réno-vésico-prostatique avec mesure du résidu post-mictionnel (RPM)",
        "Débitmétrie mictionnelle (débit de pointe Qmax)",
        "Créatininémie et ionogramme sanguin",
        "Dosage du PSA total (à distance de l'infection aiguë)"
      ),
      gravitySigns = listOf(
        "Rétention aiguë d'urines complète douloureuse",
        "Fièvre élevée avec frissons (prostatite ou pyélonéphrite aiguë obstructive)",
        "Insuffisance rénale aiguë obstructive",
        "Altération hémodynamique septique"
      ),
      pitfallsAndLimits = "Prendre une pollakiurie avec miction goutte à goutte pour une banale incontinence alors qu'il s'agit d'un regorgement sur globe vésical chronique sous tension !",
      keyPoints = listOf(
        "Dysurie = obstacle sous-vésical ou hypocontractilité du détrusor.",
        "Toujours palper l'hypogastre pour éliminer un globe vésical.",
        "Toute pollakiurie fébrile chez l'homme est une prostatite aiguë jusqu'à preuve du contraire."
      )
    ),

    // 2. Rétention aiguë et Globe vésical
    SemiologySheet(
      id = "uro_globe_vesical",
      name = "Rétention Aiguë d'Urines et Globe Vésical",
      category = SemiologyCategory.URINARY,
      subCategory = "Signes physiques",
      definition = "Impossibilité brutale, totale et douloureuse d'émettre des urines malgré une vessie pleine. Se caractérise à l'examen par la découverte d'un globe vésical sous tension.",
      physiopathology = "Augmentation brutale de la résistance urétrale dépassant la pression maximale détrusorienne (adénome prostatique congestif, caillot, spasme sphinctérien réflexe post-opératoire ou médicamenteux anticholinergique) ou sidération motrice du muscle vésical.",
      etiologies = listOf(
        "Adénome de prostate (HBP décompensée)",
        "Caillotage vésical secondaire à une hématurie macroscopique",
        "Prostatite aiguë infectieuse",
        "Prise médicamenteuse anticholinergique, neuroleptique ou morphinique",
        "Compression médullaire aiguë ou syndrome de la queue de cheval",
        "Sténose urétrale serrée"
      ),
      circumstances = "Déclenchée fréquemment par la prise d'un anticholinergique, la consommation d'alcool, un alitement prolongé, une constipation opiniâtre ou une anesthésie générale récente.",
      anamnesis = "Heure de la dernière miction, envie impérieuse et douloureuse mais infructueuse d'uriner, angoisse majeure, antécédents de dysurie progressive.",
      searchMethod = "Inspection, palpation et percussion de la région hypogastrique dénudée, patient en décubitus dorsal.",
      physicalExam = "Inspection : voussure hypogastrique médiane régulière, rénitente. Palpation : masse sus-pubienne lisse, sensible à très douloureuse, tendue, non mobilisable. Percussion : matité franche à limite supérieure convexe vers l'ombilic. Toucher rectal systématique.",
      associatedSigns = listOf(
        "Agitation anxieuse intense",
        "Tachycardie réflexe et poussée d'HTA réactionnelle à la douleur",
        "Sueurs profuses",
        "Émission de quelques gouttes par trop-plein (incontinence par regorgement)"
      ),
      clinicalSignificance = "Urgence antalgique et fonctionnelle immédiate pour décomprimer la vessie et préserver le parenchyme rénal.",
      differentialDiagnoses = listOf(
        "Anurie (vessie vide à l'échographie, absence de globe et d'envie d'uriner)",
        "Ascite enkystée ou volumineux kyste ovarien",
        "Utérus gravide ou volumineux fibrome utérin"
      ),
      complementaryExams = listOf(
        "Échographie vésicale au lit du patient (Bladder Scan : volume > 400-500 mL)",
        "Ionogramme sanguin et créatininémie après drainage vésical",
        "ECBU systématique sur les premières urines recueillies",
        "Échographie rénale (recherche d'urétéro-hydronéphrose)"
      ),
      gravitySigns = listOf(
        "Insuffisance rénale aiguë obstructive majeure avec hyperkaliémie menaçante",
        "Sepsis urinaire sur rétention purulente",
        "Rupture vésicale sous-péritonéale (exceptionnelle)"
      ),
      pitfallsAndLimits = "Attention au syndrome de levée d'obstacle après drainage rapide : polyurie massive hypotonique pouvant entraîner un collapsus et des troubles ioniques graves. Compenser les pertes hydriques au millilitre près.",
      keyPoints = listOf(
        "Matité hypogastrique à convexité supérieure = globe vésical sous tension.",
        "Drainage urgent par sondage vésical ou cathéter sus-pubien.",
        "Contre-indications du cathéter sus-pubien : hématurie, cancer vessie, suspicion de prostatite, cicatrice de laparotomie sous-ombilicale."
      )
    ),

    // 3. Syndrome néphrotique et néphritique
    SemiologySheet(
      id = "uro_syndrome_nephrotique",
      name = "Syndrome Néphrotique et Néphritique",
      category = SemiologyCategory.URINARY,
      subCategory = "Syndromes",
      definition = "Le syndrome néphrotique est défini biologiquement par une protéinurie > 3 g/24h associée à une hypoalbuminémie < 30 g/L. Le syndrome néphritique associe brutalement HTA, œdèmes, hématurie et insuffisance rénale aiguë oligurique.",
      physiopathology = "Néphrotique : altération des podocytes et de la membrane basale glomérulaire provoquant une fuite massive d'albumine, chute de la pression oncotique plasmatique et fuite d'eau vers le secteur interstitiel. Néphritique : prolifération endocapillaire et inflammation aiguë obstruant les capillaires glomérulaires.",
      etiologies = listOf(
        "Néphrose lipoïdique (LGM - syndrome néphrotique idiopathique de l'enfant)",
        "Glomérulonéphrite extra-membraneuse (GEM chez l'adulte)",
        "Hyalinose segmentaire et focale (HSF)",
        "Glomérulonéphrite post-streptococcique aiguë (syndrome néphritique)",
        "Néphropathie diabétique et amylose rénale",
        "Lupus érythémateux disséminé et vascularites"
      ),
      circumstances = "Découverte devant des œdèmes des membres inférieurs et du visage d'apparition rapide, une prise de poids inexpliquée, ou lors d'une bandelette urinaire de routine.",
      anamnesis = "Épisode infectieux ORL ou cutané récent (angine, impétigo il y a 2 à 3 semaines), antécédents de diabète, lupus, prise d'AINS récente.",
      searchMethod = "Pesée quotidienne, mesure rigoureuse de la pression artérielle aux deux bras, examen à la bandelette urinaire systématique.",
      physicalExam = "Œdèmes bilatéraux, symétriques, blancs, mous, indolores, prenant le godet, prédominant aux paupières le matin et aux chevilles le soir. Mesure de la PA. Recherche d'épanchement des séreuses (ascite, pleurésie : anasarque).",
      associatedSigns = listOf(
        "Urines mousseuses/spumeuses à l'émission",
        "Hématurie macroscopique (urines couleur bouillon sale dans le syndrome néphritique)",
        "Prise de poids de plusieurs kilogrammes en quelques jours",
        "Asthénie et pâleur"
      ),
      clinicalSignificance = "Le syndrome néphrotique expose aux thromboses veineuses graves (fuite d'antithrombine III) et aux infections sévères à germes encapsulés (fuite d'immunoglobulines).",
      differentialDiagnoses = listOf(
        "Insuffisance cardiaque globale décompensée",
        "Cirrhose hépatique ascitique",
        "Malnutrition protéique sévère",
        "Entéropathie exsudative"
      ),
      complementaryExams = listOf(
        "Bandelette urinaire (albumine 3+ à 4+)",
        "Protéinurie des 24h ou ratio protéinurie/créatininurie urinaire",
        "Électrophorèse des protéines sériques (hypoalbuminémie, pic alpha-2)",
        "Ionogramme sanguin et créatininémie",
        "Ponction-biopsie rénale (PBR) quasi systématique chez l'adulte"
      ),
      gravitySigns = listOf(
        "Thrombose vasculaire aiguë (thrombose de veine rénale, embolie pulmonaire)",
        "Sepsis sévère bactérien (péritonite primitive à pneumocoque)",
        "Anasarque avec détresse respiratoire par pleurésie bilatérale",
        "Insuffisance rénale aiguë anurique"
      ),
      pitfallsAndLimits = "Ne jamais omettre le traitement anticoagulant préventif si l'albuminémie est inférieure à 20 g/L chez l'adulte (risque thromboembolique majeur).",
      keyPoints = listOf(
        "Triade néphrotique : Protéinurie > 3 g/24h + Hypoalbuminémie < 30 g/L + Œdèmes prenant le godet.",
        "Complications majeures : thromboses veineuses et infections bactériennes.",
        "PBR systématique chez l'adulte pour typer la néphropathie glomérulaire."
      )
    )
  )
}
