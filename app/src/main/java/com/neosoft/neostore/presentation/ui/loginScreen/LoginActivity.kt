package com.neosoft.neostore.presentation.ui.loginScreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DimenRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.neosoft.neostore.R
import com.neosoft.neostore.presentation.ui.MainActivity
import com.neosoft.neostore.presentation.ui.registerScreen.RegistrationActivity
import com.neosoft.neostore.presentation.ui.ui.theme.NeoStoreTheme
import com.neosoft.neostore.presentation.ui.ui.theme.Purple700
import com.neosoft.neostore.presentation.utils.ApiState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NeoStoreTheme {
                val systemUiController = rememberSystemUiController()
                SideEffect {
                    systemUiController.isStatusBarVisible = false
                }
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    FloatingActionButton()
                    LoginPage()
                    LoginObserver()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    NeoStoreTheme {
      LoginPage()
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun LoginObserver(loginViewModal: LoginViewModal = hiltViewModel()){
    val context = LocalContext.current

      when(val result = loginViewModal.response.value){
          is ApiState.SUCCESS ->{
              Log.d("Login", result.data)
               Toast.makeText(context,"Logged in successfully",Toast.LENGTH_LONG).show()
              val intent = Intent(context, MainActivity::class.java)
              context.startActivity(intent)
          }
          is ApiState.FAILURE ->{
              Text(text = "${result.message}")
          }
          is ApiState.LOADING ->{
              CircularProgressIndicator(
                  color = colorResource(id = R.color.white),
                  strokeWidth = 2.dp
              )
          }
          is ApiState.EMPTY ->{

          }
      }
}



@Composable
fun LoginPage(loginViewModal: LoginViewModal = hiltViewModel()) {
    Box(modifier = Modifier.fillMaxSize()) {
        ClickableText(
            text = AnnotatedString("DON'T HAVE AN ACCOUNT?"),
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(25.dp),
            onClick = { },
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily.Default,
                color = colorResource(id = R.color.white),
                fontWeight = FontWeight.Bold
            )
        )
    }
    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val username = remember { mutableStateOf(TextFieldValue()) }
        val password = remember { mutableStateOf(TextFieldValue()) }

        Text(text = stringResource(id = R.string.neostore),
            style = TextStyle(fontSize = 50.sp),
            color = colorResource(id = R.color.white),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 20.dp),
            textAlign = TextAlign.Center
        )


        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            label = { Text(text = stringResource(R.string.uname),
            color = colorResource(id = R.color.white)) },
            value = username.value,
            onValueChange = { username.value = it },
            leadingIcon = {Icon(imageVector = Icons.Filled.Person,contentDescription = null,
                             tint = colorResource(id = R.color.white))},
           placeholder = { Text(text = stringResource(R.string.name_placeholder),
               color = colorResource(id = R.color.white), fontSize = 15.sp) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorResource(id = R.color.white),
                unfocusedBorderColor = colorResource(id = R.color.white),
                backgroundColor = colorResource(id = R.color.red))
        )

        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            label = { Text(text = stringResource(R.string.password),
            color = colorResource(id = R.color.white)) },
            value = password.value,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { password.value = it },
            leadingIcon = {Icon(imageVector = Icons.Filled.Password,contentDescription = null,
                tint = colorResource(id = R.color.white))},
            placeholder = { Text(text = stringResource(R.string.pass_placeholder),
                color = colorResource(id = R.color.white), fontSize = 15.sp) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorResource(id = R.color.white),
                unfocusedBorderColor = colorResource(id = R.color.white),
                backgroundColor = colorResource(id = R.color.red)))

        Spacer(modifier = Modifier.height(30.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            OutlinedButton(
                border = BorderStroke(width = 2.dp, color = colorResource(id = R.color.red)),
                onClick = {
                          loginViewModal.validateLoginData(username.value.text, password.value.text)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = colorResource(id = R.color.red),
                    disabledContentColor = Yellow,
                    backgroundColor = colorResource(id = R.color.white)
                    ),

            ) {
                Text(text = stringResource(R.string.login_button), fontSize = 20.sp,
                  fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        ClickableText(
            text = AnnotatedString("Forgot password?"),
            onClick = { },
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Bold,
               color = Color.White
            )

        )
    }


}

@Composable
fun FloatingActionButton(){
    val context = LocalContext.current
    Scaffold(topBar = { } ,
        //floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                          val intent = Intent(context,RegistrationActivity::class.java)
                          context.startActivity(intent)
                },
                backgroundColor = colorResource(id = R.color.dark_red),
                contentColor = Color.White,
                shape = RectangleShape

                ) {
                Icon(Icons.Filled.Add,"")
            }
        }
        , content = {
            //....
        })

}



