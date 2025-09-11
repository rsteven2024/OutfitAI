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

class CrearUsuarioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_crear_usuario)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        val etPassword = findViewById<EditText>(R.id.CrearUsuario)
        val etConfirmPassword= findViewById<EditText>(R.id.confirmarContraseña)
        val btnCrearUsuario  = findViewById<Button>(R.id.btncrearUsuario)

        btnCrearUsuario.setOnClickListener {
            val espacioContraseña = etPassword.text.toString().trim()
            val espacioCrearContraseña = etConfirmPassword.text.toString().trim()

            if (espacioContraseña.isEmpty() || espacioCrearContraseña.isEmpty()) {
                Toast.makeText(this, "Ambos campos de contraseña son obligatorios", Toast.LENGTH_SHORT).show()
            } else if (espacioContraseña != espacioCrearContraseña) {
                etConfirmPassword.error = "Las contraseñas no coinciden"
                Toast.makeText(this, "Verifica que las contraseñas sean iguales", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Usuario creado correctamente", Toast.LENGTH_SHORT).show()
                pantallaPrincipalActivity()
            }
        }
    }
    private fun pantallaPrincipalActivity() {
        val intent = Intent(this, PantallaPrincipalActivity::class.java)
        startActivity(intent)
    }
}