package com.example.recipia.core.network.recipes

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
    fun provideRecipesRepository(retrofit: Retrofit): RecipeRepository =
        RecipeRepositoryImpl(retrofit.create(RecipesNetworkApi::class.java))
}