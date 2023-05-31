package com.example.freight_management2.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.freight_management2.R
import com.example.freight_management2.entities.Driver
import com.example.freight_management2.fragments.addEmptyReturnFragment
import com.example.freight_management2.fragments.popUpFragment
import com.example.freight_management2.viewModels.sharedViewModel

@Suppress("DEPRECATION")
class DriverAdapter(/*val data:List<emptyReturn>,*/val ctx: Context, val vm : sharedViewModel,val fragment : popUpFragment,val addEmptyReturnFragment: addEmptyReturnFragment):
    RecyclerView.Adapter<DriverAdapter.DriverViewHolder>() {
    val bundle = Bundle()
    var data = mutableListOf<Driver>()

    fun setDriver(drivers: List<Driver>) {
        this.data = drivers.toMutableList()
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DriverViewHolder {
/*        return EmptyReturnAdapter.EmptyReturnViewHolder(

            EmptyReturnLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )*/
        //   viewmodel = vm
        return DriverViewHolder(
            LayoutInflater.from(ctx).inflate(R.layout.driver_layout, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: DriverViewHolder, position: Int) {
        bind(holder, data[position],vm)
    }

    private fun bind(holder: DriverViewHolder, driver: Driver, vm :sharedViewModel) {
        holder.apply {
            firstName.text = driver.first_name
            lastName.text = driver.last_name

            viewDriver.setOnClickListener{
                addEmptyReturnFragment.driverS = driver
                addEmptyReturnFragment.driverEditText.setText(driver.first_name)

            fragment.dismisspopup()
            }
        }
    }

    class DriverViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val firstName = view.findViewById(R.id.firstNameTV) as TextView
        val lastName = view.findViewById(R.id.lastNameTV) as TextView
        val viewDriver = view.findViewById(R.id.viewDriver) as View
        //val pic = view.findViewById(R.id.pic) as ImageView
    }


}