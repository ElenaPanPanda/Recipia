package com.example.recipia.core.domain.collections.di

import com.example.recipia.core.domain.collections.mappers.CollectionOverviewMapper
import com.example.recipia.core.domain.collections.mappers.CollectionOverviewMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal class CollectionsMappersModule {
    @Provides
    fun provideCollectionOverviewMapper():
            CollectionOverviewMapper = CollectionOverviewMapperImpl()
}