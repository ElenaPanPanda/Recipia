package com.example.recipia.feature.recipedetails.impl.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.recipia.core.ui.icons.Icons
import com.example.recipia.core.ui.theme.snowWhite
import com.example.recipia.feature.recipedetails.impl.ui.RecipeDetailsState

@Composable
fun LargeImage(
    state: RecipeDetailsState.Success
) {
    var displayFallback by remember(state.recipe.imageUrl) {
        mutableStateOf(
            state.recipe.imageUrl.isBlank()
        )
    }

    if (displayFallback) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(state.recipe.placeholderColor.color),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(Icons.recipes),
                contentDescription = null,
                tint = snowWhite,
                modifier = Modifier.size(64.dp),
            )
        }
    } else {
        AsyncImage(
            model = state.recipe.imageUrl,
            contentDescription = state.recipe.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Crop,
            onError = { displayFallback = true },
        )
    }
}