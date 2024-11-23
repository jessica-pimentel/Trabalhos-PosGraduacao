package com.example.harrypotterapi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnCharacterById).setOnClickListener {
            startActivity(Intent(this, CharacterByIdActivity::class.java))
        }

        findViewById<Button>(R.id.btnStaff).setOnClickListener {
            startActivity(Intent(this, HogwartsStaffActivity::class.java))
        }

        findViewById<Button>(R.id.btnStudentsByHouse).setOnClickListener {
            startActivity(Intent(this, StudentsByHouseActivity::class.java))
        }

        findViewById<Button>(R.id.btnExit).setOnClickListener {
            finish()
        }
    }
}