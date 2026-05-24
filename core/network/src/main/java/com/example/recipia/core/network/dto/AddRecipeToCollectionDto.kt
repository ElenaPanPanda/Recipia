package com.example.recipia.core.network.dto

data class AddRecipeToCollectionDto(
    val collection: UserCollectionDto,
    val message: String,
    val success: Boolean
)