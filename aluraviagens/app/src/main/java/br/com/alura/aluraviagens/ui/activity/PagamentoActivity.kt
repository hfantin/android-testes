package br.com.alura.aluraviagens.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.alura.aluraviagens.R
import br.com.alura.aluraviagens.formatoMoeda
import br.com.alura.aluraviagens.model.Pacote
import kotlinx.android.synthetic.main.activity_pagamento.*
import java.math.BigDecimal


class PagamentoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagamento)
        setTitle(getString(R.string.pagamento))
        obterExtras()
    }

    private fun obterExtras() {
        if (intent.hasExtra("pacote")) {
            var pacote = intent.getSerializableExtra("pacote") as Pacote
            bindView(pacote)
        }
    }

    private fun bindView(pacote: Pacote) {
        pagamento_preco_pacote.text = pacote.preco.formatoMoeda()
        pagamento_botao_finaliza_compra.setOnClickListener {
            proximaActivity(pacote, ResumoCompraActivity::class.java)
        }
    }

    private fun proximaActivity(pacote: Pacote, proxima: Class<*>) {
        val intent = Intent(this, proxima)
        intent.putExtra("pacote", pacote)
        startActivity(intent)
    }
}
