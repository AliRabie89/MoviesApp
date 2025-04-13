

package com.ali.moviesApp.main.data.preference

import android.content.SharedPreferences
import com.google.gson.Gson
import javax.inject.Inject


class SharedPreferencesManager @Inject constructor(val sharedPreferences: SharedPreferences) {
    inline fun <reified T> get(key: String, defaultValue: T): T {
        return when (defaultValue) {
            is String -> sharedPreferences.getString(key, defaultValue) as T
            is Int -> sharedPreferences.getInt(key, defaultValue) as T
            is Long -> sharedPreferences.getLong(key, defaultValue) as T
            is Float -> sharedPreferences.getFloat(key, defaultValue) as T
            is Boolean -> sharedPreferences.getBoolean(key, defaultValue) as T
            else -> throw IllegalArgumentException("Unsupported data type")
        }
    }

    inline fun <reified T> put(key: String, value: T) {
        val editor = sharedPreferences.edit()
        when (value) {
            is String -> editor.putString(key, value)
            is Int -> editor.putInt(key, value)
            is Long -> editor.putLong(key, value)
            is Float -> editor.putFloat(key, value)
            is Boolean -> editor.putBoolean(key, value)
            else -> throw IllegalArgumentException("Unsupported data type")
        }
        editor.apply()
    }

    inline fun <reified T> getObject(key: String, defaultValue: T): T {
        val jsonValue = sharedPreferences.getString(key, null)
        return if (jsonValue != null) {
            Gson().fromJson(jsonValue, T::class.java)
        } else {
            defaultValue
        }
    }

    inline fun <reified T> putObject(key: String, value: T) {
        val jsonValue = Gson().toJson(value)
        val editor = sharedPreferences.edit()
        editor.putString(key, jsonValue)
        editor.apply()
    }

    fun clearAllPreferences() {
        sharedPreferences.edit().clear().apply()
    }

    fun clearPreference(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }
}