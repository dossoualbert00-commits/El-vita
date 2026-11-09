package com.example.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
  tableName = "clinical_updates",
  foreignKeys = [
    ForeignKey(
      entity = PatientEntity::class,
      parentColumns = ["id"],
      childColumns = ["patientId"],
      onDelete = ForeignKey.CASCADE
    )
  ],
  indices = [Index(value = ["patientId"])]
)
data class ClinicalUpdateEntity(
  @PrimaryKey(autoGenerate = true)
  val id: Long = 0,
  val patientId: Long,
  val hospitalDay: String = "J1", // e.g. J1, J2, J3
  val date: String = "",
  val dailyClinicalPoint: String = "",
  val generalState: String = "",
  val physicalExamApparatus: String = "",
  val newComplementaryExams: String = "",
  val clinicalConclusion: String = "",
  val treatmentAndPlan: String = "",
  val generatedDraft: String = "",
  val finalizedText: String = "",
  val createdAt: Long = System.currentTimeMillis()
)
