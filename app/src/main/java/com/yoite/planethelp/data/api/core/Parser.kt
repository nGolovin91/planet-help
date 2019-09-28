package com.yoite.planethelp.data.api.core

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject


interface Parser {

    fun getDefaultParser(): Gson

    fun <T> parse(jsonString : String, clazz: Class<T>) : T

    fun <T> parse(jsonObject: JsonObject, clazz: Class<T>): T

    fun <T> parseList(jsonArray: JsonArray, clazz: Class<Array<T>>): List<T>

    fun parseObjectToString(any: Any): String

}