package com.hashconcepts.buycart.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.hashconcepts.buycart.R
import com.hashconcepts.buycart.presentation.screens.destinations.*
import com.hashconcepts.buycart.ui.theme.disableColor
import com.hashconcepts.buycart.ui.theme.primaryColor

/**
 * @created 07/07/2022 - 10:01 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */

sealed class BottomNavItem(val iconRes: Int, val destination: Destination) {
    object HomeScreen : BottomNavItem(R.drawable.ic_home, HomeScreenDestination)
    object CartScreen : BottomNavItem(R.drawable.ic_cart, CartScreenDestination)
    object WishListScreen : BottomNavItem(R.drawable.ic_wishlist, WishListScreenDestination)
    object ProfileScreen : BottomNavItem(R.drawable.ic_profile, ProfileScreenDestination)
}

fun bottomNavItems(): List<BottomNavItem> {
    return listOf(
        BottomNavItem.HomeScreen,
        BottomNavItem.WishListScreen,
        BottomNavItem.CartScreen,
        BottomNavItem.ProfileScreen,
    )
}


@Composable
@Preview
fun CustomBottomNavBar(
    modifier: Modifier = Modifier,
    navController: NavController,
    navItems: List<BottomNavItem> = bottomNavItems(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    BottomNavigation(
        backgroundColor = Color.White,
        elevation = 5.dp
    ) {
        navItems.forEach { item ->
            val selectedNavItem = currentRoute?.contains(item.destination.route) == true

            BottomNavigationItem(
                selected = selectedNavItem,
                selectedContentColor = primaryColor,
                unselectedContentColor = disableColor,
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconRes),
                        contentDescription = null,
                    )
                },
                onClick = {
                    if (!selectedNavItem) {
                        navController.popBackStack()
                        navController.navigate(item.destination.route) {
//                            navController.graph.startDestinationRoute?.let { route ->
//                                popUpTo(route) {
//                                    saveState = true
//                                }
//                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
            )
        }
    }
}