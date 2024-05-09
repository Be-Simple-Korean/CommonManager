package com.example.commonmanager

import android.app.Application
import com.example.commonmanager.utils.PrefManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()

        PrefManager().init(this);
    }
}