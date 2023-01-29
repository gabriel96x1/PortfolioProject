package com.rzs.corroutinesproject.domain.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.rzs.corroutinesproject.domain.services.ForegroundService

const val ACTION_CANCEL = "ACTION_CANCEL"

class StopForegroundServiceReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action.equals(ACTION_CANCEL)) {
            // Here You will cancel the alarm.
            val stopServiceIntent = Intent(context, ForegroundService::class.java)
            context?.stopService(stopServiceIntent)
        }
    }
}