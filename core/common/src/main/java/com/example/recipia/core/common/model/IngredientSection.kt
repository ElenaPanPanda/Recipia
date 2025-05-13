package com.example.recipia.core.common.model

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonClassDiscriminator("type")
sealed interface IngredientSection {
    @Serializable
    @SerialName("Group")
    data class Group(
        @SerialName("title") val title: String,
        @SerialName("items") val items: List<Ingredient>
    ) : IngredientSection

    @Serializable
    @SerialName("Ungrouped")
    data class Ungrouped(
        @SerialName("items") val items: List<Ingredient>
    ) : IngredientSection
}