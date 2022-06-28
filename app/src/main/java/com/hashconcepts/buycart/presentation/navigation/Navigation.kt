package com.hashconcepts.buycart.presentation.navigation

import androidx.activity.ComponentActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hashconcepts.buycart.presentation.screens.home.HomeScreen
import com.hashconcepts.buycart.presentation.screens.auth.onboarding.OnBoardingScreen
import com.hashconcepts.buycart.presentation.screens.auth.splash.SplashScreen

/**
 * @created 26/06/2022 - 4:13 AM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation() {
    val navController = rememberNavController()
    val systemUiController = rememberSystemUiController()
    val context = LocalContext.current

    NavHost(navController = navController, startDestination = Screens.SplashScreen.route) {
        composable(Screens.SplashScreen.route) {
            SplashScreen(
                onNavigateToOnBoarding = {
                    navController.popBackStack()
                    navController.navigate(Screens.OnBoardingScreen.route)
                }, onNavigateToHome = {
                    navController.popBackStack()
                    navController.navigate(Screens.HomeScreen.route)
                }
            )
        }
        composable(route = Screens.OnBoardingScreen.route) {
            OnBoardingScreen(
                systemUiController,
                onCloseApp = {
                    (context as ComponentActivity).finish()
                }) {
                navController.popBackStack()
                navController.navigate(Screens.HomeScreen.route)
            }
        }
        composable(route = Screens.HomeScreen.route) {
            HomeScreen(
                systemUiController
            )
        }
    }
}