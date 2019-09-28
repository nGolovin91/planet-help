package com.yoite.planethelp.events

import com.arellomobile.mvp.InjectViewState
import com.yoite.planethelp.data.repository.ErrorModel
import com.yoite.planethelp.data.repository.OnDataListener
import com.yoite.planethelp.events.di.EventsScope
import com.yoite.planethelp.events.repository.EventsRepository
import com.yoite.planethelp.events.repository.models.EventModel
import com.yoite.planethelp.events.repository.models.GoalType
import com.yoite.planethelp.utils.mvp.BasePresenter
import javax.inject.Inject


@EventsScope
@InjectViewState
class EventsPresenterImpl
@Inject constructor(
    private val repository: EventsRepository
) : BasePresenter<EventsView>(), EventsPresenter {

    private val eventList: MutableList<EventModel> = emptyList<EventModel>().toMutableList()
    private var onlyVolunt: Boolean = false

    override fun attachView(view: EventsView) {
        super.attachView(view)
        viewState.showMap()
        viewState.showEvents(eventList)

        repository.getEventsList(object: OnDataListener<List<EventModel>>{
            override fun onData(t: List<EventModel>?, e: ErrorModel?) {
                if (t != null) {
                    eventList.clear()
                    eventList.addAll(t)
                    if (onlyVolunt) {
                        viewState.showEvents(eventList.filter { it.goal == GoalType.Volunteering })
                    } else {
                        viewState.showEvents(eventList)
                    }
                }
            }
        })
    }

    override fun onMapAction() {
        viewState.showMap()
    }

    override fun onListAction() {
        viewState.showEventsList()
    }

    override fun onOnlyVoluntareAction(enabled: Boolean) {
        onlyVolunt = enabled
        if (enabled) {
            viewState.showEvents(eventList.filter { it.goal == GoalType.Volunteering })
        } else {
            viewState.showEvents(eventList)
        }
    }

    override fun onEventAction(event: EventModel) {
        viewState.showEvent(event)
    }

    override fun onAddEventAction() {

    }

    override fun onFilterAction() {

    }

}