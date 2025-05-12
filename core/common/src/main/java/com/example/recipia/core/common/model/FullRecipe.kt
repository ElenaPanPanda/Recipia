package com.example.recipia.core.common.model

import com.example.recipia.core.ui.model.PlaceholderColor
import kotlinx.serialization.Serializable

@Serializable
data class FullRecipe(
    val id: String,
    val title: String,
    val rating: Int,
    val imageUrl: String,
    val placeholderColor: PlaceholderColor,
    val ingredients: List<Ingredient>,
    val rawCategories: List<RecipeCategory>,
    val instructions: String,
)
