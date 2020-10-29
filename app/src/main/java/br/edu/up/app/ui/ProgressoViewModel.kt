package br.edu.up.app.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.edu.up.app.model.Lido
import br.edu.up.app.model.Progresso
import br.edu.up.app.repository.LidoRepository
import br.edu.up.app.repository.ProgressoRepository

class ProgressoViewModel(app: Application) : AndroidViewModel(app) {
    var progresso = MutableLiveData<Progresso>()
    var repository = ProgressoRepository()
    var listaDeProgressos = repository.listaDeProgressos
}