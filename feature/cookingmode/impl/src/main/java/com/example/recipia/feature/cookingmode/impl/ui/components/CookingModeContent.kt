package com.example.recipia.feature.cookingmode.impl.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.recipia.feature.cookingmode.impl.ui.CookingModeEvent
import com.example.recipia.feature.cookingmode.impl.ui.CookingModeState

@Composable
fun CookingModeContent(
    state: CookingModeState.Success,
    event: (CookingModeEvent) -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = state.recipeId)
    }
}

@Preview(showBackground = true)
@Composable
private fun CookingModeContentPreview() {
    CookingModeContent(
        state = CookingModeState.Success(),
        event = {}
    )
}
