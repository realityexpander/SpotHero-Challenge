package com.spothero.challenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.realityexpander.cryptoapp.presentation.ui.theme.SpotHeroTheme
import com.spothero.challenge.presentation.spot_list.SpotListScreen
import com.spothero.challenge.presentation.spot_list.SpotListViewModel
import com.spothero.challenge.ui.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

//        val spotListViewModel = SpotListViewModel(SpotHeroApi(this))

        setContent {
            SpotHeroTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.SpotListScreen.route
                    ) {
                        composable(
                            route = Screen.SpotListScreen.route
                        ) {
                            SpotListScreen(navController)
                        }
//                        composable(
//                            route = Screen.CoinInfoScreen.route + "/{coinId}"
//                        ) {
//                            CoinInfoScreen(navController)
//                        }
                    }
                }
            }
        }
    }
}
