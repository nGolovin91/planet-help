package com.yoite.planethelp.events.di

import com.yoite.planethelp.data.api.events.EventApi
import com.yoite.planethelp.data.api.events.EventTransport
import com.yoite.planethelp.events.EventsPresenter
import com.yoite.planethelp.events.EventsPresenterImpl
import com.yoite.planethelp.events.repository.EventRepositoryImpl
import com.yoite.planethelp.events.repository.EventsRepository
import dagger.Binds
import dagger.Module


@EventsScope
@Module
abstract class EventsModule {

    // ===========================================================
    // Dependencies
    // ===========================================================

    @EventsScope
    @Binds
    abstract fun provideBuyRepository(repository: EventRepositoryImpl): EventsRepository

    // ===========================================================
    // Presenters
    // ===========================================================

    @EventsScope
    @Binds
    abstract fun provideBuyPresenter(presenter: EventsPresenterImpl): EventsPresenter

    // ===========================================================
    // Api
    // ===========================================================
    @EventsScope
    @Binds
    abstract fun provideBuyApi(eventTransport: EventTransport): EventApi

}