package com.hashconcepts.buycart.data.remote.dto.request

/**
 * @created 28/06/2022 - 3:51 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
data class RegisterDto(
    val username: String,
    val password: String,
    val email: String,
    val phone: String
)
