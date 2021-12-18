package com.weather.bigyellowfishtask.data.entities.response.get_game_response_model

data class PlayArea(
    val answers: List<Answer>,
    val negative_mark: String,
    val positive_mark: String,
    val progressbar_active_color: String,
    val progressbar_inactive_color: String,
    val questions: List<Question>,
    val timer: Int,
    val timer_color: String
)