package com.assignment.myapplicationtrial.utils

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.util.Log

object AudioPlay {
    private var mediaPlayer: MediaPlayer? = null
    private val audioUrl = "https://api.dictionaryapi.dev/media/pronunciations/en/swarm-us.mp3"

    // setBtnMedia() should been set in a view.setOnClickListener
    fun setBtnMedia() {
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.stop()
            mediaPlayer?.reset()
            mediaPlayer?.release()
            releaseMediaPlayer()
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
            playMediaAudio()
        }

        mediaPlayer?.setOnCompletionListener {
            releaseMediaPlayer()
        }
    }

    private fun playMediaAudio() {
        mediaPlayer?.start()
    }

    private fun stopMediaAudio() {
        mediaPlayer?.stop()
    }

    private fun releaseMediaPlayer() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                stopMediaAudio()
            }
            it.reset()
            it.release()
        }
        mediaPlayer = null
    }
}