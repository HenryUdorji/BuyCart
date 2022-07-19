package com.hashconcepts.buycart.data.local

import androidx.room.*
import com.hashconcepts.buycart.domain.model.Product
import com.hashconcepts.buycart.domain.model.ProductInCart
import com.hashconcepts.buycart.domain.model.UserProfile

/**
 * @created 28/06/2022 - 4:36 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */

@Dao
interface BuyCartDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun saveUserProfile(userProfile: UserProfile)

    @Query("SELECT * FROM user_profile")
    suspend fun getUserProfile(): UserProfile

    @Update
    suspend fun updateUserProfile(userProfile: UserProfile)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProductToWishList(product: Product)

    @Query("SELECT * FROM wishlist WHERE id = :productId")
    suspend fun singleProductFromWishList(productId: Int): Product?

    @Query("SELECT * FROM wishlist")
    suspend fun getProductsInWishList(): List<Product>

    @Delete
    suspend fun deleteFromWishList(product: Product)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProductToCart(productInCart: ProductInCart)

    @Query("SELECT * FROM cart")
    suspend fun productsInCart(): List<ProductInCart>

    @Update
    suspend fun updateProductInCart(productInCart: ProductInCart)

    @Delete
    suspend fun deleteProductFromCart(productInCart: ProductInCart)
}