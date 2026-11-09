package com.example.ui.semiology

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.SemiologyCategory
import com.example.data.model.SemiologySheet
import com.example.ui.components.LegalDisclaimerBanner
import com.example.ui.theme.AlertGreen
import com.example.ui.theme.AlertOrange
import com.example.ui.theme.AlertRed
import com.example.ui.theme.MedicalBlue
import com.example.ui.theme.MedicalTeal

@Composable
fun SemiologyScreen(
  viewModel: SemiologyViewModel,
  initialSubTab: Int = 0,
  modifier: Modifier = Modifier
) {
  var selectedTab by remember { mutableIntStateOf(initialSubTab) }
  val state by viewModel.state.collectAsState()

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
          text = "Sémiologie & Entraînement",
          style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
          color = Color.White
        )
        Text(
          text = "Encyclopédie sémiologique (14 rubriques) et QCM d'entraînement",
          style = MaterialTheme.typography.bodySmall,
          color = Color.White.copy(alpha = 0.85f)
        )
      }
    }

    TabRow(
      selectedTabIndex = selectedTab,
      containerColor = MaterialTheme.colorScheme.surface,
      contentColor = MaterialTheme.colorScheme.primary
    ) {
      Tab(
        selected = selectedTab == 0,
        onClick = {
          selectedTab = 0
          viewModel.selectSheet(null)
        },
        text = { Text("Bibliothèque Pédagogique", fontWeight = FontWeight.SemiBold) }
      )
      Tab(
        selected = selectedTab == 1,
        onClick = { selectedTab = 1 },
        text = { Text("Quiz QCM (${state.score}/${state.quizQuestions.size})", fontWeight = FontWeight.SemiBold) }
      )
    }

    if (selectedTab == 0) {
      if (state.selectedSheet != null) {
        SemiologyDetailView(sheet = state.selectedSheet!!, onBack = { viewModel.selectSheet(null) })
      } else {
        SemiologyListView(viewModel = viewModel, state = state)
      }
    } else {
      QuizSectionView(viewModel = viewModel, state = state)
    }
  }
}

@Composable
fun SemiologyListView(viewModel: SemiologyViewModel, state: SemiologyUiState) {
  val filteredSheets = viewModel.getFilteredSheets()

  LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(16.dp),
    verticalArrangement = Arrangement.spacedBy(12.dp)
  ) {
    item { LegalDisclaimerBanner() }

    // Search bar
    item {
      OutlinedTextField(
        value = state.searchQuery,
        onValueChange = { viewModel.updateSearchQuery(it) },
        placeholder = { Text("Rechercher un signe ou syndrome clinique...") },
        leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = null) },
        modifier = Modifier.fillMaxWidth().testTag("semiology_search_input"),
        shape = RoundedCornerShape(12.dp),
        singleLine = true
      )
    }

    // Category chips
    item {
      LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        item {
          val isAllSelected = state.selectedCategory == null
          Box(
            modifier = Modifier
              .clip(RoundedCornerShape(20.dp))
              .background(if (isAllSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface)
              .border(1.dp, if (isAllSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline, RoundedCornerShape(20.dp))
              .clickable { viewModel.selectCategory(null) }
              .padding(horizontal = 14.dp, vertical = 6.dp)
          ) {
            Text(
              text = "Tous les appareils",
              style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
              color = if (isAllSelected) Color.White else MaterialTheme.colorScheme.onSurface
            )
          }
        }
        items(SemiologyCategory.entries.toTypedArray()) { cat ->
          val isSelected = state.selectedCategory == cat
          Box(
            modifier = Modifier
              .clip(RoundedCornerShape(20.dp))
              .background(if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface)
              .border(1.dp, if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline, RoundedCornerShape(20.dp))
              .clickable { viewModel.selectCategory(cat) }
              .padding(horizontal = 14.dp, vertical = 6.dp)
          ) {
            Text(
              text = cat.label,
              style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
              color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
            )
          }
        }
      }
    }

    item {
      Text(
        text = "Encyclopédie Sémiologique (${filteredSheets.size} fiches complètes)",
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
        color = MaterialTheme.colorScheme.onBackground
      )
    }

    items(filteredSheets) { sheet ->
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .clickable { viewModel.selectSheet(sheet) },
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
      ) {
        Column(modifier = Modifier.padding(16.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Box(
              modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(horizontal = 8.dp, vertical = 3.dp)
            ) {
              Text(
                text = sheet.category.label,
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSecondaryContainer
              )
            }
            Text(
              text = sheet.subCategory,
              style = MaterialTheme.typography.labelSmall,
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )
          }
          Spacer(modifier = Modifier.height(8.dp))
          Text(
            text = sheet.name,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface
          )
          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text = sheet.definition,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
          )
        }
      }
    }
  }
}

