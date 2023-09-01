package com.example.testzara.framework.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.testzara.framework.screens.DetailScreen
import com.example.testzara.framework.screens.MainScreen

@Composable
fun AppNavigation(viewModel: ViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.MainActivity.route) {
        composable(route = AppScreens.MainActivity.route) {
            MainScreen(navController = navController, viewModel)
        }
        composable(
            route = AppScreens.DetailActivity.route + "/{characterJson}",
            arguments = listOf(navArgument(name = "characterJson") {
                type = NavType.StringType
            })
        ) {
            DetailScreen(navController = navController, it.arguments?.getString("characterJson"))
        }
    }
}