package com.example.recipia.core.domain.collections.di

import com.example.recipia.core.domain.collections.mappers.CollectionOverviewMapper
import com.example.recipia.core.domain.collections.usecase.AddRecipeToCollectionUseCase
import com.example.recipia.core.domain.collections.usecase.AddRecipeToCollectionUseCaseImpl
import com.example.recipia.core.domain.collections.usecase.CreateCollectionUseCase
import com.example.recipia.core.domain.collections.usecase.CreateCollectionUseCaseImpl
import com.example.recipia.core.domain.collections.usecase.GetCollectionsOverviewsUseCase
import com.example.recipia.core.domain.collections.usecase.GetCollectionsOverviewsUseCaseImpl
import com.example.recipia.core.network.collections.CollectionsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal class CollectionsDomainModule {
    @Provides
    fun provideAddRecipeToCollectionUseCase(repository: CollectionsRepository):
            AddRecipeToCollectionUseCase = AddRecipeToCollectionUseCaseImpl(repository)

    @Provides
    fun provideCreateCollectionUseCase(repository: CollectionsRepository):
            CreateCollectionUseCase = CreateCollectionUseCaseImpl(repository)

    @Provides
    fun provideGetCollectionsOverviewsUseCase(
        repository: CollectionsRepository,
        mapper: CollectionOverviewMapper
    ): GetCollectionsOverviewsUseCase =
        GetCollectionsOverviewsUseCaseImpl(repository, mapper)
}