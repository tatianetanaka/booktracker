package br.edu.up.app.ui

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.up.app.R

class ProgressoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tituloProgresso: TextView = view.findViewById(R.id.titulo_progresso)
    val autorProgresso: TextView = view.findViewById(R.id.autor_progresso)
    val fotoProgresso: ImageView = view.findViewById(R.id.imgFotoProgresso)
    val atualProgresso: TextView = view.findViewById(R.id.atual_progresso)
    val totalProgresso: TextView = view.findViewById(R.id.total_progresso)
    val porcentagemProgresso: TextView = view.findViewById(R.id.porcentagem_progresso)
    val progressBar: ProgressBar = view.findViewById(R.id.progressBar)

    override fun toString(): String {
        return super.toString() + " '" + tituloProgresso.text + "'"
    }
}