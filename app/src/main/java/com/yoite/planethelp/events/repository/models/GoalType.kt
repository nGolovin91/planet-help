package com.yoite.planethelp.events.repository.models

enum class GoalType(private val type: Int) {
    Volunteering(0),
    Money(1),
    Undefined( -1);
}

fun getGoalByType(type: Int): GoalType {
    return when (type) {
        0 -> GoalType.Volunteering
        1 -> GoalType.Money
        else -> GoalType.Undefined
    }
}