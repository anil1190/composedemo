package com.neosoft.neostore.presentation.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.neosoft.neostore.presentation.ui.HomeScreen
import com.neosoft.neostore.presentation.ui.product.ProductDetailPage
import com.neosoft.neostore.presentation.ui.product.ProductPage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartNavigation(){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavigationScreen.Home.route ){

           composable(NavigationScreen.Home.route){
               HomeScreen(navController)
           }

        composable(NavigationScreen.Product.route){
            ProductPage(navController)
        }

        composable(NavigationScreen.ProductDetails.route){
            ProductDetailPage()
        }
    }
}