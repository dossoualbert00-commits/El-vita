package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Delete
import com.example.data.local.entities.LabResultEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LabResultDao {
  @Query("SELECT * FROM lab_results ORDER BY createdAt DESC")
  fun getAllLabResults(): Flow<List<LabResultEntity>>

  @Query("SELECT * FROM lab_results WHERE patientId = :patientId ORDER BY createdAt DESC")
  fun getLabResultsForPatient(patientId: Long): Flow<List<LabResultEntity>>

  @Query("SELECT * FROM lab_results WHERE id = :id LIMIT 1")
  suspend fun getLabResultById(id: Long): LabResultEntity?

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertLabResult(labResult: LabResultEntity): Long

  @Delete
  suspend fun deleteLabResult(labResult: LabResultEntity)

  @Query("SELECT COUNT(*) FROM lab_results")
  suspend fun getLabResultCount(): Int
}
