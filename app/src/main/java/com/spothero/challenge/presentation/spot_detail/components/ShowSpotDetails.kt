package com.spothero.challenge.presentation.spot_detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.spothero.challenge.domain.model.Spot

@Composable
fun ShowSpotDetails(spot: Spot) {
    Column() {
        AsyncImage(
            model = "file:///android_asset/" + spot.facilityPhoto,
            contentScale = ContentScale.Fit,
            contentDescription = "Facility Photo",
            modifier = Modifier
                .fillMaxWidth()
        )
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = AnnotatedString(
                    spot.address.street,
                    SpanStyle()
                ),
                style = MaterialTheme.typography.body2.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                ),
                maxLines = 1,
            )
            Text(
                text = spot.distance,
                style = MaterialTheme.typography.body2.copy(
                    fontSize = 16.sp
                ),
                maxLines = 1,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = spot.description,
                style = MaterialTheme.typography.body2.copy(
                    fontSize = 16.sp
                ),
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}