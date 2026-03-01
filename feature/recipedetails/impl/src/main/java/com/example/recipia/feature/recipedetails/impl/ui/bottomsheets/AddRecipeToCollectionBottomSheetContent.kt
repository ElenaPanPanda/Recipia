package com.example.recipia.feature.recipedetails.impl.ui.bottomsheets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipia.core.common.model.ShortRecipe
import com.example.recipia.core.ui.components.AppFilledButton
import com.example.recipia.core.ui.components.AppHorizontalDivider
import com.example.recipia.core.ui.components.AppInputField
import com.example.recipia.core.ui.components.IconTextButton
import com.example.recipia.core.ui.model.PlaceholderColor
import com.example.recipia.core.ui.theme.AppTypography
import com.example.recipia.core.ui.theme.DarkTeal
import com.example.recipia.core.ui.theme.DeepRed
import com.example.recipia.core.ui.R as CoreR
import com.example.recipia.feature.recipedetails.impl.R
import com.example.recipia.feature.recipedetails.impl.domain.model.CollectionInRecipeDetails
import com.example.recipia.feature.recipedetails.impl.ui.SaveToCollectionOption

@Composable
fun AddRecipeToCollectionBottomSheetContent(
    onDismiss: () -> Unit,
    collections: List<CollectionInRecipeDetails>,
    onCollectionSelectedChange: (String) -> Unit,
    onNewCollectionValueChange: (String) -> Unit,
    saveButtonEnabled: Boolean,
    saveToCollectionOption: SaveToCollectionOption?,
    onSave: () -> Unit,
) {
    val (checkBoxesEnabled, textFieldEnabled) = remember(saveToCollectionOption) {
        when (saveToCollectionOption) {
            is SaveToCollectionOption.Existing -> true to false
            is SaveToCollectionOption.New -> false to true
            null -> true to true
        }
    }

    Column(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .padding(bottom = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.recipe_details_save_to_collection),
            style = AppTypography().playDisplayBold.copy(fontSize = 21.sp),
            textAlign = TextAlign.Center,
            color = DarkTeal,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp)
        )
        if (collections.isNotEmpty()) {
            collections.forEachIndexed { index, collection ->
                CollectionCheckboxRow(
                    collectionName = collection.collectionName,
                    collectionSize = collection.recipesAmount,
                    isSelected = (saveToCollectionOption as? SaveToCollectionOption.Existing)?.id == collection.collectionId,
                    enabled = checkBoxesEnabled,
                    onCheckedChange = {
                        onCollectionSelectedChange(
                            collection.collectionId
                        )
                    },
                    modifier = Modifier.padding(vertical = 12.dp)
                )

                if (index < collections.lastIndex) {
                    AppHorizontalDivider()
                }
            }
        }
        AppInputField(
            value = (saveToCollectionOption as? SaveToCollectionOption.New)?.name ?: "",
            onValueChange = onNewCollectionValueChange,
            hint = stringResource(R.string.recipe_details_create_collection_hint),
            modifier = Modifier.padding(vertical = 24.dp),
            enabled = textFieldEnabled
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconTextButton(
                text = stringResource(CoreR.string.core_ui_cancel),
                onClick = onDismiss,
                fontSize = 16.sp,
                contentColor = DeepRed,
            )
            Spacer(modifier = Modifier.width(16.dp))
            AppFilledButton(
                text = stringResource(CoreR.string.core_ui_save),
                onClick = onSave,
                enabled = saveButtonEnabled,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AddRecipeToCollectionBottomSheetContentPreview() {
    AddRecipeToCollectionBottomSheetContent(
        onDismiss = {},
        collections = listOf(
            CollectionInRecipeDetails(
                collectionId = "1", collectionName = "Collection name", recipesAmount = 1
            ), CollectionInRecipeDetails(
                collectionId = "2",
                collectionName = "Collection very long very long very long very long name",
                recipesAmount = 0
            ), CollectionInRecipeDetails(
                collectionId = "3", collectionName = "Collection name", recipesAmount = 15
            )
        ),
        onCollectionSelectedChange = { _ -> },
        onNewCollectionValueChange = {},
        saveButtonEnabled = true,
        onSave = {},
        saveToCollectionOption = SaveToCollectionOption.Existing(id = "1"),
    )
}

@Preview(showBackground = true)
@Composable
private fun AddRecipeToCollectionBottomSheetContentWithoutCollectionsPreview() {
    AddRecipeToCollectionBottomSheetContent(
        onDismiss = {},
        collections = emptyList(),
        onCollectionSelectedChange = { _ -> },
        onNewCollectionValueChange = {},
        saveButtonEnabled = false,
        onSave = {},
        saveToCollectionOption = SaveToCollectionOption.New(name = "New collection"),
    )
}