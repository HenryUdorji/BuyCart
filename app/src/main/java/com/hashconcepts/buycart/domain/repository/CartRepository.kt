package com.hashconcepts.buycart.domain.repository

import com.hashconcepts.buycart.domain.model.ProductInCart

/**
 * @created 28/06/2022 - 8:02 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
interface CartRepository {
    suspend fun usersCart(): List<ProductInCart>
    suspend fun addProductToCart(productInCart: ProductInCart)
    suspend fun updateProductInCart(productInCart: ProductInCart)
    suspend fun deleteProductFromCart(productInCart: ProductInCart)
}