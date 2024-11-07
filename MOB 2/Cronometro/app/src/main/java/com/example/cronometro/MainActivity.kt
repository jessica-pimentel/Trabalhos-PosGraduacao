package com.example.cronometro

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var tvTime: TextView
    private lateinit var btnStart: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        tvTime = findViewById(R.id.tvTime)
        btnStart = findViewById(R.id.btnStart)

        btnStart.setOnClickListener {
            startCounter()
        }
    }

    private fun startCounter() {
        lifecycleScope.launch(Dispatchers.Main) {
            for (i in 1 .. 10) {
                tvTime.text = i.toString()
                delay(1000)
            }
        }
    }
}