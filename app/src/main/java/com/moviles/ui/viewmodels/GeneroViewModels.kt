package com.moviles.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moviles.books.models.Genero
import com.moviles.books.repositories.GeneroRepository

class GeneroViewModels: ViewModel(){
    private val _generoList: MutableLiveData<List<Genero>> by lazy {
        MutableLiveData<List<Genero>>()
    }

    val generoList: LiveData<List<Genero>> get() = _generoList

    fun fetchListaGeneros() {
        GeneroRepository.getGeneroList(
            success = {
                it?.let {
                    _generoList.value = it
                }
            },
            failure = {
                it.printStackTrace()
            }
        )
    }
}