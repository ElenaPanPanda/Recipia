package recipia.feature.recipe_list_impl.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import recipia.feature.recipe_list_api.RecipeListRoutingContract
import recipia.feature.recipe_list_impl.ui.RecipeListScreen

fun NavGraphBuilder.recipeListScreen(
    onShowSnackBar: (String) -> Unit,
    navigateToRecipeDetails: (String) -> Unit,
    navigateToAddRecipe: () -> Unit,
) {
    composable<RecipeListRoutingContract.RecipeList> {
        RecipeListScreen(
            onShowSnackBar = { message -> onShowSnackBar(message) },
            navigateToRecipeDetails = navigateToRecipeDetails,
            navigateToAddRecipe = navigateToAddRecipe,
        )
    }
}