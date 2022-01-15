package com.example.androidintentservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvService = findViewById<TextView>(R.id.tvService)

        findViewById<Button>(R.id.btnStart).setOnClickListener {
            Intent(this, MyIntentService::class.java).also {
                // Starting the service
                MyIntentService.enqueueWork(this, it)
                tvService.text = "Service running"
            }
        }

        findViewById<Button>(R.id.btnStop).setOnClickListener {
            // Stopping the service
            MyIntentService.stopService()
            tvService.text = "Service stopped"
        }
    }
}