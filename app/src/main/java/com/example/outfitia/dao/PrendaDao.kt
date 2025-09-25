package com.example.outfitia.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.outfitia.entity.Prenda
import kotlinx.coroutines.flow.Flow

@Dao
interface PrendaDao {
    @Query("SELECT * FROM prenda")
    fun getAllPrendas(): kotlinx.coroutines.flow.Flow<List<Prenda>>

    @Insert
    suspend fun insert(prenda: Prenda)
}
