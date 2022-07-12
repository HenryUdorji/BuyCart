package com.hashconcepts.buycart.presentation.screens.home.productDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hashconcepts.buycart.R
import com.hashconcepts.buycart.data.remote.dto.response.ProductsDto
import com.hashconcepts.buycart.presentation.components.CircularProgress
import com.hashconcepts.buycart.ui.theme.*
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
        systemUiController.setStatusBarColor(Color.White)
        systemUiController.setNavigationBarColor(backgroundColor)
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
        backgroundColor = Color.White
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            ToolbarSection(
                productDetailState = productDetailViewModel.productDetailScreenState,
                onNavigateUp = { navigator.navigateUp() },
                onProductAddedToWishList = { /*TODO*/ }
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                productDetailViewModel.productDetailScreenState.productDetail?.let { product ->
                    Spacer(modifier = Modifier.height(20.dp))

                    ProductImageSection(product)

                    Spacer(modifier = Modifier.height(20.dp))

                    ProductDetailSection(product)
                }
            }
        }

    }
}

@Composable
fun ColumnScope.ProductDetailSection(product: ProductsDto) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .weight(1f)
            .fillMaxWidth()
            .clip(RoundedCornerShape(30.dp))
            .background(backgroundColor)
            .padding(20.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = product.title,
                style = MaterialTheme.typography.h2,
                fontSize = 18.sp,
                modifier = Modifier.fillMaxWidth(0.7f)
            )

            Column(horizontalAlignment = Alignment.End) {
                Row {
                    repeat(5) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = null,
                            tint = primaryColor,
                            modifier = Modifier.size(15.dp)
                        )
                    }
                }

                Text(text = "4.9", style = MaterialTheme.typography.h2, fontSize = 14.sp)
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "Description", style = MaterialTheme.typography.h2, fontSize = 14.sp)

        Text(
            text = product.description,
            style = MaterialTheme.typography.body1,
            fontSize = 14.sp,
            modifier = Modifier.weight(1f)
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Text(text = "Price", style = MaterialTheme.typography.body1, color = disableColor)
                Text(text = "$${product.price}", style = MaterialTheme.typography.h2)
            }

            TextButton(
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = primaryColor
                ),
                onClick = { /*TODO*/ }) {
                Text(
                    text = "Add Card",
                    style = MaterialTheme.typography.button,
                    fontSize = 13.sp,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                )
            }
        }
    }
}

@Composable
fun ProductImageSection(product: ProductsDto) {
    AsyncImage(
        model = product.image,
        contentDescription = null,
        placeholder = painterResource(id = R.drawable.placeholder_image),
        alignment = Alignment.Center,
        modifier = Modifier
            .height(250.dp)
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    )
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
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
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
                    .background(backgroundColor)
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
