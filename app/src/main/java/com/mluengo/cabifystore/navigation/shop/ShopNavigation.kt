package com.mluengo.cabifystore.navigation.shop

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mluengo.cabifystore.ui.screens.shop.ShopRoute
import com.mluengo.cabifystore.ui.screens.shop.ShopViewModel

const val shopNavigationRoute = "shop_route"

fun NavController.navigateShop(navOptions: NavOptions? = null) {
    this.navigate(shopNavigationRoute, navOptions)
}

fun NavGraphBuilder.shopScreen() {
    composable(route = shopNavigationRoute) {
        val shopViewModel = hiltViewModel<ShopViewModel>()
        ShopRoute(
            shopState = shopViewModel.shopState,
            shopViewModel = shopViewModel,
        )
    }
}