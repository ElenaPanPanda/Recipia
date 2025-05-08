package recipia.feature.recipe_list_impl.domain.mapper

import com.example.recipia.core.common.model.ShortRecipe
import recipia.feature.recipe_list_impl.data.dto.ShortRecipeDto

internal interface ShortRecipeMapper {
    fun convert(request: ShortRecipe): ShortRecipeDto
}