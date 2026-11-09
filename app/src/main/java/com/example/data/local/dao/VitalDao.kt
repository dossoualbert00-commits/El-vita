package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Delete
import com.example.data.local.entities.VitalEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VitalDao {
  @Query("SELECT * FROM vitals ORDER BY timestamp DESC")
  fun getAllVitals(): Flow<List<VitalEntity>>

  @Query("SELECT * FROM vitals WHERE patientId = :patientId ORDER BY timestamp DESC")
  fun getVitalsByPatient(patientId: Long): Flow<List<VitalEntity>>

  @Query("SELECT * FROM vitals ORDER BY timestamp DESC LIMIT :limit")
  fun getRecentVitals(limit: Int): Flow<List<VitalEntity>>

  @Query("SELECT * FROM vitals WHERE timestamp >= :sinceTimestamp ORDER BY timestamp ASC")
  fun getVitalsSince(sinceTimestamp: Long): Flow<List<VitalEntity>>

  @Query("SELECT * FROM vitals WHERE patientId = :patientId AND timestamp >= :sinceTimestamp ORDER BY timestamp ASC")
  fun getPatientVitalsSince(patientId: Long, sinceTimestamp: Long): Flow<List<VitalEntity>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertVital(vital: VitalEntity): Long

  @Delete
  suspend fun deleteVital(vital: VitalEntity)

  @Query("SELECT COUNT(*) FROM vitals")
  suspend fun getVitalCount(): Int
}
