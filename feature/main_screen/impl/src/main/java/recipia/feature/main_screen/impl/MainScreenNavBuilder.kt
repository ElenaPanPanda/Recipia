package recipia.feature.main_screen.impl

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.recipia.feature.recipedetails.api.RecipeDetailsRoutingContract
import recipia.feature.main_screen.api.MainScreenRoutingContract

fun NavGraphBuilder.mainScreen(navController: NavController) {
    composable<MainScreenRoutingContract.MainScreen> {
        MainScreen(
            navigateToRecipeDetails = { recipeId ->
                navController.navigate(
                    RecipeDetailsRoutingContract.RecipeDetails(
                        recipeId = recipeId
                    )
                )
            }
        )
    }
}