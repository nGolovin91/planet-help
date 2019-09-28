package com.yoite.planethelp.data.api.events.models


data class EventScheme(
    var event_id: Long?,
    var caption: String?,
    var description: String?,
    var category: Int?,
    var goal: Int?,
    var pic: String?,
    var advert_id: Long?,
    var goal_target: Int?,
    var goal_count: Int?,
    var lat: Double?,
    var long: Double?
)