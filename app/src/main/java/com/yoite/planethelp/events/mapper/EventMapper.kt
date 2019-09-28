package com.yoite.planethelp.events.mapper

import com.yoite.planethelp.data.api.events.models.AdvertScheme
import com.yoite.planethelp.data.api.events.models.EventScheme
import com.yoite.planethelp.data.api.events.models.EventsScheme
import com.yoite.planethelp.events.repository.models.*


fun EventScheme.mapToEventModel(advertsScheme: List<AdvertScheme>) =
    EventModel().also { eventModel ->
        eventModel.id = event_id ?: 0
        eventModel.caption = caption ?: ""
        eventModel.description = description ?: ""
        eventModel.picture = pic ?: ""
        eventModel.goalTarget = goal_target ?: 0
        eventModel.goalCount = goal_count ?: 0
        eventModel.goal = getGoalByType(goal ?: -1)
        eventModel.category = valuesOfType(category ?: -1)
        eventModel.advertModel = advertsScheme.find { it.id == advert_id }?.mapToAdvertModel() ?: AdvertModel()
    }

fun AdvertScheme.mapToAdvertModel() =
    AdvertModel().also {
        it.address = address ?: ""
        it.email = email ?: ""
        it.id = id ?: 0
        it.name = name ?: ""
        it.phone = phone ?: ""
    }

fun EventsScheme.mapToEventModel() =
    EventModel().also {eventModel ->
        eventModel.id = Event?.event_id ?: 0
        eventModel.caption = Event?.caption ?: ""
        eventModel.description = Event?.description ?: ""
        eventModel.picture = Event?.pic ?: ""
        eventModel.goalTarget = Event?.goal_target ?: 0
        eventModel.goalCount = Event?.goal_count ?: 0
        eventModel.goal = getGoalByType(Event?.goal ?: -1)
        eventModel.category = valuesOfType(Event?.category ?: -1)
        eventModel.advertModel = Advert?.mapToAdvertModel() ?: AdvertModel()
        eventModel.likeCount = Likes ?: 0
        eventModel.lat = Event?.lat ?: 0.0
        eventModel.long = Event?.long ?: 0.0
    }