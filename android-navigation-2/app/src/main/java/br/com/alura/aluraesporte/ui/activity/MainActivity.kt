package br.com.alura.aluraesporte.ui.activity

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import br.com.alura.aluraesporte.R
import br.com.alura.aluraesporte.databinding.MainActivityBinding
import br.com.alura.aluraesporte.ui.viewmodel.EstadoAppViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: EstadoAppViewModel by viewModel()
    private lateinit var binding: MainActivityBinding

    private val controlador by lazy {
        findNavController(R.id.main_activity_nav_host)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
//        setContentView(R.layout.main_activity)
        controlador.addOnDestinationChangedListener { controller, destination, arguments ->
            title = destination.label
//            when(destination.id) {
//                R.id.listaProdutos -> supportActionBar?.show()
//                R.id.login -> supportActionBar?.hide()
//            }
            viewModel.componentes.observe(this) {
                it?.let { temComponentes ->
                    if (temComponentes.appBar) {
                        supportActionBar?.show()
                    } else {
                        supportActionBar?.hide()
                    }
                    if(temComponentes.bottomNavigation) {
                        binding.mainActivityBottomNavigation.visibility = VISIBLE
                    } else {
                        binding.mainActivityBottomNavigation.visibility = GONE
                    }
                }
            }
        }
        binding.mainActivityBottomNavigation.setupWithNavController(controlador)
    }


}
