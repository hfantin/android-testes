package br.com.alura.technews.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.alura.technews.model.Noticia
import br.com.alura.technews.repository.NoticiaRepository
import br.com.alura.technews.repository.Resource

class FormularioNoticiaViewModel(private val repository: NoticiaRepository) : ViewModel() {

    companion object {
        private val TAG: String = FormularioNoticiaViewModel::class.java.simpleName
    }

    fun salva(noticia: Noticia): LiveData<Resource<Void?>> =
        if (noticia.id > 0) {
            repository.edita(noticia)
        } else {
            repository.salva(noticia)
        }

    fun buscaPorId(id: Long) = repository.buscaPorId(id)
}

/* Código não é mais necessário, estamos usando o koin para fazer injeção de dependência
class FormularioNoticiaViewModelFactory(private val repository: NoticiaRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FormularioNoticiaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FormularioNoticiaViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
*/
