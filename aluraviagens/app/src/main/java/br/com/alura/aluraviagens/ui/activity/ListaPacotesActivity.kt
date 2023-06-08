package br.com.alura.aluraviagens.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import br.com.alura.aluraviagens.R
import br.com.alura.aluraviagens.model.Pacote
import br.com.alura.aluraviagens.ui.adapter.ItemPacoteAdapter
import kotlinx.android.synthetic.main.activity_lista_pacotes.*
import java.math.BigDecimal


class ListaPacotesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_pacotes)
        setTitle(getString(R.string.titulo_pacotes))
        configuraLista()
    }

    private fun configuraLista() {
        listaPacotes.layoutManager = LinearLayoutManager(this)
        val lista = listaMockada()
        listaPacotes.adapter = ItemPacoteAdapter(lista) {
            proximaActivity(it, PacoteActivity::class.java)
        }
    }

    private fun proximaActivity(pacote: Pacote, proxima: Class<*>) {
        val intent = Intent(this, proxima)
        intent.putExtra("pacote", pacote)
        startActivity(intent)
    }

    private fun listaMockada() = listOf(
            Pacote("Belo Horizonte", R.drawable.belo_horizonte_mg, 2, BigDecimal("1000.0")),
            Pacote("Foz do Iguaçu", R.drawable.foz_do_iguacu_pr, 4, BigDecimal("2000.0")),
            Pacote("Recife", R.drawable.recife_pe, 3, BigDecimal("3000.0")),
            Pacote("Rio de Janeiro", R.drawable.rio_de_janeiro_rj, 5, BigDecimal("5000.0")),
            Pacote("Salvador", R.drawable.salvador_ba, 2, BigDecimal("2000.0")),
            Pacote("São Paulo", R.drawable.sao_paulo_sp, 1, BigDecimal("500.0"))
    )

}
