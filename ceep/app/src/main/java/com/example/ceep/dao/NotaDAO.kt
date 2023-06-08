package com.example.ceep.dao

import com.example.ceep.model.Nota
import java.util.*

class NotaDAO {
    fun todos(): ArrayList<Nota> {
        return notas.clone() as ArrayList<Nota>
    }

    fun insere(vararg notas: Nota?) {
        Companion.notas.addAll(listOf(*notas))
    }

    fun altera(posicao: Int, nota: Nota?) {
        if (posicao > -1) notas[posicao] = nota
    }

    fun remove(posicao: Int) {
        notas.removeAt(posicao)
    }

    fun troca(posicaoInicio: Int, posicaoFim: Int) {
        Collections.swap(notas, posicaoInicio, posicaoFim)
    }

    fun removeTodos() {
        notas.clear()
    }

    companion object {
        private val notas = ArrayList<Nota?>()
    }
}