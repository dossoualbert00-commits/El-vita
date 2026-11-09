package com.example.ui.patients

import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.entities.PatientEntity
import com.example.ui.components.AlertLevelChip
import com.example.ui.components.LegalDisclaimerBanner
import com.example.ui.theme.AlertRed
import com.example.ui.theme.MedicalBlue
import com.example.ui.theme.MedicalTeal

@Composable
fun PatientsScreen(
  viewModel: PatientsViewModel,
  onNavigateToNewVitalForPatient: (Long) -> Unit,
  onNavigateToNewObservationForPatient: (Long) -> Unit,
  onNavigateToNewUpdateForPatient: (Long) -> Unit,
  onBack: () -> Unit,
  modifier: Modifier = Modifier
) {
  val context = LocalContext.current
  val patients by viewModel.patientsList.collectAsState()
  val selectedId by viewModel.selectedPatientId.collectAsState()
  val detailState by viewModel.patientDetail.collectAsState()
  val searchQuery by viewModel.searchQuery.collectAsState()

  var showCreateDialog by remember { mutableStateOf(false) }

  if (selectedId != null && detailState.patient != null) {
    // ══════════════════════════════════════════════════════
    // VUE DÉTAILLÉE DU DOSSIER PATIENT INDIVIDUEL
    // ══════════════════════════════════════════════════════
    PatientDetailScreen(
      detailState = detailState,
      onBack = { viewModel.selectPatient(null) },
      onDelete = {
        viewModel.deletePatient(detailState.patient!!) {
          Toast.makeText(context, "Dossier supprimé", Toast.LENGTH_SHORT).show()
        }
      },
      onAddVital = { onNavigateToNewVitalForPatient(selectedId!!) },
      onAddObservation = { onNavigateToNewObservationForPatient(selectedId!!) },
      onAddUpdate = { onNavigateToNewUpdateForPatient(selectedId!!) }
    )
  } else {
    // ══════════════════════════════════════════════════════
    // LISTE GÉNÉRALE DES DOSSIERS PATIENTS
    // ══════════════════════════════════════════════════════
    Box(
      modifier = modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
    ) {
      Column {
        // Header
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
          ) {
            IconButton(onClick = onBack) {
              Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Retour", tint = Color.White)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column {
              Text(
                text = "Dossiers Patients",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                color = Color.White
              )
              Text(
                text = "Centralisation des observations, visites et constantes",
                style = MaterialTheme.typography.bodySmall,
                color = Color.White.copy(alpha = 0.8f)
              )
            }
          }
        }

        LazyColumn(
          modifier = Modifier.fillMaxSize(),
          contentPadding = PaddingValues(16.dp),
          verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
          item { LegalDisclaimerBanner() }

          // Search bar
          item {
            OutlinedTextField(
              value = searchQuery,
              onValueChange = { viewModel.setSearchQuery(it) },
              placeholder = { Text("Rechercher un patient (nom, service, chambre)...") },
              leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = null) },
              modifier = Modifier.fillMaxWidth().testTag("patient_search_input"),
              shape = RoundedCornerShape(12.dp),
              singleLine = true
            )
          }

          item {
            Text(
              text = "Patients Enregistrés (${patients.size})",
              style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
              color = MaterialTheme.colorScheme.onBackground
            )
          }

          if (patients.isEmpty()) {
            item {
              Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
              ) {
                Box(modifier = Modifier.fillMaxWidth().padding(32.dp), contentAlignment = Alignment.Center) {
                  Text(
                    text = "Aucun dossier trouvé.\nTapez sur le bouton '+' pour créer un dossier.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                  )
                }
              }
            }
          } else {
            items(patients) { patient ->
              PatientCardItem(
                patient = patient,
                onClick = { viewModel.selectPatient(patient.id) }
              )
            }
          }
        }
      }

      // FAB to create new patient dossier
      FloatingActionButton(
        onClick = { showCreateDialog = true },
        modifier = Modifier
          .align(Alignment.BottomEnd)
          .padding(24.dp)
          .testTag("create_patient_fab"),
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = Color.White
      ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Nouveau dossier")
      }
    }
  }

  // Create Patient Modal Dialog
  if (showCreateDialog) {
    CreatePatientDialog(
      onDismiss = { showCreateDialog = false },
      onConfirm = { name, age, gender, dept, room, condition ->
        viewModel.createNewPatient(name, age, gender, dept, room, condition) {
          showCreateDialog = false
          Toast.makeText(context, "Dossier patient créé avec succès !", Toast.LENGTH_SHORT).show()
        }
      }
    )
  }
}

