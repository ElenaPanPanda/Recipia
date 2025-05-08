package com.example.recipia.core.common.model

import kotlinx.serialization.Serializable

@Serializable
enum class RecipeCategory() {
    BAKERY,
    BREAKFAST,
    DESSERT,
    MEAT,
    VEGETABLES,
    DRINKS,
    MAIN_COURSE,
    SOUP,
}