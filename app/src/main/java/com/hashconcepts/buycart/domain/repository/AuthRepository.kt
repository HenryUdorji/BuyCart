package com.hashconcepts.buycart.domain.repository

import com.hashconcepts.buycart.data.remote.dto.request.LoginDto
import com.hashconcepts.buycart.data.remote.dto.request.ProfileDto
import com.hashconcepts.buycart.data.remote.dto.request.UserDto
import com.hashconcepts.buycart.data.remote.dto.response.LoginResponse

/**
 * @created 28/06/2022 - 8:02 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
interface AuthRepository {
    suspend fun loginUser(loginDto: LoginDto): LoginResponse
    suspend fun registerUser(profileDto: ProfileDto): UserDto
    suspend fun userProfile(userId: Int): ProfileDto
}