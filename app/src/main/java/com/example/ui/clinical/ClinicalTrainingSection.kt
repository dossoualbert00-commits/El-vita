package com.example.ui.clinical

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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AnamnesisSkill
import com.example.data.model.ClassificationExercise
import com.example.data.model.ClinicalTrainingDataProvider
import com.example.data.model.InfoCategoryGuide
import com.example.data.model.ObservationMethodSkill
import com.example.data.model.SynthesisCaseTraining
import com.example.ui.theme.AlertGreen
import com.example.ui.theme.AlertOrange
import com.example.ui.theme.AlertRed
import com.example.ui.theme.MedicalBlue
import com.example.ui.theme.MedicalTeal

@Composable
fun ClinicalTrainingAccordion(
  modifier: Modifier = Modifier
) {
  var isExpanded by remember { mutableStateOf(false) }
  var selectedTab by remember { mutableIntStateOf(0) }

  Card(
    modifier = modifier
      .fillMaxWidth()
      .testTag("clinical_training_accordion"),
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    border = androidx.compose.foundation.BorderStroke(1.5.dp, MedicalBlue.copy(alpha = 0.4f))
  ) {
    Column(modifier = Modifier.padding(14.dp)) {
      // Accordion Header
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .clickable { isExpanded = !isExpanded },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(
          modifier = Modifier.weight(1f),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Box(
            modifier = Modifier
              .size(40.dp)
              .clip(CircleShape)
              .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.Default.School,
              contentDescription = null,
              tint = MaterialTheme.colorScheme.primary,
              modifier = Modifier.size(22.dp)
            )
          }
          Spacer(modifier = Modifier.width(12.dp))
          Column {
            Text(
              text = "Formation Clinique Pédagogique",
              style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
              color = MaterialTheme.colorScheme.onSurface
            )
            Text(
              text = "Interrogatoire (14 points), Organisation, Observation & Synthèse",
              style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp),
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )
          }
        }

        Icon(
          imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
          contentDescription = null,
          tint = MaterialTheme.colorScheme.primary
        )
      }

      AnimatedVisibility(visible = isExpanded) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(top = 14.dp),
          verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
          // Tab navigation across the 4 modules
          ScrollableTabRow(
            selectedTabIndex = selectedTab,
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
            contentColor = MaterialTheme.colorScheme.primary,
            edgePadding = 8.dp,
            modifier = Modifier.clip(RoundedCornerShape(10.dp))
          ) {
            Tab(
              selected = selectedTab == 0,
              onClick = { selectedTab = 0 },
              text = { Text("A. Interrogatoire (14)", fontWeight = if (selectedTab == 0) FontWeight.Bold else FontWeight.Normal) }
            )
            Tab(
              selected = selectedTab == 1,
              onClick = { selectedTab = 1 },
              text = { Text("B. Organisation (9)", fontWeight = if (selectedTab == 1) FontWeight.Bold else FontWeight.Normal) }
            )
            Tab(
              selected = selectedTab == 2,
              onClick = { selectedTab = 2 },
              text = { Text("C. Observation", fontWeight = if (selectedTab == 2) FontWeight.Bold else FontWeight.Normal) }
            )
            Tab(
              selected = selectedTab == 3,
              onClick = { selectedTab = 3 },
              text = { Text("D. Synthèse Clinique", fontWeight = if (selectedTab == 3) FontWeight.Bold else FontWeight.Normal) }
            )
          }

          when (selectedTab) {
            0 -> AnamnesisTrainingTab()
            1 -> InfoOrganizationTab()
            2 -> ObservationMethodTab()
            3 -> ClinicalSynthesisTab()
          }
        }
      }
    }
  }
}

// ══════════════════════════════════════════════════════════════════════════════
// MODULE A — L'INTERROGATOIRE MÉDICAL (14 COMPÉTENCES DÉTAILLÉES)
// ══════════════════════════════════════════════════════════════════════════════
@Composable
fun AnamnesisTrainingTab() {
  val skills = ClinicalTrainingDataProvider.anamnesisSkills

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    Text(
      text = "Apprentissage Méthodique de l'Interrogatoire Médical",
      style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
      color = MaterialTheme.colorScheme.primary
    )
    Text(
      text = "Explorez les 14 étapes pour passer d'une simple conversation à un interrogatoire médical structuré, rigoureux et bienveillant.",
      style = MaterialTheme.typography.bodySmall,
      color = MaterialTheme.colorScheme.onSurfaceVariant
    )

    skills.forEach { skill ->
      AnamnesisSkillCard(skill = skill)
    }
  }
}

