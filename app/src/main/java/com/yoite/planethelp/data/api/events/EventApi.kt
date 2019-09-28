package com.yoite.planethelp.data.api.events

import com.yoite.planethelp.data.api.core.OnApiResultListener
import com.yoite.planethelp.data.api.events.models.EventsScheme


interface EventApi {
    fun getEvents(listener: OnApiResultListener<List<EventsScheme>, String>)
    fun createEvent(eventName: String, listener: OnApiResultListener<List<String>, String>)
}