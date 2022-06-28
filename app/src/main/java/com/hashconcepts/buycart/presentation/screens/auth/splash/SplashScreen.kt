package com.hashconcepts.buycart.presentation.screens.auth.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.hashconcepts.buycart.presentation.screens.auth.AuthViewModel
import com.hashconcepts.buycart.ui.theme.primary
import kotlinx.coroutines.delay

/**
 * @created 26/06/2022 - 4:02 AM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */

@Composable
fun SplashScreen(
    onNavigateToOnBoarding: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    val viewModel = hiltViewModel<AuthViewModel>()

    LaunchedEffect(key1 = true) {
        delay(2000)
        if (viewModel.isFirstAppLaunch()) {
            onNavigateToOnBoarding()
        } else {
            onNavigateToHome()
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(primary)) {
        Text(
            text = "BuyCart",
            style = MaterialTheme.typography.h1,
            modifier = Modifier.align(Alignment.Center),
        )
    }
}