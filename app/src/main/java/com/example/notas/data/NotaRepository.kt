package com.example.notas.data

import androidx.lifecycle.LiveData

class NotaRepository(private val notaDao:NotaDao) {

    val leerNotas: LiveData<List<Nota>> = notaDao.getNotas()

    suspend fun nuevaNota(nota:Nota){
        notaDao.insertarNota(nota)
    }

    suspend fun borrarNota(nota: Nota){
        notaDao.borrarNota(nota)
    }
}