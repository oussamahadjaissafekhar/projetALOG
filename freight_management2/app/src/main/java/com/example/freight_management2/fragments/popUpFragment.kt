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
import com.example.freight_management2.databinding.FragmentPopUpBinding
import com.example.freight_management2.entities.Driver
import com.example.freight_management2.models.driverModel
import com.example.freight_management2.viewModels.sharedViewModel

class popUpFragment : DialogFragment() {
    // TODO: Rename and change types of parameters

    lateinit var driver_model : driverModel
    lateinit var binding: FragmentPopUpBinding
    lateinit var adapter: DriverAdapter
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
        // Inflate the layout for this fragment
     binding=FragmentPopUpBinding.inflate(layoutInflater, container, false)
        val view =binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragment = arguments?.getSerializable("frag") as addEmptyReturnFragment
        driver_model = ViewModelProvider(requireActivity()).get(driverModel::class.java)
        binding.recyclerVIewDriver.layoutManager = LinearLayoutManager(requireActivity())
        vm = ViewModelProvider(this).get(sharedViewModel::class.java)
        val adapter = DriverAdapter(requireActivity(),vm,this,fragment)
        adapter.setDriver (loadDrivers())
        binding.recyclerVIewDriver.adapter = adapter
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


fun loadDrivers():List<Driver> {
    val data = mutableListOf<Driver>()
    data.add(Driver(1,"AHMED","ISA"))
    data.add(Driver(1,"zzzzzzzzz","ISA"))
    data.add(Driver(1,"AHMED","ISA"))
    data.add(Driver(1,"AHMED","ISA"))
    data.add(Driver(1,"AHMED","ISA"))

    return data
}

