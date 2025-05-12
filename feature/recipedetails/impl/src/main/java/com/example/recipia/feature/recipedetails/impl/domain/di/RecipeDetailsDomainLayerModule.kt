package com.example.recipia.feature.recipedetails.impl.domain.di

import com.example.recipia.feature.recipedetails.impl.data.repo.RecipeDetailsRepository
import com.example.recipia.feature.recipedetails.impl.domain.mapper.RecipeMapper
import com.example.recipia.feature.recipedetails.impl.domain.mapper.RecipeMapperImpl
import com.example.recipia.feature.recipedetails.impl.domain.usecase.GetRecipeUseCase
import com.example.recipia.feature.recipedetails.impl.domain.usecase.GetRecipeUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal class RecipeDetailsDomainLayerModule {
    @Provides
    fun provideRecipeDetailsMapper(): RecipeMapper = RecipeMapperImpl()

    @Provides
    fun provideGetRecipeUseCase(
        mapper: RecipeMapper,
        repository: RecipeDetailsRepository
    ): GetRecipeUseCase = GetRecipeUseCaseImpl(mapper, repository)
}