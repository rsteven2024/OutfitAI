package com.example.outfitia

import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import android.widget.Spinner
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.outfitia.entity.Prenda
import com.example.outfitia.roomdatabase.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class Crear_Outfit : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var photoFile: File
    private lateinit var photoUri: Uri
    private var currentImagePath: String? = null

    private lateinit var db: AppDatabase
    private lateinit var spinnerTipo: Spinner   // 👈 aquí declaramos el spinner

    private val takePicture =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success && photoFile.exists()) {
                currentImagePath = photoFile.absolutePath
                val bitmap = BitmapFactory.decodeFile(photoFile.absolutePath)
                imageView.setImageBitmap(bitmap)
            } else {
                Toast.makeText(this, "No se tomó la foto 📸", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_outfit)

        imageView = findViewById(R.id.imageView)
        val btnCamara = findViewById<Button>(R.id.btnCamara)
        val btnGuardar = findViewById<Button>(R.id.btnGuardarfoto)
        spinnerTipo = findViewById(R.id.spinnerTipo)

        db = AppDatabase.getDatabase(applicationContext)

        btnCamara.setOnClickListener { abrirCamara() }
        btnGuardar.setOnClickListener { guardarEnCloset() }
    }

    private fun abrirCamara() {
        photoFile = File(
            getExternalFilesDir(android.os.Environment.DIRECTORY_PICTURES),
            "foto_outfit_${System.currentTimeMillis()}.jpg"
        )

        photoUri = FileProvider.getUriForFile(
            this,
            "${applicationContext.packageName}.provider",
            photoFile
        )

        takePicture.launch(photoUri)
    }

    private fun guardarEnCloset() {
        if (currentImagePath != null) {
            val tipoSeleccionado = spinnerTipo.selectedItem.toString()

            val recomendacion = when (tipoSeleccionado.lowercase()) {
                "camisa" -> "Combínala con jeans azules 👖"
                "pantalón", "pantalon" -> "Úsalo con una camiseta blanca 👕🧢"
                "zapatos" -> "Perfectos con un pantalón formal 👖"
                "chaqueta" -> "Queda genial con una bufanda 🧣"
                else -> "Combínalo con accesorios llamativos 🌟"
            }

            // 👇 Aquí completas el flujo: crear la entidad y guardarla
            val prenda = Prenda(
                imagePath = currentImagePath!!,
                tipo = tipoSeleccionado,
                recomendacion = recomendacion
            )

            CoroutineScope(Dispatchers.IO).launch {
                db.prendaDao().insert(prenda)
            }

            Toast.makeText(this, "Prenda guardada en Closet ✅", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Primero toma una foto 📸", Toast.LENGTH_SHORT).show()
        }
    }
}