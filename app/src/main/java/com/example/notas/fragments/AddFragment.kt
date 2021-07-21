package com.example.notas.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.notas.R
import com.example.notas.data.Nota
import com.example.notas.data.NotaViewModel

class AddFragment : Fragment() {

    private lateinit var btnGuardar:Button

    private lateinit var edtTitulo:EditText

    private lateinit var edtDescripcion:EditText

    private lateinit var mNotaViewModel: NotaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        btnGuardar = view.findViewById(R.id.btnGuardar)

        edtTitulo = view.findViewById(R.id.edtTitulo)

        edtDescripcion = view.findViewById(R.id.edtDescripcion)

        mNotaViewModel = ViewModelProvider(this).get(NotaViewModel::class.java)

        btnGuardar.setOnClickListener{
            insertar()
        }
        return view
    }

    private fun insertar(){
        val titulo = edtTitulo.text.toString()
        val descripcion = edtDescripcion.text.toString()
        val nota = Nota(null,titulo, descripcion)
        mNotaViewModel.nuevaNota(nota)
        findNavController().navigate(R.id.action_addFragment_to_listFragment)
    }

}