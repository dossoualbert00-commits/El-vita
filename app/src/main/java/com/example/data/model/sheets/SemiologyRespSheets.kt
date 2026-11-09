package com.example.data.model.sheets

import com.example.data.model.SemiologyCategory
import com.example.data.model.SemiologySheet

object SemiologyRespSheets {

  val sheets: List<SemiologySheet> = listOf(
    // 1. Toux et Hémoptysie
    SemiologySheet(
      id = "resp_hemoptysie",
      name = "Hémoptysie",
      category = SemiologyCategory.RESPIRATORY,
      subCategory = "Signes fonctionnels",
      definition = "Rejet par la bouche, dans un effort de toux, de sang rouge vif aéré et spumeux provenant des voies aériennes sous-glottiques.",
      physiopathology = "Rupture de vaisseaux de la circulation bronchique systémique (hypervascularisation sous l'effet de l'inflammation chronique) ou plus rarement de la circulation pulmonaire à basse pression (érosion artérielle).",
      etiologies = listOf(
        "Cancer bronchique primitif (adulte tabagique)",
        "Dilatation des bronches (DDB / bronchectasies)",
        "Tuberculose pulmonaire active ou séquellaire (aspergillome sur caverne)",
        "Embolie pulmonaire avec infarctus pulmonaire",
        "Pneumopathie infectieuse nécrosante (abcès pulmonaire, Klebsiella pneumoniae)"
      ),
      circumstances = "Survenant au cours d'un effort de toux, souvent précédée de chatouillements laryngés, d'angoisse et d'un goût métallique dans la bouche.",
      anamnesis = "Quantifier le volume (minime < 50 ml, moyenne abondance 50-200 ml, massive > 200 ml/24h), rechercher des antécédents tabagiques, de tuberculose ou de phlébite.",
      searchMethod = "Interrogatoire précis, examen bucco-pharyngé pour éliminer une épistaxis déglutie ou une gingivorragie.",
      physicalExam = "Rechercher des signes de détresse respiratoire, une polypnée, une asymétrie auscultatoire, des signes de thrombose veineuse profonde (OMI unilatéral douloureux).",
      associatedSigns = listOf("Toux quinteuse", "Point de côté thoracique", "Altération de l'état général et amaigrissement", "Fièvre au long cours avec sueurs nocturnes"),
      clinicalSignificance = "Symptôme d'alarme absolu en pneumologie imposant une enquête étiologique hospitalière immédiate.",
      differentialDiagnoses = listOf(
        "Hématémèse (rejet de sang noir digéré lors d'un effort de vomissement avec débris alimentaires et pH acide)",
        "Épistaxis postérieure déglutie rejetée à la toux",
        "Hémorragie buccale ou linguale"
      ),
      complementaryExams = listOf(
        "Scanner thoracique injecté avec protocole angioscanner (examen clé : cartographie artérielle bronchique)",
        "Endoscopie bronchique (fibroscopie souple) pour visualiser le saignement et aspirer",
        "NFS, groupe sanguin, Rhésus, RAI, hémostase (TP, TCA)",
        "Recherche de BK (bacille de Koch) dans les expectorations"
      ),
      gravitySigns = listOf("Volume > 200 ml en une prise ou > 500 ml/24h", "Détresse respiratoire avec hypoxie et encombrement bronchique asphyxique", "Choc hémorragique"),
      pitfallsAndLimits = "Le danger immédiat de l'hémoptysie n'est pas le choc hémorragique mais l'asphyxie par inondation de l'arbre trachéo-bronchique !",
      keyPoints = listOf(
        "Sang rouge, aéré, spumeux, expulsé lors d'un effort de toux.",
        "Le risque majeur immédiat est l'asphyxie par inondation bronchique.",
        "Scanner thoracique injecté et angioscanner en extrême urgence."
      )
    ),

    // 2. Râles crépitants et Auscultation
    SemiologySheet(
      id = "resp_crepitants_complet",
      name = "Râles Crépitants et Auscultation Alvéolaire",
      category = SemiologyCategory.RESPIRATORY,
      subCategory = "Signes physiques",
      definition = "Bruits adventices respiratoires anormaux, fins, discontinus, éclatants en fin d'inspiration (télé-inspiratoires), comparables au froissement d'une mèche de cheveux près de l'oreille ou au sel crépitant sur une poêle chaude.",
      physiopathology = "Ouverture explosive et brutale, lors de l'inspiration profonde, des alvéoles pulmonaires et petites bronchioles collabées par un exsudat inflammatoire (pneumonie) ou un transsudat liquidien (OAP).",
      etiologies = listOf(
        "Pneumopathie Franche Lobaire Aiguë (foyer de crépitants unilatéral et localisé)",
        "Œdème Aigu du Poumon cardiogénique (crépitants bilatéraux symétriques ascendants en marée montante)",
        "Fibrose pulmonaire idiopathique (crépitants bilatéraux secs type 'velcro' aux bases)",
        "Broncho-pneumopathie bactérienne ou virale"
      ),
      circumstances = "Auscultation systématique d'un patient fébrile, dyspnéique ou tousseur.",
      anamnesis = "Rechercher une fièvre brutale avec frissons, une douleur pleurétique unilatérale, une orthopnée ou une dyspnée d'effort récente.",
      searchMethod = "Auscultation symétrique et comparative des deux champs pulmonaires de haut en bas, patient respirant profondément la bouche ouverte.",
      physicalExam = "Foyer unilatéral fixe non modifié par la toux. Si OAP : débutent aux bases pulmonaires et progressent vers les sommets.",
      associatedSigns = listOf("Augmentation des vibrations vocales (si condensation)", "Matité franche à la percussion", "Souffle tubaire surajouté au centre du foyer", "Polypnée"),
      clinicalSignificance = "Affirme formellement l'atteinte de l'espace alvéolaire ou de l'interstitium pulmonaire.",
      differentialDiagnoses = listOf(
        "Râles sibilants (bruits musicaux continus expiratoires liés au rétrécissement bronchique)",
        "Râles ronchis ou sous-crépitants (bruits bronchiques grossiers modifiés par la toux)",
        "Frottement pleural (bruit superficiel de va-et-vient dans les deux temps respiratoires)"
      ),
      complementaryExams = listOf(
        "Radiographie thoracique de face (opacité alvéolaire avec bronchogramme aérique ou syndrome alvéolo-interstitiel bilatéral)",
        "NFS (hyperleucocytose à PNN)",
        "Gazométrie artérielle si SpO2 basse"
      ),
      gravitySigns = listOf("Ascension rapide des râles aux apex (OAP suffocant)", "Cyanose et signes de lutte respiratoire", "Hypoxie sévère non corrigée par l'O2 à bas débit"),
      pitfallsAndLimits = "Les crépitants alvéolaires ne sont JAMAIS modifiés par la toux, contrairement aux râles bronchiques qui se déplacent ou s'atténuent.",
      keyPoints = listOf(
        "Crépitants = alvéolaires, fins, inspiratoires, non modifiés par la toux.",
        "Unilatéral fébrile = PFLA (pneumonie). Bilatéral ascendant = OAP.",
        "Toujours ausculter les faces latérales et les bases postérieures."
      )
    ),

    // 3. Syndrome de condensation pulmonaire
    SemiologySheet(
      id = "resp_condensation",
      name = "Syndrome de Condensation Pulmonaire Parenchymateuse",
      category = SemiologyCategory.RESPIRATORY,
      subCategory = "Syndromes",
      definition = "Ensemble des signes physiques traduisant la densification du parenchyme pulmonaire par le comblement des alvéoles par du pus, du sang, des cellules tumorales ou par l'atélectasie.",
      physiopathology = "Le poumon normalement aéré transmet mal les bruits glottiques. Lorsque les alvéoles se remplissent de matériel dense tout en gardant une bronche de drainage perméable, le parenchyme devient un conducteur acoustique optimal.",
      etiologies = listOf(
        "Pneumopathie Franche Lobaire Aiguë (Streptococcus pneumoniae)",
        "Infarctus pulmonaire post-embolique",
        "Atélectasie par obstruction bronchique proximale (cancer, corps étranger)",
        "Abcès du poumon avant vomique"
      ),
      circumstances = "Tableau fébrile aigu avec frissons, toux productive et douleur thoracique en point de côté.",
      anamnesis = "Début brutal, expectorations rouillées caractéristiques de la pneumonie franche, toux douloureuse.",
      searchMethod = "Réalisation méthodique des 4 temps de l'examen physique : Inspection, Palpation, Percussion, Auscultation.",
      physicalExam = "La triade sémiologique classique associe : 1. Augmentation de la transmission des vibrations vocales à la palpation ('dire 33'), 2. Matité franche à la percussion, 3. Auscultation : souffle tubaire entouré d'une couronne de râles crépitants.",
      associatedSigns = listOf("Fièvre élevée à 39-40°C", "Polypnée superficielle", "Herpès naso-labial", "Tachycardie sinusale"),
      clinicalSignificance = "Signe cardinal de la pneumonie bactérienne alvéolaire systématisée de l'adulte.",
      differentialDiagnoses = listOf(
        "Syndrome pleural liquidien (matité mais ABOLITION des vibrations vocales et du murmure vésiculaire)",
        "Pneumothorax (tympanisme avec abolition des vibrations vocales)"
      ),
      complementaryExams = listOf(
        "Radiographie pulmonaire (opacité systématisée non rétractile à bronchogramme aérique)",
        "NFS et CRP (syndrome inflammatoire biologique marqué)",
        "Antigénuries solubles légionnelle et pneumocoque"
      ),
      gravitySigns = listOf("Score CRB65 >= 1", "Atteinte multiviscérale ou bilatérale", "Troubles de conscience chez le sujet âgé"),
      pitfallsAndLimits = "Si la bronche est obstruée (atélectasie), les vibrations vocales sont abolished et le souffle tubaire disparaît : c'est la condensation non perméable.",
      keyPoints = listOf(
        "Triade : Augmentation des vibrations vocales + Matité + Souffle tubaire et crépitants.",
        "Permet de distinguer formellement la condensation d'un épanchement pleural liquide.",
        "Radiographie thoracique obligatoire pour confirmer le lobe atteint."
      )
    ),

    // 4. Syndrome pleural liquidien
    SemiologySheet(
      id = "resp_pleural_liquidien",
      name = "Syndrome Pleural Liquidien (Pleurésie)",
      category = SemiologyCategory.RESPIRATORY,
      subCategory = "Syndromes",
      definition = "Ensemble des manifestations cliniques traduisant la présence anormale de liquide (transsudat ou exsudat) dans la cavité pleurale entre les deux feuillets.",
      physiopathology = "Déséquilibre entre production et réabsorption du liquide pleural par augmentation de la pression hydrostatique (insuffisance cardiaque), baisse de la pression oncotique (syndrome néphrotique) ou perméabilité accrue (infection, néoplasie).",
      etiologies = listOf(
        "Transsudats (liquide clair, protéines < 30 g/L) : Insuffisance cardiaque congestive, cirrhose décompensée, syndrome néphrotique",
        "Exsudats (liquide riche en protéines > 30 g/L) : Pleurésie purulente ou parapneumonique, cancer bronchique ou métastase pleurale, tuberculose pleurale, embolie pulmonaire"
      ),
      circumstances = "Dyspnée d'effort d'installation progressive, toux sèche survenant aux changements de position, douleur basithoracique unilatérale.",
      anamnesis = "Toux sèche provoquée par le décubitus controlatéral, antécédents néoplasiques ou d'insuffisance cardiaque.",
      searchMethod = "Inspection, palpation des vibrations vocales, percussion des bases pulmonaires et auscultation comparative.",
      physicalExam = "Triade sémiologique caractéristique : 1. Abolition des vibrations vocales à la palpation, 2. Matité déclive, franche, 'de bois', à limite supérieure concave vers le haut (courbe de Damoiseau), 3. Abolition du murmure vésiculaire à l'auscultation avec souffle pleurétique doux et voilé au sommet.",
      associatedSigns = listOf("Frottement pleural initial avant constitution de l'épanchement", "Égophonie (voix de chèvre au sommet de l'épanchement)", "Immobilité de l'hémithorax atteint"),
      clinicalSignificance = "Toute pleurésie d'étiologie indéterminée impose une ponction pleurale exploratrice immédiate.",
      differentialDiagnoses = listOf(
        "Syndrome de condensation pulmonaire (vibrations vocales AUGMENTÉES)",
        "Pachypeurite fibreuse séquellaire",
        "Paralysie phrénique avec ascension de coupole diaphragmatique"
      ),
      complementaryExams = listOf(
        "Ponction pleurale exploratrice avec analyse biochimique (protéines, LDH), cytologique et bactériologique",
        "Radiographie de thorax de face (opacité homogène dense déclive effaçant les culs-de-sac)",
        "Échographie pleurale (guide la ponction et quantifie le volume)"
      ),
      gravitySigns = listOf("Épanchement massif refoulant le médiastin (urgence compressive)", "Pleurésie purulente septique (empyème)", "Détresse respiratoire avec hypoxie"),
      pitfallsAndLimits = "Ne jamais ponctionner sans cliché préalable ou échoguidage en cas d'épanchement minime cloisonné.",
      keyPoints = listOf(
        "Triade physique : Abolition des vibrations vocales + Matité franche déclive + Silence auscultatoire.",
        "Ponction pleurale diagnostique indispensable devant tout exsudat.",
        "Distinction clé : transsudat (cause générale) vs exsudat (cause pleurale locale)."
      )
    ),

    // 5. Syndrome pleural aérien (Pneumothorax)
    SemiologySheet(
      id = "resp_pneumothorax",
      name = "Syndrome Pleural Aérien (Pneumothorax)",
      category = SemiologyCategory.RESPIRATORY,
      subCategory = "Syndromes",
      definition = "Présence anormale d'air dans la cavité pleurale entraînant un collapsus partiel ou total du poumon sous-jacent vers le hile.",
      physiopathology = "Brèche du feuillet viscéral (rupture de bulles ou blebs sous-pleurales) ou pariétal (traumatisme). L'air pénètre dans la cavité, annulant la pression négative intrapleurale nécessaire à l'expansion pulmonaire.",
      etiologies = listOf(
        "Pneumothorax spontané primitif (sujet jeune, longiligne, fumeur)",
        "Pneumothorax spontané secondaire (emphysème, BPCO, mucoviscidose, fibrose)",
        "Pneumothorax traumatique (plaie pénétrante, fracture de côtes)",
        "Pneumothorax iatrogène (pose de voie veineuse centrale, ponction pleurale, biopsie)"
      ),
      circumstances = "Survenue brutale au repos ou lors d'un effort modéré, souvent chez un homme jeune.",
      anamnesis = "Douleur thoracique unilatérale brutale en coup de poignard, anxiété, dyspnée d'intensité proportionnelle à la taille du pneumothorax.",
      searchMethod = "Percussion délicate de l'hémithorax douloureux et auscultation comparative.",
      physicalExam = "Triade classique de Gaillard : 1. Abolition des vibrations vocales, 2. Tympanisme franc à la percussion (sonorité exagérée 'en tambour'), 3. Abolition du murmure vésiculaire à l'auscultation (silence respiratoire complet).",
      associatedSigns = listOf("Distension de l'hémithorax atteint avec diminution de son amplification", "Toux sèche irritative quinteuse", "Emphysème sous-cutané crépitant au cou ou au thorax"),
      clinicalSignificance = "Urgence diagnostique. Le pneumothorax compressif sous tension engage le pronostic vital par collapsus cardiovasculaire.",
      differentialDiagnoses = listOf(
        "Pleurésie liquidienne (matité à la percussion et non tympanisme)",
        "Embolie pulmonaire (douleur brutale mais examen pleuropulmonaire normal)",
        "Grosse bulle d'emphysème unilatérale"
      ),
      complementaryExams = listOf(
        "Radiographie de thorax de face en INSPIRATION (hyperclarté périphérique avasculaire avec moignon pulmonaire rétracté au hile)",
        "Échographie pleurale au lit du patient (disparition du glissement pleural)",
        "Scanner thoracique si doute diagnostique ou récidive"
      ),
      gravitySigns = listOf("PNEUMOTHORAX SOUS TENSION (détournement médiastinal, turgescence jugulaire, collapsus hémodynamique : EXSUFFLATION IMMÉDIATE à l'aiguille sans attendre la radio !)", "Pneumothorax bilatéral", "Insuffisance respiratoire aiguë chez le BPCO"),
      pitfallsAndLimits = "NE JAMAIS DEMANDER DE RADIOGRAPHIE EN EXPIRATION FORCÉE : obsolète et dangereux pouvant aggraver un état instable !",
      keyPoints = listOf(
        "Triade de Gaillard : Tympanisme + Abolition des vibrations vocales + Silence auscultatoire.",
        "Signe de gravité : pneumothorax compressif avec choc -> exsufflation à l'aiguille au 2e EIC sans attendre la radio.",
        "Radiographie thoracique de face en inspiration stricte."
      )
    )
  )
}
