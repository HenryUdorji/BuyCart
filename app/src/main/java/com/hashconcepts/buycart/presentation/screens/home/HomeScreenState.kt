package com.hashconcepts.buycart.presentation.screens.home

import com.hashconcepts.buycart.data.remote.dto.response.ProductsDto

/**
 * @created 29/06/2022 - 8:50 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
data class HomeScreenState(
    val userIsLoggedIn: Boolean = false,
    val isLoading: Boolean = false,
    val categories: List<String> = emptyList(),
    val products: List<ProductsDto> = emptyList(),
)
