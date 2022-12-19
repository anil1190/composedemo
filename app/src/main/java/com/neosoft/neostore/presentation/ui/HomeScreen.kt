package com.neosoft.neostore.presentation.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.neosoft.neostore.R
import com.neosoft.neostore.presentation.navigation.NavigationItem
import com.neosoft.neostore.presentation.navigation.NavigationScreen
import com.neosoft.neostore.presentation.ui.product.ProductListingActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navHostController: NavHostController){
    val systemUiController = rememberSystemUiController()
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()
    systemUiController.setStatusBarColor(color = colorResource(id = R.color.dark_red))

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.neostore), color = Color.White,
                        textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth(),
                        fontWeight = FontWeight.Bold, fontSize = 26.sp,
                        fontFamily = FontFamily.SansSerif
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon( Icons.Filled.Menu, contentDescription ="" ,
                        )
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
        },
        drawerContent ={ DrawerView(scope,scaffoldState,navController)},
        drawerBackgroundColor = Color.Black
    ) {
        //  Navigation(navController)
        HorizontalPagerScreen(navHostController)
    }
}


@Composable
fun DrawerView(scope: CoroutineScope, scaffoldState: ScaffoldState, navController: NavController) {

    val items = listOf(
        NavigationItem.MyCart,
        NavigationItem.Table,
        NavigationItem.Sofas,
        NavigationItem.Chair,
        NavigationItem.Cupboard,
        NavigationItem.Account,
        NavigationItem.Store,
        NavigationItem.Orders,
        NavigationItem.Logout
    )

    Column(modifier = Modifier.background(Color.Black)) {

        Column(modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Image(
                painter = painterResource(id =R.drawable.anil),
                contentDescription = "",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.White, CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "Anil Singh",
                color = colorResource(id = R.color.white),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 19.sp,
                modifier = Modifier
                    .padding(10.dp)
                    .align(alignment = Alignment.CenterHorizontally),

                )


            Text(
                text = "anil@gmail.com",
                color = colorResource(id = R.color.white),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
            )
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(5.dp))

        Divider()

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEachIndexed { index, navigationItem ->
            DrawerItems(item = navigationItem, position = index, onItemClick = {
                navController.navigate(navigationItem.route){
                    navController.graph.startDestinationRoute?.let {route->
                        popUpTo(route){
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }

                scope.launch {
                    scaffoldState.drawerState.close()
                }
            })
        }

    }
}

@Composable
fun DrawerItems(item: NavigationItem, position:Int, onItemClick : (NavigationItem)->Unit){
    // val background = if (selected) R.color.red else R.color.black

    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(item) }
            .height(45.dp)
            .padding(start = 10.dp)
            .background(colorResource(id = R.color.black))) {

        Image(
            painter = painterResource(id = item.icon) ,
            contentDescription = item.title,
            colorFilter = ColorFilter.tint(Color.White),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .height(27.dp)
                .width(27.dp))

        Spacer(modifier = Modifier.width(7.dp))

        Text(
            text =item.title,
            fontSize = 19.sp,
            color = colorResource(id = R.color.white),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 10.dp)
        )

        if (position == 0){
            Box(modifier = Modifier.fillMaxWidth().padding(end = 15.dp, top = 2.dp, bottom = 2.dp)
            ){
                Text(
                    modifier = Modifier
                        .padding(11.dp)
                        .drawBehind {
                            drawCircle(
                                color = Color.Red,
                                radius = this.size.maxDimension
                            )
                        }
                        .align(Alignment.TopEnd),
                    text = "1",
                    fontWeight = FontWeight.Bold
                )

            }

        }
    }
    Divider()
}



/*@Composable
fun Navigation(navController: NavHostController){
    NavHost(navController = navController, startDestination = NavigationItem.MyCart.route){

        composable(NavigationItem.MyCart.route){
            CartScreen()
        }
        composable(NavigationItem.Table.route){
            TableScreen()
        }
        composable(NavigationItem.Chair.route){
            ChairScreen()
        }
    }
}*/


