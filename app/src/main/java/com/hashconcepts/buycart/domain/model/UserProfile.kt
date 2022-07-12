package com.hashconcepts.buycart.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hashconcepts.buycart.data.remote.dto.request.Address
import com.hashconcepts.buycart.data.remote.dto.request.Name

/**
 * @created 28/06/2022 - 4:23 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */

@Entity
data class UserProfile(
    @PrimaryKey
    val email: String,
    val username: String,
    val password: String,
    val phone: String,
    val name: Name,
    val address: Address,
    val paymentInfo: PaymentInfo? = null,
)

data class PaymentInfo(
    val cardNo: String,
    val cardHolderName: String,
    val cardExpiryDate: String,
    val cardCvv: String
)
