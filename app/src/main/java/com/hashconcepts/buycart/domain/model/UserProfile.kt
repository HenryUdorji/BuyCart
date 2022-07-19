package com.hashconcepts.buycart.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hashconcepts.buycart.data.remote.dto.request.Address
import com.hashconcepts.buycart.data.remote.dto.request.Name
import kotlinx.parcelize.Parcelize

/**
 * @created 28/06/2022 - 4:23 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */

@Entity(tableName = "user_profile")
@Parcelize
data class UserProfile(
    @PrimaryKey
    val email: String,
    val username: String,
    val password: String,
    val phone: String,
    val imageUrl: String = "https://github.com/HenryUdorji/BuyCart/raw/master/offersImages/profile.jpg",
    val name: Name,
    val address: Address,
    val paymentInfo: PaymentInfo? = null,
): Parcelable

@Parcelize
data class PaymentInfo(
    val cardNumber: String,
    val cardHolderName: String,
    val cardExpiryDate: String,
    val cardCVV: String
): Parcelable
