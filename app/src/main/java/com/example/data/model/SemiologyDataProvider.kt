package com.example.data.model

import com.example.data.model.quiz.quizCardioQuestions
import com.example.data.model.quiz.quizDermatologyQuestions
import com.example.data.model.quiz.quizDigestiveQuestions
import com.example.data.model.quiz.quizGeneralQuestions
import com.example.data.model.quiz.quizNeuroQuestions
import com.example.data.model.quiz.quizRespQuestions
import com.example.data.model.quiz.quizRheumatologyQuestions
import com.example.data.model.quiz.quizUrinaryQuestions
import com.example.data.model.sheets.SemiologyCardioSheets
import com.example.data.model.sheets.SemiologyDermatologySheets
import com.example.data.model.sheets.SemiologyDigestiveSheets
import com.example.data.model.sheets.SemiologyGeneralSheets
import com.example.data.model.sheets.SemiologyNeuroSheets
import com.example.data.model.sheets.SemiologyRespSheets
import com.example.data.model.sheets.SemiologyRheumatologySheets
import com.example.data.model.sheets.SemiologyUrinarySheets

object SemiologyDataProvider {

  val sheets: List<SemiologySheet> by lazy {
    SemiologyCardioSheets.sheets +
      SemiologyRespSheets.sheets +
      SemiologyDigestiveSheets.sheets +
      SemiologyNeuroSheets.sheets +
      SemiologyUrinarySheets.sheets +
      SemiologyRheumatologySheets.sheets +
      SemiologyDermatologySheets.sheets +
      SemiologyGeneralSheets.sheets
  }

  val quizQuestions: List<QuizQuestion> by lazy {
    quizCardioQuestions +
      quizRespQuestions +
      quizDigestiveQuestions +
      quizNeuroQuestions +
      quizUrinaryQuestions +
      quizRheumatologyQuestions +
      quizDermatologyQuestions +
      quizGeneralQuestions
  }
}
