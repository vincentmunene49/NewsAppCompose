package com.loc.newsapp.ui

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimensions(
    val default: Dp = 0.dp,
    val extraSmall: Dp = 2.dp,
    val small: Dp = 4.dp,
    val medium: Dp = 8.dp,
    val medium2: Dp=11.dp,
    val large: Dp = 16.dp,
    val extraLarge: Dp = 24.dp,
    val cardSize:Dp = 96.dp,
    val smallSpacing:Dp = 6.dp
)

val LocalDimensions = compositionLocalOf { Dimensions() }