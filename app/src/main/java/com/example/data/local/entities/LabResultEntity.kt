package com.example.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
  tableName = "lab_results",
  foreignKeys = [
    ForeignKey(
      entity = PatientEntity::class,
      parentColumns = ["id"],
      childColumns = ["patientId"],
      onDelete = ForeignKey.SET_NULL
    )
  ],
  indices = [Index(value = ["patientId"])]
)
data class LabResultEntity(
  @PrimaryKey(autoGenerate = true)
  val id: Long = 0,
  val patientId: Long? = null,
  val testType: String,             // "NFS", "UE_CREAT", "GLASGOW"
  val date: String,
  val summaryTitle: String,
  val valuesMapJson: String,        // Raw values representation
  val interpretationText: String,
  val alertLevel: String = "NORMAL", // "NORMAL", "MODERATE", "CRITICAL"
  val createdAt: Long = System.currentTimeMillis()
)
