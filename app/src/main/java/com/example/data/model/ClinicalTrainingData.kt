package com.example.data.model

data class AnamnesisSkill(
  val id: Int,
  val title: String,
  val subtitle: String,
  val explanation: String,
  val professionalPhrasings: List<String>,
  val errorsToAvoid: List<String>,
  val patientResponsesExamples: List<Pair<String, String>>, // (Réponse possible du patient, Question de relance recommandée)
  val followUpQuestions: List<String>,
  val practicalExerciseQuestion: String,
  val practicalExerciseChoices: List<String>,
  val correctChoiceIndex: Int,
  val practicalExerciseCorrection: String
)

data class InfoCategoryGuide(
  val categoryName: String,
  val description: String,
  val whatToInclude: List<String>,
  val whatToExclude: String,
  val hospitalExample: String
)

data class ClassificationExercise(
  val rawPatientQuote: String,
  val options: List<String>,
  val correctCategory: String,
  val explanation: String
)

data class ObservationMethodSkill(
  val title: String,
  val objective: String,
  val detailedGuide: String,
  val keyPoints: List<String>,
  val badExample: String,
  val goodExample: String,
  val exercisePrompt: String,
  val exerciseCorrection: String
)

data class SynthesisCaseTraining(
  val id: String,
  val title: String,
  val patientProfile: String,
  val symptomsCollected: List<String>,
  val physicalSignsFound: List<String>,
  val clinicalReasoningPath: String,
  val primaryHypothesis: String,
  val emergencyToRuleOut: String,
  val immediateActionPlan: List<String>
)

object ClinicalTrainingDataProvider {

