package com.example.harrypotterapi

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CharacterByIdActivity : AppCompatActivity() {
    private lateinit var hpAPI: HPAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_by_id)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://hp-api.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        hpAPI = retrofit.create(HPAPI::class.java)

        val etCharacterId = findViewById<EditText>(R.id.etCharacterId)
        val btnFetchCharacter = findViewById<Button>(R.id.btnFetchCharacter)
        val tvCharacterDetails = findViewById<TextView>(R.id.tvCharacterDetails)

        val btnBackToMain = findViewById<Button>(R.id.btnBackToMain)
        btnBackToMain.setOnClickListener {
            finish()
        }

        btnFetchCharacter.setOnClickListener {
            val id = etCharacterId.text.toString()

            if (id.isNotEmpty()) {
                fetchCharacterById(id, tvCharacterDetails)
            } else {
                Toast.makeText(this, "Por favor, insira um ID", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun fetchCharacterById(id: String, tvCharacterDetails: TextView) {
        lifecycleScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    hpAPI.getCharacterById(id)
                }
                if (response.isNotEmpty()) {
                    val character = response[0]
                    tvCharacterDetails.text = "Nome: ${character.name}\nCasa: ${character.house ?: "N/A"}"
                } else {
                    tvCharacterDetails.text = "Nenhum personagem encontrado para o ID fornecido."
                }
            } catch (e: Exception) {
                Log.e("CharacterByIdActivity", "Erro ao buscar personagem", e)
                Toast.makeText(this@CharacterByIdActivity, "Erro ao buscar personagem.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}