package com.example.finapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Context

class BankStatementActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bank_statement)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sharedPreferences = getSharedPreferences("RegisterPrefs", Context.MODE_PRIVATE)
        val registerListString = sharedPreferences.getString("registerList", "") ?: ""
        val registerList = registerListString.split("\n").filter { it.isNotEmpty() }

        val listView = findViewById<ListView>(R.id.bankStatementListView)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, registerList)
        listView.adapter = adapter
    }

    fun destroyButton(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}