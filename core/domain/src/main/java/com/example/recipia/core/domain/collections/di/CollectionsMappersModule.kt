package com.example.recipia.core.domain.collections.di

import com.example.recipia.core.domain.collections.mappers.CollectionsOverviewMapper
import com.example.recipia.core.domain.collections.mappers.CollectionsOverviewMapperImpl
import com.example.recipia.core.domain.collections.mappers.UserCollectionMapper
import com.example.recipia.core.domain.collections.mappers.UserCollectionMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal class CollectionsMappersModule {
    @Provides
    fun provideCollectionOverviewMapper():
            CollectionsOverviewMapper = CollectionsOverviewMapperImpl()

    @Provides
    fun provideUserCollectionMapper():
            UserCollectionMapper = UserCollectionMapperImpl()
}