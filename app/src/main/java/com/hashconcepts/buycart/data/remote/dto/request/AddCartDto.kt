package com.hashconcepts.buycart.data.remote.dto.request

data class AddCartDto(
    val userId: Int,
    val date: String,
    val products: List<ProductInCart>
)

data class ProductInCart(
    val productId: Int,
    val quantity: Int
)