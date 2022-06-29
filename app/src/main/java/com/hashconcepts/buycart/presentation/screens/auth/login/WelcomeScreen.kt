package com.hashconcepts.buycart.presentation.screens.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.google.accompanist.systemuicontroller.SystemUiController
import com.hashconcepts.buycart.R
import com.hashconcepts.buycart.ui.theme.background
import com.hashconcepts.buycart.ui.theme.welcomeStatusBar

/**
 * @created 29/06/2022 - 9:17 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */

@Composable
fun WelcomeScreen(
    systemUiController: SystemUiController,
    onLoginClicked: () -> Unit,
    onRegisterClicked: () -> Unit
) {
    SideEffect {
        systemUiController.setStatusBarColor(welcomeStatusBar)
        systemUiController.setNavigationBarColor(welcomeStatusBar)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.banner_image),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}