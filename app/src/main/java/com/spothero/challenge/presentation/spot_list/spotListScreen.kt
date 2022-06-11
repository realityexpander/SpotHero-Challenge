package com.spothero.challenge.presentation.spot_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.spothero.challenge.presentation.ui.ScreenNav
import java.text.NumberFormat
import java.util.*

@Composable
fun SpotListScreen(
    navController: NavController,
    viewModel: SpotListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val dollarNumberFormat = NumberFormat.getCurrencyInstance(Locale.US)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Spots")
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
                elevation = 10.dp
            )
        }, content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        val sortedSpotsByPrice = state.spots.sortedBy { it.price }

                        items(sortedSpotsByPrice) { spot ->
                            Column(modifier = Modifier.clickable(
                                onClick = {
                                    navController.navigate(
                                        ScreenNav.SpotDetailScreen.route + "/${spot.id}"
                                    )
                                })
                                .fillMaxWidth()
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    AsyncImage(
                                        model = "file:///android_asset/" + spot.facilityPhoto,
                                        contentScale = ContentScale.Fit,
                                        contentDescription = "Facility Photo",
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .padding(16.dp)
                                            .height(72.dp)
                                    )
                                    Column {
                                        Text(
                                            text = AnnotatedString(
                                                spot.address.street,
                                                SpanStyle()
                                            ),
                                            style = MaterialTheme.typography.body2,
                                            maxLines = 1,
                                        )
                                        Text(
                                            text = AnnotatedString(spot.distance, SpanStyle()),
                                            style = MaterialTheme.typography.body2,
                                            maxLines = 1,
                                        )
                                        Text(
                                            text = AnnotatedString(
                                                dollarNumberFormat.format(spot.price / 100.0),
                                                SpanStyle()
                                            ),
                                            style = MaterialTheme.typography.body2,
                                            maxLines = 1,
                                        )
                                    }
                                }
                            }
                            Divider()
                        }
                    }
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
        })
}
