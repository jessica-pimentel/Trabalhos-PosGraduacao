package com.example.churrascometro

import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sbMen = findViewById<SeekBar>(R.id.seekBarMen)
        val sbWomen = findViewById<SeekBar>(R.id.seekBarWomen)

        val menQt = findViewById<TextView>(R.id.textViewOutputMen)
        val womenQt = findViewById<TextView>(R.id.textViewOutputWomen)

        sbMen?.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                menQt.text = progress.toString()
                calculate(progress, sbWomen.progress)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        sbWomen?.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                womenQt.text = progress.toString()
                calculate(sbMen.progress, progress)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }

    fun calculate(menQt: Int, womenQt: Int){
        val outputSausage = findViewById<TextView>(R.id.textViewSausageOutput)
        val outputMeat = findViewById<TextView>(R.id.textViewMeatOutput)
        var sausageQtt: Double = (menQt * 250.0 + womenQt * 150.0) / 1000
        var meatQtt: Double = (menQt * 450.0 + womenQt * 300.0) / 1000

        outputSausage.text = sausageQtt.toString() + " Kg"
        outputMeat.text = meatQtt.toString() + " Kg"
    }

}