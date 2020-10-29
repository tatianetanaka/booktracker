package br.edu.up.app.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import br.edu.up.app.R
import br.edu.up.app.model.Lido
import kotlinx.android.synthetic.main.lidos_fragment.*

class LidosFragment : Fragment() {

    private lateinit var viewModel: LidosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.lidos_fragment, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(LidosViewModel::class.java)

        viewModel.lido.observe(viewLifecycleOwner, { lido ->

            txtTituloLido.setText(lido.titulo)
            txtAutorLido.setText(lido.autor)
            txtFotoLido.setText(lido.foto)

            view.findViewById<Button>(R.id.btnSalvarLido).setOnClickListener {
                var lido = Lido(
                    docId = lido.docId,
                    titulo = txtTituloLido.text.toString(),
                    autor = txtAutorLido.text.toString(),
                    foto = txtFotoLido.text.toString()
                )
                viewModel.repository.salvarLido(lido)
                findNavController().navigateUp()
            }

        })

        return view
    }

}