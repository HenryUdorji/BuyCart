package com.hashconcepts.buycart.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hashconcepts.buycart.R
import com.hashconcepts.buycart.ui.theme.backgroundColor
import com.hashconcepts.buycart.ui.theme.disableColor
import com.hashconcepts.buycart.ui.theme.primaryColor
import com.hashconcepts.buycart.ui.theme.secondaryColor
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

/**
 * @created 08/07/2022 - 4:49 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(backgroundColor)
        systemUiController.setNavigationBarColor(Color.White)
    }

    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = backgroundColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp)
        ) {
            Text(
                text = "Discover",
                style = MaterialTheme.typography.h1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(10.dp))

            SearchFilterSection()

            Spacer(modifier = Modifier.height(10.dp))

            OfferSection(homeViewModel.offers)
        }
    }
}

@Composable
fun OfferSection(offers: List<String>) {

    Image(
        painter = rememberAsyncImagePainter(model = offers[0]),
        contentDescription = null,
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(15.dp))
    )
}

@Composable
fun SearchFilterSection() {
    var searchText by remember { mutableStateOf("") }
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        TextField(
            value = searchText,
            onValueChange = { searchText = it },
            placeholder = {
                Text(
                    text = "Search Product",
                    style = MaterialTheme.typography.body1,
                    color = disableColor
                )
            },
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = primaryColor,
                textColor = secondaryColor,
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .background(Color.White, shape = RoundedCornerShape(10.dp))
                .weight(1f)
                .height(50.dp),
            maxLines = 1,
            singleLine = true,
        )

        Spacer(modifier = Modifier.width(8.dp))

        var filterSelected by remember { mutableStateOf(false) }
        IconButton(onClick = {
            filterSelected = !filterSelected
        }) {
            Icon(
                modifier = Modifier
                    .size(50.dp)
                    .clip(shape = RoundedCornerShape(size = 8.dp))
                    .background(if (filterSelected) primaryColor else Color.White)
                    .padding(5.dp),
                painter = painterResource(id = R.drawable.ic_filter),
                contentDescription = null,
                tint = secondaryColor
            )
        }
    }
}
