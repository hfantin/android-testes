package com.example.ceep.ui.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ceep.databinding.ItemNotaBinding
import com.example.ceep.model.Nota
import java.util.*


class ListaNotasAdapter(private val notas: ArrayList<Nota>, val onItemClick: (Int, Nota) -> Unit) :  RecyclerView.Adapter<ListaNotasAdapter.ViewHolder>() {

    class ViewHolder(private val view: ItemNotaBinding, private val onClick: (Int) -> Unit) : RecyclerView.ViewHolder(view.root) {
        init {
            itemView.setOnClickListener {
                onClick(adapterPosition)
            }
        }
        fun bind(nota: Nota) {
            view.itemNotaTitulo.text = nota.titulo
            view.itemNotaDescricao.text = nota.descricao
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNotaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding) { onItemClick(it, notas[it]) }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(notas[position])
    }

    override fun getItemCount() = notas.size

    fun adiciona(nota: Nota) {
        notas.add(nota)
        notifyDataSetChanged()
    }

    fun altera(posicao: Int, nota: Nota) {
        notas[posicao] = nota
        notifyItemChanged(posicao)
    }

    fun remove(posicao: Int) {
        notas.removeAt(posicao)
        notifyItemRemoved(posicao)
    }

    fun troca(posicaoInicial: Int, posicaoFinal: Int) {
        Collections.swap(notas, posicaoInicial, posicaoFinal)
        notifyItemMoved(posicaoInicial, posicaoFinal) }

}
