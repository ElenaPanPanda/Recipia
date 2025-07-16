package recipia.feature.impl.domain.usecase

import com.example.recipia.core.common.model.ShortRecipe

interface GetRecipesUseCase {
    suspend fun getRecipes(): List<ShortRecipe>
}