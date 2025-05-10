package com.example.recipia.core.common.model

import kotlinx.serialization.Serializable

@Serializable
data class Ingredient(
    val amount: String,
    val ingredient: String,
)