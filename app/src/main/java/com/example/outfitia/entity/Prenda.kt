package com.example.outfitia.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "prenda")
data class Prenda(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val imagePath: String,
    val tipo: String,              // 👈 nuevo campo
    val recomendacion: String
)


