package com.neosoft.neostore.presentation.ui.registerScreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Observer

import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.neosoft.neostore.MyApplication
import com.neosoft.neostore.R
import com.neosoft.neostore.presentation.ui.MainActivity
import com.neosoft.neostore.presentation.ui.ui.theme.NeoStoreTheme
import com.neosoft.neostore.presentation.utils.ApiState
import com.neosoft.neostore.presentation.utils.Gender
import com.neosoft.neostore.presentation.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NeoStoreTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                       TopBarRegistration()
                       MyObserver()

                }
            }
        }
    }
}


@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    NeoStoreTheme {
        TopBarRegistration()
    }
}

@Composable
fun MyObserver(viewModal: RegisterViewModal= hiltViewModel()){
    val context = LocalContext.current

    when(val result = viewModal.response.value){
        is ApiState.SUCCESS ->{
            Log.d("Registration", result.data.toString())
          //  Toast.makeText(context,"${result.message}",Toast.LENGTH_LONG).show()
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

@ExperimentalMaterial3Api
@Composable
fun TopBarRegistration(){
    val systemUiController = rememberSystemUiController()
     systemUiController.setStatusBarColor(color = colorResource(id = R.color.dark_red))

    Scaffold(
         topBar = {
              CenterAlignedTopAppBar(
                  title = {
                      Text(text = stringResource(id = R.string.title_register), color = Color.White,
                          textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth(),
                      fontWeight = FontWeight.Bold, fontSize = 26.sp)
                  },
                  navigationIcon = {
                      IconButton(onClick = { }) {
                          Icon( Icons.Filled.ArrowBack, contentDescription ="" )
                      }
                  },
                 colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                     containerColor =  colorResource(id = R.color.medium_dark_red)
                 )

              ) 
         }
    ) {
        RegistrationScreen()
    }
    
}

@Composable
fun  RegistrationScreen(viewModal: RegisterViewModal = hiltViewModel()) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        val firstName = remember { mutableStateOf("") }
        val lastName = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }
        val confirmPassword = remember { mutableStateOf("") }
        val email = remember { mutableStateOf("") }
        val phone = remember { mutableStateOf("") }
        var gender = remember{ mutableStateOf("") }
        var term = remember{ mutableStateOf(false) }

        Text(
            text = stringResource(id = R.string.neostore),
            style = TextStyle(fontSize = 50.sp),
            color = colorResource(id = R.color.white),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center

        )


        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            label = {
                Text(
                    text = stringResource(R.string.f_name),
                    color = colorResource(id = R.color.white)
                )
            },
            value = firstName.value,
            onValueChange = { firstName.value = it },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Person, contentDescription = null,
                    tint = colorResource(id = R.color.white)
                )
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.f_name_placeholder),
                    color = colorResource(id = R.color.white), fontSize = 15.sp
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorResource(id = R.color.white),
                unfocusedBorderColor = colorResource(id = R.color.white),
                backgroundColor = colorResource(id = R.color.red)
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            label = {
                Text(
                    text = stringResource(R.string.l_name),
                    color = colorResource(id = R.color.white)
                )
            },
            value = lastName.value,
            onValueChange = { lastName.value = it },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Person, contentDescription = null,
                    tint = colorResource(id = R.color.white)
                )
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.l_name_placeholder),
                    color = colorResource(id = R.color.white), fontSize = 15.sp
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorResource(id = R.color.white),
                unfocusedBorderColor = colorResource(id = R.color.white),
                backgroundColor = colorResource(id = R.color.red)
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            label = {
                Text(
                    text = stringResource(R.string.email),
                    color = colorResource(id = R.color.white)
                )
            },
            value = email.value,
            onValueChange = { email.value = it },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Email, contentDescription = null,
                    tint = colorResource(id = R.color.white)
                )
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.email_placeholder),
                    color = colorResource(id = R.color.white), fontSize = 15.sp
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorResource(id = R.color.white),
                unfocusedBorderColor = colorResource(id = R.color.white),
                backgroundColor = colorResource(id = R.color.red)
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            label = {
                Text(
                    text = stringResource(R.string.password),
                    color = colorResource(id = R.color.white)
                )
            },
            value = password.value,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { password.value = it },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Password, contentDescription = null,
                    tint = colorResource(id = R.color.white)
                )
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.pass_placeholder),
                    color = colorResource(id = R.color.white), fontSize = 15.sp
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorResource(id = R.color.white),
                unfocusedBorderColor = colorResource(id = R.color.white),
                backgroundColor = colorResource(id = R.color.red)
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            label = {
                Text(
                    text = stringResource(R.string.confirm_password),
                    color = colorResource(id = R.color.white)
                )
            },
            value = confirmPassword.value,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { confirmPassword.value = it },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Password, contentDescription = null,
                    tint = colorResource(id = R.color.white)
                )
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.confirm_pass_placeholder),
                    color = colorResource(id = R.color.white), fontSize = 15.sp
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorResource(id = R.color.white),
                unfocusedBorderColor = colorResource(id = R.color.white),
                backgroundColor = colorResource(id = R.color.red)
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

       gender = selectGender()

        OutlinedTextField(
            label = {
                Text(
                    text = stringResource(R.string.phone),
                    color = colorResource(id = R.color.white)
                )
            },
            value = phone.value,
            onValueChange = { phone.value = it },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.PhoneAndroid, contentDescription = null,
                    tint = colorResource(id = R.color.white)
                )
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.phone_placeholder),
                    color = colorResource(id = R.color.white), fontSize = 15.sp
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorResource(id = R.color.white),
                unfocusedBorderColor = colorResource(id = R.color.white),
                backgroundColor = colorResource(id = R.color.red)
            ),

        )
        Spacer(modifier = Modifier.height(10.dp))
        term = termCondition()


        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            OutlinedButton(
                border = BorderStroke(width = 2.dp, color = colorResource(id = R.color.red)),
                onClick = {

                     viewModal.validateUserDetail(firstName.value,lastName.value,email.value,password.value,confirmPassword.value,gender.value,phone.value)

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = colorResource(id = R.color.red),
                    disabledContentColor = Color.Gray,
                    backgroundColor = colorResource(id = R.color.white)
                ),

                enabled = term.value
                ) {
                Text(
                    text = stringResource(R.string.registration_button), fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

    }


}

