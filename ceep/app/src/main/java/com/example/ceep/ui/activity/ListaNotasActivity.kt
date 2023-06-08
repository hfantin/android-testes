package com.example.ceep.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.ceep.R
import com.example.ceep.dao.NotaDAO
import com.example.ceep.databinding.ActivityListaNotasBinding
import com.example.ceep.model.Nota
import com.example.ceep.ui.recyclerview.adapter.ListaNotasAdapter
import com.example.ceep.ui.recyclerview.helper.callback.NotaItemTouchHelperCallback


class ListaNotasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaNotasBinding
    private lateinit var adapter: ListaNotasAdapter
    private val dao: NotaDAO by lazy { inicializarLista() }
    private var activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { onResult(it) }

    private fun onResult(result: ActivityResult) {
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.run {
                when {
                    this.hasExtra(NOTA_INCLUIDA) -> incluirNota(this.getParcelableExtra(NOTA_INCLUIDA)!!)
                    this.hasExtra(NOTA_ALTERADA) -> alterarNota(this.getIntExtra(POSICAO, -1), this.getParcelableExtra(NOTA_ALTERADA)!!)
                    else -> {}
                }
            } ?: Toast.makeText(this, "dados invalidos", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "cancelado", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inicializarLista() =
        NotaDAO().apply {
            (1..15).forEach { this.insere(Nota("Nota $it", "Descrição $it")) }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaNotasBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        title = getString(R.string.titulos_notas)
        configuraBotao()
        configuraAdapter()
    }

    private fun incluirNota(nota: Nota) {
        Log.i("ListaNotas", "inclui a nota ${nota.titulo}")
        dao.insere(nota)
        adapter.adiciona(nota)
    }

    private fun alterarNota(posicao: Int, nota: Nota) {
        Log.i("ListaNotas", "alterei a nota $posicao ${nota.titulo}")
        dao.altera(posicao, nota)
        adapter.altera(posicao, nota)
    }

    private fun configuraBotao() {
        binding.listaNotasInsereNota.setOnClickListener {
            activityResultLauncher.launch(Intent(this@ListaNotasActivity, FormularioNotaActivity::class.java))
        }
    }

    private fun configuraAdapter() {
        adapter = ListaNotasAdapter(dao.todos()) { posicao, nota ->
            activityResultLauncher.launch(
                Intent(this@ListaNotasActivity, FormularioNotaActivity::class.java).apply {
                    putExtra(NOTA_ALTERAR, nota)
                    putExtra(POSICAO, posicao)
                }
            )
        }
        binding.listaNotasRecyclerview.adapter = adapter
        val itemTouchHelper = ItemTouchHelper(NotaItemTouchHelperCallback(adapter))
        itemTouchHelper.attachToRecyclerView(binding.listaNotasRecyclerview)
    }

}