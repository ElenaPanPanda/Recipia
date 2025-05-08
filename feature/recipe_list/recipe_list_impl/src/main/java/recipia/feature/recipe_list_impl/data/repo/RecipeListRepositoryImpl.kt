package recipia.feature.recipe_list_impl.data.repo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import recipia.feature.recipe_list_impl.data.api.RecipeListNetworkApi
import recipia.feature.recipe_list_impl.data.dto.ShortRecipeDto
import javax.inject.Inject

internal class RecipeListRepositoryImpl @Inject constructor(private val api: RecipeListNetworkApi) :
    RecipeListRepository {
    override suspend fun getRecipes() =
        withContext(Dispatchers.IO) {
            api.getRecipes()
        }

    override suspend fun likeRecipe(recipeId: String, recipe: ShortRecipeDto) =
        withContext(Dispatchers.IO) {
            api.likeRecipe(recipeId, recipe)
        }
}