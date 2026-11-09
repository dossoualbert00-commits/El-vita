package com.example.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "patients")
data class PatientEntity(
  @PrimaryKey(autoGenerate = true)
  val id: Long = 0,
  val fullNameOrAlias: String,
  val age: Int,
  val gender: String, // "Homme", "Femme", "Autre"
  val admissionDate: String,
  val department: String,
  val roomNumber: String,
  val primaryCondition: String = "",
  val createdAt: Long = System.currentTimeMillis()
)
