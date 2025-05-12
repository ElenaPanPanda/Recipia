package com.example.recipia.feature.recipedetails.impl.ui.utils

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

object RatingBarDefaults {
    @Composable
    fun UnratedContent(color: Color = Color.LightGray) {
        Icon(
            tint = color,
            imageVector = Icons.Rounded.Star,
            modifier = Modifier.fillMaxSize(),
            contentDescription = "Unrated Star"
        )
    }

    @Composable
    fun RatedContent(color: Color = Color(0xFFFFC107)) {
        Icon(
            tint = color,
            imageVector = Icons.Rounded.Star,
            modifier = Modifier.fillMaxSize(),
            contentDescription = "Rated Star"
        )
    }
}