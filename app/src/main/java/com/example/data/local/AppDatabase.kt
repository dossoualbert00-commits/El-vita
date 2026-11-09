package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.data.local.dao.ClinicalObservationDao
import com.example.data.local.dao.ClinicalUpdateDao
import com.example.data.local.dao.LabResultDao
import com.example.data.local.dao.PatientDao
import com.example.data.local.dao.VitalDao
import com.example.data.local.entities.ClinicalObservationEntity
import com.example.data.local.entities.ClinicalUpdateEntity
import com.example.data.local.entities.LabResultEntity
import com.example.data.local.entities.PatientEntity
import com.example.data.local.entities.VitalEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
  entities = [
    PatientEntity::class,
    VitalEntity::class,
    ClinicalObservationEntity::class,
    ClinicalUpdateEntity::class,
    LabResultEntity::class
  ],
  version = 1,
  exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
  abstract fun patientDao(): PatientDao
  abstract fun vitalDao(): VitalDao
  abstract fun clinicalObservationDao(): ClinicalObservationDao
  abstract fun clinicalUpdateDao(): ClinicalUpdateDao
  abstract fun labResultDao(): LabResultDao

  companion object {
    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
      return INSTANCE ?: synchronized(this) {
        val instance = Room.databaseBuilder(
          context.applicationContext,
          AppDatabase::class.java,
          "elvita_database.db"
        )
        .fallbackToDestructiveMigration()
        .addCallback(DatabaseCallback(scope))
        .build()
        INSTANCE = instance
        instance
      }
    }

    private class DatabaseCallback(
      private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
      override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        INSTANCE?.let { database ->
          scope.launch(Dispatchers.IO) {
            seedPedagogicalDatabase(database)
          }
        }
      }
    }

    suspend fun seedPedagogicalDatabase(db: AppDatabase) {
      val patientDao = db.patientDao()
      val vitalDao = db.vitalDao()
      val obsDao = db.clinicalObservationDao()
      val updateDao = db.clinicalUpdateDao()
      val labDao = db.labResultDao()

      // Patient 1 : Cas pédagogique Respiratoire
      val p1Id = patientDao.insertPatient(
        PatientEntity(
          fullNameOrAlias = "Mme Claire B. (Cas Pédagogique)",
          age = 68,
          gender = "Femme",
          admissionDate = "2026-11-01",
          department = "Pneumologie",
          roomNumber = "Chambre 214",
          primaryCondition = "Pneumopathie franche lobaire aiguë (PFLA)"
        )
      )

      // Vitals for Patient 1 over last 24h
      val now = System.currentTimeMillis()
      vitalDao.insertVital(
        VitalEntity(
          patientId = p1Id,
          temperature = 39.2f,
          systolicBp = 115,
          diastolicBp = 70,
          heartRate = 104,
          respiratoryRate = 24,
          oxygenSaturation = 92,
          bloodGlucose = 1.15f,
          weight = 64f,
          height = 162f,
          bmi = 24.4f,
          alertLevel = "MODERATE",
          pedagogicalNote = "Hyperthermie franche fébrile + polypnée (FR 24/min) et désaturation modérée (SpO2 92%). Contexte évocateur d'infection pulmonaire aiguë.",
          precautions = "Mise sous oxygénothérapie nasale titrée (2 L/min, objectif SpO2 >= 95%), hémocultures avant antibiothérapie, radiographie thoracique.",
          timestamp = now - 86400000L,
          formattedDateTime = "01/11/2026 08:30"
        )
      )
      vitalDao.insertVital(
        VitalEntity(
          patientId = p1Id,
          temperature = 38.6f,
          systolicBp = 120,
          diastolicBp = 75,
          heartRate = 96,
          respiratoryRate = 21,
          oxygenSaturation = 95,
          bloodGlucose = 1.10f,
          alertLevel = "MODERATE",
          pedagogicalNote = "Amélioration sous antibiothérapie et oxygène. Fréquence respiratoire qui régresse.",
          precautions = "Surveillance du score CRB65 et auscultation pulmonaire.",
          timestamp = now - 43200000L,
          formattedDateTime = "01/11/2026 20:00"
        )
      )
      vitalDao.insertVital(
        VitalEntity(
          patientId = p1Id,
          temperature = 37.8f,
          systolicBp = 125,
          diastolicBp = 78,
          heartRate = 84,
          respiratoryRate = 18,
          oxygenSaturation = 97,
          bloodGlucose = 1.05f,
          alertLevel = "NORMAL",
          pedagogicalNote = "Apyrexie obtenue, paramètres hémodynamiques et respiratoires stabilisés.",
          precautions = "Poursuite du traitement per os après relais si amélioration confirmée à 48h.",
          timestamp = now - 7200000L,
          formattedDateTime = "02/11/2026 08:00"
        )
      )

      // Observation médicale initiale
      obsDao.insertObservation(
        ClinicalObservationEntity(
          patientId = p1Id,
          admissionReason = "Fièvre élevée brutale, toux productive avec expectorations rouillées et point de côté thoracique droit.",
          historyOfIllness = "Début brutal il y a 48 heures par des frissons solennels suivis d'une ascension thermique à 39.5°C. Apparition concomitante d'une douleur thoracique basithoracique droite de type pleurétique, exacerbée par l'inspiration profonde et la toux. Toux d'abord sèche puis productive avec crachats striés de sang (aspect rouillé). Pas de contage viral rapporté.",
          pastMedicalHistory = "HTA essentielle sous IEC depuis 6 ans. Pas d'asthme ni de BPCO.",
          pastSurgicalHistory = "Appendicectomie dans l'enfance.",
          familyHistory = "Pas d'antécédent respiratoire ou néoplasique familial notable.",
          allergies = "Aucune allergie médicamenteuse connue.",
          lifestyle = "Non fumeuse, pas de consommation d'alcool.",
          generalExam = "Patiente consciente, orientée (GCS 15/15), état général conservé (OMS 1). Sueurs profuses, polypnéique au repos. Température 39.2°C, TA 115/70 mmHg, FC 104 bpm régulière, FR 24/min, SpO2 92% à l'air ambiant.",
          cardiovascularExam = "Bruits du cœur réguliers, sans souffle ni frottement péricardique. Pouls périphériques perçus et symétriques. Pas de turgescence jugulaire ni de reflux hépato-jugulaire. Absence d'œdème des membres inférieurs.",
          respiratoryExam = "Inspection : polypnée superficielle sans tirage intercostal. Palpation : augmentation des vibrations vocales à la base pulmonaire droite. Percussion : matité franche basithoracique droite. Auscultation : foyer de râles crépitants en fin d'inspiration au niveau du lobe inférieur droit, associés à un souffle tubaire au sommet de la condensation.",
          digestiveExam = "Abdomen souple, dépressible, indolore. Pas d'hépatomégalie ni de splénomégalie. Bruits hydro-aériques présents normaux.",
          neurologicalExam = "Pas de déficit sensitivo-moteur focal. Pas de raideur de nuque ni de signes méningés.",
          locomotorExam = "Pas d'arthrite, mobilité articulaire conservée.",
          urinaryExam = "Diurèse conservée (> 1 ml/kg/h). Urines claires.",
          complementaryExams = "NFS : Hyperleucocytose à 14 500/mm³ avec 86% de PNN. CRP : 142 mg/L. Radiographie thoracique de face : opacité alvéolaire systématisée dense et homogène du lobe inférieur droit avec bronchogramme aérique net.",
          clinicalSynthesis = "Patiente de 68 ans présentant un syndrome de condensation pulmonaire parenchymateuse fébrile aiguë du lobe inférieur droit, sans critère de gravité immédiat (CRB65 = 1 pour âge >= 65 ans).",
          diagnosticHypotheses = "1. Pneumopathie Franche Lobaire Aiguë (PFLA) à Streptococcus pneumoniae (pneumocoque) — hypothèse principale hautement probable.\n2. Embolie pulmonaire fébrile avec infarctus pulmonaire (moins probable vu la présence du bronchogramme aérique et l'expectoration rouillée typique).",
          generatedDraft = "Observation rédigée automatiquement par EL VITA.",
          finalizedText = "OBSERVATION MÉDICALE INITIALE\n\nPATIENTE : Mme Claire B., 68 ans, admise en Pneumologie.\n\nMOTIF D'HOSPITALISATION :\nSyndrome fébrile aigu associé à une douleur thoracique basithoracique droite et toux productive.\n\nHISTOIRE DE LA MALADIE :\nDébut brutal il y a 48 heures marqué par des frissons solennels puis une fièvre à 39.5°C, accompagnée d'un point de côté thoracique droit majoré par l'inspiration et d'une expectoration rouillée caractéristique.\n\nEXAMEN CLINIQUE :\nÀ l'admission, patiente fébrile à 39.2°C, polypnéique à 24/min, SpO2 92% en air libre. L'examen respiratoire objective un syndrome de condensation alvéolaire systématisé du lobe inférieur droit associant matité franche, augmentation des vibrations vocales, foyer de râles crépitants télé-inspiratoires et souffle tubaire.\n\nPARACLINIQUE :\nSyndrome inflammatoire biologique franc (GB 14 500/mm³ à prédominance de PNN, CRP 142 mg/L). Radiographie thoracique confirmant l'opacité alvéolaire lobaire inférieure droite avec bronchogramme aérique.\n\nCONCLUSION & CONDUITE À TENIR :\nTableau typique de PFLA à pneumocoque. Mise sous Amoxicilline 1g x 3/jour IV, oxygénothérapie nasale titrée à 2 L/min, antalgiques et surveillance clinique pluriquotidienne.",
          version = 1
        )
      )

      // Clinical Daily Update J2
      updateDao.insertUpdate(
        ClinicalUpdateEntity(
          patientId = p1Id,
          hospitalDay = "J2",
          date = "02/11/2026",
          dailyClinicalPoint = "Nuit calme sans complication. Disparition des frissons. La douleur pleurétique s'est nettement atténuée sous paracétamol.",
          generalState = "Bon état général, patiente assise au lit, souriante. Apyretique ce matin (T° 37.8°C).",
          physicalExamApparatus = "Respiratoire : Diminution de l'intensité du souffle tubaire, persistance de quelques râles crépitants de résolution à la base droite. SpO2 à 97% sous 1 L/min d'O2. Hémodynamique stable (TA 125/78, FC 84 bpm). Abdomen souple.",
          newComplementaryExams = "Hémocultures en cours : négatives à 24h. Antigénurie pneumococcique positive confirmant le diagnostic étiologique.",
          clinicalConclusion = "Évolution très favorable sous antibiothérapie adaptée à J2 de la prise en charge.",
          treatmentAndPlan = "Poursuite de l'Amoxicilline 1g x 3/jour. Sevrage progressif de l'oxygène prévu dans l'après-midi. Lever autorisé au fauteuil.",
          finalizedText = "POINT CLINIQUE ET ÉVOLUTION À J2 D'HOSPITALISATION\n\nPatient : Mme Claire B. (Chambre 214 - Pneumologie)\n\nÉvolution clinique très favorable sous antibiothérapie ciblée. La patiente est apyrétique, la douleur pleurétique a disparu et l'auscultation montre des crépitants de décrudescence. L'antigénurie pneumococcique positive confirme l'étiologie pneumococcique. Poursuite du protocole actuel avec désescalade de l'oxygénothérapie."
        )
      )

      // Lab results
      labDao.insertLabResult(
        LabResultEntity(
          patientId = p1Id,
          testType = "NFS",
          date = "01/11/2026",
          summaryTitle = "NFS d'admission : Hyperleucocytose à PNN",
          valuesMapJson = "{\"hb\":13.4,\"ht\":40.2,\"gr\":4.6,\"gb\":14.5,\"pnn\":12.4,\"lympho\":1.2,\"mono\":0.6,\"eosino\":0.1,\"baso\":0.04,\"plt\":265}",
          interpretationText = "Hyperleucocytose franche à 14.5 G/L (norme 4.0 - 10.0) à prédominance très nette de polynucléaires neutrophiles (12.4 G/L soit 85%), évocatrice d'une infection bactérienne aiguë systémique. Hémoglobine et plaquettes dans les limites normales.",
          alertLevel = "MODERATE"
        )
      )

      labDao.insertLabResult(
        LabResultEntity(
          patientId = p1Id,
          testType = "UE_CREAT",
          date = "01/11/2026",
          summaryTitle = "Ionogramme sanguin et fonction rénale",
          valuesMapJson = "{\"urea\":6.2,\"creat\":72,\"na\":139,\"k\":4.1,\"cl\":102,\"hco3\":24}",
          interpretationText = "Ionogramme sanguin normal. Fonction rénale strictement conservée avec créatininémie à 72 µmol/L (Clairance estimée Cockcroft = 76 ml/min). Kaliémie à 4.1 mmol/L sans anomalie.",
          alertLevel = "NORMAL"
        )
      )

      // Patient 2 : Cas pédagogique Cardiologie
      val p2Id = patientDao.insertPatient(
        PatientEntity(
          fullNameOrAlias = "M. Marc T. (Cas Pédagogique)",
          age = 54,
          gender = "Homme",
          admissionDate = "2026-11-02",
          department = "Cardiologie",
          roomNumber = "Chambre 105",
          primaryCondition = "Syndrome Coronarien Aigu (SCA ST-)"
        )
      )

      vitalDao.insertVital(
        VitalEntity(
          patientId = p2Id,
          temperature = 36.8f,
          systolicBp = 158,
          diastolicBp = 96,
          heartRate = 88,
          respiratoryRate = 16,
          oxygenSaturation = 98,
          bloodGlucose = 1.35f,
          alertLevel = "MODERATE",
          pedagogicalNote = "Hypertension artérielle grade 2 (158/96 mmHg). Fréquence cardiaque limite haute. Paramètres respiratoires normaux.",
          precautions = "Scope ECG continu, troponine ultrasensible répétée à H0 et H3, repos strict au lit, évaluation de la douleur (EVA).",
          timestamp = now - 3600000L,
          formattedDateTime = "02/11/2026 09:15"
        )
      )
    }
  }
}
