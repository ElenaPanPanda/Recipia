package com.example.recipia.feature.collections.impl.ui.collectionslist

sealed interface CollectionsEvent {
    data class OnCollectionClicked(val collectionId: String) : CollectionsEvent
}