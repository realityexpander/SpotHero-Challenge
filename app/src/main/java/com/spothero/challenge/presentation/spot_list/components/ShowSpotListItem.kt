package com.spothero.challenge.presentation.spot_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.spothero.challenge.common.Constants.PHOTO_LOCATION
import com.spothero.challenge.domain.model.Spot
import com.spothero.challenge.presentation.ui.ScreenNav
import java.text.NumberFormat

@Composable
fun ShowSpotListItem(
    navController: NavController,
    spot: Spot,
    dollarNumberFormat: NumberFormat
) {
    Column(
        modifier = Modifier
            .clickable(
                onClick = {
                    navController.navigate(
                        ScreenNav.SpotDetailScreen.route + "/${spot.id}"
                    )
                })
            .fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = PHOTO_LOCATION + spot.facilityPhoto,
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