@Composable
fun SemiologyDetailView(sheet: SemiologySheet, onBack: () -> Unit) {
  LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(16.dp),
    verticalArrangement = Arrangement.spacedBy(14.dp)
  ) {
    item {
      Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
      ) {
        IconButton(onClick = onBack) {
          Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Retour")
        }
        Column {
          Text(
            text = "Fiche Sémiologique Médicale",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
          )
          Text(
            text = "14 rubriques pédagogiques standardisées",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }
      }
    }

    item {
      Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
      ) {
        Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Box(
              modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
              Text(
                text = sheet.category.label,
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onPrimaryContainer
              )
            }
            Text(
              text = sheet.subCategory,
              style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
              color = MaterialTheme.colorScheme.secondary
            )
          }

          Text(
            text = sheet.name,
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface
          )

          // 1. Définition
          SectionTitle("1. Définition du Signe ou Syndrome")
          Text(text = sheet.definition, style = MaterialTheme.typography.bodyMedium)

          // 2. Physiopathologie
          SectionTitle("2. Physiopathologie & Mécanismes")
          Text(text = sheet.physiopathology, style = MaterialTheme.typography.bodyMedium)

          // 3. Étiologies principales
          SectionTitle("3. Étiologies Principales")
          sheet.etiologies.forEach { etio ->
            BulletItem(text = etio)
          }

          // 4. Circonstances d'apparition
          SectionTitle("4. Circonstances d'Apparition")
          Text(text = sheet.circumstances, style = MaterialTheme.typography.bodyMedium)

          // 5. Interrogatoire (Anamnèse)
          SectionTitle("5. Interrogatoire Spécifique (Anamnèse)")
          Text(text = sheet.anamnesis, style = MaterialTheme.typography.bodyMedium)

          // 6. Méthode de recherche
          SectionTitle("6. Méthode de Recherche & Position")
          Text(text = sheet.searchMethod, style = MaterialTheme.typography.bodyMedium)

          // 7. Examen clinique physique
          SectionTitle("7. Examen Clinique & Manœuvres Physiques")
          Text(text = sheet.physicalExam, style = MaterialTheme.typography.bodyMedium)

          // 8. Signes associés
          SectionTitle("8. Signes Cliniques Associés")
          sheet.associatedSigns.forEach { sign ->
            BulletItem(text = sign)
          }

          // 9. Signification clinique
          SectionTitle("9. Signification Clinique & Orientation")
          Text(text = sheet.clinicalSignificance, style = MaterialTheme.typography.bodyMedium)

          // 10. Diagnostics différentiels
          SectionTitle("10. Diagnostics Différentiels")
          sheet.differentialDiagnoses.forEach { dd ->
            BulletItem(text = dd)
          }

          // 11. Examens complémentaires utiles
          SectionTitle("11. Examens Complémentaires Recommandés")
          sheet.complementaryExams.forEach { ex ->
            BulletItem(text = ex)
          }

          // 12. Signes de gravité / Complications (Highlight AlertRed)
          SectionTitle("12. Signes de Gravité & Drapeaux Rouges")
          sheet.gravitySigns.forEach { gs ->
            Row(modifier = Modifier.padding(start = 4.dp), verticalAlignment = Alignment.Top) {
              Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = null,
                tint = AlertRed,
                modifier = Modifier.size(16.dp).padding(top = 2.dp)
              )
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = gs,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                color = AlertRed
              )
            }
          }

          // 13. Limites et pièges (Highlight AlertOrange)
          SectionTitle("13. Limites Sémiologiques & Pièges Diagnostiques")
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .clip(RoundedCornerShape(8.dp))
              .background(AlertOrange.copy(alpha = 0.12f))
              .padding(12.dp)
          ) {
            Text(
              text = sheet.pitfallsAndLimits,
              style = MaterialTheme.typography.bodyMedium,
              color = MaterialTheme.colorScheme.onSurface
            )
          }

          // 14. Points essentiels (Highlight AlertGreen)
          SectionTitle("14. Points Essentiels à Retenir")
          sheet.keyPoints.forEach { kp ->
            Row(modifier = Modifier.padding(start = 4.dp), verticalAlignment = Alignment.Top) {
              Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                tint = AlertGreen,
                modifier = Modifier.size(16.dp).padding(top = 2.dp)
              )
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = kp,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface
              )
            }
          }
        }
      }
    }
  }
}

