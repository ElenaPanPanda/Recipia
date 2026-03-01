package com.examplerecipia.feature.groceries.impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.recipia.core.ui.components.ErrorScreen
import com.example.recipia.core.ui.components.LoadingScreen
import com.examplerecipia.feature.groceries.impl.ui.components.GroceriesContent

@Composable
fun GroceriesScreen(
    viewModel: GroceriesViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val event: (GroceriesEvent) -> Unit = viewModel::obtainEvent

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                is GroceriesEffect.ShowSnackBar -> {}
            }
        }
    }

    when (state) {
        is GroceriesState.Loading -> LoadingScreen()
        is GroceriesState.Error -> ErrorScreen((state as GroceriesState.Error).message)
        is GroceriesState.Success -> GroceriesContent(
            state = state as GroceriesState.Success,
            event = event,
        )
    }
}