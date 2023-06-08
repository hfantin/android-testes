package com.example.ceep.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.ceep.R
import com.example.ceep.databinding.ActivityFormularioNotaBinding
import com.example.ceep.model.Nota

class FormularioNotaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormularioNotaBinding
    private var notaAlterada: Nota? = null
    private var posicao: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormularioNotaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        title = getString(R.string.titulo_insere_nota)
        obterNotaAlterada()
    }

    private fun obterNotaAlterada() {
        if (intent.hasExtra(NOTA_ALTERAR)) {
            title = getString(R.string.titulo_altera_nota)
            notaAlterada = intent.getParcelableExtra(NOTA_ALTERAR)!!
            posicao = intent.getIntExtra(POSICAO, -1)
            binding.formularioNotaTitulo.setText(notaAlterada?.titulo)
            binding.formularioNotaDescricao.setText(notaAlterada?.descricao)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_formulario_nota_salva, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (ehMenuSalvaNota(item)) {
            val acao = notaAlterada?.let { NOTA_ALTERADA } ?: NOTA_INCLUIDA
            retornaNota(acao, criarNota())
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun retornaNota(acao: String, notaCriada: Nota) {
        setResult(RESULT_OK, Intent().apply {
            putExtra(acao, notaCriada)
            putExtra(POSICAO, posicao)
        })
    }

    private fun criarNota() = Nota(binding.formularioNotaTitulo.text.toString(), binding.formularioNotaDescricao.text.toString())

    private fun ehMenuSalvaNota(item: MenuItem) = item.itemId === R.id.menu_formulario_nota_ic_salva
}