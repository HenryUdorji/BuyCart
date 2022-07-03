package com.hashconcepts.buycart.presentation.screens.auth.login

/**
 * @created 01/07/2022 - 9:08 AM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
data class LoginScreenState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val successful: Boolean = false,
    val loginFormState: LoginFormState? = null
)

data class LoginFormState(
    val username: String = "",
    val password: String = "",
    val formError: String? = null,
)