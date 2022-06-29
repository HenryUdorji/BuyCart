package com.hashconcepts.buycart.domain.repository

import com.hashconcepts.buycart.data.remote.dto.request.CartsDto
import com.hashconcepts.buycart.data.remote.dto.request.LoginDto
import com.hashconcepts.buycart.data.remote.dto.request.RegisterDto
import com.hashconcepts.buycart.data.remote.dto.request.UserDto
import com.hashconcepts.buycart.data.remote.dto.response.LoginResponse
import com.hashconcepts.buycart.data.remote.dto.response.ProductsDto

/**
 * @created 28/06/2022 - 8:02 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
interface CartRepository {
    suspend fun usersCart(userId: Int): List<CartsDto>
}