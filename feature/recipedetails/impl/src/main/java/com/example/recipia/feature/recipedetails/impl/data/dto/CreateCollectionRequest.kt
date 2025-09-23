package com.example.recipia.feature.recipedetails.impl.data.dto

import com.example.recipia.core.ui.model.PlaceholderColor
import kotlinx.serialization.Serializable

@Serializable
data class CreateCollectionRequest(
    val collectionName: String,
    val collectionColor: PlaceholderColor,
    val recipeId: String,
)