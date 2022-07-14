package com.hashconcepts.buycart.presentation.screens.wishlist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hashconcepts.buycart.presentation.components.WishListOrderContainer
import com.hashconcepts.buycart.presentation.screens.destinations.ProductDetailScreenDestination
import com.hashconcepts.buycart.ui.theme.backgroundColor
import com.hashconcepts.buycart.ui.theme.disableColor
import com.hashconcepts.buycart.utils.UIEvents
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collectLatest

/**
 * @created 08/07/2022 - 4:49 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */

@Destination
@Composable
fun WishListScreen(
    navigator: DestinationsNavigator,
    wishListViewModel: WishListViewModel = hiltViewModel()
) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(backgroundColor)
        systemUiController.setNavigationBarColor(Color.White)
    }

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        wishListViewModel.eventChannelFlow.collectLatest { uiEvent ->
            when(uiEvent) {
                is UIEvents.ErrorEvent -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = uiEvent.message,
                        duration = SnackbarDuration.Short
                    )
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = backgroundColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = "WishList",
                style = MaterialTheme.typography.h1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                textAlign = TextAlign.Center
            )

            val products = wishListViewModel.wishListScreenState.products
            if (products.isNotEmpty()) {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(20.dp), content = {
                    items(products) { product ->
                        WishListOrderContainer(
                            product = product,
                            onDeleteClicked = {
                                wishListViewModel.onEvents(WishListScreenEvents.DeleteClicked(product))
                            },
                            onClick = {
                                navigator.navigate(ProductDetailScreenDestination(product.id))
                            }
                        )

                    }
                })
            } else {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = "Ooops!! You have no product in WishList",
                        style = MaterialTheme.typography.body1,
                        color = disableColor,
                        modifier = Modifier.fillMaxWidth().align(Alignment.Center),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}