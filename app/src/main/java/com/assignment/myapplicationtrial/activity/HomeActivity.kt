package com.assignment.myapplicationtrial.activity

import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.assignment.myapplicationtrial.R
import com.assignment.myapplicationtrial.broadcast.NetworkChangeReceiver

class HomeActivity : AppCompatActivity() {
    private val networkChangeReceiver = NetworkChangeReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

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