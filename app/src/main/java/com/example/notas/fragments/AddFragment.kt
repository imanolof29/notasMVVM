package com.example.notas.fragments


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.notas.R
import com.example.notas.data.Nota
import com.example.notas.data.NotaViewModel
import com.example.notas.databinding.FragmentAddBinding


class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding

    private lateinit var mNotaViewModel: NotaViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        binding = FragmentAddBinding.bind(view)

        mNotaViewModel = ViewModelProvider(this).get(NotaViewModel::class.java)

        binding.toolbar.title = "Nueva nota"

        binding.toolbar.inflateMenu(R.menu.menu)
        binding.toolbar.setOnMenuItemClickListener{
            when(it.itemId){
                R.id.save -> {
                    insertar()
                    true
                }
                else -> false
            }
        }
        return view
    }

    private fun insertar(){
        val titulo = binding.edtTitulo.text.toString()
        val descripcion = binding.edtDescripcion.text.toString()
        val nota = Nota(null,titulo, descripcion)
        mNotaViewModel.nuevaNota(nota)
        findNavController().navigate(R.id.action_addFragment_to_listFragment)
    }

}