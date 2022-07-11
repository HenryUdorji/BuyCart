package com.hashconcepts.buycart.presentation.screens.home.productDetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hashconcepts.buycart.domain.usecases.ProductUseCase
import com.hashconcepts.buycart.presentation.screens.navArgs
import com.hashconcepts.buycart.utils.Resource
import com.hashconcepts.buycart.utils.UIEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

/**
 * @created 11/07/2022 - 5:50 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val productUseCase: ProductUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var productDetailScreenState by mutableStateOf(ProductDetailScreenState())
        private set

    private val eventChannel = Channel<UIEvents>()
    val eventChannelFlow = eventChannel.receiveAsFlow()

    init {
        val navArgs = savedStateHandle.navArgs<ProductDetailScreenNavArgs>()
        singleProduct(navArgs.productId)
    }

    fun onEvents(events: ProductDetailScreenEvents) {
        when(events) {
            is ProductDetailScreenEvents.AddProductToWishList -> {
                //save to room
            }
        }
    }

    private fun singleProduct(productId: Int) {
        productUseCase.singleProduct(productId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    productDetailScreenState = productDetailScreenState.copy(isLoading = true)
                }
                is Resource.Error -> {
                    productDetailScreenState = productDetailScreenState.copy(isLoading = false)
                    eventChannel.send(UIEvents.ErrorEvent(result.message!!))
                }
                is Resource.Success -> {
                    productDetailScreenState = productDetailScreenState.copy(
                        isLoading = false,
                        productDetail = result.data
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}