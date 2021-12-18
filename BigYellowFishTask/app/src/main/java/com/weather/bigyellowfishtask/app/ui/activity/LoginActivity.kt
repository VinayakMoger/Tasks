package com.weather.bigyellowfishtask.app.ui.activity

import BottomCard
import LoginCard
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.lifecycleScope
import com.weather.bigyellowfishtask.app.ui.components.CircularIndeterminateProgressBar
import com.weather.bigyellowfishtask.app.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    private val viewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch(Dispatchers.Main) {

            setContent {
                Scaffold {
                    BottomCard()
                    LoginCard(viewModel = viewModel)
                    CircularIndeterminateProgressBar(isDisplayed = viewModel.isLoading.collectAsState().value)
                }
            }
            viewModel.isSuccess.collect{
                if(it) {
                    startActivity(Intent(this@LoginActivity, GameActivity::class.java))
                    finish()
                }
            }

        }
    }

}


