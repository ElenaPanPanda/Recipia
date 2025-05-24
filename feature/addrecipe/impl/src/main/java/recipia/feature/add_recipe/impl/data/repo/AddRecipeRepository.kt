package recipia.feature.add_recipe.impl.data.repo

import com.example.recipia.core.common.model.FullRecipe
import recipia.feature.add_recipe.impl.data.dto.GetRecipeResponse

interface AddRecipeRepository {
    suspend fun addRecipe(recipe: FullRecipe): GetRecipeResponse
}