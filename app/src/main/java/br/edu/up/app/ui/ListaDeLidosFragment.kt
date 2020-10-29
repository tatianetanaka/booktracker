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
import br.edu.up.app.model.Lido
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * A fragment representing a list of Items.
 */
class ListaDeLidosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.lista_lidos, container, false)
        var recycler = view.findViewById<RecyclerView>(R.id.list_lidos)
        var viewModel = ViewModelProvider(requireActivity()).get(LidosViewModel::class.java)

        viewModel.listaDeLidos.observe(requireActivity(), { lidos ->
            with(recycler) {
                adapter = LidosAdapter(requireActivity(), viewModel, lidos)
            }
        })

        view.findViewById<FloatingActionButton>(R.id.fab_lidos).setOnClickListener {
            viewModel.lido.value = Lido()
            findNavController().navigate(R.id.action_lista_para_detalhes_lido)
        }

        return view
    }

}