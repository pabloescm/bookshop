package com.moviles.books.repositories

import com.moviles.books.api.APILibroService
import com.moviles.books.models.Genero
import com.moviles.books.models.Libro
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object LibroRepository {

    /*
    * @param success: function to be executed when the request is successful
    * @param failure: function to be executed when the request fails
    * @return List of books
     */
    fun getLibroList( success: (List<Libro>?) -> Unit, failure: (Throwable) -> Unit){
        val retrofit = Retrofit.Builder()
            .baseUrl("http://apilibreria.jmacboy.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: APILibroService =
            retrofit.create(APILibroService::class.java)
        service.getLibros().enqueue(object : Callback<List<Libro>> {
            override fun onResponse(call: Call<List<Libro>>, response: Response<List<Libro>>) {
                val libroList = response.body()
                success(libroList)
            }

            override fun onFailure(call: Call<List<Libro>>, t: Throwable) {

                failure(t)
                t.printStackTrace()
            }
        })
    }


    /*
    * @param success: function to be executed when the request is successful
    * @param failure: function to be executed when the request fails
    * @param newLibro: new book to be inserted
    * @return The new book inserted
     */
    fun postLibro(newLibro: Libro, success: (Libro?) -> Unit, failure: (Throwable) -> Unit){
        val retrofit = Retrofit.Builder()
            .baseUrl("http://apilibreria.jmacboy.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: APILibroService =
            retrofit.create(APILibroService::class.java)
        service.postLibro(newLibro).enqueue(object : Callback<Libro> {
            override fun onResponse(call: Call<Libro>, response: Response<Libro>) {
                val libro = response.body()
                success(libro)
            }

            override fun onFailure(call: Call<Libro>, t: Throwable) {
                failure(t)
                t.printStackTrace()
            }
        })
    }

    fun getLibroById(id: Int, success: (Libro?) -> Unit, failure: (Throwable) -> Unit){
        val retrofit = Retrofit.Builder()
            .baseUrl("http://apilibreria.jmacboy.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: APILibroService = retrofit.create(APILibroService::class.java)
        service.getLibrosById(id).enqueue(object : Callback<Libro> {
            override fun onResponse(call: Call<Libro>, response: Response<Libro>) {
                val libro = response.body()
                success(libro)
            }

            override fun onFailure(call: Call<Libro>, t: Throwable) {
                failure(t)
                t.printStackTrace()
            }
        })
    }

    fun updateLibro(id: Int, updatedLibro: Libro, success: (Libro?) -> Unit, failure: (Throwable) -> Unit){
        val retrofit = Retrofit.Builder()
            .baseUrl("http://apilibreria.jmacboy.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: APILibroService = retrofit.create(APILibroService::class.java)
        service.updateLibro(id, updatedLibro).enqueue(object : Callback<Libro> {
            override fun onResponse(call: Call<Libro>, response: Response<Libro>) {
                val libro = response.body()
                success(libro)
            }

            override fun onFailure(call: Call<Libro>, t: Throwable) {
                failure(t)
                t.printStackTrace()
            }
        })
    }

    fun deleteLibro(id: Int, success: (Libro?) -> Unit, failure: (Throwable) -> Unit){
        val retrofit = Retrofit.Builder()
            .baseUrl("http://apilibreria.jmacboy.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: APILibroService = retrofit.create(APILibroService::class.java)
        service.deleteLibro(id).enqueue(object : Callback<Libro> {
            override fun onResponse(call: Call<Libro>, response: Response<Libro>) {
                val libro = response.body()
                success(libro)
            }

            override fun onFailure(call: Call<Libro>, t: Throwable) {
                failure(t)
                t.printStackTrace()
            }
        })
    }







}