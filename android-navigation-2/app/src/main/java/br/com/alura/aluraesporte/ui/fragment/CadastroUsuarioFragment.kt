package br.com.alura.aluraesporte.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.alura.aluraesporte.databinding.CadastroUsuarioBinding
import br.com.alura.aluraesporte.ui.viewmodel.ComponentesVisuais
import br.com.alura.aluraesporte.ui.viewmodel.EstadoAppViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class CadastroUsuarioFragment: Fragment() {

//    private val estadoAppViewModel: EstadoAppViewModel by sharedViewModel()
    private val estadoAppViewModel: EstadoAppViewModel by activityViewModel()

    private val controlador by lazy {
        findNavController()
    }

    private lateinit var binding: CadastroUsuarioBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CadastroUsuarioBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        estadoAppViewModel.temComponentes = ComponentesVisuais()
        binding.cadastroUsuarioBotaoCadastrar.setOnClickListener {
            controlador.popBackStack()
        }
    }
}