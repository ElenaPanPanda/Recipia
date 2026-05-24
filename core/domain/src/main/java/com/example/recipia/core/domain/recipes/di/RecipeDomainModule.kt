package com.example.recipia.core.domain.recipes.di

import com.example.recipia.core.domain.recipes.mappers.RecipeListMapper
import com.example.recipia.core.domain.recipes.usecase.AddRecipeUseCase
import com.example.recipia.core.domain.recipes.usecase.AddRecipeUseCaseImpl
import com.example.recipia.core.domain.recipes.usecase.AdjustRecipeRatingUseCase
import com.example.recipia.core.domain.recipes.usecase.AdjustRecipeRatingUseCaseImpl
import com.example.recipia.core.domain.recipes.usecase.DeleteRecipeUseCase
import com.example.recipia.core.domain.recipes.usecase.DeleteRecipeUseCaseImpl
import com.example.recipia.core.domain.recipes.usecase.GetRecipeUseCase
import com.example.recipia.core.domain.recipes.usecase.GetRecipeUseCaseImpl
import com.example.recipia.core.domain.recipes.usecase.GetRecipesUseCase
import com.example.recipia.core.domain.recipes.usecase.GetRecipesUseCaseImpl
import com.example.recipia.core.network.recipes.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal class RecipeDomainModule {
    @Provides
    fun provideGetRecipesUseCase(
        mapper: RecipeListMapper,
        repository: RecipeRepository
    ): GetRecipesUseCase = GetRecipesUseCaseImpl(mapper, repository)

    @Provides
    fun provideGetRecipeUseCase(
        repository: RecipeRepository
    ): GetRecipeUseCase = GetRecipeUseCaseImpl(repository)

    @Provides
    fun provideAddRecipeUseCase(
        repository: RecipeRepository
    ): AddRecipeUseCase = AddRecipeUseCaseImpl(repository)

    @Provides
    fun provideAdjustRecipeRatingUseCase(
        repository: RecipeRepository
    ): AdjustRecipeRatingUseCase = AdjustRecipeRatingUseCaseImpl(repository)

    @Provides
    fun provideDeleteRecipeUseCase(
        repository: RecipeRepository
    ): DeleteRecipeUseCase = DeleteRecipeUseCaseImpl(repository)
}