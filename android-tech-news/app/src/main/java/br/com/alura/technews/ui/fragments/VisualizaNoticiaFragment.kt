package br.com.alura.technews.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import br.com.alura.technews.R
import br.com.alura.technews.databinding.VisualizaNoticiaBinding
import br.com.alura.technews.model.Noticia
import br.com.alura.technews.ui.activity.NOTICIA_ID_CHAVE
import br.com.alura.technews.ui.activity.extensions.mostraErro
import br.com.alura.technews.ui.viewmodel.VisualizaNoticiaViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


private const val NOTICIA_NAO_ENCONTRADA = "Notícia não encontrada"
private const val MENSAGEM_FALHA_REMOCAO = "Não foi possível remover notícia"
private const val TITULO_APPBAR = "Notícia"

class VisualizaNoticiaFragment : Fragment() {

    private lateinit var binding: VisualizaNoticiaBinding

    private val noticiaId: Long by lazy { requireArguments().getLong(NOTICIA_ID_CHAVE) }

    private val viewModel by viewModel<VisualizaNoticiaViewModel> { parametersOf(noticiaId) }

    var quandoSelecionaMenuEdicao: (noticia: Noticia) -> Unit = {}
    var quandoFinalizaTela: () -> Unit = {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        verificaIdDaNoticia()
        buscaNoticiaSelecionada()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = VisualizaNoticiaBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = TITULO_APPBAR
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.visualiza_noticia_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.visualiza_noticia_menu_edita -> {
                viewModel.noticiaEncontrada.value?.let(quandoSelecionaMenuEdicao)
            }
            R.id.visualiza_noticia_menu_remove -> remove()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun buscaNoticiaSelecionada() {
        viewModel.noticiaEncontrada.observe(this) { noticiaEncontrada ->
            noticiaEncontrada?.let {
                preencheCampos(it)
            }
        }
    }

    private fun verificaIdDaNoticia() {
        if (noticiaId == 0L) {
            mostraErro(NOTICIA_NAO_ENCONTRADA)
            quandoFinalizaTela()
        }
    }

    private fun preencheCampos(noticia: Noticia) {
        binding.visualizaNoticiaTitulo.text = noticia.titulo
        binding.visualizaNoticiaTexto.text = noticia.texto
    }

    private fun remove() {
        viewModel.remove().observe(this) {
            if (it.erro == null) {
                quandoFinalizaTela()
            } else {
                mostraErro(MENSAGEM_FALHA_REMOCAO)
            }
        }
    }


}