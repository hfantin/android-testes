package br.com.alura.aluraesporte.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.alura.aluraesporte.databinding.PagamentoBinding
import br.com.alura.aluraesporte.extensions.formatParaMoedaBrasileira
import br.com.alura.aluraesporte.model.Pagamento
import br.com.alura.aluraesporte.model.Produto
import br.com.alura.aluraesporte.ui.viewmodel.PagamentoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val FALHA_AO_CRIAR_PAGAMENTO = "Falha ao criar pagamento"
private const val COMPRA_REALIZADA = "Compra realizada"
class PagamentoFragment : Fragment() {


    private val argumentos by navArgs<PagamentoFragmentArgs>()
    private val produtoId by lazy { argumentos.produtoId }
//    private val produtoId by lazy {
//        arguments?.getLong(CHAVE_PRODUTO_ID)
//            ?: throw IllegalArgumentException(ID_PRODUTO_INVALIDO)
//    }
    private val viewModel: PagamentoViewModel by viewModel()
    private lateinit var produtoEscolhido: Produto
    private lateinit var binding: PagamentoBinding
    private val controlador by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PagamentoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraBotaoConfirmaPagamento()
        buscaProduto()
    }

    private fun buscaProduto() {
        viewModel.buscaProdutoPorId(produtoId).observe(this) {
            it?.let { produtoEncontrado ->
                produtoEscolhido = produtoEncontrado
                binding.pagamentoPreco.text = produtoEncontrado.preco
                    .formatParaMoedaBrasileira()
            }
        }
    }

    private fun configuraBotaoConfirmaPagamento() {
        binding.pagamentoBotaoConfirmaPagamento.setOnClickListener {
            criaPagamento()?.let(this::salva) ?: Toast.makeText(
                context,
                FALHA_AO_CRIAR_PAGAMENTO,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun salva(pagamento: Pagamento) {
        if (::produtoEscolhido.isInitialized) {
            viewModel.salva(pagamento)
                .observe(this) {
                    it?.dado?.let {
                        Toast.makeText(
                            context,
                            COMPRA_REALIZADA,
                            Toast.LENGTH_SHORT
                        ).show()
//                        controlador.popBackStack(R.id.listaProdutos, false)
//                        controlador.navigate(R.id.action_pagamento_to_listaProdutos)
                        val direction = PagamentoFragmentDirections.actionPagamentoToListaProdutos()
                        controlador.navigate(direction)
                    }
                }
        }
    }

    private fun criaPagamento(): Pagamento? {
        val numeroCartao = binding.pagamentoNumeroCartao
            .editText?.text.toString()
        val dataValidade = binding.pagamentoDataValidade
            .editText?.text.toString()
        val cvc = binding.pagamentoCvc
            .editText?.text.toString()
        return geraPagamento(numeroCartao, dataValidade, cvc)
    }

    private fun geraPagamento(
        numeroCartao: String,
        dataValidade: String,
        cvc: String
    ): Pagamento? = try {
        Pagamento(
            numeroCartao = numeroCartao.toInt(),
            dataValidade = dataValidade,
            cvc = cvc.toInt(),
            produtoId = produtoId,
            preco = produtoEscolhido.preco
        )
    } catch (e: NumberFormatException) {
        null
    }

}