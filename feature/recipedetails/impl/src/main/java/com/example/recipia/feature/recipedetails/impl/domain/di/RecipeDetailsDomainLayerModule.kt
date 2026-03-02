package com.example.recipia.feature.recipedetails.impl.domain.di

import com.example.datastore.ShoppingListRepository
import com.example.recipia.feature.recipedetails.impl.data.repo.RecipeDetailsRepository
import com.example.recipia.feature.recipedetails.impl.domain.mapper.CollectionToCollectionWithSelectedOptionMapper
import com.example.recipia.feature.recipedetails.impl.domain.mapper.CollectionToCollectionWithSelectedOptionMapperImpl
import com.example.recipia.feature.recipedetails.impl.domain.mapper.DetailedIngredientMapper
import com.example.recipia.feature.recipedetails.impl.domain.mapper.DetailedIngredientMapperImpl
import com.example.recipia.feature.recipedetails.impl.domain.mapper.DetailedIngredientSectionMapper
import com.example.recipia.feature.recipedetails.impl.domain.mapper.DetailedIngredientSectionMapperImpl
import com.example.recipia.feature.recipedetails.impl.domain.mapper.RecipeToDetailedMapper
import com.example.recipia.feature.recipedetails.impl.domain.mapper.RecipeToDetailedMapperImpl
import com.example.recipia.feature.recipedetails.impl.domain.usecase.AddAllIngredientsToShoppingListUseCase
import com.example.recipia.feature.recipedetails.impl.domain.usecase.AddAllIngredientsToShoppingListUseCaseImpl
import com.example.recipia.feature.recipedetails.impl.domain.usecase.AdjustRecipeRatingUseCase
import com.example.recipia.feature.recipedetails.impl.domain.usecase.AdjustRecipeRatingUseCaseImpl
import com.example.recipia.feature.recipedetails.impl.domain.usecase.AddIngredientToShoppingListUseCase
import com.example.recipia.feature.recipedetails.impl.domain.usecase.AddIngredientToShoppingListUseCaseImpl
import com.example.recipia.feature.recipedetails.impl.domain.usecase.AddRecipeToCollectionUseCase
import com.example.recipia.feature.recipedetails.impl.domain.usecase.AddRecipeToCollectionUseCaseImpl
import com.example.recipia.feature.recipedetails.impl.domain.usecase.CheckAddedIngredientsInShoppingListUseCase
import com.example.recipia.feature.recipedetails.impl.domain.usecase.CheckAddedIngredientsInShoppingListUseCaseImpl
import com.example.recipia.feature.recipedetails.impl.domain.usecase.CreateCollectionUseCase
import com.example.recipia.feature.recipedetails.impl.domain.usecase.CreateCollectionUseCaseImpl
import com.example.recipia.feature.recipedetails.impl.domain.usecase.DeleteRecipeUseCase
import com.example.recipia.feature.recipedetails.impl.domain.usecase.DeleteRecipeUseCaseImpl
import com.example.recipia.feature.recipedetails.impl.domain.usecase.GetCollectionsUseCase
import com.example.recipia.feature.recipedetails.impl.domain.usecase.GetCollectionsUseCaseImpl
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

    @Provides
    fun provideCreateCollectionUseCase(
        repository: RecipeDetailsRepository
    ): CreateCollectionUseCase = CreateCollectionUseCaseImpl(repository)

    @Provides
    fun provideGetCollectionsUseCase(
        repository: RecipeDetailsRepository,
        mapper: CollectionToCollectionWithSelectedOptionMapper
    ): GetCollectionsUseCase = GetCollectionsUseCaseImpl(mapper, repository)

    @Provides
    fun provideAddRecipeToCollectionUseCase(
        repository: RecipeDetailsRepository
    ): AddRecipeToCollectionUseCase = AddRecipeToCollectionUseCaseImpl(repository)

    @Provides
    fun provideDeleteRecipeUseCase(
        repository: RecipeDetailsRepository
    ): DeleteRecipeUseCase = DeleteRecipeUseCaseImpl(repository)

    @Provides
    fun provideAdjustRecipeRatingUseCase(
        repository: RecipeDetailsRepository
    ): AdjustRecipeRatingUseCase = AdjustRecipeRatingUseCaseImpl(repository)

    @Provides
    fun provideCollectionToCollectionWithSelectedOptionMapper(): CollectionToCollectionWithSelectedOptionMapper =
        CollectionToCollectionWithSelectedOptionMapperImpl()
}