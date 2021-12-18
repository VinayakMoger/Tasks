package com.weather.bigyellowfishtask.data.entities.response.get_content_question_response_model

data class Survey(
    val surveyId: Int,
    val surveyName: String,
    val surveyQuestions: List<SurveyQuestion>
)