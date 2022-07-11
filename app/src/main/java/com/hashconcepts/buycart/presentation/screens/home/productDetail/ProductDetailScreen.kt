package com.hashconcepts.buycart.presentation.screens.home.productDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hashconcepts.buycart.R
import com.hashconcepts.buycart.presentation.components.CircularProgress
import com.hashconcepts.buycart.ui.theme.backgroundColor
import com.hashconcepts.buycart.ui.theme.primaryColor
import com.hashconcepts.buycart.ui.theme.secondaryColor
import com.hashconcepts.buycart.utils.UIEvents
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collectLatest

/**
 * @created 11/07/2022 - 11:04 AM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */

data class ProductDetailScreenNavArgs(
    val productId: Int
)

@Destination(navArgsDelegate = ProductDetailScreenNavArgs::class)
@Composable
fun ProductDetailScreen(
    navigator: DestinationsNavigator,
    productDetailViewModel: ProductDetailViewModel = hiltViewModel()
) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(backgroundColor)
        systemUiController.setNavigationBarColor(Color.White)
    }

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        productDetailViewModel.eventChannelFlow.collectLatest { uiEvent ->
            when (uiEvent) {
                is UIEvents.ErrorEvent -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = uiEvent.message,
                        duration = SnackbarDuration.Short
                    )
                }
                else -> {}
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = backgroundColor
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)) {
            ToolbarSection(
                productDetailState = productDetailViewModel.productDetailScreenState,
                onNavigateUp = { navigator.navigateUp() },
                onProductAddedToWishList = { /*TODO*/ }
            )
        }

    }
}

@Composable
fun ToolbarSection(
    productDetailState: ProductDetailScreenState,
    onNavigateUp: () -> Unit,
    onProductAddedToWishList: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        IconButton(onClick = onNavigateUp) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = null
            )
        }

        Text(
            text = "Details",
            style = MaterialTheme.typography.h1,
            textAlign = TextAlign.Center
        )

        IconButton(onClick = onProductAddedToWishList) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(5.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White)
                    .size(50.dp)
            ) {
                if (productDetailState.addingToWishList) {
                    CircularProgress()
                } else {
                    Icon(
                        painter = painterResource(id = if (productDetailState.addedToWishList) R.drawable.ic_wishlist else R.drawable.ic_wishlist_border),
                        contentDescription = null,
                        tint = if (productDetailState.addedToWishList) primaryColor else secondaryColor
                    )
                }
            }
        }
    }
}
