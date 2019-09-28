package com.yoite.planethelp.data.api.events

import com.yoite.planethelp.data.api.core.DefaultHttpClient
import com.yoite.planethelp.data.api.core.DefaultTransport
import com.yoite.planethelp.data.api.core.OnApiResultListener
import com.yoite.planethelp.data.api.core.ServicesFactoryImpl
import com.yoite.planethelp.data.api.events.models.EventsScheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject


class EventTransport @Inject constructor(
    retrofitBuilder: Retrofit.Builder,
    serviceProvider: ServicesFactoryImpl,
    defaultHttpClient: DefaultHttpClient
): DefaultTransport(retrofitBuilder, serviceProvider, defaultHttpClient), EventApi {

    override fun getEvents(listener: OnApiResultListener<List<EventsScheme>, String>) {
        getEventServices().getEvents().enqueue(object : Callback<List<EventsScheme>> {
            override fun onFailure(call: Call<List<EventsScheme>>, t: Throwable) {
                listener.onFailure(t.localizedMessage!!)
            }

            override fun onResponse(call: Call<List<EventsScheme>>, response: Response<List<EventsScheme>>) {
                if (response.isSuccessful && response.body() != null) {
                    listener.onSuccess(response.body()!!)
                }
            }

        })
    }

    override fun createEvent(eventName: String, listener: OnApiResultListener<List<String>, String>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun getEventServices(): EventServices {
        return getRetrofitServices(EventServices.TAG, EventServices::class.java)
    }

}