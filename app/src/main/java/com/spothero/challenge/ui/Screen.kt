package com.spothero.challenge.ui

// Defines the names of the routes
sealed class Screen( val route: String) {
    object SpotListScreen : Screen("spot_list_screen")
    object SpotDetailScreen : Screen("spot_detail_screen")
}
