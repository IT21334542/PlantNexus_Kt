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

class ItemAdapter(val List : ArrayList<Plants>,val mcontxt :Context): RecyclerView.Adapter<ItemAdapter.VH>()
{
    class VH(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val preview : ImageView = itemView.findViewById(R.id.ImgPreviews)
        val bin : ImageView = itemView.findViewById(R.id.removeItem)
        val Title : TextView = itemView.findViewById(R.id.Pltitle)
        val qty : TextView = itemView.findViewById(R.id.QTY)
        val add :CardView = itemView.findViewById(R.id.addQTY)
        val remove :CardView = itemView.findViewById(R.id.reduceQTY)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.one_card_item,parent,false)
        return VH(v)
    }

    override fun getItemCount(): Int {

        return List.size;
        notifyDataSetChanged()

    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.Title.text = List.get(position).plantname
        holder.qty.text = List.get(position).qty.toString()
        Glide.with(mcontxt).asBitmap().load(List.get(position).plantImagePreview).into(holder.preview)

        holder.add.setOnClickListener(View.OnClickListener {
            List.get(position).qty=  List.get(position).qty + 1
            holder.qty.text = List.get(position).qty.toString()
        })

        holder.remove.setOnClickListener(View.OnClickListener {
            if( List.get(position).qty!=0)
                List.get(position).qty=  List.get(position).qty - 1;
            holder.qty.text = List.get(position).qty.toString()
        })
    }
}