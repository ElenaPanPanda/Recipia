package recipia.feature.recipe_list_impl.domain.usecase

import com.example.recipia.core.common.model.ShortRecipe

interface LikeRecipeUseCase {
    suspend fun likeRecipe(recipeId: String, recipe: ShortRecipe)
}