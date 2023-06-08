package br.com.alura.technews.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.alura.technews.repository.NoticiaRepository
import br.com.alura.technews.repository.Resource

class VisualizaNoticiaViewModel(
    id: Long,
    private val repository: NoticiaRepository
) : ViewModel() {

    companion object {
        private val TAG: String = VisualizaNoticiaViewModel::class.java.simpleName
    }

    val noticiaEncontrada = repository.buscaPorId(id)

    fun remove(): LiveData<Resource<Void?>> {
        return noticiaEncontrada.value?.run {
            repository.remove(this)
        } ?: MutableLiveData<Resource<Void?>>().also {
            it.value = Resource(null, "Notícia não encontrada")
        }
    }

}

/* Código não é mais necessário, estamos usando o koin para fazer injeção de dependência
class VisualizaNoticiaViewModelFactory(
    private val id: Long,
    private val repository: NoticiaRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VisualizaNoticiaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return VisualizaNoticiaViewModel(id, repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}*/
