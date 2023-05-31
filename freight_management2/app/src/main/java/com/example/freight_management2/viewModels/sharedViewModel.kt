package com.example.freight_management2.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.freight_management2.entities.Driver
import com.example.freight_management2.entities.Truck

class sharedViewModel: ViewModel(){
    val selectedDriver = MutableLiveData<Driver?>()
    //val selectedDriver: LiveData<Driver?> = _selectedDriver

    val selectedTruck = MutableLiveData<Truck?>()
   // val selectedTruck: LiveData<Truck?> = _selectedTruck

    fun selectDriver(driver: Driver?) {
        selectedDriver.value = driver
    }

    fun selectTruck(truck: Truck?) {
        selectedTruck.value = truck
    }
}