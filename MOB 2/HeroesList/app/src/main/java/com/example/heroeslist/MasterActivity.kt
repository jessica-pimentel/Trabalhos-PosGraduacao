package com.example.heroeslist

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MasterActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_master)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        recyclerView = findViewById(R.id.recyclerViewHeroes)
        recyclerView?.adapter = HeroesAdapter(this.createHeroes(), this){
            Toast.makeText(this, "clicou no ${it.heroName}", Toast.LENGTH_SHORT).show()
        }
        recyclerView?.layoutManager = LinearLayoutManager(this)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    private fun createHeroes(): List<Hero>{
        return listOf(
            Hero(R.drawable.batman, "Batman", "DC Company"),
            Hero(R.drawable.superman, "Superman", "DC Company"),
            Hero(R.drawable.flash, "Flash", "DC Company"),
            Hero(R.drawable.ironman, "IronMan", "Marvel"),
            Hero(R.drawable.hulk, "Hulk", "Marvel"),
            Hero(R.drawable.drestranho, "Dr.Estranho", "Marvel")
        )
    }
}