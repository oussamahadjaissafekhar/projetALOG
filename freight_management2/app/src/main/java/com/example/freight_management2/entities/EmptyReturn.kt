package com.example.freight_management2.entities

data class EmptyReturn(
    val returnID : Int,
    //val scheduledId:Int,
    //val equipmentId: Int,
    //val publisherId: Int,
    //val departureDate:  Date,
    val departureReturn: String,
    val arrivalReturn: String,
    val priceReturn: Double,
    val statusReturn: Int,
    val image:String?
    //val validated: Boolean,
    // val createdAtSchedule: Time,
    //  val updatedAtSchedule: Time,
    //  val deletedAtSchedule: Time,

)
