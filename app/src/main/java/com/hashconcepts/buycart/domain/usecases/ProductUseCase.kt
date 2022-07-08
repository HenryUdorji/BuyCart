package com.hashconcepts.buycart.domain.usecases

import com.hashconcepts.buycart.data.remote.dto.response.ProductsDto
import com.hashconcepts.buycart.domain.repository.ProductsRepository
import com.hashconcepts.buycart.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * @created 06/07/2022 - 8:45 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
class ProductUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
) {

    fun allProducts(): Flow<Resource<List<ProductsDto>>> = flow {
        try {
            emit(Resource.Loading())
            val response = productsRepository.allProducts()
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }

    fun categories(): Flow<Resource<List<String>>> = flow {
        try {
            emit(Resource.Loading())
            val response = productsRepository.allCategories()
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }

    fun productInCategories(category: String): Flow<Resource<List<ProductsDto>>> = flow {
        try {
            emit(Resource.Loading())
            val response = productsRepository.productsInCategory(category)
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }

    fun singleProduct(productId: Int): Flow<Resource<ProductsDto>> = flow {
        try {
            emit(Resource.Loading())
            val response = productsRepository.singleProduct(productId)
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}