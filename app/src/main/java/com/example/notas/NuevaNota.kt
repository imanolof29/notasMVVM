package com.example.notas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.notas.data.Nota
import com.example.notas.data.NotaDao
import com.example.notas.data.NotaDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NuevaNota : AppCompatActivity() {

    lateinit var edtTitulo:EditText

    lateinit var edtDescripcion: EditText

    lateinit var btnGuardar:Button

    lateinit var notaDao: NotaDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nueva_nota)

        edtTitulo = findViewById(R.id.edtTitulo)
        edtDescripcion = findViewById(R.id.edtDescripcion)
        btnGuardar = findViewById(R.id.btnGuardar)
        notaDao = NotaDatabase.getInstance(application).notaDao()

        btnGuardar.setOnClickListener{
            GlobalScope.launch(Dispatchers.IO) {
                notaDao.insertarNota(Nota(id = null, edtTitulo.text.toString(), descripcion = edtDescripcion.text.toString()))
                finish()
            }
        }
    }
}