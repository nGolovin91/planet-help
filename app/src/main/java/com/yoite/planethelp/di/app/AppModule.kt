package com.yoite.planethelp.di.app

import android.app.Application
import android.content.Context
import com.yoite.planethelp.data.providers.preferences.DefaultPreferencesProvider
import com.yoite.planethelp.data.providers.preferences.PreferencesProvider
import com.yoite.planethelp.data.providers.resources.ResourceProviderImpl
import com.yoite.planethelp.data.providers.resources.ResourcesProvider
import dagger.Binds
import dagger.Module


@AppScope
@Module
abstract class AppModule {

    @AppScope
    @Binds
    abstract fun provideContext(application: Application): Context

    // ===========================================================
    // Dependencies
    // ===========================================================

    @AppScope
    @Binds
    abstract fun provideResourceProvider(provider: ResourceProviderImpl): ResourcesProvider

    @AppScope
    @Binds
    abstract fun provideDefaultPreferencesProvider(provider : DefaultPreferencesProvider) : PreferencesProvider

}