package com.yoite.planethelp.events.di

import com.yoite.planethelp.events.EventsActivity
import com.yoite.planethelp.events.repository.EventsRepository
import dagger.Subcomponent
import javax.inject.Scope


@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class EventsScope

@EventsScope
@Subcomponent(
    modules = [EventsModule::class]
)
interface EventsComponent {
    // ===========================================================
    // Dependencies
    // ===========================================================

    fun getEventRepository(): EventsRepository

    // ===========================================================
    // Injects
    // ===========================================================

    fun inject(activity: EventsActivity)
}