package com.hashconcepts.buycart.data.remote.dto.response

data class ProductsDto(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: String,
    val title: String
)