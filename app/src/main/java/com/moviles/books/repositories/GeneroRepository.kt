package com.moviles.books.repositories

import com.moviles.books.api.APIGeneroService
import com.moviles.books.api.APILibroService
import com.moviles.books.models.Genero
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GeneroRepository {

    /*
    * @param success: function to be executed when the request is successful
    * @param failure: function to be executed when the request fails
    * @return List of genres
     */
    fun getGeneroList(success: (List<Genero>?) -> Unit, failure: (Throwable) -> Unit){
        val retrofit = Retrofit.Builder()
            .baseUrl("http://apilibreria.jmacboy.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: APIGeneroService =
            retrofit.create(APIGeneroService::class.java)
        service.getGeneros().enqueue(object : Callback<List<Genero>> {
            override fun onResponse(call: Call<List<Genero>>, response: Response<List<Genero>>) {
                val generoList = response.body()
                success(generoList)
            }

            override fun onFailure(call: Call<List<Genero>>, t: Throwable) {
                failure(t)
                t.printStackTrace()
            }
        })
    }

    fun getGeneroById(id: Int, success: (Genero?) -> Unit, failure: (Throwable) -> Unit){
        val retrofit = Retrofit.Builder()
            .baseUrl("http://apilibreria.jmacboy.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: APIGeneroService =
            retrofit.create(APIGeneroService::class.java)
        service.getGeneroById(id).enqueue(object : Callback<Genero> {
            override fun onResponse(call: Call<Genero>, response: Response<Genero>) {
                val genero = response.body()
                success(genero)
            }

            override fun onFailure(call: Call<Genero>, t: Throwable) {
                failure(t)
                t.printStackTrace()
            }
        })
    }

    fun addGenero(newGenero: Genero, success: (Genero?) -> Unit, failure: (Throwable) -> Unit){
        val retrofit = Retrofit.Builder()
            .baseUrl("http://apilibreria.jmacboy.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: APIGeneroService =
            retrofit.create(APIGeneroService::class.java)
        service.addGenero(newGenero).enqueue(object : Callback<Genero> {
            override fun onResponse(call: Call<Genero>, response: Response<Genero>) {
                val genero = response.body()
                success(genero)
            }

            override fun onFailure(call: Call<Genero>, t: Throwable) {
                failure(t)
                t.printStackTrace()
            }
        })
    }

fun updateGenero(id: Int, genero: Genero, success: (Genero?) -> Unit, failure: (Throwable) -> Unit){
        val retrofit = Retrofit.Builder()
            .baseUrl("http://apilibreria.jmacboy.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: APIGeneroService =
            retrofit.create(APIGeneroService::class.java)
        service.updateGenero(id, genero).enqueue(object : Callback<Genero> {
            override fun onResponse(call: Call<Genero>, response: Response<Genero>) {
                val genero = response.body()
                success(genero)
            }

            override fun onFailure(call: Call<Genero>, t: Throwable) {
                failure(t)
                t.printStackTrace()
            }
        })
    }

    fun deleteGenero(id: Int, success: (Genero?) -> Unit, failure: (Throwable) -> Unit){
        val retrofit = Retrofit.Builder()
            .baseUrl("http://apilibreria.jmacboy.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: APIGeneroService =
            retrofit.create(APIGeneroService::class.java)
        service.deleteGenero(id).enqueue(object : Callback<Genero> {
            override fun onResponse(call: Call<Genero>, response: Response<Genero>) {
                val genero = response.body()
                success(genero)
            }

            override fun onFailure(call: Call<Genero>, t: Throwable) {
                failure(t)
                t.printStackTrace()
            }
        })
    }
}