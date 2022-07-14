package com.hashconcepts.buycart.presentation.screens.home.productDetail

import com.hashconcepts.buycart.data.remote.dto.response.ProductsDto
import com.hashconcepts.buycart.domain.model.Product

/**
 * @created 11/07/2022 - 6:13 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
sealed class ProductDetailScreenEvents {
    data class AddProductToWishList(val productsDto: ProductsDto): ProductDetailScreenEvents()
    data class DeleteProductFromWishList(val product: Product): ProductDetailScreenEvents()
    data class BuyNowClicked(val product: Product): ProductDetailScreenEvents()
}
