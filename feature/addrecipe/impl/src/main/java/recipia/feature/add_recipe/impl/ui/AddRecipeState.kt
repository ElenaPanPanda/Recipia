package recipia.feature.add_recipe.impl.ui

import com.example.recipia.core.common.model.Ingredient
import com.example.recipia.core.common.model.IngredientSection
import com.example.recipia.core.common.model.RecipeCategory
import recipia.feature.add_recipe.impl.domain.model.CategoryForChoose

data class AddRecipeState(
    val enabledSaveButton: Boolean = false,
    val titleInput: String = "",
    val imageUrlInput: String = "",
    val titleInputErrorState: Boolean = false,
    val titleInputErrorText: String? = null,
    val categories: List<CategoryForChoose?> = RecipeCategory.entries.toList()
        .map { category ->
            if (category != RecipeCategory.ALL) CategoryForChoose(category, false)
            else null
        },
    val ingredients: List<IngredientSection> = listOf(
        IngredientSection(
            title = "",
            ingredientsList = listOf(Ingredient(amount = "", name = ""))
        )
    ),
    val instructionsInput: String = "",
)