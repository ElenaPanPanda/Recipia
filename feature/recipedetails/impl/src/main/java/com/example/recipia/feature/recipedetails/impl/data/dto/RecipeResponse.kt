package com.example.recipia.feature.recipedetails.impl.data.dto

import com.example.recipia.core.common.model.FullRecipe
import kotlinx.serialization.Serializable

@Serializable
data class GetRecipeResponse(
    val recipe: FullRecipe,
    val message: String,
    val success: Boolean
)