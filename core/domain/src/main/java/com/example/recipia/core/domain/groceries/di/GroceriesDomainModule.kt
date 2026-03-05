package com.example.recipia.core.domain.groceries.di

import com.example.datastore.ShoppingListRepository
import com.example.recipia.core.domain.groceries.mappers.GroceriesItemMapper
import com.example.recipia.core.domain.groceries.usecase.AddGroceriesBlockUseCase
import com.example.recipia.core.domain.groceries.usecase.AddGroceriesBlockUseCaseImpl
import com.example.recipia.core.domain.groceries.usecase.ClearGroceriesUseCase
import com.example.recipia.core.domain.groceries.usecase.ClearGroceriesUseCaseImpl
import com.example.recipia.core.domain.groceries.usecase.GetGroceriesListUseCase
import com.example.recipia.core.domain.groceries.usecase.GetGroceriesListUseCaseImpl
import com.example.recipia.core.domain.groceries.usecase.RemoveCheckedGroceriesItemsUseCase
import com.example.recipia.core.domain.groceries.usecase.RemoveCheckedGroceriesItemsUseCaseImpl
import com.example.recipia.core.domain.groceries.usecase.RemoveGroceriesListBlockUseCase
import com.example.recipia.core.domain.groceries.usecase.RemoveGroceriesListBlockUseCaseImpl
import com.example.recipia.core.domain.groceries.usecase.UpdateGroceriesListBlockUseCase
import com.example.recipia.core.domain.groceries.usecase.UpdateGroceriesListBlockUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal class GroceriesDomainModule {
    @Provides
    fun provideAddListBlockUseCase(
        repository: ShoppingListRepository,
        mapper: GroceriesItemMapper
    ): AddGroceriesBlockUseCase = AddGroceriesBlockUseCaseImpl(repository, mapper)

    @Provides
    fun provideGetGroceriesListUseCase(
        repository: ShoppingListRepository,
        mapper: GroceriesItemMapper
    ): GetGroceriesListUseCase = GetGroceriesListUseCaseImpl(repository, mapper)

    @Provides
    fun provideUpdateGroceriesListBlockUseCase(
        repository: ShoppingListRepository,
        mapper: GroceriesItemMapper
    ): UpdateGroceriesListBlockUseCase = UpdateGroceriesListBlockUseCaseImpl(repository, mapper)

    @Provides
    fun provideRemoveGroceriesListBlockUseCase(
        repository: ShoppingListRepository
    ): RemoveGroceriesListBlockUseCase = RemoveGroceriesListBlockUseCaseImpl(repository)

    @Provides
    fun provideClearGroceriesUseCase(
        repository: ShoppingListRepository
    ): ClearGroceriesUseCase = ClearGroceriesUseCaseImpl(repository)

    @Provides
    fun provideRemoveCheckedGroceriesItemsUseCase(
        repository: ShoppingListRepository,
        mapper: GroceriesItemMapper
    ): RemoveCheckedGroceriesItemsUseCase =
        RemoveCheckedGroceriesItemsUseCaseImpl(repository, mapper)
}