package recipia.feature.add_recipe.impl.domain.usecase

import com.example.recipia.core.common.model.FullRecipe

interface AddRecipeUseCase {
    suspend fun addRecipe(recipe: FullRecipe): String
}