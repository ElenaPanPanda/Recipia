package recipia.feature.impl.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import recipia.feature.impl.data.repo.RecipeListRepository
import recipia.feature.impl.domain.mapper.RecipeListMapper
import recipia.feature.impl.domain.mapper.RecipeListMapperImpl
import recipia.feature.impl.domain.usecase.GetRecipesUseCase
import recipia.feature.impl.domain.usecase.GetRecipesUseCaseImpl

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