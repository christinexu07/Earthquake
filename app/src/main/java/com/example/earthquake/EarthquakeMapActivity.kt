package com.example.earthquake

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.earthquake.databinding.ActivityEarthquakeMapBinding
import android.preference.PreferenceManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import org.osmdroid.config.Configuration.*
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import java.util.ArrayList
import kotlin.math.round

class EarthquakeMapActivity : AppCompatActivity() {
    private lateinit var binding:ActivityEarthquakeMapBinding
    private lateinit var marker:Marker
    private val REQUEST_PERMISSIONS_REQUEST_CODE = 1
    private lateinit var map : MapView
    companion object{
        val EXTRA_EARTHQUAKE = "earthquake"
        val TAG = "bloop"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityEarthquakeMapBinding.inflate(layoutInflater)
        val earthquake:Feature? = intent.getParcelableExtra<Feature>(EXTRA_EARTHQUAKE)
        getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this))
        setContentView(binding.root)
        map = binding.mapEarthquakeMapMap
        setLocation(earthquake)
        setDetails(earthquake)
        setMarker(earthquake,map)
        map.setTileSource(TileSourceFactory.MAPNIK)
    }

    override fun onResume() {
        super.onResume()
    }
    override fun onPause() {
        super.onPause()
        map.onPause()
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val permissionsToRequest = ArrayList<String>()
        var i = 0
        while (i < grantResults.size) {
            permissionsToRequest.add(permissions[i])
            i++
        }
        if (permissionsToRequest.size > 0) {
            ActivityCompat.requestPermissions(
                this,
                permissionsToRequest.toTypedArray(),
                REQUEST_PERMISSIONS_REQUEST_CODE)
        }
    }

    fun setLocation(earthquake:Feature?){
        if(earthquake!=null){
            val mapController = map.controller
            mapController.setZoom(5.7)
            var c1 = earthquake.geometry.coordinates[0]
            var c2 = earthquake.geometry.coordinates[1]
            val startPoint = GeoPoint(c2,c1)
            mapController.setCenter(startPoint)
        }
    }

    fun setDetails(earthquake:Feature?){
        if(earthquake!=null){
            var magnitude: Double = round(earthquake.properties.mag * 10) / 10
            var location: String = earthquake.properties.place
            var generalString = "${magnitude} ${location}"
            var url = earthquake.properties.url
            binding.textViewEarthquakeMapGeneral.text = generalString
            binding.textViewEarthquakeMapLink.text = url
        }
    }

    fun setMarker(earthquake:Feature?, mapView:MapView){
        if(earthquake!=null){
            var c1 = earthquake.geometry.coordinates[0]
            var c2 = earthquake.geometry.coordinates[1]
            val geoPoint = GeoPoint(c2,c1)
            marker = Marker(mapView)
            marker.position = geoPoint
            marker.title = earthquake.properties.place
            marker.setAnchor(Marker.ANCHOR_BOTTOM, Marker.ANCHOR_CENTER)
            mapView.overlays.add(marker)
            mapView.invalidate()
        }
    }


}