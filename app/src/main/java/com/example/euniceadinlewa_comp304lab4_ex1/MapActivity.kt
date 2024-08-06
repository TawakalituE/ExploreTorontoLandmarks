package com.example.euniceadinlewa_comp304lab4_ex1

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap
    private lateinit var toggleViewButton: Button
    private var isSatelliteView = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        title = "euniceadinlewa_comp304lab4_ex1"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
        }

        mapView = findViewById(R.id.mapView)
        toggleViewButton = findViewById(R.id.toggleViewButton)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        toggleViewButton.setOnClickListener {
            isSatelliteView = !isSatelliteView
            googleMap.mapType = if (isSatelliteView) GoogleMap.MAP_TYPE_SATELLITE else GoogleMap.MAP_TYPE_NORMAL
        }
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            googleMap.isMyLocationEnabled = true
        }

        val landmarkName = intent.getStringExtra("landmarkName")
        val landmarkAddress = intent.getStringExtra("landmarkAddress")
        Log.d("MapActivity", "Landmark Name: $landmarkName")
        Log.d("MapActivity", "Landmark Address: $landmarkAddress")

        if (landmarkName != null) {
            val location = getLocationByLandmarkName(landmarkName)
            googleMap.addMarker(MarkerOptions().position(location).title(landmarkName))
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
        } else {
            Log.e("MapActivity", "Landmark name is null")
        }
    }

    private fun getLocationByLandmarkName(landmarkName: String): LatLng {
        return when (landmarkName) {
            "Casa Loma" -> LatLng(43.6780, -79.4094)
            "Fort York" -> LatLng(43.6390, -79.4064)
            "Spadina Museum" -> LatLng(43.6795, -79.4057)
            "Campbell House Museum" -> LatLng(43.6515, -79.3853)
            "Osgoode Hall" -> LatLng(43.6525, -79.3841)
            "Royal Ontario Museum" -> LatLng(43.6677, -79.3948)
            "Art Gallery of Ontario" -> LatLng(43.6536, -79.3925)
            "Hockey Hall of Fame" -> LatLng(43.6469, -79.3776)
            "Aga Khan Museum" -> LatLng(43.7256, -79.3407)
            "Bata Shoe Museum" -> LatLng(43.6670, -79.4007)
            "Rogers Centre" -> LatLng(43.6414, -79.3894)
            "Scotiabank Arena" -> LatLng(43.6435, -79.3791)
            "BMO Field" -> LatLng(43.6332, -79.4180)
            "Varsity Stadium" -> LatLng(43.6660, -79.4001)
            "Coca-Cola Coliseum" -> LatLng(43.6352, -79.4157)
            "CN Tower" -> LatLng(43.6426, -79.3871)
            "Ripley's Aquarium" -> LatLng(43.6424, -79.3860)
            "Toronto Islands" -> LatLng(43.6220, -79.3706)
            "Toronto Zoo" -> LatLng(43.8177, -79.1859)
            "Distillery District" -> LatLng(43.6503, -79.3595)
            else -> LatLng(43.651070, -79.347015) // Default location
        }
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // Permission granted, set my location enabled
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    googleMap.isMyLocationEnabled = true
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
