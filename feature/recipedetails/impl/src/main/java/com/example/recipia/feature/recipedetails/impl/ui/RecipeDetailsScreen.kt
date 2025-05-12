package com.example.recipia.feature.recipedetails.impl.ui

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.recipia.core.ui.components.ErrorScreen
import com.example.recipia.core.ui.components.LoadingScreen
import com.example.recipia.feature.recipedetails.impl.ui.components.RecipeDetailsContent

@Composable
fun RecipeDetailsScreen(
    recipeId: String,
    viewModel: RecipeDetailsViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val event: (RecipeDetailsEvent) -> Unit = viewModel::obtainEvent
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                is RecipeDetailsEffect.ShowSnackBar -> snackbarHostState.showSnackbar(effect.message)
            }
        }
    }

    when (state) {
        is RecipeDetailsState.Loading -> LoadingScreen()
        is RecipeDetailsState.Error -> ErrorScreen((state as RecipeDetailsState.Error).message)
        is RecipeDetailsState.Success -> RecipeDetailsContent(
            state = state as RecipeDetailsState.Success,
            event = event,
            snackbarHostState = snackbarHostState,
        )
    }
}