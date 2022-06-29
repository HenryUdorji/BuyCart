package com.hashconcepts.buycart.presentation.screens.auth.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.SystemUiController
import com.hashconcepts.buycart.presentation.screens.auth.AuthViewModel
import com.hashconcepts.buycart.ui.theme.background

/**
 * @created 29/06/2022 - 8:57 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */

@Composable
fun LoginScreen(
    systemUiController: SystemUiController,
    onNavigateToRegisterScreen: () -> Unit
) {
    SideEffect {
        systemUiController.setStatusBarColor(background)
        systemUiController.setNavigationBarColor(background)
    }

    val viewModel = hiltViewModel<AuthViewModel>()


}