  // ══════════════════════════════════════════════════════════════════════════
  // A. INTERROGATOIRE MÉDICAL — 14 MODULES D'APPRENTISSAGE
  // ══════════════════════════════════════════════════════════════════════════
  val anamnesisSkills: List<AnamnesisSkill> = listOf(
    AnamnesisSkill(
      id = 1,
      title = "1. Accueillir correctement un patient",
      subtitle = "Posture, politesse, cadre d'intimité et mise à l'aise",
      explanation = "L'accueil pose les fondations de l'alliance thérapeutique. Il doit garantir la confidentialité, le respect de la dignité et réduire l'anxiété inhérente au milieu hospitalier.",
      professionalPhrasings = listOf(
        "« Bonjour Monsieur/Madame [Nom]. Je vous en prie, installez-vous confortablement. »",
        "« Avez-vous besoin d'aide pour vous asseoir ou relever le dossier du lit ? »",
        "« Nous allons prendre un moment ensemble au calme pour échanger sur ce qui vous amène. »"
      ),
      errorsToAvoid = listOf(
        "Appeler le patient uniquement par son numéro de chambre ou son organe (« le foie du 12 »).",
        "Laisser la porte de la chambre ouverte ou les rideaux tirés pendant l'entretien.",
        "Commencer à écrire sur le dossier ou l'ordinateur sans regarder le patient dans les yeux."
      ),
      patientResponsesExamples = listOf(
        "« Je suis un peu angoissé d'être ici, il y a trop de monde qui passe. »" to "« C'est parfaitement compréhensible. Je ferme la porte pour que nous puissions discuter en toute tranquillité. »",
        "« J'ai très froid dans cette chambre. »" to "« Je vous apporte immédiatement une couverture supplémentaire avant que nous commencions. »"
      ),
      followUpQuestions = listOf(
        "« Êtes-vous bien installé pour que nous puissions discuter ? »",
        "« Souhaitez-vous que vos proches restent avec nous ou préfèrent-ils patienter quelques minutes ? »"
      ),
      practicalExerciseQuestion = "Quel est le comportement d'accueil le plus approprié lors de l'entrée dans la chambre d'un patient hospitalisé ?",
      practicalExerciseChoices = listOf(
        "Entrer en regardant l'écran de la tablette et dire : « Bonjour, je viens pour l'observation clinique. »",
        "Frapper, entrer, saluer le patient par son nom, se positionner à sa hauteur, fermer la porte pour préserver l'intimité.",
        "Demander d'emblée : « Pourquoi êtes-vous hospitalisé ? » sans décliner son identité.",
        "Parler fort depuis le couloir pour vérifier si le patient est réveillé."
      ),
      correctChoiceIndex = 1,
      practicalExerciseCorrection = "Frapper, saluer par son nom, se placer à sa hauteur et préserver l'intimité sont les composantes essentielles d'un accueil respectueux et rassurant."
    ),

    AnamnesisSkill(
      id = 2,
      title = "2. Se présenter et établir un climat de confiance",
      subtitle = "Statut clair, fonction dans l'équipe et objectif de l'entretien",
      explanation = "L'étudiant en médecine doit expliciter clairement son statut d'externe/étudiant tout en rassurant le patient sur le travail coordonné avec le médecin senior ou le chef de clinique.",
      professionalPhrasings = listOf(
        "« Bonjour, je m'appelle [Prénom Nom], je suis étudiant en médecine dans le service. »",
        "« Je viens réaliser votre première observation clinique afin de recueillir l'ensemble de votre histoire. »",
        "« Je ferai ensuite le point complet avec le Dr [Nom du médecin référent] pour votre prise en charge. »"
      ),
      errorsToAvoid = listOf(
        "Se faire passer pour le médecin titulaire ou entretenir l'ambiguïté sur son statut d'étudiant.",
        "Dire : « Je suis juste un stagiaire, ça ne sert à rien ce que je fais. » (décrédibilise le soin).",
        "Promettre des résultats d'examens ou une heure de sortie sans accord médical préalable."
      ),
      patientResponsesExamples = listOf(
        "« Mais vous êtes médecin ou vous apprenez sur moi ? »" to "« Je suis étudiant en médecine en fin de cursus. Mon rôle est de préparer minutieusement votre dossier avec vous avant que le médecin responsable vienne valider l'examen. »",
        "« J'ai déjà tout dit aux urgences hier soir ! »" to "« Je comprends que cela soit répétitif et fatigant. Cela nous permet de vérifier chaque détail et de nous assurer que rien n'a évolué depuis votre admission. »"
      ),
      followUpQuestions = listOf(
        "« Est-ce que cela vous convient que nous prenions 20 minutes ensemble ? »",
        "« Avez-vous des questions sur le déroulement de notre entretien ? »"
      ),
      practicalExerciseQuestion = "Face à un patient réticent disant « J'ai déjà tout raconté aux urgences », que doit répondre l'étudiant ?",
      practicalExerciseChoices = listOf(
        "« C'est le protocole, vous devez recommencer avec moi. »",
        "« Valider sa fatigue, expliquer avec empathie l'importance de vérifier l'évolution des symptômes et la complémentarité de l'observation d'étage. »",
        "« Très bien, je recopierai juste la feuille des urgences sans vous examiner. »",
        "« Si vous refusez, vous ne verrez pas le médecin senior aujourd'hui. »"
      ),
      correctChoiceIndex = 1,
      practicalExerciseCorrection = "Faire preuve d'empathie face à la lassitude du patient tout en expliquant la valeur ajoutée d'un réexamen d'étage permet de désamorcer les tensions et restaurer l'adhésion."
    ),

    AnamnesisSkill(
      id = 3,
      title = "3. Identifier le motif de consultation",
      subtitle = "Le symptôme cardinal qui a déclenché le recours aux soins",
      explanation = "Le motif de consultation est le maître-symptôme ou l'événement déclencheur qui a poussé le patient à consulter. Il doit être formulé en termes médicaux précis sans porter de diagnostic prématuré.",
      professionalPhrasings = listOf(
        "« Qu'est-ce qui vous a amené à consulter ou à appeler les secours aujourd'hui ? »",
        "« Quel est le problème ou la gêne qui vous préoccupe le plus en ce moment ? »",
        "« Qu'est-ce qui a changé dans votre état pour vous faire venir à l'hôpital ? »"
      ),
      errorsToAvoid = listOf(
        "Inscrire un diagnostic au lieu du motif (ex: noter « Infarctus du myocarde » au lieu de « Douleur thoracique rétro-sternale »).",
        "Se noyer dans les 10 plaintes chroniques sans identifier l'événement aigu déclencheur.",
        "Prendre pour motif la prescription d'un confrère sans interroger le symptôme réel."
      ),
      patientResponsesExamples = listOf(
        "« C'est mon médecin traitant qui m'a dit de venir d'urgence ! »" to "« D'accord, et pour quelle raison précise vous a-t-il examiné ? Quel symptôme lui avez-vous décrit ? »",
        "« J'ai mal partout, je n'en peux plus depuis des mois. »" to "« Je comprends votre souffrance globale. Mais qu'est-ce qui s'est aggravé particulièrement ces dernières 24 heures pour nécessiter votre hospitalisation ? »"
      ),
      followUpQuestions = listOf(
        "« Si vous deviez retenir la gêne principale qui a tout déclenché, quelle serait-elle ? »",
        "« Était-ce une douleur, un essoufflement, une fièvre ou autre chose ? »"
      ),
      practicalExerciseQuestion = "Lequel des éléments suivants constitue un motif de consultation correctement rédigé ?",
      practicalExerciseChoices = listOf(
        "« Pneumopathie franche lobaire aiguë droite bactérienne »",
        "« Dyspnée aiguë fébrile avec toux purulente évoluant depuis 48 heures »",
        "« Patient envoyé par le Dr Martin pour bilan »",
        "« Syndrome coronarien aigu avec sus-décalage de ST »"
      ),
      correctChoiceIndex = 1,
      practicalExerciseCorrection = "Le motif doit décrire le maître-symptôme sémiologique (dyspnée aiguë fébrile) et sa temporalité, et non un diagnostic nosologique présumé."
    ),

    AnamnesisSkill(
      id = 4,
      title = "4. Poser des questions ouvertes",
      subtitle = "Laisser le patient raconter librement sa plainte initiale",
      explanation = "Les questions ouvertes permettent au patient de décrire avec ses propres mots la survenue du problème sans être influencé. Elles fournissent souvent 80% des informations diagnostiques utiles.",
      professionalPhrasings = listOf(
        "« Racontez-moi comment tout cela a commencé... »",
        "« Que s'est-il passé depuis le début de votre douleur ? »",
        "« Comment décririez-vous ce que vous ressentez exactement ? »"
      ),
      errorsToAvoid = listOf(
        "Interrompre le patient dès les 15 premières secondes de parole.",
        "Commencer directement par un interrogatoire policier de questions fermées (« oui/non »).",
        "Orienter d'emblée la réponse (« Vous avez mal dans le bras gauche, n'est-ce pas ? »)."
      ),
      patientResponsesExamples = listOf(
        "« Ça a commencé mardi soir devant la télé, j'ai senti comme une chape de plomb sur la poitrine... »" to "« Continuez, je vous écoute... Que s'est-il passé ensuite ? »",
        "« J'ai eu une drôle de sensation dans le ventre, puis j'ai vomi. »" to "« Décrivez-moi un peu plus cette sensation dans le ventre. »"
      ),
      followUpQuestions = listOf(
        "« Et ensuite, comment cela a-t-il évolué ? »",
        "« Y a-t-il d'autres choses que vous avez remarquées à ce moment-là ? »"
      ),
      practicalExerciseQuestion = "Quelle formulation représente une question ouverte idéale en début d'histoire de la maladie ?",
      practicalExerciseChoices = listOf(
        "« Avez-vous eu mal au thorax vers 18 heures mardi ? »",
        "« Racontez-moi comment sont apparus vos symptômes et comment la journée s'est déroulée. »",
        "« C'est une douleur constrictive qui serre ou qui pique ? »",
        "« Avez-vous de la fièvre à 39°C depuis ce matin ? »"
      ),
      correctChoiceIndex = 1,
      practicalExerciseCorrection = "Une question ouverte ne suggère aucune réponse et invite le patient à dérouler la chronologie spontanée de son expérience."
    ),

    AnamnesisSkill(
      id = 5,
      title = "5. Poser des questions fermées",
      subtitle = "Préciser des éléments sémiologiques factuels et exhaustifs",
      explanation = "Les questions fermées (auxquelles on répond par oui, non, une valeur ou un choix binaire) interviennent dans un second temps pour verrouiller des critères diagnostiques précis et des signes d'alerte.",
      professionalPhrasings = listOf(
        "« La douleur irradie-t-elle dans votre mâchoire ou votre bras gauche ? (Oui / Non) »",
        "« Avez-vous mesuré votre température avec un thermomètre ? Si oui, combien affichait-il ? »",
        "« Avez-vous vomi du sang ? (Oui / Non) »"
      ),
      errorsToAvoid = listOf(
        "Utiliser des questions fermées dès l'introduction (bloque le récit spontané du patient).",
        "Poser plusieurs questions fermées enchaînées d'une traite sans laisser le temps de répondre.",
        "Oublier de vérifier les signes négatifs pertinents (« Pas de fièvre ? Pas d'essoufflement ? »)."
      ),
      patientResponsesExamples = listOf(
        "« Oui, ça me tirait jusque dans les dents. »" to "« C'est une information très importante. Est-ce que cette douleur dans les dents a duré plus de 20 minutes ? »",
        "« Non, aucune fièvre, j'étais plutôt frissonnant. »" to "« Avez-vous eu des claquements de dents pendant ces frissons ? »"
      ),
      followUpQuestions = listOf(
        "« Est-ce que la douleur augmente lorsque vous appuyez sur votre ventre ? »",
        "« Avez-vous pris de l'aspirine ou un anti-inflammatoire ce matin ? »"
      ),
      practicalExerciseQuestion = "Quel est le rôle principal des questions fermées dans l'interrogatoire ?",
      practicalExerciseChoices = listOf(
        "Établir le premier contact et rassurer le patient sur le plan émotionnel.",
        "Valider des critères sémiologiques précis, chiffrer des paramètres et vérifier les signes négatifs essentiels.",
        "Remplacer complètement les questions ouvertes pour aller plus vite en garde.",
        "Demander au patient de poser son propre diagnostic."
      ),
      correctChoiceIndex = 1,
      practicalExerciseCorrection = "Les questions fermées ciblent des points spécifiques, mesurables et éliminent méthodiquement les diagnostics différentiels."
    ),

    AnamnesisSkill(
      id = 6,
      title = "6. Utiliser des questions de précision et de relance",
      subtitle = "Clarifier les termes flous et approfondir les caractéristiques",
      explanation = "Les patients emploient couramment des termes imprécis (« vertige », « malaise », « crise de foie », « brûlure »). Le soignant doit clarifier la réalité clinique sous-jacente sans brusquer.",
      professionalPhrasings = listOf(
        "« Quand vous dites 'vertige', est-ce que la pièce tournait autour de vous, ou s'agissait-il d'une sensation de voile noir ? »",
        "« Vous me dites que la douleur est 'forte'. Si vous deviez lui donner une note de 0 à 10, 10 étant insupportable, à combien l'estimeriez-vous ? »",
        "« Vous mentionnez 'souvent' : est-ce plusieurs fois par jour, par semaine ou par mois ? »"
      ),
      errorsToAvoid = listOf(
        "Accepter un terme du langage populaire sans demander ce que le patient ressent physiquement.",
        "Laisser subsister un flou sur la durée exacte d'un malaise ou d'une perte de connaissance.",
        "Ne pas faire préciser le siège exact de la douleur avec le doigt."
      ),
      patientResponsesExamples = listOf(
        "« J'ai fait une crise de foie après le repas de dimanche. »" to "« Qu'avez-vous ressenti exactement : une douleur sous les côtes à droite, des nausées, des brûlures d'estomac ? Montrez-moi avec votre main. »",
        "« Je suis tombé dans les pommes au marché. »" to "« Vous souvenez-vous d'avoir vu le sol arriver, ou vous êtes-vous réveillé par terre sans vous souvenir de la chute ? »"
      ),
      followUpQuestions = listOf(
        "« Pouvez-vous me montrer du doigt l'endroit exact où la douleur a commencé ? »",
        "« Combien de temps cela a-t-il duré : quelques secondes, plusieurs minutes ou plusieurs heures ? »"
      ),
      practicalExerciseQuestion = "Un patient déclare : « Docteur, j'ai tout le temps des vertiges le matin ». Quelle est la relance la plus rigoureuse ?",
      practicalExerciseChoices = listOf(
        "« C'est sûrement un problème de cristaux dans l'oreille interne. »",
        "« Quand vous avez cette sensation, est-ce que les murs tournent comme sur un manège, ou est-ce une tête vide quand vous vous levez du lit ? »",
        "« Avez-vous pris votre bétahistine ce matin ? »",
        "« Les vertiges ne sont pas graves, décrivez-moi plutôt votre toux. »"
      ),
      correctChoiceIndex = 1,
      practicalExerciseCorrection = "Distinguer un vrai vertige rotatoire vestibulaire d'une hypotension orthostatique ou d'un malaise lipothymique est la première étape indispensable."
    ),

    AnamnesisSkill(
      id = 7,
      title = "7. Obtenir des réponses précises sans influencer le patient",
      subtitle = "Neutralité bienveillante, questions non suggestives",
      explanation = "Poser des questions suggestives fausse le diagnostic en incitant le patient à confirmer ce que le médecin s'attend à entendre. L'interrogatoire doit demeurer neutre.",
      professionalPhrasings = listOf(
        "« Comment qualifieriez-vous le type de douleur : comme un serrement, une brûlure, un coup de poignard, ou autre chose ? »",
        "« À quel moment de la journée la toux se manifeste-t-elle principalement ? »",
        "« Qu'est-ce qui augmente ou au contraire soulage cette douleur ? »"
      ),
      errorsToAvoid = listOf(
        "Demander : « La douleur vous serre la poitrine, n'est-ce pas ? » (question hautement suggestive).",
        "Suggérer : « Vous n'avez pas de sang dans les selles, je suppose ? » (pousse le patient à répondre 'non').",
        "Haucher la tête avant même que le patient ait formulé sa réponse."
      ),
      patientResponsesExamples = listOf(
        "« Ça ne me brûle pas vraiment... c'est bizarre comme sensation. »" to "« Prenez votre temps, utilisez vos propres mots ou une image pour m'expliquer ce que vous ressentez. »",
        "« Vous pensez que c'est le cœur ? »" to "« Nous allons explorer toutes les pistes avec rigueur sans préjuger. Pour le moment, dites-moi si cette douleur se modifie lorsque vous respirez profondément. »"
      ),
      followUpQuestions = listOf(
        "« Y a-t-il une position dans laquelle vous vous sentez plus soulagé ? »",
        "« Est-ce continu ou cela vient-il par vagues successives ? »"
      ),
      practicalExerciseQuestion = "Laquelle de ces questions est la plus neutre et non suggestive ?",
      practicalExerciseChoices = listOf(
        "« Vous n'avez pas fait de fièvre hier, rassurez-moi ? »",
        "« La douleur irradie bien dans le bras gauche comme pour un infarctus ? »",
        "« Vers quel endroit du corps cette douleur se propage-t-elle, si elle se déplace ? »",
        "« Vous avez certainement mal quand vous mangez gras ? »"
      ),
      correctChoiceIndex = 2,
      practicalExerciseCorrection = "Demander vers quel endroit la douleur se propage sans citer d'organe laisse le patient libre de décrire son irradiation réelle."
    ),

    AnamnesisSkill(
      id = 8,
      title = "8. Explorer les antécédents médicaux et chirurgicaux",
      subtitle = "Pathologies chroniques, interventions, hospitalisations et terrain",
      explanation = "Les antécédents éclairent le terrain de survenue de la maladie actuelle. Il faut distinguer les antécédents médicaux (HTA, diabète, asthme), chirurgicaux (opérations, anesthésies), gynéco-obstétricaux et familiaux.",
      professionalPhrasings = listOf(
        "« Avez-vous des maladies pour lesquelles vous êtes suivi régulièrement ou prenez un traitement au long cours ? »",
        "« Avez-vous déjà été opéré au cours de votre vie ? Même dans votre enfance ? »",
        "« Avez-vous déjà été hospitalisé auparavant ? Si oui, pour quel motif ? »"
      ),
      errorsToAvoid = listOf(
        "Se contenter de demander « Avez-vous des antécédents ? » (le patient ne comprend souvent pas le terme).",
        "Oublier de quantifier les intoxications (tabac en paquets-années, alcool en unités par jour).",
        "Négliger les antécédents familiaux de décès précoce (mort subite avant 55 ans, cancers familiaux)."
      ),
      patientResponsesExamples = listOf(
        "« Non, je n'ai aucune maladie, je prends juste ma petite pilule pour la tension et mon insuline. »" to "« D'accord, donc vous êtes traité pour de l'hypertension et du diabète. Depuis combien d'années exactement ? »",
        "« J'ai été opéré de l'estomac il y a 30 ans mais ce n'est rien. »" to "« C'est au contraire une information précieuse ! S'agissait-il d'un ulcère, d'une vésicule ou d'autre chose ? Avez-vous gardé des comptes-rendus ? »"
      ),
      followUpQuestions = listOf(
        "« Quelqu'un dans votre famille proche (parents, frères, sœurs) a-t-il eu des problèmes cardiaques jeunes ? »",
        "« Avez-vous déjà fait une phlébite ou une embolie pulmonaire par le passé ? »"
      ),
      practicalExerciseQuestion = "Face à un patient qui répond « Non, je ne suis jamais malade, je prends juste du Kardegic et du Tahor », que doit noter l'étudiant ?",
      practicalExerciseChoices = listOf(
        "Aucun antécédent médical.",
        "Noter la présence d'une maladie cardiovasculaire sous-jacente ou d'une dyslipidémie/antécédent ischémique justifiant ces molécules.",
        "Supprimer les traitements car le patient dit qu'il n'est pas malade.",
        "Attendre la visite du médecin pour poser la question."
      ),
      correctChoiceIndex = 1,
      practicalExerciseCorrection = "Les patients sous-estiment souvent leurs pathologies chroniques quand elles sont stabilisées par un traitement préventif."
    ),

    AnamnesisSkill(
      id = 9,
      title = "9. Rechercher les traitements, allergies et facteurs de risque",
      subtitle = "Ordonnance réelle, automédication, terrain allergique et FDRCV",
      explanation = "Recueillir l'ordonnance exacte, l'automédication récente (AINS, paracétamol), les allergies vraies (anaphylaxie vs intolérance) et les facteurs de risque cardiovasculaires ou infectieux.",
      professionalPhrasings = listOf(
        "« Avez-vous sur vous votre ordonnance ou la liste exacte de vos médicaments ? »",
        "« Avez-vous pris des médicaments sans ordonnance ces derniers jours : aspirine, anti-inflammatoires, compléments ? »",
        "« Êtes-vous allergique à des médicaments ? Que s'est-il passé quand vous l'avez pris (gonflement, boutons, arrêt respiratoire) ? »"
      ),
      errorsToAvoid = listOf(
        "Noter « Allergie pénicilline » sans vérifier s'il s'agissait d'une diarrhée bénigne ou d'un œdème de Quincke.",
        "Oublier de demander la consommation de contraceptifs oraux (risque thromboembolique).",
        "Ne pas rechercher la prise récente d'AINS devant un tableau d'insuffisance rénale aiguë ou d'ulcère."
      ),
      patientResponsesExamples = listOf(
        "« Je suis allergique à l'Augmentin, ça m'a donné mal au ventre. »" to "« C'est une intolérance digestive fréquente liée à l'acide clavulanique, et non une allergie immunologique vraie avec urticaire ou choc. Nous allons le consigner précisément. »",
        "« J'ai pris de l'Advil pour calmer ma rage de dents avant d'avoir mal au ventre. »" to "« C'est un anti-inflammatoire majeur qui peut agresser l'estomac. Combien de comprimés avez-vous pris par jour ? »"
      ),
      followUpQuestions = listOf(
        "« Fumez-vous ou avez-vous fumé ? Combien de cigarettes par jour et pendant combien d'années ? »",
        "« Avez-vous du diabète, du cholestérol ou de l'hypertension dans votre entourage direct ? »"
      ),
      practicalExerciseQuestion = "Pourquoi est-il crucial de caractériser la sévérité d'une allergie médicamenteuse rapportée ?",
      practicalExerciseChoices = listOf(
        "Pour savoir si le patient aime ou non le goût du médicament.",
        "Pour discriminer une intolérance digestive mineure d'une réaction anaphylactique IgE-médiée engageant le pronostic vital et contre-indiquant formellement la classe.",
        "Pour réduire le coût de l'hospitalisation.",
        "C'est une obligation purement administrative sans impact médical."
      ),
      correctChoiceIndex = 1,
      practicalExerciseCorrection = "Distinguer une allergie vraie d'une simple intolérance évite de priver inutilement un patient d'antibiotiques majeurs tout en protégeant d'un choc anaphylactique récidivant."
    ),

    AnamnesisSkill(
      id = 10,
      title = "10. Explorer les symptômes associés",
      subtitle = "Recherche méthodique des signes d'accompagnement sémiologiques",
      explanation = "Un symptôme isolé n'a que peu de valeur ; ce sont les signes associés (fièvre, sueurs, nausées, palpitations, anomalies du transit ou de la diurèse) qui construisent le syndrome clinique.",
      professionalPhrasings = listOf(
        "« En plus de cette douleur thoracique, avez-vous ressenti un essoufflement, des sueurs ou des palpitations ? »",
        "« Cette fièvre s'est-elle accompagnée de frissons avec tremblements, ou de courbatures ? »",
        "« Avez-vous remarqué des modifications de vos urines ou de vos selles depuis le début des troubles ? »"
      ),
      errorsToAvoid = listOf(
        "Se focaliser uniquement sur l'organe douloureux et oublier d'interroger les appareils voisins.",
        "Ne pas poser la question des signes digestifs (nausées, vomissements) devant une douleur thoracique inférieure (infarctus inférieur).",
        "Oublier de vérifier l'existence de céphalées ou de photophobie devant une fièvre."
      ),
      patientResponsesExamples = listOf(
        "« Maintenant que vous le dites, j'étais trempé de sueur quand la douleur est venue. »" to "« C'est un signe d'accompagnement neurovégétatif très significatif. Avez-vous eu des nausées ou une sensation d'angoisse de mort imminente ? »",
        "« Mes urines sont devenues très foncées comme du thé. »" to "« Avez-vous remarqué si la peau ou le blanc des yeux a jauni (ictère) ? Et vos selles sont-elles décolorées ? »"
      ),
      followUpQuestions = listOf(
        "« Avez-vous remarqué une perte de poids involontaire ces dernières semaines ? »",
        "« Toussez-vous ? Si oui, crachez-vous du sang ou des sécrétions purulentes ? »"
      ),
      practicalExerciseQuestion = "Devant une douleur de la fosse iliaque droite chez une femme jeune, quel symptôme associé doit être systématiquement exploré ?",
      practicalExerciseChoices = listOf(
        "La présence de crampes dans les mollets.",
        "La date des dernières règles, l'existence de métrorragies ou de pertes vaginales anormales (GEU, salpingite).",
        "L'existence d'une allergie aux pollens de graminées.",
        "Le port de lunettes de vue."
      ),
      correctChoiceIndex = 1,
      practicalExerciseCorrection = "Chez toute femme en âge de procréer, une douleur pelvienne impose l'interrogatoire gynécologique strict pour éliminer une grossesse extra-utérine en urgence vitale."
    ),

    AnamnesisSkill(
      id = 11,
      title = "11. Reconstituer chronologiquement l'histoire de la maladie",
      subtitle = "Cinétique temporelle : début, évolution, épisodes antérieurs",
      explanation = "L'histoire de la maladie doit se lire comme un récit chronologique précis : date de début, mode d'installation (brutal, progressif), cinétique d'aggravation, efficacité des traitements et état actuel.",
      professionalPhrasings = listOf(
        "« Replaçons les événements dans l'ordre : à quelle date et heure précise est apparu le tout premier signe ? »",
        "« Était-ce brutal comme un coup de tonnerre ou s'est-il installé progressivement sur plusieurs jours ? »",
        "« Entre le début et aujourd'hui, est-ce que cela n'a fait qu'empirer ou y a-t-il eu des moments d'accalmie ? »"
      ),
      errorsToAvoid = listOf(
        "Écrire une histoire dans le désordre temporel (mélanger ce qui s'est passé il y a 3 semaines et ce matin).",
        "Oublier de dater les examens complémentaires déjà réalisés en ville avant l'hospitalisation.",
        "Ne pas préciser si le patient a déjà présenté un épisode strictement identique par le passé."
      ),
      patientResponsesExamples = listOf(
        "« Ça a commencé il y a un mois par une petite toux, puis avant-hier gros frissons, et ce matin je crache du sang. »" to "« Très bien, c'est très clair. Notons J-30 toux sèche traînante, J-2 frissons avec fièvre aiguë, et ce matin hémoptysie inaugurale. »",
        "« J'ai déjà eu ça l'année dernière, on m'avait donné des antibiotiques. »" to "« Vous souvenez-vous du nom du médicament et si vos symptômes avaient disparu en quelques jours ? »"
      ),
      followUpQuestions = listOf(
        "« Combien de temps exactement a duré le pic douloureux le plus intense ? »",
        "« Avez-vous pu dormir la nuit dernière ou la douleur vous a-t-elle réveillé ? »"
      ),
      practicalExerciseQuestion = "Quelle formule d'introduction d'histoire de la maladie respecte le mieux les standards médicaux ?",
      practicalExerciseChoices = listOf(
        "« Le patient est malade depuis longtemps et vient pour des examens divers. »",
        "« L'histoire de la maladie remonte à 72 heures, marquée par l'installation brutale d'une douleur basi-thoracique droite pleurale, complétée à H+24 par une fièvre à 39.2°C et une toux productive purulente. »",
        "« Le patient a pris du paracétamol mais sans succès donc il est venu. »",
        "« Histoire compliquée sans début bien clair selon la famille. »"
      ),
      correctChoiceIndex = 1,
      practicalExerciseCorrection = "Préciser la temporalité exacte (72h, H+24), le mode d'installation et la qualification sémiologique donne une structure chronologique irréprochable."
    ),

    AnamnesisSkill(
      id = 12,
      title = "12. Identifier les signes d'alarme (« Red Flags »)",
      subtitle = "Reconnaître sans délai les situations d'urgence vitale",
      explanation = "L'étudiant doit rechercher activement les signes de gravité immédiate : détresse respiratoire, instabilité hémodynamique, troubles de la conscience, purpura fulminans ou syndrome méningé.",
      professionalPhrasings = listOf(
        "« Avez-vous eu des vertiges intenses, l'impression de partir ou de perdre connaissance ? »",
        "« Avez-vous des difficultés à parler, avaler ou une faiblesse soudaine dans un bras ou une jambe ? »",
        "« Avez-vous des taches rouges ou violettes apparues sur la peau qui ne disparaissent pas quand on appuie ? »"
      ),
      errorsToAvoid = listOf(
        "Poursuivre un interrogatoire de 30 minutes chez un patient qui est en détresse respiratoire ou marbré (urgence vitale prioritaire).",
        "Négliger une céphalée brutale 'en coup de tonnerre' (suspicion d'hémorragie sous-arachnoïdienne).",
        "Sous-estimer une douleur thoracique constrictive prolongée de plus de 20 minutes."
      ),
      patientResponsesExamples = listOf(
        "« C'est la pire douleur de tête de toute ma vie, arrivée en une seconde comme une explosion. »" to "« ALERTE : C'est une céphalée explosive en coup de tonnerre. Appel immédiat du médecin senior pour scanner cérébral en urgence (éliminer HSA). »",
        "« J'ai les lèvres et le bout des doigts tout bleus et je n'arrive plus à reprendre mon souffle. »" to "« ALERTE : Cyanose et détresse respiratoire aiguë. Mise sous oxygène immédiate, appel de l'équipe médicale, monitoring SpO2 et constantes. »"
      ),
      followUpQuestions = listOf(
        "« Pouvez-vous coller votre menton contre votre poitrine sans que cela ne déclenche une vive douleur ? »",
        "« Avez-vous des douleurs abdominales d'une intensité brutale et syncopale ? »"
      ),
      practicalExerciseQuestion = "Quel signe d'alarme impose d'interrompre l'interrogatoire pour appeler le médecin et débuter les gestes d'urgence ?",
      practicalExerciseChoices = listOf(
        "Une rhinite claire avec éternuements sans fièvre.",
        "Une céphalée explosive en coup de tonnerre avec raideur de nuque et vomissements en jet.",
        "Une lombalgie mécanique évoluant depuis 6 mois.",
        "Une fatigue après une journée de travail."
      ),
      correctChoiceIndex = 1,
      practicalExerciseCorrection = "La céphalée explosive fébrile ou afébrile est une urgence absolue (hémorragie méningée ou méningite) nécessitant une alerte immédiate."
    ),

    AnamnesisSkill(
      id = 13,
      title = "13. Organiser les réponses du patient",
      subtitle = "Trier, structurer et hiérarchiser les données brutes",
      explanation = "Le patient s'exprime souvent de manière décousue, en mélangeant ressentis subjectifs, rumeurs familiales et faits médicaux. L'étudiant doit trier ces informations et les ranger dans les bonnes cases sémiologiques.",
      professionalPhrasings = listOf(
        "« Si vous me le permettez, reprenons point par point : parlons d'abord de votre toux, puis nous aborderons votre problème de genou. »",
        "« Je note bien ce que vous me dites pour votre sommeil ; focalisons-nous d'abord sur la douleur dans la poitrine qui vous a fait venir. »",
        "« Pour résumer ce premier point : la douleur a débuté hier matin et a duré 2 heures, c'est bien cela ? »"
      ),
      errorsToAvoid = listOf(
        "Recopier mot à mot le flux de conscience du patient sans aucun filtre sémiologique.",
        "Se laisser déborder par des détails anecdotiques sans rapport avec l'affection aiguë.",
        "Oublier de reformuler pour valider que le tri effectué correspond fidèlement à la réalité vécue."
      ),
      patientResponsesExamples = listOf(
        "« Alors ma belle-sœur m'a dit que c'était sûrement la vésicule parce que son cousin a eu la même chose après avoir mangé du poulet et ensuite le voisin m'a donné un cachet... »" to "« D'accord. Retenons les faits : vous avez ressenti une douleur abdominale post-prandiale et vous avez ingéré un comprimé inconnu. Quel était son effet ? »",
        "« J'ai mal au dos, et puis mes yeux piquent, et puis mon pied gauche me lance depuis l'an dernier... »" to "« Classons ces plaintes : quel est le problème aigu qui vous empêche de marcher aujourd'hui ? »"
      ),
      followUpQuestions = listOf(
        "« Est-ce que cette synthèse résume fidèlement ce que vous avez ressenti ? »",
        "« Y a-t-il un élément important que nous n'avons pas encore abordé concernant ce problème précis ? »"
      ),
      practicalExerciseQuestion = "Face à un patient très prolixe qui raconte des anecdotes familiales sans fin, quelle est la bonne attitude pédagogique ?",
      practicalExerciseChoices = listOf(
        "Lui couper la parole sèchement en disant : « Ça ne m'intéresse pas. »",
        "Le laisser parler pendant 45 minutes sans intervenir quitte à ne pas avoir le temps de l'examiner.",
        "Recadrer avec bienveillance en reformulant le point clinique clé et en le ramenant sur le fil conducteur de la plainte principale.",
        "Quitter la chambre et envoyer une infirmière à sa place."
      ),
      correctChoiceIndex = 2,
      practicalExerciseCorrection = "Le recadrage bienveillant par reformulation permet de maintenir le cap méthodologique sans froisser le patient."
    ),

    AnamnesisSkill(
      id = 14,
      title = "14. Produire une synthèse claire et structurée",
      subtitle = "La minute de synthèse orale au médecin senior et la trace écrite",
      explanation = "L'aboutissement de l'interrogatoire est la synthèse clinique : résumer en 60 secondes l'âge, le terrain, le motif d'admission, la chronologie des faits et les hypothèses soulevées.",
      professionalPhrasings = listOf(
        "« En synthèse, il s'agit de M. [Nom], [Âge] ans, aux antécédents de [FDR/ATCD], admis pour [Motif]. L'histoire est marquée par [Chronologie]. L'examen retrouve [Signes clés]. Nous discutons en priorité [Hypothèses]. »",
        "« Monsieur/Madame, je vais vous résumer ce que j'ai compris de votre situation pour être certain de n'avoir rien manqué. »"
      ),
      errorsToAvoid = listOf(
        "Faire une synthèse fleuve de 10 minutes qui noie l'auditeur dans les détails sans hiérarchie.",
        "Ne pas mentionner les antécédents majeurs qui changent complètement la probabilité diagnostique.",
        "Omettre de formuler clairement les 2 ou 3 hypothèses diagnostiques majeures."
      ),
      patientResponsesExamples = listOf(
        "« Vous avez tout compris docteur, c'est exactement ça ! »" to "« Parfait. Je vais maintenant passer à votre examen physique complet (cœur, poumons, ventre, réflexes). »",
        "« Non, vous avez oublié de dire que j'avais aussi eu des frissons terribles jeudi soir ! »" to "« Merci de cette précision cruciale, je la rajoute immédiatement à la chronologie avant l'auscultation. »"
      ),
      followUpQuestions = listOf(
        "« Validez-vous ce résumé avant que je présente votre dossier au médecin senior ? »"
      ),
      practicalExerciseQuestion = "Quelle structure orale de synthèse d'observation est la référence recommandée lors du staff médical ?",
      practicalExerciseChoices = listOf(
        "Donner directement le résultat de la prise de sang sans présenter le patient.",
        "Identité + Terrain/Antécédents clés → Motif d'admission → Chronologie de l'histoire → Données saillantes de l'examen → Hypothèses et plan.",
        "Lire l'intégralité du carnet de santé depuis la naissance.",
        "Donner uniquement son avis personnel sur le traitement à donner."
      ),
      correctChoiceIndex = 1,
      practicalExerciseCorrection = "La structure standardisée (Terrain → Motif → HDM → Examen → Hypothèses) est le langage universel de la transmission médicale rigoureuse."
    )
  )

