package com.example.recipia.feature.recipedetails.impl.domain.di

import com.example.datastore.ShoppingListRepository
import com.example.recipia.feature.recipedetails.impl.data.repo.RecipeDetailsRepository
import com.example.recipia.feature.recipedetails.impl.domain.mapper.DetailedIngredientMapper
import com.example.recipia.feature.recipedetails.impl.domain.mapper.DetailedIngredientMapperImpl
import com.example.recipia.feature.recipedetails.impl.domain.mapper.DetailedIngredientSectionMapper
import com.example.recipia.feature.recipedetails.impl.domain.mapper.DetailedIngredientSectionMapperImpl
import com.example.recipia.feature.recipedetails.impl.domain.mapper.RecipeToDetailedMapper
import com.example.recipia.feature.recipedetails.impl.domain.mapper.RecipeToDetailedMapperImpl
import com.example.recipia.feature.recipedetails.impl.domain.usecase.AddAllIngredientsToShoppingListUseCase
import com.example.recipia.feature.recipedetails.impl.domain.usecase.AddAllIngredientsToShoppingListUseCaseImpl
import com.example.recipia.feature.recipedetails.impl.domain.usecase.CheckAddedIngredientsInShoppingListUseCase
import com.example.recipia.feature.recipedetails.impl.domain.usecase.CheckAddedIngredientsInShoppingListUseCaseImpl
import com.example.recipia.feature.recipedetails.impl.domain.usecase.GetRecipeUseCase
import com.example.recipia.feature.recipedetails.impl.domain.usecase.GetRecipeUseCaseImpl
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
    fun provideGetRecipeUseCase(
        mapper: RecipeToDetailedMapper,
        repository: RecipeDetailsRepository,
    ): GetRecipeUseCase = GetRecipeUseCaseImpl(mapper, repository)

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
    fun provideAddAllIngredientsToShoppingListUseCase(
        shoppingListRepository: ShoppingListRepository,
        mapper: DetailedIngredientMapper,
    ): AddAllIngredientsToShoppingListUseCase =
        AddAllIngredientsToShoppingListUseCaseImpl(shoppingListRepository, mapper)

    @Provides
    fun provideUpdateShoppingListUseCase(
        shoppingListRepository: ShoppingListRepository,
        mapper: DetailedIngredientSectionMapper,
    ): UpdateShoppingListUseCase = UpdateShoppingListUseCaseImpl(shoppingListRepository, mapper)
}