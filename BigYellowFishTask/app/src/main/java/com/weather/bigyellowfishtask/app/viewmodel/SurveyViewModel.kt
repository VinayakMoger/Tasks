package com.weather.bigyellowfishtask.app.viewmodel

import android.graphics.Color
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.weather.bigyellowfishtask.app.BaseApplication
import com.weather.bigyellowfishtask.data.entities.response.get_content_question_response_model.GetContentQuestionResponseModel
import com.weather.bigyellowfishtask.data.remote.AppPreference
import com.weather.bigyellowfishtask.data.remote.Status
import com.weather.bigyellowfishtask.data.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SurveyViewModel @Inject constructor(private val baseApplication: BaseApplication, private val repository: NetworkRepository, private val preference: AppPreference) : AndroidViewModel(baseApplication){
    var isSuccess = MutableStateFlow(false)
    var isLoading = MutableStateFlow(false)
    lateinit var contentQuestion: GetContentQuestionResponseModel

    init {
        getSurvey()
    }
    fun parseColorFromString(color:String): Int {
        return Color.parseColor(color)
    }

    private fun getSurvey() {
        viewModelScope.launch {
            repository.getContentQuestion().collect {
                isLoading.value = it.status == Status.LOADING
                if (it.status == Status.ERROR)  Toast.makeText(baseApplication,it.message!!, Toast.LENGTH_LONG).show()
                if(it.status==Status.SUCCESS) {
                    contentQuestion = it.data!!
                    isSuccess.value = true
                }
            }
        }
    }
}
