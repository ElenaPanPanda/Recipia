package com.example.recipia.core.network.dto

import com.example.recipia.core.common.model.FullRecipe
import kotlinx.serialization.Serializable

@Serializable
data class FullRecipeDto(
    val recipe: FullRecipe,
    val message: String,
    val success: Boolean
)