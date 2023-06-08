package br.com.alura.aluraesporte.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.alura.aluraesporte.databinding.LoginBinding
import br.com.alura.aluraesporte.ui.viewmodel.ComponentesVisuais
import br.com.alura.aluraesporte.ui.viewmodel.EstadoAppViewModel
import br.com.alura.aluraesporte.ui.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment: Fragment() {

    private val estadoAppViewModel: EstadoAppViewModel by activityViewModel()
    private val controlador by lazy {
        findNavController()
    }

    private val viewModel: LoginViewModel by viewModel()

    private lateinit var binding: LoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        estadoAppViewModel.temComponentes = ComponentesVisuais()
        binding.loginBotaoLogar.setOnClickListener {
            viewModel.loga()
            vaiParaListaProdutos()
        }
        binding.loginBotaoCadastrarUsuario.setOnClickListener {
            val direcao = LoginFragmentDirections.acaoLoginParaCadastroUsuario()
            controlador.navigate(direcao)
        }
    }

    private fun vaiParaListaProdutos() {
        val direcao = LoginFragmentDirections.acaoLoginParaListaProdutos()
        controlador.navigate(direcao)
    }

}