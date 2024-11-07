package com.example.catfactapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
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

class MainActivity : AppCompatActivity() {
    private lateinit var tvResult: TextView
    private lateinit var progressBar: ProgressBar
    private var catFactAPI: CatFactAPI? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.tvResult)
        progressBar = findViewById(R.id.progressBar)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://catfact.ninja/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        catFactAPI = retrofit.create(CatFactAPI::class.java)
    }

    fun getFact(view: View){
        progressBar.visibility = View.VISIBLE
        tvResult.visibility = View.GONE

        // Chamar a API para obter um fato sobre gatos
        lifecycleScope.launch(Dispatchers.Main) {
            try {
                val factResponse = withContext(Dispatchers.IO) {
                    catFactAPI?.getCatFact()
                }
                tvResult.text = factResponse?.fact
                progressBar.visibility = View.GONE
                tvResult.visibility = View.VISIBLE
            } catch (e: Exception){
                Log.e("MainActivity", "Erro ao obter fato sobre gatos", e)
                tvResult.text = "Erro ao obter fato sobre gatos"
            }
        }
    }
}