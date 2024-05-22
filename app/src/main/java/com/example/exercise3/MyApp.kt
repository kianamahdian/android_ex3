package com.example.exercise3

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()

        // Schedule the periodic work
        val workRequest = PeriodicWorkRequestBuilder<StatusCheckWorker>(2, TimeUnit.MINUTES)
            .build()

        WorkManager.getInstance(this).enqueue(workRequest)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "internet_connection_channel",
                "Internet Connection",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }
}
