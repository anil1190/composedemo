package com.neosoft.neostore.presentation.ui.product.addtocart

import android.content.Intent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.core.VerticalAlignmentLine
import com.neosoft.neostore.R
import com.neosoft.neostore.presentation.ui.product.ProductDetailActivity
import com.neosoft.neostore.presentation.ui.product.address.AddAddressActivity
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CartScreen(){
    val context = LocalContext.current
    val cartList = remember {mutableStateListOf(
        "Pembroke", "Adirondack", "Chesterfield"
    )}

    val options = listOf("1", "2", "3")

    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }
    val listState = rememberLazyListState()
    var selectedIndex by remember{mutableStateOf(-1)}

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .fillMaxHeight(), state = listState) {
        items(items = cartList, itemContent = { item ->

            val dismissState = rememberDismissState()

            if (dismissState.isDismissed(DismissDirection.EndToStart)) {

                cartList.remove(item)
            }
            SwipeToDismiss(
                state = dismissState,
                modifier = Modifier,
                directions = setOf(
                    DismissDirection.EndToStart
                ),
                dismissThresholds = { direction ->
                    FractionalThreshold(if (direction == DismissDirection.EndToStart) 0.1f else 0.05f)
                },
                background = {
                    val color by animateColorAsState(
                        when (dismissState.targetValue) {
                            DismissValue.Default -> Color.White
                            else -> Color.White
                        }
                    )
                    val alignment = Alignment.CenterEnd
                    val icon = Icons.Default.Delete

                    val scale by animateFloatAsState(
                        if (dismissState.targetValue == DismissValue.Default) 0.75f else 1f
                    )

                    Box(
                        Modifier
                            .fillMaxSize()
                            .background(color),
                        contentAlignment = alignment
                    ) {
                        Icon(
                            icon,
                            contentDescription = "Delete Icon",
                            modifier = Modifier.scale(scale),
                            tint = colorResource(id = R.color.red)
                        )
                    }
                },
                dismissContent = {

                    val alpha: Dp by animateDpAsState(
                        if (dismissState.dismissDirection != null) 4.dp else 0.dp
                    )
                    Row(
                        modifier = Modifier
                            .clip(RectangleShape)
                            .background(colorResource(id = R.color.white))
                            .fillMaxWidth()
                            .graphicsLayer { alpha },
                        verticalAlignment = Alignment.CenterVertically
                    )

                    {
                      //  setUpRow(item = item)
                         Row(modifier = Modifier.fillMaxWidth().padding(10.dp)) {

                             Card(modifier = Modifier
                                 .padding(10.dp)
                                 .height(100.dp)
                                 .width(100.dp),
                                 elevation = 3.dp,
                                 backgroundColor = colorResource(id = R.color.light_gray)) {

                                 Image(
                                     painter = painterResource(id = R.drawable.anil), contentDescription = "Product",
                                     modifier = Modifier
                                         .height(50.dp)
                                         .width(50.dp)
                                         .padding(10.dp)
                                 )
                             }

                             Column(modifier = Modifier
                                 .fillMaxHeight(),
                                 verticalArrangement = Arrangement.Top){
                                 Text(
                                     text = item,
                                     color = colorResource(id = R.color.product_name_color),
                                     fontSize = 18.sp,
                                     modifier = Modifier.padding(top = 7.dp)
                                 )
                                 Text(
                                     text = "(Chair)",
                                     color = colorResource(id = R.color.product_name_color),
                                     fontSize = 15.sp,
                                     modifier = Modifier.padding(bottom = 5.dp)
                                 )


                                 Row(modifier = Modifier.fillMaxWidth().height(70.dp)) {

                                     ExposedDropdownMenuBox(
                                         expanded = expanded,
                                         onExpandedChange = {
                                             expanded = !expanded
                                         },
                                         modifier = Modifier
                                             .padding(5.dp)
                                             .height(50.dp)
                                             .width(80.dp)
                                     ) {
                                         TextField(
                                             readOnly = true,
                                             value = selectedOptionText,
                                             onValueChange = { selectedOptionText = it},
                                             trailingIcon = {
                                                 ExposedDropdownMenuDefaults.TrailingIcon(
                                                     expanded = expanded
                                                 )
                                             },
                                             colors = ExposedDropdownMenuDefaults.textFieldColors(
                                                 backgroundColor = colorResource(id = R.color.light_gray),
                                                 textColor = colorResource(id = R.color.product_detail_text2),
                                                 trailingIconColor = colorResource(id = R.color.product_detail_text2)
                                             )
                                         )
                                         ExposedDropdownMenu(
                                             expanded = expanded,
                                             onDismissRequest = {
                                                 expanded = false
                                             }
                                         ) {
                                             options.forEach { selectionOption ->
                                                 DropdownMenuItem(
                                                     onClick = {
                                                         selectedOptionText = selectionOption
                                                         expanded = false
                                                     }
                                                 ) {
                                                     Text(text = selectionOption, fontSize = 15.sp)
                                                 }
                                             }
                                         }
                                     }


                                     Box(modifier = Modifier.fillMaxWidth()){
                                             Text(
                                                 text = "Rs 45",
                                                 color = colorResource(id = R.color.product_name_color),
                                                 fontSize = 17.sp,
                                                 modifier = Modifier.align(Alignment.CenterEnd).padding(top = 10.dp)
                                             )

                                     }
                                 }

                             }


                         }
                        }
                }
            )

            Divider(color = colorResource(id = R.color.divider_color))
        })
        item {
           Column(modifier = Modifier.fillMaxWidth()) {

               Row(modifier = Modifier
                   .fillMaxSize()
                   .height(80.dp),
               verticalAlignment = Alignment.CenterVertically
               ) {
                   Spacer(modifier = Modifier.width(30.dp))
                   Text(text = stringResource(R.string.total), fontSize = 20.sp,
                       color = colorResource(id = R.color.product_name_color),
                       fontWeight = FontWeight.Bold
                       )

                   Box{
                       Row(verticalAlignment = Alignment.CenterVertically,
                           horizontalArrangement = Arrangement.End,
                       modifier = Modifier.fillMaxWidth()) {

                           Text(text = "Rs 180",fontSize = 20.sp,
                               color = colorResource(id = R.color.product_name_color),
                               fontWeight = FontWeight.Bold,
                           modifier = Modifier.padding(end = 10.dp))
                       }
                   }
               }
               Divider(color = colorResource(id = R.color.divider_color))

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
                       Text(text = stringResource(R.string.order_now), fontSize = 20.sp,
                           fontWeight = FontWeight.Bold)
                   }
               }
           }
        }
    }
}

