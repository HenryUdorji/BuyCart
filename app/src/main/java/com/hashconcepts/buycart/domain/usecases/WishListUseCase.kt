package com.hashconcepts.buycart.domain.usecases

import com.hashconcepts.buycart.data.remote.dto.response.ProductsDto
import com.hashconcepts.buycart.domain.model.Product
import com.hashconcepts.buycart.domain.repository.ProductsRepository
import com.hashconcepts.buycart.domain.repository.WishListRepository
import com.hashconcepts.buycart.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

/**
 * @created 06/07/2022 - 8:45 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
class WishListUseCase @Inject constructor(
    private val wishListRepository: WishListRepository,
) {

    fun addProductToWishList(productsDto: ProductsDto): Flow<Resource<Unit>> = flow {
        try {
            emit(Resource.Loading())
            val result = wishListRepository.addProductToWishList(productsDto)
            emit(Resource.Success(result))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unable to add product. Try again"))
        }
    }

    fun productsInWishList(): Flow<Resource<List<Product>>> = flow {
        try {
            emit(Resource.Loading())
            val result = wishListRepository.productsInWishList()
            emit(Resource.Success(result))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unable to fetch products. Try again"))
        }
    }

    fun singleProductFromWishList(productId: Int): Flow<Resource<Product?>> = flow {
        try {
            emit(Resource.Loading())
            val result = wishListRepository.singleProductFromWishList(productId)
            emit(Resource.Success(result))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unable to fetch product. Try again"))
        }
    }

    fun deleteProductFromWishList(product: Product): Flow<Resource<Unit>> = flow {
        try {
            emit(Resource.Loading())
            val result = wishListRepository.deleteProductFromWishList(product)
            emit(Resource.Success(result))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unable to delete product. Try again"))
        }
    }
}