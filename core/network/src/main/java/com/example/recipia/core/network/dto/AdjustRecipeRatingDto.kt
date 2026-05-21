package com.example.recipia.core.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class AdjustRecipeRatingDto(
    @SerialName("rating") val rating: Float
)
