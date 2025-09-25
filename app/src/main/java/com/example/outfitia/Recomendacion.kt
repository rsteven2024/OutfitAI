package com.example.outfitia

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Recomendacion : AppCompatActivity() {

    private lateinit var imageViewOutfit: ImageView
    private lateinit var btnNuevoOutfit: Button

    // Lista de outfits guardados en drawable
    private val outfits = listOf(
        R.drawable.outfit1,
        R.drawable.outfit2,
        R.drawable.outfit3,
        R.drawable.outfit4,
        R.drawable.outfit5,
        R.drawable.outfit6,
        R.drawable.outfit7,
        R.drawable.outfit8,
        R.drawable.outfit9,
        R.drawable.outfit10,
        R.drawable.outfit11,
        R.drawable.outfit12
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recomendacion)

        // Ajuste de insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Referencias a la UI
        imageViewOutfit = findViewById(R.id.imageViewOutfit)
        btnNuevoOutfit = findViewById(R.id.btnNuevoOutfit)

        // Mostrar outfit aleatorio al abrir
        mostrarOutfitAleatorio()

        // Cambiar outfit al presionar el botón
        btnNuevoOutfit.setOnClickListener {
            mostrarOutfitAleatorio()
        }
    }

    private fun mostrarOutfitAleatorio() {
        val randomOutfit = outfits.random()
        imageViewOutfit.setImageResource(randomOutfit)
    }
}