package com.example.recipia.feature.recipedetails.impl.data.dto

import com.example.recipia.core.common.model.Ingredient
import com.example.recipia.core.common.model.RecipeCategory
import com.example.recipia.core.ui.model.PlaceholderColor
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetRecipeResponse(
    val recipe: RecipeResponse,
    val message: String,
    val success: Boolean
) {
    @Serializable
    data class RecipeResponse(
        @SerialName("id") val id: String,
        @SerialName("title") val title: String,
        @SerialName("rating") val rating: Int,
        @SerialName("imageUrl") val imageUrl: String,
        @SerialName("placeholderColor") val placeholderColor: PlaceholderColor,
        @SerialName("ingredients") val ingredients: List<Ingredient>,
        @SerialName("rawCategories") val rawCategories: List<RecipeCategory>,
        @SerialName("instructions") val instructions: String,
    )
}