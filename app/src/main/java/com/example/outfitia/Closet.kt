package com.example.outfitia

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.outfitia.roomdatabase.AppDatabase
import kotlinx.coroutines.launch

class Closet : AppCompatActivity() {

    private lateinit var recyclerCloset: RecyclerView
    private lateinit var adapter: PrendaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_closet)

        recyclerCloset = findViewById(R.id.recyclerCloset)
        recyclerCloset.layoutManager = LinearLayoutManager(this)

        val db = AppDatabase.getDatabase(this)

        // Observar cambios en la base de datos con Flow
        lifecycleScope.launch {
            db.prendaDao().getAllPrendas().collect { prendas ->
                Log.d("DB", "Prendas recibidas: ${prendas.size}")
                adapter = PrendaAdapter(prendas)
                recyclerCloset.adapter = adapter
            }
        }
    }
}