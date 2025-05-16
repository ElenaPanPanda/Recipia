package recipia.feature.add_recipe.impl.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.recipia.core.ui.components.AppInputField
import com.example.recipia.feature.addrecipe.impl.R
import recipia.feature.add_recipe.impl.ui.components.AddCategoriesSection
import recipia.feature.add_recipe.impl.ui.components.AddIngredientsSection
import recipia.feature.add_recipe.impl.ui.components.AddRecipeTopBar

@Composable
fun AddRecipeScreen(
    viewModel: AddRecipeViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val event: (AddRecipeEvent) -> Unit = viewModel::obtainEvent

    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            AddRecipeTopBar(
                onSaveClick = { },
                onCancelClick = { },
            )
        },
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .imePadding()
                .padding(contentPadding)
                .verticalScroll(scrollState)
        ) {
            AppInputField(
                value = state.titleInput,
                onValueChange = { value -> event(AddRecipeEvent.OnTitleInputChanged(value)) },
                title = stringResource(id = R.string.add_recipe_recipe_title),
                hint = stringResource(id = R.string.add_recipe_recipe_title_hint),
                isMandatory = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    capitalization = KeyboardCapitalization.Sentences
                ),
                modifier = Modifier.padding(16.dp)
            )
            AddCategoriesSection(
                categories = state.categories,
                onSelectedCategory = { category -> event(AddRecipeEvent.OnCategorySelected(category)) },
                modifier = Modifier.padding(16.dp)
            )
            AddIngredientsSection(
                ingredients = state.ingredients,
                onIngredientTitleValueChange = { value, index ->
                    event(AddRecipeEvent.OnIngredientTitleValueChange(value, index))
                },
                onIngredientNameValueChange = { value, ingredientsGroupIndex, ingredientIndex ->
                    event(
                        AddRecipeEvent.OnIngredientNameValueChange(
                            value,
                            ingredientsGroupIndex,
                            ingredientIndex
                        )
                    )
                },
                onIngredientAmountValueChange = { value, ingredientsGroupIndex, ingredientIndex ->
                    event(
                        AddRecipeEvent.OnIngredientAmountValueChange(
                            value,
                            ingredientsGroupIndex,
                            ingredientIndex
                        )
                    )
                },
                onIngredientRemoveClicked = { ingredientsGroupIndex, ingredientIndex ->
                    event(
                        AddRecipeEvent.OnIngredientRemoveClicked(
                            ingredientsGroupIndex,
                            ingredientIndex
                        )
                    )
                },
                onAddIngredientClicked = { ingredientsGroupIndex ->
                    event(AddRecipeEvent.OnAddIngredientClicked(ingredientsGroupIndex))
                },
                onRemoveIngredientsCardClicked = { ingredientsGroupIndex ->
                    event(AddRecipeEvent.OnRemoveIngredientsCardClicked(ingredientsGroupIndex))
                },
                onAddIngredientsGroupClicked = { event(AddRecipeEvent.OnAddIngredientsGroupClicked) },
                modifier = Modifier.padding(16.dp)
            )
            AppInputField(
                value = state.instructionsInput,
                onValueChange = { value -> event(AddRecipeEvent.OnInstructionsInputChanged(value)) },
                title = stringResource(id = R.string.add_recipe_instructions_title),
                hint = stringResource(id = R.string.add_recipe_instructions_hint),
                singleLine = false,
                minLines = 8,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}