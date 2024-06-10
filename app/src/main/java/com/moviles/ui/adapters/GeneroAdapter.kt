package com.moviles.ui.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moviles.books.databinding.GeneroItemLayoutBinding
import com.moviles.books.models.Genero
import com.moviles.books.repositories.GeneroRepository
import com.moviles.ui.activities.FormGenreActivity
import com.moviles.ui.activities.FormLibroActivity
import com.moviles.ui.activities.ShowLibroByGenre
import com.moviles.ui.viewmodels.GeneroViewModels

class GeneroAdapter(var generoList:ArrayList<Genero>,private val ViewModel:GeneroViewModels) : RecyclerView.Adapter<GeneroAdapter.GeneroViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GeneroViewHolder {
        val binding = GeneroItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GeneroViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: GeneroAdapter.GeneroViewHolder, position: Int) {
        val genero = generoList[position]
        holder.bind(genero,ViewModel)

    }

    override fun getItemCount(): Int {
        return generoList.size
    }

    fun updateData(newGeneroList: List<Genero>?) {
        this.generoList.clear()
        newGeneroList?.let { this.generoList.addAll(it) }
        notifyDataSetChanged()

    }

    class GeneroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(genero: Genero,ViewModel: GeneroViewModels) {
            val binding = GeneroItemLayoutBinding.bind(itemView)
            binding.txtLibroGeneroNombre.text = genero.nombre

            binding.txtLibroGeneroNombre.setOnClickListener {
                val intent = Intent(it.context, ShowLibroByGenre::class.java)
                Log.d("GeneroAdapter", "Genero ID: ${genero.id}")
                intent.putExtra("generoId", genero.id)
                itemView.context.startActivity(intent)
            }

            binding.fabEditGenre.setOnClickListener {
                val intent = Intent(it.context, FormGenreActivity::class.java)
                intent.putExtra("generoId", genero.id)
                itemView.context.startActivity(intent)
            }

            binding.fabDeleteGenre.setOnClickListener {
                GeneroRepository.deleteGenero(
                    genero.id,
                    success = {
                    ViewModel.fetchListaGeneros()

                }, {
                    Log.d("GeneroAdapter", "Error al eliminar genero")
                })
            }



        }


    }


}

