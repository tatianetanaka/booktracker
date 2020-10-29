package br.edu.up.app.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.edu.up.app.model.Lido
import br.edu.up.app.repository.LidoRepository

class LidosViewModel(app: Application) : AndroidViewModel(app) {
    var lido = MutableLiveData<Lido>()
    var repository = LidoRepository()
    var listaDeLidos = repository.listaDeLidos
}