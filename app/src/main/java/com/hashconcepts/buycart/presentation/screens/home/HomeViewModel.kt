package com.hashconcepts.buycart.presentation.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hashconcepts.buycart.data.local.SharedPrefUtil
import com.hashconcepts.buycart.domain.usecases.ProductUseCase
import com.hashconcepts.buycart.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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

    init {
    }

    fun allProducts() {
        productUseCase.allProducts().onEach { result ->
            when(result) {
                is Resource.Loading -> {

                }
                is Resource.Error -> {

                }
                is Resource.Success -> {
                    Timber.d("SUCCESS ::::::::::: ${result.data}")
                }
            }
        }.launchIn(viewModelScope)
    }
}