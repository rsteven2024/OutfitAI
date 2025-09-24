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
import com.google.firebase.auth.FirebaseAuth

class mainViewActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_view)

        // Inicializar FirebaseAuth
        auth = FirebaseAuth.getInstance()

        val btnContinuar = findViewById<Button>(R.id.iniciarSesion)
        val btnCrearUsuario = findViewById<Button>(R.id.btncrearUsuario)

        val usuario = findViewById<EditText>(R.id.usuarioCreado)
        val contraseña = findViewById<EditText>(R.id.contraseñaCreada)

        // Ir a crear usuario
        btnCrearUsuario.setOnClickListener {
            crearUsuarioActivity()
        }

        // Iniciar sesión
        btnContinuar.setOnClickListener {
            val email = usuario.text.toString().trim()
            val password = contraseña.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Ambos campos son obligatorios", Toast.LENGTH_SHORT).show()
            } else {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                            pantallaPrincipalActivity()
                        } else {
                            Toast.makeText(
                                this,
                                "Error: ${task.exception?.message}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
            }
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
        finish() // opcional, para que no regrese al login
    }

    private fun crearUsuarioActivity() {
        val intent = Intent(this, CrearUsuarioActivity::class.java)
        startActivity(intent)
    }
}



