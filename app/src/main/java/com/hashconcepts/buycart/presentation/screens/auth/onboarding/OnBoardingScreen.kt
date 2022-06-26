package com.hashconcepts.buycart.presentation.screens.auth.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * @created 26/06/2022 - 4:18 AM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */

@Composable
fun OnBoardingScreen(
    onBoardingFinished: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(10.dp)
    ) {
        TopSection()
    }
}

@Composable
@Preview
fun TopSection() {

}