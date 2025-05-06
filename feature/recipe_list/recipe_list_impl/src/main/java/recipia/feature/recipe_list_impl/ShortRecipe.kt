package recipia.feature.recipe_list_impl

data class ShortRecipe(
    val id: String,
    val title: String,
    val imageUrl: String,
    val isFavorite: Boolean,
)