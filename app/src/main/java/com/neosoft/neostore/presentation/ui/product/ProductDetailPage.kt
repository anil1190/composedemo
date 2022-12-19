package com.neosoft.neostore.presentation.ui.product

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.neosoft.neostore.R
import com.neosoft.neostore.presentation.ui.GridModel
import com.neosoft.neostore.presentation.ui.product.addtocart.MyCartActivity
import com.neosoft.neostore.presentation.utils.CustomDialog

@ExperimentalMaterial3Api
@Composable
fun ProductDetailPage(){

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = colorResource(id = R.color.dark_red))

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Stylish modern dining table", color = Color.White,
                        textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth(),
                        fontWeight = FontWeight.Bold, fontSize = 25.sp)
                },
                navigationIcon = {
                    IconButton(onClick = {

                    }) {
                        Icon( Icons.Filled.ArrowBack, contentDescription ="")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor =  colorResource(id = R.color.medium_dark_red),
                    navigationIconContentColor  = colorResource(id = R.color.white)
                ),
                actions = {
                    IconButton(onClick = { }) {
                        Icon( Icons.Filled.Search, contentDescription ="" )
                    }
                }
            )
        }
    ) {
         ProductDetailContent()
    }

}

@Composable
fun ProductDetailContent(){

    val context = LocalContext.current
    val check_starList = listOf(
        RatingModal(R.drawable.star_check),
        RatingModal(R.drawable.star_check),
        RatingModal(R.drawable.star_check),
        RatingModal(R.drawable.star_check),
        RatingModal(R.drawable.star_check)
    )

    val uncheck_starList = listOf(
        RatingModal(R.drawable.star_unchek),
        RatingModal(R.drawable.star_unchek),
        RatingModal(R.drawable.star_unchek),
        RatingModal(R.drawable.star_unchek),
        RatingModal(R.drawable.star_unchek)
    )

    lateinit var horizontalListItem: List<GridModel>
    horizontalListItem = ArrayList<GridModel>()

    // on below line we are adding data to our list.
    horizontalListItem = horizontalListItem + GridModel( R.drawable.sliderimg1)
    horizontalListItem = horizontalListItem + GridModel( R.drawable.slider_img2)
    horizontalListItem = horizontalListItem + GridModel(R.drawable.slider_img3)
    horizontalListItem = horizontalListItem + GridModel(R.drawable.slider_img4)

    val showDialog =  remember { mutableStateOf(false) }

    if(showDialog.value)
        CustomDialog(value = "", setShowDialog = {
            showDialog.value = it
        }) {
            //  Log.i("Product Details Page","Product Details Page : $it")
            if (!it.isNullOrEmpty()){
                val intent = Intent(context,MyCartActivity::class.java)
                context.startActivity(intent)
            }
        }

    Column(modifier = Modifier.background(color = colorResource(id = R.color.divider_color))) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
            .padding(10.dp)){
            Text(
                text = "Stylish modern dining table",
                color = colorResource(id = R.color.product_detail_text1),
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 5.dp),
                fontWeight = FontWeight.Bold

            )
            Text(
                text = "Category - Table",
                color = colorResource(id = R.color.product_detail_text2),
                fontSize = 15.sp,
                modifier = Modifier.padding(bottom = 14.dp)
            )


            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Future Furniture Center",
                    color = colorResource(id = R.color.product_detail_text2),
                    fontSize = 12.sp
                )

                Box{
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End) {
                        for (i in 0..2){
                            Image(
                                painter = painterResource(id = check_starList[i].star),
                                contentDescription = "",
                                modifier = Modifier
                                    .height(18.dp)
                                    .width(18.dp)
                                    .padding(horizontal = 1.dp)
                            )
                        }
                        for (i in 0..1){
                            Image(painter = painterResource(id = uncheck_starList[i].star), contentDescription = "",
                                modifier = Modifier
                                    .height(18.dp)
                                    .width(18.dp)
                                    .padding(horizontal = 1.dp),
                                colorFilter = ColorFilter.tint(color = colorResource(id = R.color.uncheck_star_background)))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            Card( modifier = Modifier
                .padding(10.dp)
                .height(400.dp),
                elevation = 3.dp,
                backgroundColor = colorResource(id = R.color.white)) {

                Column(modifier = Modifier.fillMaxSize()) {

                    Row(modifier = Modifier.fillMaxWidth()) {

                        Text(
                            text = "Rs 3,290",
                            color = colorResource(id = R.color.red),
                            fontSize = 18.sp,
                            modifier = Modifier.padding(all = 14.dp)
                        )

                        Box(modifier = Modifier.fillMaxWidth()){

                            Row(modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End) {

                                IconButton(onClick = {
                                }) {
                                    Icon( Icons.Filled.Share, contentDescription ="",
                                        tint = colorResource(id = R.color.uncheck_star_background))
                                }
                            }
                        }

                    }

                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f, true),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top) {

                        Image(painter = painterResource(id = R.drawable.slider_img3), contentDescription = "",
                            modifier = Modifier
                                .height(150.dp)
                                .width(250.dp),
                            alignment = Alignment.TopCenter)

                        LazyRow(modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 3.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center ){
                            items(horizontalListItem.size){
                                Row(
                                    Modifier
                                        .padding(7.dp)
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.Top
                                ){
                                    Image(
                                        painter = painterResource(id = horizontalListItem[it].image),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .height(80.dp)
                                            .width(100.dp)
                                            .clip(RoundedCornerShape(2.dp))
                                            .border(
                                                1.dp,
                                                colorResource(id = R.color.product_detail_text2),
                                                RoundedCornerShape(2.dp)
                                            ),
                                        contentScale = ContentScale.Crop,
                                        alignment = Alignment.TopCenter
                                    )
                                }
                            }
                        }
                        Divider(color = colorResource(id = R.color.divider_color))

                        Column(modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(
                                rememberScrollState()
                            )
                            .padding(5.dp)) {

                            Text(
                                text = "Description",
                                color = colorResource(id = R.color.product_detail_text1),
                                fontSize = 18.sp,
                                modifier = Modifier.padding(bottom = 5.dp),
                                fontWeight = FontWeight.Bold

                            )
                            Text(
                                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                                color = colorResource(id = R.color.product_detail_text2),
                                fontSize = 15.sp,
                                modifier = Modifier.padding(bottom = 14.dp)
                            )

                        }
                    }

                }


            }


            Spacer(modifier = Modifier.height(10.dp))

            Row(modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.white)),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center) {

                Box(modifier = Modifier.fillMaxWidth().weight(1f).padding(start = 7.dp),
                    contentAlignment = Alignment.CenterStart) {
                    OutlinedButton(
                        border = BorderStroke(width = 2.dp, color = colorResource(id = R.color.red)),
                        onClick = {

                            showDialog.value = true
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = colorResource(id = R.color.white),
                            disabledContentColor = Color.Gray,
                            backgroundColor = colorResource(id = R.color.red)
                        ),

                        enabled = true
                    ) {
                        Text(
                            text = stringResource(R.string.buy_now), fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.width(10.dp))
                Box(modifier = Modifier.fillMaxWidth().weight(1f).padding(end = 7.dp),
                    contentAlignment = Alignment.CenterEnd) {
                    OutlinedButton(
                        border = BorderStroke(width = 2.dp, color = colorResource(id = R.color.light_gray)),
                        onClick = {

                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = colorResource(id = R.color.product_detail_text1),
                            disabledContentColor = Color.Gray,
                            backgroundColor = colorResource(id = R.color.light_gray)
                        ),

                        enabled = true
                    ) {
                        Text(
                            text = "RATE NOW", fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

            }
        }


    }

}