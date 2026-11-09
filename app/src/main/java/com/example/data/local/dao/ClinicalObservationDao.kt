package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.example.data.local.entities.ClinicalObservationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ClinicalObservationDao {
  @Query("SELECT * FROM clinical_observations WHERE patientId = :patientId ORDER BY version DESC")
  fun getObservationsForPatient(patientId: Long): Flow<List<ClinicalObservationEntity>>

  @Query("SELECT * FROM clinical_observations WHERE patientId = :patientId ORDER BY version DESC LIMIT 1")
  suspend fun getLatestObservationForPatient(patientId: Long): ClinicalObservationEntity?

  @Query("SELECT * FROM clinical_observations WHERE id = :id LIMIT 1")
  suspend fun getObservationById(id: Long): ClinicalObservationEntity?

  @Query("SELECT * FROM clinical_observations ORDER BY updatedAt DESC")
  fun getAllObservations(): Flow<List<ClinicalObservationEntity>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertObservation(observation: ClinicalObservationEntity): Long

  @Update
  suspend fun updateObservation(observation: ClinicalObservationEntity)

  @Delete
  suspend fun deleteObservation(observation: ClinicalObservationEntity)

  @Query("SELECT COUNT(*) FROM clinical_observations")
  suspend fun getObservationCount(): Int
}
