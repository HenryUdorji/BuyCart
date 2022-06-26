package com.hashconcepts.buycart.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
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

    NavHost(navController = navController, startDestination = Screens.SplashScreen.route) {
        composable(Screens.SplashScreen.route) {
            SplashScreen {
                navController.popBackStack()
                navController.navigate(Screens.OnBoardingScreen.route)
            }
        }
        composable(route = Screens.OnBoardingScreen.route) {
            OnBoardingScreen {
                navController.popBackStack()
                navController.navigate(Screens.HomeScreen.route)
            }
        }
    }
}