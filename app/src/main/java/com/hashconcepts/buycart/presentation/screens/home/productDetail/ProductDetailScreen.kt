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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hashconcepts.buycart.R
import com.hashconcepts.buycart.data.mapper.toProduct
import com.hashconcepts.buycart.data.remote.dto.response.ProductsDto
import com.hashconcepts.buycart.presentation.components.CheckoutBottomSheetDialog
import com.hashconcepts.buycart.presentation.components.CircularProgress
import com.hashconcepts.buycart.ui.theme.*
import com.hashconcepts.buycart.utils.UIEvents
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * @created 11/07/2022 - 11:04 AM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */

data class ProductDetailScreenNavArgs(
    val productId: Int
)

@OptIn(ExperimentalMaterialApi::class)
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
    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        productDetailViewModel.eventChannelFlow.collectLatest { uiEvent ->
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
        backgroundColor = Color.White
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            if (productDetailViewModel.productDetailScreenState.isLoading) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .height(2.dp)
                        .fillMaxWidth(),
                    color = errorColor
                )
            }

            ToolbarSection(
                productDetailState = productDetailViewModel.productDetailScreenState,
                onNavigateUp = { navigator.navigateUp() },
                onProductAddedToWishList = {
                    productDetailViewModel.productDetailScreenState.productDetail?.let { productDto ->
                        productDetailViewModel.onEvents(
                            ProductDetailScreenEvents.AddProductToWishList(
                                productsDto = productDto
                            )
                        )
                    }
                },
                onProductDeletedFromWishList = {
                    productDetailViewModel.productDetailScreenState.productDetail?.let { productDto ->
                        productDetailViewModel.onEvents(
                            ProductDetailScreenEvents.DeleteProductFromWishList(
                                product = productDto.toProduct()
                            )
                        )
                    }
                }
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                productDetailViewModel.productDetailScreenState.productDetail?.let { productDto ->
                    Spacer(modifier = Modifier.height(20.dp))

                    ProductImageSection(productDto)

                    Spacer(modifier = Modifier.height(20.dp))

                    ProductDetailSection(productDto) {
                        productDetailViewModel.onEvents(
                            ProductDetailScreenEvents.BuyNowClicked(
                                productDto.toProduct()
                            )
                        )
                    }
                }
            }
        }
        CheckoutBottomSheetDialog(
            bottomSheetState = bottomSheetState,
            checkoutState = productDetailViewModel.checkoutState
        ) {
            scope.launch {
                bottomSheetState.hide()
            }
        }
    }
}

@Composable
fun ColumnScope.ProductDetailSection(
    productDto: ProductsDto,
    onBuyNowClicked: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .weight(1f)
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            .background(backgroundColor)
            .padding(20.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = productDto.title,
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
            text = productDto.description,
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
                Text(text = "$${productDto.price}", style = MaterialTheme.typography.h2)
            }

            TextButton(
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = primaryColor
                ),
                onClick = { onBuyNowClicked() }) {
                Text(
                    text = "Buy now",
                    style = MaterialTheme.typography.button,
                    fontSize = 13.sp,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                )
            }
        }
    }
}

@Composable
fun ProductImageSection(productDto: ProductsDto) {
    AsyncImage(
        model = productDto.image,
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
    onProductAddedToWishList: () -> Unit,
    onProductDeletedFromWishList: () -> Unit
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

        IconButton(
            onClick = if (productDetailState.addedToWishList) onProductDeletedFromWishList else onProductAddedToWishList
        ) {
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
