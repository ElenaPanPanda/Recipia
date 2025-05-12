package com.example.recipia.feature.recipedetails.impl.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipia.core.ui.R
import com.example.recipia.core.ui.components.OutlinedButton
import com.example.recipia.core.ui.icons.Icons
import com.example.recipia.core.ui.theme.AppTypography
import com.example.recipia.core.ui.theme.DarkBlue
import com.example.recipia.feature.recipedetails.impl.ui.RecipeDetailsState
import com.example.recipia.feature.recipedetails.impl.ui.RecipeDetailsEvent

@Composable
fun RecipeDetailsContent(
    state: RecipeDetailsState.Success,
    event: (RecipeDetailsEvent) -> Unit,
    snackbarHostState: SnackbarHostState,
) {
    val scrollState = rememberScrollState()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .verticalScroll(scrollState)
        ) {
            LargeImage(state)
            Text(
                text = state.recipe.title,
                style = AppTypography().playDisplayExtraBold.copy(fontSize = 34.sp),
                color = DarkBlue,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 20.dp,
                        vertical = 24.dp
                    )
            )
            // TODO: Add star interactive progress bar
            OutlinedButton(
                text = stringResource(id = R.string.core_ui_add_start_cooking),
                onClick = {},
                leadingIcon = ImageVector.vectorResource(id = Icons.utensils),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 20.dp)
            )
            //stickyHeader {
            ActionToolbar(
                onEditClicked = { event(RecipeDetailsEvent.OnEditClicked(state.recipe.id)) },
                onSaveClicked = { event(RecipeDetailsEvent.OnSaveClicked(state.recipe.id)) },
                onCalendarClicked = { event(RecipeDetailsEvent.OnCalendarClicked(state.recipe.id)) },
                onShareClicked = { event(RecipeDetailsEvent.OnShareClicked(state.recipe.id)) },
                onDeleteClicked = { event(RecipeDetailsEvent.OnDeleteClicked(state.recipe.id)) }
            )
            //}
            if (state.recipe.ingredients.isNotEmpty()) {
                IngredientsSection(
                    ingredients = state.recipe.ingredients,
                    onAddIngredient = { },
                    onAddAllIngredients = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 8.dp, bottom = 32.dp)
                )
            }
            if (state.recipe.instructions.isNotEmpty()) {
                InstructionsSection(
                    instructions = state.recipe.instructions,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 40.dp)
                )
            }
        }
    }
}