@Composable
fun AnamnesisSkillCard(skill: AnamnesisSkill) {
  var isExpanded by remember { mutableStateOf(false) }
  var selectedChoice by remember { mutableStateOf<Int?>(null) }
  var showCorrection by remember { mutableStateOf(false) }

  Card(
    modifier = Modifier.fillMaxWidth(),
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
  ) {
    Column(modifier = Modifier.padding(12.dp)) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .clickable { isExpanded = !isExpanded },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column(modifier = Modifier.weight(1f)) {
          Text(
            text = skill.title,
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface
          )
          Text(
            text = skill.subtitle,
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp, color = MedicalBlue)
          )
        }
        Icon(
          imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
          contentDescription = null,
          tint = MaterialTheme.colorScheme.primary
        )
      }

      AnimatedVisibility(visible = isExpanded) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
          verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          // 1. Explication simple
          Text(
            text = skill.explanation,
            style = MaterialTheme.typography.bodySmall.copy(lineHeight = 18.sp),
            color = MaterialTheme.colorScheme.onSurface
          )

          // 2. Formulations professionnelles
          Card(
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = MedicalBlue.copy(alpha = 0.08f))
          ) {
            Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
              Text(
                text = "💬 Formulations professionnelles recommandées :",
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = MedicalBlue)
              )
              skill.professionalPhrasings.forEach { phrase ->
                Text(
                  text = phrase,
                  style = MaterialTheme.typography.bodySmall.copy(
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                    fontSize = 11.5.sp
                  ),
                  color = MaterialTheme.colorScheme.onSurface
                )
              }
            }
          }

          // 3. Erreurs à éviter
          Card(
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = AlertRed.copy(alpha = 0.08f))
          ) {
            Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(3.dp)) {
              Text(
                text = "⛔ Erreurs à éviter absolument :",
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = AlertRed)
              )
              skill.errorsToAvoid.forEach { err ->
                Row(verticalAlignment = Alignment.Top) {
                  Text("• ", color = AlertRed, fontWeight = FontWeight.Bold)
                  Text(text = err, style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp))
                }
              }
            }
          }

          // 4. Exemples de réponses patients et relances
          Card(
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
          ) {
            Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
              Text(
                text = "🗣️ Réponses possibles du patient & Relance conseillée :",
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = MedicalTeal)
              )
              skill.patientResponsesExamples.forEach { (rep, relance) ->
                Column(modifier = Modifier.padding(vertical = 2.dp)) {
                  Text(
                    text = "Patient : $rep",
                    style = MaterialTheme.typography.bodySmall.copy(
                      fontSize = 11.sp,
                      color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                  )
                  Text(
                    text = "→ Relance : $relance",
                    style = MaterialTheme.typography.bodySmall.copy(
                      fontSize = 11.sp,
                      fontWeight = FontWeight.SemiBold,
                      color = MedicalTeal
                    )
                  )
                }
              }
            }
          }

          // 5. Exercice pratique interactif avec correction
          Card(
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFEEF2FF)),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF6366F1).copy(alpha = 0.3f))
          ) {
            Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                  imageVector = Icons.Default.QuestionAnswer,
                  contentDescription = null,
                  tint = Color(0xFF6366F1),
                  modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                  text = "Exercice Pratique d'Entraînement :",
                  style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = Color(0xFF4F46E5))
                )
              }

              Text(
                text = skill.practicalExerciseQuestion,
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold)
              )

              skill.practicalExerciseChoices.forEachIndexed { index, choice ->
                val isSelected = selectedChoice == index
                val isCorrect = index == skill.correctChoiceIndex

                Box(
                  modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(
                      if (showCorrection) {
                        if (isCorrect) AlertGreen.copy(alpha = 0.15f)
                        else if (isSelected) AlertRed.copy(alpha = 0.15f)
                        else Color.White
                      } else {
                        if (isSelected) MaterialTheme.colorScheme.primaryContainer else Color.White
                      }
                    )
                    .clickable {
                      selectedChoice = index
                      showCorrection = true
                    }
                    .padding(8.dp)
                ) {
                  Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                      text = "${('A' + index)}. ",
                      fontWeight = FontWeight.Bold,
                      fontSize = 11.5.sp
                    )
                    Text(
                      text = choice,
                      style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp),
                      modifier = Modifier.weight(1f)
                    )
                  }
                }
              }

              if (showCorrection) {
                Box(
                  modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(AlertGreen.copy(alpha = 0.12f))
                    .padding(8.dp)
                ) {
                  Column {
                    Text(
                      text = "✓ Explication & Correction :",
                      style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = AlertGreen)
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                      text = skill.practicalExerciseCorrection,
                      style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurface)
                    )
                  }
                }
              }
            }
          }
        }
      }
    }
  }
}

