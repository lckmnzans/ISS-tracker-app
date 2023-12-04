package com.assignment.myapplicationtrial.activity

import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.assignment.myapplicationtrial.R
import com.assignment.myapplicationtrial.broadcast.NetworkChangeReceiver
import com.assignment.myapplicationtrial.databinding.ActivityHomeBinding
import com.assignment.myapplicationtrial.fragment.MapsFragment
import com.assignment.myapplicationtrial.network.ISSTracker
import com.assignment.myapplicationtrial.network.ISSWorker
import com.assignment.myapplicationtrial.viewmodel.HomeViewModel

class HomeActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityHomeBinding
    private val networkChangeReceiver = NetworkChangeReceiver()
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setWorkISSTracking()
        binding.toolbar.tvAppName.text = "ISS REALTIME TRACKER"

        val mapFragment = MapsFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_view_maps, mapFragment)
            .commit()
        setButton()

        registerNetworkChangeReceiver()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterNetworkChangeReceiver()
        WorkManager.getInstance(applicationContext).cancelAllWorkByTag(ISSTracker.TRACKER)
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.btnSwitchMapStyle0.id -> viewModel.setMapStyleRes(R.raw.map_style0)
            binding.btnSwitchMapStyle1.id -> viewModel.setMapStyleRes(R.raw.map_style1)
            binding.btnSwitchMapStyle2.id -> viewModel.setMapStyleRes(R.raw.map_style2)
            else -> {}
        }
    }

    private fun setButton() {
        binding.let {
            it.btnSwitchMapStyle0.setOnClickListener(this)
            it.btnSwitchMapStyle1.setOnClickListener(this)
            it.btnSwitchMapStyle2.setOnClickListener(this)
        }
    }

    private fun registerNetworkChangeReceiver() {
        val filter = IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")
        registerReceiver(networkChangeReceiver, filter)
    }

    private fun unregisterNetworkChangeReceiver() {
        unregisterReceiver(networkChangeReceiver)
    }

    private fun setWorkISSTracking() {
        val work = OneTimeWorkRequestBuilder<ISSWorker>().addTag(ISSTracker.TRACKER)
            .build()
        WorkManager.getInstance(applicationContext).enqueue(work)
    }
}