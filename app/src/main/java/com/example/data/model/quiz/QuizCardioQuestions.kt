package com.example.data.model.quiz

import com.example.data.model.QuizQuestion
import com.example.data.model.SemiologyCategory

val quizCardioQuestions: List<QuizQuestion> = listOf(
    QuizQuestion(
        id = "qc_01",
        question = "Quelle caractéristique sémiologique oriente typiquement vers une douleur thoracique d'origine coronarienne angineuse ?",
        options = listOf(
            "Douleur rétro-sternale constrictive irradiant vers la mâchoire et le bras gauche",
            "Douleur augmentée à l'inspiration profonde et calmée par l'antéflexion",
            "Douleur punctiforme sous-mamelonnaire déclenchée par la palpation costale",
            "Douleur brutale migratrice descendante interscapulaire déchirante"
        ),
        correctOptionIndex = 0,
        explanation = "L'angor typique réalise une douleur rétro-sternale constrictive en étau, survenant à l'effort, durant moins de 15 minutes, cédant au repos ou sous trinitrine en moins de 3 minutes, avec irradiations ascendantes caractéristiques vers le bras gauche et la mâchoire.",
        wrongOptionsExplanations = listOf(
            "La douleur augmentée à l'inspiration et soulagée penché en avant est la signature classique de la péricardite aiguë.",
            "Une douleur punctiforme sous-mamelonnaire reproductible à la palpation évoque une douleur pariétale ou un syndrome de Tietze, sans caractère coronarien.",
            "Une douleur dorsale déchirante, brutale et migratrice vers les lombes signe typiquement une dissection aortique aiguë."
        ),
        category = SemiologyCategory.CARDIOVASCULAR
    ),
    QuizQuestion(
        id = "qc_02",
        question = "Quel signe clinique traduit directement une défaillance cardiaque droite lors de l'examen au lit du patient ?",
        options = listOf(
            "Reflux hépato-jugulaire et turgescence jugulaire spontanée",
            "Râles crépitants fins prédominant aux deux bases pulmonaires",
            "Respiration de Cheyne-Stokes",
            "Souffle holosystolique à la pointe irradiant dans l'aisselle"
        ),
        correctOptionIndex = 0,
        explanation = "Le reflux hépato-jugulaire (RHJ) et la turgescence des veines jugulaires reflètent une élévation de la pression veineuse centrale et de la pression de remplissage ventriculaire droite.",
        wrongOptionsExplanations = listOf(
            "Les râles crépitants bilatéraux témoignent d'une extravasation alvéolaire liée à une insuffisance cardiaque gauche.",
            "La respiration de Cheyne-Stokes est un trouble du rythme respiratoire central pouvant survenir dans l'insuffisance cardiaque sévère mais n'est pas spécifique du ventricule droit.",
            "Un souffle holosystolique à la pointe irradiant à l'aisselle correspond à une insuffisance mitrale."
        ),
        category = SemiologyCategory.CARDIOVASCULAR
    ),
    QuizQuestion(
        id = "qc_03",
        question = "À l'auscultation cardiaque, le bruit de galop B3 correspond à :",
        options = listOf(
            "Un remplissage ventriculaire passif rapide protodiastolique dans un ventricule dilaté et compliant",
            "Une contraction auriculaire télédiastolique contre un ventricule rigide et hypertrophié",
            "Un claquement d'ouverture de la valve mitrale en début de diastole",
            "Un dédoublement large et fixe du premier bruit B1 au foyer mitral"
        ),
        correctOptionIndex = 0,
        explanation = "Le galop protodiastolique B3 correspond à la décélération brutale du flux transmitral lors de la phase de remplissage passif rapide dans un ventricule dilaté à haute pression de remplissage. C'est un signe fidèle de défaillance systolique gauche.",
        wrongOptionsExplanations = listOf(
            "La contraction auriculaire active télédiastolique contre un myocarde rigide produit un galop présystolique B4.",
            "Le claquement d'ouverture mitrale est sec, bref et survient 0.08 à 0.12 s après B2 dans le rétrécissement mitral.",
            "Le dédoublement de B1 résulte d'un asynchronisme de fermeture mitro-tricuspidien et n'est pas un galop."
        ),
        category = SemiologyCategory.CARDIOVASCULAR
    ),
    QuizQuestion(
        id = "qc_04",
        question = "Quelle auscultation caractérise le souffle du rétrécissement aortique (RAO) serré ?",
        options = listOf(
            "Souffle méso-systolique éjectionnel rude, râpeux, râpeux au foyer aortique irradiant vers les carotides",
            "Souffle holodiastolique doux, lointain, humé au foyer aortique et bord gauche du sternum",
            "Souffle holosystolique en jet de vapeur à l'apex irradiant vers l'aisselle gauche",
            "Roulement diastolique avec renforcement présystolique et éclat de B1 au foyer mitral"
        ),
        correctOptionIndex = 0,
        explanation = "Le rétrécissement aortique provoque un souffle systolique d'éjection losangique (crescendo-decrescendo), rude et râpeux, maximal au 2e espace intercostal droit (foyer aortique) avec irradiation bilatérale vers les vaisseaux du cou (artères carotides).",
        wrongOptionsExplanations = listOf(
            "Le souffle holodiastolique doux et humé caractérise l'insuffisance aortique régurgitante.",
            "Le souffle holosystolique en jet de vapeur à l'apex irradiant vers l'aisselle correspond à l'insuffisance mitrale.",
            "Le roulement diastolique avec renforcement présystolique définit le rétrécissement mitral (triade de Duroziez)."
        ),
        category = SemiologyCategory.CARDIOVASCULAR
    ),
    QuizQuestion(
        id = "qc_05",
        question = "Dans la classification de la NYHA, quel stade correspond à une dyspnée apparaissant pour des efforts minimes de la vie quotidienne ?",
        options = listOf(
            "Stade III",
            "Stade I",
            "Stade II",
            "Stade IV"
        ),
        correctOptionIndex = 0,
        explanation = "Le stade III de la NYHA est défini par une limitation marquée de l'activité physique : le patient est confortable au repos, mais des efforts minimes (s'habiller, faire sa toilette, marcher sur terrain plat à allure normale) provoquent la dyspnée.",
        wrongOptionsExplanations = listOf(
            "Le stade I correspond à une cardiopathie sans limitation fonctionnelle lors des efforts habituels.",
            "Le stade II correspond à une limitation légère lors d'efforts physiques modérés (monter plus de 2 étages, marche rapide).",
            "Le stade IV correspond à une dyspnée permanente présente même au repos complet ou au moindre geste."
        ),
        category = SemiologyCategory.CARDIOVASCULAR
    ),
    QuizQuestion(
        id = "qc_06",
        question = "Qu'est-ce que le pouls paradoxal de Kussmaul ?",
        options = listOf(
            "Une diminution de la pression artérielle systolique > 10 mmHg lors de l'inspiration spontanée",
            "Une alternance de pulsations fortes et faibles traduisant une dysfonction myocardique sévère",
            "Une dissociation entre la fréquence cardiaque auscultée et le pouls radial perçu",
            "Une élévation paradoxale de la pression artérielle lors de l'inspiration profonde"
        ),
        correctOptionIndex = 0,
        explanation = "Le pouls paradoxal correspond à une baisse exagérée (> 10 mmHg) de la PAS à l'inspiration. Il traduit une interdépendance ventriculaire sévère causée par une contrainte péricardique (tamponnade cardiaque) ou bronchique sévère (asthme aigu grave).",
        wrongOptionsExplanations = listOf(
            "L'alternance d'ondes pulsatiles fortes et faibles sans modification respiratoire définit le pouls alternant de Traube.",
            "La différence entre pulsations centrales auscultées et pulsations périphériques palpées définit le déficit pulsatile, classique dans la FA.",
            "Une hausse de pression à l'inspiration n'est pas le pouls paradoxal."
        ),
        category = SemiologyCategory.CARDIOVASCULAR
    ),
    QuizQuestion(
        id = "qc_07",
        question = "Quel signe auscultatoire est pathognomonique de la péricardite aiguë ?",
        options = listOf(
            "Frottement péricardique superficiel, méso-cardiaque, persistant en apnée",
            "Dédoublement fixe du deuxième bruit B2 au foyer pulmonaire",
            "Frottement pleural disparaissant lors de la respiration calme",
            "Souffle continu systolo-diastolique en tunnel sous-claviculaire gauche"
        ),
        correctOptionIndex = 0,
        explanation = "Le frottement péricardique est un bruit surajouté superficiel, mésocardiaque, comparé au froissement de cuir neuf ou au crissement de la neige sous les pas. Il a pour caractéristique capitale de persister en apnée inspiratoire ou expiratoire (contrairement au frottement pleural).",
        wrongOptionsExplanations = listOf(
            "Le dédoublement fixe de B2 est le signe classique d'une communication interauriculaire (CIA) avec surcharge droite.",
            "Le frottement pleural s'annule totalement lors de l'apnée, ce qui permet de le distinguer formellement du frottement péricardique.",
            "Le souffle continu en tunnel sous-claviculaire gauche est caractéristique de la persistance du canal artériel."
        ),
        category = SemiologyCategory.CARDIOVASCULAR
    ),
    QuizQuestion(
        id = "qc_08",
        question = "Dans l'insuffisance aortique volumineuse, quel signe clinique périphérique recherche-t-on ?",
        options = listOf(
            "Élargissement de la différentielle tensionnelle avec pouls bondissant de Corrigan",
            "Pincement de la différentielle tensionnelle avec pouls filiforme petit",
            "Cyanose péribuccale isolée sans hippocratisme digital",
            "Turgescence jugulaire unilatérale sans reflux hépato-jugulaire"
        ),
        correctOptionIndex = 0,
        explanation = "L'insuffisance aortique sévère s'accompagne d'une régurgitation diastolique massive vers le VG, provoquant un effondrement de la PAD et une augmentation compensatrice de la PAS (élargissement de la pression pulsée), avec pouls bondissant et dépressible (pouls de Corrigan) et signe de Musset.",
        wrongOptionsExplanations = listOf(
            "Le pincement tensionnel (baisse de la PAS et maintien de la PAD) se rencontre au contraire dans le choc cardiogénique ou la tamponnade.",
            "La cyanose péribuccale isolée évoque une hypoxémie ou un shunt droite-gauche.",
            "La turgescence jugulaire unilatérale évoque une compression locale ou une thrombose de la veine jugulaire interne."
        ),
        category = SemiologyCategory.CARDIOVASCULAR
    ),
    QuizQuestion(
        id = "qc_09",
        question = "Quelle manœuvre clinique permet d'augmenter l'intensité des souffles du cœur droit ?",
        options = listOf(
            "Manœuvre de Rivero-Carvallo (inspiration profonde)",
            "Manœuvre de Valsalva en expiration forcée glotte fermée",
            "Passage brutal en position orthostatique",
            "Manœuvre de Müller après expiration complète"
        ),
        correctOptionIndex = 0,
        explanation = "Le signe de Rivero-Carvallo désigne l'accentuation des souffles cardiaques droits (insuffisance tricuspide, rétrécissement pulmonaire) lors de l'inspiration profonde, provoquée par l'augmentation du retour veineux dans les cavités droites sous l'effet de la dépression intra-thoracique.",
        wrongOptionsExplanations = listOf(
            "La manœuvre de Valsalva diminue le retour veineux et atténue la majorité des souffles, à l'exception de la cardiomyopathie hypertrophique et du prolapsus mitral.",
            "L'orthostatisme diminue le retour veineux et réduit le volume des cavités cardiaques.",
            "La manœuvre de Müller est une inspiration contre résistance fermée, non utilisée en routine sémiologique auscultatoire."
        ),
        category = SemiologyCategory.CARDIOVASCULAR
    ),
    QuizQuestion(
        id = "qc_10",
        question = "L'œdème aigu du poumon (OAP) cardiogénique se manifeste cliniquement par :",
        options = listOf(
            "Orthopnée brutale, polypnée angoissante, expectoration rose saumonée spumeuse et râles crépitants en marée montante",
            "Bradypnée expiratoire sifflante avec râles sibilants diffus sans râles crépitants",
            "Dyspnée inspiratoire avec stridor laryngé et cornage sans expectoration",
            "Douleur basithoracique unilatérale brutale avec silence auscultatoire localisé"
        ),
        correctOptionIndex = 0,
        explanation = "L'OAP cardiogénique résulte de l'inondation alvéolaire brutale secondaire à l'élévation critique de la pression capillaire pulmonaire (> 18-20 mmHg). Il associe orthopnée majeure, anxiété, toux ramenant un liquide rose saumoné spumeux et râles crépitants fins montant des bases vers les sommets.",
        wrongOptionsExplanations = listOf(
            "La bradypnée expiratoire avec râles sibilants purs caractérise la crise d'asthme bronchique aigu.",
            "La dyspnée inspiratoire avec stridor et cornage traduit un obstacle laryngé ou trachéal supérieur.",
            "Le silence auscultatoire unilatéral brutal oriente vers un pneumothorax ou une pleurésie."
        ),
        category = SemiologyCategory.CARDIOVASCULAR
    ),
    QuizQuestion(
        id = "qc_11",
        question = "Quel examen complémentaire de première intention est indispensable pour confirmer une nécrose myocardique aiguë ?",
        options = listOf(
            "Dosage de la troponine cardiaque ultrasensible (cTn-us) et ECG 12 dérivations",
            "Dosage des D-Dimères plasmatiques et radiographie du thorax",
            "Échographie abdominale avec doppler des artères rénales",
            "Épreuve d'effort sur vélo ergométrique en urgence"
        ),
        correctOptionIndex = 0,
        explanation = "Le diagnostic d'infarctus du myocarde repose sur l'association de signes cliniques ischémiques, des modifications ECG (élévation de ST, ondes T négatives) et la cinétique d'élévation/décroissance de la troponine ultrasensible au-dessus du 99e percentile.",
        wrongOptionsExplanations = listOf(
            "Les D-dimères sont indiqués pour éliminer une maladie thrombo-embolique veineuse ou dissection aortique, mais ne dosent pas la nécrose myocardique.",
            "L'échographie des artères rénales n'a aucun rôle dans le diagnostic d'infarctus aigu.",
            "L'épreuve d'effort est strictement contre-indiquée en phase aiguë d'un syndrome coronarien suspecté !"
        ),
        category = SemiologyCategory.CARDIOVASCULAR
    ),
    QuizQuestion(
        id = "qc_12",
        question = "Quel est le mécanisme hémodynamique principal du choc cardiogénique ?",
        options = listOf(
            "Effondrement du débit cardiaque par défaillance de la pompe ventriculaire avec élévation des pressions de remplissage",
            "Vasoplégie périphérique diffuse avec chute des résistances vasculaires systémiques",
            "Hypovolémie absolue par perte sanguine ou déshydratation aiguë",
            "Obstruction mécanique extracardiaque isolée au retour veineux caverneux"
        ),
        correctOptionIndex = 0,
        explanation = "Le choc cardiogénique est une insuffisance circulatoire aiguë caractérisée par un débit cardiaque effondré (index cardiaque < 2.2 L/min/m²) malgré des pressions de remplissage ventriculaire élevées (PCP > 18 mmHg), consécutif à une atteinte myocardique sévère.",
        wrongOptionsExplanations = listOf(
            "La vasoplégie avec résistances vasculaires basses caractérise le choc distributif/septique.",
            "L'hypovolémie absolue avec pressions de remplissage basses caractérise le choc hypovolémique.",
            "L'obstruction extracardiaque au retour veineux définit le choc obstructif (tamponnade, embolie pulmonaire massive)."
        ),
        category = SemiologyCategory.CARDIOVASCULAR
    ),
    QuizQuestion(
        id = "qc_13",
        question = "Dans quel foyer d'auscultation cardiaque écoute-t-on préférentiellement l'orifice mitral ?",
        options = listOf(
            "Au 5e espace intercostal gauche sur la ligne médioclaviculaire (apex cardiaque)",
            "Au 2e espace intercostal droit au bord du sternum",
            "Au 2e espace intercostal gauche au bord du sternum",
            "Au niveau de l'appendice xiphoïde"
        ),
        correctOptionIndex = 0,
        explanation = "Le foyer mitral se situe à la pointe du cœur (apex), repéré au 5e espace intercostal gauche sur la ligne médioclaviculaire. C'est le lieu d'auscultation électif des bruits et souffles d'origine mitrale.",
        wrongOptionsExplanations = listOf(
            "Le 2e espace intercostal droit au bord sternal correspond au foyer aortique.",
            "Le 2e espace intercostal gauche au bord sternal correspond au foyer pulmonaire.",
            "L'appendice xiphoïde ou le 4e/5e espace intercostal gauche parasternal correspond au foyer tricuspide."
        ),
        category = SemiologyCategory.CARDIOVASCULAR
    ),
    QuizQuestion(
        id = "qc_14",
        question = "Quel critère clinique permet de distinguer une syncope d'une crise comitiale généralisée tonicoclonique ?",
        options = listOf(
            "Absence habituelle de morsure latérale de langue, de confusion post-critique prolongée et récupération rapide de la conscience en décubitus",
            "Présence obligatoire d'un déficit moteur focal persistant plus de 24 heures",
            "Perte de connaissance survenant exclusivement pendant le sommeil paradoxal",
            "Présence systématique d'une aura olfactive ou visuelle complexe précédant la chute"
        ),
        correctOptionIndex = 0,
        explanation = "La syncope est une perte de connaissance brève, transitoire, liée à une hypoperfusion cérébrale globale. Dès que le patient est en décubitus, la perfusion cérébrale est restaurée et la conscience redevient normale en quelques secondes sans confusion post-critique stertoreuse ni morsure latérale de langue.",
        wrongOptionsExplanations = listOf(
            "Un déficit moteur focal persistant évoque un AVC ischémique ou hémorragique, non une simple syncope.",
            "Une perte de connaissance survenant au lit pendant le sommeil n'est pas une syncope vasovagale ou orthostatique habituelle.",
            "L'aura complexe et stéréotypée oriente vers une crise d'épilepsie partielle secondairement généralisée."
        ),
        category = SemiologyCategory.CARDIOVASCULAR
    ),
    QuizQuestion(
        id = "qc_15",
        question = "Quelle anomalie physique oriente vers une thrombose veineuse profonde (TVP) du membre inférieur ?",
        options = listOf(
            "Augmentation unilatérale de la circonférence du mollet, perte du ballottement et douleur provoquée à la dorsiflexion du pied (signe de Homans)",
            "Membre inférieur blanc, glacé, insensible avec disparition complète des pouls périphériques",
            "Cordon veineux superficiel rouge, induré, chaud et douloureux sans œdème de jambe",
            "Ulcère malléolaire superficiel indolore à fond propre"
        ),
        correctOptionIndex = 0,
        explanation = "La TVP surale ou poplitée associe un œdème asymétrique du membre inférieur (> 2-3 cm de différence de tour de mollet), une diminution du ballottement musculaire passif et parfois une douleur provoquée à la flexion dorsale du pied (signe de Homans).",
        wrongOptionsExplanations = listOf(
            "La jambe blanche, glacée, douloureuse et sans pouls signe une ischémie aiguë artérielle (urgence chirurgicale).",
            "Le cordon veineux superficiel induré correspond à une thrombose veineuse superficielle (paraphlébite).",
            "L'ulcère malléolaire indolore correspond à une séquelle d'insuffisance veineuse chronique ou neuropathique diabétique."
        ),
        category = SemiologyCategory.CARDIOVASCULAR
    ),
    QuizQuestion(
        id = "qc_16",
        question = "Quelle est la définition clinique de l'hypotension orthostatique ?",
        options = listOf(
            "Baisse de la PAS >= 20 mmHg et/ou de la PAD >= 10 mmHg dans les 3 minutes suivant le passage en position debout",
            "Baisse de la fréquence cardiaque de plus de 15 battements par minute en station debout",
            "Baisse de la pression artérielle systolique sous 120 mmHg à l'effort",
            "Élévation de la pression diastolique au-delà de 90 mmHg lors du lever matinal"
        ),
        correctOptionIndex = 0,
        explanation = "L'hypotension orthostatique est définie par une baisse de la PAS d'au moins 20 mmHg et/ou de la PAD d'au moins 10 mmHg mesurée 1 et 3 minutes après le passage du décubitus à la station debout active.",
        wrongOptionsExplanations = listOf(
            "L'évaluation porte obligatoirement sur les valeurs de pression artérielle, même si la fréquence cardiaque permet de différencier une cause neurovégétative (pouls fixe) d'une hypovolémie (tachycardie réactionnelle).",
            "L'hypotension d'effort a d'autres mécanismes (sténose aortique, cardiomyopathie).",
            "L'élévation tensionnelle n'est pas une hypotension."
        ),
        category = SemiologyCategory.CARDIOVASCULAR
    ),
    QuizQuestion(
        id = "qc_17",
        question = "Le souffle d'insuffisance mitrale typique :",
        options = listOf(
            "Occupe toute la systole (holosystolique), débute dès B1 qu'il englobe, d'intensité constante en jet de vapeur",
            "Est méso-diastolique décroissant s'arrêtant avant B1",
            "Est protosystolique bref s'atténuant en fin de systole",
            "Augmente d'intensité lors de l'inspiration profonde (signe de Rivero-Carvallo)"
        ),
        correctOptionIndex = 0,
        explanation = "L'insuffisance mitrale produit un souffle holosystolique débutant avec le premier bruit B1, couvrant toute la systole jusqu'au bruit B2, en jet de vapeur, d'intensité uniforme (en plateau), maximal au foyer mitral et irradiant vers l'aisselle gauche.",
        wrongOptionsExplanations = listOf(
            "Un souffle méso-diastolique correspond à un roulement transmitral de rétrécissement mitral.",
            "Le souffle de l'IM fonctionnelle peut être protosystolique, mais l'IM organique typique est rigoureusement holosystolique.",
            "L'accentuation à l'inspiration profonde caractérise les souffles droits (Rivero-Carvallo) et non le souffle mitral gauche."
        ),
        category = SemiologyCategory.CARDIOVASCULAR
    ),
    QuizQuestion(
        id = "qc_18",
        question = "Quel signe radiologique thoracique évoque une cardiomégalie chez l'adulte de face ?",
        options = listOf(
            "Un index cardio-thoracique (ICT) supérieur à 0.50",
            "Un index cardio-thoracique inférieur à 0.35",
            "La présence de lignes B de Kerley isolées sans élargissement cardiaque",
            "Un élargissement du médiastin supérieur au-delà de 4 cm"
        ),
        correctOptionIndex = 0,
        explanation = "L'index cardio-thoracique (ICT) est le rapport entre le plus grand diamètre transversal de la silhouette cardiaque et le plus grand diamètre thoracique interne sur une radiographie de face prise en inspiration profonde. Un ICT > 0.50 chez l'adulte définit la cardiomégalie.",
        wrongOptionsExplanations = listOf(
            "Un ICT < 0.35 traduit un petit cœur en goutte, non pathologique ou fréquent chez le longiligne ou dans l'emphysème.",
            "Les lignes B de Kerley témoignent d'un œdème interstitiel septal, mais ne mesurent pas la taille du cœur.",
            "L'élargissement médiastinal supérieur oriente vers un anévrisme aortique, un goître ou une adénopathie médiastinale."
        ),
        category = SemiologyCategory.CARDIOVASCULAR
    ),
    QuizQuestion(
        id = "qc_19",
        question = "Quel signe auscultatoire précoce peut s'entendre dans le rétrécissement mitral (RM) ?",
        options = listOf(
            "Éclat de B1, claquement d'ouverture mitrale (COM) et roulement diastolique à renforcement présystolique",
            "Disparition du deuxième bruit B2 et souffle éjectionnel rude",
            "Click méso-systolique suivi d'un souffle télé-systolique",
            "Galop protodiastolique B3 avec souffle doux aspiratif"
        ),
        correctOptionIndex = 0,
        explanation = "La triade auscultatoire classique du rétrécissement mitral (rythme de Duroziez) associe un éclat du premier bruit B1 (fermeture brutale des valves scléreuses), un claquement d'ouverture mitrale (COM) en début de diastole et un roulement méso-télé-diastolique avec renforcement présystolique en rythme sinusal.",
        wrongOptionsExplanations = listOf(
            "L'abolition de B2 et le souffle éjectionnel caractérisent le rétrécissement aortique calcifié.",
            "Le click méso-systolique suivi d'un souffle télésystolique signe le prolapsus de la valve mitrale (maladie de Barlow).",
            "Le B3 est un bruit de galop de défaillance ventriculaire gauche."
        ),
        category = SemiologyCategory.CARDIOVASCULAR
    ),
    QuizQuestion(
        id = "qc_20",
        question = "Chez un patient porteur d'une prothèse valvulaire mécanique, la disparition des bruits métalliques de fermeture doit faire craindre :",
        options = listOf(
            "Une thrombose ou un blocage aigu de la prothèse valvulaire (urgence vitale)",
            "Une anémie par carence martiale bien tolérée",
            "Un phénomène physiologique d'adaptation tissulaire à long terme",
            "Une bradycardie sinusale banale survenant au repos"
        ),
        correctOptionIndex = 0,
        explanation = "Les bruits d'une prothèse mécanique normale sont secs, nets et métalliques (clics prothétiques audibles même sans stéthoscope). L'atténuation ou la disparition de ce claquement métallique avec apparition d'un souffle anormal évoque formellement une thrombose obstructive de prothèse, constituant une urgence médico-chirurgicale absolue.",
        wrongOptionsExplanations = listOf(
            "Une anémie peut causer un souffle fonctionnel mais n'éteint pas les clics mécaniques d'une prothèse.",
            "L'atténuation des bruits n'a rien de physiologique et impose une ETT/ETO immédiate.",
            "La bradycardie ne supprime pas le timbre métallique des clics lors de la fermeture valvulaire."
        ),
        category = SemiologyCategory.CARDIOVASCULAR
    )
)
