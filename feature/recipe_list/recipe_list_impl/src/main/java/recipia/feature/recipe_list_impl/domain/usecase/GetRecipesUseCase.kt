package recipia.feature.recipe_list_impl.domain.usecase

import com.example.recipia.core.common.model.ShortRecipe

interface GetRecipesUseCase {
    suspend fun getRecipes(): Result<List<ShortRecipe>>
}