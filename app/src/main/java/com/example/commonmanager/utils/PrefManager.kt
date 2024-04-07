package com.example.commonmanager.utils

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Singleton
class PrefManager {

    private lateinit var sharedPreferences: SharedPreferences
    fun init(@ApplicationContext context: Context) {
        sharedPreferences = context.getSharedPreferences("common_pref", Context.MODE_PRIVATE)
    }

    fun setValue(key: String, value: Any) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        when (value.javaClass) {
            String::class.java -> {
                editor.putString(key, (value as String));
            }

            Int::class.java -> {
                editor.putInt(key, (value as Int));
            }

            Boolean::class.java -> {
                editor.putBoolean(key, (value as Boolean));
            }

            Float::class.java -> {
                editor.putFloat(key, (value as Float));
            }

            Long::class.java -> {
                editor.putLong(key, (value as Long));
            }

            List::class.java -> {
                val listStr: String = (value as List<*>).toString();
                editor.putString(key, listStr);
            }
        }
        editor.apply()
    }

    fun getString(key: String) {
        sharedPreferences.getString(key, "");
    }

    fun getLong(key: String) {
        sharedPreferences.getLong(key, 0);
    }

    fun getInt(key: String) {
        sharedPreferences.getInt(key, 0);
    }

    fun getBoolean(key: String) {
        sharedPreferences.getBoolean(key, false);
    }

    fun getFloat(key: String) {
        sharedPreferences.getFloat(key, 0f);
    }


}