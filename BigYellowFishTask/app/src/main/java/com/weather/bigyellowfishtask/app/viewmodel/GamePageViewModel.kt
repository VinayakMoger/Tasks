package com.weather.bigyellowfishtask.app.viewmodel

import android.graphics.Color
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.weather.bigyellowfishtask.app.BaseApplication
import com.weather.bigyellowfishtask.data.entities.body.UpdateGameBodyModel
import com.weather.bigyellowfishtask.data.entities.response.get_game_response_model.GetGameResponseModel
import com.weather.bigyellowfishtask.data.remote.AppPreference
import com.weather.bigyellowfishtask.data.remote.Status
import com.weather.bigyellowfishtask.data.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamePageViewModel @Inject constructor(private val baseApplication: BaseApplication, private val repository: NetworkRepository, private val preference: AppPreference) : AndroidViewModel(baseApplication){
    var index = MutableStateFlow(0)
    var isSuccess = MutableStateFlow(false)
    var gameFinished = MutableStateFlow(false)
    var isLoading = MutableStateFlow(false)
    var isTimerRunning = mutableStateOf(false)
    var score = MutableStateFlow(0)
    lateinit var gameResponseModel: GetGameResponseModel

    fun parseColorFromString(color:String): Int {
        return Color.parseColor(color)
    }

    init {
        getGame()
    }
    private fun getGame() {
        viewModelScope.launch{
            isSuccess.value = false
            repository.getGame().collect {
                isLoading.value = it.status == Status.LOADING
                if (it.status == Status.ERROR)  Toast.makeText(baseApplication,it.message!!, Toast.LENGTH_LONG).show()
                else if (it.status == Status.SUCCESS) {
                    gameResponseModel = it.data!!
                    isTimerRunning.value = true
                    isSuccess.value = true

                }
            }
        }
    }
    private fun storeScore(score:Int) {
        viewModelScope.launch {
            repository.updateGameModel(UpdateGameBodyModel(score,""))
        }
    }

    fun updateScore(answerID:Int) {
        val answer = gameResponseModel.play_area[0].questions[index.value]
        if(answerID==answer.answerId) {
            score.value+=10
        }
        if(index.value!= gameResponseModel.play_area[0].questions.size-1){
            index.value+=1
        }  else {
            gameFinished.value = true
        }
    }
}
