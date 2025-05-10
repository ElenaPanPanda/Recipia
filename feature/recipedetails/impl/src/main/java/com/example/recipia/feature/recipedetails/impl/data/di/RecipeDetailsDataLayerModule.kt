package com.example.recipia.feature.recipedetails.impl.data.di

import com.example.recipia.feature.recipedetails.impl.data.api.RecipeDetailsNetworkApi
import com.example.recipia.feature.recipedetails.impl.data.repo.RecipeDetailsRepository
import com.example.recipia.feature.recipedetails.impl.data.repo.RecipeDetailsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
internal class RecipeDetailsDataLayerModule {
    @Provides
    fun provideRecipeDetailsRepository(retrofit: Retrofit): RecipeDetailsRepository {
        return RecipeDetailsRepositoryImpl(retrofit.create(RecipeDetailsNetworkApi::class.java))
    }
}