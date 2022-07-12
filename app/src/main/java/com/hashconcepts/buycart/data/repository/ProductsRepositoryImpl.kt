package com.hashconcepts.buycart.data.repository

import com.hashconcepts.buycart.data.local.BuyCartDao
import com.hashconcepts.buycart.data.mapper.toProduct
import com.hashconcepts.buycart.data.remote.BuyCartApi
import com.hashconcepts.buycart.data.remote.dto.response.ProductsDto
import com.hashconcepts.buycart.domain.model.Product
import com.hashconcepts.buycart.domain.repository.ProductsRepository
import javax.inject.Inject

/**
 * @created 28/06/2022 - 8:12 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
class ProductsRepositoryImpl @Inject constructor(
    private val buyCartApi: BuyCartApi,
): ProductsRepository{
    override suspend fun allProducts(): List<ProductsDto> {
        return buyCartApi.allProducts()
    }

    override suspend fun singleProduct(productId: Int): ProductsDto {
        return buyCartApi.singleProduct(productId)
    }

    override suspend fun allCategories(): List<String> {
        return buyCartApi.allCategories()
    }

    override suspend fun productsInCategory(category: String): List<ProductsDto> {
        return buyCartApi.productsInCategory(category)
    }
}