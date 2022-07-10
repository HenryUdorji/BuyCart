package com.hashconcepts.buycart.presentation.screens.home

/**
 * @created 10/07/2022 - 1:33 AM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
sealed class HomeScreenEvents {
    data class FilterClicked(val isClicked: Boolean): HomeScreenEvents()
    data class CategorySelected(val category: String, val index: Int): HomeScreenEvents()
    data class ProductClicked(val productId: Int): HomeScreenEvents()
    data class AddProductToCart(val productId: Int): HomeScreenEvents()
}
