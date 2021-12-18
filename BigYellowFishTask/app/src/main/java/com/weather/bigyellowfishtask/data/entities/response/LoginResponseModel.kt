package com.weather.bigyellowfishtask.data.entities.response

data class LoginResponseModel(
    val firstName: String,
    val id: Int,
    val lastName: String,
    val token: String,
    val username: String
)