package com.example.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
  tableName = "vitals",
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
data class VitalEntity(
  @PrimaryKey(autoGenerate = true)
  val id: Long = 0,
  val patientId: Long? = null,
  val temperature: Float? = null,           // °C
  val systolicBp: Int? = null,              // mmHg
  val diastolicBp: Int? = null,             // mmHg
  val heartRate: Int? = null,               // bpm
  val respiratoryRate: Int? = null,         // cycles/min
  val oxygenSaturation: Int? = null,        // %
  val bloodGlucose: Float? = null,          // g/L
  val weight: Float? = null,                // kg
  val height: Float? = null,                // cm
  val bmi: Float? = null,                   // kg/m²
  val comment: String = "",
  val alertLevel: String = "NORMAL",        // "NORMAL", "MODERATE", "CRITICAL"
  val pedagogicalNote: String = "",
  val precautions: String = "",
  val timestamp: Long = System.currentTimeMillis(),
  val formattedDateTime: String = ""
)
