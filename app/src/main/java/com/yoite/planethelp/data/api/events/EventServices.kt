package com.yoite.planethelp.data.api.events

import com.yoite.planethelp.data.api.events.models.EventsScheme
import retrofit2.Call
import retrofit2.http.GET


interface EventServices {

    companion object {
        const val TAG : String = "EventServices"
    }

    @GET("events/list")
    fun getEvents() : Call<List<EventsScheme>>
}