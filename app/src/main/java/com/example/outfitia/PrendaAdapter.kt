package com.example.outfitia

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.outfitia.entity.Prenda
import java.io.File

class PrendaAdapter(private val prendas: List<Prenda>) :
    RecyclerView.Adapter<PrendaAdapter.PrendaViewHolder>() {

    class PrendaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPrenda: ImageView = itemView.findViewById(R.id.imgPrenda)
        val txtTipo: TextView = itemView.findViewById(R.id.txtTipo)              // 👈 nuevo campo
        val txtRecomendacion: TextView = itemView.findViewById(R.id.txtRecomendacion)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrendaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_prenda, parent, false)
        return PrendaViewHolder(view)
    }

    override fun onBindViewHolder(holder: PrendaViewHolder, position: Int) {
        val prenda = prendas[position]

        // Mostrar la imagen con Glide
        Glide.with(holder.imgPrenda.context)
            .load(File(prenda.imagePath))
            .into(holder.imgPrenda)

        // Mostrar tipo y recomendación
        holder.txtTipo.text = prenda.tipo
        holder.txtRecomendacion.text = prenda.recomendacion
    }

    override fun getItemCount(): Int = prendas.size
}
