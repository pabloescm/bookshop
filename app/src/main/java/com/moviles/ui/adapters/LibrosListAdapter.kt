package com.moviles.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.moviles.books.databinding.LibroItemLayoutBinding
import com.moviles.books.models.Libro
import com.moviles.books.repositories.LibroRepository
import com.moviles.ui.activities.AddGeneroLibroActivity
import com.moviles.ui.activities.FormLibroActivity
import com.moviles.ui.activities.LibroDetailActivity
import com.moviles.ui.viewmodels.MainViewModels

class LibrosListAdapter(var libroList: ArrayList<Libro>, private val viewModel: MainViewModels) :
    RecyclerView.Adapter<LibrosListAdapter.LibrosViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibrosViewHolder {
        val binding =
            LibroItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LibrosViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return libroList.size
    }

    override fun onBindViewHolder(holder: LibrosViewHolder, position: Int) {
        val libro = libroList[position]
        holder.bind(libro,viewModel)


    }

    fun updateData(newLibroList: List<Libro>?) {
        this.libroList.clear()

        newLibroList?.let { this.libroList.addAll(it) }
        notifyDataSetChanged()
    }

    class LibrosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(libro: Libro, viewModel: MainViewModels) {
            val binding = LibroItemLayoutBinding.bind(itemView)
            binding.apply {
                lblLibroName.setText(libro.nombre)
                Glide.with(itemView)
                    .load(libro.imagen)
                    .into(imgItemLibro)



                btnEditLibro.setOnClickListener {
                    val intent = Intent(itemView.context, FormLibroActivity::class.java)
                    intent.putExtra("libroId", libro.id)
                    itemView.context.startActivity(intent)
                }

                btnDeleteLibro.setOnClickListener {
                    LibroRepository.deleteLibro(
                        libro.id,
                        success = {
                            viewModel.fetchListaLibros()

                        },
                        failure = {
                            it.printStackTrace()
                        }
                    )
                }
                lblLibroName.setOnClickListener {
                    val intent = Intent(itemView.context, LibroDetailActivity::class.java)
                    intent.putExtra("libroId", libro.id)
                    itemView.context.startActivity(intent)
                }

                imgItemLibro.setOnClickListener{
                    val intent = Intent(itemView.context , AddGeneroLibroActivity::class.java)
                    intent.putExtra("libroId", libro.id)
                    intent.putExtra("libroNombre", libro.nombre)
                    itemView.context.startActivity(intent)

                }

            }
        }
    }

}