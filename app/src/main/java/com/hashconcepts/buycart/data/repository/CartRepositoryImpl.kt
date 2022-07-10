package com.hashconcepts.buycart.data.repository

import com.hashconcepts.buycart.data.remote.BuyCartApi
import com.hashconcepts.buycart.data.remote.dto.AddCartDto
import com.hashconcepts.buycart.data.remote.dto.request.CartsDto
import com.hashconcepts.buycart.domain.repository.CartRepository
import okhttp3.ResponseBody
import javax.inject.Inject

/**
 * @created 28/06/2022 - 8:12 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
class CartRepositoryImpl @Inject constructor(
    private val buyCartApi: BuyCartApi
): CartRepository{
    override suspend fun usersCart(userId: Int): List<CartsDto> {
        return buyCartApi.usersCart(userId)
    }

    override suspend fun addToCart(addCartDto: AddCartDto): ResponseBody {
        return buyCartApi.addToCart(addCartDto)
    }
}