package com.rzs.corroutinesproject.domain.services

import android.app.*
import android.content.Intent
import android.os.Build
import android.os.CountDownTimer
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.graphics.drawable.IconCompat
import com.rzs.corroutinesproject.R
import com.rzs.corroutinesproject.domain.receivers.ACTION_CANCEL
import com.rzs.corroutinesproject.domain.receivers.StopForegroundServiceReceiver
import com.rzs.corroutinesproject.presentation.MainActivity


private const val CHANNEL_ID = "CHANNEL_FOREGROUND"
private const val CHANNEL_NAME = "FOREGROUND"

class ForegroundService : Service() {

    lateinit var notification: Notification

    override fun onBind(intent: Intent): IBinder? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)

        val stopServiceIntent = Intent(this, StopForegroundServiceReceiver::class.java)
        stopServiceIntent.action = ACTION_CANCEL
        val pendingIntent =
            PendingIntent.getBroadcast(this, 0, stopServiceIntent, PendingIntent.FLAG_CANCEL_CURRENT)

        val notifyIntent = Intent(this, MainActivity::class.java)
        notifyIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntentActivity =
            PendingIntent.getActivity(this, 0, notifyIntent ,PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        val notificationManager = getSystemService(NotificationManager::class.java)
        val channel = createChannel()
        notificationManager.createNotificationChannel(channel)

        notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(getString(R.string.foreground_service_title))
            .setContentText("some notification")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .addAction(
                NotificationCompat.Action(
                    IconCompat.createWithResource(this, R.drawable.ic_launcher_foreground),
                    "Stop Service",
                    pendingIntent
                )
            )
            .setContentIntent(pendingIntentActivity)
            .build()
        startForeground(1, notification)

        return START_NOT_STICKY
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel() =
        // 1
        NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {

            // 2
            description = "CHANNEL_DESCRIPTION"
            setSound(null, null)
        }
}