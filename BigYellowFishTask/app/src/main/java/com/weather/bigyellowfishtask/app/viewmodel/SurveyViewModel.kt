package com.weather.bigyellowfishtask.app.viewmodel

import android.graphics.Color
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.weather.bigyellowfishtask.app.BaseApplication
import com.weather.bigyellowfishtask.app.ui.theme.APIToken
import com.weather.bigyellowfishtask.data.entities.body.UpdateContentQuestionResultBodyModel
import com.weather.bigyellowfishtask.data.entities.response.get_content_question_response_model.GetContentQuestionResponseModel
import com.weather.bigyellowfishtask.data.entities.response.get_content_question_response_model.SurveyQuestion
import com.weather.bigyellowfishtask.data.remote.AppPreference
import com.weather.bigyellowfishtask.data.remote.Status
import com.weather.bigyellowfishtask.data.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Business Logic for Survey Model
 */
@HiltViewModel
class SurveyViewModel @Inject constructor(private val baseApplication: BaseApplication, private val repository: NetworkRepository, private val preference: AppPreference) : AndroidViewModel(baseApplication){
    var isSuccess = MutableStateFlow(false)
    var isLoading = MutableStateFlow(false)
    var isSurveyCompleted = MutableStateFlow(false)
    val isChecked = mutableListOf<MutableStateFlow<Boolean>>()
    lateinit var contentQuestion: GetContentQuestionResponseModel

    /**
     * Call Survey API
     */
    init {
        getSurvey()
    }

    /**
     * Parse color string to Int
     */
    fun parseColorFromString(color:String): Int {
        return Color.parseColor(color)
    }

    /**
     * Call survey and initialize state of all check box with false
     */
    private fun getSurvey() {
        viewModelScope.launch {
            repository.getContentQuestion("Bearer ${preference.getSessionStringVal(APIToken)}").collect {
                isLoading.value = it.status == Status.LOADING
                if (it.status == Status.ERROR)  Toast.makeText(baseApplication,it.message!!, Toast.LENGTH_LONG).show()
                if(it.status==Status.SUCCESS) {
                    contentQuestion = it.data!!
                    isSuccess.value = true
                    val questions = contentQuestion.content[0].survey.surveyQuestions
                    questions.forEach { surveyQuestion ->
                        if(surveyQuestion.isMultiChoice) {
                            surveyQuestion.answers.forEach { _ ->
                                isChecked.add(MutableStateFlow(false))

                            }
                        }

                    }
                }
            }
        }
    }


    /**
     * Find out scores and submit survey
     */
    fun submitSurvey(questions:List<SurveyQuestion>){
        var score = 0
        for(question in questions) {
            if(question.isMultiChoice) {
                for(answer in question.answers) {
                    if(answer.isSelected) {
                        if(answer.mark==1){
                            score+=10
                        }

                    }
                }
            } else {
                if(question.isCorrectAnswer) {
                    score+=10
                }
            }
        }
        /**
         * Call API to Submit Survey
         */
        viewModelScope.launch {
            repository.submitSurvey(UpdateContentQuestionResultBodyModel(score,contentQuestion.content[0].survey.surveyId),"Bearer ${preference.getSessionStringVal(APIToken)}").collect {
                isLoading.value = it.status == Status.LOADING
                if (it.status == Status.ERROR)  Toast.makeText(baseApplication,it.message!!, Toast.LENGTH_LONG).show()
                if(it.status==Status.SUCCESS) {
                    isSurveyCompleted.value = true
                }
            }
        }

    }
}
