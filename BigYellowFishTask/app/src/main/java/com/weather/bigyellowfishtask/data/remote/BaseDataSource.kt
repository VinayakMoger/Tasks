package com.weather.bigyellowfishtask.data.remote
import retrofit2.Response

abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Resource.success(body)
            }
            return error("Something went wrong")
        } catch (e: Exception) {
            return error("")
        }
    }

    private fun <T> error(message: String): Resource<T> {
        return Resource.error(message =message,data = null)
    }


}