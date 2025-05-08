package recipia.feature.recipe_list_impl.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import recipia.feature.recipe_list_impl.data.repo.RecipeListRepository
import recipia.feature.recipe_list_impl.domain.mapper.RecipeListMapper
import recipia.feature.recipe_list_impl.domain.mapper.RecipeListMapperImpl
import recipia.feature.recipe_list_impl.domain.mapper.ShortRecipeMapper
import recipia.feature.recipe_list_impl.domain.mapper.ShortRecipeMapperImpl
import recipia.feature.recipe_list_impl.domain.usecase.GetRecipesUseCase
import recipia.feature.recipe_list_impl.domain.usecase.GetRecipesUseCaseImpl
import recipia.feature.recipe_list_impl.domain.usecase.LikeRecipeUseCase
import recipia.feature.recipe_list_impl.domain.usecase.LikeRecipeUseCaseImpl

@InstallIn(SingletonComponent::class)
@Module
internal class RecipeListDomainLayerModule {
    @Provides
    fun provideRecipeListMapper(): RecipeListMapper = RecipeListMapperImpl()

    @Provides
    fun provideGetRecipesUseCase(
        mapper: RecipeListMapper,
        repository: RecipeListRepository
    ): GetRecipesUseCase = GetRecipesUseCaseImpl(mapper, repository)

    @Provides
    fun provideShortRecipeMapper(): ShortRecipeMapper = ShortRecipeMapperImpl()

    @Provides
    fun provideLikeRecipeUseCase(
        mapper: ShortRecipeMapper,
        repository: RecipeListRepository
    ): LikeRecipeUseCase = LikeRecipeUseCaseImpl(mapper,repository)
}