package com.example.data.model.sheets

import com.example.data.model.SemiologyCategory
import com.example.data.model.SemiologySheet

object SemiologyRheumatologySheets {

  val sheets: List<SemiologySheet> = listOf(
    // 1. Horaire mécanique vs inflammatoire
    SemiologySheet(
      id = "rhuma_horaire_douleur",
      name = "Horaire de Douleur Articulaire : Mécanique vs Inflammatoire",
      category = SemiologyCategory.RHEUMATOLOGY,
      subCategory = "Signes fonctionnels",
      definition = "Distinction clinique fondamentale en rhumatologie entre deux grands profils de douleur articulaire : l'horaire mécanique (dégénérescence cartilagineuse) et l'horaire inflammatoire (synovite ou atteinte systémique).",
      physiopathology = "Mécanique : contraintes de charge et de frottement sur un cartilage érodé et un os sous-chondral innervé, s'apaisant au repos. Inflammatoire : sécrétion permanente de cytokines pro-inflammatoires (TNF-alpha, IL-1, IL-6), vasodilatation synoviale nocturne et hyperpression liquidienne stimulant les nocicepteurs même au repos.",
      etiologies = listOf(
        "Mécanique : Arthrose (gonarthrose, coxarthrose, rachialgies dégénératives), tendinopathies, méniscopathies",
        "Inflammatoire : Polyarthrite rhumatoïde (PR), Spondylarthrite ankylosante",
        "Inflammatoire microcristalline : Goutte, Chondrocalcinose articulaire",
        "Inflammatoire infectieuse : Arthrite septique bactérienne",
        "Connectivites : Lupus érythémateux disséminé, sclérodermie"
      ),
      circumstances = "Consultation pour douleurs articulaires ou rachidiennes aiguës ou chroniques.",
      anamnesis = "Quatre questions clés : 1. Heure de réveil nocturne (deuxième partie de nuit en inflammatoire vs aucun en mécanique). 2. Durée du dérouillage matinal (< 15 min en mécanique vs > 30 à 60 min en inflammatoire). 3. Effet de l'effort (aggrave en mécanique vs soulage après mise en route en inflammatoire). 4. Effet du repos (soulage en mécanique vs inefficace en inflammatoire).",
      searchMethod = "Interrogatoire chronométré portant sur l'enraidissement matinal et le rythme nycthéméral de la douleur.",
      physicalExam = "Mécanique : articulation froide, craquements palpables en fin d'amplitude, pas d'épanchement chaud. Inflammatoire : rougeur, chaleur cutanée, épanchement liquidien sous tension (choc rotulien), limitation douloureuse globale.",
      associatedSigns = listOf(
        "Signes d'inflammation locale (chaleur, tuméfaction)",
        "Altération de l'état général et fébricule (rhumatisme inflammatoire)",
        "Nodosités d'Heberden et de Bouchard (arthrose digitale)"
      ),
      clinicalSignificance = "Oriente immédiatement les examens biologiques (CRP, VS) et la prise en charge thérapeutique (antalgiques/rééducation vs biothérapies/corticoïdes).",
      differentialDiagnoses = listOf(
        "Douleurs neuropathiques périphériques (brûlures sans gonflement articulaire)",
        "Fibromyalgie (douleurs diffuses aux 18 points de Yunus avec CRP normale)",
        "Ostéonécrose aseptique à la phase initiale"
      ),
      complementaryExams = listOf(
        "Bilan inflammatoire biologique : CRP et VS (normales en mécanique, élevées en inflammatoire)",
        "Radiographies standard face et profil des articulations douloureuses",
        "Ponction articulaire avec analyse cytologique du liquide synovial",
        "Bilan immunologique ciblé si horaire inflammatoire (ACPA, facteur rhumatoïde, HLA-B27)"
      ),
      gravitySigns = listOf(
        "Monoarthrite aiguë fébrile avec impotence fonctionnelle totale (urgence médico-chirurgicale : éliminer une arthrite septique)",
        "Atteinte polyarticulaire érosive d'évolution rapide"
      ),
      pitfallsAndLimits = "Une poussée congestive d'arthrose avec épanchement peut donner un dérouillage trompeur de 20-30 minutes, mais la ponction ramène un liquide mécanique (< 2 000 GB/mm3).",
      keyPoints = listOf(
        "Dérouillage > 30-60 min + réveil 2e moitié de nuit = horaire inflammatoire.",
        "Dérouillage < 15 min + aggravation à l'effort + soulagement au repos = horaire mécanique.",
        "Toute monoarthrite aiguë est une arthrite septique jusqu'à preuve du contraire."
      )
    ),

    // 2. Épanchement articulaire du genou et Choc rotulien
    SemiologySheet(
      id = "rhuma_choc_rotulien",
      name = "Épanchement Articulaire du Genou et Signe du Glaçon",
      category = SemiologyCategory.RHEUMATOLOGY,
      subCategory = "Signes physiques",
      definition = "Accumulation anormale de liquide synovial, sang ou pus dans la cavité articulaire fémoro-patellaire, mise en évidence par la manœuvre du choc rotulien (signe du glaçon).",
      physiopathology = "L'inflammation de la membrane synoviale (synovite) ou une rupture vasculaire capsulo-ligamentaire (hémarthrose) entraîne un excès de sécrétion liquidienne distendant les culs-de-sac sous-quadricipital et parapatellaires.",
      etiologies = listOf(
        "Liquide mécanique (< 2 000 GB/mm3) : Poussée congestive de gonarthrose, lésion méniscale",
        "Liquide inflammatoire (> 2 000 GB/mm3) : Goutte, chondrocalcinose, polyarthrite rhumatoïde",
        "Liquide septique (> 50 000 GB/mm3 à PNN altérés) : Arthrite bactérienne à Staphylococcus aureus",
        "Hémarthrose (sang incoagulable) : Rupture du ligament croisé antérieur (LCA), fracture ostéochondrale"
      ),
      circumstances = "Apparition rapide après traumatisme ou torsion du genou, ou poussée inflammatoire insomniante.",
      anamnesis = "Notion de craquement audible lors d'un choc, sensation de dérobement ou de genou sous tension, présence de fièvre.",
      searchMethod = "Patient en décubitus dorsal, membre inférieur en extension complète, quadriceps complètement relâché.",
      physicalExam = "Inspection : effacement des méplats parapatellaires et voussure sous-quadricipitale. Palpation : d'une main, refouler le liquide du cul-de-sac supérieur vers le bas ; de l'autre index, appuyer brusquement et verticalement sur la rotule. On sent la rotule s'enfoncer dans le liquide et claquer contre la trochlée fémorale (signe du glaçon).",
      associatedSigns = listOf(
        "Chaleur locale cutanée",
        "Limitation de la flexion du genou",
        "Kyste poplité de Baker palpable au creux postérieur"
      ),
      clinicalSignificance = "Confirme la présence d'un épanchement intra-articulaire franc et impose d'en déterminer la nature par ponction.",
      differentialDiagnoses = listOf(
        "Bursite pré-rotulienne superficielle (hygroma : collection extra-articulaire en avant de la rotule, choc rotulien négatif)",
        "Lipome ou œdème des tissus mous pré-patellaires"
      ),
      complementaryExams = listOf(
        "Ponction articulaire exploratrice stérile immédiate (aspect macroscopique, cytologie, cristaux, bactériologie)",
        "Radiographies standard du genou face, profil et défilé fémoro-patellaire",
        "Échographie articulaire (pour les épanchements de faible abondance)"
      ),
      gravitySigns = listOf(
        "Fièvre élevée > 38.5°C et frissons (arthrite septique à drainer d'urgence)",
        "Douleur hyperalgique insomniante avec choc rotulien sous tension extrême"
      ),
      pitfallsAndLimits = "Si le quadriceps reste contracté par anxiété ou douleur, la rotule est plaquée contre le fémur et le choc rotulien devient faussement négatif. Détendre parfaitement le patient.",
      keyPoints = listOf(
        "Choc rotulien = signe du glaçon = épanchement intra-articulaire franc.",
        "Règle sémiologique des 2 000 GB/mm3 : < 2 000 mécanique, > 2 000 inflammatoire.",
        "Ponctionner tout épanchement inexpliqué avant de débuter un antibiotique."
      )
    ),

    // 3. Lombosciatique et Lasègue
    SemiologySheet(
      id = "rhuma_sciatique_lasegue",
      name = "Lombosciatique et Signe de Lasègue",
      category = SemiologyCategory.RHEUMATOLOGY,
      subCategory = "Signes physiques",
      definition = "La lombosciatique est une radiculalgie du nerf sciatique (racine L5 ou S1) secondaire à un conflit disco-radiculaire. Le signe de Lasègue est la reproduction de la douleur radiculaire typique lors de l'élévation passive du membre inférieur tendu.",
      physiopathology = "La hernie discale (L4-L5 pour L5, L5-S1 pour S1) ou un récessus rétréci provoque une compression mécanique et une inflammation chimique par libération de phospholipase A2 sur la racine nerveuse.",
      etiologies = listOf(
        "Hernie discale lombaire (90% des cas)",
        "Canal lombaire étroit ou arthrose des massifs articulaires postérieurs",
        "Spondylolisthésis avec lyse isthmique",
        "Spondylodiscite infectieuse ou métastase épidurale (drapeaux rouges)"
      ),
      circumstances = "Survenue brutale après un effort de soulèvement de charge en antéflexion du tronc ou lors d'un faux mouvement.",
      anamnesis = "Topographie précise du trajet : L5 (fesse, face latérale de cuisse, face latérale de jambe, dos du pied jusqu'au gros orteil) vs S1 (fesse, face postérieure de cuisse, mollet, tendon d'Achille, plante et bord externe du pied vers le 5e orteil). Impulsivité à la toux.",
      searchMethod = "Manœuvre de Lasègue : patient couché sur le dos. L'examinateur élève lentement le membre inférieur en extension stricte. On note l'angle d'apparition de la douleur radiculaire irradiant sous le genou.",
      physicalExam = "Signe de Lasègue positif si douleur radiculaire reproduite entre 30° et 70°. Examen moteur systématique : marche sur les talons (L5 : releveurs), marche sur la pointe (S1 : triceps). Réflexes : abolition du réflexe achilléen dans la radiculopathie S1. Sensibilité des dermatomes.",
      associatedSigns = listOf(
        "Raideur rachidienne lombaire multidirectionnelle (distance doigts-sol)",
        "Contracture des muscles paravertébraux en corde",
        "Sonnette disco-radiculaire (douleur déclenchée par la pression paravertébrale en regard du disque)"
      ),
      clinicalSignificance = "Confirme l'irritation de la racine sciatique et guide le niveau lésionnel (L5 vs S1).",
      differentialDiagnoses = listOf(
        "Douleur projetée de coxarthrose ou de sacro-iliite",
        "Syndrome du muscle piriforme / fessier",
        "Claudication intermittente artérielle des membres inférieurs"
      ),
      complementaryExams = listOf(
        "Radiographies standard du rachis lombaire (non indispensables d'emblée si forme commune sans gravité)",
        "IRM lombaire en urgence si signe de gravité neurologique ou après échec du traitement médical à 4-6 semaines"
      ),
      gravitySigns = listOf(
        "Sciatique paralysante avec déficit moteur franc < 3/5 (urgence chirurgicale)",
        "Syndrome de la queue de cheval (anesthésie en selle, troubles sphinctériens mictionnels ou défécatoires)",
        "Sciatique hyperalgique résistante aux morphiniques IV"
      ),
      pitfallsAndLimits = "Ne pas confondre un vrai Lasègue avec une simple raideur douloureuse des ischio-jambiers (douleur bilatérale postérieure de cuisse sans irradiation au pied). Utiliser la manœuvre de Léri pour la racine fémorale L3/L4.",
      keyPoints = listOf(
        "Trajet L5 = face latérale et dos du pied. Trajet S1 = face postérieure, mollet, plante et abolition du réflexe achilléen.",
        "Signe de Lasègue = élévation de jambe tendue reproduisant la douleur radiculaire.",
        "Urgence chirurgicale immédiate si syndrome de la queue de cheval ou déficit moteur < 3/5."
      )
    )
  )
}
