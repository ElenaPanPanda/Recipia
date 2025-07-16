package recipia.feature.impl.domain.mapper

import com.example.recipia.core.common.model.ShortRecipe
import recipia.feature.impl.data.dto.RecipeListResponse

internal interface RecipeListMapper {
    fun convert(response: RecipeListResponse): List<ShortRecipe>
}