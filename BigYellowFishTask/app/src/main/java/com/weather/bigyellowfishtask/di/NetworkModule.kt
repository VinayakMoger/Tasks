package com.weather.bigyellowfishtask.di

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.google.gson.GsonBuilder
import com.weather.bigyellowfishtask.BuildConfig.BASEURL
import com.weather.bigyellowfishtask.app.ui.theme.APIToken
import com.weather.bigyellowfishtask.data.remote.AppPreference
import com.weather.bigyellowfishtask.data.remote.RemoteDataSource
import com.weather.bigyellowfishtask.data.remote.WebService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import okhttp3.Interceptor

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    fun provideWebService(@ApplicationContext app: Context): WebService {
        val gSONBuilder = GsonBuilder()
            .setLenient()
            .create()

        val masterKey = MasterKey.Builder(app, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        val preference = EncryptedSharedPreferences.create(
            app,
            "secret_shared_prefs",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM)

        val token = preference.getString(APIToken,"")
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(Interceptor { chain ->
            val newRequest: Request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
            chain.proceed(newRequest)
        }).addInterceptor(interceptor).build()

        /*val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()*/
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