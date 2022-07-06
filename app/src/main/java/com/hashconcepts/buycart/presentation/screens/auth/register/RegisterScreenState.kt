package com.hashconcepts.buycart.presentation.screens.auth.register

/**
 * @created 01/07/2022 - 9:08 AM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
data class RegisterScreenState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val successful: Boolean = false,
    val formError: String? = null,
)
