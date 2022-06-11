package com.spothero.challenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.spothero.challenge.common.Constants.PARAM_SPOT_ID
import com.spothero.challenge.presentation.ui.theme.SpotHeroTheme
import com.spothero.challenge.presentation.spot_detail.SpotDetailScreen
import com.spothero.challenge.presentation.spot_list.SpotListScreen
import com.spothero.challenge.presentation.ui.ScreenNav
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SpotHeroTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = ScreenNav.SpotListScreen.route
                    ) {
                        composable(
                            route = ScreenNav.SpotListScreen.route
                        ) {
                            SpotListScreen(navController)
                        }
                        composable(
                            route = ScreenNav.SpotDetailScreen.route + "/{$PARAM_SPOT_ID}",
                            arguments = listOf(
                                navArgument(PARAM_SPOT_ID) { type = NavType.IntType }
                            )
                        ) {
                            SpotDetailScreen(navController)
                        }
                    }
                }
            }
        }
    }
}
