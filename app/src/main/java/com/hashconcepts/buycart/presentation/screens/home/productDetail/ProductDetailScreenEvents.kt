package com.hashconcepts.buycart.presentation.screens.home.productDetail

import com.hashconcepts.buycart.data.remote.dto.response.ProductsDto

/**
 * @created 11/07/2022 - 6:13 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
sealed class ProductDetailScreenEvents {
    data class AddProductToWishList(val productsDto: ProductsDto): ProductDetailScreenEvents()
}