@Composable
fun BulletItem(text: String) {
  Row(modifier = Modifier.padding(start = 4.dp, top = 2.dp)) {
    Text("• ", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
    Text(text = text, style = MaterialTheme.typography.bodyMedium)
  }
}

@Composable
fun SectionTitle(text: String) {
  Text(
    text = text,
    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
    color = MaterialTheme.colorScheme.primary,
    modifier = Modifier.padding(top = 8.dp)
  )
}

// ══════════════════════════════════════════════════════
// QUIZ & QCM SECTION
// ══════════════════════════════════════════════════════
@Composable
fun QuizSectionView(viewModel: SemiologyViewModel, state: SemiologyUiState) {
  Column(modifier = Modifier.fillMaxSize()) {
    // Category selector for quiz
    LazyRow(
      contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      item {
        val isAll = state.quizCategory == null
        Box(
          modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(if (isAll) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface)
            .border(1.dp, if (isAll) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline, RoundedCornerShape(20.dp))
            .clickable { viewModel.selectQuizCategory(null) }
            .padding(horizontal = 14.dp, vertical = 6.dp)
        ) {
          Text(
            text = "Tous les QCM (90)",
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
            color = if (isAll) Color.White else MaterialTheme.colorScheme.onSurface
          )
        }
      }
      items(SemiologyCategory.entries.toTypedArray()) { cat ->
        val isSelected = state.quizCategory == cat
        val count = when (cat) {
          SemiologyCategory.CARDIOVASCULAR -> 20
          else -> 10
        }
        Box(
          modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface)
            .border(1.dp, if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline, RoundedCornerShape(20.dp))
            .clickable { viewModel.selectQuizCategory(cat) }
            .padding(horizontal = 14.dp, vertical = 6.dp)
        ) {
          Text(
            text = "${cat.label} ($count)",
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
            color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
          )
        }
      }
    }

    if (state.isQuizFinished) {
      // Quiz Result Screen
      val totalQuestions = state.quizQuestions.size
      val percentage = if (totalQuestions > 0) (state.score * 100) / totalQuestions else 0

      Box(
        modifier = Modifier
          .fillMaxSize()
          .padding(24.dp),
        contentAlignment = Alignment.Center
      ) {
        Card(
          modifier = Modifier.fillMaxWidth(),
          shape = RoundedCornerShape(20.dp),
          colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
          elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
          Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(14.dp)
          ) {
            Icon(
              imageVector = Icons.Default.Psychology,
              contentDescription = null,
              tint = MaterialTheme.colorScheme.primary,
              modifier = Modifier.size(54.dp)
            )
            Text(
              text = "Session QCM Terminée !",
              style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
            Text(
              text = "Score & Performance :",
              style = MaterialTheme.typography.bodyMedium
            )
            Text(
              text = "$percentage %",
              style = MaterialTheme.typography.displayMedium.copy(
                fontWeight = FontWeight.Black,
                color = if (percentage >= 70) AlertGreen else if (percentage >= 50) AlertOrange else AlertRed
              )
            )
            Text(
              text = "${state.score} bonnes réponses sur $totalQuestions questions",
              style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
              text = if (percentage >= 80) "Excellente maîtrise sémiologique clinique !"
              else if (percentage >= 60) "Bonne base clinique. Révisez les fiches de l'appareil pour consolider vos acquis."
              else "Approfondissez les fiches sémiologiques détaillées de cet appareil.",
              style = MaterialTheme.typography.bodyMedium,
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Button(
              onClick = { viewModel.restartQuiz() },
              modifier = Modifier.fillMaxWidth().height(48.dp),
              shape = RoundedCornerShape(10.dp)
            ) {
              Icon(imageVector = Icons.Default.Refresh, contentDescription = null)
              Spacer(modifier = Modifier.width(8.dp))
              Text("Recommencer l'entraînement")
            }
          }
        }
      }
    } else if (state.quizQuestions.isNotEmpty()) {
      val q = state.quizQuestions[state.currentQuestionIndex]

      LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
      ) {
        // Progress
        item {
          Column {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween
            ) {
              Text(
                text = "Question ${state.currentQuestionIndex + 1} / ${state.quizQuestions.size}",
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
              )
              Text(
                text = "Score : ${state.score} pts",
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.secondary
              )
            }
            Spacer(modifier = Modifier.height(6.dp))
            LinearProgressIndicator(
              progress = { (state.currentQuestionIndex + 1).toFloat() / state.quizQuestions.size },
              modifier = Modifier.fillMaxWidth().height(6.dp).clip(RoundedCornerShape(3.dp))
            )
          }
        }

        // Question card
        item {
          Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
          ) {
            Column(modifier = Modifier.padding(18.dp)) {
              Box(
                modifier = Modifier
                  .clip(RoundedCornerShape(6.dp))
                  .background(MaterialTheme.colorScheme.primaryContainer)
                  .padding(horizontal = 8.dp, vertical = 3.dp)
              ) {
                Text(
                  text = q.category.label,
                  style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                  color = MaterialTheme.colorScheme.onPrimaryContainer
                )
              }
              Spacer(modifier = Modifier.height(10.dp))
              Text(
                text = q.question,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface
              )
            }
          }
        }

        // Options A, B, C, D
        items(q.options.indices.toList()) { index ->
          val optionText = q.options[index]
          val isSelected = state.selectedOptionIndex == index
          val isSubmitted = state.isAnswerSubmitted
          val isCorrect = index == q.correctOptionIndex

          val (bgColor, borderColor) = when {
            isSubmitted && isCorrect -> AlertGreen.copy(alpha = 0.15f) to AlertGreen
            isSubmitted && isSelected && !isCorrect -> AlertRed.copy(alpha = 0.15f) to AlertRed
            isSelected -> MaterialTheme.colorScheme.primary.copy(alpha = 0.12f) to MaterialTheme.colorScheme.primary
            else -> MaterialTheme.colorScheme.surface to MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
          }

          Box(
            modifier = Modifier
              .fillMaxWidth()
              .clip(RoundedCornerShape(12.dp))
              .background(bgColor)
              .border(1.5.dp, borderColor, RoundedCornerShape(12.dp))
              .clickable(enabled = !isSubmitted) { viewModel.selectQuizOption(index) }
              .padding(14.dp)
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Box(
                modifier = Modifier
                  .size(28.dp)
                  .clip(CircleShape)
                  .background(
                    if (isSubmitted && isCorrect) AlertGreen
                    else if (isSubmitted && isSelected) AlertRed
                    else if (isSelected) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.surfaceVariant
                  ),
                contentAlignment = Alignment.Center
              ) {
                Text(
                  text = ('A' + index).toString(),
                  style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = if (isSelected || (isSubmitted && isCorrect)) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                  )
                )
              }
              Spacer(modifier = Modifier.width(12.dp))
              Text(
                text = optionText,
                style = MaterialTheme.typography.bodyMedium.copy(
                  fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
                ),
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f)
              )
            }
          }
        }

        // Action Button
        item {
          if (!state.isAnswerSubmitted) {
            Button(
              onClick = { viewModel.submitAnswer() },
              modifier = Modifier.fillMaxWidth().height(48.dp).testTag("btn_submit_quiz_answer"),
              enabled = state.selectedOptionIndex != null,
              shape = RoundedCornerShape(10.dp)
            ) {
              Text("Valider la réponse", fontWeight = FontWeight.Bold)
            }
          } else {
            Button(
              onClick = { viewModel.nextQuestion() },
              modifier = Modifier.fillMaxWidth().height(48.dp).testTag("btn_next_quiz_question"),
              shape = RoundedCornerShape(10.dp),
              colors = ButtonDefaults.buttonColors(containerColor = MedicalBlue)
            ) {
              Text(
                text = if (state.currentQuestionIndex + 1 < state.quizQuestions.size) "Question suivante" else "Voir les résultats",
                fontWeight = FontWeight.Bold
              )
            }
          }
        }

        // Pedagogical Explanation when submitted
        if (state.isAnswerSubmitted) {
          item {
            Card(
              modifier = Modifier.fillMaxWidth(),
              shape = RoundedCornerShape(14.dp),
              colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
              Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                // Correct answer explanation
                Row(verticalAlignment = Alignment.CenterVertically) {
                  Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null, tint = AlertGreen)
                  Spacer(modifier = Modifier.width(8.dp))
                  Text(
                    text = "Pourquoi la bonne réponse est exacte :",
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                    color = AlertGreen
                  )
                }
                Text(text = q.explanation, style = MaterialTheme.typography.bodyMedium)

                // Why wrong options are wrong
                if (q.wrongOptionsExplanations.isNotEmpty()) {
                  Spacer(modifier = Modifier.height(4.dp))
                  Text(
                    text = "Pourquoi les autres propositions sont fausses :",
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.error
                  )
                  q.wrongOptionsExplanations.forEach { wrongExpl ->
                    Row(modifier = Modifier.padding(start = 4.dp, top = 2.dp)) {
                      Text("✕ ", fontWeight = FontWeight.Bold, color = AlertRed)
                      Text(text = wrongExpl, style = MaterialTheme.typography.bodySmall)
                    }
                  }
                }

                if (q.clinicalPearl.isNotBlank()) {
                  Spacer(modifier = Modifier.height(4.dp))
                  Box(
                    modifier = Modifier
                      .fillMaxWidth()
                      .clip(RoundedCornerShape(8.dp))
                      .background(MedicalTeal.copy(alpha = 0.12f))
                      .padding(10.dp)
                  ) {
                    Text(
                      text = "💡 Perle Clinique : ${q.clinicalPearl}",
                      style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = MedicalTeal
                      )
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
