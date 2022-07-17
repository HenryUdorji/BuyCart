package com.hashconcepts.buycart.domain.repository

import com.hashconcepts.buycart.data.remote.dto.request.ProfileDto
import com.hashconcepts.buycart.domain.model.UserProfile

/**
 * @created 17/07/2022 - 1:16 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
interface ProfileRepository {
    suspend fun fetchUserProfile(userId: Int): ProfileDto
    suspend fun saveUserProfile(userProfile: UserProfile)
    suspend fun getUserProfile(): UserProfile
    suspend fun updateUserProfile(userProfile: UserProfile)
}