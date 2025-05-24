package com.example.recipia.feature.recipedetails.impl.domain.model

import com.example.recipia.core.common.model.RecipeCategory
import com.example.recipia.core.ui.model.PlaceholderColor
import kotlinx.serialization.Serializable

@Serializable
data class DetailedRecipe(
    val id: String,
    val title: String,
    val rating: Int,
    val imageUrl: String,
    val placeholderColor: PlaceholderColor,
    val ingredients: List<DetailedIngredientSection>,
    val rawCategories: List<RecipeCategory>,
    val instructions: String,
)