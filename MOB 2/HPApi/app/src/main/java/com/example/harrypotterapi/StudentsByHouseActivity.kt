package com.example.harrypotterapi

import StudentsAdapter
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StudentsByHouseActivity : AppCompatActivity() {
    private lateinit var hpAPI: HPAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_students_by_house)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://hp-api.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        hpAPI = retrofit.create(HPAPI::class.java)

        val rgHouses = findViewById<RadioGroup>(R.id.rgHouses)
        val btnFetchStudents = findViewById<Button>(R.id.btnFetchStudents)
        val rvStudentsList = findViewById<RecyclerView>(R.id.rvStudentsList)
        val btnBackToMain = findViewById<Button>(R.id.btnBackToMain)

        rvStudentsList.layoutManager = LinearLayoutManager(this)

        btnBackToMain.setOnClickListener {
            finish()
        }

        val houses = listOf("Gryffindor", "Hufflepuff", "Ravenclaw", "Slytherin")
        houses.forEach { house ->
            val radioButton = RadioButton(this)
            radioButton.text = house
            rgHouses.addView(radioButton)
        }

        btnFetchStudents.setOnClickListener {
            val selectedRadioButtonId = rgHouses.checkedRadioButtonId

            if (selectedRadioButtonId != -1) {
                val selectedHouse = findViewById<RadioButton>(selectedRadioButtonId).text.toString()
                fetchStudentsByHouse(selectedHouse, rvStudentsList)
            } else {
                Toast.makeText(this, "Por favor, selecione uma casa.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun fetchStudentsByHouse(house: String, rvStudentsList: RecyclerView) {
        lifecycleScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    hpAPI.getStudentsByHouse(house)
                }

                if (response.isNotEmpty()) {
                    rvStudentsList.adapter = StudentsAdapter(response, this@StudentsByHouseActivity)
                } else {
                    Toast.makeText(
                        this@StudentsByHouseActivity,
                        "Nenhum estudante encontrado para a casa $house.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: Exception) {
                Log.e("StudentsByHouseActivity", "Erro ao buscar estudantes", e)
                Toast.makeText(this@StudentsByHouseActivity, "Erro ao buscar estudantes.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
