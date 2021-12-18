package com.weather.bigyellowfishtask.app.viewmodel

import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.weather.bigyellowfishtask.app.BaseApplication
import com.weather.bigyellowfishtask.data.entities.body.LoginBodyModel
import com.weather.bigyellowfishtask.data.remote.Status
import com.weather.bigyellowfishtask.data.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val baseApplication: BaseApplication,private val repository: NetworkRepository) : AndroidViewModel(baseApplication){
    var isLoading = MutableStateFlow(false)
    var errorMessage = MutableStateFlow("")
    fun validateLogin(email:String,password:String ) {
        viewModelScope.launch {
            repository.getLoginData(LoginBodyModel(email, password)).collect {
                isLoading.value = it.status == Status.LOADING
                if (it.status == Status.ERROR) errorMessage.value = it.message!!
                else if (it.status == Status.SUCCESS) {
                    Toast.makeText(baseApplication,"Success",Toast.LENGTH_LONG).show()
                }
            }
        }

    }


}