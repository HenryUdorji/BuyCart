package com.hashconcepts.buycart.presentation.screens.auth.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.SystemUiController
import com.hashconcepts.buycart.presentation.navigation.Screens
import com.hashconcepts.buycart.presentation.screens.auth.AuthViewModel
import com.hashconcepts.buycart.ui.theme.background

/**
 * @created 29/06/2022 - 8:57 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */

@Composable
fun RegisterScreen(
    systemUiController: SystemUiController,
    onLoginClicked: () -> Unit
) {
    SideEffect {
        systemUiController.setStatusBarColor(background)
        systemUiController.setNavigationBarColor(background)
    }

    val viewModel = hiltViewModel<AuthViewModel>()

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(background)
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Register",
            style = MaterialTheme.typography.h1,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(30.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .background(Color.White)
                .weight(1f)
        ) {

        }
    }
}