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
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FieldValue

class CrearUsuarioActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_crear_usuario)

        // se inicia Firebase
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etUsuario = findViewById<EditText>(R.id.etUsuario)
        val etContraseña = findViewById<EditText>(R.id.etcontraseña)
        val etConfirmarContraseña = findViewById<EditText>(R.id.etConfirmarContraseña)
        val btnCrearUsuario = findViewById<Button>(R.id.btnCrearUsuario)

        btnCrearUsuario.setOnClickListener {
            val email = etUsuario.text.toString().trim()
            val contra1 = etContraseña.text.toString().trim()
            val contra2 = etConfirmarContraseña.text.toString().trim()

            if (email.isEmpty() || contra1.isEmpty() || contra2.isEmpty()) {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            } else if (contra1 != contra2) {
                etConfirmarContraseña.error = "Las contraseñas no coinciden"
            } else {



                auth.createUserWithEmailAndPassword(email, contra1)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val uid = auth.currentUser?.uid

                            // aqui se guarda el usario en la authentication
                            val user = hashMapOf(
                                "email" to email,
                                "fechaRegistro" to FieldValue.serverTimestamp()
                            )

                            db.collection("users").document(uid!!)
                                .set(user)
                                .addOnSuccessListener {
                                    Toast.makeText(this, "Usuario creado correctamente", Toast.LENGTH_SHORT).show()
                                    pantallaPrincipalActivity()
                                }
                                .addOnFailureListener {
                                    Toast.makeText(this, "Error guardando en Firestore", Toast.LENGTH_SHORT).show()
                                }
                        } else {
                            Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }

    private fun pantallaPrincipalActivity() {
        val intent = Intent(this, PantallaPrincipalActivity::class.java)
        startActivity(intent)
        finish()
    }
}
