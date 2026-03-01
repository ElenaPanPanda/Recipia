package com.example.recipia.feature.collections.impl.domain.di

import com.example.recipia.feature.collections.impl.data.repo.CollectionsRepository
import com.example.recipia.feature.collections.impl.domain.usecase.GetCollectionByIdUseCase
import com.example.recipia.feature.collections.impl.domain.usecase.GetCollectionByIdUseCaseImpl
import com.example.recipia.feature.collections.impl.domain.usecase.GetCollectionsUseCase
import com.example.recipia.feature.collections.impl.domain.usecase.GetCollectionsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal class CollectionsDomainLayerModule {
    @Provides
    fun provideGetCollectionsListUseCase(repository: CollectionsRepository):
            GetCollectionsUseCase = GetCollectionsUseCaseImpl(repository)

    @Provides
    fun provideGetCollectionByIdUseCase(repository: CollectionsRepository):
            GetCollectionByIdUseCase = GetCollectionByIdUseCaseImpl(repository)
}