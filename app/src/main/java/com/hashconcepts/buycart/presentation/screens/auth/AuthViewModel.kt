package com.hashconcepts.buycart.presentation.screens.auth

import android.content.SharedPreferences
import androidx.compose.ui.unit.Constraints
import androidx.lifecycle.ViewModel
import com.hashconcepts.buycart.data.local.SharedPrefUtil
import com.hashconcepts.buycart.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @created 28/06/2022 - 2:08 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
@HiltViewModel
class AuthViewModel @Inject constructor(
    private val sharedPrefUtil: SharedPrefUtil
): ViewModel() {

    val isFirstAppLaunch = sharedPrefUtil.isFirstAppLaunch()

    fun saveFirstAppLaunch(value: Boolean) = sharedPrefUtil.saveFirstAppLaunch(value)

    fun saveUserAccessToken(token: String) = sharedPrefUtil.saveUserAccessToken(token)

}