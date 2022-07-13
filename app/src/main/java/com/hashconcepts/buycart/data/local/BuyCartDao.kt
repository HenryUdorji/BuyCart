package com.hashconcepts.buycart.data.local

import androidx.room.*
import com.hashconcepts.buycart.domain.model.Product
import com.hashconcepts.buycart.domain.model.UserProfile
import retrofit2.http.DELETE

/**
 * @created 28/06/2022 - 4:36 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */

@Dao
interface BuyCartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUserProfile(userProfile: UserProfile)

    @Query("SELECT * FROM userprofile")
    suspend fun getUserProfile(): UserProfile

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProductToWishList(product: Product)

    @Update
    suspend fun updateProductInWishList(product: Product)

    @Query("SELECT * FROM wishlist WHERE id = :productId")
    suspend fun singleProductFromWishList(productId: Int): Product?

    @Query("SELECT * FROM wishlist")
    suspend fun getProductsInWishList(): List<Product>

    @Delete
    suspend fun deleteFromWishList(product: Product)

}