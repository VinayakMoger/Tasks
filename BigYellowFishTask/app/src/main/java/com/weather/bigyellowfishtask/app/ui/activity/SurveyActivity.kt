package com.weather.bigyellowfishtask.app.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import androidx.compose.runtime.collectAsState
import com.weather.bigyellowfishtask.app.ui.components.CircularIndeterminateProgressBar
import com.weather.bigyellowfishtask.app.ui.compose.Congratulations
import com.weather.bigyellowfishtask.app.ui.compose.SurveyPage
import com.weather.bigyellowfishtask.app.viewmodel.SurveyViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Survey Page
 */
@AndroidEntryPoint
class SurveyActivity : ComponentActivity() {
    private val viewModel: SurveyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold {
                SurveyPage(viewModel)
                CircularIndeterminateProgressBar(isDisplayed = viewModel.isLoading.collectAsState().value)
                Congratulations(buttonText = "Task Completed", onClick = { /*TODO*/ }, show =viewModel.isSurveyCompleted.value )
            }
        }
    }
}