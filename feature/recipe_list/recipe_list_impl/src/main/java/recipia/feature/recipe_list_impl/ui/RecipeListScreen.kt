package recipia.feature.recipe_list_impl.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import recipia.feature.recipe_list_impl.ui.RecipeListEffect.NavigateToAddRecipe
import recipia.feature.recipe_list_impl.ui.RecipeListEffect.NavigateToRecipeDetails
import recipia.feature.recipe_list_impl.ui.RecipeListEffect.ShowSnackBar
import com.example.recipia.core.ui.components.AppHorizontalDivider
import recipia.feature.recipe_list_impl.ui.components.CategoriesBar
import recipia.feature.recipe_list_impl.ui.components.RecipeItem
import recipia.feature.recipe_list_impl.ui.components.RecipeListTopBar

@Composable
fun RecipeListScreen(
    onShowSnackBar: (String) -> Unit,
    navigateToRecipeDetails: (String) -> Unit,
    navigateToAddRecipe: () -> Unit,
    viewModel: RecipeListViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val event: (RecipeListEvent) -> Unit = viewModel::obtainEvent

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                is ShowSnackBar -> onShowSnackBar(effect.message)
                is NavigateToRecipeDetails -> navigateToRecipeDetails(effect.recipeId)
                is NavigateToAddRecipe -> navigateToAddRecipe()
            }
        }
    }

    Column {
        RecipeListTopBar(
            onSearchClick = {},
            onAddClick = { event(RecipeListEvent.OnAddRecipeClicked) },
            modifier = Modifier.fillMaxWidth()
        )
        CategoriesBar(
            categories = state.categories,
            selectedCategory = state.selectedCategory,
            onCategorySelected = { category -> event(RecipeListEvent.OnCategorySelected(category)) }
        )
        AppHorizontalDivider()
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(12.dp))
            }
            items(state.filteredRecipes, key = { recipe -> recipe.id }) { recipe ->
                RecipeItem(
                    title = recipe.title,
                    imageUrl = recipe.imageUrl,
                    placeholderColor = recipe.placeholderColor.color,
                    rating = recipe.rating,
                    onClick = { event(RecipeListEvent.OnRecipeClicked(recipe.id)) }
                )
            }
            item {
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}