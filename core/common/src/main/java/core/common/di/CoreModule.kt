package core.common.di

import android.content.Context
import core.common.string_res_provider.StringResProvider
import core.common.string_res_provider.StringResProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoreModule {
    @Provides
    @Singleton
    fun provideA2EStringResourcesProvider(@ApplicationContext context: Context): StringResProvider {
        return StringResProviderImpl(context)
    }
}