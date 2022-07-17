package com.hashconcepts.buycart.domain.usecases

import com.hashconcepts.buycart.data.mapper.toUserProfile
import com.hashconcepts.buycart.domain.model.UserProfile
import com.hashconcepts.buycart.domain.repository.ProfileRepository
import com.hashconcepts.buycart.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

/**
 * @created 17/07/2022 - 1:12 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
class ProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {

    fun userProfile(userId: Int): Flow<Resource<UserProfile>> = flow {
        try {
            emit(Resource.Loading())
            val response = profileRepository.fetchUserProfile(userId)
            emit(Resource.Success(response.toUserProfile()))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }

    fun saveUserProfile(userProfile: UserProfile): Flow<Resource<UserProfile>> = flow {
        try {
            profileRepository.saveUserProfile(userProfile)
        } catch (e: Exception) {
            emit(Resource.Error("Failed to save user profile"))
        }
    }

    fun getUserProfile(): Flow<Resource<UserProfile>> = flow {
        try {
            emit(Resource.Loading())
            val response = profileRepository.getUserProfile()
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error("Failed to get user profile"))
        }
    }

    fun updateUserProfile(userProfile: UserProfile): Flow<Resource<UserProfile>> = flow {
        try {
            emit(Resource.Loading())
            profileRepository.updateUserProfile(userProfile)
        } catch (e: Exception) {
            emit(Resource.Error("Failed to update user profile"))
        }
    }
}