package com.moviles.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.moviles.books.R
import com.moviles.books.databinding.ActivityFormGenreBinding
import com.moviles.books.databinding.ActivityGeneroBinding
import com.moviles.books.models.Genero
import com.moviles.books.repositories.GeneroRepository

class FormGenreActivity : AppCompatActivity() {
    lateinit var binding: ActivityFormGenreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormGenreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        checkExistingGenre()
        setEventListeners()

    }

    private fun checkExistingGenre() {
        val generoId = intent.getIntExtra("generoId", 0)
        if (generoId != 0) {
            GeneroRepository.getGeneroById(generoId,
                success = {
                    binding.txtFormGenreName.setText(it?.nombre)
                },
                failure = {
                    Toast.makeText(this, "Error loading genero", Toast.LENGTH_SHORT).show()
                    it.printStackTrace()
                }
            )
        }
    }

    private fun setEventListeners() {


        binding.btnSaveGenre.setOnClickListener() {
            val generoId = intent.getIntExtra("generoId", 0)

            if (generoId != 0) {
                val genero = Genero(
                    generoId, binding.txtFormGenreName.text.toString(), ArrayList()
                )
                GeneroRepository.updateGenero(
                    generoId,
                    genero,
                    success = {
                        Toast.makeText(
                            this,
                            "Genero ${genero.nombre} updated successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                    },
                    failure = {
                        Toast.makeText(
                            this,
                            "Error updating genero ${genero.nombre}",
                            Toast.LENGTH_SHORT
                        ).show()
                        it.printStackTrace()
                    }
                )

            } else {

                val genero = Genero(
                    0, binding.txtFormGenreName.text.toString(), ArrayList()
                )
                GeneroRepository.addGenero(
                    genero,
                    success = {
                        Toast.makeText(
                            this,
                            "Genero ${genero.nombre} added successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                    },
                    failure = {
                        Toast.makeText(
                            this,
                            "Error adding genero ${genero.nombre}",
                            Toast.LENGTH_SHORT
                        ).show()
                        it.printStackTrace()
                    }
                )
            }
        }


    }
}