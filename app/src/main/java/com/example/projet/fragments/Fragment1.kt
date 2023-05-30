package com.example.projet.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieexample.databinding.Fragment1Binding
import com.example.projet.EmptyReturnAdapter
import com.example.projet.viewmodel.MyModel

class Fragment1 : Fragment() ,EmptyReturnAdapter.ItemRemovedListener {

    // Other fragment code...
    lateinit var binding:Fragment1Binding

    override fun onItemRemoved() {
        // Reload the fragment or update the UI
        // For example, call a function to refresh the data or update the views
        loadData()
    }

    private fun loadData() {
        // Load data or perform any other necessary operations
        // Then update the RecyclerView
        val adapter = binding.recyclerView.adapter as? EmptyReturnAdapter
        adapter?.notifyDataSetChanged()
    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= Fragment1Binding.inflate(layoutInflater,container,false)
        val view=binding.root




        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val vm= ViewModelProvider(requireActivity()).get(MyModel::class.java)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerView.adapter = EmptyReturnAdapter(requireActivity(),vm.data,vm)
       val adapter =EmptyReturnAdapter(requireActivity(),vm.data,vm)
        adapter.setItemRemovedListener(this)

    }
}