package com.example.notas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notas.data.Nota

class NotasAdapter():RecyclerView.Adapter<NotasAdapter.ViewHolder>() {

    var notasList = emptyList<Nota>()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val titulo:TextView = view.findViewById(R.id.tvTitulo)
        private val descripcion:TextView = view.findViewById(R.id.tvDescripcion)

        fun bind(nota:Nota){
            titulo.text = nota.titulo
            descripcion.text = nota.descripcion
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.nota_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotasAdapter.ViewHolder, position: Int) {
        val nota = notasList[position]
        holder.bind(nota)
    }

    override fun getItemCount(): Int = notasList.size

    fun setData(notas: List<Nota>){
        this.notasList = notas
        notifyDataSetChanged()
    }

    fun getNotaAt(pos:Int):Nota{
        return this.notasList[pos]
    }

}