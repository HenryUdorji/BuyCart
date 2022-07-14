package com.hashconcepts.buycart.presentation.screens.home.products

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hashconcepts.buycart.R
import com.hashconcepts.buycart.data.mapper.toProduct
import com.hashconcepts.buycart.data.mapper.toProductInCart
import com.hashconcepts.buycart.data.remote.dto.response.ProductsDto
import com.hashconcepts.buycart.presentation.components.CircularProgress
import com.hashconcepts.buycart.presentation.components.Indicators
import com.hashconcepts.buycart.presentation.screens.destinations.ProductDetailScreenDestination
import com.hashconcepts.buycart.presentation.screens.home.productDetail.ProductDetailScreenNavArgs
import com.hashconcepts.buycart.ui.theme.*
import com.hashconcepts.buycart.utils.UIEvents
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import java.lang.Exception

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
    productsViewModel: ProductsViewModel = hiltViewModel()
) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(backgroundColor)
        systemUiController.setNavigationBarColor(Color.White)
    }

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        productsViewModel.eventChannelFlow.collectLatest { uiEvent ->
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp)
        ) {
            if (productsViewModel.productsScreenState.isLoading) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .height(2.dp)
                        .fillMaxWidth(),
                    color = errorColor
                )
            }

            Text(
                text = "Discover",
                style = MaterialTheme.typography.h1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(10.dp))

            SearchFilterSection(productsViewModel.productsScreenState) {
                productsViewModel.onEvents(
                    ProductsScreenEvents.FilterClicked(!productsViewModel.productsScreenState.filterSelected)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            if (!productsViewModel.productsScreenState.filterSelected) {
                OfferSection(productsViewModel.offerImages)
            } else {
                CategorySection(productState = productsViewModel.productsScreenState) { category, index ->
                    productsViewModel.onEvents(
                        ProductsScreenEvents.CategorySelected(
                            category = category,
                            index = index
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            ProductSection(
                productState = productsViewModel.productsScreenState,
                onDeleteProductFromCart = { productDto ->
                    productsViewModel.onEvents(ProductsScreenEvents.DeleteProductFromCart(productDto))
                },
                onAddToCartCart = { productDto ->
                    productsViewModel.onEvents(ProductsScreenEvents.AddProductToCart(productDto))
                },
                onProductItemClicked = {
                    navigator.navigate(ProductDetailScreenDestination(ProductDetailScreenNavArgs(productId = it)))
                }
            )
        }
    }
}

@Composable
fun ProductSection(
    productState: ProductsScreenState,
    onAddToCartCart: (ProductsDto) -> Unit,
    onDeleteProductFromCart: (ProductsDto) -> Unit,
    onProductItemClicked: (Int) -> Unit,
) {
    val productInCart = productState.productInCart

    LazyVerticalGrid(
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        columns = GridCells.Fixed(2),
        content = {
            items(productState.products) { productsDto ->
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.White)
                        .padding(10.dp)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            onProductItemClicked(productsDto.id)
                        }
                ) {
                    AsyncImage(
                        model = productsDto.image,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        placeholder = painterResource(id = R.drawable.placeholder_image),
                        modifier = Modifier
                            .size(100.dp)
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = productsDto.title,
                        style = MaterialTheme.typography.h2,
                        fontSize = 12.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "$${productsDto.formatPrice()}",
                            style = MaterialTheme.typography.h2,
                            fontSize = 13.sp
                        )
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .background(primaryColor)
                                .padding(vertical = 5.dp, horizontal = 7.dp)
                                .clickable(
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() }
                                ) {
                                     if (productInCart.contains(productsDto.toProductInCart())) {
                                         onDeleteProductFromCart(productsDto)
                                     } else {
                                         onAddToCartCart(productsDto)
                                     }
                                }
                        ) {
                            if (productState.addingToCart && productInCart.contains(productsDto.toProductInCart())) {
                                CircularProgress()
                            } else {
                                Text(
                                    text = if (productInCart.contains(productsDto.toProductInCart())) "Added" else "Add Cart",
                                    style = MaterialTheme.typography.h2,
                                    fontSize = 11.sp,
                                )
                            }
                        }
                    }
                }
            }
        })
}

@Composable
fun CategorySection(
    productState: ProductsScreenState,
    onCategorySelected: (String, Int) -> Unit,
) {
    val categories = productState.categories
    val categoryIndex = productState.selectedCategoryIndex

    if (categories.isNotEmpty()) {
        LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp), content = {
            items(categories) { category ->
                Text(
                    text = category,
                    style = MaterialTheme.typography.h2,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(if (categoryIndex == categories.indexOf(category)) primaryColor else Color.White)
                        .padding(horizontal = 15.dp, vertical = 10.dp)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            onCategorySelected(category, categories.indexOf(category))
                        }
                )
            }
        })
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OfferSection(offers: List<String>) {
    val pagerState = rememberPagerState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        HorizontalPager(
            count = offers.size,
            state = pagerState,
            itemSpacing = 10.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            val currentOffer = offers[pagerState.currentPage]
            AsyncImage(
                model = currentOffer,
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.placeholder_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .height(170.dp)
            )
        }

        Spacer(modifier = Modifier.height(7.dp))

        Indicators(size = offers.size, index = pagerState.currentPage)
    }

    LaunchedEffect(key1 = pagerState.currentPage) {
        try {
            delay(3000L)
            val page = if (pagerState.currentPage < pagerState.pageCount - 1) {
                pagerState.currentPage + 1
            } else 0
            pagerState.scrollToPage(page)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

@Composable
fun SearchFilterSection(
    productState: ProductsScreenState,
    onShowCategories: () -> Unit
) {
    var searchText by remember { mutableStateOf("") }
    Row(
        verticalAlignment = Alignment.CenterVertically,
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
                .weight(1f),
            maxLines = 1,
            singleLine = true,
        )

        Spacer(modifier = Modifier.width(8.dp))

        IconButton(onClick = { onShowCategories() }) {
            Icon(
                modifier = Modifier
                    .size(50.dp)
                    .clip(shape = RoundedCornerShape(size = 8.dp))
                    .background(if (productState.filterSelected) primaryColor else Color.White)
                    .padding(5.dp),
                painter = painterResource(id = R.drawable.ic_filter),
                contentDescription = null,
                tint = secondaryColor
            )
        }
    }
}
