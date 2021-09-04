package com.example.notas.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notas.NotasAdapter
import com.example.notas.R
import com.example.notas.data.NotaViewModel
import com.example.notas.databinding.FragmentListBinding
import com.google.android.material.snackbar.Snackbar

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding

    private lateinit var mNotaViewModel: NotaViewModel

    private lateinit var adapter: NotasAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_list, container, false)

        binding = FragmentListBinding.bind(view)

        binding.toolbar.title = "Notas"

        //adapter
        adapter = NotasAdapter()

        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())

        //NotaViewModel
        mNotaViewModel = ViewModelProvider(this).get(NotaViewModel::class.java)
        mNotaViewModel.getAllData.observe(viewLifecycleOwner, Observer { nota ->
            adapter.setData(nota)
        })

        //item touch helper
        ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                mNotaViewModel.borrarNota(adapter.getNotaAt(viewHolder.adapterPosition))
                Snackbar.make(binding.recyclerview, "Nota borrada", Snackbar.LENGTH_SHORT).show()
            }
        }).attachToRecyclerView(binding.recyclerview)


        //floating on click event
        binding.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        return view
    }




}