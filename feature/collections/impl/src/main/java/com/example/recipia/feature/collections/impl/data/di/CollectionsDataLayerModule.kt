package com.example.recipia.feature.collections.impl.data.di

import com.example.recipia.feature.collections.impl.data.api.CollectionsNetworkApi
import com.example.recipia.feature.collections.impl.data.repo.CollectionsRepository
import com.example.recipia.feature.collections.impl.data.repo.CollectionsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
internal class CollectionsDataLayerModule {
    @Provides
    fun provideCollectionsRepository(retrofit: Retrofit): CollectionsRepository {
        return CollectionsRepositoryImpl(retrofit.create(CollectionsNetworkApi::class.java))
    }
}