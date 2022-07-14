package com.hashconcepts.buycart.presentation.screens.cart

import com.hashconcepts.buycart.domain.model.ProductInCart

/**
 * @created 10/07/2022 - 1:33 AM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
sealed class CartsScreenEvents {
    data class DeleteClicked(val productInCart: ProductInCart): CartsScreenEvents()
    data class IncrementClicked(val productInCart: ProductInCart): CartsScreenEvents()
    data class DecrementClicked(val productInCart: ProductInCart): CartsScreenEvents()
    data class CheckoutClicked(val productInCart: List<ProductInCart>): CartsScreenEvents()
}
