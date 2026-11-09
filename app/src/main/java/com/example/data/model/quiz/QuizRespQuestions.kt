package com.example.data.model.quiz

import com.example.data.model.QuizQuestion
import com.example.data.model.SemiologyCategory

val quizRespQuestions: List<QuizQuestion> = listOf(
    QuizQuestion(
        id = "qr_01",
        question = "Quelle triade physique caractérise le syndrome de condensation pulmonaire rétractile (atélectasie) ?",
        options = listOf(
            "Matité franche, abolition du murmure vésiculaire et abolition des vibrations vocales avec attraction médiastinale",
            "Matité franche, souffle tubaire, râles crépitants en couronne et exagération des vibrations vocales",
            "Tympanisme, abolition du murmure vésiculaire et abolition des vibrations vocales",
            "Hypersonorité bilatérale diffuse avec diminution globale des bruits respiratoires"
        ),
        correctOptionIndex = 0,
        explanation = "Dans l'atélectasie (condensation rétractile avec obstruction bronchique complète), la bronche est fermée : les vibrations vocales ne sont plus transmises et sont abolies, le murmure vésiculaire est aboli, et il y a une matité avec signes d'attraction homolatérale.",
        wrongOptionsExplanations = listOf(
            "La matité avec souffle tubaire et augmentation des vibrations vocales caractérise la condensation NON rétractile à bronche perméable (pneumonie franche lobaire aiguë).",
            "Le tympanisme avec abolition du murmure et des vibrations vocales signe le pneumothorax (épanchement aérien).",
            "L'hypersonorité bilatérale diffuse est typique de l'emphysème pulmonaire pan-lobulaire."
        ),
        category = SemiologyCategory.RESPIRATORY
    ),
    QuizQuestion(
        id = "qr_02",
        question = "Quelle triade physique signe formellement un épanchement pleural liquidien de moyenne ou grande abondance ?",
        options = listOf(
            "Matité déclive à limite supérieure concave en haut et en dehors (ligne de Damoiseau), abolition des vibrations vocales et abolition du murmure vésiculaire",
            "Tympanisme sonore, exagération des vibrations vocales et souffle tubaire intense",
            "Sonorité normale, râles sibilants diffus expiratoires et frottement péricardique",
            "Matité fixe avec vibrations vocales conservées et râles sous-crépitants bilatéraux"
        ),
        correctOptionIndex = 0,
        explanation = "La triade pleurétique classique associe matité franche de tonalité ligneuse (à convexité supérieure en ligne de Damoiseau), silence auscultatoire (abolition du murmure vésiculaire) et abolition de la transmission des vibrations vocales au niveau de l'épanchement liquidien.",
        wrongOptionsExplanations = listOf(
            "Le tympanisme avec augmentation des vibrations vocales est impossible (le tympanisme traduit de l'air où les vibrations sont abolies).",
            "Les râles sibilants traduisent un bronchospasme et non un épanchement pleural liquidien.",
            "La conservation des vibrations vocales élimine formellement un épanchement pleural franc sous-jacent."
        ),
        category = SemiologyCategory.RESPIRATORY
    ),
    QuizQuestion(
        id = "qr_03",
        question = "À quel moment du cycle respiratoire entend-on préférentiellement les râles crépitants fins alvéolaires ?",
        options = listOf(
            "En fin d'inspiration (télé-inspiratoires), brefs, secs, égaux entre eux, non modifiés par la toux",
            "Durant toute l'expiration, sous forme de sifflements musicaux aigus continus",
            "En début d'inspiration (proto-inspiratoires), humides, inégaux et modifiés par l'expectoration",
            "Exclusivement aux deux temps de la respiration avec sensation de froissement superficiel"
        ),
        correctOptionIndex = 0,
        explanation = "Les râles crépitants sont des bruits télé-inspiratoires fins, secs, discontinus, comparés au bruit produit par le froissement de cheveux près de l'oreille ou au sel jeté dans la poêle. Ils traduisent l'ouverture brutale des alvéoles et bronchioles collapsées lors de l'inspiration terminale.",
        wrongOptionsExplanations = listOf(
            "Les sifflements musicaux continus expiratoires sont des râles sibilants bronchiques.",
            "Les bruits humides inégaux de début d'inspiration modifiés par la toux sont des râles sous-crépitants ou bulleux bronchiques.",
            "Le froissement superficiel audible aux deux temps respiratoires correspond au frottement pleural."
        ),
        category = SemiologyCategory.RESPIRATORY
    ),
    QuizQuestion(
        id = "qr_04",
        question = "Qu'est-ce que le balancement thoraco-abdominal lors de l'inspection respiratoire ?",
        options = listOf(
            "À l'inspiration, le thorax se soulève tandis que la paroi abdominale se creuse (rentre vers l'intérieur)",
            "À l'inspiration, l'abdomen et le thorax se dilatent simultanément de façon symétrique",
            "Une asymétrie d'ampliation thoracique limitée à un seul hémithorax",
            "Un affaissement inspiratoire du creux sus-claviculaire et des espaces intercostaux"
        ),
        correctOptionIndex = 0,
        explanation = "La respiration paradoxale ou balancement thoraco-abdominal survient lors de la fatigue ou paralysie diaphragmatique : à l'inspiration, la pression négative intrathoracique aspire le diaphragme flasque vers le haut et creuse l'abdomen. C'est un signe clinique d'épuisement respiratoire imminent.",
        wrongOptionsExplanations = listOf(
            "L'expansion simultanée et coordonnée du thorax et de l'abdomen est la dynamique respiratoire normale.",
            "L'asymétrie d'ampliation unilatérale oriente vers un pneumothorax, une pleurésie unilatérale ou une atélectasie.",
            "L'affaissement du creux sus-claviculaire et des espaces intercostaux définit le tirage respiratoire."
        ),
        category = SemiologyCategory.RESPIRATORY
    ),
    QuizQuestion(
        id = "qr_05",
        question = "Quelle expectoration est classiquement décrite dans la pneumonie franche lobaire aiguë à pneumocoque ?",
        options = listOf(
            "Expectoration rouillée, visqueuse et adhérente (teinte brique ou jus de pruneau)",
            "Expectoration perlée de Laennec, blanche, visqueuse et filante",
            "Expectoration mousseuse et saumonée abondante",
            "Vomique franche de liquide purulent chocolaté"
        ),
        correctOptionIndex = 0,
        explanation = "L'expectoration rouillée est pathognomonique de l'hépatisation rouge de la pneumonie aiguë lobaire à Streptococcus pneumoniae. Elle est épaisse, visqueuse, striée d'hématies lysées conférant sa couleur brique rouillée caractéristique.",
        wrongOptionsExplanations = listOf(
            "Le crachat perlé de Laennec s'observe à la fin de la crise d'asthme bronchique.",
            "L'expectoration mousseuse rose saumonée est le signe classique de l'inondation alvéolaire de l'œdème aigu du poumon.",
            "La vomique purulente chocolatée évoque la rupture d'un abcès amibien du foie dans l'arbre bronchique."
        ),
        category = SemiologyCategory.RESPIRATORY
    ),
    QuizQuestion(
        id = "qr_06",
        question = "Quel est le signe physique cardinal du pneumothorax unilatéral ?",
        options = listOf(
            "Tympanisme franc à la percussion avec abolition du murmure vésiculaire et des vibrations vocales",
            "Matité ligneuse avec râles crépitants et souffle tubaire intense",
            "Exagération des vibrations vocales avec ronchi bronchiques bilatéraux",
            "Augmentation unilatérale du murmure vésiculaire sans modification de la sonorité"
        ),
        correctOptionIndex = 0,
        explanation = "La triade de Gaillard signe le pneumothorax : 1. Tympanisme à la percussion de l'hémithorax atteint. 2. Abolition du murmure vésiculaire (silence auscultatoire). 3. Abolition de la transmission des vibrations vocales.",
        wrongOptionsExplanations = listOf(
            "La matité ligneuse avec râles crépitants correspond au syndrome de condensation ou pleurésie.",
            "Les vibrations vocales sont obligatoirement abolies par la lame d'air interposée entre le poumon et la paroi.",
            "Le murmure vésiculaire est diminué ou éteint, jamais augmenté du côté du pneumothorax."
        ),
        category = SemiologyCategory.RESPIRATORY
    ),
    QuizQuestion(
        id = "qr_07",
        question = "Quelle anomalie des ongles définit l'hippocratisme digital ?",
        options = listOf(
            "Incurvation des ongles en verre de montre avec hypertrophie des pulpes unguéales en baguettes de tambour",
            "Excavation cupuliforme des ongles en cuillère avec fragilité lamellaire (koïlonychie)",
            "Lignes transversales blanches parallèles sur tous les ongles (lignes de Muehrcke)",
            "Décollement distal indolore de la tablette unguéale (onycholyse)"
        ),
        correctOptionIndex = 0,
        explanation = "L'hippocratisme digital associe une déformation convexe longitudinale et transversale de l'ongle en 'verre de montre', un effacement de l'angle unguéo-phalangien de Lovibond (> 180°) et un épaississement bulbeux des dernières phalanges en 'baguettes de tambour'.",
        wrongOptionsExplanations = listOf(
            "L'ongle en cuillère ou koïlonychie est la conséquence classique d'une anémie par carence martiale chronique prolongée.",
            "Les lignes de Muehrcke sont des bandes blanchâtres transversales associées aux hypoalbuminémies sévères.",
            "L'onycholyse se rencontre dans l'hyperthyroïdie (ongles de Plummer) ou le psoriasis."
        ),
        category = SemiologyCategory.RESPIRATORY
    ),
    QuizQuestion(
        id = "qr_08",
        question = "Quelle est la principale cause d'hémoptysie de grande abondance engageant le pronostic vital par asphyxie ?",
        options = listOf(
            "Dilatation des bronches (bronchectasies) et tuberculose pulmonaire active ou séquellaire",
            "Rhinopharyngite virale banale avec épistaxis postérieure minime",
            "Pleurésie séro-fibrineuse idiopathique sans lésion parenchymateuse",
            "Insuffisance veineuse périphérique des membres inférieurs sans embolie"
        ),
        correctOptionIndex = 0,
        explanation = "Les deux principales causes d'hémoptysies graves sont la dilatation des bronches (DDB) par hypervascularisation artérielle systémique bronchique et la tuberculose (cavernes tuberculeuses avec rupture d'anévrisme de Rasmussen), suivies par le carcinome bronchique primitif.",
        wrongOptionsExplanations = listOf(
            "La rhinopharyngite produit des crachats teintés d'origine nasale haute, non une véritable hémoptysie sous-glottique.",
            "La pleurésie séro-fibrineuse n'entraîne pas d'hémoptysie en l'absence de lésion parenchymateuse associée.",
            "L'insuffisance veineuse n'a aucun lien direct avec l'arbre bronchique sans embolie pulmonaire associée."
        ),
        category = SemiologyCategory.RESPIRATORY
    ),
    QuizQuestion(
        id = "qr_09",
        question = "Dans la BPCO et l'asthme, le sifflement expiratoire (râle sibilant) est généré par :",
        options = listOf(
            "La vibration de la paroi bronchique rétrécie lors de l'écoulement d'air turbulent à vitesse accélérée",
            "La présence de sécrétions épaisses stagnantes dans la trachée et les grosses bronches cartilagineuses",
            "Le frottement mécanique entre les deux feuillets enflammés de la plèvre pariétale et viscérale",
            "L'ouverture brutale et explosive des alvéoles distales collapsées en inspiration"
        ),
        correctOptionIndex = 0,
        explanation = "Les râles sibilants sont des bruits adventices continus musicaux à prédominance expiratoire générés par la vibration des parois de petites et moyennes bronches rétrécies (bronchospasme, œdème sous-muqueux, hypersécrétion) traversées par un flux gazeux accéléré.",
        wrongOptionsExplanations = listOf(
            "Les sécrétions épaisses dans les grosses bronches produisent des râles bronchiques ronflants ou ronchi.",
            "Le frottement des feuillets pleuraux produit le frottement pleural.",
            "L'ouverture télé-inspiratoire des alvéoles produit les râles crépitants."
        ),
        category = SemiologyCategory.RESPIRATORY
    ),
    QuizQuestion(
        id = "qr_10",
        question = "Quel signe clinique permet d'affirmer le caractère central d'une cyanose ?",
        options = listOf(
            "Atteinte de la muqueuse linguale et de la face interne des joues ne disparaissant pas au réchauffement local",
            "Cyanose limitée aux extrémités distales des doigts devenant chauds après immersion dans l'eau tiède",
            "Présence d'un purpura pétéchial déclive sur les membres inférieurs",
            "Pâleur conjonctivale intense associée à une tachycardie de repos"
        ),
        correctOptionIndex = 0,
        explanation = "La cyanose centrale traduit une désaturation artérielle systémique en oxygène (SaO2 < 85%, taux d'hémoglobine réduite > 5 g/dL). Elle intéresse tout le tégument ET les muqueuses chaudes (langue, gencives, intérieur des joues), et ne s'efface pas au réchauffement local des extrémités.",
        wrongOptionsExplanations = listOf(
            "La cyanose des extrémités s'effaçant au réchauffement traduit une cyanose périphérique par vasoconstriction ou ralentissement circulatoire (SaO2 normale).",
            "Le purpura est une lésion hémorragique extravasée, sans rapport avec la cyanose.",
            "La pâleur conjonctivale est le signe d'une anémie."
        ),
        category = SemiologyCategory.RESPIRATORY
    )
)
