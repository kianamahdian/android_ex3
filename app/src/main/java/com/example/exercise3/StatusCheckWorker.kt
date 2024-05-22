package com.example.exercise3

import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.provider.Settings
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class StatusCheckWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    override fun doWork(): Result {
        // Check Bluetooth status
        val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
        val isBluetoothEnabled = bluetoothAdapter?.isEnabled == true

        // Check Airplane mode status
        val isAirplaneModeOn = Settings.Global.getInt(
            applicationContext.contentResolver,
            Settings.Global.AIRPLANE_MODE_ON, 0
        ) != 0

        // Log the statuses
        Log.i("worker_airplane", "Bluetooth is ${if (isBluetoothEnabled) "enabled" else "disabled"}")
        Log.i("worker_airplane", "Airplane mode is ${if (isAirplaneModeOn) "enabled" else "disabled"}")

        return Result.success()
    }
}
