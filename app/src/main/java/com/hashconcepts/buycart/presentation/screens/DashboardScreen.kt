package com.hashconcepts.buycart.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.hashconcepts.buycart.presentation.components.CustomBottomNavBar
import com.hashconcepts.buycart.presentation.navigation.Screens
import com.hashconcepts.buycart.presentation.navigation.SetupDashboardNavigation
import com.hashconcepts.buycart.presentation.screens.home.HomeViewModel
import com.hashconcepts.buycart.ui.theme.backgroundColor

/**
 * @created 28/06/2022 - 2:44 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun DashboardScreen(
    systemUiController: SystemUiController,
) {
    SideEffect {
        systemUiController.setStatusBarColor(backgroundColor)
        systemUiController.setNavigationBarColor(Color.White)
    }

    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),
        backgroundColor = backgroundColor,
        bottomBar = {
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
    ) {
        SetupDashboardNavigation(navController)
    }
}
