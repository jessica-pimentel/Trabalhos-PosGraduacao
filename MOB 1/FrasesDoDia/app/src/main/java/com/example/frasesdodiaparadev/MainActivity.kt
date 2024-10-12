package com.example.frasesdodiaparadev

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun quote(view: View) {
        val quotes = arrayOf(
            "Um desenvolvedor experiente é aquele que já viu todos os erros possíveis... e os cometeu pelo menos duas vezes.",
            "Debugando código é como ser um detetive em um romance policial onde você é o assassino.",
            "A diferença entre um programador e um hacker? Um café da manhã.",
            "Meu código funciona, não sei porquê.",
            "O café é a linguagem de programação favorita do meu compilador.",
            "Se depurar é o processo de remover bugs, então programar deve ser o processo de colocá-los lá.",
            "Eu não tenho preguiça, estou apenas em modo de economia de energia.",
            "O Google é meu melhor amigo, Stack Overflow meu mentor e o café meu combustível.",
            "Escrever código é fácil. A parte difícil é fazer com que ele funcione.",
            "Na dúvida, reinicie. Se não funcionar, reinstale. Se ainda não funcionar, culpe o hardware."
        )

        val index = (0..9).random()
        val textView = findViewById<TextView>(R.id.textviewOutPut)
        textView.text = quotes[index]
    }
}