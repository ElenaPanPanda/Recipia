package recipia.feature.add_recipe.impl.domain.mapper

import com.example.recipia.core.common.model.FullRecipe
import recipia.feature.add_recipe.impl.data.dto.GetRecipeResponse

internal interface RecipeMapper {
    fun convert(response: GetRecipeResponse): FullRecipe
}