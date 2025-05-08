package com.example.recipia.core.common.model

import kotlinx.serialization.Serializable

@Serializable
data class FullRecipe(
    val id: String,
    val title: String,
    val isFavorite: Boolean,
    val imageUrl: String,
    val ingredients: List<Ingredient>,
    val rawCategories: List<RecipeCategory>,
    val instructions: String,
)
