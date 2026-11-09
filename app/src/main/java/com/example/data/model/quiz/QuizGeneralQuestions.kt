package com.example.data.model.quiz

import com.example.data.model.QuizQuestion
import com.example.data.model.SemiologyCategory

val quizGeneralQuestions: List<QuizQuestion> = listOf(
    QuizQuestion(
        id = "qg_01",
        question = "Quels sont les 3 symptômes cardinaux composant la triade de l'Altération de l'État Général (AEG) ?",
        options = listOf(
            "Asthénie, Anorexie et Amaigrissement (les '3A')",
            "Anxiété, Agitation et Aphasie",
            "Arthralgies, Alopécie et Anasarque",
            "Apyrexie, Anémie et Amnésie"
        ),
        correctOptionIndex = 0,
        explanation = "La triade sémiologique des 3A regroupe l'Asthénie (fatigue anormale persistante non résolue par le repos), l'Anorexie (perte de l'appétit) et l'Amaigrissement (perte de poids involontaire documentée). C'est un signal d'alarme clinique fondamental qui impose la recherche d'une néoplasie, d'une infection torpide ou d'une maladie systémique.",
        wrongOptionsExplanations = listOf(
            "Anxiété, agitation et aphasie sont des symptômes neuropsychiatriques, non les composantes de l'AEG.",
            "Arthralgies, alopécie et anasarque sont des signes de maladies auto-immunes ou néphrologiques spécifiques.",
            "Apyrexie, anémie et amnésie ne définissent pas le syndrome d'AEG."
        ),
        category = SemiologyCategory.GENERAL
    ),
    QuizQuestion(
        id = "qg_02",
        question = "Quel signe physique est spécifique de la déshydratation intracellulaire (DIC) ?",
        options = listOf(
            "Sécheresse de la face interne des joues et de la muqueuse jugale avec soif vive inextinguible",
            "Pli cutané persistant sous la clavicule après pincement de la peau",
            "Hypotension artérielle orthostatique avec aplatissement des veines jugulaires",
            "Yeux cernés et enfoncés dans les orbites avec tachycardie de repos"
        ),
        correctOptionIndex = 0,
        explanation = "La déshydratation intracellulaire traduit une hyperosmolarité plasmatique (hypernatrémie > 145 mmol/L) entraînant une sortie d'eau hors des cellules. Ses signes cardinaux sont la soif intense, la sécheresse des muqueuses (face interne des joues, langue 'rôtie'), l'hypotonie des globes oculaires et les troubles de vigilance (somnolence, confusion, fièvre centrale).",
        wrongOptionsExplanations = listOf(
            "Le pli cutané persistant signe la déshydratation extracellulaire (perte du volume interstitiel).",
            "L'hypotension orthostatique et les veines jugulaires plates signent l'hypovolémie de la déshydratation extracellulaire.",
            "Les yeux cernés et la tachycardie reflètent le déficit volumique extracellulaire."
        ),
        category = SemiologyCategory.GENERAL
    ),
    QuizQuestion(
        id = "qg_03",
        question = "Quelle constatation clinique permet d'affirmer un pli cutané pathologique de déshydratation extracellulaire (DEC) ?",
        options = listOf(
            "Le pli cutané formé par le pincement doux de la peau sous la clavicule ou à la face antérieure de la cuisse persiste plusieurs secondes après le relâchement",
            "La peau redevient plane instantanément en moins d'une fraction de seconde",
            "Le pincement cutané laisse une ecchymose bleuâtre sous-cutanée douloureuse",
            "La zone pincée présente un œdème prenant le godet de façon bilatérale"
        ),
        correctOptionIndex = 0,
        explanation = "Dans la déshydratation extracellulaire, la perte du liquide interstitiel diminue la turgescence tissulaire élastique de la peau : un pli formé entre le pouce et l'index (recherché électivement dans les régions pauvres en graisse sous-cutanée comme la région sous-claviculaire ou la cuisse) met plusieurs secondes à s'effacer (persistance du pli cutané).",
        wrongOptionsExplanations = listOf(
            "Le retour instantané à l'état plat est le comportement d'une peau normale bien hydratée.",
            "Une ecchymose signe une fragilité vasculaire capillaire sans rapport avec la déshydratation.",
            "L'œdème prenant le godet est au contraire le signe d'une surcharge hydrosodée du secteur extracellulaire (inflation interstitielle)."
        ),
        category = SemiologyCategory.GENERAL
    ),
    QuizQuestion(
        id = "qg_04",
        question = "Quels sont les signes cliniques généraux d'un syndrome anémique d'installation progressive ?",
        options = listOf(
            "Pâleur cutanéo-muqueuse (conjonctives palpébrales, paumes des mains), asthénie, dyspnée d'effort, tachycardie compensatrice et souffle systolique fonctionnel éjectionnel",
            "Cyanose péribuccale avec bradypnée expiratoire et marbrures des genoux",
            "Rougeur faciale érythrosique avec céphalées battantes et prurit à l'eau",
            "Ictère franc avec hépatomégalie nodulaire et ascite hémorragique"
        ),
        correctOptionIndex = 0,
        explanation = "La baisse du taux d'hémoglobine diminue le transport d'oxygène aux tissus et la coloration des capillaires superficiels : cela engendre pâleur conjonctivale et linguale, asthénie, dyspnée d'effort, céphalées, vertiges. Pour compenser l'hypoxie tissulaire, le cœur augmente son débit (tachycardie, souffle systolique anorganique d'hyperdébit au foyer pulmonaire/aortique).",
        wrongOptionsExplanations = listOf(
            "La cyanose traduit une désaturation en O2 de l'hémoglobine présente (taux d'Hb réduite > 5 g/dL), très difficile à voir chez l'anémique sévère !",
            "L'érythrose faciale avec prurit à l'eau (aquagénique) est le tableau inverse de la polyglobulie (maladie de Vaquez).",
            "L'ictère avec hépatomégalie oriente vers une hépatopathie chronique ou une hémolyse aiguë."
        ),
        category = SemiologyCategory.GENERAL
    ),
    QuizQuestion(
        id = "qg_05",
        question = "Quels sont les 3 critères du score de dépistage rapide du sepsis grave 'qSOFA' (quick SOFA) ?",
        options = listOf(
            "Fréquence respiratoire >= 22/min, Altération de la conscience (Glasgow < 15), et Pression artérielle systolique <= 100 mmHg",
            "Température > 38.5°C, Leucocytes > 12 000/mm3, et Fréquence cardiaque > 90 bpm",
            "Diurèse < 500 mL/24h, Ictère cutané, et Thrombopénie < 100 000/mm3",
            "Glycémie > 2 g/L, Lactates > 4 mmol/L, et Saturation O2 < 90%"
        ),
        correctOptionIndex = 0,
        explanation = "Le score qSOFA (Sepsis-3) permet d'identifier au lit du malade les patients suspects d'infection à haut risque d'évolution péjorative avec 3 critères simples (1 point chacun, suspect dès >= 2 points) : 1. Polypnée avec fréquence respiratoire >= 22 /min, 2. Altération de l'état mental (GCS < 15), 3. Hypotension avec PAS <= 100 mmHg.",
        wrongOptionsExplanations = listOf(
            "Température, leucocytes et tachycardie étaient les anciens critères du SRIS (SIRS), abandonnés car trop peu spécifiques.",
            "Diurèse et thrombopénie font partie du score SOFA complet réanimatoire nécessitant des examens de laboratoire.",
            "Glycémie et lactates ne font pas partie du score clinique qSOFA au lit du malade."
        ),
        category = SemiologyCategory.GENERAL
    ),
    QuizQuestion(
        id = "qg_06",
        question = "Quelle caractéristique clinique oriente vers une adénopathie d'allure maligne (cancéreuse ou métastatique) ?",
        options = listOf(
            "Ganglion dur comme de la pierre, indolore, fixé aux plans profonds et sous-cutanés, mesurant plus de 2-3 cm",
            "Ganglion mou, très douloureux spontanément, mobile, entouré d'une peau rouge et chaude",
            "Petits ganglions infra-centimétriques souples et mobiles bilatéraux dans les régions inguinales",
            "Ganglion fluctuant avec fistulisation cutanée laissant sourdre du pus jaunâtre"
        ),
        correctOptionIndex = 0,
        explanation = "Une adénopathie maligne (ganglion métastatique ou lymphome) se caractérise sémiologiquement par une consistance très ferme à pierreuse, une indolence complète, une absence de signes inflammatoires cutanés locaux, une fixation aux plans adjacents profonds et superficiels, et une taille significative (> 2 cm).",
        wrongOptionsExplanations = listOf(
            "Le ganglion mou, douloureux, mobile et chaud correspond à une adénite aiguë bactérienne réactionnelle infectieuse.",
            "Les micropolyadénopathies inguinales souples de moins de 1 cm sont banales et physiologiques chez l'adulte.",
            "L'adénite fistulisée purulente évoque une tuberculose ganglionnaire (écrouelles/scrofuloderme) ou une bartonellose (griffes du chat)."
        ),
        category = SemiologyCategory.GENERAL
    ),
    QuizQuestion(
        id = "qg_07",
        question = "Selon les critères de la Haute Autorité de Santé (HAS), quel critère quantitatif permet d'affirmer une dénutrition chez l'adulte de moins de 70 ans ?",
        options = listOf(
            "Perte de poids involontaire >= 5% en 1 mois ou >= 10% en 6 mois (ou Indice de Masse Corporelle IMC < 18.5 kg/m²)",
            "Perte de poids de 1% sur une durée de trois ans sans modification alimentaire",
            "Indice de Masse Corporelle compris entre 20 et 24.9 kg/m²",
            "Diminution isolée de l'apport hydrique quotidien en période de forte chaleur"
        ),
        correctOptionIndex = 0,
        explanation = "Le diagnostic phénotypique de dénutrition selon l'HAS repose sur au moins un des critères suivants : Perte de poids involontaire >= 5% en 1 mois ou >= 10% en 6 mois (ou >= 10% par rapport au poids habituel d'avant la maladie), ou IMC < 18.5 kg/m² chez l'adulte < 70 ans (et < 21 kg/m² chez le sujet de 70 ans et plus).",
        wrongOptionsExplanations = listOf(
            "Une perte de 1% sur trois ans n'a pas de signification pathologique.",
            "Un IMC entre 20 et 24.9 kg/m² est la zone de corpulence normale.",
            "La baisse hydrique concerne l'hydratation, non la dénutrition protéino-énergétique."
        ),
        category = SemiologyCategory.GENERAL
    ),
    QuizQuestion(
        id = "qg_08",
        question = "Dans la recherche de foyers infectieux devant un syndrome fébrile aigu inexpliqué, quelle auscultation systématique ne doit jamais être oubliée ?",
        options = listOf(
            "Auscultation des foyers cardiaques à la recherche d'un souffle nouveau de régurgitation (endocardite infectieuse)",
            "Auscultation des artères fémorales à la recherche d'un anévrisme mycotique géant",
            "Auscultation de la thyroïde à la recherche d'un thrill vasculaire",
            "Auscultation de la région lombo-sacrée à la recherche d'un souffle vertébral"
        ),
        correctOptionIndex = 0,
        explanation = "Toute fièvre inexpliquée, a fortiori avec altération de l'état général ou bactériémie, impose l'auscultation rigoureuse et répétée des foyers cardiaques pour dépister l'apparition d'un souffle de régurgitation (insuffisance aortique ou mitrale) ou la modification d'un souffle préexistant, signant une endocardite infectieuse d'Osler.",
        wrongOptionsExplanations = listOf(
            "L'auscultation fémorale est utile pour l'artériopathie mais rare cause de fièvre isolée.",
            "Le thrill thyroïdien accompagne la maladie de Basedow thyrotoxique sans fièvre bactérienne.",
            "Il n'y a pas de souffle vertébral à l'auscultation normale."
        ),
        category = SemiologyCategory.GENERAL
    ),
    QuizQuestion(
        id = "qg_09",
        question = "Comment définit-on sémiologiquement une fièvre hectique ?",
        options = listOf(
            "De très grandes oscillations thermiques quotidiennes avec des pointes vespérales élevées séparées par des rémissions complètes au cours de la même journée, accompagnées de sueurs profuses",
            "Une température rigoureusement constante en plateau à 40°C pendant plusieurs semaines",
            "Une alternance de 3 jours fébriles et 3 jours sans aucune fièvre de façon rythmée (fièvre tierce ou quarte)",
            "Une élévation thermique modérée matinale avec hypothermie vespérale"
        ),
        correctOptionIndex = 0,
        explanation = "La fièvre hectique (ou désarticulée) se traduit par des écarts thermiques majeurs au cours du nycthémère (ex: passage de 37°C le matin à 40°C le soir avec frissons intenses et sueurs d'inondation lors de la défervescence). Elle est très évocatrice d'une suppuration profonde collectée (abcès hépatique, sous-phrénique, pyonéphrose, empyème).",
        wrongOptionsExplanations = listOf(
            "La température constante en plateau est typique du deuxième septénaire de la fièvre typhoïde non traitée.",
            "L'accès intermittent tierce ou quarte tous les 2 ou 3 jours est la marque du paludisme (Plasmodium vivax/malariae).",
            "L'hypothermie vespérale n'est pas une fièvre hectique."
        ),
        category = SemiologyCategory.GENERAL
    ),
    QuizQuestion(
        id = "qg_10",
        question = "La découverte d'un ganglion de Troisier (adénopathie supraclaviculaire gauche) à l'examen clinique oriente classiquement vers :",
        options = listOf(
            "Une métastase ganglionnaire d'un cancer digestif sous-diaphragmatique (estomac, côlon, pancréas) ou gynécologique/urologique via le canal thoracique",
            "Une infection dentaire banale de la mandibule gauche",
            "Une pneumonie aiguë lobaire franche du lobe supérieur gauche",
            "Une arthrose acromio-claviculaire gauche évoluée"
        ),
        correctOptionIndex = 0,
        explanation = "Le ganglion de Troisier (ou de Virchow) est une adénopathie située dans le creux supraclaviculaire gauche, à l'abouchement du canal thoracique dans la veine sous-clavière gauche. Sa présence témoigne d'un envahissement métastatique par voie lymphatique rétrograde d'une néoplasie maligne sous-diaphragmatique, au premier rang desquelles l'adénocarcinome gastrique, œsophagien ou pancréatique.",
        wrongOptionsExplanations = listOf(
            "Une infection dentaire donne des adénopathies sous-mandibulaires ou jugulo-carotidiennes, non sus-claviculaires.",
            "Une pneumonie ne donne pas d'adénopathie sus-claviculaire gauche isolée dure.",
            "L'arthrose acromio-claviculaire est une atteinte ostéo-articulaire sans adénomégalie."
        ),
        category = SemiologyCategory.GENERAL
    )
)
