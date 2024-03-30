package com.example.aidharbour

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.aidharbour.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        addMarker(LatLng(13.328711, 74.743307),"Sampada Udupi" )
        addMarker(LatLng(13.503692, 74.708389), "Geethanand Foundation")
        addMarker(LatLng(12.813610, 74.890241), "Sevaashrama")
        addMarker(LatLng(12.931587, 77.553201), "Parisara Abhivrudhi")
        addMarker(LatLng(12.934249, 77.545554), "Aasara Charitable Trust")
        addMarker(LatLng(12.915684220607757, 74.88967416652778), "Nirashritha Kendra")
        addMarker(LatLng(12.858046463051803, 74.85463783769148),"St. Anthony's Ashram")

        mMap.setOnMarkerClickListener { marker ->
            val title = marker.title
            val uid = intent.getStringExtra("uid")
            val intent = Intent(this@MapsActivity, Donation::class.java)
            intent.putExtra("marker_title", title)
            intent.putExtra("uid", uid)
            startActivity(intent)
            true
        }
    }
    private fun addMarker(position: LatLng, name: String)
    {
        mMap?.addMarker(MarkerOptions()
            .position(position)
            .title(name)
        )
        mMap?.moveCamera(CameraUpdateFactory
            .newLatLng(position)
        )
    }
}