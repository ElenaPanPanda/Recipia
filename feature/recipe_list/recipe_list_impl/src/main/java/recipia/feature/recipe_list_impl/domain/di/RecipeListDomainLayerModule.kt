package recipia.feature.recipe_list_impl.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import recipia.feature.recipe_list_impl.data.repo.RecipeListRepository
import recipia.feature.recipe_list_impl.domain.mapper.RecipeListMapper
import recipia.feature.recipe_list_impl.domain.mapper.RecipeListMapperImpl
import recipia.feature.recipe_list_impl.domain.usecase.GetRecipesUseCase
import recipia.feature.recipe_list_impl.domain.usecase.GetRecipesUseCaseImpl

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
}