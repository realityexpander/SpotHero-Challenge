package com.spothero.challenge.presentation.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

private val DarkColorPalette = darkColors(
    primary = spotHeroPrimary,
    background = spotHeroPrimaryVariant,
    onBackground = spotHeroText,
    onPrimary = DarkGray,
)

private val LightColorPalette = lightColors(
    primary = spotHeroPrimary,
    primaryVariant = spotHeroPrimaryVariant,
    onPrimary = spotHeroText,
    secondary = Color.Red,
    background = spotHeroText,
    onBackground = Color.Black,
)

@Composable
fun SpotHeroTheme(darkTheme: Boolean = false, content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography.copy(
            body2 = MaterialTheme.typography.body2.copy(fontSize = 16.sp)
        ),
        shapes = Shapes,
        content = content
    )
}