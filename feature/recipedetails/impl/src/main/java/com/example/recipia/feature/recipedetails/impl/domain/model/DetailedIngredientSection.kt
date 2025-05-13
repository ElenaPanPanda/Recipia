package com.example.recipia.feature.recipedetails.impl.domain.model

import kotlinx.serialization.Serializable

@Serializable
sealed interface DetailedIngredientSection {
    @Serializable
    data class Group(
        val title: String,
        val items: List<DetailedIngredient>
    ) : DetailedIngredientSection

    @Serializable
    data class Ungrouped(
        val items: List<DetailedIngredient>
    ) : DetailedIngredientSection
}