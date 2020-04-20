package com.example.playingsound

import android.media.MediaPlayer
import android.media.SoundPool
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.playingsound.databinding.ActivityMainBinding
import com.example.playingsound.model.MyAdapter
import com.example.playingsound.model.MyViewModel

const val PLAYING = "playing"
const val STOPPED = "stopped"

class MainActivity : AppCompatActivity() {

    private lateinit var myViewModel : MyViewModel

    private lateinit var mainBinding : ActivityMainBinding
    private var mediaPlayer : MediaPlayer? = null
    private lateinit var soundPool : SoundPool

    private var soundStatus = MutableLiveData<String>()
    //private val trackList = listOf(R.raw.coin, R.raw.crash_x, R.raw.cymbals, R.raw.test_papa)
    private val trackList = listOf(R.raw.lianah_10, R.raw.lianah_moins, R.raw.lianah_4, R.raw.lianah_egal)
    private var soundId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        myViewModel = MyViewModel(application)

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mainBinding.recyclerView.apply {
            adapter = MyAdapter(myViewModel.listFile)
            layoutManager = LinearLayoutManager(applicationContext)
        }

        buttonConfiguration()

        soundStatus.observe(this, Observer {
            when (it) {
                PLAYING -> {
                    mainBinding.group1IsEnabled = true
                    mainBinding.playButton.isEnabled = false
                    //mainBinding.stopButton.isEnabled = true
                }

                STOPPED -> {
                    mainBinding.group1IsEnabled = false
                    mainBinding.playButton.isEnabled = true
                    //mainBinding.stopButton.isEnabled = false
                }
            }
        })
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

            if (soundId < trackList.size) {
                playSound(trackList[soundId])
            }
            else {
                soundStatus.value = STOPPED
            }

        }

        mediaPlayer?.start()
        soundStatus.value = PLAYING
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
