package com.hashconcepts.buycart.presentation.screens.auth

/**
 * @created 01/07/2022 - 12:18 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
sealed class AuthScreenEvents {
    data class LoginClicked(val username: String, val password: String) : AuthScreenEvents()
    data class RegisterClicked(
        val username: String,
        val password: String,
        val email: String,
        val phoneNo: String
    ) : AuthScreenEvents()
}
