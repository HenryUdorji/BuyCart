package com.hashconcepts.buycart.data.remote.dto.request

import com.hashconcepts.buycart.data.remote.dto.response.ProductsDto

/**
 * @created 28/06/2022 - 4:23 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
data class CartsDto(
    val id: Int,
    val userId: Int,
    val date: String,
    val products: List<ProductsDto>
)
