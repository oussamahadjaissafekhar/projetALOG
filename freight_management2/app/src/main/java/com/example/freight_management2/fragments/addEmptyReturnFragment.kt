package com.example.freight_management2.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.freight_management2.R
import com.example.freight_management2.databinding.FragmentAddEmptyReturnBinding
import com.example.freight_management2.entities.Driver
import com.example.freight_management2.entities.Truck
import com.example.freight_management2.models.emptyReturnModel
import com.example.freight_management2.viewModels.sharedViewModel
import java.util.*

class addEmptyReturnFragment : Fragment(),java.io.Serializable {
    lateinit var empty_return_model: emptyReturnModel
    lateinit var binding: FragmentAddEmptyReturnBinding
    lateinit var driverEditText: EditText
    lateinit var TruckEditText: EditText
    lateinit var driverS: Driver
    lateinit var TruckS: Truck

    val bundle = Bundle()

    /* override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddEmptyReturnBinding.inflate(inflater, container, false)
        val view = binding.root
        // binding!!.recyclerViewER.layoutManager = LinearLayoutManager(requireActivity())
        // binding!!.recyclerViewER.adapter = EmptyReturnAdapter(loadData2(),requireActivity())
        return view
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bundle.putString("transportor", "1")

        val fragment = popUpFragment()
        fragment.arguments = bundle
        val fragment2 = popUp2Fragment()
        fragment2.arguments = bundle

        val vm=ViewModelProvider(requireActivity()).get(sharedViewModel::class.java)

        driverEditText=view.findViewById(R.id.driverTV) as EditText
        TruckEditText=view.findViewById(R.id.truckTV) as EditText

        binding?.Driver?.setOnClickListener {
            val showUp = popUpFragment()
            val args = Bundle()
            args.putSerializable("frag",this)
            showUp.setArguments(args)

            showUp.show((activity as AppCompatActivity).supportFragmentManager, "showUp")

        }
        binding?.truck?.setOnClickListener {
            val showUp = popUp2Fragment()
            val args = Bundle()
            args.putSerializable("frag",this)
            showUp.setArguments(args)

            showUp.show((activity as AppCompatActivity).supportFragmentManager, "showUp")

        }



        empty_return_model = ViewModelProvider(requireActivity()).get(emptyReturnModel::class.java)
        binding?.addER?.setOnClickListener {
            // it.findNavController().navigate(R.id.action_restrentFragment_to_panierFragment)
            val arrivalPlace = view.findViewById<EditText>(R.id.departureCity).toString()
            val departurePlace = view.findViewById<EditText>(R.id.arrivalCity).toString()
            val Price = view.findViewById<EditText>(R.id.Price).toString()
            val datePicker = view.findViewById<DatePicker>(R.id.date_Picker)
            val year = datePicker.year
            val month = datePicker.month
            val dayOfMonth = datePicker.dayOfMonth
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val departureData = calendar.time
            empty_return_model.addEmptyReturn(arrivalPlace, departurePlace, Price, departureData)
        }

        //binding.recyclerViewER.layoutManager = LinearLayoutManager(requireActivity())
        //val adapter = EmptyReturnAdapter(requireActivity())

        // binding.recyclerViewER.adapter = adapter
        /*    empty_return_model.loadEmptyReturns()
    */
        // adapter.setEmptyReturns (loadData2())

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
}

