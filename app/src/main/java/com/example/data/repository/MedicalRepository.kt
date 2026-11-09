package com.example.data.repository

import com.example.data.local.AppDatabase
import com.example.data.local.entities.ClinicalObservationEntity
import com.example.data.local.entities.ClinicalUpdateEntity
import com.example.data.local.entities.LabResultEntity
import com.example.data.local.entities.PatientEntity
import com.example.data.local.entities.VitalEntity
import kotlinx.coroutines.flow.Flow

class MedicalRepository(private val database: AppDatabase) {

  // Patients
  fun getAllPatients(): Flow<List<PatientEntity>> = database.patientDao().getAllPatients()

  fun searchPatients(query: String): Flow<List<PatientEntity>> =
    if (query.isBlank()) database.patientDao().getAllPatients()
    else database.patientDao().searchPatients(query)

  fun observePatient(id: Long): Flow<PatientEntity?> = database.patientDao().observePatientById(id)

  suspend fun getPatientById(id: Long): PatientEntity? = database.patientDao().getPatientById(id)

  suspend fun insertPatient(patient: PatientEntity): Long = database.patientDao().insertPatient(patient)

  suspend fun updatePatient(patient: PatientEntity) = database.patientDao().updatePatient(patient)

  suspend fun deletePatient(patient: PatientEntity) = database.patientDao().deletePatient(patient)

  // Vitals
  fun getAllVitals(): Flow<List<VitalEntity>> = database.vitalDao().getAllVitals()

  fun getRecentVitals(limit: Int = 10): Flow<List<VitalEntity>> = database.vitalDao().getRecentVitals(limit)

  fun getVitalsForPatient(patientId: Long): Flow<List<VitalEntity>> = database.vitalDao().getVitalsByPatient(patientId)

  fun getVitalsSince(sinceTimestamp: Long): Flow<List<VitalEntity>> = database.vitalDao().getVitalsSince(sinceTimestamp)

  fun getPatientVitalsSince(patientId: Long, sinceTimestamp: Long): Flow<List<VitalEntity>> =
    database.vitalDao().getPatientVitalsSince(patientId, sinceTimestamp)

  suspend fun insertVital(vital: VitalEntity): Long = database.vitalDao().insertVital(vital)

  suspend fun deleteVital(vital: VitalEntity) = database.vitalDao().deleteVital(vital)

  // Clinical Observations
  fun getObservationsForPatient(patientId: Long): Flow<List<ClinicalObservationEntity>> =
    database.clinicalObservationDao().getObservationsForPatient(patientId)

  suspend fun getLatestObservationForPatient(patientId: Long): ClinicalObservationEntity? =
    database.clinicalObservationDao().getLatestObservationForPatient(patientId)

  suspend fun getObservationById(id: Long): ClinicalObservationEntity? =
    database.clinicalObservationDao().getObservationById(id)

  fun getAllObservations(): Flow<List<ClinicalObservationEntity>> =
    database.clinicalObservationDao().getAllObservations()

  suspend fun insertObservation(observation: ClinicalObservationEntity): Long =
    database.clinicalObservationDao().insertObservation(observation)

  suspend fun updateObservation(observation: ClinicalObservationEntity) =
    database.clinicalObservationDao().updateObservation(observation)

  suspend fun deleteObservation(observation: ClinicalObservationEntity) =
    database.clinicalObservationDao().deleteObservation(observation)

  // Clinical Daily Updates
  fun getUpdatesForPatient(patientId: Long): Flow<List<ClinicalUpdateEntity>> =
    database.clinicalUpdateDao().getUpdatesForPatient(patientId)

  suspend fun getUpdateById(id: Long): ClinicalUpdateEntity? =
    database.clinicalUpdateDao().getUpdateById(id)

  fun getAllUpdates(): Flow<List<ClinicalUpdateEntity>> =
    database.clinicalUpdateDao().getAllUpdates()

  suspend fun insertUpdate(update: ClinicalUpdateEntity): Long =
    database.clinicalUpdateDao().insertUpdate(update)

  suspend fun deleteUpdate(update: ClinicalUpdateEntity) =
    database.clinicalUpdateDao().deleteUpdate(update)

  // Lab Results
  fun getAllLabResults(): Flow<List<LabResultEntity>> = database.labResultDao().getAllLabResults()

  fun getLabResultsForPatient(patientId: Long): Flow<List<LabResultEntity>> =
    database.labResultDao().getLabResultsForPatient(patientId)

  suspend fun insertLabResult(labResult: LabResultEntity): Long =
    database.labResultDao().insertLabResult(labResult)

  suspend fun deleteLabResult(labResult: LabResultEntity) =
    database.labResultDao().deleteLabResult(labResult)

  suspend fun getCounts(): Triple<Int, Int, Int> {
    val patients = database.patientDao().getPatientCount()
    val vitals = database.vitalDao().getVitalCount()
    val obs = database.clinicalObservationDao().getObservationCount()
    return Triple(patients, vitals, obs)
  }
}
