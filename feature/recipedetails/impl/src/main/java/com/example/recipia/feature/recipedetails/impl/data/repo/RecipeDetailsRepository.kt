package com.example.recipia.feature.recipedetails.impl.data.repo

import com.example.recipia.feature.recipedetails.impl.data.dto.GetRecipeResponse

interface RecipeDetailsRepository {
    suspend fun getRecipe(id: String): GetRecipeResponse
}