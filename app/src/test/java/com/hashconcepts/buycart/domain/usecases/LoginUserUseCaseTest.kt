package com.hashconcepts.buycart.domain.usecases

import com.google.common.truth.Truth.assertThat
import com.hashconcepts.buycart.data.remote.dto.request.LoginDto
import com.hashconcepts.buycart.domain.usecases.LoginUserUseCase.Companion.validateLoginRequest
import org.junit.Test

/**
 * @created 30/06/2022 - 3:51 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
class LoginUserUseCaseTest {

    @Test
    fun validateLoginRequest_usernameBlank_returnError() {
        val username = ""
        val password = "123456"

        val result = validateLoginRequest(username, password)
        assertThat(result.successful).isFalse()
    }

    @Test
    fun validateLoginRequest_passwordBlank_returnError() {
        val username = "Henry"
        val password = ""

        val result = validateLoginRequest(username, password)
        assertThat(result.successful).isFalse()
    }

    @Test
    fun validateLoginRequest_bothBlank_returnError() {
        val username = ""
        val password = ""

        val result = validateLoginRequest(username, password)
        assertThat(result.successful).isFalse()
    }

    @Test
    fun validateLoginRequest_valid_returnSuccess() {
        val username = "Henry"
        val password = "123456"

        val result = validateLoginRequest(username, password)
        assertThat(result.successful).isTrue()
    }
}