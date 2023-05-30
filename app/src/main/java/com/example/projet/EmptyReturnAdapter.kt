package com.example.projet

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieexample.databinding.EmptyReturnDetailsBinding
import com.example.projet.entity.EmptyReturn
import com.example.projet.viewmodel.MyModel

class EmptyReturnAdapter(val ctx: Context, val data:MutableList<EmptyReturn>, val vm: MyModel): RecyclerView.Adapter<EmptyReturnAdapter.MyViewHolder>() {
    interface ItemRemovedListener {
        fun onItemRemoved()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(EmptyReturnDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = data.size


    private var itemRemovedListener: ItemRemovedListener? = null

    fun setItemRemovedListener(listener: ItemRemovedListener) {
        itemRemovedListener = listener
    }
    private fun removeItem(position: Int) {
        if (position >= 0 && position < data.size) {
            data.removeAt(position)
            itemRemovedListener?.onItemRemoved()
        }
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = vm.data[position]

        holder.binding.apply {
            departurePlaceTextView.text = data[position].depart
            arrivalPlaceTextView.text = data[position].destination
            textView9.text=data[position].prix.toString()+"DA"
            emptyNumberTextView.text="Empty Return N _"+data[position].emptyReturnid.toString()
            imageButton.setOnClickListener {

                removeItem(position)
            }

        }
    }


        class MyViewHolder(val binding: EmptyReturnDetailsBinding) : RecyclerView.ViewHolder(binding.root)

    }



/*

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.RestaurentApp.entity.Restaurent
import com.example.RestaurentApp.openCall
import com.example.RestaurentApp.openFacebook
import com.example.RestaurentApp.openInsta
import com.example.RestaurentApp.openMaps
import com.example.RestaurentApp.viewmodel.MyModel
import com.example.movieexample.R
import com.example.movieexample.databinding.RestaurentLayoutBinding

class MyAdapter(val ctx:Context, val data:List<Restaurent>, val vm: MyModel):RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(RestaurentLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = vm.data[position]

        // Find the ImageView in your ViewHolder
        val imageView = holder.itemView.findViewById<ImageView>(R.id.Logo)

        // Use Glide to load the image into the ImageView
        Glide.with(holder.itemView)
            .load(currentItem.restaurentLogo)
            .into(imageView)
        holder.binding.apply {
            textName.text = data[position].restaurentName
            //Logo.setImageResource(data[position].restaurentLogo)
            Type.text = data[position].restaurentType
            ratingBar.rating = data[position].rating
        }

        holder.binding.facebook.setOnClickListener(){
            openFacebook(this.ctx,data[position].restaurentFacebook)
        }

        holder.binding.instagramme.setOnClickListener(){
            openInsta(this.ctx,data[position].restaurentInstagrame)
        }

        holder.binding.Location.setOnClickListener(){
            openMaps(this.ctx,data[position].restaurentLongitude,data[position].restaurentLatitude)
        }

        holder.binding.Number.setOnClickListener(){
            openCall(this.ctx,data[position].restaurentPhone);
        }

        holder.itemView.setOnClickListener(){
            view: View ->view.findNavController().navigate(R.id.action_blankFragment4_to_restaurentFragment)
            vm.restaurantId = data[position].restaurentId
            vm.position = position
        }

    }


    class MyViewHolder(val binding: RestaurentLayoutBinding) : RecyclerView.ViewHolder(binding.root)

}

*/

