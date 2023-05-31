package com.example.freight_management2.endPoints

import com.example.freight_management2.entities.Driver
import com.example.freight_management2.entities.EmptyReturn
import com.example.freight_management2.entities.Truck
import com.example.freight_management2.url
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.*

interface endPoints {

    @GET("EmptyReturn/getall")
    suspend fun getEmptyReturns(): Response<List<EmptyReturn>>
    @POST("EmptyReturn/add")
    suspend fun addEmptyReturn(@Query("departureReturn")daparturePlace:String, @Query("arrivalReturn")arrivalPlace:String, @Query("priceReturn")Price:String, @Query("departureDate")departureDate: Date)
    @GET("Driver/getall")
    suspend fun getDrivers(@Query("id")transportor:Int) : Response<List<Driver>>
    @GET("Truck/getall")
    suspend fun getTrucks(@Query("id")transportor:Int) : Response<List<Truck>>


    companion object {
        @Volatile
        var endpoint: endPoints? = null
        fun createEndpoint(): endPoints {
            if(endpoint ==null) {
                synchronized(this) {
                    endpoint = Retrofit.Builder().baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create()).build()
                        .create(endPoints::class.java)
                }
            }
            return endpoint!!

        }


    }

}