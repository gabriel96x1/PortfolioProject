package com.rzs.corroutinesproject.domain.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.graphics.drawable.IconCompat
import androidx.lifecycle.MutableLiveData
import com.rzs.corroutinesproject.R
import kotlin.random.Random

private const val CHANNEL_ID = "CHANNEL_BOUNDED"
private const val CHANNEL_NAME = "BOUNDED"
class BoundService : Service() {

    private val localBinder: IBinder = MyBinder()
    val randomNumberLiveData: MutableLiveData<Int> = MutableLiveData()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        Log.d("bound service", "creating bound service...")
        setupNotification()

        Handler().postDelayed({
            val random = randomGenerator(100)
            randomNumberLiveData.postValue(random)
        },1000)
    }

    override fun onBind(intent: Intent): IBinder {
        return localBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
    }

    private fun randomGenerator(seed: Int): Int {
        val random = Random(seed)

        return random.nextInt()
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

    @RequiresApi(Build.VERSION_CODES.O)
    private  fun setupNotification() {
        val notificationManager = getSystemService(NotificationManager::class.java)
        val channel = createChannel()
        notificationManager.createNotificationChannel(channel)

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Notification Bounded")
            .setContentText("starting randoms")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()

        startForeground(1, notification)
    }

    inner class MyBinder : Binder() {
        // Return this instance of MyService so clients can call public methods
        fun getService(): BoundService = this@BoundService
    }
}