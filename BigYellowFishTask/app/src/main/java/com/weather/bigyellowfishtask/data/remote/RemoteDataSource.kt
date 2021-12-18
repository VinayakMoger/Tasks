package com.weather.bigyellowfishtask.data.remote

import com.weather.bigyellowfishtask.data.entities.body.LoginBodyModel
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val webservice: WebService
): BaseDataSource() {
    // Example code
    suspend fun validateCredentail(body: LoginBodyModel) = getResult { webservice.authenticate(body) }

}