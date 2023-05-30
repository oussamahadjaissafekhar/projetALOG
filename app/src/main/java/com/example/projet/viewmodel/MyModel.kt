package com.example.projet.viewmodel

import androidx.lifecycle.ViewModel
import com.example.projet.entity.EmptyReturn

class MyModel:ViewModel() {
    var emptyReturnId = -1
    val data=loadData()
    var ClientId = -1

    fun loadData():MutableList<EmptyReturn> {
        val data = mutableListOf<EmptyReturn>()
        data.add(EmptyReturn(1,"Alger","Bourdj Bou Arreridj",7000,"en attente"))
        data.add(EmptyReturn(2,"Alger","Oran",10000,"en attente"))
        data.add(EmptyReturn(3,"Alger","Blida",1000,"en attente"))
        data.add(EmptyReturn(4,"Tamerasset","Bourdj Bou Arreridj",150000,"en attente"))
        data.add(EmptyReturn(4,"Tamenrasset","Alger",20000,"en attente"))


        return data
    }
}