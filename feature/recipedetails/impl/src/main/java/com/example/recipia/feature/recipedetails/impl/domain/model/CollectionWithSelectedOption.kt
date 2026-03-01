package com.example.recipia.feature.recipedetails.impl.domain.model

import com.example.recipia.core.common.model.ShortRecipe
import com.example.recipia.core.ui.model.PlaceholderColor

data class CollectionWithSelectedOption(
    val collectionId: String,
    val collectionName: String,
    val recipes: List<ShortRecipe>,
    val isSelected: Boolean = false,
    val collectionColor: PlaceholderColor = PlaceholderColor.entries.random(),
)