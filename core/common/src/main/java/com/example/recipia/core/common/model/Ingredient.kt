package com.example.recipia.core.common.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Ingredient(
    @SerialName("amount") val amount: String,
    @SerialName("ingredient") val name: String,
)