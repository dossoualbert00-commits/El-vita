package com.example.data.model.quiz

import com.example.data.model.QuizQuestion
import com.example.data.model.SemiologyCategory

val quizUrinaryQuestions: List<QuizQuestion> = listOf(
    QuizQuestion(
        id = "qu_01",
        question = "Dans l'épreuve des trois verres de Guyon, que signifie une hématurie macroscopique terminale (apparaissant uniquement à la fin du jet mictionnel) ?",
        options = listOf(
            "Origine vésicale du saignement (col vésical ou vessie)",
            "Origine urétrale ou prostatique du saignement",
            "Origine haute rénale ou pyélo-calicielle diffuse",
            "Fausses urines rouges d'origine alimentaire ou médicamenteuse"
        ),
        correctOptionIndex = 0,
        explanation = "Dans l'épreuve des trois verres : l'hématurie initiale (1er verre seul) signe une origine sous-cervicale (urétrale ou prostatique) ; l'hématurie terminale (3e verre seul) signe une origine vésicale (la vessie se contracte à vide en fin de miction provoquant le saignement) ; l'hématurie totale (les 3 verres également colorés) oriente vers une origine haute rénale (ou vésicale abondante).",
        wrongOptionsExplanations = listOf(
            "L'origine urétrale ou prostatique donne typiquement une hématurie initiale au début du jet.",
            "L'origine haute rénale donne une hématurie totale homogène sur les trois verres.",
            "La coloration par betteraves ou rifampicine colore les urines de façon constante sans hématies au microscope."
        ),
        category = SemiologyCategory.URINARY
    ),
    QuizQuestion(
        id = "qu_02",
        question = "Quelle caractéristique clinique oriente formellement vers une colique néphrétique non compliquée ?",
        options = listOf(
            "Douleur lombaire unilatérale violente, à début brutal, irradiant vers l'aine et les organes génitaux externes, sans position antalgique soulageante",
            "Douleur bilatérale calmée complètement par la position penchée en avant",
            "Douleur pelvienne associée à une contracture abdominale généralisée invincible",
            "Douleur lombaire sourde permanente aggravée par la toux avec abolition du réflexe rotulien"
        ),
        correctOptionIndex = 0,
        explanation = "La colique néphrétique par mise sous tension pyélique aiguë (calcul bloqué dans l'uretère) provoque une douleur paroxystique insoutenable unilatérale descendant le long du trajet urétéral vers les organes génitaux externes. Le patient est dans un état d'agitation motrice perpétuelle ('colique frénétique') car aucune posture ne le soulage.",
        wrongOptionsExplanations = listOf(
            "La position penchée en avant soulage la pancréatite ou la péricardite, non la colique néphrétique.",
            "La contracture abdominale généralisée signe une péritonite par perforation viscérale, incompatible avec une CN simple.",
            "La douleur sourde avec abolition d'un réflexe correspond à une radiculopathie lombaire discale (cruralgie/sciatique)."
        ),
        category = SemiologyCategory.URINARY
    ),
    QuizQuestion(
        id = "qu_03",
        question = "Quels critères biologiques définissent rigoureusement le syndrome néphrotique chez l'adulte ?",
        options = listOf(
            "Protéinurie des 24h > 3 g (ou > 300 mg/mmol) + Hypoalbuminémie < 30 g/L (et hypoprotidémie < 60 g/L)",
            "Hématurie macroscopique + Clairance de la créatinine < 15 mL/min",
            "Leucocyturie > 100 000/mL + Bactériurie significative > 10^5 UFC/mL",
            "Protéinurie modérée à 1 g/24h avec micro-albuminurie isolée"
        ),
        correctOptionIndex = 0,
        explanation = "Le syndrome néphrotique est une entité purement biologique définie par une fuite glomérulaire majeure de protéines : protéinurie > 3 g/24h (ou ratio P/C urinaire > 200-300 mg/mmol) associée à une albuminémie < 30 g/L et une protidémie totale < 60 g/L.",
        wrongOptionsExplanations = listOf(
            "Hématurie et insuffisance rénale terminale ne définissent pas le syndrome néphrotique.",
            "Leucocyturie et bactériurie définissent une infection urinaire bactérienne (ECBU positif).",
            "Une protéinurie à 1 g/24h sans hypoalbuminémie profonde est une protéinurie non néphrotique."
        ),
        category = SemiologyCategory.URINARY
    ),
    QuizQuestion(
        id = "qu_04",
        question = "Quelle est la triade classique du syndrome néphritique aigu (glomérulonéphrite aiguë post-infectieuse) ?",
        options = listOf(
            "Installation brutale d'œdèmes, d'une hypertension artérielle (HTA) et d'une hématurie macroscopique avec oligurie",
            "Polyurie nocturne, soif insatiable et amaigrissement rapide",
            "Fièvre avec frissons solennels, lombalgie aiguë et pyurie trouble",
            "Douleur pelvienne avec incontinence d'effort et globe vésical indolore"
        ),
        correctOptionIndex = 0,
        explanation = "Le syndrome néphritique aigu traduit une inflammation endocapillaire aiguë des glomérules (souvent post-streptococcique après angine ou impétigo). Il associe de façon brutale : œdèmes du visage et des paupières, HTA de surcharge hydrosodée, et hématurie (urines couleur bouillon sale ou coca-cola) avec insuffisance rénale aiguë oligurique.",
        wrongOptionsExplanations = listOf(
            "Polyurie et polydipsie évoquent un diabète sucré ou insipide.",
            "Fièvre, frissons et lombalgie forment le tableau de la pyélonéphrite aiguë bactérienne.",
            "L'incontinence avec globe relève d'une rétention vésicale avec regorgement."
        ),
        category = SemiologyCategory.URINARY
    ),
    QuizQuestion(
        id = "qu_05",
        question = "Quel signe à la percussion permet de différencier avec certitude un globe vésical d'une ascite libre ?",
        options = listOf(
            "Matité hypogastrique médiane à limite supérieure convexe vers le haut dans le globe, versus matité déclive des flancs à concavité supérieure dans l'ascite",
            "Tympanisme sonore médian dans le globe vésical",
            "Matité mobile lors des changements de position dans le globe vésical",
            "Absence totale de matité dans le globe vésical qui est toujours tympanique"
        ),
        correctOptionIndex = 0,
        explanation = "La vessie distendue forme une masse médiane issue du pelvis remontant vers l'ombilic, caractérisée par une matité franche à limite supérieure CONVEXE vers le haut. Dans l'ascite libre péritonéale, le liquide s'accumule dans les flancs par gravité déclive, dessinant une matité des flancs à concavité supérieure (tympanisme péri-ombilical central).",
        wrongOptionsExplanations = listOf(
            "Le globe est rempli d'urine et est rigoureusement mat, jamais tympanique.",
            "La matité du globe est fixe et médiane, non mobile avec la position (contrairement à l'ascite libre).",
            "Le globe vésical n'est jamais tympanique."
        ),
        category = SemiologyCategory.URINARY
    ),
    QuizQuestion(
        id = "qu_06",
        question = "Quelle manœuvre clinique réveille une vive douleur lors de la palpation lombaire en cas de pyélonéphrite aiguë ?",
        options = listOf(
            "Le signe de Giordano (douleur provoquée à la percussion douce du poing fermé sur la fosse lombaire)",
            "Le signe de Mac Burney par décompression de la fosse iliaque droite",
            "La palpation profonde de l'épigastre irradiant dans le dos",
            "La manœuvre de Lasègue par élévation de la jambe tendue"
        ),
        correctOptionIndex = 0,
        explanation = "Le signe de Giordano (ébranlement ou percussion de la fosse lombaire) consiste à frapper doucement avec le bord cubital du poing la fosse lombaire du patient assis penché en avant : une douleur vive et syncopale est déclenchée du côté infecté en cas de pyélonéphrite aiguë.",
        wrongOptionsExplanations = listOf(
            "Le point de Mac Burney correspond à l'appendicite aiguë.",
            "La douleur épigastrique transfixiante correspond à la pancréatite aiguë.",
            "La manœuvre de Lasègue explore la sciatique discale."
        ),
        category = SemiologyCategory.URINARY
    ),
    QuizQuestion(
        id = "qu_07",
        question = "Devant une suspicion de prostatite aiguë chez l'homme jeune fébrile, quelle précaution sémiologique est impérative lors du toucher rectal ?",
        options = listOf(
            "Réaliser un toucher rectal extrêmement doux et prudent sans massage de la prostate pour éviter le risque de décharge bactériémique ou de choc septique",
            "Effectuer un massage vigoureux de la prostate pour exprimer le liquide séminal",
            "S'abstenir formellement d'examiner le malade pour ne pas déclencher de récidive",
            "Réaliser une ponction de prostate au doigt à l'aveugle"
        ),
        correctOptionIndex = 0,
        explanation = "En cas de prostatite aiguë, la prostate est augmentée de volume, extrêmement douloureuse, chaude et œdématiée au toucher rectal. Le TR doit être réalisé avec une douceur extrême : le massage prostatique est formellement contre-indiqué en phase aiguë car il risque d'essaimer les bactéries dans le sang et de précipiter un choc septique foudroyant.",
        wrongOptionsExplanations = listOf(
            "Le massage prostatique vigoureux est dangereux et contre-indiqué en phase aiguë fébrile.",
            "Le toucher rectal reste indispensable au diagnostic pour constater la tuméfaction chaude et douloureuse.",
            "La ponction au doigt est un non-sens médical."
        ),
        category = SemiologyCategory.URINARY
    ),
    QuizQuestion(
        id = "qu_08",
        question = "Quelle anomalie biologique urinaire oriente vers une insuffisance rénale aiguë fonctionnelle (pré-rénale) ?",
        options = listOf(
            "Fraction d'excrétion du sodium (FE-Na) < 1% et concentration urinaire en sodium U-Na < 20 mmol/L avec réabsorption sodée intense",
            "Fraction d'excrétion du sodium > 2% et U-Na > 40 mmol/L avec incapacité de concentration des urines",
            "Présence massive de cylindres granuleux pigmentés 'brun boueux' dans le culot urinaire",
            "Rapport Urée plasmatique / Créatinine plasmatique très effondré"
        ),
        correctOptionIndex = 0,
        explanation = "Dans l'insuffisance rénale aiguë fonctionnelle, le parenchyme tubulaire est sain et réagit à l'hypoperfusion rénale en activant au maximum le système rénine-angiotensine-aldostérone : il retient avidement l'eau et le sodium. Le sodium urinaire est effondré (< 20 mmol/L), la FENa < 1%, l'osmolalité urinaire est élevée (> 500 mOsm/kg) et le ratio urée/créatinine plasmatique s'élève (> 100).",
        wrongOptionsExplanations = listOf(
            "Une FENa > 2% et NaU élevé traduisent une nécrose tubulaire aiguë (IRA organique par perte de la fonction tubulaire).",
            "Les cylindres brun boueux signent la nécrose tubulaire aiguë organique.",
            "Le ratio urée/créatinine s'élève fortement en fonctionnel, il ne s'effondre pas."
        ),
        category = SemiologyCategory.URINARY
    ),
    QuizQuestion(
        id = "qu_09",
        question = "Dans quel cas une colique néphrétique devient-elle une urgence médico-chirurgicale absolue imposant un drainage des urines en urgence ?",
        options = listOf(
            "Colique néphrétique fébrile (température >= 38.5°C avec pyonéphrose sur obstacle lithiasique)",
            "Colique néphrétique chez un patient présentant des urines claires apyrétiques",
            "Colique néphrétique avec micro-hématurie isolée à la bandelette urinaire",
            "Calcul radiologique de 3 mm situé dans l'uretère distal sans dilatation d'amont"
        ),
        correctOptionIndex = 0,
        explanation = "La colique néphrétique fébrile correspond à une infection du haut appareil urinaire en amont d'un obstacle lithiasique complet (rein en rétention purulente ou pyonéphrose). C'est une urgence médico-chirurgicale vitale : risque imminent de choc septique et de destruction rénale. Elle impose une dérivation immédiate en urgence (sonde double J ou néphrostomie percutanée) et une antibiothérapie IV.",
        wrongOptionsExplanations = listOf(
            "La CN apyrétique simple est une urgence antalgique, non chirurgicale immédiate.",
            "La micro-hématurie est constante et banale au cours de la migration d'un calcul.",
            "Un calcul de 3 mm s'évacue spontanément dans plus de 80% des cas sans geste chirurgical en urgence."
        ),
        category = SemiologyCategory.URINARY
    ),
    QuizQuestion(
        id = "qu_10",
        question = "Quelle différence distingue l'anurie de la rétention aiguë d'urines ?",
        options = listOf(
            "Dans l'anurie, la vessie est vide car les reins ne produisent plus d'urine (diurèse < 100 mL/24h), alors que dans la rétention, les reins fonctionnent mais l'urine ne peut s'évacuer et s'accumule dans la vessie",
            "Dans l'anurie, la vessie est distendue et douloureuse sous tension",
            "L'anurie est toujours réversible spontanément après sondage urétrale évacuateur",
            "La rétention d'urines ne donne jamais de douleur hypogastrique"
        ),
        correctOptionIndex = 0,
        explanation = "L'anurie est l'absence de production d'urine par les reins (diurèse < 100 à 200 mL/24h) : la vessie est vide à l'échographie ou au sondage, il n'y a pas de globe vésical ni d'envie douloureuse d'uriner. Dans la rétention aiguë, les reins continuent à filtrer mais l'obstacle urétral empêche l'évacuation, formant un globe sous tension douloureux.",
        wrongOptionsExplanations = listOf(
            "La vessie distendue sous tension caractérise la rétention vésicale, non l'anurie parenchymateuse.",
            "Le sondage d'une vessie anurique ne ramène pas d'urine et ne résout pas l'anurie sécrétoire rénale.",
            "La rétention aiguë d'urine provoque au contraire une douleur sus-pubienne angoissante violente."
        ),
        category = SemiologyCategory.URINARY
    )
)
