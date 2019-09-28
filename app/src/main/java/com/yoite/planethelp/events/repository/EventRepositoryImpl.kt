package com.yoite.planethelp.events.repository

import com.yoite.planethelp.data.api.core.OnApiResultListener
import com.yoite.planethelp.data.api.events.EventApi
import com.yoite.planethelp.data.api.events.models.EventsScheme
import com.yoite.planethelp.data.repository.ErrorModel
import com.yoite.planethelp.data.repository.OnDataListener
import com.yoite.planethelp.events.di.EventsScope
import com.yoite.planethelp.events.mapper.mapToEventModel
import com.yoite.planethelp.events.repository.models.AdvertModel
import com.yoite.planethelp.events.repository.models.CategoryType
import com.yoite.planethelp.events.repository.models.EventModel
import com.yoite.planethelp.events.repository.models.GoalType
import javax.inject.Inject


@EventsScope
class EventRepositoryImpl
@Inject constructor(
    private val eventApi: EventApi
) : EventsRepository {

    override fun getEventsList(listener: OnDataListener<List<EventModel>>) {
        eventApi.getEvents(object : OnApiResultListener<List<EventsScheme>, String> {
            override fun onSuccess(responseScheme: List<EventsScheme>) {
                listener.onData(responseScheme.map { it.mapToEventModel() }, null)
            }

            override fun onFailure(errorScheme: String) {
                listener.onData(null, ErrorModel(400, "1", "LoL"))
            }

        })
    }

    override fun destroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}