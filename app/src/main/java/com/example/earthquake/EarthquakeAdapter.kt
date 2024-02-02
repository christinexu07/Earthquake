package com.example.earthquake

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.round
import java.time.Instant
import java.time.LocalDateTime
import java.util.Date

class EarthquakeAdapter(var earthquakeList:Earthquake) :


    RecyclerView.Adapter<EarthquakeAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val tvmagnitude: TextView
        val tvlocation: TextView
        val tvtime: TextView
        val layout: ConstraintLayout

        init {
            tvmagnitude = view.findViewById(R.id.textView_earthquakeItem_magnitude)
            tvlocation = view.findViewById(R.id.textView_earthquakeItem_location)
            tvtime = view.findViewById(R.id.textView_earthquakeItem_time)
            layout = view.findViewById(R.id.layout_itemEarthquake)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_earthquake, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val earthquake = earthquakeList.features[position]
        val context = viewHolder.layout.context
        val time: Long = earthquake.properties.time
        var magnitude: Double = round(earthquake.properties.mag * 10) / 10
        var location: String = earthquake.properties.place

        viewHolder.tvmagnitude.text = magnitude.toString()
        viewHolder.tvlocation.text = location
        viewHolder.tvtime.text = Date(time).toString()


        viewHolder.layout.setOnClickListener {
            val mapIntent = Intent(context, EarthquakeMapActivity::class.java)
            mapIntent.putExtra(EarthquakeMapActivity.EXTRA_EARTHQUAKE, earthquake)
            context.startActivity(mapIntent)

        }


    }

    override fun getItemCount(): Int {
        return earthquakeList.features.size
    }

}

