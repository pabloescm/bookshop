package com.moviles.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.moviles.books.models.Libro
import com.moviles.books.databinding.LibrobygeneroItemBinding

class LibroByGenreAdapter( var libroList: ArrayList<Libro>) : RecyclerView.Adapter<LibroByGenreAdapter.LibroByGenreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibroByGenreViewHolder {
        val binding = LibrobygeneroItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LibroByGenreViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: LibroByGenreViewHolder, position: Int) {
        val libro = libroList[position]
        holder.bind(libro)
    }

    override fun getItemCount(): Int {
        return libroList.size
    }

    fun updateData(newLibroList: List<Libro>?) {
        this.libroList.clear()
        newLibroList?.let { this.libroList.addAll(it) }
        notifyDataSetChanged()
    }

    class LibroByGenreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(libro: Libro) {
            val binding = LibrobygeneroItemBinding.bind(itemView)
            binding.txtLibroByGenreItem.text = libro.nombre
            Glide.with(itemView)
                .load(libro.imagen)
                .into(binding.imgByGenreItem)
        }
    }
}