@Composable
fun selectGender() : MutableState<String>{
    val selectedGender = remember { mutableStateOf("") }
        Row (
            verticalAlignment = Alignment.CenterVertically){
            Text(stringResource(R.string.gender),fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 5.dp))
            RadioButton(selected = selectedGender.value == Gender.male,
                colors = RadioButtonDefaults.colors(
                    selectedColor = Color.White,
                    unselectedColor = Color.Gray,
                    disabledColor = Color.LightGray
                ),onClick = {
                selectedGender.value = Gender.male
            })

            Text(Gender.male,fontWeight = FontWeight.Bold)
            RadioButton(selected = selectedGender.value == Gender.female,
                colors = RadioButtonDefaults.colors(
                    selectedColor = Color.White,
                    unselectedColor = Color.Gray,
                    disabledColor = Color.LightGray
                ),onClick = {
                selectedGender.value = Gender.female
            })

            Text(Gender.female, fontWeight = FontWeight.Bold)
        }

    return selectedGender

}

@Composable
fun termCondition() : MutableState<Boolean>{
    val isChecked = remember { mutableStateOf(false) }
    Row( verticalAlignment = Alignment.CenterVertically) {

        Checkbox(checked = isChecked.value, onCheckedChange = { isChecked.value = it },
        colors = CheckboxDefaults.colors(
            checkedColor = Color.Gray,
            uncheckedColor = Color.Gray,
            disabledColor = Color.LightGray
        ),
        enabled = true)
        Text(text = stringResource(R.string.termcond), fontWeight = FontWeight.Bold)

    }

    return isChecked
}