@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun HorizontalPagerScreen(navHostController: NavHostController) {
    val slideImage = remember { mutableStateOf(R.drawable.sliderimg1) }
    val state = rememberPagerState()

    Column{
        HorizontalPager(
            count = 4,
            state = state
        ) { page ->
            when (page) {

                0 -> {
                    slideImage.value = R.drawable.sliderimg1
                }

                1 -> {
                    slideImage.value = R.drawable.slider_img2
                }

                2 -> {
                    slideImage.value = R.drawable.slider_img3
                }
                3 ->{
                    slideImage.value = R.drawable.slider_img4
                }
            }

            Image(
                painterResource(slideImage.value),
                contentDescription = "",
                modifier = Modifier
                    .height(210.dp)
                    .fillMaxWidth(),
                alignment = Alignment.TopCenter,
                contentScale = ContentScale.FillWidth

            )

        }


        DotsIndicator(
            totalDots = 4,
            selectedIndex = state.currentPage,
            selectedColor = Color.Red,
            unSelectedColor = Color.Black,
            navHostController = navHostController
        )
    }


}

@SuppressWarnings("FrequentlyChangedStateReadInComposition")
@Composable
fun DotsIndicator(
    totalDots : Int,
    selectedIndex : Int,
    selectedColor: Color,
    unSelectedColor: Color,
    navHostController: NavHostController
){

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Center,
        contentPadding = PaddingValues(vertical = 3.dp)

    ) {

        items(totalDots) { index ->
            if (index == selectedIndex) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(selectedColor)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(unSelectedColor)
                )
            }

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }

    gridView(LocalContext.current,navHostController)
    // productItems(LocalContext.current)
}


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun productItems(context: Context){

    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxSize()
        .padding(start = 15.dp, end = 15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp)
                .height(180.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Box(modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)){

                Image(
                    painter = painterResource(id = R.drawable.tableicon),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(180.dp)
                        .width(180.dp)
                        .align(Alignment.TopStart)
                        .clickable {
                            Toast
                                .makeText(
                                    context,
                                    " table icon..",
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                        },


                    )

                Image(
                    painter = painterResource(id = R.drawable.sofaicon) ,
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(180.dp)
                        .width(180.dp)
                        .align(Alignment.TopEnd)
                        .clickable {
                            Toast
                                .makeText(
                                    context,
                                    " sofa icon..",
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                        }
                )
            }
        }


        Row( modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp)
            .height(180.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {

            Box(modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)){

                Image(
                    painter = painterResource(id = R.drawable.chairsicon),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(180.dp)
                        .width(180.dp)
                        .align(Alignment.BottomStart)
                )


                Image(
                    painter = painterResource(id = R.drawable.cupboardicon),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(180.dp)
                        .width(180.dp)
                        .align(Alignment.BottomEnd)
                )
            }

        }

    }

}


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun gridView(context: Context, navHostController: NavHostController) {
    lateinit var productList: List<GridModel>
    productList = ArrayList<GridModel>()

    // on below line we are adding data to our list.
    productList = productList + GridModel( R.drawable.tableicon)
    productList = productList + GridModel( R.drawable.sofaicon)
    productList = productList + GridModel(R.drawable.chairsicon)
    productList = productList + GridModel(R.drawable.cupboardicon)



    LazyVerticalGrid(

        columns = GridCells.Fixed(2),

        modifier = Modifier.padding(10.dp)
    ) {

        items(productList.size) {

            Card(
                onClick = {
                    // inside on click we are displaying the toast message.
                  /*  val intent = Intent(context, ProductListingActivity::class.java)
                    context.startActivity(intent)*/
                     navHostController.navigate(NavigationScreen.Product.route)
                },

                modifier = Modifier
                    .padding(8.dp)
                    .height(160.dp),
                // elevation = 6.dp,
                backgroundColor = colorResource(id = R.color.red),
                shape = RectangleShape
            ) {

                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = productList[it].image),
                        contentDescription = "",
                        modifier = Modifier
                            .height(165.dp)
                            .width(170.dp),
                        contentScale = ContentScale.Fit
                    )

                    Spacer(modifier = Modifier.height(9.dp))

                }
            }
        }
    }
}
