package recipia.feature.add_recipe.impl.ui

import com.example.recipia.core.common.model.RecipeCategory

data class AddRecipeState (
    val enabledSaveButton: Boolean = false,
    val titleInput: String = "",
    val instructionsInput: String = "",
    val categories: List<RecipeCategory> = RecipeCategory.entries.toList(),
)