@Composable
fun PatientCardItem(patient: PatientEntity, onClick: () -> Unit) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .clickable { onClick() }
      .testTag("patient_card_${patient.id}"),
    shape = RoundedCornerShape(14.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Box(
        modifier = Modifier
          .size(48.dp)
          .clip(CircleShape)
          .background(MaterialTheme.colorScheme.primaryContainer),
        contentAlignment = Alignment.Center
      ) {
        Icon(
          imageVector = Icons.Default.Folder,
          contentDescription = null,
          tint = MaterialTheme.colorScheme.onPrimaryContainer,
          modifier = Modifier.size(24.dp)
        )
      }
      Spacer(modifier = Modifier.width(14.dp))
      Column(modifier = Modifier.weight(1f)) {
        Text(
          text = patient.fullNameOrAlias,
          style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
          color = MaterialTheme.colorScheme.onSurface
        )
        Text(
          text = "${patient.age} ans • ${patient.gender} • ${patient.department} (${patient.roomNumber})",
          style = MaterialTheme.typography.bodySmall,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        if (patient.primaryCondition.isNotBlank()) {
          Spacer(modifier = Modifier.height(2.dp))
          Text(
            text = "Motif : ${patient.primaryCondition}",
            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium),
            color = MaterialTheme.colorScheme.primary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
          )
        }
      }
    }
  }
}

@Composable
fun PatientDetailScreen(
  detailState: PatientDetailState,
  onBack: () -> Unit,
  onDelete: () -> Unit,
  onAddVital: () -> Unit,
  onAddObservation: () -> Unit,
  onAddUpdate: () -> Unit
) {
  val p = detailState.patient ?: return
  var detailSubTab by remember { mutableIntStateOf(0) }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
  ) {
    // Top Bar
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.primary)
        .padding(horizontal = 16.dp, vertical = 14.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          IconButton(onClick = onBack) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Retour", tint = Color.White)
          }
          Spacer(modifier = Modifier.width(4.dp))
          Column {
            Text(
              text = p.fullNameOrAlias,
              style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
              color = Color.White
            )
            Text(
              text = "${p.department} • ${p.roomNumber}",
              style = MaterialTheme.typography.bodySmall,
              color = Color.White.copy(alpha = 0.8f)
            )
          }
        }
        IconButton(onClick = onDelete) {
          Icon(imageVector = Icons.Default.Delete, contentDescription = "Supprimer le dossier", tint = Color.White)
        }
      }
    }

    // Patient Identity Summary Card
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
      shape = RoundedCornerShape(14.dp),
      colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
      Column(modifier = Modifier.padding(14.dp)) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Text(text = "Âge : ${p.age} ans (${p.gender})", style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold))
          Text(text = "Entrée : ${p.admissionDate}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        if (p.primaryCondition.isNotBlank()) {
          Spacer(modifier = Modifier.height(4.dp))
          Text(text = "Diagnostic principal : ${p.primaryCondition}", style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.primary))
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Quick action row
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
          OutlinedButton(
            onClick = onAddVital,
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(8.dp)
          ) {
            Icon(imageVector = Icons.Default.Timeline, contentDescription = null, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(4.dp))
            Text("+ Constante", fontSize = 11.sp)
          }
          OutlinedButton(
            onClick = onAddUpdate,
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(8.dp)
          ) {
            Icon(imageVector = Icons.Default.Update, contentDescription = null, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(4.dp))
            Text("+ Visite", fontSize = 11.sp)
          }
        }
      }
    }

    // Tabs inside Dossier
    ScrollableTabRow(
      selectedTabIndex = detailSubTab,
      containerColor = MaterialTheme.colorScheme.surface,
      contentColor = MaterialTheme.colorScheme.primary,
      edgePadding = 12.dp
    ) {
      Tab(selected = detailSubTab == 0, onClick = { detailSubTab = 0 }, text = { Text("Observation (${detailState.observations.size})") })
      Tab(selected = detailSubTab == 1, onClick = { detailSubTab = 1 }, text = { Text("Visites Quotidiennes (${detailState.updates.size})") })
      Tab(selected = detailSubTab == 2, onClick = { detailSubTab = 2 }, text = { Text("Constantes (${detailState.vitals.size})") })
      Tab(selected = detailSubTab == 3, onClick = { detailSubTab = 3 }, text = { Text("Analyses (${detailState.labResults.size})") })
    }

    LazyColumn(
      modifier = Modifier.fillMaxSize(),
      contentPadding = PaddingValues(16.dp),
      verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
      when (detailSubTab) {
        0 -> {
          // Observations
          if (detailState.observations.isEmpty()) {
            item {
              Text("Aucune observation rédigée pour ce patient.", style = MaterialTheme.typography.bodyMedium)
              Spacer(modifier = Modifier.height(10.dp))
              Button(onClick = onAddObservation) {
                Text("Rédiger l'observation initiale")
              }
            }
          } else {
            items(detailState.observations) { obs ->
              Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
              ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                  Text(
                    text = "Observation Médicale Initiale (v${obs.version})",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.primary
                  )
                  Text(text = obs.finalizedText.ifBlank { obs.generatedDraft }, style = MaterialTheme.typography.bodySmall)
                }
              }
            }
          }
        }
        1 -> {
          // Updates
          if (detailState.updates.isEmpty()) {
            item {
              Text("Aucune mise à jour quotidienne enregistrée.", style = MaterialTheme.typography.bodyMedium)
            }
          } else {
            items(detailState.updates) { update ->
              Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
              ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                  Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                  ) {
                    Text(
                      text = "Évolution : ${update.hospitalDay} (${update.date})",
                      style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                      color = MaterialTheme.colorScheme.primary
                    )
                  }
                  Text(text = update.finalizedText.ifBlank { update.dailyClinicalPoint }, style = MaterialTheme.typography.bodySmall)
                }
              }
            }
          }
        }
        2 -> {
          // Vitals
          if (detailState.vitals.isEmpty()) {
            item {
              Text("Aucune constante enregistrée pour ce patient.", style = MaterialTheme.typography.bodyMedium)
            }
          } else {
            items(detailState.vitals) { v ->
              Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
              ) {
                Column(modifier = Modifier.padding(14.dp)) {
                  Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                  ) {
                    Text(text = v.formattedDateTime, style = MaterialTheme.typography.labelSmall)
                    AlertLevelChip(alertLevel = v.alertLevel)
                  }
                  Spacer(modifier = Modifier.height(4.dp))
                  Text(
                    text = "T°: ${v.temperature ?: "-"}°C • PA: ${v.systolicBp ?: "-"}/${v.diastolicBp ?: "-"} • FC: ${v.heartRate ?: "-"} bpm • SpO2: ${v.oxygenSaturation ?: "-"}%",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                  )
                }
              }
            }
          }
        }
        3 -> {
          // Lab results
          if (detailState.labResults.isEmpty()) {
            item {
              Text("Aucune analyse enregistrée pour ce patient.", style = MaterialTheme.typography.bodyMedium)
            }
          } else {
            items(detailState.labResults) { lab ->
              Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
              ) {
                Column(modifier = Modifier.padding(14.dp)) {
                  Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                  ) {
                    Text(text = lab.summaryTitle, style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold))
                    AlertLevelChip(alertLevel = lab.alertLevel)
                  }
                  Spacer(modifier = Modifier.height(4.dp))
                  Text(text = lab.interpretationText, style = MaterialTheme.typography.bodySmall)
                }
              }
            }
          }
        }
      }
    }
  }
}