// ══════════════════════════════════════════════════════════════════════════════
// MODULE B — ORGANISATION DES INFORMATIONS (9 RUBRIQUES) & QUIZ DE CLASSEMENT
// ══════════════════════════════════════════════════════════════════════════════
@Composable
fun InfoOrganizationTab() {
  val guides = ClinicalTrainingDataProvider.infoCategoriesGuide
  val exercises = ClinicalTrainingDataProvider.classificationExercises

  Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
    Text(
      text = "Classification des Données dans les 9 Rubriques Canoniques",
      style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
      color = MaterialTheme.colorScheme.primary
    )
    Text(
      text = "Savoir classer chaque information recueillie dans sa rubrique standardisée évite les confusions diagnostiques et garantit la transmission claire au médecin.",
      style = MaterialTheme.typography.bodySmall,
      color = MaterialTheme.colorScheme.onSurfaceVariant
    )

    // Fiches des 9 rubriques
    guides.forEach { guide ->
      Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f))
      ) {
        Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
          Text(
            text = guide.categoryName,
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
          )
          Text(text = guide.description, style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp))

          Text(
            text = "À inclure : ${guide.whatToInclude.joinToString(", ")}",
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp, color = AlertGreen)
          )
          Text(
            text = "À exclure : ${guide.whatToExclude}",
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp, color = AlertRed)
          )
          Text(
            text = "Exemple hospitalier : « ${guide.hospitalExample} »",
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp, fontStyle = androidx.compose.ui.text.font.FontStyle.Italic)
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(6.dp))

    // Exercices interactifs de classement
    Text(
      text = "Exercices Pratiques de Classement Sémiologique :",
      style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
      color = Color(0xFF6366F1)
    )

    exercises.forEachIndexed { i, ex ->
      ClassificationExerciseCard(index = i + 1, exercise = ex)
    }
  }
}

@Composable
fun ClassificationExerciseCard(index: Int, exercise: ClassificationExercise) {
  var selectedCategory by remember { mutableStateOf<String?>(null) }
  var isAnswered by remember { mutableStateOf(false) }

  Card(
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(containerColor = Color(0xFFF8FAFC)),
    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)),
    modifier = Modifier.fillMaxWidth()
  ) {
    Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
      Text(
        text = "Exercice $index — Classez cette déclaration du patient :",
        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
      )

      Box(
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(8.dp))
          .background(MaterialTheme.colorScheme.surfaceVariant)
          .padding(10.dp)
      ) {
        Text(
          text = exercise.rawPatientQuote,
          style = MaterialTheme.typography.bodySmall.copy(
            fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
            fontWeight = FontWeight.SemiBold
          )
        )
      }

      Text(
        text = "Dans quelle rubrique de l'observation rangez-vous cette information ?",
        style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp)
      )

      exercise.options.chunked(2).forEach { rowOptions ->
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          rowOptions.forEach { opt ->
            val isSelected = selectedCategory == opt
            val isCorrect = opt == exercise.correctCategory

            OutlinedButton(
              onClick = {
                selectedCategory = opt
                isAnswered = true
              },
              modifier = Modifier.weight(1f),
              shape = RoundedCornerShape(8.dp),
              colors = ButtonDefaults.outlinedButtonColors(
                containerColor = if (isAnswered) {
                  if (isCorrect) AlertGreen.copy(alpha = 0.2f)
                  else if (isSelected) AlertRed.copy(alpha = 0.2f)
                  else Color.Transparent
                } else {
                  if (isSelected) MaterialTheme.colorScheme.primaryContainer else Color.Transparent
                }
              )
            ) {
              Text(
                text = opt,
                fontSize = 11.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
              )
            }
          }
        }
      }

      if (isAnswered) {
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(AlertGreen.copy(alpha = 0.12f))
            .padding(8.dp)
        ) {
          Column {
            Text(
              text = if (selectedCategory == exercise.correctCategory) "✓ Réponse exacte !" else "✗ Réponse attendue : ${exercise.correctCategory}",
              style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                color = if (selectedCategory == exercise.correctCategory) AlertGreen else AlertRed
              )
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
              text = exercise.explanation,
              style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp)
            )
          }
        }
      }
    }
  }
}

