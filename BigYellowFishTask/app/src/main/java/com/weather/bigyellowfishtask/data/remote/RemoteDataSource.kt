package com.weather.bigyellowfishtask.data.remote

import com.weather.bigyellowfishtask.data.entities.body.LoginBodyModel
import com.weather.bigyellowfishtask.data.entities.body.UpdateGameBodyModel
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val webservice: WebService
): BaseDataSource() {
    // Example code
    suspend fun validateCredential(body: LoginBodyModel) = getResult { webservice.authenticate(body) }

    suspend fun getGame() = getResult { webservice.getGame() }

    suspend fun updateScore(body:UpdateGameBodyModel) = getResult { webservice.updateGameResult(body) }

    suspend fun getContentQuestion() = getResult { webservice.getContentQuestion() }

}