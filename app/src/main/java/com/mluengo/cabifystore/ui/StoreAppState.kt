package com.mluengo.cabifystore.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.mluengo.cabifystore.navigation.Cart
import com.mluengo.cabifystore.navigation.Shop
import com.mluengo.cabifystore.navigation.StoreDestination
import com.mluengo.cabifystore.navigation.cart.navigateCart
import com.mluengo.cabifystore.navigation.shop.navigateShop

@Composable
fun rememberStoreAppState(
    navController: NavHostController = rememberNavController(),
): StoreAppState {
    return remember(
        navController
    ) {
        StoreAppState(
            navController
        )
    }
}

class StoreAppState(
    val navController: NavHostController,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    fun navigateToTopLevelDestination(storeDestination: StoreDestination) {
        val destinationNavOptions = navOptions {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }

        when (storeDestination) {
            Shop -> navController.navigateShop(destinationNavOptions)
            Cart -> navController.navigateCart(destinationNavOptions)
        }
    }
}