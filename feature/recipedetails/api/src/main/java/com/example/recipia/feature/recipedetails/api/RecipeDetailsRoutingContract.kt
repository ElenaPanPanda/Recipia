package com.example.recipia.feature.recipedetails.api

import kotlinx.serialization.Serializable

object RecipeDetailsRoutingContract {
    @Serializable
    data class RecipeDetails(val recipeId: String)
}