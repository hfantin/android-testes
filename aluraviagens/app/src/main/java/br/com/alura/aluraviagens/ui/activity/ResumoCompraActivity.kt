package br.com.alura.aluraviagens.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.alura.aluraviagens.R
import br.com.alura.aluraviagens.formataUnidade
import br.com.alura.aluraviagens.formatoMoeda
import br.com.alura.aluraviagens.model.Pacote
import kotlinx.android.synthetic.main.activity_resumo_compra.*
import java.math.BigDecimal

class ResumoCompraActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resumo_compra)
        title = getString(R.string.resumo_compra)
        obterExtras()
    }

    private fun obterExtras() {
        if (intent.hasExtra("pacote")) {
            var pacote = intent.getSerializableExtra("pacote") as Pacote
            bindView(pacote)
        }
    }

    private fun bindView(pacote: Pacote) {
        resumo_compra_local_pacote.text = pacote.local
        resumo_compra_imagem_pacote.setImageResource(pacote.imagem)
        resumo_compra_data_viagem.text = pacote.dias.formataUnidade("dia")
        resumo_compra_preco_pacote.text = pacote.preco.formatoMoeda()
    }
}
