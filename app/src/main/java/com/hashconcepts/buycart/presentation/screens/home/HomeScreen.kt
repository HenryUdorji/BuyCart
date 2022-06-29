package com.hashconcepts.buycart.presentation.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.SystemUiController
import com.hashconcepts.buycart.ui.theme.background

/**
 * @created 28/06/2022 - 2:44 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */

@Composable
fun HomeScreen(
    systemUiController: SystemUiController
) {
    val viewModel = hiltViewModel<HomeViewModel>()

    SideEffect {
        systemUiController.setStatusBarColor(background)
    }
}