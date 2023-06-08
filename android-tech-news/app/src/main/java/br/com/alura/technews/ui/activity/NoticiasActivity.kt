package br.com.alura.technews.ui.activity

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import br.com.alura.technews.R
import br.com.alura.technews.databinding.ActivityNoticiasBinding
import br.com.alura.technews.model.Noticia
import br.com.alura.technews.ui.activity.extensions.transacaoFragment
import br.com.alura.technews.ui.fragments.ListaNoticiasFragment
import br.com.alura.technews.ui.fragments.VisualizaNoticiaFragment

private const val TAG_FRAGMENT_VISUALIZA_NOTICIA = "visualizaNoticia"

class NoticiasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoticiasBinding

//    Código não é mais necessário, estamos usando o koin para fazer injeção de dependência
//    private val viewModel: ListaNoticiasViewModel by viewModels(factoryProducer = {
//        ListaNoticiasViewModelFactory(NoticiaRepository(AppDatabase.getInstance(applicationContext).noticiaDAO))
//    })

//    private val viewModel by viewModel<ListaNoticiasViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoticiasBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
//        title = TITULO_APPBAR
        configuraFragmentPeloEstado(savedInstanceState)
    }

    private fun configuraFragmentPeloEstado(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace<ListaNoticiasFragment>(R.id.activity_noticias_container_primario)
            }
        } else {
            tentaReabrirFragmentVisualizaNoticia()
        }
    }

    private fun tentaReabrirFragmentVisualizaNoticia() {
        supportFragmentManager
            .findFragmentByTag(TAG_FRAGMENT_VISUALIZA_NOTICIA)?.let { fragment ->
                removeFragmentVisualizaNoticia(fragment)
                val novoFragment =
                    VisualizaNoticiaFragment().apply { arguments = fragment.arguments }
                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace(getContainer(), novoFragment, TAG_FRAGMENT_VISUALIZA_NOTICIA)
                }
            }
    }

    private fun removeFragmentVisualizaNoticia(fragment: Fragment) {
        transacaoFragment {
            remove(fragment)
        }
        supportFragmentManager.popBackStack()
    }

    private fun FragmentTransaction.getContainer() =
//        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        if (binding.activityNoticiasContainerSecundario != null) {
            R.id.activity_noticias_container_secundario
        } else {
            addToBackStack(null)
            R.id.activity_noticias_container_primario
        }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        when (fragment) {
            is ListaNoticiasFragment -> {
                configuraListaNoticias(fragment)
            }
            is VisualizaNoticiaFragment -> {
                configuraVisualizaNoticia(fragment)
            }
        }

    }

    private fun configuraVisualizaNoticia(fragment: VisualizaNoticiaFragment) {
//        fragment.quandoFinalizaTela = this::finish
        fragment.quandoFinalizaTela = {
            supportFragmentManager
                .findFragmentByTag(TAG_FRAGMENT_VISUALIZA_NOTICIA)?.let { fragment ->
                    removeFragmentVisualizaNoticia(fragment)
                }
        }
        fragment.quandoSelecionaMenuEdicao = this::abreFormularioEdicao
    }

    private fun configuraListaNoticias(fragment: ListaNoticiasFragment) {
        fragment.quandoNoticiaSeleciona = this::abreVisualizadorNoticia
        fragment.quandoFabSalvaNoticiaClicado = this::abreFormularioModoCriacao
    }

    private fun abreFormularioModoCriacao() {
        val intent = Intent(this, FormularioNoticiaActivity::class.java)
        startActivity(intent)
    }

    private fun abreVisualizadorNoticia(noticia: Noticia) {
        val bundle = bundleOf(NOTICIA_ID_CHAVE to noticia.id)
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<VisualizaNoticiaFragment>(
                getContainer(),
                args = bundle,
                tag = TAG_FRAGMENT_VISUALIZA_NOTICIA
            )
        }
    }

    private fun abreFormularioEdicao(noticia: Noticia) {
        val intent = Intent(this, FormularioNoticiaActivity::class.java)
        intent.putExtra(NOTICIA_ID_CHAVE, noticia.id)
        startActivity(intent)
    }


}

