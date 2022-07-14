package com.hashconcepts.buycart.presentation.screens.wishlist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hashconcepts.buycart.data.remote.dto.response.ProductsDto
import com.hashconcepts.buycart.domain.model.Product
import com.hashconcepts.buycart.domain.usecases.WishListUseCase
import com.hashconcepts.buycart.utils.Resource
import com.hashconcepts.buycart.utils.UIEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

/**
 * @created 12/07/2022 - 6:08 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
@HiltViewModel
class WishListViewModel @Inject constructor(
    private val wishListUseCase: WishListUseCase
): ViewModel() {

    var wishListScreenState by mutableStateOf(WishListScreenState())
        private set

    private val eventChannel = Channel<UIEvents>()
    val eventChannelFlow = eventChannel.receiveAsFlow()

    init {
        productsInWishList()
    }

    fun onEvents(events: WishListScreenEvents) {
        when(events) {
            is WishListScreenEvents.DeleteClicked -> {
                deleteProductFromWishList(events.product)
            }
        }
    }

    private fun productsInWishList() {
        wishListUseCase.productsInWishList().onEach { result ->
            when(result) {
                is Resource.Loading -> {
                    wishListScreenState = wishListScreenState.copy(isLoading = true)
                }
                is Resource.Error -> {
                    wishListScreenState = wishListScreenState.copy(isLoading = false)
                    eventChannel.send(UIEvents.ErrorEvent(result.message!!))
                }
                is Resource.Success -> {
                    wishListScreenState = wishListScreenState.copy(isLoading = false, products = result.data!!)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun singleProductFromWishList(productId: Int) {
        wishListUseCase.singleProductFromWishList(productId).onEach { result ->
            when(result) {
                is Resource.Loading -> { }
                is Resource.Error -> {
                    eventChannel.send(UIEvents.ErrorEvent(result.message!!))
                }
                is Resource.Success -> {
                    wishListScreenState = wishListScreenState.copy(product = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun deleteProductFromWishList(product: Product) {
        wishListUseCase.deleteProductFromWishList(product).onEach { result ->
            when(result) {
                is Resource.Loading -> {}
                is Resource.Error -> {
                    eventChannel.send(UIEvents.ErrorEvent(result.message!!))
                }
                is Resource.Success -> {
                    productsInWishList()
                }
            }
        }.launchIn(viewModelScope)
    }
}