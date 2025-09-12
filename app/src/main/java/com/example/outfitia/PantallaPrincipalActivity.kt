package com.example.outfitia

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PantallaPrincipalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pantalla_principal)


        val btnCrearOutfit = findViewById<Button>(R.id.btnCrearOutfit)
        btnCrearOutfit.setOnClickListener {
            Crear_Outfit_Activity()
        }
        val btnCloset = findViewById<Button>(R.id.btnCloset)
        btnCloset.setOnClickListener {
            Closet_Activity()
        }
        val btnRecomendacion = findViewById<Button>(R.id.btnRecomendacion)
        btnRecomendacion.setOnClickListener {
            Recomendacion_Activity()
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.menu)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        }
    fun Crear_Outfit_Activity() {
        val intent = Intent(this, Crear_Outfit::class.java)
        startActivity(intent)
    }
    private fun Closet_Activity() {
        val intent = Intent(this, Closet::class.java)
        startActivity(intent)
    }
    private fun Recomendacion_Activity() {
        val intent = Intent(this, Recomendacion::class.java)
        startActivity(intent)
    }
}