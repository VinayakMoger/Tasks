package com.weather.bigyellowfishtask.app.viewmodel

import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.weather.bigyellowfishtask.app.BaseApplication
import com.weather.bigyellowfishtask.app.ui.theme.APIToken
import com.weather.bigyellowfishtask.app.ui.theme.Username
import com.weather.bigyellowfishtask.data.entities.body.LoginBodyModel
import com.weather.bigyellowfishtask.data.remote.AppPreference
import com.weather.bigyellowfishtask.data.remote.Status
import com.weather.bigyellowfishtask.data.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val baseApplication: BaseApplication,private val repository: NetworkRepository,private val preference: AppPreference) : AndroidViewModel(baseApplication){
    var isLoading = MutableStateFlow(false)
    var isSuccess = MutableStateFlow(false)
    fun validateLogin(email:String,password:String ) {
        viewModelScope.launch{
            isSuccess.value = false
            repository.getLoginData(LoginBodyModel(email, password)).collect {
                isLoading.value = it.status == Status.LOADING
                if (it.status == Status.ERROR)  Toast.makeText(baseApplication,it.message!!,Toast.LENGTH_LONG).show()
                else if (it.status == Status.SUCCESS) {
                    preference.insertSessionData(APIToken,it.data!!.token)
                    preference.insertSessionData(Username,it.data.username)
                    delay(1000)
                    isSuccess.value = true
                }
            }
        }

    }
    fun isLoggedIn() = preference.getSessionStringVal(APIToken).isNotEmpty()


}