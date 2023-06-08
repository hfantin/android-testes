package br.com.alura.ceep.ui.recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.ceep.databinding.ItemNotaBinding
import br.com.alura.ceep.model.Nota

class ListaNotasAdapter(
    var onItemClickListener: (notaSelecionada: Nota) -> Unit = {}
) : ListAdapter<Nota, ListaNotasAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewDataBinding = ItemNotaBinding.inflate(inflater, parent, false)
        return ViewHolder(viewDataBinding)
//        return ViewHolder(viewDataBinding).also {
//            viewDataBinding.lifecycleOwner = it
//        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { nota ->
            holder.vincula(nota)
        }
    }

//    override fun onViewAttachedToWindow(holder: ViewHolder) {
//        super.onViewAttachedToWindow(holder)
//        holder.marcaComoAtivo()
//    }
//
//    override fun onViewDetachedFromWindow(holder: ViewHolder) {
//        super.onViewDetachedFromWindow(holder)
//        holder.marcaComoDesativado()
//    }

    inner class ViewHolder(private val viewDataBinding: ItemNotaBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root), View.OnClickListener
//        , LifecycleOwner
    {

        private lateinit var nota: Nota

//        private val registry = LifecycleRegistry(this)
//
//        override fun getLifecycle(): Lifecycle = registry


        init {
            viewDataBinding.clicaNaNota = this
//            registry.markState(Lifecycle.State.INITIALIZED)
        }


//        fun marcaComoAtivo(){
//            registry.markState(Lifecycle.State.STARTED)
//        }
//
//        fun marcaComoDesativado(){
//            registry.markState(Lifecycle.State.DESTROYED)
//        }

        override fun onClick(v: View?) {
            if (::nota.isInitialized) {
                onItemClickListener(nota)
            }
        }

        fun vincula(nota: Nota) {
            this.nota = nota
            viewDataBinding.nota = nota
//            val notaData = NotaData(nota)
//            viewDataBinding.nota = notaData
        }

    }

}

object DiffCallback : DiffUtil.ItemCallback<Nota>() {
    override fun areItemsTheSame(
        oldItem: Nota,
        newItem: Nota
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Nota, newItem: Nota) = oldItem == newItem

}