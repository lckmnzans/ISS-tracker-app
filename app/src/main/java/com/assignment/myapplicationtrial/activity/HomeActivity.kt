package com.assignment.myapplicationtrial.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.assignment.myapplicationtrial.R
import com.assignment.myapplicationtrial.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}