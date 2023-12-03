package com.assignment.myapplicationtrial.activity

import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.assignment.myapplicationtrial.R
import com.assignment.myapplicationtrial.broadcast.NetworkChangeReceiver
import com.assignment.myapplicationtrial.databinding.ActivityHomeBinding
import com.assignment.myapplicationtrial.fragment.MapsFragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val networkChangeReceiver = NetworkChangeReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = MapsFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_view_maps, mapFragment)
            .commit()

        registerNetworkChangeReceiver()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterNetworkChangeReceiver()
    }

    private fun registerNetworkChangeReceiver() {
        val filter = IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")
        registerReceiver(networkChangeReceiver, filter)
    }

    private fun unregisterNetworkChangeReceiver() {
        unregisterReceiver(networkChangeReceiver)
    }
}