package com.example.tempcalc

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
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
    }

    fun convert (view: View) {
        val toCelsius = findViewById<RadioButton>(R.id.radioButtonToCelsius)
        val input = findViewById<EditText>(R.id.editTextNumberInput)
        val output = findViewById<TextView>(R.id.textViewOutput)

        if (input.length() == 0) {
            Toast.makeText(this, "Digite uma temperatura", Toast.LENGTH_SHORT).show()
            output.text = ""
        } else {
            val tempInput = input.text.toString().toDouble()
            var tempOutput: Double = 0.0

            if (toCelsius.isChecked()) {
                tempOutput = (tempInput - 32) * 5 / 9
                output.text = String.format("%.2f", tempOutput) + " ºC"
            } else {
                tempOutput = tempInput * 9 / 5 + 32
                output.text = String.format("%.2f", tempOutput) + " ºF"
            }
        }
    }
}