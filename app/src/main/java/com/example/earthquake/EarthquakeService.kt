package com.example.earthquake

import retrofit2.Call
import retrofit2.http.GET

interface EarthquakeService {
    // where you list out the different endpoints in the API you want to call
    // function returns Call<type> where type is the data returned in the json
    // in the @GET("blah"), "blah" is the path to the endpoint
    @GET("summary/all_day.geojson")
    fun getEarthquakeDataForPastDay(): Call<Earthquake>
}