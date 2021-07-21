package com.example.notas.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Nota::class], version = 1, exportSchema = false)
abstract class NotaDatabase : RoomDatabase() {
    abstract fun notaDao():NotaDao

    companion object {
        private var INSTANCE : NotaDatabase? = null

        fun getInstance(context: Context): NotaDatabase {
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    NotaDatabase::class.java,
                    "notas-db"
                ).build()
            }
            return INSTANCE!!
        }
    }
}