package com.assignment.myapplicationtrial.activity

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.assignment.myapplicationtrial.R
import com.assignment.myapplicationtrial.broadcast.NetworkChangeReceiver
import com.assignment.myapplicationtrial.databinding.ActivityHomeBinding
import com.assignment.myapplicationtrial.fragment.MapsFragment
import com.assignment.myapplicationtrial.manager.ToolbarManager
import com.assignment.myapplicationtrial.network.ISSTracker
import com.assignment.myapplicationtrial.network.ISSWorker
import com.assignment.myapplicationtrial.utils.ISSPositionLiveData
import com.assignment.myapplicationtrial.viewmodel.HomeViewModel

class HomeActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var toolbar: ToolbarManager
    private val networkChangeReceiver = NetworkChangeReceiver()
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerNetworkChangeReceiver()
        setWorkISSTracking()
        binding.toolbar.tvAppName.text = "ISS REALTIME TRACKER"
        toolbar = ToolbarManager(this)

        val mapFragment = MapsFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_view_maps, mapFragment)
            .commit()

        ISSPositionLiveData.issPosition.observe(this) {
            binding.tvIssLat.text = it.latitude.toString()
            binding.tvIssLon.text = it.longitude.toString()
        }

        setButton()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterNetworkChangeReceiver()
        stopWorkISSTracking()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        toolbar.onCreateOptionsMenu(menu!!)
        return true
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.btnSwitchMapStyle0.id -> {
                viewModel.setMapStyleRes(R.raw.map_style0)
                binding.btnSwitchMapStyle0.foreground = ResourcesCompat
                    .getDrawable(resources, R.drawable.square_shape, null)
                setButtonSelected(binding.btnSwitchMapStyle0)
            }
            binding.btnSwitchMapStyle1.id -> {
                viewModel.setMapStyleRes(R.raw.map_style1)
                binding.btnSwitchMapStyle1.foreground = ResourcesCompat
                    .getDrawable(resources, R.drawable.square_shape, null)
                setButtonSelected(binding.btnSwitchMapStyle1)
            }
            binding.btnSwitchMapStyle2.id -> {
                viewModel.setMapStyleRes(R.raw.map_style2)
                binding.btnSwitchMapStyle2.foreground = ResourcesCompat
                    .getDrawable(resources, R.drawable.square_shape, null)
                setButtonSelected(binding.btnSwitchMapStyle2)
            }
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
        val work = OneTimeWorkRequestBuilder<ISSWorker>()
            .addTag(ISSTracker.TRACKER)
            .build()
        WorkManager.getInstance(applicationContext).enqueue(work)
    }

    private fun stopWorkISSTracking() {
        WorkManager.getInstance(applicationContext).cancelAllWorkByTag(ISSTracker.TRACKER)
    }

    private fun setButtonSelected(view: View) {
        when (view) {
            binding.btnSwitchMapStyle0 -> {
                binding.btnSwitchMapStyle1.foreground = null
                binding.btnSwitchMapStyle2.foreground = null
            }
            binding.btnSwitchMapStyle1 -> {
                binding.btnSwitchMapStyle0.foreground = null
                binding.btnSwitchMapStyle2.foreground = null
            }
            binding.btnSwitchMapStyle2 -> {
                binding.btnSwitchMapStyle0.foreground = null
                binding.btnSwitchMapStyle1.foreground = null
            }
        }
    }
}