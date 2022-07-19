package com.hashconcepts.buycart.presentation.screens.profile

import com.hashconcepts.buycart.domain.model.PaymentInfo
import com.hashconcepts.buycart.domain.model.UserProfile

/**
 * @created 18/07/2022 - 10:08 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
sealed class PaymentInfoEvents {
    data class SaveCard(val paymentInfo: PaymentInfo?): PaymentInfoEvents()
}
