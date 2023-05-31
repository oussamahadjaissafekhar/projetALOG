package com.example.freight_management2.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.freight_management2.R
import com.example.freight_management2.entities.Truck
import com.example.freight_management2.fragments.addEmptyReturnFragment
import com.example.freight_management2.fragments.popUp2Fragment
import com.example.freight_management2.fragments.popUpFragment
import com.example.freight_management2.viewModels.sharedViewModel

@Suppress("DEPRECATION")
class TruckAdapter(/*val data:List<emptyReturn>,*/val ctx: Context, val vm : sharedViewModel,val fragment : popUp2Fragment, val addEmptyReturnFragment: addEmptyReturnFragment):
    RecyclerView.Adapter<TruckAdapter.TruckViewHolder>() {
    val bundle = Bundle()
    var data = mutableListOf<Truck>()
    // private lateinit var viewmodel : sharedviewModel

    fun setTrucks(Trucks: List<Truck>) {
        this.data = Trucks.toMutableList()
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TruckViewHolder {
/*        return EmptyReturnAdapter.EmptyReturnViewHolder(

            EmptyReturnLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )*/
        //   viewmodel = vm
        return TruckViewHolder(
            LayoutInflater.from(ctx).inflate(R.layout.truck_layout, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: TruckViewHolder, position: Int) {
        bind(holder, data[position],vm)
    }

    private fun bind(holder: TruckViewHolder, Truck: Truck, vm :sharedViewModel) {
        holder.apply {
            Matricule.text = Truck.TruckID.toString()
            Type.text = Truck.TruckName
            // Glide.with(ctx).load(/*url + */emptyReturn.image).into(pic)

            viewTruck.setOnClickListener{
                addEmptyReturnFragment.TruckS = Truck
                addEmptyReturnFragment.TruckEditText.setText(Truck.TruckName)
                fragment.dismisspopup()
            }
        }
    }

    class TruckViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val Matricule = view.findViewById(R.id.MatriculeTV) as TextView
        val Type = view.findViewById(R.id.TypeTV) as TextView
        val viewTruck = view.findViewById(R.id.viewTruck) as View
        //val pic = view.findViewById(R.id.pic) as ImageView
    }


}