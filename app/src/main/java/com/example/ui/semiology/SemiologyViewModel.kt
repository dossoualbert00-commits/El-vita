package com.example.ui.semiology

import androidx.lifecycle.ViewModel
import com.example.data.model.QuizQuestion
import com.example.data.model.SemiologyCategory
import com.example.data.model.SemiologyDataProvider
import com.example.data.model.SemiologySheet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class SemiologyUiState(
  val sheets: List<SemiologySheet> = SemiologyDataProvider.sheets,
  val selectedCategory: SemiologyCategory? = null,
  val selectedSheet: SemiologySheet? = null,
  val searchQuery: String = "",
  // Quiz state
  val quizCategory: SemiologyCategory? = null,
  val quizQuestions: List<QuizQuestion> = SemiologyDataProvider.quizQuestions,
  val currentQuestionIndex: Int = 0,
  val selectedOptionIndex: Int? = null,
  val isAnswerSubmitted: Boolean = false,
  val score: Int = 0,
  val answeredCount: Int = 0,
  val isQuizFinished: Boolean = false
)

class SemiologyViewModel : ViewModel() {

  private val _state = MutableStateFlow(SemiologyUiState())
  val state: StateFlow<SemiologyUiState> = _state.asStateFlow()

  fun selectCategory(category: SemiologyCategory?) {
    _state.value = _state.value.copy(
      selectedCategory = category,
      selectedSheet = null
    )
  }

  fun selectSheet(sheet: SemiologySheet?) {
    _state.value = _state.value.copy(selectedSheet = sheet)
  }

  fun updateSearchQuery(query: String) {
    _state.value = _state.value.copy(searchQuery = query)
  }

  fun getFilteredSheets(): List<SemiologySheet> {
    val s = _state.value
    return s.sheets.filter { sheet ->
      val matchCategory = s.selectedCategory == null || sheet.category == s.selectedCategory
      val matchQuery = s.searchQuery.isBlank() ||
        sheet.name.contains(s.searchQuery, ignoreCase = true) ||
        sheet.definition.contains(s.searchQuery, ignoreCase = true) ||
        sheet.clinicalSignificance.contains(s.searchQuery, ignoreCase = true)
      matchCategory && matchQuery
    }
  }

  // Quiz methods
  fun selectQuizCategory(category: SemiologyCategory?) {
    val allQuestions = SemiologyDataProvider.quizQuestions
    val filtered = if (category == null) {
      allQuestions
    } else {
      allQuestions.filter { it.category == category }
    }
    _state.value = _state.value.copy(
      quizCategory = category,
      quizQuestions = filtered,
      currentQuestionIndex = 0,
      selectedOptionIndex = null,
      isAnswerSubmitted = false,
      score = 0,
      answeredCount = 0,
      isQuizFinished = false
    )
  }

  fun selectQuizOption(index: Int) {
    if (!_state.value.isAnswerSubmitted) {
      _state.value = _state.value.copy(selectedOptionIndex = index)
    }
  }

  fun submitAnswer() {
    val s = _state.value
    if (s.selectedOptionIndex == null || s.isAnswerSubmitted) return

    val currentQ = s.quizQuestions[s.currentQuestionIndex]
    val isCorrect = s.selectedOptionIndex == currentQ.correctOptionIndex
    val newScore = if (isCorrect) s.score + 1 else s.score

    _state.value = s.copy(
      isAnswerSubmitted = true,
      score = newScore,
      answeredCount = s.answeredCount + 1
    )
  }

  fun nextQuestion() {
    val s = _state.value
    if (s.currentQuestionIndex + 1 < s.quizQuestions.size) {
      _state.value = s.copy(
        currentQuestionIndex = s.currentQuestionIndex + 1,
        selectedOptionIndex = null,
        isAnswerSubmitted = false
      )
    } else {
      _state.value = s.copy(isQuizFinished = true)
    }
  }

  fun restartQuiz() {
    _state.value = _state.value.copy(
      currentQuestionIndex = 0,
      selectedOptionIndex = null,
      isAnswerSubmitted = false,
      score = 0,
      answeredCount = 0,
      isQuizFinished = false
    )
  }
}
