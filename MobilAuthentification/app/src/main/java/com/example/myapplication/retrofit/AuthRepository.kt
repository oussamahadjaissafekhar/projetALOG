package com.example.myapplication.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AuthRepository {
    companion object {
        var apiService: ApiService? = null
        fun createApiService(): ApiService {
            if (apiService == null) {
                synchronized(this) {
                    apiService = Retrofit.Builder().baseUrl(com.example.myapplication.url)
                        .addConverterFactory(GsonConverterFactory.create()).build()
                        .create(ApiService::class.java)
                }
            }
            return apiService!!

        }
    }}
