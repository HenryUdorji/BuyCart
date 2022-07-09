package com.hashconcepts.buycart.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

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
        bottomBar = {
            if (showBottomBar) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                CustomBottomNavBar(currentRoute = currentRoute) { navItem ->
                    navController.navigate(navItem.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        }
    ) { paddingValues ->
        content(paddingValues)
    }
}