package com.yoite.planethelp.data.providers.preferences


interface PreferencesProvider {

    fun getInt(key : String) : Int
    fun getInt(key : String, defaultValue: Int) : Int

    fun getLong(key : String) : Long
    fun getLong(key : String, defaultValue: Long) : Long

    fun getFloat(key : String) : Float
    fun getFloat(key : String, defaultValue: Float) : Float

    fun getBoolean(key : String) : Boolean
    fun getBoolean(key : String, defaultValue: Boolean) : Boolean

    fun getString(key : String) : String
    fun getString(key : String, defaultValue: String) : String

    fun put(key : String, value : Int)
    fun put(key : String, value : Long)
    fun put(key : String, value : Float)
    fun put(key : String, value : Boolean)
    fun put(key : String, value : String)

    fun remove(key : String)

    fun clearAll()
}