package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.example.data.local.entities.ClinicalUpdateEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ClinicalUpdateDao {
  @Query("SELECT * FROM clinical_updates WHERE patientId = :patientId ORDER BY createdAt DESC")
  fun getUpdatesForPatient(patientId: Long): Flow<List<ClinicalUpdateEntity>>

  @Query("SELECT * FROM clinical_updates WHERE id = :id LIMIT 1")
  suspend fun getUpdateById(id: Long): ClinicalUpdateEntity?

  @Query("SELECT * FROM clinical_updates ORDER BY createdAt DESC")
  fun getAllUpdates(): Flow<List<ClinicalUpdateEntity>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertUpdate(update: ClinicalUpdateEntity): Long

  @Update
  suspend fun updateUpdate(update: ClinicalUpdateEntity)

  @Delete
  suspend fun deleteUpdate(update: ClinicalUpdateEntity)

  @Query("SELECT COUNT(*) FROM clinical_updates")
  suspend fun getUpdateCount(): Int
}
