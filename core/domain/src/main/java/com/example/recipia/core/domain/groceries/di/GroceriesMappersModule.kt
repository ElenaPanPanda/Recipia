package com.example.recipia.core.domain.groceries.di

import com.example.recipia.core.domain.groceries.mappers.GroceriesIngredientMapper
import com.example.recipia.core.domain.groceries.mappers.GroceriesIngredientMapperImpl
import com.example.recipia.core.domain.groceries.mappers.GroceriesItemMapper
import com.example.recipia.core.domain.groceries.mappers.GroceriesItemMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal class GroceriesMappersModule {
    @Provides
    fun provideGroceriesIngredientMapper(): GroceriesIngredientMapper =
        GroceriesIngredientMapperImpl()

    @Provides
    fun provideGroceriesItemMapper(groceriesIngredientsMapper: GroceriesIngredientMapper): GroceriesItemMapper =
        GroceriesItemMapperImpl(groceriesIngredientsMapper)
}