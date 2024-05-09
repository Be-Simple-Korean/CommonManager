package com.example.commonmanager.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class ChargeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        if (Intent.ACTION_POWER_CONNECTED == action) {
            Toast.makeText(context, "Charger Connected", Toast.LENGTH_SHORT).show()
        } else if (Intent.ACTION_POWER_DISCONNECTED == action) {
            Toast.makeText(context, "Charger Disconnected", Toast.LENGTH_SHORT).show()
        }
    }
}