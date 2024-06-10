package com.moviles.ui.activities

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.moviles.books.R
import com.moviles.books.databinding.ActivityAddGeneroLibroBinding
import com.moviles.books.models.Genero
import com.moviles.books.models.GeneroLibro
import com.moviles.books.repositories.GeneroLibroRepository
import com.moviles.books.repositories.GeneroRepository


class AddGeneroLibroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddGeneroLibroBinding
    private var generos: List<Genero>? = null
    private var selectedGeneroId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddGeneroLibroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        showLibroNameAndId()
        fetchGeneros()
        setupEventListener()

    }


    private fun fetchGeneros() {
        GeneroRepository.getGeneroList(
            success = { fetchedGeneros ->
                generos = fetchedGeneros
                setupSpinner()
            },
            failure = { error ->
                error.printStackTrace()
            }
        )
    }

    private fun setupSpinner() {
        // Create an ArrayAdapter using the genre names
        val adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_item,
            generos?.map { it.nombre } ?: listOf()
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerGenero.adapter = adapter

        // Set an onItemSelectedListener to save the selected genre ID
        binding.spinnerGenero.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                selectedGeneroId = generos?.get(position)?.id
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                selectedGeneroId = null
            }
        }
    }

    private fun onAddAssociationClicked() {
        val libroId = intent.getIntExtra("libroId", 0)
        if (selectedGeneroId != null) {
            val generoLibro = GeneroLibro(libro_id = libroId, genero_id = selectedGeneroId!!)
            GeneroLibroRepository.postGeneroLibro(
                generoLibro,
                success = { generoLibro ->
                    Toast.makeText(this, "Género asociado", Toast.LENGTH_SHORT).show()
                    finish()

                },
                failure = { error ->
                    Toast.makeText(this, "Error al asociar el género o ya existe", Toast.LENGTH_SHORT).show()
                    error.printStackTrace()
                }
            )
        }
    }

    private fun onDeleteAssociationClicked() {
        val libroId = intent.getIntExtra("libroId", 0)
        if (selectedGeneroId != null) {
            val generoLibro = GeneroLibro(libro_id = libroId, genero_id = selectedGeneroId!!)
            GeneroLibroRepository.deleteGeneroLibro(
                generoLibro,
                success = {
                    Toast.makeText(this, "Asociación eliminada", Toast.LENGTH_SHORT).show()
                    finish()

                },
                failure = { error ->
                    Toast.makeText(this, "Error al eliminar la asociación", Toast.LENGTH_SHORT).show()
                    error.printStackTrace()
                }
            )
        } else {
            Toast.makeText(this, "Seleccione un género", Toast.LENGTH_SHORT).show()
        }
        //show selectedGeneroId in a Toast
        Toast.makeText(this, "Genero ID: $selectedGeneroId", Toast.LENGTH_SHORT).show()
    }
    private fun showLibroNameAndId() {
        val libroId = intent.getIntExtra("libroId", 0)
        val libroNombre = intent.getStringExtra("libroNombre")
        binding.txtLibroGeneroShowId.text = "Libro ID: $libroId - $libroNombre"
    }

    private fun setupEventListener() {
        binding.btnAddAssociation.setOnClickListener {
            onAddAssociationClicked()
        }
        binding.btnDeleteAssociation.setOnClickListener {
            onDeleteAssociationClicked()
        }
    }


}