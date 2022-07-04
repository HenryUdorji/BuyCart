package com.hashconcepts.buycart.domain.usecases

import com.google.common.truth.Truth.assertThat
import org.junit.Test

/**
 * @created 04/07/2022 - 7:28 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
class RegisterUserUseCaseTest {

    @Test
    fun validateRegisterRequest_allFieldEmpty_returnFalse() {

        val result = RegisterUserUseCase.validateRegisterRequest(
            username = "",
            password = "",
            email = "",
            phone = ""
        )
        assertThat(result.successful).isFalse()
    }

    @Test
    fun validateRegisterRequest_oneFieldEmpty_returnFalse() {

        val result = RegisterUserUseCase.validateRegisterRequest(
            username = "",
            password = "123456",
            email = "henry@gmail.com",
            phone = "07062396742"
        )
        assertThat(result.successful).isFalse()
    }

    @Test
    fun validateRegisterRequest_invalidEmail_returnFalse() {

        val result = RegisterUserUseCase.validateRegisterRequest(
            username = "Henry",
            password = "123456",
            email = "henry@",
            phone = "07062396742"
        )
        assertThat(result.successful).isFalse()
    }

    @Test
    fun validateRegisterRequest_invalidPhoneNo_returnFalse() {

        val result = RegisterUserUseCase.validateRegisterRequest(
            username = "Henry",
            password = "123456",
            email = "henry@gmail.com",
            phone = "07062396"
        )
        assertThat(result.successful).isFalse()
    }

    @Test
    fun validateRegisterRequest_allFieldValid_returnTrue() {

        val result = RegisterUserUseCase.validateRegisterRequest(
            username = "Henry",
            password = "123456",
            email = "henry@gmail.com",
            phone = "07062396742"
        )
        assertThat(result.successful).isTrue()
    }

}