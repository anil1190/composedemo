package com.neosoft.neostore.presentation.ui.product.address


import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.foundation.Box
import com.neosoft.neostore.R


@SuppressLint("SuspiciousIndentation")
@Composable
fun AddressScreen(){
    val context = LocalContext.current
    var address by remember { mutableStateOf(TextFieldValue("")) }
    var landmark by remember { mutableStateOf(TextFieldValue("")) }
    var city by remember { mutableStateOf(TextFieldValue("")) }
    var state by remember { mutableStateOf(TextFieldValue("")) }
    var zipcode by remember { mutableStateOf(TextFieldValue("")) }
    var country by remember { mutableStateOf(TextFieldValue("")) }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)) {
        
        Text(text = stringResource(id = R.string.address_text), fontSize = 15.sp,
        color = colorResource(id = R.color.add_color), fontWeight = FontWeight.Bold)
        
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .height(110.dp)
                .fillMaxWidth()
                .background(
                    color = colorResource(id = R.color.white),
                    shape = RoundedCornerShape(2.dp)
                )
        ){
            TextField(
                value = address,
                onValueChange = { address = it },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = colorResource(id = R.color.add_color)
                )
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = stringResource(R.string.landmark), fontSize = 15.sp,
            color = colorResource(id = R.color.add_color), fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .background(
                    color = colorResource(id = R.color.white),
                    shape = RoundedCornerShape(2.dp)
                )
        ){
            TextField(
                value = landmark,
                onValueChange = { landmark = it },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = colorResource(id = R.color.add_color)
                )
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
         Row(modifier = Modifier.fillMaxWidth(1f)) {

             Column(modifier = Modifier.width(180.dp)) {
                 Text(text = stringResource(R.string.city), fontSize = 15.sp,
                     color = colorResource(id = R.color.add_color), fontWeight = FontWeight.Bold)

                 Spacer(modifier = Modifier.height(10.dp))
                 Box(
                     modifier = Modifier
                         .height(50.dp)
                         .fillMaxWidth()
                         .background(
                             color = colorResource(id = R.color.white),
                             shape = RoundedCornerShape(2.dp)
                         )
                 ){
                     TextField(
                         value = city,
                         onValueChange = { city = it },
                         colors = TextFieldDefaults.textFieldColors(
                             textColor = colorResource(id = R.color.add_color)
                         )
                     )
                 }
             }

             Spacer(modifier = Modifier.width(20.dp))

             Column(modifier = Modifier.width(180.dp)) {
                 Text(text = stringResource(R.string.state), fontSize = 15.sp,
                     color = colorResource(id = R.color.add_color), fontWeight = FontWeight.Bold)

                 Spacer(modifier = Modifier.height(10.dp))
                 Box(
                     modifier = Modifier
                         .height(50.dp)
                         .fillMaxWidth()
                         .background(
                             color = colorResource(id = R.color.white),
                             shape = RoundedCornerShape(2.dp)
                         )
                 ){
                     TextField(
                         value = state,
                         onValueChange = { state = it },
                         colors = TextFieldDefaults.textFieldColors(
                             textColor = colorResource(id = R.color.add_color)
                         )
                     )
                 }
             }


         }

        Spacer(modifier = Modifier.height(20.dp))
        Row(modifier = Modifier.fillMaxWidth(1f)) {

            Column(modifier = Modifier.width(180.dp)) {
                Text(text = stringResource(R.string.zip_code), fontSize = 15.sp,
                    color = colorResource(id = R.color.add_color), fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth()
                        .background(
                            color = colorResource(id = R.color.white),
                            shape = RoundedCornerShape(2.dp)
                        )
                ){
                    TextField(
                        value = zipcode,
                        onValueChange = { zipcode = it },
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = colorResource(id = R.color.add_color)
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.width(20.dp))

            Column(modifier = Modifier.width(180.dp)) {
                Text(text = stringResource(R.string.country), fontSize = 15.sp,
                    color = colorResource(id = R.color.add_color), fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth()
                        .background(
                            color = colorResource(id = R.color.white),
                            shape = RoundedCornerShape(2.dp)
                        )
                ){
                    TextField(
                        value = country,
                        onValueChange = { country = it },
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = colorResource(id = R.color.add_color)
                        )
                    )
                }
            }


        }


        Spacer(modifier = Modifier.height(30.dp))
        Box(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 0.dp)) {
            OutlinedButton(
                border = BorderStroke(width = 2.dp, color = colorResource(id = R.color.red)),
                onClick = {
                  val intent = Intent(context, AddressListActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = colorResource(id = R.color.white),
                    disabledContentColor = Color.Yellow,
                    backgroundColor = colorResource(id = R.color.red)
                ),

                ) {
                Text(text = stringResource(R.string.save_address), fontSize = 20.sp,
                    fontWeight = FontWeight.Bold)
            }
        }
    }
   
}