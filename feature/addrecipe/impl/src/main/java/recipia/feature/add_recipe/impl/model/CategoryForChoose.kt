package recipia.feature.add_recipe.impl.model

import com.example.recipia.core.common.model.RecipeCategory

data class CategoryForChoose (
    val category: RecipeCategory,
    val isSelected: Boolean,
)