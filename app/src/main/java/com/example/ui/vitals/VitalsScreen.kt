package com.example.ui.vitals

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.entities.VitalEntity
import com.example.ui.components.AlertLevelChip
import com.example.ui.components.LegalDisclaimerBanner
import com.example.ui.components.VitalsLineChart
import com.example.ui.theme.AlertGreen
import com.example.ui.theme.AlertOrange
import com.example.ui.theme.AlertRed
import com.example.ui.theme.MedicalBlue
import com.example.ui.theme.MedicalTeal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VitalsScreen(
  viewModel: VitalsViewModel,
  initialTab: Int = 0,
  modifier: Modifier = Modifier
) {
  val context = LocalContext.current
  val state by viewModel.state.collectAsState()
  var selectedTab by remember { mutableIntStateOf(initialTab) }

  Column(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
  ) {
    // Header Bar
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.primary)
        .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
      Column {
        Text(
          text = "Constantes & Paramètres Vitaux",
          style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
          color = Color.White
        )
        Text(
          text = "Saisie, interprétation clinique et courbes d'évolution",
          style = MaterialTheme.typography.bodySmall,
          color = Color.White.copy(alpha = 0.8f)
        )
      }
    }

    // Top Tabs: Saisie vs Historique
    TabRow(
      selectedTabIndex = selectedTab,
      containerColor = MaterialTheme.colorScheme.surface,
      contentColor = MaterialTheme.colorScheme.primary
    ) {
      Tab(
        selected = selectedTab == 0,
        onClick = { selectedTab = 0 },
        text = { Text("Saisie des Constantes", fontWeight = FontWeight.SemiBold) }
      )
      Tab(
        selected = selectedTab == 1,
        onClick = { selectedTab = 1 },
        text = { Text("Historique & Courbes", fontWeight = FontWeight.SemiBold) }
      )
    }

    if (selectedTab == 0) {
      // ══════════════════════════════════════════════════════
      // MODULE MESURES : FORMULAIRE DE SAISIE ET INTERPRÉTATION
      // ══════════════════════════════════════════════════════
      LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
      ) {
        item {
          LegalDisclaimerBanner()
        }

        // Patient Selector
        item {
          Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
          ) {
            Column(modifier = Modifier.padding(14.dp)) {
              Text(
                text = "Patient associé (Optionnel)",
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface
              )
              Spacer(modifier = Modifier.height(8.dp))

              var expanded by remember { mutableStateOf(false) }
              val selectedPatient = state.patients.find { it.id == state.selectedPatientId }

              ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
              ) {
                OutlinedTextField(
                  value = selectedPatient?.let { "${it.fullNameOrAlias} (${it.department})" } ?: "Mesure ponctuelle (sans dossier associé)",
                  onValueChange = {},
                  readOnly = true,
                  trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                  modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                  shape = RoundedCornerShape(10.dp)
                )
                ExposedDropdownMenu(
                  expanded = expanded,
                  onDismissRequest = { expanded = false }
                ) {
                  DropdownMenuItem(
                    text = { Text("Mesure ponctuelle (sans dossier associé)") },
                    onClick = {
                      viewModel.onPatientSelected(null)
                      expanded = false
                    }
                  )
                  state.patients.forEach { patient ->
                    DropdownMenuItem(
                      text = { Text("${patient.fullNameOrAlias} — ${patient.department} (${patient.roomNumber})") },
                      onClick = {
                        viewModel.onPatientSelected(patient.id)
                        expanded = false
                      }
                    )
                  }
                }
              }
            }
          }
        }

        // Physiological inputs grid
        item {
          Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
          ) {
            Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
              Text(
                text = "Paramètres Physiologiques",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface
              )

              // Température & SpO2
              Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                OutlinedTextField(
                  value = state.tempInput,
                  onValueChange = { viewModel.updateFormFields(temp = it) },
                  label = { Text("Température (°C)") },
                  placeholder = { Text("ex: 37.2") },
                  keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                  modifier = Modifier.weight(1f).testTag("input_temperature"),
                  shape = RoundedCornerShape(10.dp),
                  singleLine = true
                )
                OutlinedTextField(
                  value = state.spo2Input,
                  onValueChange = { viewModel.updateFormFields(spo2 = it) },
                  label = { Text("SpO2 (%)") },
                  placeholder = { Text("ex: 98") },
                  keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                  modifier = Modifier.weight(1f).testTag("input_spo2"),
                  shape = RoundedCornerShape(10.dp),
                  singleLine = true
                )
              }

              // Pression Artérielle (PAS / PAD)
              Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                OutlinedTextField(
                  value = state.pasInput,
                  onValueChange = { viewModel.updateFormFields(pas = it) },
                  label = { Text("PAS (mmHg)") },
                  placeholder = { Text("ex: 120") },
                  keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                  modifier = Modifier.weight(1f).testTag("input_pas"),
                  shape = RoundedCornerShape(10.dp),
                  singleLine = true
                )
                OutlinedTextField(
                  value = state.padInput,
                  onValueChange = { viewModel.updateFormFields(pad = it) },
                  label = { Text("PAD (mmHg)") },
                  placeholder = { Text("ex: 80") },
                  keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                  modifier = Modifier.weight(1f).testTag("input_pad"),
                  shape = RoundedCornerShape(10.dp),
                  singleLine = true
                )
              }

              // Fréquence Cardiaque & Fréquence Respiratoire
              Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                OutlinedTextField(
                  value = state.hrInput,
                  onValueChange = { viewModel.updateFormFields(hr = it) },
                  label = { Text("Fréquence Cardiaque (bpm)") },
                  placeholder = { Text("ex: 75") },
                  keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                  modifier = Modifier.weight(1f).testTag("input_hr"),
                  shape = RoundedCornerShape(10.dp),
                  singleLine = true
                )
                OutlinedTextField(
                  value = state.rrInput,
                  onValueChange = { viewModel.updateFormFields(rr = it) },
                  label = { Text("Fréq. Resp. (/min)") },
                  placeholder = { Text("ex: 16") },
                  keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                  modifier = Modifier.weight(1f).testTag("input_rr"),
                  shape = RoundedCornerShape(10.dp),
                  singleLine = true
                )
              }

              // Glycémie
              OutlinedTextField(
                value = state.glucoseInput,
                onValueChange = { viewModel.updateFormFields(glucose = it) },
                label = { Text("Glycémie capillaire (g/L)") },
                placeholder = { Text("ex: 1.05") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth().testTag("input_glucose"),
                shape = RoundedCornerShape(10.dp),
                singleLine = true
              )

              // Poids & Taille -> IMC automatique
              Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                OutlinedTextField(
                  value = state.weightInput,
                  onValueChange = { viewModel.updateFormFields(weight = it) },
                  label = { Text("Poids (kg)") },
                  placeholder = { Text("ex: 72") },
                  keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                  modifier = Modifier.weight(1f).testTag("input_weight"),
                  shape = RoundedCornerShape(10.dp),
                  singleLine = true
                )
                OutlinedTextField(
                  value = state.heightInput,
                  onValueChange = { viewModel.updateFormFields(height = it) },
                  label = { Text("Taille (cm)") },
                  placeholder = { Text("ex: 175") },
                  keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                  modifier = Modifier.weight(1f).testTag("input_height"),
                  shape = RoundedCornerShape(10.dp),
                  singleLine = true
                )
              }

              // Commentaire clinique facultatif
              OutlinedTextField(
                value = state.commentInput,
                onValueChange = { viewModel.updateFormFields(comment = it) },
                label = { Text("Commentaire ou contexte clinique (optionnel)") },
                placeholder = { Text("ex: Mesure prise au repos, patient sous 2L O2...") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                maxLines = 3
              )
            }
          }
        }

        // Live Pedagogical Interpretation Card
        state.currentInterpretation?.let { interp ->
          item {
            Card(
              modifier = Modifier.fillMaxWidth(),
              shape = RoundedCornerShape(16.dp),
              colors = CardDefaults.cardColors(
                containerColor = when (interp.alertLevel) {
                  "CRITICAL" -> AlertRed.copy(alpha = 0.08f)
                  "MODERATE" -> AlertOrange.copy(alpha = 0.08f)
                  else -> AlertGreen.copy(alpha = 0.08f)
                }
              ),
              border = androidx.compose.foundation.BorderStroke(
                1.dp,
                when (interp.alertLevel) {
                  "CRITICAL" -> AlertRed.copy(alpha = 0.4f)
                  "MODERATE" -> AlertOrange.copy(alpha = 0.4f)
                  else -> AlertGreen.copy(alpha = 0.4f)
                }
              )
            ) {
              Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(
                  modifier = Modifier.fillMaxWidth(),
                  horizontalArrangement = Arrangement.SpaceBetween,
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Text(
                    text = interp.alertTitle,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurface
                  )
                  AlertLevelChip(alertLevel = interp.alertLevel)
                }

                // Computed metrics (PAM, IMC)
                if (interp.computedMetrics.isNotEmpty()) {
                  Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    interp.computedMetrics.forEach { (name, value) ->
                      Box(
                        modifier = Modifier
                          .clip(RoundedCornerShape(8.dp))
                          .background(MaterialTheme.colorScheme.surface)
                          .padding(horizontal = 8.dp, vertical = 4.dp)
                      ) {
                        Text(
                          text = "$name : $value",
                          style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                          color = MaterialTheme.colorScheme.primary
                        )
                      }
                    }
                  }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                  text = "Explications Pédagogiques Synthétiques :",
                  style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                )
                interp.explanations.forEach { exp ->
                  Row(modifier = Modifier.padding(start = 4.dp)) {
                    Text(text = "• ", fontWeight = FontWeight.Bold)
                    Text(text = exp, style = MaterialTheme.typography.bodySmall)
                  }
                }

                // 8-Point Systematic Clinical Evaluation
                if (interp.systematicPoints.isNotEmpty()) {
                  Spacer(modifier = Modifier.height(8.dp))
                  Text(
                    text = "Grille d'Évaluation Clinique Systématique (8 points) :",
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.primary
                  )

                  interp.systematicPoints.forEach { point ->
                    Card(
                      modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                      shape = RoundedCornerShape(10.dp),
                      colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                    ) {
                      Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Row(
                          modifier = Modifier.fillMaxWidth(),
                          horizontalArrangement = Arrangement.SpaceBetween,
                          verticalAlignment = Alignment.CenterVertically
                        ) {
                          Text(
                            text = point.parameterName,
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onSurface
                          )
                          AlertLevelChip(alertLevel = point.alertLevel)
                        }

                        Text(
                          text = "1. Statut clinique : ${point.status}",
                          style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold)
                        )
                        Text(
                          text = "2. Signification physiologique : ${point.physiologicalSignificance}",
                          style = MaterialTheme.typography.bodySmall
                        )
                        Text(
                          text = "3. Physiopathologie : ${point.physiopathology}",
                          style = MaterialTheme.typography.bodySmall
                        )
                        Text(
                          text = "4. Étiologies : ${point.etiologies.joinToString(", ")}",
                          style = MaterialTheme.typography.bodySmall
                        )
                        Text(
                          text = "5. Signes à rechercher : ${point.clinicalSignsToSearch.joinToString(", ")}",
                          style = MaterialTheme.typography.bodySmall
                        )
                        Text(
                          text = "6. Examens utiles : ${point.usefulComplementaryExams.joinToString(", ")}",
                          style = MaterialTheme.typography.bodySmall
                        )
                        Text(
                          text = "7. Niveau de gravité : ${point.alertLevel}",
                          style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold)
                        )
                        Text(
                          text = "8. Conduite à tenir (CAT) : ${point.emergencyCare}",
                          style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold, color = AlertRed)
                        )
                      }
                    }
                  }
                }

                if (interp.precautions.isNotEmpty()) {
                  Spacer(modifier = Modifier.height(4.dp))
                  Text(
                    text = "Précautions Cliniques Recommandées :",
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold, color = AlertRed)
                  )
                  interp.precautions.forEach { prec ->
                    Row(modifier = Modifier.padding(start = 4.dp)) {
                      Text(text = "→ ", fontWeight = FontWeight.Bold, color = AlertRed)
                      Text(text = prec, style = MaterialTheme.typography.bodySmall)
                    }
                  }
                }
              }
            }
          }
        }

        // Save Button
        item {
          Button(
            onClick = {
              viewModel.saveVitals {
                Toast.makeText(context, "Constantes enregistrées avec succès !", Toast.LENGTH_SHORT).show()
                selectedTab = 1 // Switch to History
              }
            },
            modifier = Modifier
              .fillMaxWidth()
              .height(52.dp)
              .testTag("save_vitals_button"),
            enabled = state.currentInterpretation != null,
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
          ) {
            Icon(imageVector = Icons.Default.Save, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = "ENREGISTRER LA MESURE",
              style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
          }
        }
      }
    } else {
      // ══════════════════════════════════════════════════════
      // MODULE HISTORIQUE : COURBES DISTINCTES & LISTE DÉTAILLÉE
      // ══════════════════════════════════════════════════════
      val filteredVitals = viewModel.getFilteredVitals()
      val bpPoints = viewModel.getBpChartPoints(filteredVitals)
      val hrPoints = viewModel.getHrChartPoints(filteredVitals)
      val tempPoints = viewModel.getTempChartPoints(filteredVitals)
      val spo2Points = viewModel.getSpo2ChartPoints(filteredVitals)

      LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
      ) {
        // Time Filters Bar
        item {
          Column {
            Text(
              text = "Période d'observation :",
              style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(6.dp))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
              items(HistoryFilter.values()) { filter ->
                val isSelected = filter == state.selectedFilter
                Box(
                  modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                      if (isSelected) MaterialTheme.colorScheme.primary
                      else MaterialTheme.colorScheme.surface
                    )
                    .border(
                      1.dp,
                      if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
                      RoundedCornerShape(20.dp)
                    )
                    .clickable { viewModel.onFilterSelected(filter) }
                    .padding(horizontal = 14.dp, vertical = 6.dp)
                ) {
                  Text(
                    text = filter.label,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
                    color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
                  )
                }
              }
            }
          }
        }

        // Patient filter indicator
        item {
          val pat = state.patients.find { it.id == state.selectedPatientId }
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = pat?.let { "Filtré par patient : ${it.fullNameOrAlias}" } ?: "Affichage de toutes les constantes",
              style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium),
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            if (state.selectedPatientId != null) {
              Text(
                text = "Tous les patients",
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                  .clickable { viewModel.onPatientSelected(null) }
                  .padding(4.dp)
              )
            }
          }
        }

        // 1. COURBE PRESSION ARTÉRIELLE (SYSTOLIQUE & DIASTOLIQUE SÉPARÉES)
        item {
          VitalsLineChart(
            title = "Pression Artérielle (PAS & PAD)",
            unit = "mmHg",
            points = bpPoints,
            primaryColor = MedicalBlue,
            secondaryColor = MedicalTeal,
            isDualCurve = true,
            primaryLabel = "PAS (Systolique)",
            secondaryLabel = "PAD (Diastolique)"
          )
        }

        // 2. COURBE FRÉQUENCE CARDIAQUE
        item {
          VitalsLineChart(
            title = "Fréquence Cardiaque",
            unit = "bpm",
            points = hrPoints,
            primaryColor = Color(0xFFEF4444)
          )
        }

        // 3. COURBE TEMPÉRATURE CORPORELLE
        item {
          VitalsLineChart(
            title = "Température Corporelle",
            unit = "°C",
            points = tempPoints,
            primaryColor = AlertOrange
          )
        }

        // 4. COURBE SATURATION EN OXYGÈNE
        item {
          VitalsLineChart(
            title = "Saturation en Oxygène (SpO2)",
            unit = "%",
            points = spo2Points,
            primaryColor = Color(0xFF0284C7)
          )
        }

        // HISTORICAL LIST VIEW
        item {
          Text(
            text = "Relevés Détaillés (${filteredVitals.size})",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onBackground
          )
        }

        if (filteredVitals.isEmpty()) {
          item {
            Card(
              modifier = Modifier.fillMaxWidth(),
              colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
              Box(modifier = Modifier.fillMaxWidth().padding(24.dp), contentAlignment = Alignment.Center) {
                Text(
                  text = "Aucune mesure pour cette période",
                  style = MaterialTheme.typography.bodyMedium,
                  color = MaterialTheme.colorScheme.onSurfaceVariant
                )
              }
            }
          }
        } else {
          items(filteredVitals.reversed()) { vital ->
            VitalHistoryItem(
              vital = vital,
              patientName = state.patients.find { it.id == vital.patientId }?.fullNameOrAlias,
              onDelete = { viewModel.deleteVital(vital) }
            )
          }
        }
      }
    }
  }
}

