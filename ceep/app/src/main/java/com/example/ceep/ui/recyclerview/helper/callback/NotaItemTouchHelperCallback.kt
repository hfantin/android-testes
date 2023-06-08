package com.example.ceep.ui.recyclerview.helper.callback

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.ceep.dao.NotaDAO
import com.example.ceep.ui.recyclerview.adapter.ListaNotasAdapter


class NotaItemTouchHelperCallback(private val adapter: ListaNotasAdapter) : ItemTouchHelper.Callback() {
    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        val marcacoesDeDeslize = ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        val marcacoesDeArrastar = ItemTouchHelper.RIGHT or ItemTouchHelper.RIGHT or ItemTouchHelper.DOWN or ItemTouchHelper.UP
        return makeMovementFlags(marcacoesDeArrastar, marcacoesDeDeslize)
    }

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        val posicaoInicial = viewHolder.adapterPosition
        val posicaoFinal = target.adapterPosition
        NotaDAO().troca(posicaoInicial, posicaoFinal)
        adapter.troca(posicaoInicial, posicaoFinal)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val posicaoDaNotaDeslizada = viewHolder.adapterPosition
        NotaDAO().remove(posicaoDaNotaDeslizada)
        adapter.remove(posicaoDaNotaDeslizada)
    }

}
