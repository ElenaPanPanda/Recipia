package com.example.recipia.feature.collections.impl.ui.collectiondetails.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.recipia.core.common.model.UserCollection

@Composable
fun CollectionDetailsContent(
    collection: UserCollection,
    onDeleteCollectionClicked: () -> Unit
) {
    Column {
        CollectionDetailsTopBar(
            title = collection.collectionName,
            onDeleteClicked = onDeleteCollectionClicked
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)
        ) {
            item { Spacer(modifier = Modifier.height(12.dp)) }
            items(collection.recipes, key = { recipe -> recipe.id }) {
                RecipeItem
            }
        }
    }
}