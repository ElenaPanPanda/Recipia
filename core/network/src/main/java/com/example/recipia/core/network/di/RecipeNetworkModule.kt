package com.example.recipia.core.network.di

import com.example.recipia.core.network.api.RecipesNetworkApi
import com.example.recipia.core.network.repository.RecipeRepository
import com.example.recipia.core.network.repository.RecipeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RecipeNetworkModule {
    @Provides
    @Singleton
    fun provideRecipesRepository(retrofit: Retrofit): RecipeRepository {
        return RecipeRepositoryImpl(retrofit.create(RecipesNetworkApi::class.java))
    }
}