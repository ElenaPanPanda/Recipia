package com.example.recipia.core.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class GetCollectionsDto(
    val collections: List<UserCollectionDto>,
    val message: String,
    val success: Boolean
)