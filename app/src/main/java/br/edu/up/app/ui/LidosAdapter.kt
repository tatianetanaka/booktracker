package br.edu.up.app.ui

import android.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import br.edu.up.app.R
import br.edu.up.app.model.Lido
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage

class LidosAdapter(
    private val activity: FragmentActivity,
    private val viewModel: LidosViewModel,
    private val lidos: List<Lido>

) : RecyclerView.Adapter<LidosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LidosViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_lidos, parent, false)
        return LidosViewHolder(view)
    }

    override fun onBindViewHolder(holder: LidosViewHolder, position: Int) {
        val lido = lidos[position]

        holder.tituloLido.text = lido.titulo
        holder.autorLido.text = lido.autor

        val storage = FirebaseStorage.getInstance()
        val storageReference = storage.getReference(lido.foto)
        storageReference.downloadUrl.addOnSuccessListener { imageURL ->
            Glide.with(activity)
                .load(imageURL)
                .into(holder.fotoLido)
        }

        storageReference.downloadUrl.addOnFailureListener {
            Glide.with(activity)
                .load(R.drawable.sem_foto)
                .into(holder.fotoLido)
        }

        holder.itemView.setOnClickListener { view ->
            viewModel.lido.value = lido
            view.findNavController().navigate(R.id.action_lista_para_detalhes_lido)
        }

        holder.itemView.setOnLongClickListener { view ->
            view?.let {
                AlertDialog.Builder(activity)
                    .setTitle("Atenção")
                    .setMessage("Deseja excluir esse livro lido?")
                    .setPositiveButton("Sim") { dialog, which ->
                        viewModel.repository.excluirLidos(lido.docId)
                    }
                    .setNegativeButton("Não", null)
                    .show()
            }
            true
        }
    }

    override fun getItemCount(): Int = lidos.size

}