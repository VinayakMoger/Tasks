/*
Created by Vinayak
 */

package com.weather.bigyellowfishtask.data.remote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

fun <T> performGetOperation(networkCall: suspend () -> Resource<T>): Flow<Resource<T>> =
    flow{
        emit(Resource.loading())
        val responseStatus = networkCall.invoke()
        if (responseStatus.status == Status.SUCCESS) {
            emit(Resource.success(data = responseStatus.data!!))
        } else if (responseStatus.status == Status.ERROR) {
            emit(Resource.error(message = responseStatus.message!!, data = null))
        }
    }.flowOn(Dispatchers.IO)
