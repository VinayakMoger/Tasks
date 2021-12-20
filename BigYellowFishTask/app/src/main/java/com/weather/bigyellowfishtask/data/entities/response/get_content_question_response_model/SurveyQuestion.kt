package com.weather.bigyellowfishtask.data.entities.response.get_content_question_response_model

import androidx.compose.runtime.MutableState
import kotlinx.coroutines.flow.MutableStateFlow

data class SurveyQuestion(
    val answers: List<Answer>,
    val description: String,
    val isMultiChoice: Boolean,
    val question_id: String,
    val questions: String,
    var isCorrectAnswer:Boolean = false
)