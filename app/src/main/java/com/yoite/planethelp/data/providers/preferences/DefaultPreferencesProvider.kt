package com.yoite.planethelp.data.providers.preferences

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.yoite.planethelp.data.providers.preferences.PreferencesConstant.PREFERENCE_KEY_DEFAULT_STORAGE
import com.yoite.planethelp.di.app.AppScope
import javax.inject.Inject


@AppScope
class DefaultPreferencesProvider
@Inject
constructor (
    application: Application
) : PreferencesProvider {

    private val context: Context = application

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFERENCE_KEY_DEFAULT_STORAGE, Context.MODE_PRIVATE)

    override fun getInt(key: String): Int {
        return sharedPreferences.getInt(key, 0)
    }

    override fun getInt(key: String, defaultValue: Int): Int {
        return sharedPreferences.getInt(key, defaultValue)
    }

    override fun getLong(key: String): Long {
        return sharedPreferences.getLong(key, 0L)
    }

    override fun getLong(key: String, defaultValue: Long): Long {
        return sharedPreferences.getLong(key, defaultValue)
    }

    override fun getFloat(key: String): Float {
        return sharedPreferences.getFloat(key, 0f)
    }

    override fun getFloat(key: String, defaultValue: Float): Float {
        return sharedPreferences.getFloat(key, defaultValue)
    }

    override fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    override fun getString(key: String): String {
        return sharedPreferences.getString(key, "")!!
    }

    override fun getString(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key, defaultValue)!!
    }

    override fun put(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    override fun put(key: String, value: Long) {
        sharedPreferences.edit().putLong(key, value).apply()
    }

    override fun put(key: String, value: Float) {
        sharedPreferences.edit().putFloat(key, value).apply()
    }

    override fun put(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    override fun put(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    override fun remove(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }

    override fun clearAll() {
        sharedPreferences.edit().clear().apply()
    }

}