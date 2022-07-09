package com.hashconcepts.buycart.presentation.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hashconcepts.buycart.data.local.SharedPrefUtil
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
    private val sharedPrefUtil: SharedPrefUtil,
    private val productUseCase: ProductUseCase
): ViewModel() {

    var homeScreenState by mutableStateOf(HomeScreenState())
        private set

    private val eventChannel = Channel<UIEvents>()
    val eventChannelFlow = eventChannel.receiveAsFlow()

    init {
        fetchProducts()
    }

    val offers = listOf(
        "https://github.com/HenryUdorji/BuyCart/raw/master/Fire_flash_sale_template_ss.jpg"
    )

    fun fetchProducts(category: String? = null) {
        productUseCase.products(category).onEach { result ->
            when(result) {
                is Resource.Loading -> {
                    homeScreenState = homeScreenState.copy(isLoading = true)
                }
                is Resource.Error -> {
                    eventChannel.send(UIEvents.ErrorEvent(result.message!!))
                }
                is Resource.Success -> {
                    homeScreenState = homeScreenState.copy(products = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }
}