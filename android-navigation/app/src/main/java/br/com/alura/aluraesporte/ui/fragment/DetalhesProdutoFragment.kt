package br.com.alura.aluraesporte.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.alura.aluraesporte.databinding.DetalhesProdutoBinding
import br.com.alura.aluraesporte.extensions.formatParaMoedaBrasileira
import br.com.alura.aluraesporte.ui.viewmodel.DetalhesProdutoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetalhesProdutoFragment : Fragment() {
    private val arguments by navArgs<DetalhesProdutoFragmentArgs>()
    private val produtoId by lazy { arguments.produtoId }

    //    private val produtoId by lazy { requireArguments().getLong(CHAVE_PRODUTO_ID) }
    private val viewModel: DetalhesProdutoViewModel by viewModel { parametersOf(produtoId) }
    private val controlador by lazy { findNavController() }
    private lateinit var binding: DetalhesProdutoBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetalhesProdutoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buscaProduto()
        configuraBotaoComprar()
    }

    private fun configuraBotaoComprar() {
        binding.detalhesProdutoBotaoComprar.setOnClickListener {
            viewModel.produtoEncontrado.value?.let {
                val direction = DetalhesProdutoFragmentDirections.actionDetalhesProdutoToPagamento(produtoId)
                controlador.navigate(direction)
//                controlador.navigate(
//                    R.id.action_detalhesProduto_to_pagamento,
//                    bundleOf(CHAVE_PRODUTO_ID to produtoId)
//                )
            }
        }
    }

    private fun buscaProduto() {
        viewModel.produtoEncontrado.observe(this) {
            it?.let { produto ->
                binding.detalhesProdutoNome.text = produto.nome
                binding.detalhesProdutoPreco.text = produto.preco.formatParaMoedaBrasileira()
            }
        }
    }

}