package com.example.recipia.feature.recipedetails.impl.ui.managers

import com.example.recipia.core.common.model.UserCollectionOverview
import com.example.recipia.core.domain.collections.usecase.AddRecipeToCollectionUseCase
import com.example.recipia.core.domain.collections.usecase.CreateCollectionUseCase
import com.example.recipia.core.domain.collections.usecase.GetCollectionsOverviewsUseCase
import com.example.recipia.feature.recipedetails.impl.ui.RecipeDetailsEffect
import com.example.recipia.feature.recipedetails.impl.ui.RecipeDetailsState
import com.example.recipia.feature.recipedetails.impl.ui.SaveToCollectionOption
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class RecipeDetailsCollectionsManager @Inject constructor(
    private val getCollectionsUseCase: GetCollectionsOverviewsUseCase,
    private val createCollectionUseCase: CreateCollectionUseCase,
    private val addRecipeToCollectionUseCase: AddRecipeToCollectionUseCase
) {
    fun getCollectionsForBottomSheets(
        scope: CoroutineScope,
        updateState: (RecipeDetailsState.Success.() -> RecipeDetailsState.Success) -> Unit,
        emitEffect: suspend (RecipeDetailsEffect) -> Unit
    ) {
        scope.launch {
            /*
            try {
                val collections = getCollectionsUseCase.getCollections()
                updateState { copy(collections = collections) }
                emitEffect(RecipeDetailsEffect.OpenCollectionsBottomSheet)
            } catch (e: Exception) {
                // handle error
            }
            */

            updateState { copy(collections = COLLECTIONS) }
            emitEffect(RecipeDetailsEffect.OpenCollectionsBottomSheet)
        }
    }

    fun selectCollection(
        collectionId: String,
        updateState: (RecipeDetailsState.Success.() -> RecipeDetailsState.Success) -> Unit
    ) {
        updateState {
            val isDeselecting = selectedCollectionId == collectionId

            if (isDeselecting) {
                copy(
                    selectedCollectionId = null,
                    saveToCollectionOption = null,
                    saveRecipeInCollectionButtonIsEnabled = false
                )
            } else {
                copy(
                    selectedCollectionId = collectionId,
                    saveToCollectionOption = SaveToCollectionOption.Existing(collectionId),
                    saveRecipeInCollectionButtonIsEnabled = true
                )
            }
        }
    }

    fun changeNewCollectionValue(
        value: String,
        updateState: (RecipeDetailsState.Success.() -> RecipeDetailsState.Success) -> Unit
    ) {
        updateState {
            if (value.isBlank()) {
                copy(
                    saveToCollectionOption = null,
                    saveRecipeInCollectionButtonIsEnabled = false
                )
            } else {
                copy(
                    selectedCollectionId = null,
                    saveToCollectionOption = SaveToCollectionOption.New(value),
                    saveRecipeInCollectionButtonIsEnabled = true
                )
            }
        }
    }

    fun saveToCollection(
        recipeId: String,
        currentState: RecipeDetailsState.Success,
        emitEffect: suspend (RecipeDetailsEffect) -> Unit,
        scope: CoroutineScope,
    ) {
        scope.launch {
            // Temporary commented out while we do not have collections requests.
/*            when (val option = currentState.saveToCollectionOption) {
                is SaveToCollectionOption.New -> {
                    createCollectionUseCase.create(
                        collectionName = option.name,
                        recipeId = recipeId
                    )
                }
                is SaveToCollectionOption.Existing -> {
                    addRecipeToCollectionUseCase.add(
                        collectionId = option.id,
                        recipeId = recipeId
                    )
                }
                null -> {
                    Timber.e("SaveToCollectionOption is null")
                }
            }*/

            // TODO: show a snackBar "Recipe added to collection." And "collection" is a link to the collection.
            emitEffect(RecipeDetailsEffect.OpenCollectionsBottomSheet)
        }
    }

    companion object {
        val COLLECTIONS: List<UserCollectionOverview> = listOf(
            UserCollectionOverview(
                collectionId = "1",
                collectionName = "Collection 1",
                recipesAmount = 0
            ),
            UserCollectionOverview(
                collectionId = "2",
                collectionName = "Collection 2",
                recipesAmount = 1
            ),
            UserCollectionOverview(
                collectionId = "3",
                collectionName = "Collection 3",
                recipesAmount = 15
            ),
            UserCollectionOverview(
                collectionId = "4",
                collectionName = "Collection 4",
                recipesAmount = 100
            )
        )
    }
}