package com.example.recipia.feature.collections.api

import kotlinx.serialization.Serializable

object CollectionsRoutingContract {
    @Serializable
    data object Collections

    @Serializable
    data class CollectionDetails(val collectionId: String)
}