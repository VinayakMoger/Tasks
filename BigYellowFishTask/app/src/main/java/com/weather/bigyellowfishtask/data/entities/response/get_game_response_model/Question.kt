package com.weather.bigyellowfishtask.data.entities.response.get_game_response_model

data class Question(
    val answerId: Int,
    val question_1: String,
    val question_1_color: String,
    val question_2: String,
    val question_2_color: String
)