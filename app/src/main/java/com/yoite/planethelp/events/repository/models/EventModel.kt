package com.yoite.planethelp.events.repository.models

import java.io.Serializable


data class EventModel(
    var id: Long = 0,
    var caption: String = "",
    var description: String = "",
    var category: CategoryType = CategoryType.Undefined,
    var goal: GoalType = GoalType.Undefined,
    var picture: String = "",
    var advertModel: AdvertModel = AdvertModel(),
    var goalTarget: Int = 0,
    var goalCount: Int = 0,
    var likeCount: Int = 0,
    var lat: Double = 0.0,
    var long: Double = 0.0
) : Serializable