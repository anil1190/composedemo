package com.neosoft.neostore.presentation.utils

import android.R
import android.graphics.Color.red
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Money
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun CustomDialog(value: String, setShowDialog: (Boolean) -> Unit, setValue: (String) -> Unit) {

    val txtFieldError = remember { mutableStateOf("") }
    val txtField = remember { mutableStateOf(value) }

    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {

                   /* Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Set value",
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Icon(
                            imageVector = Icons.Filled.Cancel,
                            contentDescription = "",
                            tint = colorResource(android.R.color.darker_gray),
                            modifier = Modifier
                                .width(30.dp)
                                .height(30.dp)
                                .clickable { setShowDialog(false) }
                        )
                    }*/

                    Text(
                        text = "Stylish modern dining table",
                        color = colorResource(id = com.neosoft.neostore.R.color.popup_text),
                        fontSize = 20.sp,
                        modifier = Modifier.padding(all = 14.dp)
                    )
                    Card( modifier = Modifier
                        .padding(10.dp)
                        .height(150.dp),
                        elevation = 5.dp,
                        backgroundColor = colorResource(id = com.neosoft.neostore.R.color.white)){

                        Image(painter = painterResource(id = com.neosoft.neostore.R.drawable.slider_img3), contentDescription = "",
                            modifier = Modifier
                                .height(150.dp)
                                .width(250.dp),
                            alignment = Alignment.TopCenter,
                        contentScale = ContentScale.Crop)
                    }


                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = stringResource(com.neosoft.neostore.R.string.enter_quantity),
                        color = colorResource(id = com.neosoft.neostore.R.color.popup_text),
                        fontSize = 18.sp,
                        modifier = Modifier.padding(all = 10.dp)
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(50.dp, 0.dp, 50.dp, 0.dp)
                            .border(
                                BorderStroke(
                                    width = 2.dp,
                                    color = colorResource(id = if (txtFieldError.value.isEmpty()) R.color.holo_green_light else R.color.holo_red_dark)
                                ),
                                shape = RoundedCornerShape(10)
                            ),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            placeholderColor = colorResource(id = com.neosoft.neostore.R.color.divider_color),
                            textColor = colorResource(id = com.neosoft.neostore.R.color.popup_text),
                            errorLabelColor = colorResource(id = com.neosoft.neostore.R.color.red)
                        ),

                        placeholder = { Text(text = "Enter quantity") },
                        value = txtField.value,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        onValueChange = {
                            txtField.value = it.take(10)
                        })

                    Spacer(modifier = Modifier.height(20.dp))

                    Box(modifier = Modifier.padding(30.dp, 0.dp, 30.dp, 0.dp)) {
                        Button(
                            onClick = {
                                if (txtField.value.isEmpty()) {
                                    txtFieldError.value = "Field can not be empty"
                                    return@Button
                                }
                                setValue(txtField.value)
                                setShowDialog(false)
                            },
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = colorResource(id = com.neosoft.neostore.R.color.red)
                            )
                        ) {
                            Text(text = stringResource(com.neosoft.neostore.R.string.submit),
                            fontWeight = FontWeight.Bold, fontSize = 18.sp,
                            color = Color.White)
                        }
                    }
                }
            }
        }
    }
}
