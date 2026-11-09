package com.example.data.model.quiz

import com.example.data.model.QuizQuestion
import com.example.data.model.SemiologyCategory

val quizNeuroQuestions: List<QuizQuestion> = listOf(
    QuizQuestion(
        id = "qn_01",
        question = "Quelle réponse motrice caractérise formellement un signe de Babinski lors de la stimulation du bord latéral de la plante du pied ?",
        options = listOf(
            "Extension lente et majestueuse du gros orteil avec souvent écartement des autres orteils en éventail",
            "Flexion réflexe rapide et syncinétique de l'ensemble des orteils vers la plante du pied",
            "Retrait brusque et triple flexion involontaire d'évitement du membre inférieur",
            "Fasciculations musculaires isolées de la loge antéro-externe de la jambe"
        ),
        correctOptionIndex = 0,
        explanation = "Le signe de Babinski (inversion du réflexe cutané plantaire) est pathognomonique d'une lésion de la voie cortico-spinale (faisceau pyramidal). Il consiste en une extension dorsale lente et majestueuse de l'hallux, fréquemment associée à une abduction des 4 derniers orteils (signe de l'éventail de Dupré).",
        wrongOptionsExplanations = listOf(
            "La flexion plantaire rapide des orteils est la réponse physiologique normale du réflexe cutané-plantaire.",
            "Le retrait en triple flexion est un réflexe de défense nociceptif, à différencier d'un vrai Babinski.",
            "Les fasciculations traduisent une souffrance du motoneurone périphérique de la corne antérieure de la moelle."
        ),
        category = SemiologyCategory.NEUROLOGICAL
    ),
    QuizQuestion(
        id = "qn_02",
        question = "Quelle triade clinique constitue le syndrome méningé franc ?",
        options = listOf(
            "Céphalées intenses pulsatiles diffuses, vomissements faciles en jet sans nausées préalables, et raideur de nuque douloureuse",
            "Tremblement de repos lent, rigidité en tuyau de plomb et akinésie motrice",
            "Ataxie multidirectionnelle, dysmétrie à l'épreuve doigt-nez et hypotonie musculaire",
            "Diplopie horizontale, hémiplégie controlatérale et nystagmus rotatoire"
        ),
        correctOptionIndex = 0,
        explanation = "Le trépied méningé associe céphalées violentes et diffuses majorées par la lumière (photophobie) et le bruit (phonophobie), vomissements faciles en jet survenant sans effort ni nausée, et raideur méningée (résistance invincible et douloureuse à la flexion passive de la nuque).",
        wrongOptionsExplanations = listOf(
            "Tremblement de repos, rigidité et akinésie forment la triade du syndrome parkinsonien.",
            "Ataxie, dysmétrie et hypotonie caractérisent le syndrome cérébelleux statique et cinétique.",
            "La diplopie avec hémiplégie relève d'un syndrome alterne du tronc cérébral."
        ),
        category = SemiologyCategory.NEUROLOGICAL
    ),
    QuizQuestion(
        id = "qn_03",
        question = "Quelle différence clinique permet de distinguer avec certitude une paralysie faciale périphérique d'une paralysie faciale centrale ?",
        options = listOf(
            "La paralysie périphérique touche de façon égale la partie supérieure et inférieure du visage (signe de Charles Bell), tandis que la centrale prédomine très largement au territoire facial inférieur avec occlusion palpébrale conservée",
            "La paralysie centrale empêche complètement la fermeture de l'œil du côté atteint",
            "La paralysie périphérique n'entraîne jamais d'effacement du pli naso-génien ni de déviation de la commissure labiale",
            "La paralysie centrale s'accompagne systématiquement d'une agueusie des 2/3 antérieurs de la langue"
        ),
        correctOptionIndex = 0,
        explanation = "En raison de la double innervation corticale supranucléaire du noyau facial supérieur, la paralysie faciale centrale épargne les muscles du haut du visage : l'occlusion des paupières reste possible et le front peut se rider. Dans la paralysie périphérique (atteinte du nerf VII ou de son noyau), la paralysie est globale, homolatérale, avec impossibilité d'occlusion palpébrale (inocclusion) et signe de Charles Bell (déviation du globe oculaire en haut et en dehors lors de la tentative de fermeture).",
        wrongOptionsExplanations = listOf(
            "L'inocclusion palpébrale complète avec signe de Charles Bell est propre à la paralysie périphérique.",
            "La déviation de la bouche et l'effacement du sillon nasogénien existent dans les deux formes au niveau du bas du visage.",
            "L'atteinte du goût (agueusie des 2/3 antérieurs) peut survenir dans la paralysie périphérique par lésion proximale de la corde du tympan, non dans la centrale."
        ),
        category = SemiologyCategory.NEUROLOGICAL
    ),
    QuizQuestion(
        id = "qn_04",
        question = "Quelle caractéristique distingue l'hypertonie spastique pyramidale de l'hypertonie plastique extrapyramidale ?",
        options = listOf(
            "L'hypertonie spastique est élastique, vitesse-dépendante, prédominant sur les fléchisseurs aux membres supérieurs et extenseurs aux membres inférieurs, cédant en lame de canif",
            "L'hypertonie spastique est plastique, constante, homogène du début à la fin du mouvement avec phénomène de la roue dentée de Negro",
            "L'hypertonie pyramidale disparaît totalement lors du sommeil et n'est jamais accompagnée de réflexes vifs",
            "L'hypertonie extrapyramidale est prédominante sur un seul hémicorps avec signe de Babinski systématique"
        ),
        correctOptionIndex = 0,
        explanation = "La spasticité pyramidale est élastique : la résistance à l'étirement passif augmente avec la vitesse de mobilisation, puis cède brusquement (signe de la lame de canif) et le membre reprend sa position initiale. À l'opposé, l'hypertonie parkinsonienne est plastique ('en tuyau de plomb'), uniforme sur toute la course articulaire, cédant par à-coups successifs (phénomène de la roue dentée).",
        wrongOptionsExplanations = listOf(
            "La rigidité en tuyau de plomb et la roue dentée caractérisent l'atteinte extrapyramidale (parkinsonisme).",
            "L'hypertonie pyramidale s'accompagne obligatoirement d'hyperréflexie ostéo-tendineuse et de clonus.",
            "Le signe de Babinski est exclusif de l'atteinte de la voie pyramidale et n'existe pas dans le syndrome parkinsonien pur."
        ),
        category = SemiologyCategory.NEUROLOGICAL
    ),
    QuizQuestion(
        id = "qn_05",
        question = "Comment se caractérise le signe de Kernig chez un patient suspect de méningite ?",
        options = listOf(
            "Résistance douloureuse invincible empêchant l'extension passive complète de la jambe lorsque la cuisse est préalablement fléchie à angle droit sur le bassin",
            "Flexion involontaire réflexe des genoux et des hanches provoquée par la flexion passive antérieure de la nuque",
            "Chute du bras tendu vers le bas lors de l'occlusion des yeux",
            "Incapacité à réaliser des mouvements alternatifs rapides de prono-supination des mains"
        ),
        correctOptionIndex = 0,
        explanation = "Le signe de Kernig est positif lorsque, le patient étant en décubitus dorsal avec les cuisses fléchies à 90° sur l'abdomen, l'extension passive de la jambe sur la cuisse déclenche une douleur rachidienne aiguë et une résistance invincible obligeant à la flexion des genoux.",
        wrongOptionsExplanations = listOf(
            "La flexion réflexe des membres inférieurs lors de la flexion de la nuque est le signe de Brudzinski méningé.",
            "La chute du bras les yeux fermés teste le déficit moteur fruste (manœuvre de Barré).",
            "L'impossibilité de réaliser la prono-supination rapide définit l'adiadococinésie cérébelleuse (épreuve des marionnettes)."
        ),
        category = SemiologyCategory.NEUROLOGICAL
    ),
    QuizQuestion(
        id = "qn_06",
        question = "Quelle anomalie définit une adiadococinésie lors de l'examen neurologique ?",
        options = listOf(
            "L'impossibilité ou l'extrême lenteur et maladresse à exécuter des mouvements alternatifs rapides (épreuve des marionnettes)",
            "L'exagération de l'amplitude du mouvement dépassant le but visé lors de l'épreuve doigt-nez (dysmétrie)",
            "L'absence d'inhibition antagoniste provoquant le heurt de la main contre le thorax lors du lâcher contre résistance (signe de Stewart-Holmes)",
            "Une instabilité posturale statique non aggravée par la fermeture des yeux"
        ),
        correctOptionIndex = 0,
        explanation = "L'adiadococinésie est l'incapacité d'exécuter rapidement des mouvements alternatifs antagonistes successifs (comme visser une ampoule ou battre la paume puis le dos de la main sur la cuisse dans l'épreuve des marionnettes). C'est un élément cardinal du syndrome cérébelleux cinétique.",
        wrongOptionsExplanations = listOf(
            "Le dépassement du but visé définit l'hypermétrie ou dysmétrie cérébelleuse.",
            "L'absence de freinage avec heurt de la main au thorax est le signe du ressaut de Stewart-Holmes.",
            "L'instabilité statique les yeux ouverts comme fermés est l'ataxie cérébelleuse avec danse des tendons."
        ),
        category = SemiologyCategory.NEUROLOGICAL
    ),
    QuizQuestion(
        id = "qn_07",
        question = "Quelle constellation de signes cliniques définit le syndrome de la queue de cheval (urgence chirurgicale) ?",
        options = listOf(
            "Anesthésie en selle (périnée et organes génitaux), troubles sphinctériens (rétention ou incontinence urinaire/fécale), abolition du réflexe anal et déficits moteurs pluriradiculaires des membres inférieurs",
            "Hémiplégie brachio-faciale unilatérale avec aphasie de Broca motrice",
            "Névralgie cervico-brachiale C6 aiguë avec abolition du réflexe bicipital sans déficit moteur",
            "Paralysie faciale périphérique bilatérale avec conservation totale des réflexes sacrés"
        ),
        correctOptionIndex = 0,
        explanation = "Le syndrome de la queue de cheval résulte de la compression des racines nerveuses sacrées et lombaires sous le cône médullaire (L2). Il associe anesthésie en selle, troubles sphinctériens (mictionnels et défécatoires), béance et hypotonie du sphincter anal au toucher rectal, abolition du réflexe bulbo-caverneux et clitorido-sphinctérien, et sciatiques bilatérales avec paralysies des releveurs ou du triceps sural.",
        wrongOptionsExplanations = listOf(
            "L'hémiplégie avec aphasie traduit un accident vasculaire cérébral sylvien superficiel gauche.",
            "La névralgie C6 isolée concerne le plexus brachial cervical supérieur.",
            "La dipégie faciale périphérique s'observe dans le syndrome de Guillain-Barré ou la neuroborréliose."
        ),
        category = SemiologyCategory.NEUROLOGICAL
    ),
    QuizQuestion(
        id = "qn_08",
        question = "Dans l'évaluation du score de Glasgow (GCS), quel est le score d'un patient qui ouvre les yeux à la demande verbale, formule des propos confus et localise la douleur à la stimulation ?",
        options = listOf(
            "GCS 12 (Y3, V4, M5)",
            "GCS 15 (Y4, V5, M6)",
            "GCS 9 (Y2, V2, M5)",
            "GCS 7 (Y1, V2, M4)"
        ),
        correctOptionIndex = 0,
        explanation = "Détail du score : Ouverture des yeux à la demande = 3 points (Y3). Réponse verbale confuse/désorientée = 4 points (V4). Réponse motrice adaptée localisant la douleur = 5 points (M5). Total = 3 + 4 + 5 = 12/15.",
        wrongOptionsExplanations = listOf(
            "15 est le score normal d'un patient parfaitement vigile, orienté et obéissant aux ordres simples.",
            "9 supposerait une ouverture des yeux à la douleur (2) et des sons incompréhensibles (2).",
            "7 correspond à un état de coma lourd (score < 8 imposant l'intubation trachéale)."
        ),
        category = SemiologyCategory.NEUROLOGICAL
    ),
    QuizQuestion(
        id = "qn_09",
        question = "Quelle atteinte nerveuse périphérique entraîne un steppage unilatéral de la marche ?",
        options = listOf(
            "Paralysie du nerf fibulaire commun (nerf sciatique poplité externe - SPE) avec déficit de la loge antéro-latérale de jambe",
            "Paralysie du nerf tibial (nerf sciatique poplité interne - SPI) avec paralysie de la flexion plantaire",
            "Paralysie isolée du nerf fémoral (crural) avec abolition du réflexe rotulien",
            "Atteinte du nerf obturateur avec faiblesse des adducteurs de la cuisse"
        ),
        correctOptionIndex = 0,
        explanation = "L'atteinte du nerf fibulaire commun (L4-L5) paralyse les muscles releveurs du pied et des orteils (tibial antérieur, long extenseur des orteils et fibulaires). Le pied tombant oblige le malade à lever exagérément le genou pour que la pointe du pied ne bute pas au sol, puis à laisser retomber le pied à plat ou sur la pointe (marche en steppage).",
        wrongOptionsExplanations = listOf(
            "L'atteinte du nerf tibial empêche la marche sur la pointe des pieds et abolit le réflexe achilléen.",
            "L'atteinte du nerf fémoral empêche le verrouillage du genou et provoque un dérobement de la jambe.",
            "L'atteinte du nerf obturateur gêne le croisement des jambes sans provoquer de steppage."
        ),
        category = SemiologyCategory.NEUROLOGICAL
    ),
    QuizQuestion(
        id = "qn_10",
        question = "Quel signe oculaire signe une paralysie complète du nerf moteur oculaire commun (III) ?",
        options = listOf(
            "Ptosis total, strabisme divergent homolatéral et mydriase aréactive",
            "Énophtalmie, myosis serré et rétrécissement de la fente palpébrale (syndrome de Claude Bernard-Horner)",
            "Strabisme convergent isolé sans anomalie pupillaire",
            "Incapacité à regarder en bas et en dedans lors de la descente des escaliers"
        ),
        correctOptionIndex = 0,
        explanation = "Le nerf oculo-moteur (III) innerve le releveur de la paupière supérieure, les muscles droit médial, droit supérieur, droit inférieur et petit oblique, ainsi que le sphincter irien parasympathique. Sa lésion complète associe donc ptosis complet, œil fixé en bas et en dehors (strabisme divergent dû à l'action non contrariée du VI et du IV) et mydriase paralytique unilatérale aréactive.",
        wrongOptionsExplanations = listOf(
            "Ptosis, myosis et énophtalmie forment le syndrome de Claude Bernard-Horner par lésion du système sympathique cervical.",
            "Le strabisme convergent isolé signe l'atteinte du nerf abducens (VI).",
            "La gêne au regard en bas et en dedans signe la paralysie du nerf trochléaire (IV)."
        ),
        category = SemiologyCategory.NEUROLOGICAL
    )
)
