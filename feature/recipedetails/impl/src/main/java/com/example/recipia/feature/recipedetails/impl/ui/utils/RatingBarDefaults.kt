package com.example.recipia.feature.recipedetails.impl.ui.utils

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.recipia.core.ui.icons.Icons
import com.example.recipia.core.ui.theme.GoldOrange
import com.example.recipia.core.ui.theme.StarEmpty

object RatingBarDefaults {
    @Composable
    fun UnratedContent(color: Color = StarEmpty) {
        Icon(
            tint = color,
            imageVector = ImageVector.vectorResource(Icons.starFilled),
            modifier = Modifier.fillMaxSize(),
            contentDescription = "Unrated Star"
        )
    }

    @Composable
    fun RatedContent(color: Color = GoldOrange) {
        Icon(
            tint = color,
            imageVector = ImageVector.vectorResource(Icons.starFilled),
            modifier = Modifier.fillMaxSize(),
            contentDescription = "Rated Star"
        )
    }
}