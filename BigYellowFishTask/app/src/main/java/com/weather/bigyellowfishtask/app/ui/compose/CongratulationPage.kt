package com.weather.bigyellowfishtask.app.ui.compose
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.weather.bigyellowfishtask.app.ui.theme.BgColor
import com.weather.bigyellowfishtask.app.ui.theme.ButtonColor

/**
 * Survey/ Game Completion Page
 */
@Composable
fun Congratulations(buttonText:String,onClick: () -> Unit,show:Boolean) {
    if(show) {
        Surface(
            color = BgColor,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Congratulations", fontWeight = FontWeight.ExtraBold, color = Color.Black,fontSize = 28.sp)
                Spacer(Modifier.height(40.dp))
                Button(
                    onClick = onClick,
                    colors = ButtonDefaults.buttonColors(backgroundColor = ButtonColor),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    shape = RoundedCornerShape(12.dp),
                ) {
                    Text(
                        text = buttonText, fontSize = 22.sp, color = BgColor, modifier = Modifier
                            .padding(5.dp)
                            .background(Color.Transparent)
                    )
                }
            }
        }
    }
}