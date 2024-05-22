package com.example.exercise3

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class StatusCheckWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        checkAirplaneMode()
        checkBluetoothStatus()
        return Result.success()
    }

    private fun checkAirplaneMode() {
        val isAirplaneModeOn = android.provider.Settings.Global.getInt(
            applicationContext.contentResolver,
            android.provider.Settings.Global.AIRPLANE_MODE_ON, 0
        ) != 0
        Log.i("worker_airplane", "Airplane mode: ${if (isAirplaneModeOn) "ON" else "OFF"}")
    }

    private fun checkBluetoothStatus() {
        val bluetoothAdapter = android.bluetooth.BluetoothAdapter.getDefaultAdapter()
        val isBluetoothOn = bluetoothAdapter?.isEnabled == true
        Log.i("worker_bluetooth", "Bluetooth: ${if (isBluetoothOn) "ON" else "OFF"}")
    }
}
