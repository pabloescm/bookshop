package com.moviles.books.repositories

import com.moviles.books.api.APIGeneroLibroService
import com.moviles.books.models.GeneroLibro
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GeneroLibroRepository {
    fun postGeneroLibro(generoLibro: GeneroLibro, success: (GeneroLibro?) -> Unit, failure: (Throwable) -> Unit) {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://apilibreria.jmacboy.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: APIGeneroLibroService = retrofit.create(APIGeneroLibroService::class.java)
        service.postGeneroLibro(generoLibro).enqueue(object : Callback<GeneroLibro> {
            override fun onResponse(call: Call<GeneroLibro>, response: Response<GeneroLibro>) {
                val generoLibroResponse = response.body()
                success(generoLibroResponse)
            }

            override fun onFailure(call: Call<GeneroLibro>, t: Throwable) {
                failure(t)
                t.printStackTrace()
            }
        })
    }


    fun deleteGeneroLibro(generoLibro: GeneroLibro, success: () -> Unit, failure: (Throwable) -> Unit) {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://apilibreria.jmacboy.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: APIGeneroLibroService = retrofit.create(APIGeneroLibroService::class.java)
        service.deleteGeneroLibro(generoLibro).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                success()
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                failure(t)
            }
        })
    }
}