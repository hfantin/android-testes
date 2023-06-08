package br.com.alura.technews.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.technews.R
import br.com.alura.technews.databinding.ItemNoticiaBinding
import br.com.alura.technews.model.Noticia

class ListaNoticiasAdapter(
    private val context: Context,
    private val noticias: MutableList<Noticia> = mutableListOf(),
    var quandoItemClicado: (noticia: Noticia) -> Unit = {}
) : RecyclerView.Adapter<ListaNoticiasAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemNoticiaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = noticias.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val noticia = noticias[position]
        holder.vincula(noticia)
    }

    fun atualiza(noticias: List<Noticia>) {
        notifyItemRangeRemoved(0, this.noticias.size)
        this.noticias.clear()
        this.noticias.addAll(noticias)
        notifyItemRangeInserted(0, this.noticias.size)
    }

//    class ViewHolder(private val view: ItemNoticiaBinding, private val onClick: (Int) -> Unit) : RecyclerView.ViewHolder(view.root) {
//        init {
//            itemView.setOnClickListener {
//                onClick(adapterPosition)
//            }
//        }
//        fun bind(nota: Nota) {
//            view.itemNotaTitulo.text = nota.titulo
//            view.itemNotaDescricao.text = nota.descricao
//        }
//    }


    inner class ViewHolder(private val view: ItemNoticiaBinding) :
        RecyclerView.ViewHolder(view.root) {

        private lateinit var noticia: Noticia

        init {
            itemView.setOnClickListener {
                if (::noticia.isInitialized) {
                    quandoItemClicado(noticia)
                }
            }
        }

        fun vincula(noticia: Noticia) {
            this.noticia = noticia
            view.itemNoticiaTitulo.text = noticia.titulo
            view.itemNoticiaTexto.text = noticia.texto
        }

    }

}
