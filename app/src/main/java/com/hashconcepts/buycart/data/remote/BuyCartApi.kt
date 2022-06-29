package com.hashconcepts.buycart.data.remote

import com.hashconcepts.buycart.data.remote.dto.request.CartsDto
import com.hashconcepts.buycart.data.remote.dto.request.LoginDto
import com.hashconcepts.buycart.data.remote.dto.request.RegisterDto
import com.hashconcepts.buycart.data.remote.dto.request.UserDto
import com.hashconcepts.buycart.data.remote.dto.response.LoginResponse
import com.hashconcepts.buycart.data.remote.dto.response.ProductsDto
import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * @created 28/06/2022 - 3:07 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
interface BuyCartApi {

    //Login
    @POST("auth/login")
    suspend fun loginUser(@Body loginDto: LoginDto): LoginResponse
    //Register
    @POST("users")
    suspend fun registerUser(@Body registerDto: RegisterDto): UserDto
    //All product
    @GET("products")
    suspend fun allProducts(): List<ProductsDto>
    //Single product
    @GET("products")
    suspend fun singleProduct(@Path("productId") productId: Int): ProductsDto
    //All category
    @GET("products/categories")
    suspend fun allCategories(): List<String>
    //products in a category
    @GET("products/category")
    suspend fun productsInCategory(@Path("category") category: String): List<ProductsDto>
    //Get user cart
    @GET("carts/user")
    suspend fun usersCart(@Path("userId") userId: Int): List<CartsDto>

    //Add new cart

    //update a cart

    //Delete a cart

    //Add user

    //Get user
    @GET("users")
    suspend fun user(@Path("userId") userId: Int): UserDto

    //Update user
}