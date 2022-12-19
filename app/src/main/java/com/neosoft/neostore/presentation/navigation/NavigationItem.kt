package com.neosoft.neostore.presentation.navigation

import com.neosoft.neostore.R

sealed class NavigationItem(val route : String, val icon: Int, val title : String){

    object MyCart : NavigationItem("cart", R.drawable.shopping_cart,"My Cart")
    object Table : NavigationItem("table", R.drawable.tables_icon,"Tables")
    object Sofas : NavigationItem("sofas",R.drawable.sofa_icon,"Sofas")
    object Chair : NavigationItem("chair", R.drawable.chair,"Chairs")
    object Cupboard : NavigationItem("cupboard", R.drawable.cupboard_icon,"Cupboards")
    object Account : NavigationItem("account", R.drawable.username_icon,"My Account")
    object Store : NavigationItem("store", R.drawable.storelocator_icon,"Store Locator")
    object Orders : NavigationItem("order", R.drawable.myorders_icon,"My Orders")
    object Logout : NavigationItem("logout", R.drawable.logout_icon,"Logout")

}
