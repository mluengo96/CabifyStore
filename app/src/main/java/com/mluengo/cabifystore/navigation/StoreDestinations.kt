package com.mluengo.cabifystore.navigation

import androidx.compose.runtime.Composable
import com.mluengo.cabifystore.ui.icons.Icon
import com.mluengo.cabifystore.ui.icons.StoreIcons.LocalMall
import com.mluengo.cabifystore.ui.icons.StoreIcons.ShoppingCart

interface StoreDestination {
    val icon: Icon
    val route: String
    val screen: @Composable () -> Unit
}

object Shop : StoreDestination {
    override val icon = Icon.DrawableResourceIcon(LocalMall)
    override val route = "Shop"
    override val screen: () -> Unit
        get() = TODO("Not yet implemented")

}

object Cart : StoreDestination {
    override val icon = Icon.DrawableResourceIcon(ShoppingCart)
    override val route: String
        get() = "Cart"
    override val screen: () -> Unit
        get() = TODO("Not yet implemented")

}

// Screens to be displayed in the Navigation bar
val storeTabRowScreens = listOf(Shop, Cart)