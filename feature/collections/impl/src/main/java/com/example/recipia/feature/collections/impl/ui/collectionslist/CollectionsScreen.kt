package com.example.recipia.feature.collections.impl.ui.collectionslist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.recipia.core.ui.components.ErrorScreen
import com.example.recipia.core.ui.components.LoadingScreen
import com.example.recipia.feature.collections.impl.ui.collectionslist.components.CollectionsContent

@Composable
fun CollectionsScreen(
    viewModel: CollectionsViewModel = hiltViewModel(),
    navigateToCollection: (String) -> Unit,
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val event: (CollectionsEvent) -> Unit = viewModel::obtainEvent

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                is CollectionsEffect.NavigateToCollection -> navigateToCollection(effect.collectionId)
            }
        }
    }

    when (state) {
        is CollectionsState.Loading -> LoadingScreen()
        is CollectionsState.Error -> ErrorScreen((state as CollectionsState.Error).message)
        is CollectionsState.Success -> CollectionsContent(
            state = state as CollectionsState.Success,
            event = event,
        )
    }
}