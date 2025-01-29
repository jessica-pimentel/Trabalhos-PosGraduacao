package com.example.harrypotterapi

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HogwartsStaffActivity : AppCompatActivity() {
    private lateinit var hpAPI: HPAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hogwarts_staff)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://hp-api.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        hpAPI = retrofit.create(HPAPI::class.java)

        val tvProfessors = findViewById<TextView>(R.id.tvProfessors)
        val btnBackToMain = findViewById<Button>(R.id.btnBackToMain)

        btnBackToMain.setOnClickListener {
            finish()
        }

        lifecycleScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    hpAPI.getHogwartsStaff()
                }
                val names = response.joinToString("\n") { it.name }
                tvProfessors.text = names
            } catch (e: Exception) {
                Log.e("HogwartsStaffActivity", "Erro ao buscar professores", e)
                Toast.makeText(this@HogwartsStaffActivity, "Erro ao buscar professores.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}