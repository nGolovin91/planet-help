package com.yoite.planethelp.events.repository

import com.yoite.planethelp.data.repository.OnDataListener
import com.yoite.planethelp.data.repository.Repository
import com.yoite.planethelp.events.repository.models.EventModel


interface EventsRepository: Repository {
    fun getEventsList(listener: OnDataListener<List<EventModel>>)
}