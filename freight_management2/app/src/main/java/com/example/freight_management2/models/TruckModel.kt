package com.example.freight_management2.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.freight_management2.endPoints.endPoints
import com.example.freight_management2.entities.Truck
import kotlinx.coroutines.*

class TruckModel: ViewModel() {

    val Trucks = MutableLiveData<List<Truck>>()
    val loading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()




    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        CoroutineScope(Dispatchers.Main).launch   {
            loading.value = false
            errorMessage.value = "Une erreur s'est produite"
        }
    }


    fun loadTrucks(transportor :Int) {
        if(Trucks.value==null) {
            loading.value = true
            CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
                val response1 = endPoints.createEndpoint().getTrucks(transportor)
                withContext(Dispatchers.Main) {
                    loading.value = false
                    if (response1.isSuccessful && response1.body() != null) {
                        Trucks.value = response1.body()

                    } else {
                        errorMessage.value = "Une erreur s'est produite"
                    }
                }
            }
        }


    }






}