package com.weather.bigyellowfishtask.data.repository

import com.weather.bigyellowfishtask.data.entities.body.LoginBodyModel
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

    fun getGame() = performGetOperation(
        networkCall = { remoteDataSource.getGame() }
    )

    fun updateGameModel(body:UpdateGameBodyModel) = performGetOperation(
        networkCall = { remoteDataSource.updateScore(body) }
    )

    fun getContentQuestion() = performGetOperation(
        networkCall = { remoteDataSource.getContentQuestion() }
    )

}