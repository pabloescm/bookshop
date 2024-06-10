package com.moviles.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.moviles.books.models.Genero
import com.moviles.books.repositories.LibroRepository
import com.moviles.books.databinding.ActivityFormLibroBinding
import com.moviles.books.models.Libro

class FormLibroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFormLibroBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormLibroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkExistinBook()

        binding.btnSaveLibro.setOnClickListener {
            val libroId = intent.getIntExtra("libroId", 0)
            Toast.makeText(this, "LibroId: $libroId", Toast.LENGTH_SHORT).show()
            val libro = Libro(
                id = libroId,
                nombre = binding.txtNombreLibro.text.toString(),
                autor = binding.txtAutorLibro.text.toString(),
                editorial = binding.txtEditorialLibro.text.toString(),
                imagen = binding.txtUrlImagenLibro.text.toString(),
                sinopsis = binding.txtSinopsisLibro.text.toString(),
                isbn = binding.textInputEditText.text.toString(),
                calificacion = binding.textInputEditText2.text.toString().toLong(),
                generos = listOf()
            )


            if (libroId != 0) {
                Toast.makeText(this, "PUT: $libroId", Toast.LENGTH_SHORT).show()


                LibroRepository.updateLibro(
                    id = libro.id,
                    updatedLibro = libro,
                    success = { updatedLibro ->
                        Toast.makeText(
                            this,
                            "Libro ${updatedLibro?.nombre} updated successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()  // Navigate back to the main activity
                    },
                    failure = { error ->
                        error.printStackTrace()
                        // TODO: Handle the error case
                    }
                )
            } else {
                Toast.makeText(this, "POST", Toast.LENGTH_SHORT).show()

                // Create new Libro
                LibroRepository.postLibro(
                    newLibro = libro,
                    success = { newLibro ->
                        Toast.makeText(
                            this,
                            "Libro ${newLibro?.nombre} inserted successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()  // Navigate back to the main activity
                    },
                    failure = { error ->
                        error.printStackTrace()
                        // TODO: Handle the error case
                    }
                )
            }
        }
    }




    fun checkExistinBook() {
        val libroId = intent.getIntExtra("libroId", 0)

        Toast.makeText(this, "LibroId: $libroId", Toast.LENGTH_SHORT).show()
        if (libroId != 0) {
            LibroRepository.getLibroById(
                id = libroId,
                success = { libro ->
                    binding.txtNombreLibro.setText(libro?.nombre)
                    binding.txtAutorLibro.setText(libro?.autor)
                    binding.txtEditorialLibro.setText(libro?.editorial)
                    binding.txtUrlImagenLibro.setText(libro?.imagen)
                    binding.txtSinopsisLibro.setText(libro?.sinopsis)
                    binding.textInputEditText.setText(libro?.isbn)
                    binding.textInputEditText2.setText(libro?.calificacion.toString())
                },
                failure = { error ->
                    error.printStackTrace()
                }
            )
        }
    }

}