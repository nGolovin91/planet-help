package com.yoite.planethelp.data.providers.resources


interface ResourcesProvider {

    fun getString(resourcesId : Int) : String

    fun getString(resourcesId : Int, vararg x: Any) : String

    fun getBool(resourcesId: Int) : Boolean

    fun getInteger(resourcesId: Int) : Int
}