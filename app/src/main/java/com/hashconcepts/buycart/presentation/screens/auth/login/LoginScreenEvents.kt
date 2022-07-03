package com.hashconcepts.buycart.presentation.screens.auth.login

/**
 * @created 01/07/2022 - 12:18 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
sealed class LoginScreenEvents {
    data class LoginClicked(val username: String, val password: String): LoginScreenEvents()
}
