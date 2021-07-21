package com.example.notas.fragments

import android.graphics.Canvas
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
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
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator


class ListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    private lateinit var floatingActionButton: FloatingActionButton

    private lateinit var mNotaViewModel: NotaViewModel

    private lateinit var adapter: NotasAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_list, container, false)

        //findViews
        recyclerView = view.findViewById(R.id.recyclerview)
        floatingActionButton = view.findViewById(R.id.floatingActionButton)


        //adapter
        adapter = NotasAdapter()

        //recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

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
                Snackbar.make(recyclerView, "Item borrado", Snackbar.LENGTH_SHORT).show()
            }
        }).attachToRecyclerView(recyclerView)


        //floating on click event
        floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        return view
    }




}