package com.yoite.planethelp.data.providers.resources

import android.app.Application
import android.content.Context
import com.yoite.planethelp.di.app.AppScope
import javax.inject.Inject


@AppScope
class ResourceProviderImpl
@Inject
constructor(
    application : Application
) : ResourcesProvider {

    private val context : Context = application

    override fun getString(resourcesId : Int) : String {
        return context.getString(resourcesId)
    }

    override fun getString(resourcesId : Int, vararg x : Any) : String {
        return context.getString(resourcesId, *x)
    }

    override fun getBool(resourcesId : Int) : Boolean {
        return context.resources.getBoolean(resourcesId)
    }

    override fun getInteger(resourcesId : Int) : Int {
        return context.resources.getInteger(resourcesId)
    }

}