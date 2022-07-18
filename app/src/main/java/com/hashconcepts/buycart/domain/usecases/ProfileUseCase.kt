package com.hashconcepts.buycart.domain.usecases

import com.hashconcepts.buycart.data.mapper.toUserProfile
import com.hashconcepts.buycart.domain.model.UserProfile
import com.hashconcepts.buycart.domain.repository.ProfileRepository
import com.hashconcepts.buycart.presentation.screens.auth.ValidationResult
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

    fun saveUserProfile(userProfile: UserProfile): Flow<Boolean> = flow {
        try {
            profileRepository.saveUserProfile(userProfile)
            emit(true)
        } catch (e: Exception) {
            emit(false)
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

    fun updateUserProfile(userProfile: UserProfile): Flow<Resource<Unit>> = flow {
        try {
            emit(Resource.Loading())
            profileRepository.updateUserProfile(userProfile)
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error("Failed to update user profile"))
        }
    }

    companion object {
        fun validatePaymentInfo(
            cardNumber: String,
            cardHolderName: String,
            cardExpiry: String,
            cardCVV: String,
        ): ValidationResult {
            if (cardNumber.isBlank() && cardHolderName.isBlank() && cardExpiry.isBlank() && cardCVV.isBlank()) {
                return ValidationResult(
                    successful = false,
                    error =  "Please provide all fields."
                )
            }

            if (cardNumber.isBlank()) {
                return ValidationResult(
                    successful = false,
                    error = "Card Number field cannot be empty"
                )
            }

            if (cardHolderName.isBlank()) {
                return ValidationResult(
                    successful = false,
                    error = "CardHolder Name field cannot be empty"
                )
            }

            if (cardExpiry.isBlank()) {
                return ValidationResult(
                    successful = false,
                    error = "Card Expiry date field cannot be empty"
                )
            }

            if (cardCVV.isBlank()) {
                return ValidationResult(
                    successful = false,
                    error = "Card CVV field cannot be empty"
                )
            }

            if (cardNumber.length < 15) {
                return ValidationResult(
                    successful = false,
                    error = "Card Number is invalid"
                )
            }

            if (cardCVV.length < 3) {
                return ValidationResult(
                    successful = false,
                    error = "Card CVV field is invalid"
                )
            }
            return ValidationResult(true)
        }

    }
}