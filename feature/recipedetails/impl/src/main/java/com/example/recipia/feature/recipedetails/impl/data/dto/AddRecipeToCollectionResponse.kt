package com.example.recipia.feature.recipedetails.impl.data.dto

import com.example.recipia.core.common.model.UserCollection

data class AddRecipeToCollectionResponse(
    val collection: UserCollection,
    val message: String,
    val success: Boolean
)