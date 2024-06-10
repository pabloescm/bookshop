package com.moviles.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.moviles.books.R
import com.moviles.books.databinding.ActivityGeneroBinding
import com.moviles.books.repositories.GeneroRepository
import com.moviles.ui.adapters.GeneroAdapter
import com.moviles.ui.adapters.LibrosListAdapter
import com.moviles.ui.viewmodels.GeneroViewModels

class GeneroActivity : AppCompatActivity() {
    lateinit var binding: ActivityGeneroBinding
    private val model : GeneroViewModels by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGeneroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        setupRecyclerView()
        setupListeners()
        setupViewModelListeners()


    }

    private fun setupViewModelListeners() {
        model.generoList.observe(this, {
            (binding.lstGeneros.adapter as GeneroAdapter).updateData(it)
        })
    }

    private fun setupListeners() {
        binding.fabAddGenero.setOnClickListener(){
            val intent = Intent(this, FormGenreActivity::class.java)
            startActivity(intent)
        }
    }



    private fun setupRecyclerView() {
        val adapter = GeneroAdapter(arrayListOf(), model)
        binding.lstGeneros.apply {
            this.adapter = adapter
            this.layoutManager = LinearLayoutManager(this@GeneroActivity)
        }
    }

    override fun onResume() {
        super.onResume()
        model.fetchListaGeneros()
    }
}