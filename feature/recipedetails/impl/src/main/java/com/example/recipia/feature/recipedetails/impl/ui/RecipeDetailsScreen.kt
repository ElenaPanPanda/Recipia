package com.example.recipia.feature.recipedetails.impl.ui

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.recipia.core.ui.components.AppBottomSheet
import com.example.recipia.core.ui.components.ErrorScreen
import com.example.recipia.core.ui.components.LoadingScreen
import com.example.recipia.feature.recipedetails.impl.ui.bottomsheets.AddRecipeToCollectionBottomSheetContent
import com.example.recipia.feature.recipedetails.impl.ui.components.RecipeDetailsContent

@Composable
fun RecipeDetailsScreen(
    recipeId: String,
    onNavigateBack: () -> Unit,
    onNavigateToCookingMode: (String) -> Unit,
    viewModel: RecipeDetailsViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val event: (RecipeDetailsEvent) -> Unit = viewModel::obtainEvent
    val snackbarHostState = remember { SnackbarHostState() }

    var isBottomSheetVisible by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                is RecipeDetailsEffect.ShowSnackBar -> snackbarHostState.showSnackbar(effect.message)
                is RecipeDetailsEffect.OpenCollectionsBottomSheet -> isBottomSheetVisible = true
                is RecipeDetailsEffect.NavigateBack -> onNavigateBack()
                is RecipeDetailsEffect.NavigateToCookingMode -> onNavigateToCookingMode(effect.recipeId)
            }
        }
    }

    if (isBottomSheetVisible) {
        val currentState = state
        if (currentState !is RecipeDetailsState.Success) {
            isBottomSheetVisible = false
            return
        }

        val collections = currentState.collections
        if (collections == null) {
            isBottomSheetVisible = false
            return
        }

        AppBottomSheet(
            onDismiss = { isBottomSheetVisible = false },
            content = {
                AddRecipeToCollectionBottomSheetContent(
                    onDismiss = { isBottomSheetVisible = false },
                    collections = collections,
                    onCollectionSelectedChange = { collectionId ->
                        event(
                            RecipeDetailsEvent.OnCollectionSelectedChange(
                                collectionId
                            )
                        )
                    },
                    onNewCollectionValueChange = { newCollectionValue: String ->
                        event(
                            RecipeDetailsEvent.OnNewCollectionValueChange(
                                newCollectionValue
                            )
                        )
                    },
                    saveButtonEnabled = currentState.saveRecipeInCollectionButtonIsEnabled,
                    saveToCollectionOption = currentState.saveToCollectionOption,
                    onSave = {
                        isBottomSheetVisible = false
                        event(RecipeDetailsEvent.OnSaveToCollectionClicked)
                    },
                )
            }
        )
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