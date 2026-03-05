package com.example.recipia.core.domain.recipes.di

import com.example.recipia.core.domain.recipes.mappers.RecipeListMapper
import com.example.recipia.core.domain.recipes.mappers.RecipeListMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal class RecipeMappersModule {
    @Provides
    fun provideRecipeListMapper(): RecipeListMapper = RecipeListMapperImpl()
}