package recipia.feature.add_recipe.impl.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.recipia.feature.recipedetails.api.RecipeDetailsRoutingContract
import recipia.feature.add_recipe.api.AddRecipeRoutingContract
import recipia.feature.add_recipe.impl.ui.AddRecipeScreen

fun NavGraphBuilder.addRecipeScreen(navController: NavController) {
    composable<AddRecipeRoutingContract.AddRecipe> {
        AddRecipeScreen(
            navigateToRecipeDetails = { id ->
                navController.navigate(
                    RecipeDetailsRoutingContract.RecipeDetails(recipeId = id)
                ) {
                    popUpTo<AddRecipeRoutingContract.AddRecipe> { inclusive = true }
                }
            },
            navigateUp = { navController.popBackStack() }
        )
    }
}