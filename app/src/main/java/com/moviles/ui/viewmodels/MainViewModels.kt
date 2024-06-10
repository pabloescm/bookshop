package com.moviles.ui.viewmodels

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moviles.books.models.Libro
import com.moviles.books.repositories.LibroRepository
import com.moviles.ui.adapters.LibrosListAdapter

class MainViewModels : ViewModel() {
    private val _libroList: MutableLiveData<List<Libro>> by lazy {
        MutableLiveData<List<Libro>>()
    }

    val libroList: LiveData<List<Libro>> get() = _libroList


     fun fetchListaLibros() {
        LibroRepository.getLibroList(
            success = {
                it?.let {
                    _libroList.value = it
                }
            },
            failure = {
                it.printStackTrace()
            }
        )
    }
}