package com.hashconcepts.buycart.domain.usecases

import com.hashconcepts.buycart.data.remote.dto.request.AddCartDto
import com.hashconcepts.buycart.data.remote.dto.request.ProductInCart
import com.hashconcepts.buycart.domain.repository.CartRepository
import com.hashconcepts.buycart.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * @created 10/07/2022 - 8:49 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */

class CartUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {

    fun addToCart(productId: Int): Flow<Resource<ResponseBody>> = flow {
        try {
            emit(Resource.Loading())
            val addCartDto = AddCartDto(
                userId = 5, date = "2020-05-13", products = listOf(
                    ProductInCart(productId = productId, quantity = 3)
                )
            )
            val response = cartRepository.addToCart(addCartDto)
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}