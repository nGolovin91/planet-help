package com.yoite.planethelp.di

import android.app.Application
import com.yoite.planethelp.di.app.AppComponent
import com.yoite.planethelp.di.app.DaggerAppComponent
import com.yoite.planethelp.di.events.GlobalEventComponent
import com.yoite.planethelp.di.session.SessionComponent
import com.yoite.planethelp.events.di.EventsComponent


class DIManager(private var application: Application) {

    // ===========================================================
    // AppComponent
    // ===========================================================
    var appComponent: AppComponent? = null
        get() {
            if (field == null) {
                field = DaggerAppComponent.builder()
                    .application(application)
                    .build()
            }

            return field
        }

    // ===========================================================
    // SessionComponent
    // ===========================================================

    var sessionComponent: SessionComponent? = null
        get() {
            if (field == null) {
                field = appComponent?.getSessionComponent()
            }

            return field
        }

    fun releaseSesionComponent() {
        appComponent?.getDefaultPreferencesProvider()?.clearAll()

//        releaseGlobalAuthComponent()
        releaseGlobalEventsComponent()

        sessionComponent = null
    }

    // ===========================================================
    // GlobalEventsComponent
    // ===========================================================

    var globalWalletComponent: GlobalEventComponent? = null
        get() {
            if (field == null) {
                field = sessionComponent?.getGlobalEventComponent()
            }

            return field
        }

    fun releaseGlobalEventsComponent() {
        globalWalletComponent = null
    }

    // ===========================================================
    // EventsComponent
    // ===========================================================

    var eventsComponent: EventsComponent? = null
        get() {
            if (field == null) {
                field = globalWalletComponent?.getEventsComponent()
            }

            return field
        }

    fun releaseEventsComponent() {
        eventsComponent = null
    }
}