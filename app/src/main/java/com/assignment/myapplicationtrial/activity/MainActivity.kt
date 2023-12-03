package com.assignment.myapplicationtrial.activity

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
    }
}