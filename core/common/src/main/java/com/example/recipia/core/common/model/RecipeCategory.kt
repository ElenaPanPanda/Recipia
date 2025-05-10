package com.example.recipia.core.common.model

import com.example.recipia.core.common.R
import kotlinx.serialization.Serializable

@Serializable
enum class RecipeCategory(val textId: Int) { // TODO: add ALL category
    ALL
        (textId = R.string.core_common_categories_all),
    BAKERY
        (textId = R.string.core_common_categories_bakery),
    BREAKFAST
        (textId = R.string.core_common_categories_breakfast),
    DESSERT
        (textId = R.string.core_common_categories_dessert),
    MEAT
        (textId = R.string.core_common_categories_meat),
    VEGETABLES
        (textId = R.string.core_common_categories_vegetables),
    DRINKS
        (textId = R.string.core_common_categories_drinks),
    MAIN_COURSE
        (textId = R.string.core_common_categories_main_course),
    SOUP
        (textId = R.string.core_common_categories_soup),
}