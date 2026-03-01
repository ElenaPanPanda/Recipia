package com.example.recipia.feature.recipedetails.impl.data.dto

import com.example.recipia.core.common.model.UserCollection
import kotlinx.serialization.Serializable

@Serializable
data class CreateCollectionResponse(
    val collection: UserCollection,
    val message: String,
    val success: Boolean,
)