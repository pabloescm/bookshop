package com.moviles.books.api

import com.moviles.books.models.GeneroLibro
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.HTTP
import retrofit2.http.POST
import retrofit2.http.Path

interface APIGeneroLibroService {

    @POST("libro-generos")
    fun postGeneroLibro(@Body generoLibro: GeneroLibro): Call<GeneroLibro>

    //@DELETE("libro-generos")
   // fun deleteGeneroLibro(@Body generoLibro: GeneroLibro): Call<GeneroLibro>

    @HTTP(method = "DELETE", path = "libro-generos", hasBody = true)
    fun deleteGeneroLibro(@Body generoLibro: GeneroLibro): Call<Void>


}