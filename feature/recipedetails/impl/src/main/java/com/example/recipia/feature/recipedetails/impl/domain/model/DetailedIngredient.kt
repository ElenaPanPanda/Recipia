package com.example.recipia.feature.recipedetails.impl.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class DetailedIngredient(
    val amount: String,
    val ingredient: String,
    val addedToList: Boolean,
)