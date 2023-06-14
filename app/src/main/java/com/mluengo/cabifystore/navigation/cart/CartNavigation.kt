package com.mluengo.cabifystore.navigation.cart

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mluengo.cabifystore.ui.screens.cart.CartRoute

const val cartNavigationRoute = "cart_route"

fun NavController.navigateCart(navOptions: NavOptions? = null) {
    this.navigate(cartNavigationRoute, navOptions)
}

fun NavGraphBuilder.cartScreen() {
    composable(route = cartNavigationRoute) {
        CartRoute()
    }
}