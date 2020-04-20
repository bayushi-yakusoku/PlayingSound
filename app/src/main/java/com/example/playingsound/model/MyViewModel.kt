package com.example.playingsound.model

import android.app.Application
import android.content.res.AssetManager
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import java.io.File

class MyViewModel(private val app: Application) : AndroidViewModel(app) {
    private val _listFiles : MutableList<String> = mutableListOf()
    val listFile : List<String>
        get() = _listFiles

    init {
        Log.d("Init", "Init Lancement")
        getFiles()
    }

    private fun getFiles() {
        app.applicationContext.getExternalFilesDirs(null).forEach {
            it.walk().forEach {
                if (it.isDirectory) return@forEach
                Log.d("Fichier", it.absolutePath)
                _listFiles.add(it.absolutePath)
            }
        }
    }

    private fun walkThroughFiles() {
        val manager : AssetManager = app.applicationContext.assets

        manager.list("")?.forEach {
            Log.d("Fichier", "$it")
        }

//        File("/sdcard/DCIM/").walk().forEach {
//            Log.d("Fichier", "${it.absolutePath}")
//        }

        //val storageLocation = Environment.getDataDirectory().absolutePath
        val storageLocations = app.applicationContext.getExternalFilesDirs(null)

        app.applicationContext.getExternalFilesDirs(null).forEach {
            it.walk().forEach {
                if (it.isDirectory) return@forEach
                Log.d("Fichier", it.absolutePath)
            }
        }

        File("/storage/emulated/0/DCIM/").walk().forEach {
            Log.d("Fichier", "${it.absolutePath}")
        }
    }
}
