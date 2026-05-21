package com.example.recipia.core.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreateCollectionResponseDto(
    val collection: UserCollectionDto,
    val message: String,
    val success: Boolean,
)