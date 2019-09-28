package com.yoite.planethelp.data.api.core

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.yoite.planethelp.di.app.AppScope
import java.util.*
import javax.inject.Inject


@AppScope
class ParserImpl : Parser {

    object ParserImplProvider {
        fun provideParserImpl() : ParserImpl {
            return ParserImpl()
        }
    }

    @Inject
    constructor(
    )

    private val defaultGson : Gson = GsonBuilder().create()

    override fun getDefaultParser(): Gson {
        return defaultGson
    }

    override fun <T> parse(jsonString: String, clazz: Class<T>): T {
        return defaultGson.fromJson(jsonString, clazz)
    }

    override fun <T> parse(jsonObject: JsonObject, clazz: Class<T>): T {
        return getDefaultParser().fromJson(jsonObject, clazz)
    }

    override fun <T> parseList(jsonArray: JsonArray, clazz: Class<Array<T>>): List<T> {
        val array = getDefaultParser().fromJson(jsonArray, clazz)

        return Arrays.asList(*array)
    }

    override fun parseObjectToString(any: Any): String {
        return getDefaultParser().toJson(any)
    }

}