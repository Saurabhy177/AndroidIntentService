package com.example.androidintentservice

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.JobIntentService

/**
 * JobIntentService is deprecated and it can be replaced with either:
 * a) a foreground service
 * b) a WorkManager with a long-running worker
 * */
class MyIntentService: JobIntentService() {

    init {
        instance = this
    }

    companion object {
        private lateinit var instance: MyIntentService
        var isRunning = false

        /**
         * This method can be called from any activity/fragment to start a new service.
         * Note: We can start multiple services at a time.
         * */
        fun enqueueWork(context: Context, intent: Intent) {
            Log.d("MyIntentService", "Service is starting ...")
            enqueueWork(context, MyIntentService::class.java, 1, intent)
        }

        /**
         * This method can be called from any activity/fragment to stop any current service.
         * NOTE: For each service, we'll have to call this method.
         * */
        fun stopService() {
            Log.d("MyIntentService", "Service is stopping ...")
            isRunning = false
            instance.stopSelf()
        }
    }

    override fun onHandleWork(intent: Intent) {
        try {
            isRunning = true
            while (isRunning) {
                Log.d("MyIntentService", "Service is running ...")
                Thread.sleep(1000)
            }
        } catch(e: InterruptedException) {
            Thread.currentThread().interrupt()
        }
    }
}