// ══════════════════════════════════════════════════════════════════════════════
// MODULE C — OBSERVATION MÉDICALE MÉTHODIQUE
// ══════════════════════════════════════════════════════════════════════════════
@Composable
fun ObservationMethodTab() {
  val skills = ClinicalTrainingDataProvider.observationSkills

  Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
    Text(
      text = "Méthodologie de l'Observation Médicale Hospitalière",
      style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
      color = MaterialTheme.colorScheme.primary
    )
    Text(
      text = "Apprenez à rédiger une observation claire, à distinguer signes subjectifs et objectifs, et à structurer une conclusion médicale.",
      style = MaterialTheme.typography.bodySmall,
      color = MaterialTheme.colorScheme.onSurfaceVariant
    )

    skills.forEach { skill ->
      var isExpanded by remember { mutableStateOf(false) }
      var showAnswer by remember { mutableStateOf(false) }

      Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f)),
        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
      ) {
        Column(modifier = Modifier.padding(12.dp)) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .clickable { isExpanded = !isExpanded },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column(modifier = Modifier.weight(1f)) {
              Text(
                text = skill.title,
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface
              )
              Text(
                text = skill.objective,
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp, color = MedicalBlue)
              )
            }
            Icon(
              imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
              contentDescription = null,
              tint = MaterialTheme.colorScheme.primary
            )
          }

          AnimatedVisibility(visible = isExpanded) {
            Column(
              modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
              verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              Text(
                text = skill.detailedGuide,
                style = MaterialTheme.typography.bodySmall.copy(lineHeight = 18.sp)
              )

              // Points clés
              Card(
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
              ) {
                Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(3.dp)) {
                  Text(
                    text = "Points clés à retenir :",
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = MedicalTeal)
                  )
                  skill.keyPoints.forEach { pt ->
                    Row(verticalAlignment = Alignment.Top) {
                      Text("• ", color = MedicalTeal, fontWeight = FontWeight.Bold)
                      Text(text = pt, style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp))
                    }
                  }
                }
              }

              // Bon vs Mauvais exemple
              Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Box(
                  modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(6.dp))
                    .background(AlertRed.copy(alpha = 0.1f))
                    .padding(8.dp)
                ) {
                  Text(
                    text = "Mauvais exemple : ${skill.badExample}",
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp, color = AlertRed)
                  )
                }

                Box(
                  modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(6.dp))
                    .background(AlertGreen.copy(alpha = 0.12f))
                    .padding(8.dp)
                ) {
                  Text(
                    text = "Exemple recommandé : ${skill.goodExample}",
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp, color = Color(0xFF065F46))
                  )
                }
              }

              // Exercice d'application
              Card(
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F5F9))
              ) {
                Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                  Text(
                    text = "Exercice pratique :",
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                  )
                  Text(
                    text = skill.exercisePrompt,
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp)
                  )

                  OutlinedButton(
                    onClick = { showAnswer = !showAnswer },
                    shape = RoundedCornerShape(6.dp),
                    modifier = Modifier.fillMaxWidth().height(36.dp)
                  ) {
                    Text(if (showAnswer) "Masquer la correction" else "Voir la correction commentée", fontSize = 11.sp)
                  }

                  if (showAnswer) {
                    Box(
                      modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(6.dp))
                        .background(AlertGreen.copy(alpha = 0.15f))
                        .padding(8.dp)
                    ) {
                      Text(
                        text = skill.exerciseCorrection,
                        style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp, color = Color(0xFF047857))
                      )
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }
}

