package com.example.recipia.feature.recipedetails.impl.domain.di

import com.example.recipia.feature.recipedetails.impl.data.repo.RecipeDetailsRepository
import com.example.recipia.feature.recipedetails.impl.domain.mapper.RecipeToDetailedMapper
import com.example.recipia.feature.recipedetails.impl.domain.mapper.RecipeToDetailedMapperImpl
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
    fun provideRecipeDetailsMapper(): RecipeToDetailedMapper = RecipeToDetailedMapperImpl()

    @Provides
    fun provideGetRecipeUseCase(
        mapper: RecipeToDetailedMapper,
        repository: RecipeDetailsRepository
    ): GetRecipeUseCase = GetRecipeUseCaseImpl(mapper, repository)
}