  // ══════════════════════════════════════════════════════════════════════════
  // B. ORGANISATION DES INFORMATIONS RECUEILLIES (9 RUBRIQUES)
  // ══════════════════════════════════════════════════════════════════════════
  val infoCategoriesGuide: List<InfoCategoryGuide> = listOf(
    InfoCategoryGuide(
      categoryName = "1. Identité du patient",
      description = "Données d'identification et administratives indispensables.",
      whatToInclude = listOf("Nom, prénom ou pseudonyme/initiales", "Âge et date de naissance", "Sexe biologique", "Profession habituelle", "Mode d'entrée et statut de protection"),
      whatToExclude = "Ne pas inclure les symptômes ni les antécédents médicaux.",
      hospitalExample = "Mme Sophie M., 54 ans, comptable, admise via les urgences le 04/11/2026."
    ),
    InfoCategoryGuide(
      categoryName = "2. Motif de consultation",
      description = "Le problème ou symptôme cardinal qui motive la venue.",
      whatToInclude = listOf("Plainte principale formulée en termes sémiologiques précis", "Durée d'évolution globale"),
      whatToExclude = "Ne pas inscrire une hypothèse diagnostique présumée.",
      hospitalExample = "Douleur thoracique rétro-sternale constrictive apparue depuis 2 heures."
    ),
    InfoCategoryGuide(
      categoryName = "3. Histoire de la maladie",
      description = "Le récit chronologique de l'épisode actuel.",
      whatToInclude = listOf("Date et heure de début précises", "Mode d'installation (brutal / progressif)", "Facteurs déclenchants", "Caractéristiques sémiologiques détaillées", "Évolution dans le temps", "Traitements pris et efficacité"),
      whatToExclude = "Ne pas mélanger les pathologies anciennes sans rapport direct.",
      hospitalExample = "Installation brutale à 06h00 au repos d'un serrement thoracique irradiant dans la mâchoire, sans soulagement par le paracétamol."
    ),
    InfoCategoryGuide(
      categoryName = "4. Antécédents",
      description = "Le terrain pathologique antérieur du patient.",
      whatToInclude = listOf("Antécédents médicaux (HTA, diabète, asthme...)", "Antécédents chirurgicaux datés", "Antécédents gynéco-obstétricaux", "Antécédents familiaux significatifs"),
      whatToExclude = "Ne pas inscrire les symptômes de la maladie aiguë actuelle.",
      hospitalExample = "HTA depuis 10 ans, cholécystectomie en 2018, père décédé d'un infarctus à 52 ans."
    ),
    InfoCategoryGuide(
      categoryName = "5. Signes fonctionnels",
      description = "Les plaintes et sensations subjectives ressenties par le patient.",
      whatToInclude = listOf("Douleur, nausées, vertiges, essoufflement, palpitations, asthénie, prurit"),
      whatToExclude = "Les signes physiques objectivés par l'auscultation ou les appareils de mesure.",
      hospitalExample = "Sensation d'oppression thoracique, dyspnée au moindre effort, angoisse d'étouffement."
    ),
    InfoCategoryGuide(
      categoryName = "6. Symptômes associés",
      description = "Les signes surajoutés accompagnant la plainte principale.",
      whatToInclude = listOf("Sueurs profuses, nausées, vomissements, céphalées, frissons, troubles du transit"),
      whatToExclude = "Les antécédents anciens guéris.",
      hospitalExample = "Sueurs froides d'accompagnement et deux épisodes de vomissements alimentaires."
    ),
    InfoCategoryGuide(
      categoryName = "7. Facteurs de risque",
      description = "Éléments augmentant la probabilité de survenue d'une pathologie.",
      whatToInclude = listOf("FDRCV : Tabagisme (chiffré en PA), HTA, diabète, dyslipidémie, obésité, sédentarité", "FDR thromboemboliques, infectieux, toxiques ou professionnels"),
      whatToExclude = "Les traitements médicamenteux prescrits.",
      hospitalExample = "Tabagisme actif à 25 paquets-années, sédentarité, IMC à 31 kg/m²."
    ),
    InfoCategoryGuide(
      categoryName = "8. Traitements en cours",
      description = "Liste exhaustive de l'ordonnance habituelle et des prises récentes.",
      whatToInclude = listOf("DCI des molécules", "Posologies et horaires de prise", "Automédication récente (AINS, antalgiques)", "Allergies médicamenteuses avérées"),
      whatToExclude = "Les traitements arrêtés il y a plusieurs années.",
      hospitalExample = "Ramipril 5mg 1 cp le matin, Atorvastatine 20mg le soir. Pas d'allergie connue."
    ),
    InfoCategoryGuide(
      categoryName = "9. Synthèse de l'interrogatoire",
      description = "Résumé articulé reliant le terrain, l'histoire et les hypothèses.",
      whatToInclude = listOf("Patient de X ans avec terrain Y", "Présentant un tableau de Z évoluant depuis...", "Évoquant en première intention..."),
      whatToExclude = "Le compte-rendu exhaustif mot à mot.",
      hospitalExample = "Homme de 54 ans tabagique et hypertendu, présentant une douleur angineuse prolongée typique avec sueurs, évocatrice en urgence d'un syndrome coronarien aigu."
    )
  )

