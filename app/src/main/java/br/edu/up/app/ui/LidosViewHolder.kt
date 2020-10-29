package br.edu.up.app.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.up.app.R

class LidosViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tituloLido: TextView = view.findViewById(R.id.titulo_lido)
    val autorLido: TextView = view.findViewById(R.id.autor_lido)
    val fotoLido: ImageView = view.findViewById(R.id.imgFotoLido)

    override fun toString(): String {
        return super.toString() + " '" + tituloLido.text + "'"
    }
}