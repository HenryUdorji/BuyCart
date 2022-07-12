package com.hashconcepts.buycart.data.repository

import com.hashconcepts.buycart.data.remote.BuyCartApi
import com.hashconcepts.buycart.data.remote.dto.request.LoginDto
import com.hashconcepts.buycart.data.remote.dto.request.ProfileDto
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
    private val buyCartApi: BuyCartApi
): AuthRepository{
    override suspend fun loginUser(loginDto: LoginDto): LoginResponse {
        return buyCartApi.loginUser(loginDto)
    }

    override suspend fun registerUser(profileDto: ProfileDto): UserDto {
        return buyCartApi.registerUser(profileDto)
    }

    override suspend fun userProfile(userId: Int): ProfileDto {
        return buyCartApi.user(userId)
    }

}