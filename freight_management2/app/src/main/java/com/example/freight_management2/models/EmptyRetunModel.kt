package com.example.freight_management2.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.freight_management2.endPoints.endPoints
import com.example.freight_management2.entities.EmptyReturn
import kotlinx.coroutines.*
import java.util.*

class emptyReturnModel: ViewModel() {

    val emptyReturns = MutableLiveData<List<EmptyReturn>>()
    val loading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()




    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        CoroutineScope(Dispatchers.Main).launch   {
            loading.value = false
            errorMessage.value = "Une erreur s'est produite"
        }
    }


    fun loadEmptyReturns() {
        if(emptyReturns.value==null) {
            loading.value = true
            CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
                val response = endPoints.createEndpoint().getEmptyReturns()
                withContext(Dispatchers.Main) {
                    loading.value = false
                    if (response.isSuccessful && response.body() != null) {
                        emptyReturns.value = response.body()

                    } else {
                        errorMessage.value = "Une erreur s'est produite"
                    }
                }
            }
        }


    }

    fun addEmptyReturn(arrivalPlace:String ,departurePlace:String ,Price:String  ,departureData: Date) {
        //  if(emptyReturns.value==null) {
        loading.value = true
        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = endPoints.createEndpoint().addEmptyReturn( arrivalPlace ,departurePlace ,Price  ,departureData)
            withContext(Dispatchers.Main) {
                loading.value = false

            }
        }
        // }


    }






}