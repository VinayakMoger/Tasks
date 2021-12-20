package com.weather.bigyellowfishtask.di

import com.google.gson.GsonBuilder
import com.weather.bigyellowfishtask.BuildConfig.BASEURL
import com.weather.bigyellowfishtask.data.remote.RemoteDataSource
import com.weather.bigyellowfishtask.data.remote.WebService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    fun provideWebService(): WebService {
        val gSONBuilder = GsonBuilder()
            .setLenient()
            .create()

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .baseUrl(BASEURL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gSONBuilder))
            .build().create(WebService::class.java)
    }

    @Singleton
    @Provides
    fun provideCharacterRemoteDataSource(webservice: WebService) = RemoteDataSource(webservice)
}