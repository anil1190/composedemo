package com.neosoft.neostore.presentation.ui.product.address

import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neosoft.neostore.R
import com.neosoft.neostore.presentation.utils.Gender


@Composable
fun AddressListScreen(){
    val context = LocalContext.current
    val iconSize = 24.dp
    val offsetInPx = LocalDensity.current.run { (iconSize / 2).roundToPx() }
    val addressList = listOf(
        "Pembroke", "Adirondack", "Chesterfield"
    )
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(addressList[0]) }


    Text(
        text = stringResource(R.string.shipping_address),
        color = colorResource(id = R.color.add_color),
        fontSize = 18.sp,
        modifier = Modifier.padding(15.dp)
    )

    LazyColumn(modifier = Modifier
        .fillMaxHeight()
        .fillMaxSize()
        .padding(top = 40.dp))
    {
             items(items = addressList, itemContent = { item ->

                 Row(
                     modifier = Modifier
                         .padding(10.dp)
                         .fillMaxWidth()
                         .selectable(
                             selected = (item == selectedOption),
                             onClick = { onOptionSelected(item) }
                         ),
                     verticalAlignment = Alignment.CenterVertically,
                 ) {

                     RadioButton(
                         selected = (item == selectedOption),
                         colors = RadioButtonDefaults.colors(
                             selectedColor = Color.White,
                             unselectedColor = Color.Gray,
                             disabledColor = Color.LightGray
                         ),onClick = {
                             onOptionSelected(item)
                         })

                     Card(modifier = Modifier
                         .height(100.dp)
                         .fillMaxWidth(),
                         elevation = 3.dp,
                         backgroundColor = colorResource(id = R.color.white)) {

                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)) {

                            Row(modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = item,
                                    color = colorResource(id = R.color.add_color),
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Box(modifier = Modifier.fillMaxWidth()) {

                                        IconButton(onClick = {  },
                                            modifier = Modifier
                                                .offset {
                                                    IntOffset(x = +offsetInPx, y = -offsetInPx)
                                                }
                                                .size(iconSize)
                                                .align(Alignment.TopEnd)
                                        )
                                        {
                                            Icon( Icons.Filled.Cancel, contentDescription ="" , tint = colorResource(
                                                id = R.color.uncheck_star_background),
                                            modifier = Modifier.align(Alignment.TopEnd))
                                        }


                                }

                            }

                          Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "fkvmfjfldidmvkdldldkf cmjdldld ddjflfkd djdkfk",
                                color = colorResource(id = R.color.add_color),
                                fontSize = 15.sp
                            )
                        }
                     }

                 }

             })

        item {
            Spacer(modifier = Modifier.height(30.dp))
            Box(modifier = Modifier.padding(20.dp, 0.dp, 20.dp, 10.dp)) {
                OutlinedButton(
                    border = BorderStroke(width = 2.dp, color = colorResource(id = R.color.red)),
                    onClick = {
                        val intent = Intent(context, AddAddressActivity::class.java)
                        context.startActivity(intent)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = colorResource(id = R.color.white),
                        disabledContentColor = Color.Yellow,
                        backgroundColor = colorResource(id = R.color.red)
                    ),

                    ) {
                    Text(text = stringResource(R.string.place_order), fontSize = 20.sp,
                        fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}