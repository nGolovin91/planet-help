package com.yoite.planethelp.di.events

import com.yoite.planethelp.events.di.EventsComponent
import dagger.Subcomponent
import javax.inject.Scope


@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class GlobalEventScope

@GlobalEventScope
@Subcomponent(
    modules = [GlobalEventModule::class]
)
interface GlobalEventComponent {
    // ===========================================================
    // Dependencies
    // ===========================================================

    // ===========================================================
    // Subcomponents
    // ===========================================================

    fun getEventsComponent(): EventsComponent
}