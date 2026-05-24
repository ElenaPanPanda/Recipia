package com.example.recipia.core.network.collections

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CollectionsNetworkModule {
    @Provides
    @Singleton
    fun provideCollectionsRepository(retrofit: Retrofit): CollectionsRepository =
        CollectionsRepositoryImpl(retrofit.create(CollectionsNetworkApi::class.java))
}