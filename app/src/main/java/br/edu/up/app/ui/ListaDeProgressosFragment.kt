package br.edu.up.app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.edu.up.app.R
import br.edu.up.app.model.Progresso
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListaDeProgressosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.lista_de_progressos, container, false)
        var recycler = view.findViewById<RecyclerView>(R.id.list_progresso)
        var viewModel = ViewModelProvider(requireActivity()).get(ProgressoViewModel::class.java)

        viewModel.listaDeProgressos.observe(requireActivity(), { progressos ->
            with(recycler) {
                adapter = ProgressoAdapter(requireActivity(), viewModel, progressos)
            }
        })

        view.findViewById<FloatingActionButton>(R.id.fab_progresso).setOnClickListener {
            viewModel.progresso.value = Progresso()
            findNavController().navigate(R.id.action_lista_para_detalhes_progresso)
        }

        return view
    }

}