package com.example.recipia.feature.collections.impl.ui.collectionslist.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipia.feature.collections.impl.ui.collectionslist.CollectionsEvent
import com.example.recipia.feature.collections.impl.ui.collectionslist.CollectionsState
import com.example.recipia.feature.collections.impl.ui.collectionslist.CollectionsViewModel

@Composable
fun CollectionsContent(
    state: CollectionsState.Success,
    event: (CollectionsEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()

    Column(modifier = modifier) {
        CollectionsTopBar()
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .verticalScroll(scrollState)
                .padding(16.dp)
        ) {
            if (state.collections.isNotEmpty()) {
                CollectionsCard(
                    listOfCollections = state.collections,
                    onItemClick = { id -> event(CollectionsEvent.OnCollectionClicked(collectionId = id)) },
                )
            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    // TODO: create a composable for empty collections
                    Text(text = "Create your first collection")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CollectionsContentPreview() {
    CollectionsContent(
        state = CollectionsState.Success(collections = CollectionsViewModel.COLLECTIONS),
        event = {},
    )
}