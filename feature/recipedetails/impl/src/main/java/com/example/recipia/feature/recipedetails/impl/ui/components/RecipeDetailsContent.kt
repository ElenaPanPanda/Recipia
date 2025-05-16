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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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

    var rating by remember { mutableFloatStateOf(state.recipe.rating / 2.0f) }

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
                    .padding(horizontal = 20.dp)
                    .padding(top = 24.dp, bottom = 16.dp)
            )

            RatingBar(
                rating = rating,
                starSize = 40.dp,
                onRatingChanged = { newRating ->
                    rating = newRating
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 16.dp)
            )

            OutlinedButton(
                text = stringResource(id = R.string.core_ui_start_cooking),
                onClick = {},
                leadingIcon = ImageVector.vectorResource(id = Icons.cutlery),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 20.dp)
            )
            ActionToolbar(
                onEditClicked = { event(RecipeDetailsEvent.OnEditClicked(state.recipe.id)) },
                onSaveClicked = { event(RecipeDetailsEvent.OnSaveClicked(state.recipe.id)) },
                onCalendarClicked = { event(RecipeDetailsEvent.OnCalendarClicked(state.recipe.id)) },
                onShareClicked = { event(RecipeDetailsEvent.OnShareClicked(state.recipe.id)) },
                onDeleteClicked = { event(RecipeDetailsEvent.OnDeleteClicked(state.recipe.id)) }
            )

            if (state.recipe.rawCategories.isNotEmpty()) {
                CategoriesSection(
                    categories = state.recipe.rawCategories,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 20.dp, bottom = 32.dp)
                )
            }

            if (state.recipe.ingredients.isNotEmpty()) {
                state.recipe.ingredients.forEach { detailedIngredientSection ->
                    IngredientsSection(
                        ingredients = detailedIngredientSection.ingredientsList,
                        onAddIngredient = { },
                        onAddAllIngredients = { },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 8.dp, bottom = 32.dp)
                    )
                }
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