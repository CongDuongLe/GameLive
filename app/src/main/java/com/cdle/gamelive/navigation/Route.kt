package com.cdle.gamelive.navigation

sealed class RootScreen(val route: String) {
    object Home : RootScreen("home_root")
    object Search : RootScreen("search_root")
    object Anime : RootScreen("anime_root")
}

sealed class LeafScreen(val route: String) {
    object Home : LeafScreen("home")
    object Search : LeafScreen("search")
    object Anime : LeafScreen("anime")
    object HomeDetail : LeafScreen("home_detail/{gameId}") {
        fun createRoute(gameId: Int): String {
            return "home_detail/$gameId"
        }
    }
}
