package com.assignment.myapplicationtrial.network

import android.content.Context
import android.os.Handler
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class ISSWorker(context: Context, params: WorkerParameters): CoroutineWorker(context, params) {
    private val issTracker = ISSTracker()

    override suspend fun doWork(): Result  {
        return try {
            val issPosition = issTracker.getISSPosition()
            issPosition?.let {
                //Log.d("ISSWorker", "ISS position: $it")
                issTracker.updateMap(it.latitude, it.longitude)
            }
            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            //Log.e("ISSWorker", "Error in ISSWorker", e)
            Result.retry()
        }
    }
}