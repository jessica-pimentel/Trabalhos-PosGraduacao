package com.example.multiactivity

import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val closeButton = findViewById<Button>(R.id.closeButton)
        val bundle = intent.extras
        /* if(bundle != null){
            println(bundle?.getString("userName"))
            println(bundle?.getInt("n1Peso"))
            println(bundle?.getDouble("n1"))
         } */
         val user = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
             bundle?.getParcelable("user", User::class.java)
         } else {
             bundle?.getParcelable("user")
         }
        println(user?.userName)
        println(user?.n1Peso)
        println(user?.n1)
        closeButton.setOnClickListener(){
            finish()
        }
    }
}