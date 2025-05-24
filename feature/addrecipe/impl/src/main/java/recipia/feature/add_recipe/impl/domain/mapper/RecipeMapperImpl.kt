package recipia.feature.add_recipe.impl.domain.mapper

import com.example.recipia.core.common.model.FullRecipe
import recipia.feature.add_recipe.impl.data.dto.GetRecipeResponse
import javax.inject.Inject

internal class RecipeMapperImpl @Inject constructor() : RecipeMapper {
    override fun convert(response: GetRecipeResponse): FullRecipe {
        with(response) {
            return FullRecipe(
                id = recipe.id,
                title = recipe.title,
                rating = recipe.rating,
                imageUrl = recipe.imageUrl,
                placeholderColor = recipe.placeholderColor,
                ingredients = recipe.ingredients,
                rawCategories = recipe.rawCategories,
                instructions = recipe.instructions,
            )
        }
    }
}