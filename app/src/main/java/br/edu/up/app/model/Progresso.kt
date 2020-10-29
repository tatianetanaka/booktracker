package br.edu.up.app.model

data class Progresso (
    var docId: String,
    var titulo: String,
    var autor: String,
    var foto: String,
    var paginas_totais: Int,
    var pagina_atual: Int
)

{
    constructor() : this(String(), String(), String(), String(), 0, 0)
}