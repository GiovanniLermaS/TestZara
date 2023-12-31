package com.example.testzara.framework.navigation

sealed class AppScreens(val route: String) {
    object MainActivity : AppScreens("main_screen")
    object DetailActivity : AppScreens("detail_screen")
}