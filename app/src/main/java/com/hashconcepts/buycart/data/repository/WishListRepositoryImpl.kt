package com.hashconcepts.buycart.data.repository

import com.hashconcepts.buycart.data.local.BuyCartDao
import com.hashconcepts.buycart.data.mapper.toProduct
import com.hashconcepts.buycart.data.remote.BuyCartApi
import com.hashconcepts.buycart.data.remote.dto.response.ProductsDto
import com.hashconcepts.buycart.domain.model.Product
import com.hashconcepts.buycart.domain.repository.ProductsRepository
import com.hashconcepts.buycart.domain.repository.WishListRepository
import javax.inject.Inject

/**
 * @created 28/06/2022 - 8:12 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
class WishListRepositoryImpl @Inject constructor(
    private val buyCartDao: BuyCartDao
): WishListRepository{
    override suspend fun addProductToWishList(productsDto: ProductsDto) {
        return buyCartDao.addProductToWishList(productsDto.toProduct())
    }

    override suspend fun productsInWishList(): List<Product> {
        return buyCartDao.getProductsInWishList()
    }

    override suspend fun singleProductFromWishList(productId: Int): Product? {
        return buyCartDao.singleProductFromWishList(productId)
    }

    override suspend fun deleteProductFromWishList(product: Product) {
        return buyCartDao.deleteFromWishList(product)
    }
}