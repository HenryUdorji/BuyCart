package com.hashconcepts.buycart.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    object HomeScreen: BottomNavItem(R.drawable.ic_home, HomeScreenDestination)
    object SearchScreen: BottomNavItem(R.drawable.ic_search, SearchScreenDestination)
    object CartScreen: BottomNavItem(R.drawable.ic_cart, CartScreenDestination)
    object WishListScreen: BottomNavItem(R.drawable.ic_wishlist, WishListScreenDestination)
    object ProfileScreen: BottomNavItem(R.drawable.ic_profile, ProfileScreenDestination)
}

fun bottomNavItems(): List<BottomNavItem> {
    return listOf(
        BottomNavItem.HomeScreen,
        BottomNavItem.SearchScreen,
        BottomNavItem.WishListScreen,
        BottomNavItem.CartScreen,
        BottomNavItem.ProfileScreen,
    )
}


@Composable
@Preview
fun CustomBottomNavBar(
    modifier: Modifier = Modifier,
    navItems: List<BottomNavItem> = bottomNavItems(),
    currentRoute: String?,
    onBottomNavItemSelected: (Destination) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp))
            .background(color = Color.White)
            .height(70.dp)
    ) {
        navItems.forEach { navItem ->
            val selectedNavItem = navItem.destination.route == currentRoute
            val selectedColor = if (selectedNavItem) primaryColor else disableColor

            Icon(
                painter = painterResource(id = navItem.iconRes),
                contentDescription = null,
                tint = selectedColor,
                modifier = Modifier.clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    if (!selectedNavItem) {
                        onBottomNavItemSelected(navItem.destination)
                    }
                }
            )
        }
    }
}