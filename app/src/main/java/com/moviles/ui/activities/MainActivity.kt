package com.moviles.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.moviles.books.R
import com.moviles.books.databinding.ActivityMainBinding
import com.moviles.books.repositories.LibroRepository
import com.moviles.ui.adapters.LibrosListAdapter
import com.moviles.ui.viewmodels.MainViewModels

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val model: MainViewModels by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        model.fetchListaLibros()
        setupEventListeners()
        setupRecyclerView()
        setupViewModelListeners()

    }


    override fun onResume() {
        super.onResume()
        model.fetchListaLibros()
    }

    private fun setupEventListeners() {
        binding.fabAddLibro.setOnClickListener {
            val intent = Intent(this, FormLibroActivity::class.java)
            startActivity(intent)
        }

        binding.imgGoToGenre.setOnClickListener(){
            val intent = Intent(this, GeneroActivity::class.java)
            startActivity(intent)
        }


    }

    private fun setupViewModelListeners() {
        model.libroList.observe(this, {
            (binding.lstLibros.adapter as LibrosListAdapter).updateData(it)
        })
    }


    private fun setupRecyclerView() {
        val adapter = LibrosListAdapter(arrayListOf(), model)
        binding.lstLibros.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}