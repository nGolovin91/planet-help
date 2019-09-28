package com.yoite.planethelp.events.repository.models


enum class CategoryType(private val type: Int) {
    Animals(1),
    Ecology(2),
    Health(3),
    Emergency(4),
    Children(5),
    Homeless(6),
    Charity(7),
    Undefined(-1);
}

fun valuesOfType(type: Int): CategoryType {
    return when (type) {
        1 -> CategoryType.Animals
        2 -> CategoryType.Ecology
        3 -> CategoryType.Health
        4 -> CategoryType.Emergency
        5 -> CategoryType.Children
        6 -> CategoryType.Homeless
        7 -> CategoryType.Charity
        else -> CategoryType.Undefined
    }
}