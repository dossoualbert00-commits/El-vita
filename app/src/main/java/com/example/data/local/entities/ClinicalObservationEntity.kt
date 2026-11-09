package com.example.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
  tableName = "clinical_observations",
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
data class ClinicalObservationEntity(
  @PrimaryKey(autoGenerate = true)
  val id: Long = 0,
  val patientId: Long,
  val admissionReason: String = "",
  val historyOfIllness: String = "",
  val pastMedicalHistory: String = "",
  val pastSurgicalHistory: String = "",
  val familyHistory: String = "",
  val allergies: String = "",
  val lifestyle: String = "",
  val generalExam: String = "",
  val cardiovascularExam: String = "",
  val respiratoryExam: String = "",
  val digestiveExam: String = "",
  val neurologicalExam: String = "",
  val locomotorExam: String = "",
  val urinaryExam: String = "",
  val otherExams: String = "",
  val complementaryExams: String = "",
  val clinicalSynthesis: String = "",
  val diagnosticHypotheses: String = "",
  val generatedDraft: String = "",
  val finalizedText: String = "",
  val version: Int = 1,
  val createdAt: Long = System.currentTimeMillis(),
  val updatedAt: Long = System.currentTimeMillis()
)
