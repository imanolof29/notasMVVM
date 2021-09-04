package com.example.notas.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotaDao {
    @Query("SELECT * FROM nota")
    fun getNotas(): LiveData<List<Nota>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarNota(nota:Nota)

    @Update
    suspend fun actualizarNota(nota:Nota)

    @Delete
    suspend fun borrarNota(nota: Nota)
}