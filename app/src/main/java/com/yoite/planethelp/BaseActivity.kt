package com.yoite.planethelp

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity


abstract class BaseActivity: MvpAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        PlanetHelpApp.diManager.appComponent?.inject(this)
        super.onCreate(savedInstanceState)
    }
}