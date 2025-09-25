package com.example.outfitia.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.outfitia.dao.PrendaDao
import com.example.outfitia.entity.Prenda

@Database(entities = [Prenda::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun prendaDao(): PrendaDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "closet-db"
                )
                    .fallbackToDestructiveMigration() // ⚠️ borra y recrea la DB si cambia el esquema
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