// ══════════════════════════════════════════════════════════════════════════════
// MODULE D — SYNTHÈSE CLINIQUE & DÉMARCHE DIAGNOSTIQUE
// ══════════════════════════════════════════════════════════════════════════════
@Composable
fun ClinicalSynthesisTab() {
  val cases = ClinicalTrainingDataProvider.synthesisCases

  Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
    Text(
      text = "De la Plainte du Patient aux Hypothèses Diagnostiques",
      style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
      color = MaterialTheme.colorScheme.primary
    )
    Text(
      text = "La démarche clinique repose sur un entonnoir rigoureux : Symptômes rapportés → Signes physiques objectivés → Regroupement syndromique → Hypothèses hiérarchisées → Plan d'action.",
      style = MaterialTheme.typography.bodySmall,
      color = MaterialTheme.colorScheme.onSurfaceVariant
    )

    // Entonnoir méthodologique
    Card(
      shape = RoundedCornerShape(12.dp),
      colors = CardDefaults.cardColors(containerColor = MedicalBlue.copy(alpha = 0.08f)),
      border = androidx.compose.foundation.BorderStroke(1.dp, MedicalBlue.copy(alpha = 0.35f))
    ) {
      Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(
          text = "L'entonnoir du raisonnement diagnostique en 5 étapes :",
          style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold, color = MedicalBlue)
        )

        val steps = listOf(
          "1. Symptômes (subjectifs) : plaintes recueillies lors de l'interrogatoire.",
          "2. Signes physiques (objectifs) : anomalies vérifiées lors de l'examen clinique.",
          "3. Syndrome : regroupement cohérent de signes fonctionnels et physiques.",
          "4. Diagnostics différentiels : hypothèse principale + urgences vitales à éliminer.",
          "5. Plan d'investigation : examens complémentaires ciblés de 1ère intention."
        )

        steps.forEach { s ->
          Row(verticalAlignment = Alignment.Top) {
            Text("→ ", color = MedicalBlue, fontWeight = FontWeight.Bold)
            Text(text = s, style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.5.sp))
          }
        }
      }
    }

    Text(
      text = "Cas Didactiques d'Entraînement au Raisonnement :",
      style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
    )

    cases.forEach { c ->
      var isExpanded by remember { mutableStateOf(false) }

      Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.35f))
      ) {
        Column(modifier = Modifier.padding(12.dp)) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .clickable { isExpanded = !isExpanded },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column(modifier = Modifier.weight(1f)) {
              Text(
                text = c.title,
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface
              )
              Text(
                text = c.patientProfile,
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp, color = MaterialTheme.colorScheme.primary)
              )
            }
            Icon(
              imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
              contentDescription = null,
              tint = MaterialTheme.colorScheme.primary
            )
          }

          AnimatedVisibility(visible = isExpanded) {
            Column(
              modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
              verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              // Symptômes
              Text(
                text = "1. Symptômes recueillis à l'interrogatoire :",
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
              )
              c.symptomsCollected.forEach { s ->
                Text(text = "• $s", style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp))
              }

              // Signes physiques
              Text(
                text = "2. Signes physiques objectivés à l'examen :",
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
              )
              c.physicalSignsFound.forEach { p ->
                Text(text = "• $p", style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp))
              }

              // Démarche de raisonnement
              Card(
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
              ) {
                Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                  Text(
                    text = "Cheminement du raisonnement syndromique :",
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = MedicalTeal)
                  )
                  Text(
                    text = c.clinicalReasoningPath,
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp, lineHeight = 17.sp)
                  )
                }
              }

              // Hypothèse principale et urgences
              Box(
                modifier = Modifier
                  .fillMaxWidth()
                  .clip(RoundedCornerShape(8.dp))
                  .background(AlertGreen.copy(alpha = 0.12f))
                  .padding(8.dp)
              ) {
                Text(
                  text = "Hypothèse diagnostique principale : ${c.primaryHypothesis}",
                  style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold, color = AlertGreen)
                )
              }

              Box(
                modifier = Modifier
                  .fillMaxWidth()
                  .clip(RoundedCornerShape(8.dp))
                  .background(AlertRed.copy(alpha = 0.1f))
                  .padding(8.dp)
              ) {
                Text(
                  text = "Urgence vitale à éliminer formellement : ${c.emergencyToRuleOut}",
                  style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold, color = AlertRed)
                )
              }

              // Plan d'action immédiat
              Text(
                text = "Plan d'action & investigations immédiates :",
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
              )
              c.immediateActionPlan.forEach { action ->
                Row(verticalAlignment = Alignment.Top) {
                  Text("✓ ", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                  Text(text = action, style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp))
                }
              }
            }
          }
        }
      }
    }
  }
}
