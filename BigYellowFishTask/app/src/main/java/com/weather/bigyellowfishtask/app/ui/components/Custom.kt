package com.weather.bigyellowfishtask.app.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.weather.bigyellowfishtask.app.ui.theme.Transp

@Composable
fun CircularIndeterminateProgressBar(isDisplayed: Boolean) {
    if (isDisplayed) {
        Surface(modifier = Modifier.fillMaxSize(), color = Transp) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.offset(y = (-30).dp)
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colors.primary
                )
                Spacer(modifier = Modifier.size(25.dp))
                Text(text ="Please wait....", color = Color.White,fontSize = 25.sp)
            }
        }
    }
}


@Composable
fun getTextFieldOutlineColor() : TextFieldColors {
    return  TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = Color.Black,
        textColor = Color.Black
    )
}

@Composable
fun GetTextFieldHintColor(label:String) {
    Text(label,color = Color.Gray)
}