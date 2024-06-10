package com.moviles.books.api

import com.moviles.books.models.Genero
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface APIGeneroService {

    @GET("generos")
    fun getGeneros(): Call<List<Genero>>


    @GET("generos/{id}")
    fun getGeneroById(@Path("id") id: Int): Call<Genero>

    @POST("generos")
    fun addGenero(@Body newGenero: Genero): Call<Genero>

    @PUT("generos/{id}")
    fun updateGenero(@Path("id") id: Int, @Body genero: Genero): Call<Genero>

    @DELETE("generos/{id}")
    fun deleteGenero(@Path("id") id: Int): Call<Genero>
}