package com.hashconcepts.buycart.domain.usecases

import com.google.common.truth.Truth.assertThat
import org.junit.Test

/**
 * @created 18/07/2022 - 1:37 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
class ProfileUseCaseTest {

    @Test
    fun validatePaymentInfo_allFieldsEmpty_returnFalse() {
        val result = ProfileUseCase.validatePaymentInfo(
            cardHolderName = "",
            cardCVV = "",
            cardExpiry = "",
            cardNumber = ""
        )

        assertThat(result.successful).isFalse()
    }

    @Test
    fun validatePaymentInfo_allFieldsValid_returnTrue() {
        val result = ProfileUseCase.validatePaymentInfo(
            cardHolderName = "UDORJI IFECHUKWU HENRY",
            cardCVV = "522",
            cardExpiry = "22-12-2022",
            cardNumber = "5265889760123698"
        )

        assertThat(result.successful).isTrue()
    }

    @Test
    fun validatePaymentInfo_FieldEmpty_returnFalse() {
        val result = ProfileUseCase.validatePaymentInfo(
            cardHolderName = "",
            cardCVV = "522",
            cardExpiry = "22-12-2022",
            cardNumber = "5265889760123698"
        )

        assertThat(result.successful).isFalse()
    }

    @Test
    fun validatePaymentInfo_cardNoInvalid_returnFalse() {
        val result = ProfileUseCase.validatePaymentInfo(
            cardHolderName = "UDORJI IFECHUKWU HENRY",
            cardCVV = "522",
            cardExpiry = "22-12-2022",
            cardNumber = "526588"
        )

        assertThat(result.successful).isFalse()
    }

    @Test
    fun validatePaymentInfo_cardCVVInvalid_returnFalse() {
        val result = ProfileUseCase.validatePaymentInfo(
            cardHolderName = "UDORJI IFECHUKWU HENRY",
            cardCVV = "5",
            cardExpiry = "22-12-2022",
            cardNumber = "5265889760123698"
        )

        assertThat(result.successful).isFalse()
    }
}