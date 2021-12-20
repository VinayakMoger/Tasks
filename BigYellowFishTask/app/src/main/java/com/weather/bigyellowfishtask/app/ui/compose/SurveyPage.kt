package com.weather.bigyellowfishtask.app.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.weather.bigyellowfishtask.app.ui.theme.BgColor
import com.weather.bigyellowfishtask.app.ui.theme.ButtonColor
import com.weather.bigyellowfishtask.app.viewmodel.SurveyViewModel
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Survey page design
 */
@Composable
fun SurveyPage(viewModel: SurveyViewModel) {
    if (viewModel.isSuccess.collectAsState().value) {
        val contentQuestion = viewModel.contentQuestion.content
        val questions = viewModel.contentQuestion.content[0].survey.surveyQuestions

        if (contentQuestion.isNotEmpty() && questions.isNotEmpty()) {
            val page = contentQuestion[0].page
            Surface(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                LazyColumn(
                    modifier = Modifier
                        .padding(20.dp),
                ) {
                    if (page.isNotEmpty()) {
                        item {
                            Column {
                                Text(
                                    text = page[0].title,
                                    color = Color.Black,
                                    fontSize = page[0].title_font.toInt().sp
                                )
                                Spacer(Modifier.height(20.dp))
                                Text(
                                    text = page[0].sub_title,
                                    color = Color(viewModel.parseColorFromString(page[0].sub_title_colour)),
                                    fontSize = page[0].sub_title_font.toInt().sp
                                )
                                Spacer(Modifier.height(20.dp))
                                Image(
                                    painter = rememberImagePainter(page[0].image_url),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(500.dp)
                                )
                            }
                        }
                    }

                    if (questions.isNotEmpty()) {
                        val isChecked = viewModel.isChecked
                        item {
                            Column() {
                                var isCheckedIndex = 0
                                for (question in questions) {
                                    Text(text = question.questions)
                                    Spacer(modifier = Modifier.height(10.dp))
                                    if (question.isMultiChoice) {
                                        question.answers.forEachIndexed { answerIndex,answer ->
                                           Row(Modifier.fillMaxSize()) {
                                               Checkbox(checked = isChecked[answerIndex].collectAsState().value, onCheckedChange ={
                                                   isChecked[answerIndex].value = it
                                                   questions.forEachIndexed{ quesIndex,data->
                                                       if(data==question) {
                                                           val answersData = questions[quesIndex].answers
                                                           answersData.forEachIndexed { ansIndex, answerVal->
                                                               if(answerVal==answer) {
                                                                   questions[quesIndex].answers[ansIndex].isSelected = it
                                                               }
                                                           }
                                                       }
                                                   }
                                               } )
                                               Text(
                                                   text = answer.optionText,
                                                   modifier = Modifier.padding(start = 16.dp)
                                               )
                                           }
                                            isCheckedIndex +=1
                                        }
                                    } else {
                                        val (selectedOption, onOptionSelected) = remember {
                                            mutableStateOf(
                                                question.answers[0].optionText
                                            )
                                        }
                                        question.answers.forEach { answer ->
                                            Row(Modifier
                                                .fillMaxWidth()
                                                .selectable(
                                                    selected = (answer.optionText == selectedOption),
                                                    onClick = {
                                                        onOptionSelected(answer.optionText)
                                                    }
                                                )
                                                .padding(horizontal = 16.dp)) {

                                                RadioButton(
                                                    // inside this method we are
                                                    // adding selected with a option.
                                                    selected = (answer.optionText == selectedOption),
                                                    modifier = Modifier.padding(all = Dp(value = 8F)),
                                                    onClick = {
                                                        question.isCorrectAnswer=answer.mark==1
                                                        onOptionSelected(answer.optionText)

                                                    }
                                                )
                                                Text(
                                                    text = answer.optionText,
                                                    modifier = Modifier.padding(start = 16.dp)
                                                )

                                            }
                                        }
                                    }
                                }
                                Spacer(modifier = Modifier.height(20.dp))

                                Button(
                                    onClick = {
                                              viewModel.submitSurvey(questions)
                                    },
                                    colors = ButtonDefaults.buttonColors(backgroundColor = ButtonColor),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(5.dp),
                                    shape = RoundedCornerShape(12.dp),
                                ) {
                                    Text(text = "Submit Survey", fontSize = 22.sp, color= BgColor,modifier = Modifier
                                        .padding(5.dp)
                                        .background(Color.Transparent))
                                }
                            }

                        }
                    }




                }
            }

        }

    }

}