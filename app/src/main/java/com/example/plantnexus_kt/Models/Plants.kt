package com.example.plantnexus_kt.Models

import java.io.Serializable

data class Plants(val plantname:String,
                  val plantImagePreview:String,
                  val plantPrice:Double,
                  val Description:String="Dectiption of the plant",
                  val Mode:String = "indoor",
                  val varient :String = "plant"
    ) : Serializable
