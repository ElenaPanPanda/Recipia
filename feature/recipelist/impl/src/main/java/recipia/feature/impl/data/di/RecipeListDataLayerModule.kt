package recipia.feature.impl.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import recipia.feature.impl.data.api.RecipeListNetworkApi
import recipia.feature.impl.data.repo.RecipeListRepository
import recipia.feature.impl.data.repo.RecipeListRepositoryImpl
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
internal class RecipeListDataLayerModule {
    @Provides
    fun provideRecipeListRepository(retrofit: Retrofit): RecipeListRepository {
        return RecipeListRepositoryImpl(retrofit.create(RecipeListNetworkApi::class.java))
    }
}