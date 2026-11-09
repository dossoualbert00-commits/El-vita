package com.example.data.model.quiz

import com.example.data.model.QuizQuestion
import com.example.data.model.SemiologyCategory

val quizDigestiveQuestions: List<QuizQuestion> = listOf(
    QuizQuestion(
        id = "qd_01",
        question = "Comment met-on en évidence le signe de Murphy à l'examen de l'abdomen ?",
        options = listOf(
            "Palpation profonde de l'hypochondre droit sous le rebord costal provoquant une inhibition inspiratoire brutale et douloureuse",
            "Décompression brusque de la fosse iliaque droite déclenchant une vive douleur localisée",
            "Pression manuelle de la fosse iliaque gauche reproduisant la douleur dans la fosse iliaque droite",
            "Percussion de la paroi abdominale antérieure révélant un tympanisme pré-hépatique"
        ),
        correctOptionIndex = 0,
        explanation = "Le signe de Murphy consiste à placer les doigts sous le rebord costal droit au niveau de la ligne médioclaviculaire et à inviter le patient à inspirer profondément. Le contact de la vésicule biliaire enflammée descendant avec le diaphragme bloque brutalement l'inspiration (inhibition inspiratoire = Murphy positif, typique de cholécystite aiguë).",
        wrongOptionsExplanations = listOf(
            "La décompression brusque de la fosse iliaque droite est le signe de Blumberg.",
            "La palpation en fosse iliaque gauche déclenchant une douleur en fosse iliaque droite est le signe de Rovsing.",
            "La disparition de la matité hépatique au profit d'un tympanisme est le signe de Jobert (pneumopéritoine)."
        ),
        category = SemiologyCategory.DIGESTIVE
    ),
    QuizQuestion(
        id = "qd_02",
        question = "Quelle différence clinique fondamentale sépare la défense pariétale de la contracture abdominale ?",
        options = listOf(
            "La défense est réductible par une palpation douce et progressive avec les mains réchauffées, tandis que la contracture est invincible, permanente et involontaire (ventre de bois)",
            "La défense ne s'observe que chez les patients fébriles, alors que la contracture est toujours apyrétique",
            "La défense touche l'ensemble des quadrants abdominaux, alors que la contracture est toujours strictement localisée",
            "La défense disparaît complètement lors de la déglutition alors que la contracture s'aggrave"
        ),
        correctOptionIndex = 0,
        explanation = "La défense abdominale est une tension musculaire réflexe douloureuse qu'un examinateur doux, patient et aux mains chaudes arrive à vaincre en décontractant le malade. À l'inverse, la contracture péritonéale est un réflexe viscéro-moteur invincible, rigide, constant ('ventre de bois') signant formellement une péritonite aiguë généralisée.",
        wrongOptionsExplanations = listOf(
            "La fièvre peut accompagner aussi bien une défense localisée (appendicite, diverticulite) qu'une contracture généralisée.",
            "La contracture est le plus souvent généralisée à tout l'abdomen ou prédomine initialement au foyer causal.",
            "La déglutition n'a aucun effet relaxant sur une contracture péritonéale."
        ),
        category = SemiologyCategory.DIGESTIVE
    ),
    QuizQuestion(
        id = "qd_03",
        question = "Quelle triade de symptômes constitue le syndrome de Charcot dans l'angiocholite aiguë bactérienne ?",
        options = listOf(
            "Succession chronologique en 24-48h de : Douleur biliaire, Fièvre avec frissons, puis Ictère",
            "Association d'une hépatomégalie douloureuse, d'une ascite hémorragique et d'un souffle hépatique",
            "Triade de vomissements fécaloïdes, météorisme péri-ombilical et arrêt des matières et des gaz",
            "Fièvre continue en plateau, splénomégalie et taches rosées lenticulaires"
        ),
        correctOptionIndex = 0,
        explanation = "La triade sémiologique de Charcot (angiocholite aiguë) se caractérise par une chronologie stricte en 24 à 48 heures : 1. Douleur de l'hypochondre droit (colique hépatique), 2. Fièvre élevée avec frissons solennels (bactériémie), 3. Ictère cutanéo-muqueux d'aggravation rapide.",
        wrongOptionsExplanations = listOf(
            "L'hépatomégalie douloureuse avec ascite et souffle correspond au syndrome de Budd-Chiari ou au carcinome hépatocellulaire.",
            "Les vomissements fécaloïdes et l'arrêt des matières et des gaz définissent le syndrome d'occlusion intestinale.",
            "La fièvre en plateau avec splénomégalie et taches rosées signe la fièvre typhoïde."
        ),
        category = SemiologyCategory.DIGESTIVE
    ),
    QuizQuestion(
        id = "qd_04",
        question = "Que signe la découverte d'une grosse vésicule biliaire palpable et indolore chez un patient présentant un ictère franc (loi de Courvoisier-Terrier) ?",
        options = listOf(
            "Un obstacle tumoral malin sous-cystique sur la voie biliaire principale (adénocarcinome de la tête du pancréas ou ampullome)",
            "Une lithiase cholédocienne simple récidivante sur vésicule scléro-atrophique",
            "Une cirrhose hépatique d'origine éthylique avec hypertension portale",
            "Une hépatite virale aiguë fulminante sans obstacle mécanique"
        ),
        correctOptionIndex = 0,
        explanation = "La loi de Courvoisier-Terrier énonce qu'un ictère rétentionnel progressif sans douleur initiale associé à une grosse vésicule tendue, lisse et indolore à la palpation témoigne d'un obstacle malin extrinsèque sous-cystique (au premier rang duquel l'adénocarcinome de la tête du pancréas). Une vésicule lithiasique est rétractée et ne se distend pas.",
        wrongOptionsExplanations = listOf(
            "Dans la lithiase cholédocienne banale, les poussées inflammatoires antérieures rendent la vésicule scléreuse et non distensible, elle n'est donc pas palpable.",
            "La cirrhose donne une atrophie hépatique ou une hépatomégalie nodulaire sans distension vésiculaire isolée.",
            "L'hépatite virale produit un ictère hépatocellulaire sans distension mécanique de la vésicule."
        ),
        category = SemiologyCategory.DIGESTIVE
    ),
    QuizQuestion(
        id = "qd_05",
        question = "Quelle caractéristique sémiologique permet d'affirmer avec certitude un méléna ?",
        options = listOf(
            "Émission par l'anus de selles noires comme du goudron, poisseuses, fétides, résultant de la digestion du sang par les sucs gastriques",
            "Émission de sang rouge vif frais entourant des selles moulées lors de la défécation",
            "Coloration noirâtre des selles après prise de comprimés de sulfate de fer ou de charbon actif sans odeur nauséabonde",
            "Émission glaireuse et purulente striée de filets hémorragiques"
        ),
        correctOptionIndex = 0,
        explanation = "Le méléna est l'évacuation de sang digéré d'origine haute (en amont de l'angle de Treitz ou colique droit à transit ralenti). Il se reconnaît immédiatement à son aspect noir goudronneux, liquide ou pâteux, collant et à son odeur particulièrement pestilentielle et fétide sui generis.",
        wrongOptionsExplanations = listOf(
            "L'émission de sang rouge frais enrobant la selle correspond à une rectorragie d'origine ano-rectale (hémorroïdes, polype, cancer rectal).",
            "La coloration par le fer ou le charbon donne des selles noirâtres normales, moulées, sans consistance de goudron ni odeur fétide digestive.",
            "L'émission de glaires muco-purulentes sanguinolentes constitue le syndrome dysentérique (amibiase, shigellose, RCH)."
        ),
        category = SemiologyCategory.DIGESTIVE
    ),
    QuizQuestion(
        id = "qd_06",
        question = "Quels sont les 4 signes cardinaux composant le syndrome occlusif intestinal complet ?",
        options = listOf(
            "Douleur abdominale, vomissements précoces ou tardifs, arrêt total des matières et des gaz, et météorisme abdominal",
            "Diarrhée profuse, hyperthermie, déshydratation aiguë et pli cutané persistant",
            "Ictère cutané, prurit généralisé, selles décolorées et urines foncées",
            "Ascite abondante, hépatomégalie nodulaire, angiomes stellaires et érythrose palmaire"
        ),
        correctOptionIndex = 0,
        explanation = "Le syndrome occlusif réunit la tétrade classique : douleur abdominale paroxystique à type de colique, vomissements (alimentaires puis bilieux ou fécaloïdes), arrêt franc des matières et surtout des gaz (signe pathognomonique de l'interruption du transit) et météorisme (distension abdominale).",
        wrongOptionsExplanations = listOf(
            "Diarrhée et fièvre caractérisent un syndrome de gastro-entérite aiguë infectieuse ou colite.",
            "Ictère, prurit et selles blanches traduisent un syndrome de cholestase biliaire.",
            "Ascite et signes cutanés angiomateux sont les stigmates d'une décompensation cirrhotique."
        ),
        category = SemiologyCategory.DIGESTIVE
    ),
    QuizQuestion(
        id = "qd_07",
        question = "Comment recherche-t-on le signe du glaçon abdominal en présence d'une ascite abondante ?",
        options = listOf(
            "Impulsion brusque de la main sur la paroi au niveau du foie ou de la rate qui s'enfonce dans le liquide puis revient frapper les doigts",
            "Percussion combinée de deux hémi-abdomens avec onde de choc transmise par la ligne médiane",
            "Écoute au stéthoscope d'un souffle systolique continu au niveau de l'épigastre",
            "Pression douce exercée au point de Mac Burney avec soulèvement de la jambe droite"
        ),
        correctOptionIndex = 0,
        explanation = "Le signe du glaçon abdominal se recherche en déprimant brusquement la paroi abdominale en regard d'un viscère plein (foie ou rate) chez un patient avec ascite volumineuse : le viscère est refoulé en profondeur dans le liquide libre, puis remonte au contact et vient percuter en retour la pulpe des doigts qui restent en place.",
        wrongOptionsExplanations = listOf(
            "La percussion d'un flanc avec perception de l'onde sur l'autre flanc correspond au signe du flot abdominal (signe de la vague).",
            "Le souffle épigastrique continu avec renforcement systolique correspond au souffle de Cruveilhier-Baumgarten (reperméabilisation veineuse ombilicale).",
            "La manœuvre sur la jambe droite au point de Mac Burney teste le psoas (psoïtis appendiculaire)."
        ),
        category = SemiologyCategory.DIGESTIVE
    ),
    QuizQuestion(
        id = "qd_08",
        question = "Dans la sémiologie de l'insuffisance hépatocellulaire, quelle anomalie neuropsychiatrique constitue l'astérixis (flapping tremor) ?",
        options = listOf(
            "Chute brutale et brève du tonus musculaire des mains tendues en dorsiflexion, réalisant un battement rythmique saccadé",
            "Tremblement d'action intentionnel rapide prédominant aux extrémités lors de l'épreuve doigt-nez",
            "Contracture spastique pyramidale en flexion des membres supérieurs",
            "Mouvements choréiques amples involontaires et incoordonnés de la face et du cou"
        ),
        correctOptionIndex = 0,
        explanation = "L'astérixis ou flapping tremor est un myoclonus négatif : lors du maintien des bras tendus avec poignets en extension dorsale et doigts écartés, on observe des pertes brèves et répétées du tonus postural suivies d'une correction immédiate, donnant l'impression d'un battement d'ailes. C'est un signe majeur d'encéphalopathie hépatique (ou hypercapnique).",
        wrongOptionsExplanations = listOf(
            "Le tremblement intentionnel d'action à l'épreuve doigt-nez signe un syndrome cérébelleux cinétique.",
            "La spasticité élastique avec marche en fauchant relève d'un syndrome pyramidal.",
            "Les mouvements choréiques amples relèvent d'une atteinte des ganglions de la base (maladie de Huntington)."
        ),
        category = SemiologyCategory.DIGESTIVE
    ),
    QuizQuestion(
        id = "qd_09",
        question = "Quelle palpation permet d'affirmer qu'une masse de l'hypochondre gauche est une splénomégalie et non un rein gauche ptosé ?",
        options = listOf(
            "La présence d'un bord antérieur tranchant et crénelé, l'absence de contact lombaire et l'impossibilité d'interposer les doigts sous le rebord costal",
            "La présence d'un contact lombaire net et d'un ballottement bimanuel franc avec tympanisme colique sus-jacent",
            "Une mobilité complète de la masse lors des changements de position décubitus latéral",
            "Une pulsation expansive synchrone du pouls artériel fémoral"
        ),
        correctOptionIndex = 0,
        explanation = "La rate augmentée de volume présente un pôle inférieur descendant vers l'ombilic, un bord antérieur incisé d'une ou plusieurs crénelures caractéristiques, une matité franche en continuité avec la matité splénique, sans contact lombaire postérieur (contrairement au gros rein qui présente un ballottement et un contact lombaire).",
        wrongOptionsExplanations = listOf(
            "Le contact lombaire postérieur et le ballottement bimanuel de Guyon caractérisent une masse rénale ou rétropéritonéale.",
            "La mobilité totale indépendante de la respiration évoque une tumeur mésentérique ou un kyste ovarien pédiculé.",
            "Une masse expansive pulsatile traduit un volumineux anévrisme artériel."
        ),
        category = SemiologyCategory.DIGESTIVE
    ),
    QuizQuestion(
        id = "qd_10",
        question = "La triade de Whipple permet d'affirmer cliniquement le diagnostic d'hypoglycémie organique. Elle comprend :",
        options = listOf(
            "Signes neuro-végétatifs/neuroglucopéniques + Glycémie veineuse basse (< 0.50 g/L) + Disparition rapide des symptômes après resucrage",
            "Fièvre élevée + Douleur épigastrique transfixiante + Lipasémie > 3 fois la normale",
            "Polyurie + Polydipsie + Amaigrissement rapide avec cétonurie franche",
            "Ictère + Gros foie marron douloureux + Hémorragie digestive haute"
        ),
        correctOptionIndex = 0,
        explanation = "La triade de Whipple requiert : 1. Présence de symptômes compatibles avec une hypoglycémie (sueurs, tremblements, faim, confusion). 2. Constatation d'une glycémie plasmatique basse contemporaine (< 0.50 g/L ou 2.8 mmol/L chez le sujet non diabétique). 3. Correction immédiate des symptômes dès la normalisation de la glycémie.",
        wrongOptionsExplanations = listOf(
            "Douleur épigastrique et lipasémie élevée définissent la pancréatite aiguë.",
            "Polyurie, polydipsie et cétonurie définissent la décompensation acidocétosique du diabète.",
            "Ictère et hépatomégalie relèvent d'une hépatopathie sévère ou cholangite."
        ),
        category = SemiologyCategory.DIGESTIVE
    )
)
