package com.example.recipia.core.common.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IngredientSection(
    @SerialName("title") val title: String? = null,
    @SerialName("ingredientsList") val ingredientsList: List<Ingredient>,
)