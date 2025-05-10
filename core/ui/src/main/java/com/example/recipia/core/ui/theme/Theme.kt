package com.example.recipia.core.ui.theme

import androidx.annotation.VisibleForTesting
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

val LocalTypography = staticCompositionLocalOf { AppTypography() }

@VisibleForTesting
val LightDefaultColorScheme = lightColorScheme(background = LighterBeige)

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = LightDefaultColorScheme
    val typography = AppTypography()

    CompositionLocalProvider(
        LocalTypography provides typography,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            content = content,
        )
    }
}