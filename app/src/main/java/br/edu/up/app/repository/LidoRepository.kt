package br.edu.up.app.repository

import androidx.lifecycle.MutableLiveData
import br.edu.up.app.model.Lido
import com.google.firebase.firestore.FirebaseFirestore

class LidoRepository {
    var listaDeLidos = MutableLiveData<MutableList<Lido>>()
    private val db = FirebaseFirestore.getInstance()

    init {
        db.collection("lidos")
            .get()
            .addOnCompleteListener { docs ->
                if (docs.isSuccessful){
                    var lidos = mutableListOf<Lido>()
                    docs.result?.forEach { doc ->
                        val lido = doc.toObject(Lido::class.java)
                        if (lido != null){
                            lido.docId = doc.id
                            lidos.add(lido)
                        }
                    }
                    listaDeLidos.value = lidos
                } else {
                    listaDeLidos.value = mutableListOf()
                }
            }

        db.collection("lidos")
            .addSnapshotListener { snapshot, _ ->
                if (snapshot != null){
                    var lidos = mutableListOf<Lido>()
                    snapshot.documents.forEach { doc ->
                        val lido = doc.toObject(Lido::class.java)
                        if (lido != null){
                            lido.docId = doc.id
                            lidos.add(lido)
                        }
                    }
                    listaDeLidos.value = lidos
                }
            }
    }

    fun salvarLido(lido : Lido) {
        if(lido.docId.isBlank()){
            var doc = db.collection("lidos").document()
            lido.docId = doc.id
            doc.set(lido)
        } else {
            db.collection("lidos")
                .document(lido.docId)
                .set(lido)
        }
    }

    fun excluirLidos(docId : String) {
        db.collection("lidos")
            .document(docId)
            .delete()
    }
}