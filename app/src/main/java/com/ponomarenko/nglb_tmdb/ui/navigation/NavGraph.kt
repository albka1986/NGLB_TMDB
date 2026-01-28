package com.ponomarenko.nglb_tmdb.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ponomarenko.nglb_tmdb.ui.screen.MainScreen
import com.ponomarenko.nglb_tmdb.ui.screen.MovieDetailsScreen

const val MOVIE_ID = "movieId"
private const val ANIMATION_DURATION = 300

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Main.route,
    ) {
        composable(
            route = Screen.Main.route,
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -it },
                    animationSpec = tween(ANIMATION_DURATION)
                )
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -it },
                    animationSpec = tween(ANIMATION_DURATION)
                )
            }
        ) {
            MainScreen(onMovieClick = {
                navController.navigate("${Screen.Details.route}/$it")
            })
        }
        composable(
            route = "${Screen.Details.route}/{$MOVIE_ID}",
            arguments = listOf(navArgument(MOVIE_ID) {
                type = NavType.IntType
            }),
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { it },
                    animationSpec = tween(ANIMATION_DURATION)
                )
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { it },
                    animationSpec = tween(ANIMATION_DURATION)
                )
            }
        ) {
            val movieId = it.arguments?.getInt(MOVIE_ID)
            require(movieId != null)
            MovieDetailsScreen(
                movieId = movieId,
                onUpClick = { navController.popBackStack() }
            )
        }
    }
}

sealed class Screen(val route: String) {
    data object Main : Screen("main")
    data object Details : Screen("details")
}
