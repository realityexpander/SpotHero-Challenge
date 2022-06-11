package com.spothero.challenge.presentation.spot_detail.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.spothero.challenge.domain.model.Spot
import com.spothero.challenge.presentation.spot_list.SpotDetailState
import java.text.NumberFormat
import java.util.*

@Composable
fun ShowBottomBar(
    state: SpotDetailState,
    spot: Spot?,
    dollarNumberFormat: NumberFormat
) {
    if (!state.isError && !state.isLoading && spot != null) {
        Button(
            onClick = {
                // viewModel.onBookSpotClicked()
            },
            shape = RoundedCornerShape(32.dp),
            modifier = Modifier
                .padding(top = 8.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Text(
                text = AnnotatedString(
                    "Book for ${dollarNumberFormat.format(spot.price / 100.0)}"
                        .uppercase(Locale.US),
                    SpanStyle()
                ),
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Center,
            )
        }
    }
}