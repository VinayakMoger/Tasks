package com.weather.bigyellowfishtask.app.ui.compose

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.weather.bigyellowfishtask.app.ui.components.CustomButton
import com.weather.bigyellowfishtask.app.ui.components.Timer
import com.weather.bigyellowfishtask.app.ui.theme.BgColor
import com.weather.bigyellowfishtask.app.viewmodel.GamePageViewModel

/**
 * Game Screen Design
 */
@Composable
fun GameCard(viewModel: GamePageViewModel) {
    if (viewModel.isSuccess.collectAsState().value) {
        val data = viewModel.gameResponseModel
        Surface(
            color = BgColor,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Timer(
                    modifier = Modifier.size(100.dp),
                    totalTime = data.play_area[0].timer * 1000L,
                    handleColor = Color.Cyan,
                    inactiveBarColor = Color.Black,
                    activeBarColor = Color.Green,
                    viewModel = viewModel
                )
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    text = data.play_area[0].questions[viewModel.index.collectAsState().value].question_1,
                    fontSize=30.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(viewModel.parseColorFromString(data.play_area[0].questions[viewModel.index.collectAsState().value].question_1_color))
                )
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    text = data.play_area[0].questions[viewModel.index.collectAsState().value].question_2,
                    fontSize=30.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(viewModel.parseColorFromString(data.play_area[0].questions[viewModel.index.collectAsState().value].question_2_color))
                )

                Spacer(modifier = Modifier.weight(1.0f))
                Text(
                    text = "Score : ${viewModel.score.collectAsState().value} / ${data.play_area[0].questions.size*10}",
                    fontSize=22.sp,
                )
                Spacer(modifier = Modifier.height(20.dp))
                var index = 0
                for (i in 0 until (data.play_area[0].answers.size / 3)) {
                    Row() {
                        for (j in 0..2) {
                            if (index < data.play_area[0].answers.size) {
                                CustomButton(
                                    color = Color(viewModel.parseColorFromString(data.play_area[0].answers[index].color)),
                                    answerID = data.play_area[0].answers[index].answerId,
                                    modifier = Modifier
                                        .height(60.dp)
                                        .weight(1f),
                                    viewModel
                                )
                                if (j != 2) {
                                    Spacer(modifier = Modifier.width(10.dp))
                                }
                                index += 1
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }

            }

        }
    }

}
