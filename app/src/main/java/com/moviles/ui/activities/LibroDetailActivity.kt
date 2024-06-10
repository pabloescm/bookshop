package com.moviles.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.moviles.books.databinding.ActivityLibroDetailBinding
import com.moviles.books.models.Libro
import com.moviles.books.repositories.LibroRepository

class LibroDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityLibroDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLibroDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val libroId = intent.getIntExtra("libroId", 0)
        LibroRepository.getLibroById(
            id = libroId,
            success = { libro ->
                displayLibroDetails(libro)
            },
            failure = { error ->
                error.printStackTrace()
            }
        )

        setupEventListeners()
    }

    private fun setupEventListeners() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun displayLibroDetails(libro: Libro?) {
        libro?.let {
            binding.lblLibroName.text = it.nombre
            binding.lblAutor.text = it.autor
            binding.lblEditorial.text = it.editorial
            binding.lblSinopsis.text = it.sinopsis
            binding.lblIsbn.text = it.isbn
            binding.lblCalificacion.text = it.calificacion.toString()
            binding.lblGeneros.text = it.generos.joinToString(", ") { genero -> genero.nombre }
            Glide.with(this)
                .load(it.imagen)
                .into(binding.imgDetailLibro)
        }
    }
}