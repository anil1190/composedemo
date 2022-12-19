package com.neosoft.neostore.presentation.ui.product

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.neosoft.neostore.R
import com.neosoft.neostore.presentation.navigation.NavigationScreen

@ExperimentalMaterial3Api
@Composable
fun ProductPage(navHostController: NavHostController) {

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = colorResource(id = R.color.dark_red))

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Tables", color = Color.White,
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
        ProductPageContent(navHostController)
    }

}

@Composable
fun ProductPageContent(navHostController: NavHostController){

    val context = LocalContext.current
    val productList = listOf(
        "Stylish modern dining table", "C", "C#", "Java", "Kotlin", "Dart", "Python", "Javascript", "SpringBoot",
        "XML", "Dart", "Node JS", "Typescript", "Dot Net", "GoLang", "MongoDb",
    )
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
    // val totalRating = 5
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .fillMaxHeight()) {
        items(items = productList, itemContent = { item ->

            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .clip(RectangleShape)
                    .background(colorResource(id = R.color.white))
                    .fillMaxWidth()
                    .selectable(selected = true,
                        onClick = {
                            /* val intent = Intent(context,ProductDetailActivity::class.java)
                             context.startActivity(intent)*/
                            navHostController.navigate(NavigationScreen.ProductDetails.route)
                        }),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .clip(RectangleShape)
                        .size(100.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.anil), contentDescription = "Product",

                        )

                }

                Column(modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Top){
                    Text(
                        text = item,
                        color = colorResource(id = R.color.product_name_color),
                        fontSize = 18.sp,
                        modifier = Modifier.padding(bottom = 5.dp)

                    )
                    Text(
                        text = "Aron table center",
                        color = colorResource(id = R.color.product_name_color),
                        fontSize = 10.sp,
                        modifier = Modifier.padding(bottom = 14.dp)
                    )
                    Spacer(modifier = Modifier.padding(top = 7.dp))

                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Rs 27,390",
                            color = colorResource(id = R.color.red),
                            fontSize = 17.sp
                        )

                        Box{
                            Row(modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End) {
                                for (i in 0..2){
                                    Image(
                                        painter = painterResource(id = check_starList[i].star),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .height(20.dp)
                                            .width(20.dp),
                                    )
                                }
                                for (i in 0..1){
                                    Image(painter = painterResource(id = uncheck_starList[i].star), contentDescription = "",
                                        modifier = Modifier
                                            .height(20.dp)
                                            .width(20.dp),
                                        colorFilter = ColorFilter.tint(color = colorResource(id = R.color.uncheck_star_background)))
                                }
                            }
                        }
                    }

                }

            }

            Divider(color = colorResource(id = R.color.divider_color))
        })
    }
}