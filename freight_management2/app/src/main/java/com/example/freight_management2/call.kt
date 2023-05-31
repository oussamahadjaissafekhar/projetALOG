package com.example.freight_management2

import android.content.Context
import android.content.Intent
import android.net.Uri

fun call(ctx: Context, number:String){
    val data = Uri.parse("tel:$number")
    val intent = Intent(Intent.ACTION_DIAL,data)
    ctx.startActivity(intent)
}

fun open_map(ctx: Context, x_cor: Double, y_cor: Double){
    val latitude = x_cor.toString()
    val longitude = y_cor.toString()
    val data = Uri.parse("geo:$latitude,$longitude")
    val intent = Intent(Intent.ACTION_VIEW,data)
    ctx.startActivity(intent)
}