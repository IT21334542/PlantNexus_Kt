package com.example.plantnexus_kt.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.plantnexus_kt.Models.Plants
import com.example.plantnexus_kt.R

class ProdAdapter(val L : ArrayList<Plants>,val mcont : Context,val Items : ArrayList<Plants>) : RecyclerView.Adapter<ProdAdapter.VH>()
{
    class VH(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val img : ImageView = itemView.findViewById(R.id.productImg)
        val title : TextView =  itemView.findViewById(R.id.titleProducts)
        val Price : TextView = itemView.findViewById(R.id.txtPrice)
        val Mero : CardView = itemView.findViewById(R.id.Cars)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
       val V = LayoutInflater.from(parent.context).inflate(R.layout.one_card_products,parent,false)
        return VH(V)
    }

    override fun getItemCount(): Int {

        return L.size
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.title.text = L.get(position).plantname
        holder.Price.text = L.get(position).plantPrice.toString()
        Glide.with(mcont).asBitmap().load(L.get(position).plantImagePreview).into(holder.img)
        holder.Mero.setOnClickListener(View.OnClickListener {
            Items.add(L.get(position))
        })

    }
}