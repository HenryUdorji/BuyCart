package com.hashconcepts.buycart.presentation.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.hashconcepts.buycart.R

/**
 * @created 26/06/2022 - 4:06 AM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */

sealed class Screens(val route: String) {
    object SplashScreen: Screens("Splash")
    object OnBoardingScreen: Screens("OnBoarding")
    object WelcomeScreen: Screens("Welcome")
    object LoginScreen: Screens("Login")
    object RegisterScreen: Screens("Register")
    object DashboardScreen: Screens("Dashboard")

    sealed class BottomNavScreens(
        val route: String,
        val iconRes: Int
    ) {
        object HomeScreen: BottomNavScreens("Home", R.drawable.ic_home)
        object SearchScreen: BottomNavScreens("Search", R.drawable.ic_search)
        object CartScreen: BottomNavScreens("Cart", R.drawable.ic_cart)
        object WishListScreen: BottomNavScreens("WishList", R.drawable.ic_wishlist)
        object ProfileScreen: BottomNavScreens("Profile", R.drawable.ic_profile)
    }

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}