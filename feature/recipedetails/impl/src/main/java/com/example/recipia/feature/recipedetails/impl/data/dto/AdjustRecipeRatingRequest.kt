package com.example.recipia.feature.recipedetails.impl.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class AdjustRecipeRatingRequest(
    @SerialName("rating") val rating: Float
)
