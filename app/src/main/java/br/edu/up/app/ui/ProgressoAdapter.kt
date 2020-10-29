package br.edu.up.app.ui

import android.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import br.edu.up.app.R
import br.edu.up.app.model.Progresso
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage

class ProgressoAdapter(
    private val activity: FragmentActivity,
    private val viewModel: ProgressoViewModel,
    private val progressos: List<Progresso>
) : RecyclerView.Adapter<ProgressoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgressoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_progresso, parent, false)
        return ProgressoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProgressoViewHolder, position: Int) {
        val progresso = progressos[position]
        holder.tituloProgresso.text = progresso.titulo
        holder.autorProgresso.text = progresso.autor
        holder.atualProgresso.text = progresso.pagina_atual.toString()
        holder.totalProgresso.text = progresso.paginas_totais.toString()

        var porcentagem = (progresso.pagina_atual * 100 / progresso.paginas_totais).toString()

        holder.porcentagemProgresso.text = porcentagem
        holder.progressBar.progress = porcentagem.toInt()

        val storage = FirebaseStorage.getInstance()
        val storageReference = storage.getReference(progresso.foto)
        storageReference.downloadUrl.addOnSuccessListener { imageURL ->
            Glide.with(activity)
                .load(imageURL)
                .into(holder.fotoProgresso)
        }

        storageReference.downloadUrl.addOnFailureListener {
            Glide.with(activity)
                .load(R.drawable.book_image)
                .into(holder.fotoProgresso)
        }

        holder.itemView.setOnClickListener { view ->
            viewModel.progresso.value = progresso
            view.findNavController().navigate(R.id.action_lista_para_detalhes_progresso)
        }

        holder.itemView.setOnLongClickListener { view ->
            view?.let {
                AlertDialog.Builder(activity)
                    .setTitle("Atenção")
                    .setMessage("Deseja excluir esse livro em progresso?")
                    .setPositiveButton("Sim") { dialog, which ->
                        viewModel.repository.excluirProgresso(progresso.docId)
                    }
                    .setNegativeButton("Não", null)
                    .show()
            }
            true
        }
    }

    override fun getItemCount(): Int = progressos.size
}