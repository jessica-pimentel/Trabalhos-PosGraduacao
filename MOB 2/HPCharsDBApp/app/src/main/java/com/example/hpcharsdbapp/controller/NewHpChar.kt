package com.example.hpcharsdbapp.controller

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hpcharsdbapp.R
import com.example.hpcharsdbapp.data.dao.HpCharDAO
import com.example.hpcharsdbapp.model.HPChar

class NewHpChar : AppCompatActivity() {
    private lateinit var hpCharDAO: HpCharDAO
    private var hpCharID: Int = 0
    private lateinit var etName: EditText
    private lateinit var etHouse: EditText
    private lateinit var etAncestry: EditText
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_new_hp_char)

        hpCharDAO = HpCharDAO(this)

        etName = findViewById(R.id.etName)
        etHouse = findViewById(R.id.etHouse)
        etAncestry = findViewById(R.id.etAncestry)
        btnDelete = findViewById(R.id.btnDelete)

        val btnSave: Button = findViewById(R.id.btnSave)
        btnSave.setOnClickListener { saveChar(it) }

        hpCharID = intent.getIntExtra("charId", 0)
            if(hpCharID != 0){
                btnDelete.visibility = Button.VISIBLE
                editChar()
            }

        btnDelete.setOnClickListener { deleteChar(it) }
    }


    private fun saveChar(view: View) {
        if (etName.text.isNotEmpty() && etHouse.text.isNotEmpty() && etAncestry.text.isNotEmpty()) {
         if (hpCharID == 0) {
            val newChar = HPChar(
                name = etName.text.toString(),
                house = etHouse.text.toString(),
                ancestry = etAncestry.text.toString()
            )
            hpCharDAO.addHPChar(newChar)
            Toast.makeText(this, "Personagem adicionado com sucesso", Toast.LENGTH_SHORT).show()
         } else {
             val updateChar = HPChar(
                 id = hpCharID,
                 name = etName.text.toString(),
                 house = etHouse.text.toString(),
                 ancestry = etAncestry.text.toString()
             )
             hpCharDAO.updateHPChar(updateChar)
             Toast.makeText(this, "Personagem atualizado com sucesso", Toast.LENGTH_SHORT).show()
         }
            finish()
        } else {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun editChar() {
        val char = hpCharDAO.getCharById(hpCharID)
        char?.let {
            etName.setText(it.name)
            etHouse.setText(it.house)
            etAncestry.setText(it.ancestry)
        }
    }

    private fun deleteChar(view: View) {
        hpCharDAO.deleteHPChar(hpCharID)
        Toast.makeText(this, "Personagem excluído com sucesso", Toast.LENGTH_SHORT).show()
        finish()
    }
}