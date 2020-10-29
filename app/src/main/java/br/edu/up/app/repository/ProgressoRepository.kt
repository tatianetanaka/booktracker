package br.edu.up.app.repository

import androidx.lifecycle.MutableLiveData
import br.edu.up.app.model.Progresso
import com.google.firebase.firestore.FirebaseFirestore

class ProgressoRepository {
    var listaDeProgressos = MutableLiveData<MutableList<Progresso>>()
    private val db = FirebaseFirestore.getInstance()

    init {
        db.collection("progressos")
            .get()
            .addOnCompleteListener { docs ->
                if (docs.isSuccessful){
                    var progressos = mutableListOf<Progresso>()
                    docs.result?.forEach { doc ->
                        val progresso = doc.toObject(Progresso::class.java)
                        if (progresso != null){
                            progresso.docId = doc.id
                            progressos.add(progresso)
                        }
                    }
                    listaDeProgressos.value = progressos
                } else {
                    listaDeProgressos.value = mutableListOf()
                }
            }

        db.collection("progressos")
            .addSnapshotListener { snapshot, _ ->
                if (snapshot != null){
                    var progressos = mutableListOf<Progresso>()
                    snapshot.documents.forEach { doc ->
                        val progresso = doc.toObject(Progresso::class.java)
                        if (progresso != null){
                            progresso.docId = doc.id
                            progressos.add(progresso)
                        }
                    }
                    listaDeProgressos.value = progressos
                }
            }
    }

    fun salvarProgresso(progresso : Progresso) {
        if(progresso.docId.isBlank()){
            var doc = db.collection("progressos").document()
            progresso.docId = doc.id
            doc.set(progresso)
        } else {
            db.collection("progressos")
                .document(progresso.docId)
                .set(progresso)
        }
    }

    fun excluirProgresso(docId : String) {
        db.collection("progressos")
            .document(docId)
            .delete()
    }
}