package com.hashconcepts.buycart.presentation.screens.cart

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hashconcepts.buycart.data.mapper.toProductInCart
import com.hashconcepts.buycart.data.remote.dto.response.ProductsDto
import com.hashconcepts.buycart.domain.model.Product
import com.hashconcepts.buycart.domain.model.ProductInCart
import com.hashconcepts.buycart.domain.usecases.CartUseCase
import com.hashconcepts.buycart.presentation.screens.wishlist.WishListScreenEvents
import com.hashconcepts.buycart.utils.Resource
import com.hashconcepts.buycart.utils.UIEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

/**
 * @created 14/07/2022 - 5:54 AM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */

@HiltViewModel
class CartsViewModel @Inject constructor(
    private val cartUseCase: CartUseCase
) : ViewModel() {

    var cartScreenState by mutableStateOf(CartsScreenState())
        private set

    private val eventChannel = Channel<UIEvents>()
    val eventChannelFlow = eventChannel.receiveAsFlow()

    init {
        usersCart()
    }

    fun onEvents(events: CartsScreenEvents) {
        when (events) {
            is CartsScreenEvents.DeleteClicked -> {
                deleteProductToCart(events.productInCart)
            }
            is CartsScreenEvents.DecrementClicked -> {
                if (events.productInCart.quantity > 1) {
                    updateProductInCart(
                        quantity = events.productInCart.quantity - 1,
                        price = events.productInCart.price.toDouble() - events.productInCart.pricePerItem,
                        events.productInCart
                    )
                }
            }
            is CartsScreenEvents.IncrementClicked -> {
                updateProductInCart(
                    quantity = events.productInCart.quantity + 1,
                    price = events.productInCart.price.toDouble() + events.productInCart.pricePerItem,
                    events.productInCart
                )
            }
        }
    }

    private fun updateProductInCart(
        quantity: Int,
        price: Double,
        productInCart: ProductInCart
    ) {
        val copy = productInCart.copy(price = price.toString(), quantity = quantity)

        cartUseCase.updateProductInCart(copy).onEach { result ->
            when (result) {
                is Resource.Loading -> {}
                is Resource.Error -> {
                    eventChannel.send(UIEvents.ErrorEvent(result.message!!))
                }
                is Resource.Success -> {
                    usersCart()
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun deleteProductToCart(productInCart: ProductInCart) {
        cartUseCase.deleteProductInCart(productInCart).onEach { result ->
            when (result) {
                is Resource.Loading -> {}
                is Resource.Error -> {
                    eventChannel.send(UIEvents.ErrorEvent(result.message!!))
                }
                is Resource.Success -> {
                    usersCart()
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun usersCart() {
        cartUseCase.usersCart().onEach { result ->
            when (result) {
                is Resource.Loading -> {}
                is Resource.Error -> {
                    eventChannel.send(UIEvents.ErrorEvent(result.message!!))
                }
                is Resource.Success -> {
                    cartScreenState =
                        cartScreenState.copy(productInCart = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }
}