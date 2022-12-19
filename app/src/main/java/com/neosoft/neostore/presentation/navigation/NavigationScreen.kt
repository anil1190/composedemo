package com.neosoft.neostore.presentation.navigation

sealed class NavigationScreen(val route : String){
    object Home : NavigationScreen("home")
    object Product : NavigationScreen("product")
    object ProductDetails : NavigationScreen("product_details")
}
