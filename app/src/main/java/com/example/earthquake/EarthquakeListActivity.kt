package com.example.earthquake

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.earthquake.databinding.ActivityEarthquakeListBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EarthquakeListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEarthquakeListBinding
    private lateinit var earthquakeList: Earthquake
    companion object{
        val TAG = "EarthquakeListActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityEarthquakeListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadJson()

        val retrofit = RetrofitHelper.getInstance()
        val earthquakeService = retrofit.create(EarthquakeService::class.java)
        val earthquakeCall = earthquakeService.getEarthquakeDataForPastDay()
        earthquakeCall.enqueue(object: Callback<Earthquake> {
            override fun onResponse(call: Call<Earthquake>, response: Response<Earthquake>) {
                Log.d(TAG, "onResponse: ${response.body()}")
                if(response.body()!=null){
                    setRecyclerView(response.body()!!)
                }
                // this is where you will get your data
                // this is where you will set up your adapter for recycler view
                // response.body() contains the object in the <> after response
                // do a null check on response.body()
            }

            override fun onFailure(call: Call<Earthquake>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }
        })
        //Log.d(TAG, "onCreate: ${earthquakeList}")
    }

    fun setRecyclerView(earthquakeList:Earthquake){
        var adapter = EarthquakeAdapter(earthquakeList)
        binding.recyclerViewEarthquakeListPotato.adapter = adapter
        binding.recyclerViewEarthquakeListPotato.layoutManager = LinearLayoutManager(this)
    }


    private fun loadJson() {
        // load JSON file as a string
        var inputStream = resources.openRawResource(R.raw.earthquake)
        var jsonString = inputStream.bufferedReader().use {
            it.readText()
        }
        val gson = Gson()
        //finds the type of the objects in the JSON file?
        val sType = object : TypeToken<Earthquake>() {}.type
        //creates a list of objects by pirating them from JSON
        earthquakeList = gson.fromJson<Earthquake>(jsonString, sType)
    }
}