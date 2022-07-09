package com.hashconcepts.buycart.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.hashconcepts.buycart.ui.theme.backgroundColor

/**
 * @created 09/07/2022 - 2:02 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */

@Composable
fun CustomScaffold(
    navController: NavController,
    showBottomBar: Boolean = true,
    content: @Composable (paddingValues: PaddingValues) -> Unit
) {
    Scaffold(
        backgroundColor = backgroundColor,
        bottomBar = {
            if (showBottomBar) {
                CustomBottomNavBar(navController = navController)
            }
        }
    ) { paddingValues ->
        content(paddingValues)
    }
}