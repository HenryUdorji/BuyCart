package com.hashconcepts.buycart.presentation.screens.home

import androidx.lifecycle.ViewModel
import com.hashconcepts.buycart.data.local.SharedPrefUtil
import javax.inject.Inject

/**
 * @created 29/06/2022 - 8:52 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
class HomeViewModel @Inject constructor(
    private val sharedPrefUtil: SharedPrefUtil
): ViewModel() {

    val userIsLoggedIn = sharedPrefUtil.userIsLoggedIn()
}