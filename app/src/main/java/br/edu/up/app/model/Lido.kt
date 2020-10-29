package br.edu.up.app.model

import java.util.*

data class Lido(
    var docId: String,
    var titulo: String,
    var autor: String,
    var foto: String,
    var data_termino: String
)

{
    constructor() : this(String(), String(), String(), String(), String())
}