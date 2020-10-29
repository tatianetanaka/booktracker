package br.edu.up.app.model

data class Lido (
    var docId: String,
    var titulo: String,
    var autor: String,
    var foto: String
)

{
    constructor() : this(String(), String(), String(), String())
}