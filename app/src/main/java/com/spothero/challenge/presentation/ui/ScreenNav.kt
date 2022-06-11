package com.spothero.challenge.presentation.ui

// Defines the names of the routes
sealed class ScreenNav(val route: String) {
    object SpotListScreen : ScreenNav("spot_list_screen")
    object SpotDetailScreen : ScreenNav("spot_detail_screen")
}
