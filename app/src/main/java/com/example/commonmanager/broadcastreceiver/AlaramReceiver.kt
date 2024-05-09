package com.example.commonmanager.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.commonmanager.utils.getCurrentTime

class AlaramReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.e("onRecive",">> ${getCurrentTime()}")
    }
}