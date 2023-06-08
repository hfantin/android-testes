package br.com.alura.ceep.ui.dialog

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import br.com.alura.ceep.databinding.FormularioInserirImagemBinding

private const val INSERIR_URL = "Inserir URL"
private const val CANCELAR = "Cancelar"
private const val SALVAR = "Salvar"

class CarregaImagemDialog {

    private lateinit var binding:  FormularioInserirImagemBinding

    fun mostra(
        context: Context,
        urlAtual: String = "",
        quandoTemUrlNova: (urlNova: String) -> Unit
    ) {
        binding = FormularioInserirImagemBinding
            .inflate( LayoutInflater.from(context), null, false)
        with(binding.root) {

            binding.formularioInserirImagemUrl.setText(urlAtual)
//            val campoUrl = binding.formularioInserirImagemUrl
//            configuraCampoUrl(urlAtual)?.let { campoUrl ->
                configuraDialog(context, this, binding.formularioInserirImagemUrl, quandoTemUrlNova)
//            }
        }

//        val inflate: View = LayoutInflater.from(context).inflate(
//            R.layout.formulario_inserir_imagem,
//            null,
//            false
//        )
//        inflate?.let { viewCriada ->
//            configuraCampoUrl(viewCriada, urlAtual)?.let { campoUrl ->
//                configuraDialog(context, viewCriada, campoUrl, quandoTemUrlNova)
//            }
//        }
    }

    private fun configuraDialog(
        context: Context,
        viewCriada: View,
        campoUrl: EditText,
        quandoTemUrlNova: (urlNova: String) -> Unit
    ): AlertDialog? {
        return AlertDialog.Builder(context)
            .setTitle(INSERIR_URL)
            .setView(viewCriada)
            .setPositiveButton(SALVAR) { _, _ ->
                val urlNova = campoUrl.text.toString()
                quandoTemUrlNova(urlNova)
            }
            .setNegativeButton(CANCELAR, null)
            .show()
    }

//    private fun configuraCampoUrl(urlAtual: String): EditText? {
//        binding.formularioInserirImagemUrl.setText(urlAtual)
//        val campoUrl = binding.formularioInserirImagemUrl
//        urlAtual.let(campoUrl::setText)
//        return campoUrl
//    }

}