  val classificationExercises: List<ClassificationExercise> = listOf(
    ClassificationExercise(
      rawPatientQuote = "« J'ai été opéré de l'appendicite en 2012 et mon père est mort d'une crise cardiaque à 50 ans. »",
      options = listOf("Motif de consultation", "Histoire de la maladie", "Antécédents", "Signes fonctionnels"),
      correctCategory = "Antécédents",
      explanation = "Ces éléments renseignent le passé chirurgical personnel et le risque cardiovasculaire familial génétique."
    ),
    ClassificationExercise(
      rawPatientQuote = "« Vers 14h, alors que je montais les escaliers, j'ai soudainement manqué d'air et senti un point de côté aigu. »",
      options = listOf("Histoire de la maladie", "Traitements en cours", "Facteurs de risque", "Identité du patient"),
      correctCategory = "Histoire de la maladie",
      explanation = "Ce récit décrit la chronologie précise, le facteur déclenchant (effort) et le mode d'installation des symptômes de l'épisode aigu."
    ),
    ClassificationExercise(
      rawPatientQuote = "« J'ai une douleur qui serre très fort sous le sternum et qui m'empêche de respirer à fond. »",
      options = listOf("Signes fonctionnels", "Antécédents", "Traitements en cours", "Facteurs de risque"),
      correctCategory = "Signes fonctionnels",
      explanation = "Il s'agit de la description directe de la plainte subjective ressentie par le patient (douleur constrictive et gêne respiratoire)."
    ),
    ClassificationExercise(
      rawPatientQuote = "« Je fume un paquet par jour depuis mes 20 ans et je fais de la tension non soignée. »",
      options = listOf("Facteurs de risque", "Motif de consultation", "Synthèse de l'interrogatoire", "Histoire de la maladie"),
      correctCategory = "Facteurs de risque",
      explanation = "Le tabagisme quantifié (paquet-année) et l'hypertension artérielle non contrôlée sont des facteurs majeurs de risque cardiovasculaire."
    )
  )

