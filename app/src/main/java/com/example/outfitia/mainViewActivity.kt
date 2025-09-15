package com.example.outfitia

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class mainViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_view)

        val btnContinuar = findViewById<Button>(R.id.iniciarSesion)

        val btnCrearUsuario = findViewById<Button>(R.id.btncrearUsuario)
        btnCrearUsuario.setOnClickListener {
            crearUsuarioActivity()
        }


        val usurio = findViewById<EditText>(R.id.usuarioCreado)
        val contraseña = findViewById<EditText>(R.id.contraseñaCreada)

        btnContinuar.setOnClickListener {
            val contra1 = usurio.text.toString().trim()
            val contra2 = contraseña.text.toString().trim()

            if (contra1.isEmpty() || contra2.isEmpty()) {
                Toast.makeText(this, "Ambos campos del Inicio de sesión son obligatorios", Toast.LENGTH_SHORT).show()
            }else(
                pantallaPrincipalActivity()
            )
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


    private fun pantallaPrincipalActivity() {
        val intent = Intent(this, PantallaPrincipalActivity::class.java)
        startActivity(intent)
    }

    private fun crearUsuarioActivity() {
        val intent = Intent(this, CrearUsuarioActivity::class.java)
        startActivity(intent)
    }
}


