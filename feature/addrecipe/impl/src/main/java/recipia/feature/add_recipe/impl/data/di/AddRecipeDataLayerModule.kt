package recipia.feature.add_recipe.impl.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import recipia.feature.add_recipe.impl.data.api.AddRecipeNetworkApi
import recipia.feature.add_recipe.impl.data.repo.AddRecipeRepository
import recipia.feature.add_recipe.impl.data.repo.AddRecipeRepositoryImpl
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
internal class AddRecipeDataLayerModule {
    @Provides
    fun provideAddRecipeRepository(retrofit: Retrofit): AddRecipeRepository {
        return AddRecipeRepositoryImpl(retrofit.create(AddRecipeNetworkApi::class.java))
    }
}