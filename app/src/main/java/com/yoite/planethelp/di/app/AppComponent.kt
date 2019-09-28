package com.yoite.planethelp.di.app

import android.app.Application
import com.yoite.planethelp.BaseActivity
import com.yoite.planethelp.PlanetHelpApp
import com.yoite.planethelp.data.api.core.DefaultHttpClient
import com.yoite.planethelp.data.api.core.ServicesFactoryImpl
import com.yoite.planethelp.data.providers.preferences.DefaultPreferencesProvider
import com.yoite.planethelp.di.session.SessionComponent
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Scope


@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AppScope


@AppScope
@Component(
    modules = [AppModule::class, NetworkModule::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    // ===========================================================
    // Subcomponents
    // ===========================================================

    fun getSessionComponent(): SessionComponent

    // ===========================================================
    // Dependencies
    // ===========================================================

    fun getDefaultHttpClient() : DefaultHttpClient

    fun getServicesFactory() : ServicesFactoryImpl

    fun getDefaultPreferencesProvider(): DefaultPreferencesProvider

    // ===========================================================
    // Injects
    // ===========================================================

    fun inject(application: PlanetHelpApp)
    fun inject(activity: BaseActivity)
}