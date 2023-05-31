package com.example.freight_management2.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.freight_management2.endPoints.endPoints
import com.example.freight_management2.entities.Driver
import kotlinx.coroutines.*

class driverModel: ViewModel() {

    val Drivers = MutableLiveData<List<Driver>>()
    val loading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()




    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        CoroutineScope(Dispatchers.Main).launch   {
            loading.value = false
            errorMessage.value = "Une erreur s'est produite"
        }
    }


    fun loadDrivers(transportor :Int) {
        if(Drivers.value==null) {
            loading.value = true
            CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
                val response1 = endPoints.createEndpoint().getDrivers(transportor)
                withContext(Dispatchers.Main) {
                    loading.value = false
                    if (response1.isSuccessful && response1.body() != null) {
                        Drivers.value = response1.body()

                    } else {
                        errorMessage.value = "Une erreur s'est produite"
                    }
                }
            }
        }


    }






}