package com.hashconcepts.buycart.presentation.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hashconcepts.buycart.data.local.SharedPrefUtil
import com.hashconcepts.buycart.data.remote.dto.response.ProductsDto
import com.hashconcepts.buycart.domain.usecases.CartUseCase
import com.hashconcepts.buycart.domain.usecases.ProductUseCase
import com.hashconcepts.buycart.utils.Resource
import com.hashconcepts.buycart.utils.UIEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import timber.log.Timber
import javax.inject.Inject

/**
 * @created 29/06/2022 - 8:52 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productUseCase: ProductUseCase,
    private val cartUseCase: CartUseCase,
): ViewModel() {

    var homeScreenState by mutableStateOf(HomeScreenState())
        private set

    private val eventChannel = Channel<UIEvents>()
    val eventChannelFlow = eventChannel.receiveAsFlow()

    init {
        fetchCategories()
        fetchProducts()
    }

    val offerImages = listOf(
        "https://github.com/HenryUdorji/BuyCart/raw/master/offersImages/offerImage1.jpg",
        "https://github.com/HenryUdorji/BuyCart/raw/master/offersImages/offerImage2.jpg",
        "https://github.com/HenryUdorji/BuyCart/raw/master/offersImages/offerImage3.jpg",
    )

    fun onEvents(events: HomeScreenEvents) {
        when(events) {
            is HomeScreenEvents.FilterClicked -> {
                homeScreenState = homeScreenState.copy(filterSelected = events.isClicked)
            }
            is HomeScreenEvents.CategorySelected -> {
                fetchProducts(events.category)
                homeScreenState = homeScreenState.copy(selectedCategoryIndex = events.index)
            }
            is HomeScreenEvents.AddProductToCart -> {
                addToCart(events.productId)
                homeScreenState = homeScreenState.copy(productInCart = events.productId)
            }
            is HomeScreenEvents.ProductClicked -> TODO()
        }
    }

    private fun fetchProducts(category: String = "All") {
        productUseCase.products(category).onEach { result ->
            when(result) {
                is Resource.Loading -> {
                    homeScreenState = homeScreenState.copy(isLoading = true)
                }
                is Resource.Error -> {
                    homeScreenState = homeScreenState.copy(isLoading = false)
                    eventChannel.send(UIEvents.ErrorEvent(result.message!!))
                }
                is Resource.Success -> {
                    homeScreenState = homeScreenState.copy(isLoading = false)
                    homeScreenState = homeScreenState.copy(products = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun fetchCategories() {
        productUseCase.categories().onEach { result ->
            when(result) {
                is Resource.Loading -> {
                    homeScreenState = homeScreenState.copy(isLoading = true)
                }
                is Resource.Error -> {
                    homeScreenState = homeScreenState.copy(isLoading = false)
                    eventChannel.send(UIEvents.ErrorEvent(result.message!!))
                }
                is Resource.Success -> {
                    val toMutableList = result.data?.toMutableList()
                    toMutableList?.add(0, "All")
                    homeScreenState = homeScreenState.copy(categories = (toMutableList!!))
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun addToCart(productId: Int) {
        cartUseCase.addToCart(productId).onEach { result ->
            when(result) {
                is Resource.Loading -> {
                    homeScreenState = homeScreenState.copy(addingToCart = true)
                }
                is Resource.Error -> {
                    homeScreenState = homeScreenState.copy(addingToCart = false)
                    eventChannel.send(UIEvents.ErrorEvent(result.message!!))
                }
                is Resource.Success -> {
                    homeScreenState = homeScreenState.copy(addingToCart = false)
                    homeScreenState = homeScreenState.copy(addedToCart = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}