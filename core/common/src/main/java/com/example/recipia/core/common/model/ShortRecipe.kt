package com.example.recipia.core.common.model

import com.example.recipia.core.ui.model.PlaceholderColor

data class ShortRecipe(
    val id: String,
    val title: String,
    val rating: Int,
    val imageUrl: String,
    val placeholderColor: PlaceholderColor,
    val rawCategories: List<RecipeCategory> = emptyList(),
)