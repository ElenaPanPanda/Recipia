package recipia.feature.recipe_list_impl.domain.usecase

import com.example.recipia.core.common.model.ShortRecipe
import recipia.feature.recipe_list_impl.data.repo.RecipeListRepository
import recipia.feature.recipe_list_impl.domain.mapper.ShortRecipeMapper
import javax.inject.Inject

internal class LikeRecipeUseCaseImpl @Inject constructor(
    private val mapper: ShortRecipeMapper,
    private val repository: RecipeListRepository
) : LikeRecipeUseCase {
    override suspend fun likeRecipe(recipeId: String, recipe: ShortRecipe) {
        val shortRecipe = mapper.convert(recipe)
        repository.likeRecipe(recipeId, shortRecipe)
    }
}