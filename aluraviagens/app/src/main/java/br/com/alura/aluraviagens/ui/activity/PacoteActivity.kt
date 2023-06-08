package br.com.alura.aluraviagens.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.com.alura.aluraviagens.*
import br.com.alura.aluraviagens.model.Pacote
import kotlinx.android.synthetic.main.activity_pacote.*
import java.math.BigDecimal
import java.util.*

class PacoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pacote)
        title = getString(R.string.resumo_pacote)
        obterExtras()
    }

    private fun obterExtras() {
        if (intent.hasExtra("pacote")) {
            var pacote = intent.getSerializableExtra("pacote") as Pacote
            bindView(pacote)
        }
    }

    private fun bindView(pacote: Pacote) {
        resumo_pacote_local.text = pacote.local
        resumo_pacote_imagem.setImageResource(pacote.imagem)
        resumo_pacote_dias.text = pacote.dias.formataUnidade("dia")
        resumo_pacote_preco.text = pacote.preco.formatoMoeda()
        resumo_pacote_data.text = pacote.dias.periodoEntreDatas()
        resumo_pacote_botao_realiza_pagamento.setOnClickListener {
            proximaActivity(pacote, PagamentoActivity::class.java)
        }
    }


    private fun proximaActivity(pacote: Pacote, proxima: Class<*>) {
        val intent = Intent(this, proxima)
        intent.putExtra("pacote", pacote)
        startActivity(intent)
    }

}
