package recipia.feature.add_recipe.impl.domain.usecase

import com.example.recipia.core.common.model.FullRecipe
import recipia.feature.add_recipe.impl.data.repo.AddRecipeRepository
import recipia.feature.add_recipe.impl.domain.mapper.RecipeMapper
import javax.inject.Inject

internal class AddRecipeUseCaseImpl @Inject constructor(
    private val mapper: RecipeMapper,
    private val repository: AddRecipeRepository
) : AddRecipeUseCase {
    override suspend fun addRecipe(recipe: FullRecipe) =
        mapper.convert(repository.addRecipe(recipe)).id
}