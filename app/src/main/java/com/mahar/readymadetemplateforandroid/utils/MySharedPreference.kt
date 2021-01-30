package com.comquas.mizzimaapp.utils

import android.content.Context
import android.content.SharedPreferences

object MySharedPreference {

    @Volatile
    private var preference: SharedPreferences? = null

    private fun createPreference(context: Context): SharedPreferences {
        val name = "${context.applicationContext.packageName}.my_share_preference"
        return context.getSharedPreferences(name, Context.MODE_PRIVATE)
    }

    private fun getPreference(context: Context): SharedPreferences {
        synchronized(this) {
            var instance = preference
            if (instance == null) {
                instance = createPreference(context)
                preference = instance
            }
            return instance
        }
    }

    fun getString(context: Context, key: String) = getPreference(context).getString(key, "")

    fun getBoolean(context: Context, key: String) = getPreference(context).getBoolean(key, false)


    fun setBoolean(context: Context, key: String, value: Boolean) {
        getPreference(context).edit().apply {
            putBoolean(key, value)
        }.apply()
    }


    fun setString(context: Context, key: String, value: String) {
        with(getPreference(context).edit()) {
            putString(key, value)
            commit()
        }
    }

    fun clearSharedPreferenceByKey(context: Context, key: String) {
        with(getPreference(context).edit()) {
            remove(key)
            commit()
        }
    }

    fun clearSharedPreference(context: Context) {
        with(getPreference(context).edit()) {
            clear()
            commit()
        }
    }


}