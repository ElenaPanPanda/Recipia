package com.example.recipia.feature.recipedetails.impl.ui.managers

import com.example.recipia.feature.recipedetails.impl.domain.usecase.AdjustRecipeRatingUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class RecipeDetailsEditManager @Inject constructor(
    private val adjustRecipeRatingUseCase: AdjustRecipeRatingUseCase
) {

    fun onEditClick(recipeId: String) {
        // Handle edit click logic
    }

    fun submitRating(recipeId: String, newRating: Float, scope: CoroutineScope) {
        scope.launch {
            try {
                adjustRecipeRatingUseCase.adjustRating(recipeId, newRating)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
