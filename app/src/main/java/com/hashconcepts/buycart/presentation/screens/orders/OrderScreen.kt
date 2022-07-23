package com.hashconcepts.buycart.presentation.screens.orders

import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hashconcepts.buycart.ui.theme.backgroundColor
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

/**
 * @created 20/07/2022 - 2:37 AM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */

@Destination
@Composable
fun OrderScreen(
    navigator: DestinationsNavigator
) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(backgroundColor)
        systemUiController.setNavigationBarColor(Color.White)
    }

    val scaffoldState = rememberScaffoldState()
}