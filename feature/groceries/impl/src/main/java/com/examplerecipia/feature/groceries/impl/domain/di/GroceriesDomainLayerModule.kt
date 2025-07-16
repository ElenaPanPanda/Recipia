package com.examplerecipia.feature.groceries.impl.domain.di

import com.example.datastore.ShoppingListRepository
import com.examplerecipia.feature.groceries.impl.domain.mapper.ShoppingListIngredientMapper
import com.examplerecipia.feature.groceries.impl.domain.mapper.ShoppingListIngredientMapperImpl
import com.examplerecipia.feature.groceries.impl.domain.mapper.ShoppingListItemMapper
import com.examplerecipia.feature.groceries.impl.domain.mapper.ShoppingListItemMapperImpl
import com.examplerecipia.feature.groceries.impl.domain.usecase.AddListBlockUseCase
import com.examplerecipia.feature.groceries.impl.domain.usecase.AddListBlockUseCaseImpl
import com.examplerecipia.feature.groceries.impl.domain.usecase.GetShoppingListUseCase
import com.examplerecipia.feature.groceries.impl.domain.usecase.GetShoppingListUseCaseImpl
import com.examplerecipia.feature.groceries.impl.domain.usecase.RemoveListBlockUseCase
import com.examplerecipia.feature.groceries.impl.domain.usecase.RemoveListBlockUseCaseImpl
import com.examplerecipia.feature.groceries.impl.domain.usecase.UpdateListBlockUseCase
import com.examplerecipia.feature.groceries.impl.domain.usecase.UpdateListBlockUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal class GroceriesDomainLayerModule {
    @Provides
    fun provideShoppingListIngredientMapper(): ShoppingListIngredientMapper =
        ShoppingListIngredientMapperImpl()

    @Provides
    fun provideShoppingListItemMapper(ingredientsListMapper: ShoppingListIngredientMapper): ShoppingListItemMapper =
        ShoppingListItemMapperImpl(ingredientsListMapper)

    @Provides
    fun provideGetShoppingListUseCase(
        repository: ShoppingListRepository,
        mapper: ShoppingListItemMapper,
    ): GetShoppingListUseCase = GetShoppingListUseCaseImpl(repository, mapper)

    @Provides
    fun provideAddListBlockUseCase(
        repository: ShoppingListRepository,
        mapper: ShoppingListItemMapper
    ): AddListBlockUseCase = AddListBlockUseCaseImpl(repository, mapper)

    @Provides
    fun updateListBlockUseCase(
        repository: ShoppingListRepository,
        mapper: ShoppingListItemMapper
    ): UpdateListBlockUseCase = UpdateListBlockUseCaseImpl(repository, mapper)

    @Provides
    fun removeListBlockUseCase(repository: ShoppingListRepository): RemoveListBlockUseCase =
        RemoveListBlockUseCaseImpl(repository)
}