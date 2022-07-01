package com.hashconcepts.buycart.presentation.screens.auth.login

/**
 * @created 01/07/2022 - 12:18 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
sealed class LoginScreenEvents {
    data class UsernameEntered(val username: String): LoginScreenEvents()
    data class PasswordEntered(val password: String): LoginScreenEvents()
    object LoginClicked: LoginScreenEvents()
    object RegisterClicked: LoginScreenEvents()
}
