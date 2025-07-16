package recipia.feature.impl.domain.usecase

import recipia.feature.impl.data.repo.RecipeListRepository
import recipia.feature.impl.domain.mapper.RecipeListMapper
import javax.inject.Inject

internal class GetRecipesUseCaseImpl @Inject constructor(
    private val mapper: RecipeListMapper,
    private val repository: RecipeListRepository
) : GetRecipesUseCase {
    override suspend fun getRecipes() = mapper.convert(repository.getRecipes())
}