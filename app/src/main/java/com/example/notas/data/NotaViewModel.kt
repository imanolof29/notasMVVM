package com.example.notas.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotaViewModel(application: Application) : AndroidViewModel(application) {

    val getAllData: LiveData<List<Nota>>

    private val respository: NotaRepository

    init{
        val notaDao = NotaDatabase.getInstance(application).notaDao()
        respository = NotaRepository(notaDao)
        getAllData = respository.leerNotas
    }

    fun nuevaNota(nota: Nota){
        viewModelScope.launch(Dispatchers.IO) {
            respository.nuevaNota(nota)
        }
    }

    fun borrarNota(nota:Nota){
        viewModelScope.launch(Dispatchers.IO){
            respository.borrarNota(nota)
        }
    }
}