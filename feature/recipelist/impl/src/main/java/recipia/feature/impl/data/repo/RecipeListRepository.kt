package recipia.feature.impl.data.repo

import recipia.feature.impl.data.dto.RecipeListResponse

interface RecipeListRepository {
    suspend fun getRecipes(): RecipeListResponse
}