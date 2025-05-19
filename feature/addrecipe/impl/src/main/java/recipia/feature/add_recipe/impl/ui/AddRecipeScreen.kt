package recipia.feature.add_recipe.impl.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.recipia.core.ui.R as uiR
import com.example.recipia.core.ui.components.AppAlertDialog
import com.example.recipia.core.ui.components.AppInputField
import com.example.recipia.feature.addrecipe.impl.R
import recipia.feature.add_recipe.impl.ui.components.AddCategoriesSection
import recipia.feature.add_recipe.impl.ui.components.AddIngredientsSection
import recipia.feature.add_recipe.impl.ui.components.AddRecipeTopBar

@Composable
fun AddRecipeScreen(
    viewModel: AddRecipeViewModel = hiltViewModel(),
    navigateToRecipeDetails: (String) -> Unit,
    navigateUp: () -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val event: (AddRecipeEvent) -> Unit = viewModel::obtainEvent
    val snackbarHostState = remember { SnackbarHostState() }
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()
    var showExitDialog by remember { mutableStateOf(false) }

    BackHandler(enabled = true) { event(AddRecipeEvent.OpenExitDialog) }

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                is AddRecipeEffect.ShowSnackBar -> snackbarHostState.showSnackbar(effect.message)
                is AddRecipeEffect.NavigateToRecipeDetails -> navigateToRecipeDetails(effect.id)
                is AddRecipeEffect.ShowExitDialog -> showExitDialog = true
            }
        }
    }

    if (showExitDialog) {
        AppAlertDialog(
            title = stringResource(id = uiR.string.core_ui_want_to_exit),
            onShowAlertDialog = { showExitDialog = false },
            confirmButtonText = stringResource(uiR.string.core_ui_exit),
            onConfirmButtonClick = {
                focusManager.clearFocus()
                showExitDialog = false
                navigateUp()
            },
            dismissButtonText = stringResource(uiR.string.core_ui_cancel),
            onDismissButtonClick = { showExitDialog = false },
        )
    }

    Scaffold(
        topBar = {
            AddRecipeTopBar(
                saveEnabled = state.enabledSaveButton,
                onSaveEnabledClick = { event(AddRecipeEvent.OnSaveEnabledClicked) },
                onSaveDisabledClick = {
                    focusManager.clearFocus()
                    event(AddRecipeEvent.OnSaveDisabledClicked)
                },
                onCancelClick = { event(AddRecipeEvent.OpenExitDialog) },
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
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
                isError = state.titleInputErrorState,
                supportingText = state.titleInputErrorText,
                modifier = Modifier.padding(16.dp)
            )
            AppInputField(
                value = state.imageUrlInput,
                onValueChange = { value -> event(AddRecipeEvent.OnImageUrlInputChanged(value)) },
                title = "Image URL (temporary)",
                hint = "https://...",
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
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