package com.hashconcepts.buycart.data.repository

import com.hashconcepts.buycart.data.remote.dto.request.LoginDto
import com.hashconcepts.buycart.data.remote.dto.request.RegisterDto
import com.hashconcepts.buycart.data.remote.dto.request.UserDto
import com.hashconcepts.buycart.data.remote.dto.response.LoginResponse
import com.hashconcepts.buycart.domain.repository.AuthRepository
import javax.inject.Inject

/**
 * @created 28/06/2022 - 8:12 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
class AuthRepositoryImpl @Inject constructor(

): AuthRepository{
    override suspend fun loginUser(loginDto: LoginDto): LoginResponse {
        TODO("Not yet implemented")
    }

    override suspend fun registerUser(registerDto: RegisterDto): UserDto {
        TODO("Not yet implemented")
    }

    override suspend fun userProfile(userId: Int): UserDto {
        TODO("Not yet implemented")
    }

}