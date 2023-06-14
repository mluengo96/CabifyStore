package com.mluengo.cabifystore.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.mluengo.cabifystore.navigation.cart.cartScreen
import com.mluengo.cabifystore.navigation.shop.shopNavigationRoute
import com.mluengo.cabifystore.navigation.shop.shopScreen
import com.mluengo.cabifystore.ui.StoreAppState

@Composable
fun StoreNavHost(
    appState: StoreAppState,
    modifier: Modifier = Modifier,
    startDestination: String = shopNavigationRoute
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        shopScreen()
        cartScreen()
    }
}