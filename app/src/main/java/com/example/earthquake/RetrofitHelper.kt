package com.example.earthquake
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object RetrofitHelper {
    val baseUrl = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/"

    fun getInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}