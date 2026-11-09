package com.example.data.model.sheets

import com.example.data.model.SemiologyCategory
import com.example.data.model.SemiologySheet

object SemiologyDermatologySheets {

  val sheets: List<SemiologySheet> = listOf(
    // 1. Lésions élémentaires
    SemiologySheet(
      id = "dermato_lesions_elementaires",
      name = "Lésions Élémentaires Cutanées (Macule, Papule, Vésicule, Bulle)",
      category = SemiologyCategory.DERMATOLOGY,
      subCategory = "Lésions élémentaires",
      definition = "Alphabet sémiologique fondamental de la dermatologie. Une lésion élémentaire primitive est l'anomalie morphologique inaugurale analysée à l'inspection et à la palpation.",
      physiopathology = "Macule : modification de couleur sans relief. Papule : surélévation solide < 1 cm par infiltration dermique ou épidermique. Vésicule : surélévation liquidienne claire < 5 mm (intra-épidermique). Bulle : surélévation liquidienne > 5 mm. Pustule : surélévation à contenu louche ou purulent d'emblée.",
      etiologies = listOf(
        "Macules érythémateuses : exanthèmes viraux, toxidermies médicamenteuses",
        "Papules : lichen plan, verrues, molluscum, urticaire, acné",
        "Vésicules : herpès simplex (HSV), zona (VZV), varicelle, eczéma de contact",
        "Bulles : syndrome de Lyell (NET), pemphigoïde bulleuse, pemphigus vulgaire",
        "Pustules : psoriasis pustuleux, folliculite bactérienne"
      ),
      circumstances = "Bilan d'une éruption cutanée aiguë ou chronique après prise médicamenteuse, exposition solaire ou contage infectieux.",
      anamnesis = "Date et mode d'apparition de la première lésion, topographie initiale et cinétique d'extension, intensité du prurit ou des sensations de brûlure, anamnèse médicamenteuse des 2 derniers mois.",
      searchMethod = "Examen complet du patient déshabillé sous un bon éclairage naturel, complété par la palpation, la vitropression et l'examen des muqueuses et des phanères.",
      physicalExam = "Inspection : forme, taille, contours, regroupement (linéaire, annulaire, métamérique, en bouquet). Palpation : consistance, infiltration, chaleur. Recherche systématique d'une atteinte des muqueuses (buccale, oculaire, génitale) et du signe de Nikolsky.",
      associatedSigns = listOf(
        "Signe de Nikolsky (décollement épidermique au frottement tangentiel sur peau saine)",
        "Érosions muqueuses pluriorificielles",
        "Adénopathies satellites",
        "Prurit féroce insomniant"
      ),
      clinicalSignificance = "L'identification rigoureuse de la lésion élémentaire initiale permet d'orienter immédiatement le diagnostic étiologique.",
      differentialDiagnoses = listOf(
        "Confusion entre vésicule et pustule",
        "Confusion entre macule érythémateuse et purpura (tranchée par la vitropression)",
        "Lésions secondaires de grattage (croûtes, excoriations) masquant la lésion primitive"
      ),
      complementaryExams = listOf(
        "Biopsie cutanée avec étude histologique standard et immunofluorescence directe (IFD)",
        "Prélèvement virologique par PCR (HSV, VZV)",
        "Prélèvement bactériologique ou mycologique direct",
        "Dermoscopie optique"
      ),
      gravitySigns = listOf(
        "Signe de Nikolsky positif (urgence dermatologique : Lyell, pemphigus)",
        "Décollement cutané étendu > 10% de la surface corporelle",
        "Atteinte muqueuse érosive douloureuse",
        "Fièvre élevée avec altération profonde de l'état général"
      ),
      pitfallsAndLimits = "Ne jamais négliger l'examen des plis cutanés, du cuir chevelu, des ongles et des organes génitaux externes.",
      keyPoints = listOf(
        "Identifier la lésion élémentaire primitive avant tout remaniement de grattage.",
        "Toujours réaliser le test de vitropression.",
        "Rechercher le signe de Nikolsky et l'atteinte muqueuse devant toute bulle."
      )
    ),

    // 2. Purpura et Vitropression
    SemiologySheet(
      id = "dermato_purpura_complet",
      name = "Purpura et Test de Vitropression",
      category = SemiologyCategory.DERMATOLOGY,
      subCategory = "Signes physiques",
      definition = "Extravasation spontanée de globules rouges hors des capillaires dans le derme. Sa caractéristique sémiologique obligatoire est qu'il NE S'EFFACE PAS À LA VITROPRESSION.",
      physiopathology = "Purpura hématologique : thrombopénie sévère (< 50 G/L) ou thrombopathie, extravasation passive sans nécrose pariétale (purpura plan pétéchial). Purpura vasculaire : inflammation et nécrose de la paroi vasculaire avec infiltration leucocytaire (purpura infiltré palpable et nécrotique).",
      etiologies = listOf(
        "Hématologiques : Purpura thrombopénique immunologique (PTI), leucémie aiguë, aplasie médullaire, CIVD",
        "Vasculaires infectieux : Purpura fulminans méningococcique (URGENCE ABSOLUE), endocardite infectieuse",
        "Vasculaires immunologiques : Vascularite à IgA (Schönlein-Henoch), vascularite à ANCA, cryoglobulinémie"
      ),
      circumstances = "Apparition brutale avec syndrome fébrile (urgence infectieuse) ou survenue déclive aux jambes après station debout prolongée.",
      anamnesis = "Notion de fièvre avec frissons, céphalées et vomissements, douleurs abdominales ou arthralgies associées, saignements muqueux (épistaxis, gingivorragies).",
      searchMethod = "Application d'une lame de verre ou du fond d'un verre propre transparent sur la lésion en exerçant une pression ferme continue pendant 5 secondes.",
      physicalExam = "Vitropression : la rougeur persiste intégralement sans s'effacer (différencie le purpura de l'érythème). Palpation : déterminer s'il est plan (hématologique) ou infiltré en relief (vasculaire). Morphologie : pétéchies en tête d'épingle, ecchymoses, vibices linéaires, éléments nécrotiques grisâtres.",
      associatedSigns = listOf(
        "Bulles hémorragiques endobuccales (signe de gravité d'une thrombopénie profonde < 20 G/L)",
        "Syndrome méningé (raideur de nuque)",
        "Hématurie macroscopique ou microscopique à la bandelette urinaire",
        "Méléna ou rectorragies"
      ),
      clinicalSignificance = "Permet de reconnaître immédiatement le Purpura Fulminans et d'injecter sans délai l'antibiothérapie par C3G parentérale.",
      differentialDiagnoses = listOf(
        "Érythème cutané (s'efface totalement à la vitropression)",
        "Angiomes stellaires (s'effacent sous la pression)",
        "Télangiectasies cutanées"
      ),
      complementaryExams = listOf(
        "NFS-plaquettes en extrême urgence",
        "Bilan d'hémostase (TP, TCA, fibrinogène, D-Dimères : recherche de CIVD)",
        "Hémocultures immédiates",
        "Bandelette urinaire (protéinurie, hématurie)",
        "Biopsie cutanée avec immunofluorescence directe"
      ),
      gravitySigns = listOf(
        "PURPURA FULMINANS : 1 seul élément purpurique ecchymotique ou nécrotique > 5 mm fébrile = CELESTO-URGENCE (C3G immédiate)",
        "Bulles hémorragiques sur la muqueuse jugale ou gingivale",
        "Saignements viscéraux actifs (digestif, hématurie massive)"
      ),
      pitfallsAndLimits = "Retarder l'injection de Ceftriaxone ou de Céfotaxime pour réaliser une ponction lombaire ou attendre l'ambulance est une faute lourde engageant le pronostic vital !",
      keyPoints = listOf(
        "Ne s'efface pas à la vitropression = extravasation globulaire = purpura.",
        "Purpura fébrile extensif = Purpura fulminans = C3G parentérale immédiate.",
        "Bulle hémorragique buccale = alerte rouge de thrombopénie profonde menaçante."
      )
    )
  )
}
