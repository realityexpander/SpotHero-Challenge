package com.spothero.challenge.presentation.spot_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.spothero.challenge.domain.model.Spot
import com.spothero.challenge.presentation.spot_detail.components.ShowBottomBar
import com.spothero.challenge.presentation.spot_detail.components.ShowSpotDetails
import com.spothero.challenge.presentation.spot_list.SpotDetailState
import java.text.NumberFormat
import java.util.*

@Composable
fun SpotDetailScreen(
    navController: NavController,
    viewModel: SpotDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val dollarNumberFormat = NumberFormat.getCurrencyInstance(Locale.US)
    val spot = state.spot

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Spot Details")
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.navigateUp() },
                    ) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
                elevation = 10.dp
            )
        }, content = {
            Box(modifier = Modifier.fillMaxSize()) {
                if(!state.isLoading && !state.isError && spot != null) {
                    ShowSpotDetails(spot)
                }

                if (state.isError) {
                    Text(
                        text = state.errorMessage,
                        color = MaterialTheme.colors.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .align(Alignment.Center)
                    )
                }
                if (state.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.fillMaxWidth()
                    .height(68.dp),
                backgroundColor = MaterialTheme.colors.onPrimary,
                contentColor = MaterialTheme.colors.onPrimary,
                elevation = 0.dp
            ) {
                ShowBottomBar(state, spot, dollarNumberFormat)
            }
        }
    )
}
