package com.example.hpcharsdbapp.controller

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hpcharsdbapp.R
import com.example.hpcharsdbapp.data.dao.HpCharDAO
import com.example.hpcharsdbapp.model.HPChar

class MainActivity : AppCompatActivity() {
    private lateinit var hpCharDAO: HpCharDAO
    private lateinit var listView: ListView
    private lateinit var emptyTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.lvChars)
        emptyTextView = findViewById(R.id.tvEmpty)
        hpCharDAO = HpCharDAO(this)

        listAllChars()

        listView.setOnItemClickListener { parent, view, position, id ->
            val selectedChar = parent.getItemAtPosition(position) as HPChar
            val intent = Intent(this, NewHpChar::class.java).apply {
                putExtra("charId", selectedChar.id)
            }
            startActivity(intent)
        }
    }

    private fun listAllChars() {
        val hpChars = hpCharDAO.getAllChars()
        if (hpChars.isEmpty()) {
            listView.visibility = ListView.GONE
            emptyTextView.visibility = TextView.VISIBLE
        } else {
            listView.visibility = ListView.VISIBLE
            emptyTextView.visibility = TextView.GONE
            val adapter: ArrayAdapter<HPChar> = ArrayAdapter(this, android.R.layout.simple_list_item_1, hpChars)
            listView.adapter = adapter
        }
    }

    fun newChar(view: android.view.View) {
        val intent = Intent(this, NewHpChar::class.java)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        listAllChars()
    }
}