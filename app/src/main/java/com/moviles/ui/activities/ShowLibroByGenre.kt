package com.moviles.ui.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moviles.books.R
import com.moviles.books.databinding.ActivityShowLibroByGenreBinding
import com.moviles.books.models.Genero
import com.moviles.books.repositories.GeneroRepository
import com.moviles.ui.adapters.LibroByGenreAdapter


class ShowLibroByGenre : AppCompatActivity() {
    lateinit var binding: ActivityShowLibroByGenreBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: LibroByGenreAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowLibroByGenreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val genreId = intent.getIntExtra("generoId", 0)


        setupRecyclerView()
        fetchLibrosByGenre(genreId)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupRecyclerView() {
        adapter = LibroByGenreAdapter(arrayListOf())
        binding.lstLibroByGenre.apply {
            this.adapter = this@ShowLibroByGenre.adapter
            this.layoutManager = LinearLayoutManager(this@ShowLibroByGenre)
        }
    }

    private fun fetchLibrosByGenre(genreId: Int) {

        GeneroRepository.getGeneroById(
            genreId,
            success = {
               //Log.d("ShowLibroByGenre", "Genero: ${it}")


                val genero = it?.let { it1 -> Genero(it1.id, it1.nombre,it1.libros) }
                Log.d("ShowLibroByGenre", "Genero: ${genero?.libros}")
                adapter.updateData(genero?.libros)


            },
            failure = {
                it.printStackTrace()
                Toast.makeText(this, "Error fetching genre", Toast.LENGTH_SHORT).show()
            }
        )
    }



}