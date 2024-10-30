package com.example.finapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Context
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun destroyButton(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        findViewById<EditText>(R.id.descriptionEditText).text.clear()
        findViewById<EditText>(R.id.valueEditText).text.clear()
    }

    fun sendRegister(view: View){
        val description = findViewById<EditText>(R.id.descriptionEditText).text.toString()
        val valueText = findViewById<EditText>(R.id.valueEditText).text.toString()
        val isCreditSelected = findViewById<RadioButton>(R.id.radioBankStatementButton).isChecked ||
                findViewById<RadioButton>(R.id.radioRegisterButton).isChecked

        if (description.isEmpty() || valueText.isEmpty() || !isCreditSelected) {
            Toast.makeText(this, "Preencha todos os campos obrigatórios", Toast.LENGTH_SHORT).show()
            return
        }

        val value = valueText.toDoubleOrNull() ?: 0.0
        val transactionType = if (findViewById<RadioButton>(R.id.radioBankStatementButton).isChecked) "Crédito" else "Débito"
        val formattedValue = String.format("%.2f", value)
        val registerItem = "$transactionType - Descrição: $description, Valor: R$ $formattedValue"

        val sharedPreferences = getSharedPreferences("RegisterPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val currentList = sharedPreferences.getString("registerList", "") ?: ""
        val updatedList = if (currentList.isEmpty()) registerItem else "$currentList\n$registerItem"
        editor.putString("registerList", updatedList)
        editor.apply()

        Toast.makeText(this, "Cadastro criado com sucesso", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}