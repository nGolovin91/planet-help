package com.yoite.planethelp.data.api.core


interface ServiceFactory<Service> {
    fun createService() : Service
}