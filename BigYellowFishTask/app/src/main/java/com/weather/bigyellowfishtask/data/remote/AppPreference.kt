package com.weather.bigyellowfishtask.data.remote

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AppPreference @Inject constructor() {
    @Inject
    lateinit var sharedPreferences: SharedPreferences

    fun insertSessionData(key:String, value:Any?) {
        val editor = sharedPreferences.edit()
        when (value) {
            is String -> editor.putString(key,value)
            is Int -> editor.putInt(key,value)
            is Boolean -> editor.putBoolean(key,value)
            else -> editor.putString(key,"")
        }
        editor.apply()
    }

    fun clearAll() = sharedPreferences.edit().clear().apply()


    fun getSessionStringVal(key:String):String = sharedPreferences.getString(key,"")!!

}