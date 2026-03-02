package com.example.recipia.feature.cookingmode.impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.recipia.core.ui.components.ErrorScreen
import com.example.recipia.core.ui.components.LoadingScreen
import com.example.recipia.feature.cookingmode.impl.ui.components.CookingModeContent

@Composable
fun CookingModeScreen(
    viewModel: CookingModeViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val event: (CookingModeEvent) -> Unit = viewModel::obtainEvent

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect { effect ->
            // handle effects when added
        }
    }

    when (val currentState = state) {
        is CookingModeState.Loading -> LoadingScreen()
        is CookingModeState.Error -> ErrorScreen(currentState.message)
        is CookingModeState.Success -> CookingModeContent(
            state = currentState,
            event = event,
        )
    }
}
