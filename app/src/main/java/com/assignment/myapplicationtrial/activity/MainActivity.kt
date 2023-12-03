package com.assignment.myapplicationtrial.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.assignment.myapplicationtrial.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val splashText = "Splash screen!"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        animationPlaying()
    }

    private fun animationPlaying()  {
        val handler = Handler(Looper.getMainLooper())

        val tvSplash = binding.tvSplash
        var index = 0
        val runnable = object: Runnable {
            override fun run() {
                if (index < splashText.length) {
                    val currentText = splashText.substring(0, index + 1)
                    tvSplash.text = currentText.uppercase()
                    index++
                    handler.postDelayed(this, SPLASH_SCREEN_DELAY / splashText.length)
                } else {
                    startActivity(Intent(this@MainActivity, HomeActivity::class.java))
                }
            }
        }
        handler.postDelayed(runnable, SPLASH_SCREEN_DELAY / splashText.length)
    }

    companion object {
        const val SPLASH_SCREEN_DELAY = 3000L
    }
}