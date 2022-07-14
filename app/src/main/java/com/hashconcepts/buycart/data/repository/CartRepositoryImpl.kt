package com.hashconcepts.buycart.data.repository

import com.hashconcepts.buycart.data.local.BuyCartDao
import com.hashconcepts.buycart.domain.model.ProductInCart
import com.hashconcepts.buycart.domain.repository.CartRepository
import javax.inject.Inject

/**
 * @created 28/06/2022 - 8:12 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
class CartRepositoryImpl @Inject constructor(
    private val buyCartDao: BuyCartDao
): CartRepository{
    override suspend fun usersCart(): List<ProductInCart> {
        return buyCartDao.productsInCart()
    }

    override suspend fun addProductToCart(productInCart: ProductInCart) {
        return buyCartDao.addProductToCart(productInCart)
    }

    override suspend fun updateProductInCart(productInCart: ProductInCart) {
        return buyCartDao.updateProductInCart(productInCart)
    }

    override suspend fun deleteProductFromCart(productInCart: ProductInCart) {
        return buyCartDao.deleteProductFromCart(productInCart)
    }

}