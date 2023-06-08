package br.com.alura.aluraviagens.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.alura.aluraviagens.R
import br.com.alura.aluraviagens.formataUnidade
import br.com.alura.aluraviagens.formatoMoeda
import br.com.alura.aluraviagens.model.Pacote
import kotlinx.android.synthetic.main.item_pacote.view.*

class ItemPacoteAdapter(private val pacotes: List<Pacote>, private val itemClick:(Pacote) -> Unit) : RecyclerView.Adapter<ItemPacoteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pacote, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(pacotes[position])
    }


    override fun getItemCount() = pacotes.size


    class ViewHolder(view: View, private val itemClick: (Pacote) -> Unit) : RecyclerView.ViewHolder(view){
        fun bind(pacote: Pacote) {
            with(pacote){
                itemView.item_pacote_imagem.setImageResource(imagem)
                itemView.item_pacote_dias.text = dias.formataUnidade("dia")
                itemView.item_pacote_local.text = local
                itemView.item_pacote_preco.text = "${preco.formatoMoeda()}"
                itemView.setOnClickListener { itemClick(this) }

            }
        }

    }

}