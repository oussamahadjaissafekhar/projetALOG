package com.example.freight_management2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.freight_management2.R
import com.example.freight_management2.adapters.DriverAdapter
import com.example.freight_management2.adapters.TruckAdapter
import com.example.freight_management2.databinding.FragmentPopUp2Binding
import com.example.freight_management2.databinding.FragmentPopUpBinding
import com.example.freight_management2.entities.Truck
import com.example.freight_management2.models.TruckModel
import com.example.freight_management2.viewModels.sharedViewModel

class popUp2Fragment : DialogFragment() {
    // TODO: Rename and change types of parameters

    lateinit var truck_model : TruckModel
    lateinit var binding: FragmentPopUp2Binding
    lateinit var adapter: TruckAdapter
    lateinit var  vm : sharedViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
                bundle ->val transportor = bundle.getString("transportor")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentPopUp2Binding.inflate(layoutInflater, container, false)
        val view =binding.root
        return view }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragment = arguments?.getSerializable("frag") as addEmptyReturnFragment

        truck_model = ViewModelProvider(requireActivity()).get(TruckModel::class.java)
        binding.recyclerVIewTruck.layoutManager = LinearLayoutManager(requireActivity())
        vm = ViewModelProvider(this).get(sharedViewModel::class.java)
        val adapter = TruckAdapter(requireActivity(),vm,this,fragment)
        adapter.setTrucks(loadTrucks())
        binding.recyclerVIewTruck.adapter = adapter
        // binding.recyclerViewER.adapter = adapter
        /*    empty_return_model.loadDrivers(transportor)
    */

        /* binding.button.setOnClickListener {
             movieModel.loadMovies()
         }*//*
        empty_return_model.errorMessage.observe(requireActivity(), { errorMessaage ->
            Toast.makeText(requireContext(),errorMessaage,Toast.LENGTH_SHORT).show()
        })
        // List movies observer
        empty_return_model.emptyReturns.observe(requireActivity(),  {  emptyReturns ->
            adapter.setEmptyReturns (emptyReturns)
        })*/

    }

    fun dismisspopup(){
        this.dismiss()
    }
}

/**/


fun loadTrucks():List<Truck> {
    val data = mutableListOf<Truck>()
    data.add(Truck(1,"camion"))
    data.add(Truck(1,"camion"))
    data.add(Truck(1,"camion"))
    data.add(Truck(1,"camion"))
    data.add(Truck(1,"camion"))

    return data
}
