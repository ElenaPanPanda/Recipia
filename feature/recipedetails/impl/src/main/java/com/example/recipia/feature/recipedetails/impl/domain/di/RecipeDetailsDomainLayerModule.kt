package com.example.recipia.feature.recipedetails.impl.domain.di

import com.example.datastore.ShoppingListRepository
import com.example.recipia.feature.recipedetails.impl.domain.mapper.DetailedIngredientMapper
import com.example.recipia.feature.recipedetails.impl.domain.mapper.DetailedIngredientMapperImpl
import com.example.recipia.feature.recipedetails.impl.domain.mapper.DetailedIngredientSectionMapper
import com.example.recipia.feature.recipedetails.impl.domain.mapper.DetailedIngredientSectionMapperImpl
import com.example.recipia.feature.recipedetails.impl.domain.mapper.RecipeToDetailedMapper
import com.example.recipia.feature.recipedetails.impl.domain.mapper.RecipeToDetailedMapperImpl
import com.example.recipia.feature.recipedetails.impl.domain.usecase.AddIngredientToShoppingListUseCase
import com.example.recipia.feature.recipedetails.impl.domain.usecase.AddIngredientToShoppingListUseCaseImpl
import com.example.recipia.feature.recipedetails.impl.domain.usecase.CheckAddedIngredientsInShoppingListUseCase
import com.example.recipia.feature.recipedetails.impl.domain.usecase.CheckAddedIngredientsInShoppingListUseCaseImpl
import com.example.recipia.feature.recipedetails.impl.domain.usecase.UpdateShoppingListUseCase
import com.example.recipia.feature.recipedetails.impl.domain.usecase.UpdateShoppingListUseCaseImpl
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
    fun provideDetailedIngredientMapper(): DetailedIngredientMapper = DetailedIngredientMapperImpl()

    @Provides
    fun provideDetailedIngredientSectionMapper(detailedIngredientMapper: DetailedIngredientMapper): DetailedIngredientSectionMapper =
        DetailedIngredientSectionMapperImpl(detailedIngredientMapper)

    @Provides
    fun provideCheckAddedIngredientsInShoppingListUseCase(
        shoppingListRepository: ShoppingListRepository,
        mapper: DetailedIngredientMapper,
    ): CheckAddedIngredientsInShoppingListUseCase =
        CheckAddedIngredientsInShoppingListUseCaseImpl(shoppingListRepository, mapper)

    @Provides
    fun provideAddIngredientToShoppingListUseCase(
        shoppingListRepository: ShoppingListRepository,
        mapper: DetailedIngredientSectionMapper,
    ): AddIngredientToShoppingListUseCase =
        AddIngredientToShoppingListUseCaseImpl(shoppingListRepository, mapper)

    @Provides
    fun provideUpdateShoppingListUseCase(
        shoppingListRepository: ShoppingListRepository,
        mapper: DetailedIngredientSectionMapper,
    ): UpdateShoppingListUseCase = UpdateShoppingListUseCaseImpl(shoppingListRepository, mapper)
}