package com.cdle.gamelive.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.cdle.gamelive.domain.Game
import com.cdle.gamelive.presentation.screens.detail.DetailScreen
import com.cdle.gamelive.presentation.screens.home.HomeScreen
import com.cdle.gamelive.presentation.screens.home.HomeViewModel
import com.cdle.gamelive.presentation.screens.search.SearchScreen
import com.cdle.gamelive.presentation.screens.anime.AnimeScreen
import com.cdle.gamelive.presentation.screens.anime.AnimeViewModel


@Composable
fun AppNavGraph(
    navController: NavHostController,
) {


    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = RootScreen.Home.route
    ) {
        addHomeRoute(navController, context)
        addSearchRoute(navController)
        addAnimeRoute(navController, context)
    }
}

//home navigation
private fun NavGraphBuilder.addHomeRoute(navController: NavController, context: Context) {
    navigation(
        route = RootScreen.Home.route,
        startDestination = LeafScreen.Home.route
    ) {
        showHome(navController)
        showHomeDetail(navController)
    }
}

private fun NavGraphBuilder.showHome(navController: NavController) {
    composable(route = LeafScreen.Home.route) {
        val homeViewModel = hiltViewModel<HomeViewModel>()
        val games = homeViewModel.gameFlow.collectAsLazyPagingItems()
        HomeScreen(
            navController = navController,
            games = games
        )
    }
}

private fun NavGraphBuilder.showHomeDetail(navController: NavController) {
    composable(route = LeafScreen.HomeDetail.route) {

        val gameId = it.arguments?.getString("gameId")?.toInt() ?: 0

        DetailScreen(
            gameId = gameId,
            navController = navController,
        )
    }
}
//end of home navigation

//search navigation
private fun NavGraphBuilder.addSearchRoute(navController: NavController) {
    navigation(
        route = RootScreen.Search.route,
        startDestination = LeafScreen.Search.route
    ) {
        showSearch(navController)
    }
}

private fun NavGraphBuilder.showSearch(navController: NavController) {
    composable(route = LeafScreen.Search.route) {
        SearchScreen(
            navController = navController,
        )
    }
}
//end of search navigation

//Anime navigation
private fun NavGraphBuilder.addAnimeRoute(navController: NavController, context: Context) {
    navigation(
        route = RootScreen.Anime.route,
        startDestination = LeafScreen.Anime.route
    ) {
        showAnime(navController, context)
    }
}

private fun NavGraphBuilder.showAnime(navController: NavController, context: Context) {
    composable(route = LeafScreen.Anime.route) {
        val animeViewModel = hiltViewModel<AnimeViewModel>()
        val animeState = animeViewModel.animeFlow.collectAsLazyPagingItems()
        AnimeScreen(
            navController = navController,
            context = context,
            animeState = animeState
        )
    }
}
//end of favorites navigation
