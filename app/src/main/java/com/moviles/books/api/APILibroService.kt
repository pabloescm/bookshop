package com.moviles.books.api

import com.moviles.books.models.Genero
import com.moviles.books.models.Libro
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface APILibroService {


    @GET("libros")
    fun getLibros(): Call<List<Libro>>

    @GET("libros/{id}")
    fun getLibrosById(@Path("id") id: Int): Call<Libro>

    @POST("libros")
    fun postLibro(@Body newLibro: Libro): Call<Libro>

    @PUT("libros/{id}")
    fun updateLibro(@Path("id") id: Int, @Body libro: Libro): Call<Libro>

    @DELETE("libros/{id}")
    fun deleteLibro(@Path("id") id: Int): Call<Libro>





}