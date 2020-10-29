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
import br.edu.up.app.model.Progresso
import kotlinx.android.synthetic.main.lidos_fragment.*
import kotlinx.android.synthetic.main.progresso_fragment.*

class ProgressoFragment : Fragment() {

    private lateinit var viewModel: ProgressoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.progresso_fragment, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(ProgressoViewModel::class.java)

        viewModel.progresso.observe(viewLifecycleOwner, { progresso ->

            txtTituloProgresso.setText(progresso.titulo)
            txtAutorProgresso.setText(progresso.autor)
            txtFotoProgresso.setText(progresso.foto)
            txtTotalProgresso.setText(progresso.paginas_totais.toString())
            txtAtualProgresso.setText(progresso.pagina_atual.toString())

            view.findViewById<Button>(R.id.btnSalvarProgresso).setOnClickListener {
                var progresso = Progresso(
                    docId = progresso.docId,
                    titulo = txtTituloProgresso.text.toString(),
                    autor = txtAutorProgresso.text.toString(),
                    foto = txtFotoProgresso.text.toString(),
                    paginas_totais = txtTotalProgresso.text.toString().toInt(),
                    pagina_atual = txtAtualProgresso.text.toString().toInt()
                )
                viewModel.repository.salvarProgresso(progresso)
                findNavController().navigateUp()
            }

        })

        return view
    }

}