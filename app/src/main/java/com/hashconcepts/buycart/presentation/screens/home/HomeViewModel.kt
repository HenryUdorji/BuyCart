package com.hashconcepts.buycart.presentation.screens.home

import androidx.lifecycle.ViewModel
import com.hashconcepts.buycart.data.local.SharedPrefUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @created 29/06/2022 - 8:52 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val sharedPrefUtil: SharedPrefUtil
): ViewModel() {

    val userIsLoggedIn = sharedPrefUtil.userIsLoggedIn()
}