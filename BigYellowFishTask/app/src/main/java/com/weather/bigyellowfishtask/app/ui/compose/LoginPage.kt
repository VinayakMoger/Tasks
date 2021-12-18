import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.weather.bigyellowfishtask.R
import com.weather.bigyellowfishtask.app.ui.components.GetTextFieldHintColor
import com.weather.bigyellowfishtask.app.ui.components.getTextFieldOutlineColor
import com.weather.bigyellowfishtask.app.ui.theme.BgColor
import com.weather.bigyellowfishtask.app.ui.theme.ButtonColor
import com.weather.bigyellowfishtask.app.viewmodel.LoginViewModel


/**
 * Created by Vinayak on 13-08-2021.
 */


@Composable
fun LoginCard(viewModel: LoginViewModel) {

    val emailState = remember { mutableStateOf(TextFieldValue()) }
    val passState = remember { mutableStateOf(TextFieldValue()) }
    val emailStateError = remember { mutableStateOf(false) }
    val passStateError= remember { mutableStateOf(false) }

    Surface(
        color = BgColor,
        modifier = Modifier
            .fillMaxSize()
            .offset(y = 150.dp),
        shape = RoundedCornerShape(60.dp).copy(bottomStart = ZeroCornerSize, bottomEnd = ZeroCornerSize)
    ) {
        Column(
            modifier = Modifier
                .padding(start = 30.dp, end = 30.dp)
                .offset(y = (-50).dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
            //Spacer(modifier = Modifier.size(50.dp))
            OutlinedTextField(
                value = emailState.value,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    emailState.value = it
                    emailStateError.value = it.text.isEmpty()
                },
                label = {
                    GetTextFieldHintColor("Email Address")
                },
                leadingIcon = {
                    Icon(Icons.Filled.Email, "")
                },
                colors = getTextFieldOutlineColor(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                isError = emailStateError.value
            )
            Spacer(modifier = Modifier.size(25.dp))
            OutlinedTextField(
                value = passState.value,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    passState.value = it
                    passStateError.value = it.text.isEmpty()
                },
                label = {
                    GetTextFieldHintColor("Password")
                },
                leadingIcon = {
                    Icon(Icons.Filled.Lock, "")
                },
                colors = getTextFieldOutlineColor(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation(),
                isError = passStateError.value
            )
            Spacer(modifier = Modifier.size(30.dp))
            Button(
                onClick = {
                    val emailID = emailState.value.text
                    val password = passState.value.text
                    emailStateError.value = emailID.isEmpty()
                    passStateError.value = password.isEmpty()
                    if(!(emailStateError.value || passStateError.value)) {
                        viewModel.validateLogin(emailID, password)
                    }
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = ButtonColor),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                shape = RoundedCornerShape(12.dp),
            ) {
                Text(text = "Login", fontSize = 22.sp, color= BgColor,modifier = Modifier
                    .padding(5.dp)
                    .background(Color.Transparent))
            }
        }
    }
}




@Composable
fun BottomCard() {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.Black) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(painter = painterResource(id = R.drawable.logo), contentDescription = "",modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .offset(y = (25.dp))
                .padding(10.dp))
        }
    }
}