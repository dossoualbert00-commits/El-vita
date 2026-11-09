package com.example.ui.clinical

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Update
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
import androidx.compose.material3.OutlinedButton
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
import com.example.data.local.entities.PatientEntity
import com.example.ui.components.LegalDisclaimerBanner
import com.example.ui.theme.AlertGreen
import com.example.ui.theme.MedicalBlue
import com.example.ui.theme.MedicalTeal
import com.example.ui.theme.NavyDark

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClinicalScreen(
  viewModel: ClinicalViewModel,
  initialSubTab: Int = 0,
  modifier: Modifier = Modifier
) {
  val context = LocalContext.current
  var activeTab by remember { mutableIntStateOf(initialSubTab) }
  val obsForm by viewModel.obsForm.collectAsState()
  val updateForm by viewModel.updateForm.collectAsState()
  val patients by viewModel.patients.collectAsState()

  Column(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
  ) {
    // Header
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.primary)
        .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
      Column {
        Text(
          text = "Module Clinique & Observations",
          style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
          color = Color.White
        )
        Text(
          text = "Observation médicale initiale & Mises à jour quotidiennes de visite",
          style = MaterialTheme.typography.bodySmall,
          color = Color.White.copy(alpha = 0.8f)
        )
      }
    }

    TabRow(
      selectedTabIndex = activeTab,
      containerColor = MaterialTheme.colorScheme.surface,
      contentColor = MaterialTheme.colorScheme.primary
    ) {
      Tab(
        selected = activeTab == 0,
        onClick = { activeTab = 0 },
        text = { Text("Observation Médicale", fontWeight = FontWeight.SemiBold) }
      )
      Tab(
        selected = activeTab == 1,
        onClick = { activeTab = 1 },
        text = { Text("Mise à Jour Clinique", fontWeight = FontWeight.SemiBold) }
      )
    }

    if (activeTab == 0) {
      // ══════════════════════════════════════════════════════
      // A. OBSERVATION MÉDICALE
      // ══════════════════════════════════════════════════════
      var showGuide by remember { mutableStateOf(false) }

      LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
      ) {
        item {
          LegalDisclaimerBanner()
        }

        // PREMIÈRE PARTIE : FORMATION CLINIQUE & GUIDE MÉTHODOLOGIQUE
        item {
          ClinicalTrainingAccordion()
        }

        // DEUXIÈME PARTIE : FORMULAIRE DE SAISIE STRUCTURÉ
        item {
          Text(
            text = "Formulaire d'Observation Structurée",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onBackground
          )
        }

        // 1. Identification du Patient
        item {
          Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
          ) {
            Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
              Text(
                text = "1. Identification du Patient",
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
              )

              // Select from existing patient or enter new
              var patientMenuExpanded by remember { mutableStateOf(false) }
              ExposedDropdownMenuBox(
                expanded = patientMenuExpanded,
                onExpandedChange = { patientMenuExpanded = !patientMenuExpanded }
              ) {
                OutlinedTextField(
                  value = if (obsForm.patientId != null) "${obsForm.patientName} (${obsForm.department})" else "Saisir un nouveau dossier patient",
                  onValueChange = {},
                  readOnly = true,
                  trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = patientMenuExpanded) },
                  modifier = Modifier.menuAnchor().fillMaxWidth(),
                  shape = RoundedCornerShape(10.dp)
                )
                ExposedDropdownMenu(
                  expanded = patientMenuExpanded,
                  onDismissRequest = { patientMenuExpanded = false }
                ) {
                  DropdownMenuItem(
                    text = { Text("Saisir un nouveau dossier patient") },
                    onClick = {
                      viewModel.updatePatientIdFields("", "45", "Homme", "Médecine Interne", "Chambre 101")
                      patientMenuExpanded = false
                    }
                  )
                  patients.forEach { p ->
                    DropdownMenuItem(
                      text = { Text("${p.fullNameOrAlias} — ${p.department} (${p.roomNumber})") },
                      onClick = {
                        viewModel.selectPatientForObservation(p)
                        patientMenuExpanded = false
                      }
                    )
                  }
                }
              }

              OutlinedTextField(
                value = obsForm.patientName,
                onValueChange = { viewModel.updatePatientIdFields(it, obsForm.patientAge, obsForm.patientGender, obsForm.department, obsForm.roomNumber) },
                label = { Text("Nom et prénom ou pseudonyme") },
                placeholder = { Text("ex: M. Robert D. (ou pseudonyme)") },
                modifier = Modifier.fillMaxWidth().testTag("obs_patient_name"),
                shape = RoundedCornerShape(10.dp),
                singleLine = true
              )

              Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                OutlinedTextField(
                  value = obsForm.patientAge,
                  onValueChange = { viewModel.updatePatientIdFields(obsForm.patientName, it, obsForm.patientGender, obsForm.department, obsForm.roomNumber) },
                  label = { Text("Âge") },
                  keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                  modifier = Modifier.weight(1f),
                  shape = RoundedCornerShape(10.dp),
                  singleLine = true
                )
                OutlinedTextField(
                  value = obsForm.patientGender,
                  onValueChange = { viewModel.updatePatientIdFields(obsForm.patientName, obsForm.patientAge, it, obsForm.department, obsForm.roomNumber) },
                  label = { Text("Sexe") },
                  modifier = Modifier.weight(1f),
                  shape = RoundedCornerShape(10.dp),
                  singleLine = true
                )
              }

              Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                OutlinedTextField(
                  value = obsForm.department,
                  onValueChange = { viewModel.updatePatientIdFields(obsForm.patientName, obsForm.patientAge, obsForm.patientGender, it, obsForm.roomNumber) },
                  label = { Text("Service d'hospitalisation") },
                  modifier = Modifier.weight(1f),
                  shape = RoundedCornerShape(10.dp),
                  singleLine = true
                )
                OutlinedTextField(
                  value = obsForm.roomNumber,
                  onValueChange = { viewModel.updatePatientIdFields(obsForm.patientName, obsForm.patientAge, obsForm.patientGender, obsForm.department, it) },
                  label = { Text("Chambre") },
                  modifier = Modifier.weight(1f),
                  shape = RoundedCornerShape(10.dp),
                  singleLine = true
                )
              }
            }
          }
        }

        // 2. Motif d'admission
        item {
          Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
          ) {
            Column(modifier = Modifier.padding(14.dp)) {
              Text(
                text = "2. Motif Principal d'Admission",
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
              )
              Spacer(modifier = Modifier.height(6.dp))
              OutlinedTextField(
                value = obsForm.admissionReason,
                onValueChange = { viewModel.updateObsFields(admissionReason = it) },
                placeholder = { Text("ex: Douleur thoracique aiguë constrictive irradiant au bras gauche depuis 3 heures...") },
                modifier = Modifier.fillMaxWidth().testTag("obs_admission_reason"),
                shape = RoundedCornerShape(10.dp),
                minLines = 2
              )
            }
          }
        }

        // 3. Histoire de la maladie
        item {
          Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
          ) {
            Column(modifier = Modifier.padding(14.dp)) {
              Text(
                text = "3. Histoire de la Maladie",
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
              )
              Text(
                text = "Date et mode de début, durée, symptômes, facteurs soulageants, traitements reçus",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
              Spacer(modifier = Modifier.height(8.dp))
              OutlinedTextField(
                value = obsForm.historyOfIllness,
                onValueChange = { viewModel.updateObsFields(historyOfIllness = it) },
                placeholder = { Text("Détaillez la chronologie des événements et symptômes...") },
                modifier = Modifier.fillMaxWidth().testTag("obs_history_illness"),
                shape = RoundedCornerShape(10.dp),
                minLines = 3
              )
            }
          }
        }

        // 4. Antécédents
        item {
          Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
          ) {
            Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
              Text(
                text = "4. Antécédents & Mode de Vie",
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
              )
              OutlinedTextField(
                value = obsForm.pastMedicalHistory,
                onValueChange = { viewModel.updateObsFields(pastMedical = it) },
                label = { Text("Antécédents médicaux") },
                placeholder = { Text("ex: HTA, Diabète de type 2, Asthme...") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp)
              )
              OutlinedTextField(
                value = obsForm.pastSurgicalHistory,
                onValueChange = { viewModel.updateObsFields(pastSurgical = it) },
                label = { Text("Antécédents chirurgicaux") },
                placeholder = { Text("ex: Appendicectomie, pose de stent...") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp)
              )
              OutlinedTextField(
                value = obsForm.allergies,
                onValueChange = { viewModel.updateObsFields(allergies = it) },
                label = { Text("Allergies connues") },
                placeholder = { Text("ex: Allergie à la pénicilline...") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp)
              )
              OutlinedTextField(
                value = obsForm.lifestyle,
                onValueChange = { viewModel.updateObsFields(lifestyle = it) },
                label = { Text("Mode de vie & Toxiques") },
                placeholder = { Text("ex: Tabac 15 PA, Alcool occasionnel...") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp)
              )
            }
          }
        }

        // 5. Examen général & Constantes
        item {
          Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
          ) {
            Column(modifier = Modifier.padding(14.dp)) {
              Text(
                text = "5. Examen Général & Constantes",
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
              )
              Spacer(modifier = Modifier.height(6.dp))
              OutlinedTextField(
                value = obsForm.generalExam,
                onValueChange = { viewModel.updateObsFields(generalExam = it) },
                placeholder = { Text("ex: Conscient GCS 15, OMS 1. Constantes : TA 130/80, FC 75, FR 16, SpO2 98%, T° 37.2°C...") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                minLines = 2
              )
            }
          }
        }

        // 6. Examen physique appareil par appareil
        item {
          Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
          ) {
            Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
              Text(
                text = "6. Examen Physique Appareil par Appareil",
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
              )
              OutlinedTextField(
                value = obsForm.cardiovascularExam,
                onValueChange = { viewModel.updateObsFields(cvExam = it) },
                label = { Text("Appareil Cardiovasculaire") },
                placeholder = { Text("B1/B2, souffles, pouls périphériques, OMI, turgescence jugulaire...") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp)
              )
              OutlinedTextField(
                value = obsForm.respiratoryExam,
                onValueChange = { viewModel.updateObsFields(respExam = it) },
                label = { Text("Appareil Respiratoire") },
                placeholder = { Text("Murmure vésiculaire, crépitants, sibilants, matité/tympanisme...") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp)
              )
              OutlinedTextField(
                value = obsForm.digestiveExam,
                onValueChange = { viewModel.updateObsFields(digestiveExam = it) },
                label = { Text("Appareil Digestif") },
                placeholder = { Text("Palpation, défense/contracture, bruits hydro-aériques, orifices herniaires...") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp)
              )
              OutlinedTextField(
                value = obsForm.neurologicalExam,
                onValueChange = { viewModel.updateObsFields(neuroExam = it) },
                label = { Text("Système Neurologique") },
                placeholder = { Text("Conscience, paires crâniennes, force motrice, réflexes, signes méningés...") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp)
              )
              OutlinedTextField(
                value = obsForm.locomotorExam,
                onValueChange = { viewModel.updateObsFields(locomotorExam = it) },
                label = { Text("Appareil Locomoteur & Urinaire") },
                placeholder = { Text("Articulations, rachis, diurèse, loges rénales...") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp)
              )
            }
          }
        }

        // 7. Examens complémentaires
        item {
          Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
          ) {
            Column(modifier = Modifier.padding(14.dp)) {
              Text(
                text = "7. Examens Complémentaires Paracliniques",
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
              )
              Spacer(modifier = Modifier.height(6.dp))
              OutlinedTextField(
                value = obsForm.complementaryExams,
                onValueChange = { viewModel.updateObsFields(compExams = it) },
                placeholder = { Text("Résultats NFS, Ionogramme, Troponine, ECG, Radiographie, Scanner...") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                minLines = 2
              )
            }
          }
        }

        // 8. Synthèse & Hypothèses
        item {
          Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
          ) {
            Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
              Text(
                text = "8. Synthèse & Hypothèses Diagnostiques",
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
              )
              OutlinedTextField(
                value = obsForm.clinicalSynthesis,
                onValueChange = { viewModel.updateObsFields(synthesis = it) },
                label = { Text("Synthèse clinique (Regroupement syndromique)") },
                placeholder = { Text("ex: Homme de 58 ans présentant un syndrome coronarien aigu probable...") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                minLines = 2
              )
              OutlinedTextField(
                value = obsForm.diagnosticHypotheses,
                onValueChange = { viewModel.updateObsFields(hypotheses = it) },
                label = { Text("Hypothèses diagnostiques hiérarchisées") },
                placeholder = { Text("1. SCA ST- (probable)\n2. Péricardite aiguë\n3. Embolie pulmonaire...") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                minLines = 2
              )
            }
          }
        }

        // TROISIÈME PARTIE : BOUTON « GÉNÉRER MON OBSERVATION »
        item {
          Button(
            onClick = {
              viewModel.generateObservation()
              Toast.makeText(context, "Observation rédigée avec succès !", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
              .fillMaxWidth()
              .height(52.dp)
              .testTag("btn_generate_observation"),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
          ) {
            Icon(imageVector = Icons.Default.AutoAwesome, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = "GÉNÉRER MON OBSERVATION",
              style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
          }
        }

        // DISPLAY AND EDIT GENERATED DRAFT
        if (obsForm.isDraftGenerated && obsForm.generatedObservationText.isNotBlank()) {
          item {
            Card(
              modifier = Modifier.fillMaxWidth(),
              shape = RoundedCornerShape(16.dp),
              colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
              border = androidx.compose.foundation.BorderStroke(1.5.dp, MedicalBlue)
            ) {
              Column(modifier = Modifier.padding(16.dp)) {
                Row(
                  modifier = Modifier.fillMaxWidth(),
                  horizontalArrangement = Arrangement.SpaceBetween,
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Assignment, contentDescription = null, tint = MedicalBlue)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                      text = "Observation Médicale Générée",
                      style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                      color = MaterialTheme.colorScheme.onSurface
                    )
                  }
                  IconButton(
                    onClick = {
                      val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                      val clip = ClipData.newPlainText("Observation El Vita", obsForm.generatedObservationText)
                      clipboard.setPrimaryClip(clip)
                      Toast.makeText(context, "Observation copiée dans le presse-papiers", Toast.LENGTH_SHORT).show()
                    }
                  ) {
                    Icon(imageVector = Icons.Default.ContentCopy, contentDescription = "Copier")
                  }
                }

                Text(
                  text = "Vous pouvez relire et modifier chaque paragraphe avant validation finale :",
                  style = MaterialTheme.typography.bodySmall,
                  color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                  value = obsForm.generatedObservationText,
                  onValueChange = { viewModel.updateGeneratedObservationText(it) },
                  modifier = Modifier.fillMaxWidth().testTag("generated_obs_editor"),
                  shape = RoundedCornerShape(10.dp),
                  minLines = 8
                )

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                  modifier = Modifier.fillMaxWidth(),
                  horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                  OutlinedButton(
                    onClick = { viewModel.generateObservation() },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(10.dp)
                  ) {
                    Icon(imageVector = Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Régénérer")
                  }

                  Button(
                    onClick = {
                      viewModel.saveObservation {
                        Toast.makeText(context, "Observation enregistrée dans le dossier !", Toast.LENGTH_SHORT).show()
                      }
                    },
                    modifier = Modifier.weight(1.5f).testTag("save_observation_btn"),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = AlertGreen)
                  ) {
                    Icon(imageVector = Icons.Default.Save, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Valider & Enregistrer", fontWeight = FontWeight.Bold)
                  }
                }
              }
            }
          }
        }
      }
    } else {
      // ══════════════════════════════════════════════════════
      // B. MISE À JOUR CLINIQUE QUOTIDIENNE (STAFF / VISITE)
      // ══════════════════════════════════════════════════════
      LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
      ) {
        item {
          LegalDisclaimerBanner()
        }

        item {
          Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
          ) {
            Column(modifier = Modifier.padding(14.dp)) {
              Text(
                text = "Mise à Jour Clinique Quotidienne (Staff & Visite)",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface
              )
              Text(
                text = "Permet d'évaluer l'évolution du patient sans réécrire l'observation initiale. Conserve l'historique chronologique.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
            }
          }
        }

        // Mode : Patient existant vs Nouveau patient direct
        item {
          Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
          ) {
            Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
              Text(
                text = "1. Sélection du Patient",
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
              )

              var dropExpanded by remember { mutableStateOf(false) }
              ExposedDropdownMenuBox(
                expanded = dropExpanded,
                onExpandedChange = { dropExpanded = !dropExpanded }
              ) {
                OutlinedTextField(
                  value = if (updateForm.isNewPatientMode) "Nouveau patient (Accès direct sans observation préalable)"
                          else if (updateForm.patientName.isNotBlank()) "${updateForm.patientName} (${updateForm.department})"
                          else "Choisir un patient ou nouveau patient",
                  onValueChange = {},
                  readOnly = true,
                  trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = dropExpanded) },
                  modifier = Modifier.menuAnchor().fillMaxWidth(),
                  shape = RoundedCornerShape(10.dp)
                )
                ExposedDropdownMenu(
                  expanded = dropExpanded,
                  onDismissRequest = { dropExpanded = false }
                ) {
                  DropdownMenuItem(
                    text = { Text("+ Nouveau patient direct (sans observation)") },
                    onClick = {
                      viewModel.setUpdatePatient(null)
                      dropExpanded = false
                    }
                  )
                  patients.forEach { p ->
                    DropdownMenuItem(
                      text = { Text("${p.fullNameOrAlias} — ${p.department} (${p.roomNumber})") },
                      onClick = {
                        viewModel.setUpdatePatient(p)
                        dropExpanded = false
                      }
                    )
                  }
                }
              }

              if (updateForm.isNewPatientMode) {
                OutlinedTextField(
                  value = updateForm.newPatientName,
                  onValueChange = { viewModel.updateDailyUpdateFields(newPatientName = it) },
                  label = { Text("Nom du patient") },
                  placeholder = { Text("ex: M. Pascal K.") },
                  modifier = Modifier.fillMaxWidth().testTag("update_new_patient_name"),
                  shape = RoundedCornerShape(10.dp),
                  singleLine = true
                )
              }
            }
          }
        }

        // 2. Hospitalisation & Jour
        item {
          Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
          ) {
            Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
              Text(
                text = "2. Hospitalisation & Temporalité",
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
              )

              Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                OutlinedTextField(
                  value = updateForm.hospitalDay,
                  onValueChange = { viewModel.updateDailyUpdateFields(hospitalDay = it) },
                  label = { Text("Jour (ex: J1, J2, J5)") },
                  modifier = Modifier.weight(1f).testTag("update_hospital_day"),
                  shape = RoundedCornerShape(10.dp),
                  singleLine = true
                )
                OutlinedTextField(
                  value = updateForm.date,
                  onValueChange = { viewModel.updateDailyUpdateFields(date = it) },
                  label = { Text("Date de la visite") },
                  modifier = Modifier.weight(1f),
                  shape = RoundedCornerShape(10.dp),
                  singleLine = true
                )
              }
              OutlinedTextField(
                value = updateForm.department,
                onValueChange = { viewModel.updateDailyUpdateFields(department = it) },
                label = { Text("Service hospitalier") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                singleLine = true
              )
            }
          }
        }

        // 3. Point clinique du jour
        item {
          Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
          ) {
            Column(modifier = Modifier.padding(14.dp)) {
              Text(
                text = "3. Présentation ou Point Clinique du Jour",
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
              )
              Text(
                text = "Événements survenus durant la nuit, amélioration ou nouvelle plainte du patient",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
              Spacer(modifier = Modifier.height(6.dp))
              OutlinedTextField(
                value = updateForm.dailyPoint,
                onValueChange = { viewModel.updateDailyUpdateFields(dailyPoint = it) },
                placeholder = { Text("ex: Nuit calme, apyrexie maintenue, régression de la douleur...") },
                modifier = Modifier.fillMaxWidth().testTag("update_daily_point"),
                shape = RoundedCornerShape(10.dp),
                minLines = 2
              )
            }
          }
        }

        // 4. État général du jour
        item {
          Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
          ) {
            Column(modifier = Modifier.padding(14.dp)) {
              Text(
                text = "4. État Général & Constantes du Jour",
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
              )
              Spacer(modifier = Modifier.height(6.dp))
              OutlinedTextField(
                value = updateForm.generalState,
                onValueChange = { viewModel.updateDailyUpdateFields(generalState = it) },
                placeholder = { Text("ex: Bon état général, patient levé au fauteuil, TA 120/75, FC 72, SpO2 97%...") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                minLines = 2
              )
            }
          }
        }

        // 5. Examen physique appareil par appareil
        item {
          Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
          ) {
            Column(modifier = Modifier.padding(14.dp)) {
              Text(
                text = "5. Examen Physique Appareil par Appareil",
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
              )
              Spacer(modifier = Modifier.height(6.dp))
              OutlinedTextField(
                value = updateForm.physicalExam,
                onValueChange = { viewModel.updateDailyUpdateFields(physicalExam = it) },
                placeholder = { Text("Cardio, Resp, Abdomen, Neuro ciblés...") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                minLines = 2
              )
            }
          }
        }

        // 6. Nouveaux examens complémentaires
        item {
          Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
          ) {
            Column(modifier = Modifier.padding(14.dp)) {
              Text(
                text = "6. Nouveaux Examens Complémentaires Reçus",
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
              )
              Spacer(modifier = Modifier.height(6.dp))
              OutlinedTextField(
                value = updateForm.newComplementaryExams,
                onValueChange = { viewModel.updateDailyUpdateFields(newExams = it) },
                placeholder = { Text("Hémocultures, NFS de contrôle, Radio de contrôle...") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                minLines = 2
              )
            }
          }
        }

        // 7. Conclusion & Conduite à tenir
        item {
          Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
          ) {
            Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
              Text(
                text = "7. Conclusion Clinique & Conduite à Tenir",
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
              )
              OutlinedTextField(
                value = updateForm.clinicalConclusion,
                onValueChange = { viewModel.updateDailyUpdateFields(conclusion = it) },
                label = { Text("Conclusion clinique d'évolution") },
                placeholder = { Text("ex: Évolution favorable, désescalade thérapeutique...") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp)
              )
              OutlinedTextField(
                value = updateForm.treatmentAndPlan,
                onValueChange = { viewModel.updateDailyUpdateFields(treatmentPlan = it) },
                label = { Text("Traitement et Conduite à Tenir (CAT)") },
                placeholder = { Text("ex: Poursuite de l'antibiothérapie, relais oral envisagé...") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp)
              )
            }
          }
        }

        // BOUTON « GÉNÉRER MA MISE À JOUR »
        item {
          Button(
            onClick = {
              viewModel.generateDailyUpdateDraft()
              Toast.makeText(context, "Mise à jour rédigée !", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
              .fillMaxWidth()
              .height(52.dp)
              .testTag("btn_generate_daily_update"),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
          ) {
            Icon(imageVector = Icons.Default.Update, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = "GÉNÉRER MA MISE À JOUR",
              style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
          }
        }

        // PREVIEW GENERATED UPDATE
        if (updateForm.isDraftGenerated && updateForm.generatedUpdateText.isNotBlank()) {
          item {
            Card(
              modifier = Modifier.fillMaxWidth(),
              shape = RoundedCornerShape(16.dp),
              colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
              border = androidx.compose.foundation.BorderStroke(1.5.dp, MedicalTeal)
            ) {
              Column(modifier = Modifier.padding(16.dp)) {
                Row(
                  modifier = Modifier.fillMaxWidth(),
                  horizontalArrangement = Arrangement.SpaceBetween,
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Text(
                    text = "Compte Rendu Quotidien Généré",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurface
                  )
                  IconButton(
                    onClick = {
                      val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                      val clip = ClipData.newPlainText("Mise à jour El Vita", updateForm.generatedUpdateText)
                      clipboard.setPrimaryClip(clip)
                      Toast.makeText(context, "Texte copié dans le presse-papiers", Toast.LENGTH_SHORT).show()
                    }
                  ) {
                    Icon(imageVector = Icons.Default.ContentCopy, contentDescription = "Copier")
                  }
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                  value = updateForm.generatedUpdateText,
                  onValueChange = { viewModel.updateGeneratedDailyUpdateText(it) },
                  modifier = Modifier.fillMaxWidth(),
                  shape = RoundedCornerShape(10.dp),
                  minLines = 6
                )

                Spacer(modifier = Modifier.height(14.dp))

                Button(
                  onClick = {
                    viewModel.saveDailyUpdate {
                      Toast.makeText(context, "Mise à jour enregistrée dans l'historique du dossier !", Toast.LENGTH_SHORT).show()
                    }
                  },
                  modifier = Modifier.fillMaxWidth().height(48.dp).testTag("save_daily_update_btn"),
                  shape = RoundedCornerShape(10.dp),
                  colors = ButtonDefaults.buttonColors(containerColor = AlertGreen)
                ) {
                  Icon(imageVector = Icons.Default.Save, contentDescription = null)
                  Spacer(modifier = Modifier.width(8.dp))
                  Text("Enregistrer dans le dossier", fontWeight = FontWeight.Bold)
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
fun MethodStepCard(step: MethodStep) {
  Card(
    modifier = Modifier.fillMaxWidth(),
    shape = RoundedCornerShape(10.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
  ) {
    Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
          modifier = Modifier
            .size(24.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(MaterialTheme.colorScheme.primaryContainer),
          contentAlignment = Alignment.Center
        ) {
          Text(
            text = "${step.number}",
            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onPrimaryContainer
          )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
          text = step.title,
          style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
          color = MaterialTheme.colorScheme.onSurface
        )
      }

      Text(
        text = "À rechercher : ${step.whatToLookFor}",
        style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.5.sp)
      )

      Text(
        text = "Formulation médicale type : « ${step.phrasingExample} »",
        style = MaterialTheme.typography.bodySmall.copy(
          fontSize = 11.sp,
          fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
          color = MaterialTheme.colorScheme.primary
        )
      )
    }
  }
}
