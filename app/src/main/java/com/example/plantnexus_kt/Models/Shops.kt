package com.example.plantnexus_kt.Models

import java.io.Serializable

data class Shops(val Shopname :String,val ShopPreviewUrl :String,val Shoplocation :String,val PlantsSells : ArrayList<Plants>):Serializable
