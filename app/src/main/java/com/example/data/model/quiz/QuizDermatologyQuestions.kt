package com.example.data.model.quiz

import com.example.data.model.QuizQuestion
import com.example.data.model.SemiologyCategory

val quizDermatologyQuestions: List<QuizQuestion> = listOf(
    QuizQuestion(
        id = "qdm_01",
        question = "Quelle manœuvre physique simple permet d'affirmer avec certitude la nature purpurique d'une lésion cutanée érythémateuse ?",
        options = listOf(
            "Le test de vitropression (la lésion purpurique persiste intégralement sans s'effacer sous la pression d'une lame de verre)",
            "Le grattage méthodique à la curette de Brocq révélant une tache de bougie",
            "La friction énergique avec une pointe mousse réveillant une papule d'urticaire",
            "L'éclairage en lumière de Wood révélant une fluorescence jaune-vert"
        ),
        correctOptionIndex = 0,
        explanation = "La vitropression est l'épreuve clé : une macule érythémateuse congestive par vasodilatation capillaire s'efface totalement à la pression, alors qu'un purpura (hématies extravasées dans le derme hors des vaisseaux) persiste sans aucune modification de couleur.",
        wrongOptionsExplanations = listOf(
            "Le grattage de Brocq explore les squames du psoriasis (signe de la tache de bougie, pellicule décollable et signe de la rosée sanglante d'Auspitz).",
            "La friction réveillant une papule urticarienne explore le dermographisme ou le signe de Darier de la mastocytose.",
            "La lumière de Wood sert au diagnostic des mycoses (teignes du cuir chevelu) ou de l'érythrasma."
        ),
        category = SemiologyCategory.DERMATOLOGY
    ),
    QuizQuestion(
        id = "qdm_02",
        question = "Qu'est-ce que le signe de Nikolsky à l'examen de la peau ?",
        options = listOf(
            "Un décollement épidermique provoqué en lambeau par un frottement tangentiel doux sur une zone de peau d'apparence saine",
            "L'apparition de lésions cutanées spécifiques le long d'une strie de grattage cutané",
            "Une rougeur fugace avec œdème survenant après application locale de glace",
            "La formation d'une ecchymose spontanée sans traumatisme préalable"
        ),
        correctOptionIndex = 0,
        explanation = "Le signe de Nikolsky se recherche par pression et glissement doux du doigt tangentiellement à la surface cutanée saine : s'il provoque un clivage et glissement de l'épiderme superficiel sur le derme (comme une peau de tomate mûre), il est positif. Il traduit une perte d'adhésion inter-kératinocytaire intra-épidermique (acantholyse du pemphigus ou nécrose épidermique toxique du syndrome de Lyell).",
        wrongOptionsExplanations = listOf(
            "L'apparition de lésions spécifiques le long d'un trait de grattage est le phénomène isomorphe de Koebner (psoriasis, lichen plan).",
            "L'œdème après glaçon teste l'urticaire au froid.",
            "L'ecchymose spontanée sans frottement est un signe de fragilité capillaire ou de coagulopathie."
        ),
        category = SemiologyCategory.DERMATOLOGY
    ),
    QuizQuestion(
        id = "qdm_03",
        question = "Devant un patient fébrile présentant un purpura ecchymotique extensif avec au moins un élément nécrotique > 5 mm, quelle est l'attitude immédiate salvatrice ?",
        options = listOf(
            "Injection immédiate par voie intraveineuse ou intramusculaire d'une céphalosporine de 3e génération (Ceftriaxone ou Céfotaxime) sans attendre le transfert hospitalier ni les examens",
            "Réaliser en priorité une ponction lombaire pour identifier la bactérie au direct",
            "Prescrire une corticothérapie orale à forte dose et surveiller l'évolution à domicile",
            "Attendre le résultat de la numération formule sanguine pour doser les plaquettes"
        ),
        correctOptionIndex = 0,
        explanation = "C'est la définition et la conduite à tenir d'extrême urgence du PURPURA FULMINANS (généralement à méningocoque). Chaque minute compte : l'antibiothérapie par C3G parentérale doit être injectée immédiatement dès le diagnostic clinique posé, avant tout transport, avant toute biologie et sans attendre la ponction lombaire (qui sera réalisée secondairement).",
        wrongOptionsExplanations = listOf(
            "La ponction lombaire ne doit jamais faire retarder l'injection salvatrice d'antibiotique en cas de purpura fulminans !",
            "Les corticoïdes oraux à domicile sont inadaptés et dramatiques face à une septicémie bactérienne fulminante.",
            "Attendre la NFS entraîne un retard de plusieurs heures létal pour le patient."
        ),
        category = SemiologyCategory.DERMATOLOGY
    ),
    QuizQuestion(
        id = "qdm_04",
        question = "Quelle lésion élémentaire cutanée caractérise l'érythème noueux ?",
        options = listOf(
            "Nouures dermohypodermiques : nodules sous-cutanés fermes, arrondis, douloureux, chauds, siégeant préférentiellement sur les crêtes tibiales antérieures",
            "Bulles flasques intra-épidermiques à contenu séreux clair",
            "Vésicules ombiliquées groupées en bouquet le long d'un métamère thoracique",
            "Plaques érythémato-squameuses bien délimitées avec squames épaisses argentées"
        ),
        correctOptionIndex = 0,
        explanation = "L'érythème noueux est l'hypodermite nodulaire aiguë par excellence. Il se manifeste par des nodosités ou nouures sous-cutanées palpables, fermes, chaudes, érythémateuses et très douloureuses à la palpation, localisées bilatéralement et symétriquement sur les faces d'extension des jambes (crêtes tibiales), évoluant selon les teintes de la biligénie comme une ecchymose.",
        wrongOptionsExplanations = listOf(
            "Les bulles flasques intra-épidermiques se rencontrent dans le pemphigus.",
            "Les vésicules groupées en bouquet métamérique caractérisent le zona (VZV).",
            "Les plaques érythémato-squameuses argentées caractérisent le psoriasis vulgaire."
        ),
        category = SemiologyCategory.DERMATOLOGY
    ),
    QuizQuestion(
        id = "qdm_05",
        question = "Dans la règle sémiologique 'ABCDE' de dépistage précoce du mélanome cutané, que signifie la lettre 'D' ?",
        options = listOf(
            "Diamètre supérieur à 6 millimètres (ou dynamique d'évolution rapide)",
            "Décoloration centrale complète",
            "Douleur spontanée permanente",
            "Dureté pierreuse à la palpation"
        ),
        correctOptionIndex = 0,
        explanation = "La règle clinique mnémotechnique ABCDE pour le dépistage du mélanome : A = Asymétrie de la lésion ; B = Bords irréguliers, encochés, polycycliques ; C = Couleur hétérogène (plusieurs nuances de brun, noir, bleu, rouge ou blanc) ; D = Diamètre supérieur à 6 mm ; E = Évolution récente documentée (taille, forme, relief, couleur).",
        wrongOptionsExplanations = listOf(
            "La décoloration n'est pas le D de la règle (même si des zones de régression achromiques sont suspectes au dermoscope).",
            "Le mélanome débutant est typiquement indolore.",
            "L'induration pierreuse s'observe plutôt dans les carcinomes épidermoïdes invasifs évolués."
        ),
        category = SemiologyCategory.DERMATOLOGY
    ),
    QuizQuestion(
        id = "qdm_06",
        question = "Quelle est la définition stricte d'une bulle par rapport à une vésicule en sémiologie dermatologique ?",
        options = listOf(
            "Une bulle est une élevure liquidienne cutanée de diamètre strictement supérieur à 5 millimètres, alors qu'une vésicule mesure moins de 5 millimètres",
            "Une bulle a un contenu purulent épais tandis que la vésicule a un contenu hématique",
            "Une bulle siège uniquement sur les muqueuses alors que la vésicule ne touche que la peau glabre",
            "Une bulle est toujours sous-épidermique alors que la vésicule est toujours sous-cutanée"
        ),
        correctOptionIndex = 0,
        explanation = "Le critère discriminant fondamental entre vésicule et bulle est la dimension : les deux sont des lésions circonscrites surélevées à contenu liquidien clair, mais la vésicule est petite (< 5 mm de diamètre), tandis que la bulle est de plus grande dimension (> 5 mm, pouvant atteindre plusieurs centimètres).",
        wrongOptionsExplanations = listOf(
            "Une lésion à contenu purulent est une pustule, quelle que soit sa taille.",
            "Les bulles et les vésicules peuvent toutes deux siéger sur la peau comme sur les muqueuses.",
            "La profondeur histologique (intra ou sous-épidermique) varie selon la maladie pour les deux types de lésions."
        ),
        category = SemiologyCategory.DERMATOLOGY
    ),
    QuizQuestion(
        id = "qdm_07",
        question = "Quel signe physique est pathognomonique de l'infestation par la gale humaine (Sarcoptes scabiei) ?",
        options = listOf(
            "Le sillon scabieux (trajet filiforme sinueux de quelques millimètres terminé par une vésicule perlée)",
            "L'érythème migrant centrifuge en cocarde avec éclaircissement central",
            "La tache noire d'inoculation indolore avec adénopathie satellite",
            "Le chancre d'inoculation induré et propre à fond cartilagineux"
        ),
        correctOptionIndex = 0,
        explanation = "Le sillon scabieux correspond au tunnel creusé par la femelle sarcopte dans la couche cornée de l'épiderme pour y pondre ses œufs. C'est une fine ligne sinueuse, grisâtre ou rosée, de 5 à 15 mm, terminée par une surélévation translucide (vésicule perlée abritant le parasite). Siège d'élection : espaces interdigitaux, face antérieure des poignets, verge chez l'homme, aréoles mammaires chez la femme.",
        wrongOptionsExplanations = listOf(
            "L'érythème migrant est pathognomonique de la borréliose de Lyme (après piqûre de tique).",
            "La tache noire avec escarre est le stigmate de la rickettsiose (fièvre boutonneuse méditerranéenne).",
            "Le chancre induré indolore est la lésion inaugurale de la syphilis primaire (Treponema pallidum)."
        ),
        category = SemiologyCategory.DERMATOLOGY
    ),
    QuizQuestion(
        id = "qdm_08",
        question = "Le phénomène de Koebner correspond à :",
        options = listOf(
            "L'apparition de lésions cutanées spécifiques identiques à la dermatose sous-jacente sur une zone de traumatisme ou de friction cutanée",
            "La disparition complète des lésions de psoriasis après exposition solaire prolongée",
            "L'accentuation des rides faciales lors du sourire chez le patient sclérodermique",
            "La présence de pustules stériles péri-unguéales récidivantes"
        ),
        correctOptionIndex = 0,
        explanation = "Le phénomène isomorphe de Koebner est la survenue de lésions dermatologiques spécifiques sur une peau saine préalablement soumise à une agression mécanique, physique ou chirurgicale (strie de grattage, coupure, cicatrice, brûlure, frottement). Il est caractéristique du psoriasis, du lichen plan et des verrues planes.",
        wrongOptionsExplanations = listOf(
            "L'amélioration par les UV est un effet photo-thérapeutique, non le phénomène de Koebner.",
            "L'amincissement des lèvres avec plis radiés péri-oraux est le signe de la sclérodermie systémique.",
            "Les pustules péri-unguéales caractérisent l'acrodermatite continue de Hallopeau."
        ),
        category = SemiologyCategory.DERMATOLOGY
    ),
    QuizQuestion(
        id = "qdm_09",
        question = "Dans la pemphigoïde bulleuse (sujet âgé), quelle est la caractéristique habituelle des bulles ?",
        options = listOf(
            "Bulles volumineuses, tendues, à contenu séro-hématique, reposant sur une base érythémateuse urticarienne, avec signe de Nikolsky négatif",
            "Bulles très flasques, fragiles, se rompant immédiatement pour laisser des érosions suintantes, avec signe de Nikolsky positif",
            "Bulles purulentes stériles d'emblée associées à une desquamation lamellaire généralisée",
            "Petites vésicules punctiformes herpétiformes prurigineuses sur les coudes et fesses"
        ),
        correctOptionIndex = 0,
        explanation = "La pemphigoïde bulleuse est une dermatose auto-immune sous-épidermique : le toit de la bulle est épais (formé de tout l'épiderme intact), conférant à la bulle un aspect tendu, volumineux, résistant à la pression, sur placard urticarien prurigineux, avec un signe de Nikolsky négatif. Elle touche préférentiellement les personnes de plus de 70 ans.",
        wrongOptionsExplanations = listOf(
            "Les bulles flasques, fragiles, à signe de Nikolsky positif caractérisent le pemphigus vulgaire (maladie intra-épidermique par anticorps anti-desmogléines).",
            "Les bulles purulentes avec desquamation évoquent le psoriasis pustuleux ou l'impétigo bulleux staphylococcique.",
            "Les vésicules herpétiformes symétriques sur coudes et fesses caractérisent la dermatite herpétiforme (associée à la maladie cœliaque)."
        ),
        category = SemiologyCategory.DERMATOLOGY
    ),
    QuizQuestion(
        id = "qdm_10",
        question = "Quel signe sémiologique précoce doit faire suspecter une toxidermie bulleuse grave (syndrome de Stevens-Johnson ou de Lyell) ?",
        options = listOf(
            "Présence d'un érythème douloureux 'brûlant' avec érosions muqueuses pluriorificielles (lèvres, yeux, organes génitaux) et signe de Nikolsky positif",
            "Apparition d'un prurit isolé sans aucune lésion cutanée visible",
            "Présence de petites papules folliculaires centrées par un poil sur le dos",
            "Taches brunes lenticulaires congénitales des paumes et plantes"
        ),
        correctOptionIndex = 0,
        explanation = "Les toxidermies nécrotiques sévères (SJS et NET/Lyell) débutent typiquement par un prodrome pseudo-grippal fébrile, suivi d'un érythème cutané sombre douloureux 'en coup de soleil' avec décollement épidermique en linge mouillé (Nikolsky positif) et atteinte érosive douloureuse d'au moins 2 muqueuses (chéilite croûteuse hémorragique, conjonctivite purulente synéchiante, érosions génitales).",
        wrongOptionsExplanations = listOf(
            "Le prurit nu sans lésion oriente vers une cholestase, insuffisance rénale ou lymphome, non une nécrose épidermique aiguë.",
            "Les papules folliculaires correspondent à des folliculites superficielles banales.",
            "Les taches brunes lenticulaires congénitales sont des macules pigmentaires bénignes."
        ),
        category = SemiologyCategory.DERMATOLOGY
    )
)
