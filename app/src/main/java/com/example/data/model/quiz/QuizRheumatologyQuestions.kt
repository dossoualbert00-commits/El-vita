package com.example.data.model.quiz

import com.example.data.model.QuizQuestion
import com.example.data.model.SemiologyCategory

val quizRheumatologyQuestions: List<QuizQuestion> = listOf(
    QuizQuestion(
        id = "qrh_01",
        question = "Quelle caractéristique clinique signe un horaire de douleur articulaire purement inflammatoire ?",
        options = listOf(
            "Réveil nocturne en seconde partie de nuit (vers 3-4h du matin) et dérouillage matinal prolongé supérieur à 30-60 minutes",
            "Douleur maximale le soir en fin de journée, aggravée par la marche et calmée par le repos complet",
            "Douleur aiguë survenant uniquement au début d'un effort puis disparaissant totalement pendant l'activité",
            "Dérouillage matinal très bref inférieur à 10 minutes cédant aux premiers pas"
        ),
        correctOptionIndex = 0,
        explanation = "La douleur inflammatoire est liée à la stase et à la synthèse nocturne de cytokines pro-inflammatoires synoviales : elle réveille le malade dans la seconde moitié de la nuit, s'accompagne d'un enraidissement matinal durable (> 30 à 60 minutes) et a tendance à s'améliorer au fil de la journée avec la mobilisation articulaire.",
        wrongOptionsExplanations = listOf(
            "La douleur vespérale calmée par le repos et majorée par l'effort correspond typiquement à l'horaire mécanique de l'arthrose.",
            "La douleur de début d'effort disparaissant à l'échauffement s'observe dans les tendinopathies mécaniques chroniques.",
            "Le dérouillage matinal court (< 10-15 minutes) est typique des pathologies dégénératives mécaniques."
        ),
        category = SemiologyCategory.RHEUMATOLOGY
    ),
    QuizQuestion(
        id = "qrh_02",
        question = "Quelle articulation est le siège préférentiel électif de la première crise de goutte aiguë microcristalline ?",
        options = listOf(
            "La première articulation métatarso-phalangienne (gros orteil / podagre)",
            "L'articulation gléno-humérale de l'épaule gauche",
            "L'articulation temporo-mandibulaire",
            "L'articulation sacro-iliaque droite isolée"
        ),
        correctOptionIndex = 0,
        explanation = "La première articulation métatarso-phalangienne (MTP1) du gros orteil est touchée dans plus de 70% des premières crises de goutte (podagre). Elle réalise un tableau de monoarthrite aiguë hyperalgique nocturne avec tuméfaction rouge violacée luisante, chaleur locale intense et hypersensibilité cutanée extrême au simple contact du drap.",
        wrongOptionsExplanations = listOf(
            "L'épaule est plutôt le siège d'une tendinopathie de la coiffe des rotateurs ou d'une chondrocalcinose.",
            "L'atteinte temporo-mandibulaire isolée est exceptionnelle dans la goutte.",
            "La sacro-iliite est le siège d'élection de la spondylarthrite ankylosante."
        ),
        category = SemiologyCategory.RHEUMATOLOGY
    ),
    QuizQuestion(
        id = "qrh_03",
        question = "Dans l'arthrose digitale des mains, quelle est la localisation respective des nodosités d'Heberden et de Bouchard ?",
        options = listOf(
            "Heberden = Interphalangiennes distales (IPD) ; Bouchard = Interphalangiennes proximales (IPP)",
            "Heberden = Interphalangiennes proximales (IPP) ; Bouchard = Interphalangiennes distales (IPD)",
            "Heberden = Métacarpo-phalangiennes (MCP) ; Bouchard = Articulation radio-carpienne",
            "Heberden = Trapézo-métacarpienne (rhizarthrose) ; Bouchard = IPD"
        ),
        correctOptionIndex = 0,
        explanation = "Moyen mnémotechnique classique : H-D (Heberden = Distales, IPD) et B-P (Bouchard = Proximales, IPP). Ces nodosités sont des ostéophytes marginaux fermes, indolores ou peu douloureux, déformant progressivement les doigts dans l'arthrose digitale.",
        wrongOptionsExplanations = listOf(
            "Inverser IPD et IPP est une erreur classique de sémiologie locomotrice.",
            "L'atteinte prédominante des MCP avec respect des IPD est la marque de fabrique de la Polyarthrite Rhumatoïde, non de l'arthrose.",
            "L'arthrose de la base du pouce s'appelle la rhizarthrose du pouce."
        ),
        category = SemiologyCategory.RHEUMATOLOGY
    ),
    QuizQuestion(
        id = "qrh_04",
        question = "Comment réalise-t-on la manœuvre de Phalen pour rechercher un syndrome du canal carpien ?",
        options = listOf(
            "Flexion passive complète forcée des deux poignets à 90° maintenue pendant 60 secondes dos des mains jointes, provoquant des paresthésies dans le territoire du nerf médian",
            "Extension forcée du poignet avec percussion douce au marteau réflexe sur la tabatière anatomique",
            "Abduction contrariée du pouce contre résistance provoquant une douleur au processus styloïde radial",
            "Élévation du bras au-dessus de la tête jusqu'à disparition du pouls radial"
        ),
        correctOptionIndex = 0,
        explanation = "Le test de Phalen consiste à maintenir les poignets fléchis à angle droit pendant une minute en appuyant les dos des mains l'un contre l'autre. La flexion comprime le nerf médian dans le canal carpien et réveille les paresthésies (fourmillements, engourdissements) dans les trois premiers doigts et la moitié radiale du 4e doigt.",
        wrongOptionsExplanations = listOf(
            "La percussion sur le ligament antérieur du carpe au marteau ou au doigt est le signe de Tinel.",
            "La douleur au styloïde radial lors de l'abduction/adduction contrariée du pouce est le test de Finkelstein (ténosynovite de De Quervain).",
            "La disparition du pouls radial lors de l'élévation du bras explore le défilé thoraco-brachial (manœuvre d'Adson)."
        ),
        category = SemiologyCategory.RHEUMATOLOGY
    ),
    QuizQuestion(
        id = "qrh_05",
        question = "Quelle manœuvre permet d'explorer une radiculalgie crurale (racine L3 ou L4) ?",
        options = listOf(
            "La manœuvre de Léri (ou signe du Lasègue inversé en décubitus ventral)",
            "La manœuvre de Lasègue en décubitus dorsal jambe tendue",
            "La manœuvre de Trendelenburg en appui monopodal",
            "Le test du tiroir antérieur du genou fléchi à 90°"
        ),
        correctOptionIndex = 0,
        explanation = "Le signe de Léri se recherche chez un patient en décubitus ventral : l'examinateur fléchit le genou à 90° puis soulève la cuisse en hyperextension postérieure de hanche. Cela met sous tension le nerf fémoral (crural) et reproduit la douleur cuisante antérieure de la cruralgie L3 ou L4.",
        wrongOptionsExplanations = listOf(
            "La manœuvre de Lasègue met sous tension le nerf sciatique (racines L5 et S1) à la face postérieure.",
            "La manœuvre de Trendelenburg évalue la force du moyen fessier.",
            "Le tiroir antérieur teste l'intégrité du ligament croisé antérieur (LCA)."
        ),
        category = SemiologyCategory.RHEUMATOLOGY
    ),
    QuizQuestion(
        id = "qrh_06",
        question = "Devant un épanchement articulaire aigu, quel nombre de leucocytes par millimètre cube dans le liquide synovial signe un liquide de nature inflammatoire ?",
        options = listOf(
            "Supérieur à 2 000 éléments / mm3 (souvent > 10 000 / mm3)",
            "Inférieur à 200 éléments / mm3 avec liquide limpide visqueux",
            "Strictement compris entre 500 et 1 000 éléments / mm3",
            "Présence exclusive d'hématies sans aucun leucocyte"
        ),
        correctOptionIndex = 0,
        explanation = "L'analyse cytochimique du liquide de ponction articulaire pose la règle sémiologique des 2 000 éléments : un liquide mécanique contient < 2 000 GB/mm3 avec moins de 50% de PNN (clair, visqueux). Un liquide inflammatoire contient > 2 000 GB/mm3 avec prédominance de PNN. Au-delà de 50 000 GB/mm3 avec PNN altérés, une arthrite septique doit être suspectée en priorité.",
        wrongOptionsExplanations = listOf(
            "Moins de 200 éléments correspond à un liquide mécanique parfaitement normal ou d'arthrose débutante.",
            "Entre 500 et 1 000 éléments correspond à un liquide articulaire mécanique.",
            "La présence de sang incoagulable signe une hémarthrose traumatique ou hémophile."
        ),
        category = SemiologyCategory.RHEUMATOLOGY
    ),
    QuizQuestion(
        id = "qrh_07",
        question = "Dans la polyarthrite rhumatoïde (PR) établie, quelle déformation caractéristique des doigts observe-t-on fréquemment ?",
        options = listOf(
            "Déviation des doigts en coup de vent ulnaire, doigts en col de cygne et en boutonnière",
            "Doigts élargis en boudin (dactylite) avec atteinte isolée des interphalangiennes distales",
            "Rétraction irréductible de l'aponévrose palmaire moyenne (maladie de Dupuytren)",
            "Hyperlaxité ligamentaire majeure avec déformation indolore réductible de Jaccoud"
        ),
        correctOptionIndex = 0,
        explanation = "La destruction progressive des structures capsulo-ligamentaires et tendineuses dans la PR conduit aux déformations caractéristiques des mains : coup de vent ulnaire des métacarpo-phalangiennes, doigts en col-de-cygne (hyperextension de l'IPP et flexion de l'IPD), doigts en boutonnière (flexion de l'IPP et hyperextension de l'IPD) et pouce en Z.",
        wrongOptionsExplanations = listOf(
            "L'aspect de doigt en boudin (dactylite) avec atteinte des IPD caractérise le rhumatisme psoriasique.",
            "La maladie de Dupuytren est une fibromatose palmaire rétractile bénigne sans synovite inflammatoire.",
            "La déformation réductible de Jaccoud s'observe typiquement dans le lupus érythémateux disséminé."
        ),
        category = SemiologyCategory.RHEUMATOLOGY
    ),
    QuizQuestion(
        id = "qrh_08",
        question = "Quelle atteinte enthésique est très évocatrice d'une spondylarthrite ankylosante chez un sujet jeune ?",
        options = listOf(
            "Talalgie bilatérale ou à bascule à horaire inflammatoire (enthésite calcanéenne de l'insertion du tendon d'Achille ou de l'aponévrose plantaire)",
            "Épicondylite latérale d'effort unilatérale du coude chez le joueur de tennis",
            "Ténosynovite des extenseurs des doigts post-traumatique",
            "Bursite trochantérienne mécanique après course à pied"
        ),
        correctOptionIndex = 0,
        explanation = "L'enthèse (zone d'ancrage osseux d'un tendon ou ligament) est la cible anatomique privilégiée des spondylarthrites. L'enthésite calcanéenne (talalgie postérieure ou sous-calcanéenne survenant au premier lever le matin ou réveillant en fin de nuit, parfois à bascule) chez un adulte jeune est un signe d'orientation capital.",
        wrongOptionsExplanations = listOf(
            "L'épicondylite latérale est une tendinopathie mécanique de surmenage professionnel ou sportif.",
            "La ténosynovite post-traumatique est un phénomène local mécanique.",
            "La bursite trochantérienne mécanique ne s'accompagne pas d'horaire inflammatoire ni de terrain HLA-B27."
        ),
        category = SemiologyCategory.RHEUMATOLOGY
    ),
    QuizQuestion(
        id = "qrh_09",
        question = "Quel est le geste diagnostic et thérapeutique indispensable et immédiat devant toute monoarthrite aiguë fébrile ?",
        options = listOf(
            "Ponction articulaire exploratrice stérile immédiate avec examen cytologique, bactériologique direct et cultures",
            "Mise en route d'une antibiothérapie probabiliste orale avant tout prélèvement",
            "Immobilisation plâtrée stricte et prescription d'anti-inflammatoires non stéroïdiens (AINS)",
            "Attendre le résultat de la radiographie osseuse standard à 48 heures"
        ),
        correctOptionIndex = 0,
        explanation = "Toute monoarthrite aiguë est une arthrite septique jusqu'à preuve du contraire ! La ponction articulaire sous asepsie chirurgicale stricte est une urgence diagnostique absolue, à pratiquer AVANT toute antibiothérapie pour identifier le germe au direct et sur cultures et débuter le traitement adapté.",
        wrongOptionsExplanations = listOf(
            "Débuter des antibiotiques avant la ponction décapite les cultures bactériologiques et compromet le diagnostic de certitude.",
            "Les AINS sont formellement contre-indiqués devant une infection articulaire aiguë !",
            "Les radiographies sont normales au début d'une arthrite septique et ne doivent en aucun cas retarder le geste."
        ),
        category = SemiologyCategory.RHEUMATOLOGY
    ),
    QuizQuestion(
        id = "qrh_10",
        question = "Qu'est-ce que le test de Schöber à l'examen du rachis lombaire ?",
        options = listOf(
            "Mesure de l'ampliation du rachis lombaire en flexion antérieure à partir d'un repère tracé 10 cm au-dessus des épines iliaques postéro-supérieures (normale : allongement >= + 5 cm)",
            "Mesure de la distance doigts-sol en centimètres jambes tendues",
            "Mesure de l'ampliation thoracique circonférentielle au 4e espace intercostal",
            "Recherche d'une scoliose structurée par le test de flexion du tronc vers l'avant (gibbosité)"
        ),
        correctOptionIndex = 0,
        explanation = "L'indice de Schöber évalue la souplesse du rachis lombaire : on marque un point sur l'apophyse épineuse de S1 (ligne des fossettes de Vénus) et un second point 10 cm plus haut. En flexion antérieure maximale genoux tendus, la distance normale s'allonge à 15 cm ou plus (Schöber 10 + 5 cm). Un allongement < 4 cm traduit une raideur lombaire pathologique (spondylarthrite, discopathie sévère).",
        wrongOptionsExplanations = listOf(
            "La distance doigts-sol mesure la flexion globale du tronc mais dépend aussi de la souplesse des hanches.",
            "L'ampliation thoracique mesure l'expansion costale respiratoire (normale >= 5 cm).",
            "La recherche d'une gibbosité est le test d'Adams pour dépister la scoliose."
        ),
        category = SemiologyCategory.RHEUMATOLOGY
    )
)
