package com.example.data.model.sheets

import com.example.data.model.SemiologyCategory
import com.example.data.model.SemiologySheet

object SemiologyCardioSheets {

  val sheets: List<SemiologySheet> = listOf(
    // 1. Douleur thoracique
    SemiologySheet(
      id = "cv_douleur_thoracique",
      name = "Douleur Thoracique Angineuse (Angor)",
      category = SemiologyCategory.CARDIOVASCULAR,
      subCategory = "Signes fonctionnels",
      definition = "Sensation pénible, constrictive ou d'écrasement ('en étau'), siégeant typiquement en région rétrosternale, traduisant une ischémie myocardique aiguë par déséquilibre entre apports et besoins en oxygène.",
      physiopathology = "Sténose coronarienne athéromateuse ou spasme réduisant le flux sanguin coronaire. L'hypoxie myocardique stimule la production d'adénosine et de bradykinine activant les terminaisons nerveuses sympathiques cardiaques (afférences C7-T4).",
      etiologies = listOf(
        "Athérosclérose coronarienne (Angor stable, SCA ST- et SCA ST+)",
        "Spasme coronarien (Angor de Prinzmetal)",
        "Rétrécissement aortique serré (angor d'effort sans lésion coronarienne)",
        "Cardiomyopathie hypertrophique obstructive",
        "Dissection aortique (douleur d'emblée maximale, déchirante, dorsale)"
      ),
      circumstances = "Déclenchée à l'effort physique, au froid, post-prandiale ou survenant au repos dans les syndromes coronariens aigus.",
      anamnesis = "Préciser l'heure exacte de début, le caractère constrictif, l'irradiation aux mâchoires et au bras gauche, la durée (> 20 min = SCA jusqu'à preuve du contraire), l'efficacité de la Trinitrine sublinguale (cède en < 3 min dans l'angor d'effort stable).",
      searchMethod = "Interrogatoire systématique, recherche des facteurs de risque cardiovasculaires (tabac, diabète, HTA, dyslipidémie, âge, antécédents familiaux précoces).",
      physicalExam = "Examen souvent normal au repos. Rechercher un souffle aortique, un galop B4, des signes d'insuffisance cardiaque, une asymétrie tensionnelle ou de pouls (dissection aortique).",
      associatedSigns = listOf("Sueurs profuses", "Angoisse de mort imminente", "Nausées et vomissements (territoire inférieur)", "Dyspnée d'effort associée"),
      clinicalSignificance = "Maître-symptôme de la maladie coronarienne. Toute douleur thoracique prolongée est une urgence diagnostique et thérapeutique vitale.",
      differentialDiagnoses = listOf(
        "Péricardite aiguë (douleur augmentée en inspiration profonde et décubitus, soulagée penché en avant)",
        "Embolie pulmonaire (douleur basithoracique pleurétique avec polypnée brutale)",
        "Pneumothorax spontané (douleur brutale unilatérale en coup de poignard)",
        "Reflux gastro-œsophagien et spasmes œsophagiens"
      ),
      complementaryExams = listOf(
        "ECG 12 dérivations immédiat (dans les 10 minutes)",
        "Troponine ultrasensible répétée (H0, H1-H3)",
        "Radiographie de thorax",
        "Coronarographie urgente si SCA ST+"
      ),
      gravitySigns = listOf("Instabilité hémodynamique (PAS < 90 mmHg)", "OAP associé (râles crépitants bilatéraux)", "Troubles du rythme ventriculaire (TV, FV)", "Syncope inaugurale"),
      pitfallsAndLimits = "Attention aux présentations atypiques fréquentes chez le diabétique (ischémie silencieuse ou simple malaise), le sujet âgé et la femme (épigastralgie, fatigue inexpliquée, dyspnée isolée).",
      keyPoints = listOf(
        "Rétrosternale constrictive irradiant au bras gauche et à la mâchoire.",
        "Durée > 20 minutes au repos = suspicion de Syndrome Coronarien Aigu.",
        "ECG 12 dérivations dans les 10 premières minutes de la prise en charge."
      )
    ),

    // 2. Dyspnée cardiaque
    SemiologySheet(
      id = "cv_dyspnee",
      name = "Dyspnée d'Origine Cardiaque (Classification NYHA)",
      category = SemiologyCategory.CARDIOVASCULAR,
      subCategory = "Signes fonctionnels",
      definition = "Sensation consciente de gêne respiratoire ('soif d'air') secondaire à l'élévation des pressions de remplissage ventriculaires gauches et à la congestion pulmonaire capillaire.",
      physiopathology = "Défaillance de pompe du ventricule gauche -> élévation de la pression télédiastolique VG -> répercussion rétrograde sur la pression capillaire pulmonaire (> 18 mmHg) -> extravasation de liquide dans l'interstitium puis les alvéoles -> diminution de la compliance pulmonaire.",
      etiologies = listOf(
        "Insuffisance ventriculaire gauche (ischémique, hypertensive, valvulaire)",
        "Rétrécissement aortique serré ou insuffisance aortique aiguë",
        "Insuffisance mitrale aiguë ou chronique décompensée",
        "Troubles du rythme rapide (Fibrillation atriale à réponse rapide)",
        "Tamponnade et péricardite constrictive"
      ),
      circumstances = "Initialement pour des efforts importants, puis pour des efforts modérés ou minimes, et enfin au repos.",
      anamnesis = "Classifier selon la NYHA : Stade I (effort intense inhabituel), Stade II (efforts de la vie quotidienne : 2 étages), Stade III (efforts minimes : marche à plat), Stade IV (repos permanent).",
      searchMethod = "Interroger sur le nombre d'oreillers nécessaires pour dormir (orthopnée) et la survenue de réveils nocturnes en détresse (DPN).",
      physicalExam = "Polypnée superficielle, auscultation pulmonaire (râles crépitants bilatéraux en marée montante), auscultation cardiaque (galop B3 protodiastolique, tachycardie), signes droits associés.",
      associatedSigns = listOf("Toux quinteuse à l'effort", "Grésillement laryngé", "Expectorations rosées mousseuses (OAP)", "Prise de poids rapide par rétention hydrosodée"),
      clinicalSignificance = "Témoin direct de la défaillance contractile ou de compliance du ventricule gauche.",
      differentialDiagnoses = listOf(
        "Dyspnée bronchopulmonaire (asthme, BPCO avec sibilants)",
        "Embolie pulmonaire (dyspnée brutale sans crépitants initiaux)",
        "Anémie aiguë sévère",
        "Hyperventilation anxieuse / attaque de panique"
      ),
      complementaryExams = listOf(
        "Peptides natriurétiques (BNP > 100 pg/ml ou NT-proBNP > 300 pg/ml)",
        "Échocardiographie transthoracique (ETT) : fraction d'éjection VG, pressions de remplissage",
        "Radiographie thoracique (cardiomégalie, redistribution vasculaire aux apex, lignes B de Kerley)",
        "ECG (recherche de trouble du rythme ou nécrose)"
      ),
      gravitySigns = listOf("Orthopnée stricte imposant la position assise", "Tirage et respiration abdominale paradoxale", "SpO2 < 90%", "Sueurs froides et marbrures (choc cardiogénique)"),
      pitfallsAndLimits = "Chez l'insuffisant respiratoire chronique (BPCO), distinguer la décompensation cardiaque de l'exacerbation respiratoire nécessite le dosage du BNP et l'ETT.",
      keyPoints = listOf(
        "Orthopnée et DPN sont très spécifiques de l'insuffisance cardiaque gauche.",
        "Le BNP élimine l'origine cardiaque si normal (haute valeur prédictive négative).",
        "L'OAP impose la position assise, l'oxygène et les diurétiques de l'anse IV."
      )
    ),

    // 3. Syncope et Lipothymie
    SemiologySheet(
      id = "cv_syncope",
      name = "Syncope et Lipothymie Cardiaques",
      category = SemiologyCategory.CARDIOVASCULAR,
      subCategory = "Signes fonctionnels",
      definition = "La syncope est une perte de connaissance brève, complète, avec récupération spontanée et intégrale, due à une hypoperfusion cérébrale globale transitoire. La lipothymie est une sensation de malaise imminent sans perte de connaissance réelle.",
      physiopathology = "Chute brutale du débit cardiaque (bradycardie extrême, tachyarythmie, obstacle mécanique à l'éjection) ou chute des résistances vasculaires périphériques (réflexe vasovagal).",
      etiologies = listOf(
        "Bloc auriculoventriculaire de haut degré (BAV 2 Möbitz II, BAV 3 complet - Syndrome de Stokes-Adams)",
        "Tachycardie ventriculaire ou fibrillation ventriculaire",
        "Rétrécissement aortique serré (syncope d'effort classique)",
        "Cardiomyopathie hypertrophique obstructive",
        "Syncope réflexe vasovagale (bénigne, précédée de prodromes)",
        "Hypotension orthostatique (iatrogène, dysautonomie)"
      ),
      circumstances = "À l'effort (urgence : évoquer rétrécissement aortique, CMHO, TV), au repos sans prodromes (syncope à l'emporte-pièce évocatrice d'un trouble conductif), au lever (orthostatisme).",
      anamnesis = "Présence de prodromes (sueurs, nausées, bâillements = vasovagal) ou survenue à l'emporte-pièce brutale, position lors du malaise, durée, traumatismes secondaires (chute traumatique fréquente si cardiaque).",
      searchMethod = "Mesure de la pression artérielle couché puis debout à 1, 3 et 5 minutes (test d'hypotension orthostatique), palpation des pouls, auscultation cardiaque minutieuse.",
      physicalExam = "Rechercher un souffle d'éjection aortique rugueux, une bradycardie sévère permanente, un déficit neurologique résiduel (qui orienterait vers un AVC).",
      associatedSigns = listOf("Pâleur céruléenne initiale", "Traumatisme crânien ou facial", "Morsure du bout de la langue (contrairement à la morsure latérale de la crise d'épilepsie)"),
      clinicalSignificance = "Toute syncope survenant à l'effort ou chez un insuffisant cardiaque fait craindre une mort subite imminente.",
      differentialDiagnoses = listOf(
        "Crise d'épilepsie généralisée tonico-clonique (confusion post-critique prolongée, stertor)",
        "Accident ischémique transitoire vertébro-basilaire (signes neurologiques focaux associés)",
        "Hypoglycémie (pas de récupération spontanée sans resucrage)"
      ),
      complementaryExams = listOf(
        "ECG 12 dérivations de repos systématique",
        "Holter ECG des 24-48h ou moniteur cardiaque implantable",
        "Échocardiographie doppler transthoracique (évaluation valvulaire et FEVG)",
        "Épreuve d'effort (si syncope à l'effort)"
      ),
      gravitySigns = listOf("Syncope d'effort", "Syncope sans aucun prodrome chez un cardiaque connu", "Anomalies ECG inaugurales (onde de Brugada, QT long, bloc de branche)", "Antécédent familial de mort subite"),
      pitfallsAndLimits = "Une syncope d'origine cardiaque peut s'accompagner de quelques secousses myocloniques réflexes (anoxie cérébrale brève), à ne pas confondre à tort avec une vraie crise d'épilepsie.",
      keyPoints = listOf(
        "Syncope = perte de connaissance brève avec récupération spontanée complète.",
        "Syncope à l'effort = rétrécissement aortique ou cardiomyopathie obstructive.",
        "L'ECG 12 dérivations est l'examen fondamental de première intention."
      )
    ),

    // 4. Turgescence jugulaire et Reflux hépato-jugulaire
    SemiologySheet(
      id = "cv_rhj_turgescence",
      name = "Turgescence Jugulaire et Reflux Hépato-Jugulaire (RHJ)",
      category = SemiologyCategory.CARDIOVASCULAR,
      subCategory = "Signes physiques",
      definition = "Distension anormale de la veine jugulaire interne au repos (turgescence) et majoration durable de cette distension lors de la compression manuelle du foie (reflux hépato-jugulaire).",
      physiopathology = "Témoigne de l'augmentation de la pression veineuse centrale (PVC) transmise rétrogradement depuis l'oreillette droite en raison d'une défaillance diastolique ou systolique du ventricule droit.",
      etiologies = listOf(
        "Insuffisance cardiaque droite décompensée",
        "Cœur pulmonaire chronique post-embolique ou BPCO",
        "Tamponnade péricardique (signe de Kussmaul)",
        "Péricardite constrictive",
        "Insuffisance tricuspidienne sévère avec onde V géante",
        "Syndrome de la veine cave supérieure (turgescence sans reflux)"
      ),
      circumstances = "Bilan d'œdèmes des membres inférieurs, dyspnée inexpliquée ou suspicion de décompensation cardiaque globale.",
      anamnesis = "Recherche d'hépatalgie d'effort (pesanteur douloureuse de l'hypochondre droit à la marche) et de prise de poids récente.",
      searchMethod = "Patient installé en position demi-assise à 45°, tête discrètement tournée vers la gauche. Appliquer une pression manuelle continue et ferme sur l'hypochondre droit pendant 15 à 30 secondes en demandant au patient de respirer calmement par la bouche sans bloquer sa respiration.",
      physicalExam = "Le test est positif si la colonne veineuse jugulaire monte de plus de 3 cm et se maintient durant toute la durée de la compression abdominale.",
      associatedSigns = listOf("Hépatomégalie douloureuse à surface lisse", "Œdèmes bilatéraux des membres inférieurs", "Bruit de galop droit (B3 ventriculaire)", "Souffle holosystolique au foyer tricuspide majoré à l'inspiration (signe de Carvallo)"),
      clinicalSignificance = "Signe physique le plus fidèle et reproductible pour évaluer l'élévation des pressions de remplissage du cœur droit.",
      differentialDiagnoses = listOf(
        "Syndrome cave supérieur (turgescence jugulaire permanente sans reflux hépato-jugulaire et sans pulsation, œdème en pèlerine)",
        "Fausses turgescences chez le patient obèse au cou court"
      ),
      complementaryExams = listOf(
        "Échocardiographie cardiaque : mesure du diamètre et de la collapsibilité de la veine cave inférieure",
        "Évaluation des pressions artérielles pulmonaires (PAPS)",
        "Radiographie thoracique (recherche d'épanchement pleural ou péricardique)"
      ),
      gravitySigns = listOf("Pulsation hépatique systolique expansive", "Turgescence permanente assise à 90° (PVC > 15 mmHg)", "Signes de choc associés (tamponnade)"),
      pitfallsAndLimits = "Si le patient retient sa respiration (manœuvre de Valsalva), la distension jugulaire devient faussement positive. Exiger une respiration buccale libre.",
      keyPoints = listOf(
        "Examen impératif à 45° et respiration buccale sans apnée.",
        "Reflux positif = maintien de la distension pendant toute la compression.",
        "Signe de Carvallo : souffle d'insuffisance tricuspide majoré à l'inspiration."
      )
    ),

    // 5. Souffles cardiaques
    SemiologySheet(
      id = "cv_souffles",
      name = "Souffles Cardiaques Systoliques et Diastoliques",
      category = SemiologyCategory.CARDIOVASCULAR,
      subCategory = "Signes physiques",
      definition = "Bruits anormaux produits par l'écoulement turbulent du sang à travers un orifice valvulaire rétréci, une valve incontinente ou une communication anormale intracardiaque.",
      physiopathology = "L'écoulement laminaire devient turbulent dès lors que le nombre de Reynolds dépasse une valeur critique, produisant des vibrations perceptibles à l'auscultation.",
      etiologies = listOf(
        "Rétrécissement Aortique : souffle mésosystolique éjectionnel râpeux au foyer aortique irradiant aux carotides",
        "Insuffisance Mitrale : souffle holosystolique en jet de vapeur à l'apex irradiant vers l'aisselle",
        "Insuffisance Aortique : souffle holodiastolique doux humé decrescendo au foyer aortique et endapex",
        "Rétrécissement Mitral : roulement méso-télédiastolique à l'apex avec claquement d'ouverture mitrale"
      ),
      circumstances = "Découverte systématique lors d'un examen médical, bilan d'une dyspnée, d'une syncope, de fièvre prolongée (endocardite).",
      anamnesis = "Rechercher des antécédents de rhumatisme articulaire aigu (RAA), de valvulopathie connue, de cardiopathie congénitale ou de prothèse valvulaire.",
      searchMethod = "Auscultation méthodique des 4 foyers classiques : Aortique (2e EIC droit), Pulmonaire (2e EIC gauche), Tricuspide (bas du sternum), Mitral (apex, 5e EIC gauche sur la ligne médio-claviculaire).",
      physicalExam = "Caractériser le souffle selon 6 critères : Chronologie (systolique/diastolique), Timbre (râpeux, jet de vapeur, musical), Intensité (gradée de 1/6 à 6/6), Siège maximum, Irradiations et Variations avec la respiration.",
      associatedSigns = listOf("Frémissement cataire (palpation d'un souffle coté >= 4/6)", "Modification des bruits du cœur B1 et B2", "Élargissement de la pression pulsée (PA différentielle élargie dans l'IA)"),
      clinicalSignificance = "Oriente vers l'atteinte anatomique de la valve cardiaque et sa sévérité hémodynamique.",
      differentialDiagnoses = listOf(
        "Souffle anorganique / fonctionnel de débit (anémie, grossesse, fièvre, hyperthyroïdie : toujours systolique et d'intensité <= 2/6)",
        "Frottement péricardique (bruit superficiel de va-et-vient en cuir neuf non lié aux bruits valvulaires)"
      ),
      complementaryExams = listOf(
        "Échocardiographie doppler transthoracique (ETT) : examen de référence absolu",
        "ECG 12 dérivations (hypertrophie ventriculaire gauche, fibrillation atriale)",
        "Hémocultures si fièvre associée (éliminer une endocardite infectieuse d'Osler)"
      ),
      gravitySigns = listOf("Tout souffle diastolique est TOUJOURS pathologique !", "Abolition de B2 dans le rétrécissement aortique serré", "Apparition brutale d'un souffle systolique au décours d'un infarctus (rupture de pilier ou CIV)"),
      pitfallsAndLimits = "Un souffle peu intense n'exclut pas une valvulopathie sévère (notamment dans l'insuffisance mitrale aiguë ou le rétrécissement aortique calcifié serré avec bas débit).",
      keyPoints = listOf(
        "Souffle systolique : éjectionnel (aortique) ou de régurgitation (mitral/tricuspide).",
        "Souffle diastolique : TOUJOURS pathologique, jamais anorganique !",
        "L'ETT confirme l'anatomie valvulaire, la surface d'orifice et le retentissement."
      )
    ),

    // 6. Syndrome d'insuffisance cardiaque
    SemiologySheet(
      id = "cv_syndrome_ic",
      name = "Syndrome d'Insuffisance Cardiaque Globale",
      category = SemiologyCategory.CARDIOVASCULAR,
      subCategory = "Syndromes",
      definition = "Syndrome clinique complexe résultant de l'incapacité du cœur à assurer un débit sanguin suffisant pour subvenir aux besoins métaboliques de l'organisme, ou ne le faisant qu'au prix d'une augmentation anormale des pressions de remplissage ventriculaires.",
      physiopathology = "Dysfonction systolique (baisse de contractilité) ou diastolique (défaut de relaxation/compliance). Activation neuro-hormonale compensatrice délétère à long terme : système rénine-angiotensine-aldostérone et système sympathique entraînant vasoconstriction et rétention hydrosodée.",
      etiologies = listOf(
        "Cardiopathie ischémique (antécédent d'infarctus du myocarde)",
        "Cardiopathie hypertensive avec remodelage ventriculaire",
        "Cardiomyopathies dilatées primitives ou toxiques (alcool, chimiothérapies)",
        "Valvulopathies décompensées (sténose aortique, fuite mitrale)",
        "Troubles du rythme chroniques (tachycardiomyopathie sur FA)"
      ),
      circumstances = "Installation progressive avec limitation d'effort ou décompensation aiguë déclenchée par un écart de régime sans sel, une infection intercurrente ou un arrêt de traitement.",
      anamnesis = "Association de signes gauches (dyspnée d'effort, orthopnée, DPN) et de signes droits (hépatalgie d'effort, pesanteur abdominale, prise de poids rapide).",
      searchMethod = "Recherche conjointe des signes d'aval (fatigabilité, hypotension, altération rénale) et des signes d'amont (congestion pulmonaire et périphérique).",
      physicalExam = "Association : Galop B3, crépitants pulmonaires bilatéraux, turgescence jugulaire, reflux hépato-jugulaire, hépatomégalie et œdèmes des membres inférieurs prenant le godet.",
      associatedSigns = listOf("Prise de 2 à 4 kg en quelques jours", "Oligurie", "Épanchement pleural transsudatif (souvent bilatéral ou droit)", "Cachexie cardiaque au stade terminal"),
      clinicalSignificance = "Affection fréquente, grevée d'une lourde morbi-mortalité nécessitant une prise en charge médicale codifiée.",
      differentialDiagnoses = listOf(
        "Syndrome néphrotique (œdèmes généralisés avec protéinurie massive)",
        "Cirrhose hépatique décompensée (ascite prédominante avec insuffisance hépato-cellulaire)",
        "Insuffisance veineuse chronique (œdèmes vespéraux sans turgescence jugulaire)"
      ),
      complementaryExams = listOf(
        "Dosage des peptides natriurétiques (BNP / NT-proBNP)",
        "Échocardiographie transthoracique (ETT) : fraction d'éjection (ICFEr vs ICFEp)",
        "Bilan rénal et ionogramme (créatinine, kaliémie, natrémie)",
        "Radiographie thoracique (cardiomégalie avec index cardiothoracique > 0.50)"
      ),
      gravitySigns = listOf("Choc cardiogénique avec PAM < 65 mmHg", "OAP résistant aux diurétiques", "Hyponatrémie de dilution sévère (< 130 mmol/L)", "Insuffisance rénale aiguë fonctionnelle cardio-rénale"),
      pitfallsAndLimits = "L'absence d'œdèmes des membres inférieurs n'élimine pas l'insuffisance cardiaque, particulièrement chez le sujet jeune alité où la rétention prédomine au niveau sacré.",
      keyPoints = listOf(
        "Association typique : signes congestifs gauches (poumon) et droits (foie, jugulaires, OMI).",
        "Rechercher systématiquement le facteur déclenchant de la poussée.",
        "Le dosage du BNP et l'ETT sont les piliers diagnostiques incontournables."
      )
    )
  )
}
