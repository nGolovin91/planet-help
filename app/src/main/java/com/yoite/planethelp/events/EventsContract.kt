package com.yoite.planethelp.events

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.yoite.planethelp.events.repository.models.EventModel

@StateStrategyType(AddToEndStrategy::class)
interface EventsView: MvpView {
    fun showEventsList()
    fun showMap()

    fun showEvents(events: List<EventModel>)

    fun showFilters()
    fun showEvent(event: EventModel)

    fun showCreateEventForm()
}

interface EventsPresenter {
    fun onMapAction()
    fun onListAction()
    fun onOnlyVoluntareAction(enabled: Boolean)
    fun onEventAction(event: EventModel)
    fun onAddEventAction()
    fun onFilterAction()
}