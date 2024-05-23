package com.example.exercise3

import android.app.NotificationManager
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.IBinder
import androidx.core.app.NotificationCompat
import android.util.Log

class InternetConnectionService : Service() {

    private val connectivityManager by lazy {
        getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        registerReceiver(connectionReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        return START_STICKY
    }

    private val connectionReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val networkInfo = connectivityManager.activeNetworkInfo
            val isConnected = networkInfo != null && networkInfo.isConnected
            val message = if (isConnected) "Internet Connected" else "Internet Disconnected"
            displayNotification(message)
            // Log connection status to file
            (applicationContext as MyApp).writeLogToFile("Internet connection: $message")
        }
    }

    private fun displayNotification(message: String) {
        val notification = NotificationCompat.Builder(this, "internet_connection_channel")
            .setContentTitle("Internet Connection Status")
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .build()

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(101, notification)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(connectionReceiver)
    }
}
