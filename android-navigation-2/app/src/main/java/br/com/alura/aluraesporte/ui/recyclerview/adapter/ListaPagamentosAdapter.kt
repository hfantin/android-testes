package br.com.alura.aluraesporte.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.aluraesporte.databinding.ItemPagamentoBinding
import br.com.alura.aluraesporte.extensions.formatParaMoedaBrasileira
import br.com.alura.aluraesporte.model.Pagamento

class ListaPagamentosAdapter(
    private val context: Context, pagamentos: List<Pagamento> = listOf()
) : RecyclerView.Adapter<ListaPagamentosAdapter.ViewHolder>() {

    private val pagamentos = pagamentos.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemPagamentoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = pagamentos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pagamento = pagamentos[position]
        holder.vincula(pagamento)
    }

    fun add(pagamentos: List<Pagamento>) {
        notifyItemRangeRemoved(0, this.pagamentos.size)
        this.pagamentos.clear()
        this.pagamentos.addAll(pagamentos)
        notifyItemRangeInserted(0, this.pagamentos.size)
    }

    class ViewHolder(private val view: ItemPagamentoBinding) : RecyclerView.ViewHolder(view.root) {
        fun vincula(pagamento: Pagamento) {
            view.itemPagamentoId.text = pagamento.id.toString()
            view.itemPagamentoPreco.text = pagamento.preco.formatParaMoedaBrasileira()
        }
    }
}