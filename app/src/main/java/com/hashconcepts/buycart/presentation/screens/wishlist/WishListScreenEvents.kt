package com.hashconcepts.buycart.presentation.screens.wishlist

import com.hashconcepts.buycart.domain.model.Product

/**
 * @created 10/07/2022 - 1:33 AM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
sealed class WishListScreenEvents {
    data class DeleteClicked(val product: Product): WishListScreenEvents()
    data class IncrementClicked(val product: Product): WishListScreenEvents()
    data class DecrementClicked(val product: Product): WishListScreenEvents()
}
