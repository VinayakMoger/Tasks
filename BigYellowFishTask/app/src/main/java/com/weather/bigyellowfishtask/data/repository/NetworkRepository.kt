package com.weather.bigyellowfishtask.data.repository

import com.weather.bigyellowfishtask.data.entities.body.LoginBodyModel
import com.weather.bigyellowfishtask.data.entities.body.UpdateContentQuestionResultBodyModel
import com.weather.bigyellowfishtask.data.entities.body.UpdateGameBodyModel
import com.weather.bigyellowfishtask.data.remote.RemoteDataSource
import com.weather.bigyellowfishtask.data.remote.performGetOperation
import javax.inject.Inject

class NetworkRepository@Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {
    fun getLoginData(body: LoginBodyModel) = performGetOperation(
        networkCall = { remoteDataSource.validateCredential(body = body) }
    )

    fun getGame(token:String) = performGetOperation(
        networkCall = { remoteDataSource.getGame(token) }
    )

    fun updateGameModel(body:UpdateGameBodyModel,token:String) = performGetOperation(
        networkCall = { remoteDataSource.updateScore(body,token) }
    )

    fun getContentQuestion(token:String) = performGetOperation(
        networkCall = { remoteDataSource.getContentQuestion(token) }
    )

    fun submitSurvey(body:UpdateContentQuestionResultBodyModel,token:String) = performGetOperation(
        networkCall = { remoteDataSource.submitSurvey(body,token) }
    )

}