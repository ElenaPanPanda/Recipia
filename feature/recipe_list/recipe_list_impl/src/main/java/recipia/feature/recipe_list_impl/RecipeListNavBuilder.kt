package recipia.feature.recipe_list_impl

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import recipia.feature.recipe_list_api.RecipeListRoutingContract
import recipia.feature.recipe_list_impl.ui.RecipeListScreen

fun NavGraphBuilder.recipeListScreen(navController: NavController) {
    composable<RecipeListRoutingContract.RecipeList> {
        RecipeListScreen()
    }
}