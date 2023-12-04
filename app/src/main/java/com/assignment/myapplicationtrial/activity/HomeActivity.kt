package com.assignment.myapplicationtrial.activity

import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.assignment.myapplicationtrial.R
import com.assignment.myapplicationtrial.broadcast.NetworkChangeReceiver
import com.assignment.myapplicationtrial.databinding.ActivityHomeBinding
import com.assignment.myapplicationtrial.fragment.MapsFragment
import com.assignment.myapplicationtrial.network.ISSTracker
import com.assignment.myapplicationtrial.network.ISSWorker
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import java.util.concurrent.TimeUnit

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val networkChangeReceiver = NetworkChangeReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val work = OneTimeWorkRequestBuilder<ISSWorker>().addTag(ISSTracker.TRACKER)
            .build()
        WorkManager.getInstance(applicationContext).enqueue(work)

        val mapFragment = MapsFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_view_maps, mapFragment)
            .commit()

        registerNetworkChangeReceiver()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterNetworkChangeReceiver()
        WorkManager.getInstance(applicationContext).cancelAllWorkByTag(ISSTracker.TRACKER)
    }

    private fun registerNetworkChangeReceiver() {
        val filter = IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")
        registerReceiver(networkChangeReceiver, filter)
    }

    private fun unregisterNetworkChangeReceiver() {
        unregisterReceiver(networkChangeReceiver)
    }
}