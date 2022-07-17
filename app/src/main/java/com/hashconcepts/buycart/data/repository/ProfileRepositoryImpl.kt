package com.hashconcepts.buycart.data.repository

import com.hashconcepts.buycart.data.local.BuyCartDao
import com.hashconcepts.buycart.data.remote.BuyCartApi
import com.hashconcepts.buycart.data.remote.dto.request.ProfileDto
import com.hashconcepts.buycart.domain.model.UserProfile
import com.hashconcepts.buycart.domain.repository.ProfileRepository
import javax.inject.Inject

/**
 * @created 17/07/2022 - 1:18 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
class ProfileRepositoryImpl @Inject constructor(
    private val buyCartApi: BuyCartApi,
    private val buyCartDao: BuyCartDao
): ProfileRepository {

    override suspend fun fetchUserProfile(userId: Int): ProfileDto {
        return buyCartApi.user(userId)
    }

    override suspend fun saveUserProfile(userProfile: UserProfile) {
        return buyCartDao.saveUserProfile(userProfile)
    }

    override suspend fun getUserProfile(): UserProfile {
        return buyCartDao.getUserProfile()
    }

    override suspend fun updateUserProfile(userProfile: UserProfile) {
        return buyCartDao.updateUserProfile(userProfile)
    }
}