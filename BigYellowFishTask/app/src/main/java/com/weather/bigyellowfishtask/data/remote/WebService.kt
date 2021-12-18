package com.weather.bigyellowfishtask.data.remote

import com.weather.bigyellowfishtask.data.entities.body.LoginBodyModel
import com.weather.bigyellowfishtask.data.entities.body.UpdateContentQuestionResultBodyModel
import com.weather.bigyellowfishtask.data.entities.body.UpdateGameBodyModel
import com.weather.bigyellowfishtask.data.entities.response.LoginResponseModel
import com.weather.bigyellowfishtask.data.entities.response.get_content_question_response_model.GetContentQuestionResponseModel
import com.weather.bigyellowfishtask.data.entities.response.get_game_response_model.GetGameResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface WebService {

    @POST("api/User/authenticate")
    suspend fun authenticate(@Body body: LoginBodyModel): Response<LoginResponseModel>

    @GET("api/Game/GetGame")
    suspend fun getGame() : Response<GetGameResponseModel>

    @POST("api/Game/UpdateGameResult")
    suspend fun updateGameResult(@Body bodyModel: UpdateGameBodyModel) : Response<Void>

    @GET("api/ContentQuestion/GetContentQuestion")
    suspend fun getContentQuestion() : Response<GetContentQuestionResponseModel>

    @POST("api/ContentQuestion/UpdateContentQuestionResult")
    suspend fun updateContentQuestionResult(@Body bodyModel: UpdateContentQuestionResultBodyModel) : Response<Void>
}