@Composable
fun CreatePatientDialog(
  onDismiss: () -> Unit,
  onConfirm: (name: String, age: Int, gender: String, dept: String, room: String, condition: String) -> Unit
) {
  var name by remember { mutableStateOf("") }
  var ageStr by remember { mutableStateOf("52") }
  var gender by remember { mutableStateOf("Homme") }
  var dept by remember { mutableStateOf("Médecine Interne") }
  var room by remember { mutableStateOf("Chambre 205") }
  var condition by remember { mutableStateOf("") }

  AlertDialog(
    onDismissRequest = onDismiss,
    title = { Text("Créer un Dossier Patient", fontWeight = FontWeight.Bold) },
    text = {
      Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        OutlinedTextField(
          value = name,
          onValueChange = { name = it },
          label = { Text("Nom ou Pseudonyme") },
          placeholder = { Text("ex: M. Albert T.") },
          modifier = Modifier.fillMaxWidth().testTag("dialog_patient_name"),
          singleLine = true
        )
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
          OutlinedTextField(
            value = ageStr,
            onValueChange = { ageStr = it },
            label = { Text("Âge") },
            modifier = Modifier.weight(1f),
            singleLine = true
          )
          OutlinedTextField(
            value = gender,
            onValueChange = { gender = it },
            label = { Text("Sexe") },
            modifier = Modifier.weight(1f),
            singleLine = true
          )
        }
        OutlinedTextField(
          value = dept,
          onValueChange = { dept = it },
          label = { Text("Service hospitalier") },
          modifier = Modifier.fillMaxWidth(),
          singleLine = true
        )
        OutlinedTextField(
          value = room,
          onValueChange = { room = it },
          label = { Text("Chambre / Lit") },
          modifier = Modifier.fillMaxWidth(),
          singleLine = true
        )
        OutlinedTextField(
          value = condition,
          onValueChange = { condition = it },
          label = { Text("Motif ou Pathologie principale") },
          modifier = Modifier.fillMaxWidth(),
          singleLine = true
        )
      }
    },
    confirmButton = {
      Button(
        onClick = {
          if (name.isNotBlank()) {
            onConfirm(name, ageStr.toIntOrNull() ?: 50, gender, dept, room, condition)
          }
        },
        enabled = name.isNotBlank()
      ) {
        Text("Créer le dossier")
      }
    },
    dismissButton = {
      OutlinedButton(onClick = onDismiss) {
        Text("Annuler")
      }
    }
  )
}
