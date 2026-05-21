package com.example.recipia.core.network.dto

import com.example.recipia.core.ui.model.PlaceholderColor
import kotlinx.serialization.Serializable

@Serializable
data class CreateCollectionRequestDto(
    val collectionName: String,
    val collectionColor: PlaceholderColor,
    val recipeId: String,
)