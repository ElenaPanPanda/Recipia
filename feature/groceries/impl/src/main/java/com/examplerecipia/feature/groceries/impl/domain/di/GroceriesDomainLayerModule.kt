package com.examplerecipia.feature.groceries.impl.domain.di

import com.example.datastore.ShoppingListRepository
import com.examplerecipia.feature.groceries.impl.domain.usecase.AddIngredientToListBlockUseCase
import com.examplerecipia.feature.groceries.impl.domain.usecase.AddIngredientToListBlockUseCaseImpl
import com.examplerecipia.feature.groceries.impl.domain.usecase.AddListBlockUseCase
import com.examplerecipia.feature.groceries.impl.domain.usecase.AddListBlockUseCaseImpl
import com.examplerecipia.feature.groceries.impl.domain.usecase.GetShoppingListUseCase
import com.examplerecipia.feature.groceries.impl.domain.usecase.GetShoppingListUseCaseImpl
import com.examplerecipia.feature.groceries.impl.domain.usecase.RemoveListBlockUseCase
import com.examplerecipia.feature.groceries.impl.domain.usecase.RemoveListBlockUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal class GroceriesDomainLayerModule {
    @Provides
    fun getShoppingListUseCase(repository: ShoppingListRepository): GetShoppingListUseCase =
        GetShoppingListUseCaseImpl(repository)

    @Provides
    fun provideAddListBlockUseCase(repository: ShoppingListRepository): AddListBlockUseCase =
        AddListBlockUseCaseImpl(repository)

    @Provides
    fun provideAddIngredientToListBlockUseCase(repository: ShoppingListRepository): AddIngredientToListBlockUseCase =
        AddIngredientToListBlockUseCaseImpl(repository)

    @Provides
    fun removeListBlockUseCase(repository: ShoppingListRepository): RemoveListBlockUseCase =
        RemoveListBlockUseCaseImpl(repository)
}