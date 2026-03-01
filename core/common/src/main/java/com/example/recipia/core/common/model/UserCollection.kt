package com.example.recipia.core.common.model

import com.example.recipia.core.ui.model.PlaceholderColor
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserCollection(
    @SerialName("id") val collectionId: String,
    @SerialName("name") val collectionName: String,
    @SerialName("recipes") val recipes: List<ShortRecipe>,
    @SerialName("color") val collectionColor: PlaceholderColor = PlaceholderColor.entries.random(),
)