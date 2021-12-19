package com.weather.bigyellowfishtask.app.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import androidx.compose.runtime.collectAsState
import com.weather.bigyellowfishtask.app.ui.components.CircularIndeterminateProgressBar
import com.weather.bigyellowfishtask.app.ui.compose.GameCard
import com.weather.bigyellowfishtask.app.viewmodel.GamePageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameActivity : ComponentActivity() {
    private val viewModel: GamePageViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold {
                GameCard(viewModel)
                CircularIndeterminateProgressBar(isDisplayed = viewModel.isLoading.collectAsState().value)
            }
        }
    }
}