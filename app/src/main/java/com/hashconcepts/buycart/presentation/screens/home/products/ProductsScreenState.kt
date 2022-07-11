package com.hashconcepts.buycart.presentation.screens.home.products

import com.hashconcepts.buycart.data.remote.dto.response.ProductsDto

/**
 * @created 29/06/2022 - 8:50 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
data class ProductsScreenState(
    val userIsLoggedIn: Boolean = false,
    val isLoading: Boolean = false,
    val filterSelected: Boolean = false,
    val selectedCategoryIndex: Int = 0,
    val categories: MutableList<String> = mutableListOf(),
    val products: List<ProductsDto> = emptyList(),
    val addingToCart: Boolean = false,
    val addedToCart: Boolean = false,
    val productInCart: Int = -1,
)
