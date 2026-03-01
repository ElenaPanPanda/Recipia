package com.example.recipia.feature.collections.impl.data.dto

import com.example.recipia.core.common.model.UserCollection
import kotlinx.serialization.Serializable

@Serializable
data class GetCollectionByIdResponse(
    val collection: UserCollection,
    val message: String,
    val success: Boolean
)