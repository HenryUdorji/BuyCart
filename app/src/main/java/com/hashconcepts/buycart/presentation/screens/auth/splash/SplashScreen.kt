package com.hashconcepts.buycart.presentation.screens.auth.splash

import android.window.SplashScreen
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.hashconcepts.buycart.ui.theme.primary
import kotlinx.coroutines.delay

/**
 * @created 26/06/2022 - 4:02 AM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */

@Composable
fun SplashScreen(
    onSplashFinished: () -> Unit
) {

    LaunchedEffect(key1 = true) {
        delay(2000)
        onSplashFinished()
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