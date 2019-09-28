package com.yoite.planethelp.data.api.core

import com.yoite.planethelp.di.app.AppScope
import javax.inject.Inject


@AppScope
class ServicesFactoryImpl
@Inject
constructor() {

    private val serviceMap : MutableMap<String, Any> = HashMap<String, Any>()

    private fun getApiServices() : MutableMap<String, Any> {
        return serviceMap
    }

    fun <Service> getService(className : String, serviceClass : Class<Service>, factory : ServiceFactory<Service>) : Service {
        val service : Service

        if (!getApiServices().containsKey(className)) {
            service = factory.createService()

            getApiServices().put(className, service!!)
        } else {
            service = serviceClass.cast(getApiServices().get(className))!!
        }

        return service
    }

    fun destroyAllServices() {
        getApiServices().clear()
    }
}