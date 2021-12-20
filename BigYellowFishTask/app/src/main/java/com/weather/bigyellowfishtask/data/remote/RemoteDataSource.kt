package com.weather.bigyellowfishtask.data.remote

import com.weather.bigyellowfishtask.data.entities.body.LoginBodyModel
import com.weather.bigyellowfishtask.data.entities.body.UpdateContentQuestionResultBodyModel
import com.weather.bigyellowfishtask.data.entities.body.UpdateGameBodyModel
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val webservice: WebService
): BaseDataSource() {
    // Example code
    suspend fun validateCredential(body: LoginBodyModel) = getResult { webservice.authenticate(body) }

    suspend fun getGame(token:String) = getResult { webservice.getGame(token) }

    suspend fun updateScore(body:UpdateGameBodyModel,token:String) = getResult { webservice.updateGameResult(body,token) }

    suspend fun getContentQuestion(token:String) = getResult { webservice.getContentQuestion(token) }

    suspend fun submitSurvey(body:UpdateContentQuestionResultBodyModel,token:String) = getResult { webservice.updateContentQuestionResult(body,token) }

}