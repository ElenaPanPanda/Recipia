package recipia.feature.recipe_list_impl.data.repo

import recipia.feature.recipe_list_impl.data.dto.RecipeListResponse

interface RecipeListRepository {
    suspend fun getRecipes(): RecipeListResponse
}