package com.example.exercise3

import android.app.Application
import androidx.work.*
import java.util.concurrent.TimeUnit

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // Create a periodic work request for StatusCheckWorker
        val periodicWorkRequest = PeriodicWorkRequestBuilder<StatusCheckWorker>(
            15, TimeUnit.MINUTES,  // Repeat interval (minimum is 15 minutes)
            5, TimeUnit.MINUTES    // Flex interval
        ).build()

        // Enqueue the work request
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "StatusCheckWork",
            ExistingPeriodicWorkPolicy.KEEP,
            periodicWorkRequest
        )
    }
}