@Composable
fun VitalHistoryItem(
  vital: VitalEntity,
  patientName: String?,
  onDelete: () -> Unit
) {
  Card(
    modifier = Modifier.fillMaxWidth(),
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
  ) {
    Column(modifier = Modifier.padding(14.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column {
          Text(
            text = vital.formattedDateTime.ifBlank { "Date non renseignée" },
            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary
          )
          if (patientName != null) {
            Text(
              text = "Patient : $patientName",
              style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.5.sp),
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )
          }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
          AlertLevelChip(alertLevel = vital.alertLevel)
          Spacer(modifier = Modifier.width(6.dp))
          IconButton(
            onClick = onDelete,
            modifier = Modifier.size(28.dp)
          ) {
            Icon(
              imageVector = Icons.Default.Delete,
              contentDescription = "Supprimer",
              tint = MaterialTheme.colorScheme.error.copy(alpha = 0.7f),
              modifier = Modifier.size(18.dp)
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(8.dp))

      // Values row
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
      ) {
        if (vital.temperature != null) {
          Text("T°: ${vital.temperature}°C", style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold))
        }
        if (vital.systolicBp != null && vital.diastolicBp != null) {
          Text("PA: ${vital.systolicBp}/${vital.diastolicBp} mmHg", style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold))
        }
        if (vital.heartRate != null) {
          Text("FC: ${vital.heartRate} bpm", style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold))
        }
        if (vital.oxygenSaturation != null) {
          Text("SpO2: ${vital.oxygenSaturation}%", style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold))
        }
      }

      if (vital.pedagogicalNote.isNotBlank()) {
        Spacer(modifier = Modifier.height(6.dp))
        Text(
          text = vital.pedagogicalNote,
          style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.5.sp),
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )
      }
    }
  }
}
