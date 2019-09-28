package com.yoite.planethelp.di.session

import com.yoite.planethelp.di.events.GlobalEventComponent
import dagger.Subcomponent
import javax.inject.Scope


@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class SessionScope

@SessionScope
@Subcomponent(
    modules = [SessionModule::class]
)
interface SessionComponent {
    // ===========================================================
    // Subcomponents
    // ===========================================================

    fun getGlobalEventComponent(): GlobalEventComponent
}