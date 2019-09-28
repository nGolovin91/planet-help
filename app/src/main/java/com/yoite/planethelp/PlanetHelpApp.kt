package com.yoite.planethelp

import android.app.Application
import com.yoite.planethelp.di.DIManager


class PlanetHelpApp : Application() {

    companion object {
        lateinit var diManager: DIManager
    }

    override fun onCreate() {
        super.onCreate()

        diManager = DIManager(this)
        diManager.appComponent?.inject(this)
    }
}