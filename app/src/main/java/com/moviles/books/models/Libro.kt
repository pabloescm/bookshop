package com.moviles.books.models


data class Libro (
    val id: Int,
    val nombre: String,
    val autor: String,
    val editorial: String,
    val imagen: String,
    val sinopsis: String,
    val isbn: String,
    val calificacion: Long,
    val generos: List<Genero>
)

