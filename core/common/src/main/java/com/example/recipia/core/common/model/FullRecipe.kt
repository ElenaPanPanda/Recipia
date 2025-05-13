package com.example.recipia.core.common.model

import com.example.recipia.core.ui.model.PlaceholderColor
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FullRecipe(
    @SerialName("id") val id: String,
    @SerialName("title") val title: String,
    @SerialName("rating") val rating: Int,
    @SerialName("imageUrl") val imageUrl: String,
    @SerialName("placeholderColor") val placeholderColor: PlaceholderColor,
    @SerialName("ingredients") val ingredients: List<IngredientSection>,
    @SerialName("rawCategories") val rawCategories: List<RecipeCategory>,
    @SerialName("instructions") val instructions: String,
)
