package com.hashconcepts.buycart.presentation.screens.profile

import com.hashconcepts.buycart.domain.model.UserProfile

/**
 * @created 17/07/2022 - 1:51 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
data class ProfileScreenState(
    val userProfile: UserProfile? = null,
)
