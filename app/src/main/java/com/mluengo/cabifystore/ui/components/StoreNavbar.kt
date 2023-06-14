package com.mluengo.cabifystore.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.mluengo.cabifystore.navigation.StoreDestination
import com.mluengo.cabifystore.ui.icons.Icon

@Composable
fun StoreNavbar(
    allScreens: List<StoreDestination>,
    currentScreen: NavDestination?,
    onNavigateToDestination: (StoreDestination) -> Unit,
) {
    NavigationBar {
        allScreens.forEach { screen ->
            val selected = currentScreen.isTopLevelDestinationInHierarchy(screen)
            NavigationBarItem(
                onClick = { onNavigateToDestination(screen) },
                icon = {
                    when(val icon = screen.icon) {
                        is Icon.ImageVectorIcon -> Icon(
                            imageVector = icon.imageVector,
                            contentDescription = null
                        )

                        is Icon.DrawableResourceIcon -> Icon(
                            painter = painterResource(id = icon.id),
                            contentDescription = null
                        )
                    }
                },
                label = { Text(text = screen.route) },
                selected =  selected
            )
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: StoreDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.route, true) ?: false
    } ?: false