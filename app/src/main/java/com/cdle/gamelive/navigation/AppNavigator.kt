package com.cdle.gamelive.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.cdle.gamelive.R

@Composable
fun AppNavigator() {
    val navController = rememberNavController()
    val currentSelectedScreen by navController.currentScreenAsState()
    val currentRoute by navController.currentRouteAsState()
    /***
     * Define a list of routes if you wanna show it for specific routes.
     * For example I wanna show the app bar only for these routes
    val bottomNavRoutes = listOf(
    LeafScreen.Home.route,
    LeafScreen.Search.route,
    LeafScreen.Favorites.route,
    LeafScreen.Profile.route,
    )
     ***/
    Scaffold(
        bottomBar = {
            BottomNavBar(navController = navController, currentSelectedScreen = currentSelectedScreen)
            /***
             * Use [BottomNavBar] Like this if you wanna show it for specific routes
            if (currentRoute == null || bottomNavRoutes.contains(currentRoute)) {
            BottomNavBar(navController = navController, currentSelectedScreen = currentSelectedScreen)
            }
             ***/
        },
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            AppNavGraph(navController = navController)
        }
    }
}

@Composable
private fun BottomNavBar(
    navController: NavController,
    currentSelectedScreen: RootScreen
) {
    NavigationBar {
        NavigationBarItem(
            selected = currentSelectedScreen == RootScreen.Home,
            onClick = { navController.navigateToRootScreen(RootScreen.Home) },
            alwaysShowLabel = true,
            label = {
                Text(text = "Hot games")
            },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_home),
                    contentDescription = "home icon",
                    modifier = Modifier.size(24.dp)
                )
            }
        )
        NavigationBarItem(
            selected = currentSelectedScreen == RootScreen.Anime,
            onClick = { navController.navigateToRootScreen(RootScreen.Anime) },
            alwaysShowLabel = true,
            label = {
                Text(text = "Anime")
            },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_microsoft),
                    contentDescription = "Anime icon",
                    modifier = Modifier.size(24.dp)
                )
            }
        )
        NavigationBarItem(
            selected = currentSelectedScreen == RootScreen.Search,
            onClick = { navController.navigateToRootScreen(RootScreen.Search) },
            alwaysShowLabel = true,
            label = {
                Text(text = "Search")
            },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "search icon",
                    modifier = Modifier.size(24.dp)
                )
            }
        )
    }
}

@Stable
@Composable
private fun NavController.currentScreenAsState(): State<RootScreen> {
    val selectedItem = remember { mutableStateOf<RootScreen>(RootScreen.Home) }
    DisposableEffect(key1 = this) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            when {
                destination.hierarchy.any { it.route == RootScreen.Home.route } -> {
                    selectedItem.value = RootScreen.Home
                }

                destination.hierarchy.any { it.route == RootScreen.Search.route } -> {
                    selectedItem.value = RootScreen.Search
                }

                destination.hierarchy.any { it.route == RootScreen.Anime.route } -> {
                    selectedItem.value = RootScreen.Anime
                }
            }

        }
        addOnDestinationChangedListener(listener)
        onDispose {
            removeOnDestinationChangedListener(listener)
        }
    }
    return selectedItem
}

@Stable
@Composable
private fun NavController.currentRouteAsState(): State<String?> {
    val selectedItem = remember { mutableStateOf<String?>(null) }
    DisposableEffect(this) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            selectedItem.value = destination.route
        }
        addOnDestinationChangedListener(listener)

        onDispose {
            removeOnDestinationChangedListener(listener)
        }
    }
    return selectedItem
}

private fun NavController.navigateToRootScreen(rootScreen: RootScreen) {
    navigate(rootScreen.route) {
        launchSingleTop = true
        restoreState = true
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
    }
}
