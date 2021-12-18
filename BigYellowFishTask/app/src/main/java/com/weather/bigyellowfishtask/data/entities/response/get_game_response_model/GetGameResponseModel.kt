package com.weather.bigyellowfishtask.data.entities.response.get_game_response_model

data class GetGameResponseModel(
    val description: String,
    val description_color: String,
    val description_start_bg_color: String,
    val description_start_text_color: String,
    val info_1: Info1,
    val play_area: List<PlayArea>,
    val results_info: ResultsInfo
)
