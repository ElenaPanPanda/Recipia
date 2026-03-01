package com.example.recipia.feature.collections.impl.ui.collectionslist.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipia.core.common.model.UserCollection
import com.example.recipia.core.ui.components.AppHorizontalDivider
import com.example.recipia.core.ui.theme.snowWhite
import com.example.recipia.feature.collections.impl.ui.collectionslist.CollectionsViewModel

@Composable
fun CollectionsCard(
    listOfCollections: List<UserCollection>,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = snowWhite)
    ) {
        Column {
            listOfCollections.forEachIndexed { index, collection ->
                CollectionItem(
                    title = collection.collectionName,
                    amountOfRecipes = collection.recipes.size,
                    collectionColor = collection.collectionColor,
                    onItemClick = { onItemClick(collection.collectionId) },
                )
                if (index < listOfCollections.lastIndex) {
                    AppHorizontalDivider()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun CollectionsCardPreview() {
    CollectionsCard(
        listOfCollections = CollectionsViewModel.COLLECTIONS,
        onItemClick = {},
        modifier = Modifier.padding(24.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun EmptyCollectionsCardPreview() {
    CollectionsCard(
        listOfCollections = emptyList(),
        onItemClick = {},
        modifier = Modifier.padding(24.dp)
    )
}