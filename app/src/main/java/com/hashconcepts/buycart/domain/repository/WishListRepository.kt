package com.hashconcepts.buycart.domain.repository

import com.hashconcepts.buycart.data.remote.dto.response.ProductsDto
import com.hashconcepts.buycart.domain.model.Product

/**
 * @created 28/06/2022 - 8:02 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
interface WishListRepository {
    suspend fun addProductToWishList(productsDto: ProductsDto)
    suspend fun productsInWishList(): List<Product>
    suspend fun singleProductFromWishList(productId: Int): Product?
    suspend fun deleteProductFromWishList(product: Product)
}