  // ══════════════════════════════════════════════════════════════════════════
  // C. OBSERVATION MÉDICALE MÉTHODIQUE
  // ══════════════════════════════════════════════════════════════════════════
  val observationSkills: List<ObservationMethodSkill> = listOf(
    ObservationMethodSkill(
      title = "1. Organiser une observation médicale complète",
      objective = "Maîtriser le plan canonique en 7 étapes d'une observation hospitalière.",
      detailedGuide = "L'observation médicale hospitalière suit une architecture immuable :\n" +
        "1. Identification du patient et données administratives\n" +
        "2. Motif d'admission précis\n" +
        "3. Histoire de la maladie détaillée et chronologique\n" +
        "4. Antécédents médicaux, chirurgicaux, familiaux et ordonnance\n" +
        "5. Examen clinique général et constantes vitales\n" +
        "6. Examen physique méthodique appareil par appareil\n" +
        "7. Synthèse clinique, hypothèses hiérarchisées et stratégie de prise en charge.",
      keyPoints = listOf("Toujours respecter l'ordre standardisé", "Noter les signes négatifs pertinents", "Ne jamais mélanger interrogatoire et examen physique"),
      badExample = "« Le patient a un infarctus. Il a mal et son cœur bat vite. Il prend de l'aspirine. »",
      goodExample = "Observation structurée avec titres de paragraphes clairs, constantes chiffrées et auscultation documentée avec signes positifs et négatifs.",
      exercisePrompt = "Quel est le défaut majeur si un étudiant place l'auscultation cardiaque dans le paragraphe de l'histoire de la maladie ?",
      exerciseCorrection = "L'histoire de la maladie relève de l'anamnèse (ce que raconte le patient), tandis que l'auscultation est un examen physique objectif réalisé par le soignant. Les mélanger nuit gravement à la clarté et à la fiabilité médico-légale."
    ),

    ObservationMethodSkill(
      title = "2. Décrire l'état général et les constantes vitales",
      objective = "Quantifier la tolérance clinique globale et l'autonomie.",
      detailedGuide = "L'état général s'évalue selon plusieurs composantes clés :\n" +
        "• Le score OMS / Performance Status ECOG (0 = activité normale sans restriction, à 4 = alité en permanence)\n" +
        "• L'état nutritionnel et les téguments : poids, taille, IMC, fonte musculaire temporale/quadricipitale, déshydratation (pli cutané, sécheresse des muqueuses)\n" +
        "• L'aspect du faciès : douloureux, angoissé, tirage, cyanose, sueurs, ictère conjonctival\n" +
        "• Les 8 constantes vitales chiffrées : PA aux deux bras, FC, FR (sur 1 min entière), SpO2, Température, Glycémie, EVA douleur, Diurèse.",
      keyPoints = listOf("La FR est la constante la plus sensible de dégradation", "Toujours noter l'OMS pour évaluer le terrain", "Vérifier la SpO2 en précisant 'air ambiant' ou le débit d'O2"),
      badExample = "« Patient en forme moyenne avec tension normale. »",
      goodExample = "« Patient conscient, orienté, état général conservé (OMS 1). Apyrétique (37.2°C). PA 132/82 mmHg aux deux bras, FC 72 bpm régulière, FR 16/min sans tirage, SpO2 98% en air ambiant. Poids 74 kg, taille 1m75 (IMC 24.2 kg/m²). Muqueuses bien hydratées, conjonctives normocolorées. »",
      exercisePrompt = "Pourquoi est-il insuffisant de noter simplement 'SpO2 96%' sans préciser l'environnement respiratoire ?",
      exerciseCorrection = "Une SpO2 à 96% en air ambiant est physiologique, alors qu'une SpO2 à 96% sous 15 litres d'oxygène au masque à haute concentration traduit une détresse respiratoire aiguë gravissime."
    ),

    ObservationMethodSkill(
      title = "3. Distinguer rigoureusement signes subjectifs et signes objectifs",
      objective = "Savoir séparer ce que ressent le patient de ce que constate le clinicien.",
      detailedGuide = "• Signes subjectifs (symptômes) : sensations rapportées par le patient que le médecin ne peut pas directement mesurer (douleur, nausées, vertiges, fatigue, angoisse, palpitations ressenties).\n" +
        "• Signes objectifs (signes physiques et mesures) : anomalies constatées et vérifiées par le clinicien lors de l'examen physique ou instrumental (râles crépitants auscultatoires, souffle systolique, hépatomégalie mesurée en cm, fièvre au thermomètre, œdèmes prenant le godet, tachycardie à l'ECG).",
      keyPoints = listOf("Les signes subjectifs guident l'orientation", "Les signes objectifs confirment et étayent le syndrome", "Ne jamais transformer une plainte en signe physique sans examen"),
      badExample = "« Examen physique : le patient a très mal au ventre et a envie de vomir. » (Ce sont des symptômes subjectifs !)",
      goodExample = "« Examen physique abdominal : Abdomen souple, dépressible, douleur provoquée à la palpation du point de McBurney avec signe de Blumberg positif. Bruits hydro-aériques présents. Orifices herniaires libres. »",
      exercisePrompt = "Classez les éléments suivants : Céphalée, Souffle carotidien, Nausée, SpO2 88%, Prurit, Ictère cutanéo-muqueux.",
      exerciseCorrection = "• Subjectifs : Céphalée, Nausée, Prurit.\n• Objectifs : Souffle carotidien (ausculté), SpO2 88% (oxymètre), Ictère cutanéo-muqueux (constaté visuellement)."
    ),

    ObservationMethodSkill(
      title = "4. Rédiger une conclusion clinique structurée",
      objective = "Terminer l'observation par une synthèse claire et un plan d'action hiérarchisé.",
      detailedGuide = "La conclusion d'observation répond à 4 questions fondamentales :\n" +
        "1. Qui est le patient et quel est son terrain ? (Âge, sexe, comorbidités majeures)\n" +
        "2. Quel est le syndrome clinique principal ? (Regroupement des signes positifs)\n" +
        "3. Quelles sont les hypothèses diagnostiques hiérarchisées ? (Diagnostic le plus probable + Urgences vitales à éliminer formellement)\n" +
        "4. Quelle est la conduite à tenir immédiate ? (Examens complémentaires ciblés de première intention, thérapeutique d'urgence et surveillance).",
      keyPoints = listOf("Toujours citer l'urgence vitale à éliminer", "Justifier les examens prescrits", "Indiquer le niveau de surveillance requis"),
      badExample = "« En conclusion, c'est peut-être une embolie ou une pneumonie. On verra avec les prises de sang. »",
      goodExample = "« En conclusion, patient de 62 ans coronarien et hypertendu, présentant un syndrome douloureux thoracique constrictif aigu sans sus-décalage de ST à l'ECG per-critique. Diagnostic le plus probable : Syndrome Coronarien Aigu sans sus-ST (NSTEMI) à troponine en attente. Diagnostics différentiels à éliminer : Embolie pulmonaire, Dissection aortique. CAT : Pose de VVP, monitoring scopique continu, double anti-agrégation, anticoagulation curative, dosage cinétique des troponines et avis cardiologique en urgence. »",
      exercisePrompt = "Quelles sont les 3 urgences vitales thoraciques qui doivent toujours être discutées dans la conclusion devant une douleur thoracique aiguë ?",
      exerciseCorrection = "Le Syndrome Coronarien Aigu (SCA), l'Embolie Pulmonaire (EP), et la Dissection Aortique (auxquelles s'ajoutent le pneumothorax compressif et la rupture œsophagienne)."
    )
  )

