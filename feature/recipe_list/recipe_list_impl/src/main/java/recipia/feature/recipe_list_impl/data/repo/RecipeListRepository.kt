package recipia.feature.recipe_list_impl.data.repo

import recipia.feature.recipe_list_impl.data.dto.RecipeListResponse
import recipia.feature.recipe_list_impl.data.dto.ShortRecipeDto

interface RecipeListRepository {
    suspend fun getRecipes(): RecipeListResponse

    suspend fun likeRecipe(recipeId: String, recipe: ShortRecipeDto)
}