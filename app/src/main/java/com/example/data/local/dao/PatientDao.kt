package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.example.data.local.entities.PatientEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PatientDao {
  @Query("SELECT * FROM patients ORDER BY createdAt DESC")
  fun getAllPatients(): Flow<List<PatientEntity>>

  @Query("SELECT * FROM patients WHERE id = :id LIMIT 1")
  suspend fun getPatientById(id: Long): PatientEntity?

  @Query("SELECT * FROM patients WHERE id = :id LIMIT 1")
  fun observePatientById(id: Long): Flow<PatientEntity?>

  @Query("SELECT * FROM patients WHERE fullNameOrAlias LIKE '%' || :query || '%' OR department LIKE '%' || :query || '%' ORDER BY createdAt DESC")
  fun searchPatients(query: String): Flow<List<PatientEntity>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertPatient(patient: PatientEntity): Long

  @Update
  suspend fun updatePatient(patient: PatientEntity)

  @Delete
  suspend fun deletePatient(patient: PatientEntity)

  @Query("SELECT COUNT(*) FROM patients")
  suspend fun getPatientCount(): Int
}
