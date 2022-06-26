package com.hashconcepts.buycart.presentation.navigation

/**
 * @created 26/06/2022 - 4:06 AM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */

sealed class Screens(val route: String) {
    object SplashScreen: Screens("Splash")
    object OnBoardingScreen: Screens("OnBoarding")
    object LoginScreen: Screens("Login")
    object RegisterScreen: Screens("Register")
    object HomeScreen: Screens("Home")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}