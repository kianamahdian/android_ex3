package com.example.exercise3

import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.work.*
import java.io.File
import java.util.concurrent.TimeUnit

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // Start Internet Connection Service
        startService(Intent(this, InternetConnectionService::class.java))

        // Schedule periodic work for checking Bluetooth and airplane mode status
        val workRequest = PeriodicWorkRequestBuilder<StatusCheckWorker>(
            15, TimeUnit.MINUTES // Adjust this interval as needed
        ).build()

        WorkManager.getInstance(this).enqueue(workRequest)
    }

    fun writeLogToFile(log: String) {
        try {
            val logFile = File(applicationContext.filesDir, "log.txt")
            logFile.appendText("$log\n")
        } catch (e: Exception) {
            Log.e("LogWriter", "Error writing log to file: ${e.message}")
        }
    }
}
