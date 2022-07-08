package com.hashconcepts.buycart.presentation.navigation

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hashconcepts.buycart.presentation.screens.auth.login.LoginScreen
import com.hashconcepts.buycart.presentation.screens.auth.welcome.WelcomeScreen
import com.hashconcepts.buycart.presentation.screens.DashboardScreen
import com.hashconcepts.buycart.presentation.screens.auth.onboarding.OnBoardingScreen
import com.hashconcepts.buycart.presentation.screens.auth.register.RegisterScreen
import com.hashconcepts.buycart.presentation.screens.auth.splash.SplashScreen
import com.hashconcepts.buycart.presentation.screens.cart.CartScreen
import com.hashconcepts.buycart.presentation.screens.home.HomeScreen
import com.hashconcepts.buycart.presentation.screens.profile.ProfileScreen
import com.hashconcepts.buycart.presentation.screens.search.SearchScreen
import com.hashconcepts.buycart.presentation.screens.wishlist.WishListScreen

/**
 * @created 26/06/2022 - 4:13 AM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */

const val ROOT_ROUTE = "root"
const val DASHBOARD_ROUTE = "dashboard"


@Composable
fun SetupRootNavigation() {
    val navController = rememberNavController()
    val systemUiController = rememberSystemUiController()
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.route,
        route = ROOT_ROUTE
    ) {
        composable(Screens.SplashScreen.route) {
            SplashScreen(
                systemUiController,
                onNavigateToOnBoarding = {
                    navController.popBackStack()
                    navController.navigate(Screens.OnBoardingScreen.route)
                },
                onNavigateToHome = {
                    navController.popBackStack()
                    navController.navigate(Screens.DashboardScreen.route)
                }
            )
        }
        composable(route = Screens.OnBoardingScreen.route) {
            OnBoardingScreen(
                systemUiController,
                onCloseApp = {
                    (context as ComponentActivity).finish()
                },
                onBoardingFinished = {
                    navController.popBackStack()
                    navController.navigate(Screens.WelcomeScreen.route)
                })
        }
        composable(route = Screens.DashboardScreen.route) {
            DashboardScreen(
                systemUiController,
            )
        }
        composable(route = Screens.WelcomeScreen.route) {
            WelcomeScreen(
                systemUiController,
                onLoginClicked = {
                    navController.navigate(Screens.LoginScreen.route)
                },
                onRegisterClicked = {
                    navController.navigate(Screens.RegisterScreen.route)
                }
            )
        }
        composable(route = Screens.LoginScreen.route) {
            LoginScreen(
                systemUiController,
                onRegisterClicked = {
                    navController.popBackStack()
                    navController.navigate(Screens.RegisterScreen.route)
                },
                onLoginSuccessful = {
                    navController.navigate(Screens.DashboardScreen.route) {
                        popUpTo(Screens.LoginScreen.route)
                    }
                }
            )
        }
        composable(route = Screens.RegisterScreen.route) {
            RegisterScreen(
                systemUiController,
                onLoginClicked = {
                    navController.popBackStack()
                    navController.navigate(Screens.LoginScreen.route)
                },
                onRegisterSuccessful = {
                    navController.popBackStack()
                    navController.navigate(Screens.LoginScreen.route)
                }
            )
        }
    }
}

@Composable
fun SetupDashboardNavigation(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screens.BottomNavScreens.HomeScreen.route,
        route = DASHBOARD_ROUTE
    ) {
        composable(route = Screens.BottomNavScreens.HomeScreen.route) {
            HomeScreen()
        }
        composable(route = Screens.BottomNavScreens.SearchScreen.route) {
            SearchScreen()
        }
        composable(route = Screens.BottomNavScreens.WishListScreen.route) {
            WishListScreen()
        }
        composable(route = Screens.BottomNavScreens.CartScreen.route) {
            CartScreen()
        }
        composable(route = Screens.BottomNavScreens.ProfileScreen.route) {
            ProfileScreen()
        }
    }
}