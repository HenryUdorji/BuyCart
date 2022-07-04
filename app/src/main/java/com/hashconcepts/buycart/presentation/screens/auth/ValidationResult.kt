package com.hashconcepts.buycart.presentation.screens.auth

/**
 * @created 30/06/2022 - 3:33 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
data class ValidationResult(
    val successful: Boolean,
    val error: String? = null
)