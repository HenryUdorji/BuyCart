package com.hashconcepts.buycart.presentation.screens.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hashconcepts.buycart.data.mapper.toProduct
import com.hashconcepts.buycart.presentation.components.CheckoutBottomSheetDialog
import com.hashconcepts.buycart.presentation.components.WishListOrderContainer
import com.hashconcepts.buycart.presentation.screens.destinations.ProductDetailScreenDestination
import com.hashconcepts.buycart.ui.theme.backgroundColor
import com.hashconcepts.buycart.ui.theme.disableColor
import com.hashconcepts.buycart.ui.theme.primaryColor
import com.hashconcepts.buycart.utils.UIEvents
import com.hashconcepts.buycart.utils.noRippleClickable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * @created 08/07/2022 - 4:49 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */

@OptIn(ExperimentalMaterialApi::class)
@Destination
@Composable
fun CartScreen(
    navigator: DestinationsNavigator,
    cartsViewModel: CartsViewModel = hiltViewModel()
) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(backgroundColor)
        systemUiController.setNavigationBarColor(Color.White)
    }

    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val bottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    LaunchedEffect(key1 = true) {
        cartsViewModel.eventChannelFlow.collectLatest { uiEvent ->
            when (uiEvent) {
                is UIEvents.ErrorEvent -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = uiEvent.message,
                        duration = SnackbarDuration.Short
                    )
                }
                else -> {
                    bottomSheetState.show()
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
                .padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
        ) {
            Text(
                text = "Cart",
                style = MaterialTheme.typography.h1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                textAlign = TextAlign.Center
            )

            val productInCart = cartsViewModel.cartScreenState.productInCart
            if (productInCart.isNotEmpty()) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = 10.dp),
                    content = {
                        items(productInCart) { product ->
                            WishListOrderContainer(
                                product = product.toProduct(),
                                isWishList = false,
                                onDeleteClicked = {
                                    cartsViewModel.onEvents(CartsScreenEvents.DeleteClicked(product))
                                },
                                onIncrement = {
                                    cartsViewModel.onEvents(CartsScreenEvents.IncrementClicked(product))
                                },
                                onDecrement = {
                                    cartsViewModel.onEvents(CartsScreenEvents.DecrementClicked(product))
                                },
                                onClick = {
                                    navigator.navigate(ProductDetailScreenDestination(product.id))
                                }
                            )

                        }
                    })

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(primaryColor)
                        .padding(vertical = 10.dp)
                        .noRippleClickable {
                            cartsViewModel.onEvents(
                                CartsScreenEvents.CheckoutClicked(
                                    productInCart
                                )
                            )
                        }
                ) {
                    Text(text = "Check out", style = MaterialTheme.typography.button)
                }
            } else {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = "Ooops!! You have no product in WishList",
                        style = MaterialTheme.typography.body1,
                        color = disableColor,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        CheckoutBottomSheetDialog(
            bottomSheetState = bottomSheetState,
            checkoutState = cartsViewModel.checkoutState
        ) {
            scope.launch {
                bottomSheetState.hide()
            }
        }
    }
}