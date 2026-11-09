package com.example.data.model.sheets

import com.example.data.model.SemiologyCategory
import com.example.data.model.SemiologySheet

object SemiologyDigestiveSheets {

  val sheets: List<SemiologySheet> = listOf(
    // 1. Défense et Contracture abdominale
    SemiologySheet(
      id = "dig_defense_contracture",
      name = "Défense et Contracture Abdominale (Péritonite)",
      category = SemiologyCategory.DIGESTIVE,
      subCategory = "Signes physiques",
      definition = "La défense est une tension musculaire réflexe de la paroi abdominale déclenchée par la palpation, réductible à la palpation douce. La contracture est une rigidité musculaire pariétale involontaire, permanente, invincible, douloureuse ('ventre de bois').",
      physiopathology = "Irritation du péritoine pariétal par du suc gastrique, du pus, de la bile ou des matières fécales, déclenchant un arc réflexe viscéro-moteur spinal entre les afférences sympathiques et les motoneurones des muscles larges de l'abdomen.",
      etiologies = listOf(
        "Perforation d'ulcère gastroduodénal (péritonite chimique brutale)",
        "Appendicite aiguë perforée ou gangrénée (péritonite purulente)",
        "Sigmoïdite diverticulaire perforée",
        "Cholécystite aiguë gangrénée",
        "Infarctus mésentérique au stade de nécrose"
      ),
      circumstances = "Douleur abdominale aiguë intense d'apparition brutale ou faisant suite à une douleur localisée s'étendant à tout l'abdomen.",
      anamnesis = "Heure précise du début, prise d'AINS ou corticoïdes (ulcère perforé), fièvre associée, vomissements.",
      searchMethod = "Patient en décubitus dorsal strict, cuisses semi-fléchies, bras le long du corps. Mains de l'examinateur réchauffées, posées à plat sur l'abdomen, palpant doucement de la zone indolore vers la zone douloureuse.",
      physicalExam = "Défense : résistance élastique vaincue par une pression douce et progressive. Contracture : impossibilité totale d'enfoncer la main même en faisant respirer le patient ('ventre de bois'). Disparition de la respiration abdominale.",
      associatedSigns = listOf("Cri du Douglas au toucher rectal (douleur vive du cul-de-sac péritonéal)", "Disparition de la matité pré-hépatique (présence d'air sous le diaphragme = pneumopéritoine)", "Faciès péritonéal altéré et tachycardie"),
      clinicalSignificance = "La contracture abdominale est le signe pathognomonique de la péritonite aiguë généralisée. C'est une URGENCE CHIRURGICALE ABSOLUE.",
      differentialDiagnoses = listOf(
        "Simple douleur pariétale ou hématome des grands droits",
        "Colique néphrétique ou hépatique (abdomen souple sans vraie défense)",
        "Acidocétose diabétique inaugurale chez l'enfant",
        "Insuffisance surrénalienne aiguë"
      ),
      complementaryExams = listOf(
        "Scanner abdomino-pelvien injecté (TDM) : examen de référence pour identifier la brèche digestive et l'épanchement péritonéal",
        "NFS (hyperleucocytose à PNN franche)",
        "CRP, ionogramme, bilan de coagulation pré-opératoire",
        "Radiographie de thorax de face debout (recherche de croissant gazeux sous-diaphragmatique)"
      ),
      gravitySigns = listOf("Choc septique péritonéal (hypotension, marbrures, oligurie)", "Défaillance multiviscérale", "Acidose lactique"),
      pitfallsAndLimits = "Chez le sujet très âgé ou immunodéprimé, la contracture peut être discrète voire absente, remplacée par un météorisme diffus et une altération hémodynamique insidieuse.",
      keyPoints = listOf(
        "Défense = tension douloureuse réductible à la palpation douce.",
        "Contracture = rigidité invincible permanente ('ventre de bois') = péritonite.",
        "Urgence chirurgicale absolue : bloc opératoire et antibiothérapie IV immédiats."
      )
    ),

    // 2. Ictère
    SemiologySheet(
      id = "dig_ictere",
      name = "Ictère (Jaunisse)",
      category = SemiologyCategory.DIGESTIVE,
      subCategory = "Signes physiques",
      definition = "Coloration jaune des téguments et des muqueuses (conjonctives bulbaires en premier lieu) due à une imprégnation tissulaire par la bilirubine lorsque sa concentration plasmatique dépasse 40-50 µmol/L (norme < 17 µmol/L).",
      physiopathology = "Excès de production de bilirubine non conjuguée (hémolyse), défaut de conjugaison hépatique, ou obstacle à l'excrétion biliaire de bilirubine conjuguée (cholestase mécanique ou intrahépatique).",
      etiologies = listOf(
        "Ictère à bilirubine conjuguée (cholestatique) : Lithiase de la voie biliaire principale, Cancer de la tête du pancréas, Cholangiocarcinome",
        "Ictère hépatocellulaire : Hépatites aiguës virales (A, B, E), hépatite médicamenteuse toxique (paracétamol), cirrhose décompensée",
        "Ictère à bilirubine libre (non conjuguée) : Anémies hémolytiques (auto-immune, drépanocytose, paludisme), Maladie de Gilbert (déficit partiel bénin en glucuronosyltransférase)"
      ),
      circumstances = "Découverte à la lumière naturelle du jour, associée ou non à des urines foncées, des selles décolorées ou un prurit.",
      anamnesis = "Recherche de la triade de Charcot : Douleur biliaire -> Fièvre avec frissons -> Ictère apparaissant en 24-48h (signe cardinal de l'angiocholite aiguë). Prurit précurseur, urines acajou couleur bière brune, selles mastic décolorées.",
      searchMethod = "Examen attentif des sclérotiques à la lumière naturelle en tirant la paupière inférieure vers le bas, puis de la face inférieure de la langue et de l'ensemble de la peau.",
      physicalExam = "Rechercher une grosse vésicule biliaire palpable tendue et indolore (loi de Courvoisier-Terrier : évoque une tumeur de la tête du pancréas), une hépatomégalie, une splénomégalie (hémolyse ou HTP).",
      associatedSigns = listOf("Prurit généralisé féroce avec lésions de grattage (cholestase)", "Selles décolorées blanc mastic", "Urines foncées mousseuses", "Asthénie et nausées"),
      clinicalSignificance = "L'ictère fébrile avec douleur (angiocholite) est une urgence médico-chirurgicale vitale par choc septique biliaire.",
      differentialDiagnoses = listOf(
        "Caroténodermie (coloration jaune-orangée des paumes et plantes par excès d'apport en bêta-carotènes, mais les CONJONCTIVES restent strictement blanches)",
        "Teint cireux de l'anémie sévère"
      ),
      complementaryExams = listOf(
        "Bilan hépatique complet : Bilirubine totale, libre et conjuguée, PAL, GGT, ASAT, ALAT",
        "Échographie abdominale hépatobiliaire en urgence : recherche de dilatation des voies biliaires et calcul",
        "Scanner abdominal ou Bili-IRM si suspicion tumorale ou calcul enclavé",
        "NFS avec réticulocytes et haptoglobine si suspicion d'hémolyse"
      ),
      gravitySigns = listOf("Angiocholite aiguë suppurée (fièvre 40°C + frissons + choc)", "Ictère de l'hépatite fulminante avec chute du TP (< 50%) et encéphalopathie hépatique"),
      pitfallsAndLimits = "Ne jamais examiner un ictère sous lumière artificielle jaune ou tamisée, qui masque les ictères modérés débutants.",
      keyPoints = listOf(
        "Examen impératif des conjonctives à la lumière naturelle du jour.",
        "Selles blanches + Urines foncées = cholestase (obstacle biliaire).",
        "Triade de Charcot (Douleur -> Fièvre -> Ictère) = Angiocholite aiguë (urgence vitale)."
      )
    ),

    // 3. Ascite et Signe du Flot
    SemiologySheet(
      id = "dig_ascite",
      name = "Ascite et Signes Physiques d'Épanchement Péritonéal",
      category = SemiologyCategory.DIGESTIVE,
      subCategory = "Signes physiques",
      definition = "Accumulation anormale de liquide libre dans la cavité péritonéale, perceptible cliniquement à partir d'un volume de 1 à 2 litres.",
      physiopathology = "Hypertension portale sous-hépatique ou intrahépatique entraînant une hyperpression sinusoïdale et une vasodilatation splanchnique, associée à une rétention hydrosodée rénale et une hypoalbuminémie (cirrhose). Ou carcinose péritonéale par exsudation tumorale.",
      etiologies = listOf(
        "Cirrhose hépatique décompensée (cause la plus fréquente : 80% des cas)",
        "Carcinose péritonéale (cancers de l'ovaire, estomac, côlon, pancréas)",
        "Insuffisance cardiaque droite ou globale sévère",
        "Tuberculose péritonéale",
        "Syndrome néphrotique sévère (anasarque)"
      ),
      circumstances = "Augmentation progressive du périmètre abdominal, prise de poids rapide, distension abdominale gênante.",
      anamnesis = "Antécédents d'éthylisme chronique, d'hépatite virale B ou C, hernie ombilicale sous tension.",
      searchMethod = "Patient en décubitus dorsal. Inspection, percussion rayonnée depuis l'appendice xiphoïde vers les flancs, recherche du signe du flot et du signe du glaçon.",
      physicalExam = "1. Matité déclive des flancs à limite supérieure concave vers le haut, mobile lors des changements de position (décubitus latéral). 2. Signe du flot : une chiquenaude sur un flanc transmet une onde liquidienne perçue par la paume opposée (la main d'un tiers étant posée au milieu pour bloquer la transmission pariétale adipeuse). 3. Signe du glaçon : dépression brusque de la paroi au-dessus du foie le fait plonger dans le liquide puis remonter heurter les doigts.",
      associatedSigns = listOf("Déplissement et hernie de l'ombilic sous tension", "Circulation veineuse collatérale abdominale", "Œdèmes des membres inférieurs associés", "Épanchement pleural droit associé (hydrothorax hépatique)"),
      clinicalSignificance = "Signe cardinal de décompensation hépatique ou de dissémination néoplasique intrapéritonéale.",
      differentialDiagnoses = listOf(
        "Obésité abdominale (sonorité tympanique diffuse sans matité déclive)",
        "Volumineux globe vésical (matité médiane convexe vers le haut)",
        "Kyste géant de l'ovaire (matité antérieure médiane convexe vers le haut)"
      ),
      complementaryExams = listOf(
        "Ponction d'ascite exploratrice systématique (cytologie, protides, bactériologie)",
        "Recherche d'infection spontanée du liquide d'ascite (ISLA si PNN > 250/mm³)",
        "Échographie abdominale : visualise le liquide dès 100 ml et guide la ponction",
        "Calcul du gradient albumine sérum - ascite (GASA >= 11 g/L = hypertension portale)"
      ),
      gravitySigns = listOf("Infection du liquide d'ascite (fièvre, douleurs abdominales, encéphalopathie : urgence vitale !)", "Rupture de hernie ombilicale sous tension", "Syndrome hépato-rénal"),
      pitfallsAndLimits = "Toute première décompensation ascitique ou toute aggravation chez un cirrhotique impose une PONCTION D'ASCITE exploratrice immédiate pour éliminer une infection !",
      keyPoints = listOf(
        "Matité des flancs déclive, mobile aux changements de position.",
        "Signe du flot et signe du glaçon.",
        "Toute ascite impose une ponction exploratrice avec comptage des PNN (ISLA si > 250/mm³)."
      )
    ),

    // 4. Syndrome Occlusif
    SemiologySheet(
      id = "dig_occlusion",
      name = "Syndrome Occlusif Intestinal",
      category = SemiologyCategory.DIGESTIVE,
      subCategory = "Syndromes",
      definition = "Arrêt mécanique ou fonctionnel du transit intestinal des gaz et des matières fécales.",
      physiopathology = "Obstruction intraluminale, compression extrinsèque (bride, carcinose), strangulation vasculaire (volvulus, hernie étranglée) ou paralysie réflexe du péristaltisme (iléus paralytique réflexe).",
      etiologies = listOf(
        "Occlusion du grêle sur bride post-opératoire (cause la plus fréquente)",
        "Hernie pariétale ou crurale étranglée (urgence chirurgicale)",
        "Cancer colorectal obstructif (occlusion colique basse)",
        "Volvulus du côlon sigmoïde ou du cæcum",
        "Invagination intestinale aiguë du nourrisson"
      ),
      circumstances = "Apparition brutale (strangulation) ou progressive (obstruction tumorale) de douleurs abdominales.",
      anamnesis = "Rechercher la tétralogie cardinale : 1. Douleur abdominale paroxystique à type de colique, 2. Vomissements précoces (grêle) ou tardifs fécaloïdes (côlon), 3. ARRÊT DES MATIÈRES ET DES GAZ (signe le plus précoce et constant : l'arrêt des gaz est pathognomonique), 4. Météorisme abdominal.",
      searchMethod = "Vérification systématique et obligatoire des orifices herniaires (inguinaux, cruraux, ombilicaux et cicatrices de laparotomie) !",
      physicalExam = "Inspection : météorisme abdominal localisé ou diffus, ondulations péristaltiques visibles sous la peau. Percussion : tympanisme diffus. Auscultation : bruits hydro-aériques métalliques et augmentés au début ('bruit de lutte'), puis silence sépulcral. Palpation d'une hernie douloureuse non réductible et non impulsive à la toux.",
      associatedSigns = listOf("Déshydratation extracellulaire par troisième secteur digestif", "Tachycardie et oligurie", "Toucher rectal : ampoule rectale vide"),
      clinicalSignificance = "L'occlusion par strangulation engage le pronostic de vitalité de l'anse en quelques heures (ischémie puis perforation).",
      differentialDiagnoses = listOf(
        "Iléus fonctionnel réflexe (colique néphrétique, pancréatite aiguë, foyer infectieux intrapéritonéal)",
        "Gastro-entérite aiguë (vomissements et douleurs mais diarrhée et gaz conservés)",
        "Constipation opiniâtre ou fécalome (sans arrêt des gaz)"
      ),
      complementaryExams = listOf(
        "Scanner abdomino-pelvien injecté (TDM) sans et avec injection : examen clé qui localise le siège, identifie la cause et évalue la souffrance vasculaire pariétale",
        "Ionogramme sanguin et bilan rénal (recherche d'alcalose hypokaliémique et déshydratation)",
        "Lactates artériels (témoin précoce d'ischémie mésentérique)"
      ),
      gravitySigns = listOf("HERNIE ÉTRANGLÉE NON RÉDUCTIBLE (intervention chirurgicale en extrême urgence)", "Signes de souffrance digestive au scanner (défaut de rehaussement, pneumatose pariétale)", "Défense localisée ou contracture (péritonite par perforation)"),
      pitfallsAndLimits = "Ne jamais oublier d'examiner les orifices herniaires (surtout chez la personne âgée : hernie crurale passée inaperçue) !",
      keyPoints = listOf(
        "Tétralogie : Douleurs + Vomissements + Arrêt des gaz et matières + Météorisme.",
        "L'arrêt des gaz est le signe sémiologique le plus précoce et fidèle.",
        "Palpation impérative des orifices herniaires devant tout syndrome occlusif."
      )
    )
  )
}
