package com.example.recipia.core.domain.recipes.mappers

import com.example.recipia.core.domain.recipes.RecipesDomainTestHelper.createShortRecipeDtoList
import com.example.recipia.core.domain.recipes.RecipesDomainTestHelper.createShortRecipeList
import com.example.recipia.core.network.dto.RecipeListResponse
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RecipeListMapperTest {
    private val mapper = RecipeListMapperImpl()

    @Test
    fun mapper_convertsResponseCorrectly() {
        val response = RecipeListResponse(recipes = createShortRecipeDtoList())
        val expectedShortRecipeList = createShortRecipeList()

        val result = mapper.convert(response)

        assertThat(result).isEqualTo(expectedShortRecipeList)
    }
}