package com.example.ui.profile

import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.School
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
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.LegalDisclaimerBanner
import com.example.ui.theme.AlertGreen
import com.example.ui.theme.MedicalBlue
import com.example.ui.theme.MedicalTeal
import com.example.ui.theme.NavyDark

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.R

val MEDICAL_YEARS = listOf(
  "3e Année (DFGSM 3 / Externat)",
  "4e Année (DFASM 1 / Externe)",
  "5e Année (DFASM 2 / Externe)",
  "6e Année (DFASM 3 / Préparation ECN/EDN)",
  "7e Année & plus (Interne / Résident)"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
  viewModel: ProfileViewModel,
  onBack: () -> Unit,
  onToggleTheme: (Boolean) -> Unit,
  onNavigateToAbout: () -> Unit = {},
  modifier: Modifier = Modifier
) {
  val context = LocalContext.current
  val profile by viewModel.profile.collectAsState()
  val stats by viewModel.stats.collectAsState()

  var name by remember(profile.name) { mutableStateOf(profile.name) }
  var year by remember(profile.studyYear) { mutableStateOf(profile.studyYear) }
  var uni by remember(profile.university) { mutableStateOf(profile.university) }
  var hospital by remember(profile.currentHospital) { mutableStateOf(profile.currentHospital) }
  var isEditing by remember { mutableStateOf(false) }

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background),
    contentPadding = PaddingValues(16.dp),
    verticalArrangement = Arrangement.spacedBy(14.dp)
  ) {
    // Header
    item {
      Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
      ) {
        IconButton(onClick = onBack) {
          Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Retour")
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
          text = "Profil Étudiant & Paramètres",
          style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )
      }
    }

    item { LegalDisclaimerBanner() }

    // Student identity card
    item {
      Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
      ) {
        Column(modifier = Modifier.padding(18.dp)) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
              modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer),
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.size(34.dp)
              )
            }
            Spacer(modifier = Modifier.width(14.dp))
            Column {
              Text(
                text = profile.name,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface
              )
              Text(
                text = profile.studyYear,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
              )
              Text(
                text = profile.university,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
            }
          }

          Spacer(modifier = Modifier.height(14.dp))

          if (isEditing) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
              OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nom et prénom") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
              )

              var yearDropdownExpanded by remember { mutableStateOf(false) }
              ExposedDropdownMenuBox(
                expanded = yearDropdownExpanded,
                onExpandedChange = { yearDropdownExpanded = !yearDropdownExpanded }
              ) {
                OutlinedTextField(
                  value = year,
                  onValueChange = {},
                  readOnly = true,
                  label = { Text("Année d'études médicales") },
                  trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = yearDropdownExpanded) },
                  modifier = Modifier.menuAnchor().fillMaxWidth()
                )
                ExposedDropdownMenu(
                  expanded = yearDropdownExpanded,
                  onDismissRequest = { yearDropdownExpanded = false }
                ) {
                  MEDICAL_YEARS.forEach { yr ->
                    DropdownMenuItem(
                      text = { Text(yr) },
                      onClick = {
                        year = yr
                        yearDropdownExpanded = false
                      }
                    )
                  }
                }
              }

              OutlinedTextField(
                value = uni,
                onValueChange = { uni = it },
                label = { Text("Faculté de médecine") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
              )
              OutlinedTextField(
                value = hospital,
                onValueChange = { hospital = it },
                label = { Text("Hôpital / Service de stage") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
              )

              Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(
                  onClick = {
                    viewModel.updateProfile(name, year, uni, hospital)
                    isEditing = false
                    Toast.makeText(context, "Profil mis à jour", Toast.LENGTH_SHORT).show()
                  },
                  modifier = Modifier.weight(1f)
                ) {
                  Text("Enregistrer")
                }
                OutlinedButton(
                  onClick = { isEditing = false },
                  modifier = Modifier.weight(1f)
                ) {
                  Text("Annuler")
                }
              }
            }
          } else {
            OutlinedButton(
              onClick = { isEditing = true },
              modifier = Modifier.fillMaxWidth().height(42.dp),
              shape = RoundedCornerShape(10.dp)
            ) {
              Text("Modifier mon profil")
            }
          }
        }
      }
    }

    // Formation Statistics
    item {
      Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
      ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
          Text(
            text = "Statistiques de Formation Clinique",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface
          )

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
          ) {
            StatCard(
              title = "Dossiers",
              value = "${stats.first}",
              icon = Icons.Default.Folder,
              color = NavyDark,
              modifier = Modifier.weight(1f)
            )
            StatCard(
              title = "Constantes",
              value = "${stats.second}",
              icon = Icons.Default.Timeline,
              color = MedicalBlue,
              modifier = Modifier.weight(1f)
            )
            StatCard(
              title = "Observations",
              value = "${stats.third}",
              icon = Icons.Default.Assignment,
              color = MedicalTeal,
              modifier = Modifier.weight(1f)
            )
          }
        }
      }
    }

    // App Preferences
    item {
      Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
      ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
          Text(
            text = "Préférences de l'Application",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface
          )

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Icon(imageVector = Icons.Default.DarkMode, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
              Spacer(modifier = Modifier.width(10.dp))
              Column {
                Text(text = "Mode Sombre", fontWeight = FontWeight.SemiBold)
                Text(text = "Améliore le confort visuel de garde", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
              }
            }
            Switch(
              checked = profile.isDarkMode,
              onCheckedChange = { checked ->
                viewModel.toggleDarkMode(checked)
                onToggleTheme(checked)
              }
            )
          }
        }
      }
    }

    // À propos d'El Vita - Identité institutionnelle
    item {
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .clickable { onNavigateToAbout() }
          .testTag("btn_open_about_elvita"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, MedicalBlue.copy(alpha = 0.4f))
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Box(
            modifier = Modifier
              .size(46.dp)
              .clip(RoundedCornerShape(12.dp))
              .background(NavyDark)
              .padding(4.dp),
            contentAlignment = Alignment.Center
          ) {
            Image(
              painter = painterResource(id = R.drawable.img_elvita_logo),
              contentDescription = "Logo El Vita",
              modifier = Modifier.size(38.dp),
              contentScale = ContentScale.Fit
            )
          }

          Spacer(modifier = Modifier.width(14.dp))

          Column(modifier = Modifier.weight(1f)) {
            Text(
              text = "À propos d'El Vita",
              style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
              color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
              text = "Identité, fondateur Albert Dossou, valeurs & vision",
              style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.5.sp),
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )
          }

          Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
            contentDescription = "Ouvrir",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(16.dp)
          )
        }
      }
    }

    // Pedagogical Data Management
    item {
      Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
      ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
          Text(
            text = "Données Pédagogiques & Démonstration",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface
          )
          Text(
            text = "Recharger les cas cliniques didactiques d'entraînement (Mme Claire B. - Pneumologie, M. Marc T. - Cardiologie).",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
          Spacer(modifier = Modifier.height(4.dp))
          OutlinedButton(
            onClick = {
              viewModel.reseedDemoData {
                Toast.makeText(context, "Cas pédagogiques réinitialisés !", Toast.LENGTH_SHORT).show()
              }
            },
            modifier = Modifier.fillMaxWidth()
          ) {
            Icon(imageVector = Icons.Default.Refresh, contentDescription = null)
            Spacer(modifier = Modifier.width(6.dp))
            Text("Recharger les cas cliniques exemples")
          }
        }
      }
    }
  }
}

@Composable
fun StatCard(title: String, value: String, icon: androidx.compose.ui.graphics.vector.ImageVector, color: Color, modifier: Modifier = Modifier) {
  Box(
    modifier = modifier
      .clip(RoundedCornerShape(12.dp))
      .background(color.copy(alpha = 0.08f))
      .padding(12.dp),
    contentAlignment = Alignment.Center
  ) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
      Icon(imageVector = icon, contentDescription = null, tint = color, modifier = Modifier.size(20.dp))
      Spacer(modifier = Modifier.height(4.dp))
      Text(text = value, style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold), color = color)
      Text(text = title, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
  }
}