  // ══════════════════════════════════════════════════════════════════════════
  // D. SYNTHÈSE CLINIQUE & DÉMARCHE DIAGNOSTIQUE
  // ══════════════════════════════════════════════════════════════════════════
  val synthesisCases: List<SynthesisCaseTraining> = listOf(
    SynthesisCaseTraining(
      id = "case_pneumo",
      title = "Cas 1 : De la toux fébrile au syndrome de condensation alvéolaire",
      patientProfile = "Homme de 68 ans, fumeur sevré (15 PA), autonome (OMS 0), pas d'autre antécédent.",
      symptomsCollected = listOf(
        "Frissons solennels intenses avec sensation de fièvre depuis 48 heures",
        "Point de côté basi-thoracique droit d'apparition brutale",
        "Toux initialement sèche devenue productive avec crachats rouillés couleur 'jus de prune'",
        "Asthénie aiguë et anorexie"
      ),
      physicalSignsFound = listOf(
        "Température : 39.4°C, PA 125/75 mmHg, FC 102 bpm, FR 26/min (polypnée), SpO2 93% en air ambiant",
        "Palpation : Augmentation des vibrations vocales à la base pulmonaire droite",
        "Percussion : Matité franche basi-thoracique droite non déclive",
        "Auscultation : Râles crépitants en foyer en fin d'inspiration avec souffle tubaire central bien audible à la base droite"
      ),
      clinicalReasoningPath = "1. Regroupement syndromique : Association (Augmentation des vibrations vocales + Matité + Souffle tubaire + Crépitants en foyer) = SYNDROME DE CONDENSATION PULMONAIRE ALVÉOLAIRE TYPIQUE.\n" +
        "2. Contexte fébrile aigu avec frissons solennels et crachats rouillés = Étiologie infectieuse bactérienne typique (Pneumocoque / Streptococcus pneumoniae).\n" +
        "3. Évaluation de la gravité (Score CRB-65) : Âge >= 65 ans (1 point), FR >= 30 (0), PA < 90/60 (0), Confusion (0) -> Score CRB-65 = 1 (Hospitalisation recommandée pour surveillance et antibiothérapie).",
      primaryHypothesis = "Pneumopathie Franche Lobaire Aiguë (PFLA) droite bactérienne présumée à pneumocoque.",
      emergencyToRuleOut = "Décompensation respiratoire aiguë avec choc septique, ou épanchement pleural parapneumonique purulent.",
      immediateActionPlan = listOf(
        "Radiographie thoracique de face en urgence (rechercher opacité alvéolaire systématisée)",
        "Bilan biologique : NFS (hyperleucocytose à PNN), CRP, hémocultures avant toute antibiothérapie",
        "Antibiothérapie probabiliste précoce dirigée contre le pneumocoque (Amoxicilline 1g x 3/jour)",
        "Oxygénothérapie adaptée pour maintenir SpO2 >= 95%, surveillance des constantes toutes les 4h."
      )
    ),

    SynthesisCaseTraining(
      id = "case_cardio",
      title = "Cas 2 : De la dyspnée nocturne au syndrome d'insuffisance cardiaque",
      patientProfile = "Femme de 74 ans, antécédent d'hypertension artérielle mal équilibrée et d'infarctus antérieur il y a 8 ans.",
      symptomsCollected = listOf(
        "Essoufflement progressif à la marche puis au repos depuis 5 jours",
        "Orthopnée majeure obligeant la patiente à dormir assise sur 3 oreillers",
        "Réveils nocturnes en sursaut avec sensation d'étouffement (dyspnée paroxystique nocturne)",
        "Prise de poids rapide de 4 kg en une semaine avec gonflement des chevilles"
      ),
      physicalSignsFound = listOf(
        "Constantes : PA 165/95 mmHg, FC 110 bpm régulière, FR 28/min, SpO2 91% en air ambiant, apyrétique",
        "Inspection : Turgescence jugulaire spontanée bilatérale à 45° et œdèmes des membres inférieurs blancs, mous, indolores, prenant le godet jusqu'à mi-jambes",
        "Palpation : Reflux hépato-jugulaire net et sensible",
        "Auscultation cardiaque : Galop protodiastolique B3 bien perçu à la pointe",
        "Auscultation pulmonaire : Râles crépitants bilatéraux symétriques ascendant jusqu'aux mi-champs pulmonaires ('en marée montante')"
      ),
      clinicalReasoningPath = "1. Regroupement syndromique gauche : Orthopnée + Crépitants bilatéraux en marée montante + Bruit de galop B3 = SYNDROME D'INSUFFISANCE CARDIAQUE GAUCHE (Œdème Aigu du Poumon hémodynamique).\n" +
        "2. Regroupement syndromique droit : Turgescence jugulaire + Reflux hépato-jugulaire + OMI bilatéraux prenant le godet + Prise de poids rapide = SYNDROME D'INSUFFISANCE CARDIAQUE DROITE.\n" +
        "3. Synthèse globale : Décompensation cardiaque globale à prédominance gauche (OAP), favorisée par une poussée hypertensive sur cardiopathie ischémique préexistante.",
      primaryHypothesis = "Insuffisance cardiaque globale décompensée avec Œdème Aigu du Poumon (OAP) hémodynamique.",
      emergencyToRuleOut = "Infarctus du myocarde récidivant (SCA gâchette), trouble du rythme ventriculaire ou tamponnade.",
      immediateActionPlan = listOf(
        "Position assise stricte jambes pendantes, oxygénothérapie pour SpO2 >= 95%",
        "Diurétique de l'anse intraveineux rapide (Furosémide 40 à 80 mg IVD)",
        "Dérivés nitrés intraveineux si PAS > 110 mmHg pour réduire la post-charge et la précharge",
        "ECG 12 dérivations immédiat, dosage Troponine, NT-proBNP, Ionogramme/Créatinine, et Échocardiographie transthoracique (ETT) dès stabilisation."
      )
    )
  )
}
