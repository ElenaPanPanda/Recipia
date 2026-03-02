package com.example.recipia.feature.cookingmode.api

import kotlinx.serialization.Serializable

object CookingModeRoutingContract {
    @Serializable
    data class CookingMode(val recipeId: String)
}
