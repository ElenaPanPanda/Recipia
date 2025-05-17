package recipia.feature.add_recipe.impl.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import recipia.feature.add_recipe.impl.data.repo.AddRecipeRepository
import recipia.feature.add_recipe.impl.domain.mapper.RecipeMapper
import recipia.feature.add_recipe.impl.domain.mapper.RecipeMapperImpl
import recipia.feature.add_recipe.impl.domain.usecase.AddRecipeUseCase
import recipia.feature.add_recipe.impl.domain.usecase.AddRecipeUseCaseImpl

@InstallIn(SingletonComponent::class)
@Module
internal class AddRecipeDomainLayerModule {
    @Provides
    fun provideRecipeMapper(): RecipeMapper = RecipeMapperImpl()

    @Provides
    fun provideAddRecipeUseCase(
        mapper: RecipeMapper,
        repository: AddRecipeRepository,
    ): AddRecipeUseCase =
        AddRecipeUseCaseImpl(mapper, repository)
}