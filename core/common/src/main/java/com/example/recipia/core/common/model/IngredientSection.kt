package com.example.recipia.core.common.model

import kotlinx.serialization.Serializable

@Serializable
data class IngredientSection(
    val title: String? = null,
    val ingredientsList: List<Ingredient>,
)