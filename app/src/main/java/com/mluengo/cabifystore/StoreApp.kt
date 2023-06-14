package com.mluengo.cabifystore

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.mluengo.cabifystore.navigation.StoreNavHost
import com.mluengo.cabifystore.navigation.storeTabRowScreens
import com.mluengo.cabifystore.ui.StoreAppState
import com.mluengo.cabifystore.ui.components.StoreNavbar
import com.mluengo.cabifystore.ui.components.StoreTopAppBar
import com.mluengo.cabifystore.ui.rememberStoreAppState
import com.mluengo.cabifystore.ui.theme.CabifyStoreTheme

@Preview("NavigationBar", showBackground = true)
@Composable
fun StoreApp(
    appState: StoreAppState = rememberStoreAppState(),
) {
    CabifyStoreTheme {
        Scaffold(
            containerColor = Color.Transparent,
            bottomBar = {
                StoreNavbar(
                    allScreens = storeTabRowScreens,
                    currentScreen = appState.currentDestination,
                    onNavigateToDestination = appState::navigateToTopLevelDestination
                )
            }
        ) { padding ->
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                StoreTopAppBar()
                StoreNavHost(appState)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    StoreApp()
}

