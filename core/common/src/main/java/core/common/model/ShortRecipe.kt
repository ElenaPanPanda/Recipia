package core.common.model

data class ShortRecipe(
    val id: String,
    val title: String,
    val isFavorite: Boolean,
    val imageUrl: String,
    val rawCategories: List<RecipeCategory> = emptyList(),
)