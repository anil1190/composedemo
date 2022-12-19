package com.neosoft.neostore.presentation.ui.product.address

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.neosoft.neostore.R
import com.neosoft.neostore.presentation.ui.product.ProductDetailPage
import com.neosoft.neostore.presentation.ui.product.address.ui.theme.NeoStoreTheme

class AddAddressActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NeoStoreTheme(true) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                   AddressTopBar()
                }
            }
        }
    }
}


@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun DefaultPreview7() {
    NeoStoreTheme {
        AddressTopBar()
    }
}

@ExperimentalMaterial3Api
@Composable
fun AddressTopBar(){
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = colorResource(id = R.color.dark_red))

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(R.string.add_address), color = Color.White,
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
        AddressScreen()
    }
}