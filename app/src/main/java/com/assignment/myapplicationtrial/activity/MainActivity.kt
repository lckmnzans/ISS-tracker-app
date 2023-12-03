package com.assignment.myapplicationtrial.activity

<<<<<<< HEAD
import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.assignment.myapplicationtrial.R
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private var mediaPlayer: MediaPlayer? = null
    private val audioUrl = "https://api.dictionaryapi.dev/media/pronunciations/en/swarm-us.mp3"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMediaAudio = findViewById<Button>(R.id.btn_media_audio)
        btnMediaAudio.setOnClickListener {
            setBtnMediaAudio()
        }
    }
    private fun setBtnMediaAudio() {
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.stop()
            mediaPlayer?.reset()
            mediaPlayer?.release()
        } else {
            setMediaPlayer()
        }
    }

    private fun setMediaPlayer() {
        mediaPlayer = MediaPlayer()
        mediaPlayer?.setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
        )

        try {
            mediaPlayer?.setDataSource(audioUrl)
            mediaPlayer?.prepare()
        } catch (e: Exception) {
            Log.e("MediaPlayer", "Error setting data source", e)
        }

        mediaPlayer?.setOnPreparedListener {
            playMediaPlayer()
        }

        mediaPlayer?.setOnCompletionListener {
            releaseMediaPlayer()
        }
    }

    private fun playMediaPlayer() {
        mediaPlayer?.start()
    }

    private fun stopMediaPlayer() {
        mediaPlayer?.stop()
    }

    private fun releaseMediaPlayer() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                stopMediaPlayer()
            }
            it.reset()
            it.release()
        }
        mediaPlayer = null
    }

    override fun onDestroy() {
        super.onDestroy()
        releaseMediaPlayer()
=======
import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.assignment.myapplicationtrial.R
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
>>>>>>> main
    }
}