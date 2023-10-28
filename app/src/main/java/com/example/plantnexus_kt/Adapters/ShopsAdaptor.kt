package com.example.plantnexus_kt.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.plantnexus_kt.Models.Plants
import com.example.plantnexus_kt.Models.Shops
import com.example.plantnexus_kt.OnShop
import com.example.plantnexus_kt.R

class ShopsAdaptor(val mcontxt : Context,val ShoppersList:ArrayList<Shops>): RecyclerView.Adapter<ShopsAdaptor.V>() {

    class V(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val Img :ImageView = itemView.findViewById(R.id.ShopperPreview)
        val ShopName : TextView = itemView.findViewById(R.id.txtShopname)
        val Shoplocation : TextView  = itemView.findViewById(R.id.txtShopLoca)
        val Cards :CardView = itemView.findViewById(R.id.Cards)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): V {
        val vi = LayoutInflater.from(parent.context).inflate(R.layout.rec_one_shoppers,parent,false)
        return V(vi)
    }

    override fun getItemCount(): Int {

        return ShoppersList.size
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: V, position: Int) {
        holder.ShopName.text = ShoppersList.get(position).Shopname
        holder.Shoplocation.text = ShoppersList.get(position).Shoplocation
        holder.Cards.setOnClickListener(View.OnClickListener {
            val to = Intent(mcontxt,OnShop::class.java)
            to.putExtra("SHOP",ShoppersList.get(position))
          mcontxt.startActivity(to)
        })
        Glide.with(mcontxt).asBitmap().load(ShoppersList.get(position).ShopPreviewUrl).into(holder.Img)

    }
}