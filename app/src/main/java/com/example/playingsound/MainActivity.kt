package com.example.playingsound

import android.media.MediaPlayer
import android.media.SoundPool
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.playingsound.databinding.ActivityMainBinding

const val PLAYING = "playing"
const val STOPPED = "stopped"

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding : ActivityMainBinding
    private var mediaPlayer : MediaPlayer? = null
    private lateinit var soundPool : SoundPool

    private var soundStatus = MutableLiveData<String>()
    //private val trackList = listOf(R.raw.coin, R.raw.crash_x, R.raw.cymbals, R.raw.test_papa)
    private val trackList = listOf(R.raw.lianah_10, R.raw.lianah_moins, R.raw.lianah_4, R.raw.lianah_egal)
    private var soundId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        buttonConfiguration()
        //withSoundPool()

        soundStatus.observe(this, Observer {
            if (it == STOPPED && soundId < trackList.size) {
                playSound(trackList[soundId])
            }
        })

        setContentView(mainBinding.root)
    }

    private fun buttonConfiguration() {
        mainBinding.playButton.setOnClickListener {
            soundId = 0
            playSound(trackList[soundId])
        }

        mainBinding.stopButton.setOnClickListener {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }

    private fun playSound(soundResId : Int) {
        mediaPlayer = MediaPlayer.create(this, soundResId)

        mediaPlayer?.setOnCompletionListener {
            Toast.makeText(applicationContext, "Stop playing", Toast.LENGTH_LONG).show()
            soundId ++
            soundStatus.value = STOPPED
        }

        soundStatus.value = PLAYING

        mediaPlayer?.start()
    }

    private fun withSoundPool() {
        soundPool = SoundPool.Builder()
            .setMaxStreams(1)
            .build()

        val crashSound = soundPool.load(applicationContext, R.raw.crash_x, 0)

        mainBinding.playButton.setOnClickListener {
            soundPool.play(crashSound, 1f, 1f, 0, 0, 1f)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
    }
}
