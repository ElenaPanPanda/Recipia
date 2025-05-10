package recipia.feature.recipe_list_impl.domain.usecase

import recipia.feature.recipe_list_impl.data.repo.RecipeListRepository
import recipia.feature.recipe_list_impl.domain.mapper.RecipeListMapper
import javax.inject.Inject

internal class GetRecipesUseCaseImpl @Inject constructor(
    private val mapper: RecipeListMapper,
    private val repository: RecipeListRepository
) : GetRecipesUseCase {
    override suspend fun getRecipes() = mapper.convert(repository.getRecipes())
}