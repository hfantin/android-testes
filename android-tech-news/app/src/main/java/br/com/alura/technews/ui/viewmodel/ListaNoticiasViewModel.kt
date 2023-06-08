package br.com.alura.technews.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.alura.technews.model.Noticia
import br.com.alura.technews.repository.NoticiaRepository
import br.com.alura.technews.repository.Resource

class ListaNoticiasViewModel(private val repository: NoticiaRepository) : ViewModel() {

    companion object {
        private val TAG: String = ListaNoticiasViewModel::class.java.simpleName
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(TAG, "destruindo viewmodel")
    }

    fun buscaTodos() : LiveData<Resource<List<Noticia>?>> {
        return repository.buscaTodos()
    }
}

/* Código não é mais necessário, estamos usando o koin para fazer injeção de dependência
class ListaNoticiasViewModelFactory(private val repository: NoticiaRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListaNoticiasViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ListaNoticiasViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}*/
