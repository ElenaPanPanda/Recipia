package com.example.recipia.feature.recipedetails.impl.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class DetailedIngredientSection(
    val title: String? = null,
    val ingredientsList: List<DetailedIngredient>,
)