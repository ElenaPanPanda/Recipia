package recipia.feature.recipe_list_impl.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import recipia.feature.recipe_list_impl.ui.RecipeListEffect.ShowSnackBar
import recipia.feature.recipe_list_impl.ui.components.RecipeItem

@Composable
fun RecipeListScreen(
    onShowSnackBar: (String) -> Unit,
    viewModel: RecipeListViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val event: (RecipeListEvent) -> Unit = viewModel::obtainEvent

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                is ShowSnackBar -> onShowSnackBar(effect.message)
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        items(state.recipes) { recipe ->
            RecipeItem(
                title = recipe.title,
                imageUrl = recipe.imageUrl,
                rating = 0 // TODO: use recipe rating
            )
        }
    }
}