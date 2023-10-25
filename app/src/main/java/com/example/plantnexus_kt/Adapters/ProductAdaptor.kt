package com.example.plantnexus_kt.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.plantnexus_kt.Models.Plants
import com.example.plantnexus_kt.R

class ProductAdaptor(val ProductList : ArrayList<Plants>,val mcontxt : Context) : RecyclerView.Adapter<ProductAdaptor.ViewH>() {


    class ViewH(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val ImgHolder : ImageView = itemView.findViewById(R.id.PlantPreview);
        val plantname : TextView =itemView.findViewById(R.id.plant_name);

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.rec_one_products,parent,false)
        return  ViewH(v);
    }

    override fun getItemCount(): Int {
        return ProductList.size;
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewH, position: Int) {
        Glide.with(mcontxt).asBitmap().load(ProductList.get(position).plantImagePreview).into(holder.ImgHolder);
        holder.plantname.text = ProductList.get(position).plantname
    }
}