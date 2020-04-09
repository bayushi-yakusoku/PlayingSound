package com.example.playingsound.model

import android.media.MediaPlayer
import com.example.playingsound.R

class TheVoice : CalculMental {
    override fun nouvelleQuestion() {
        TODO("Not yet implemented")
    }

    override fun repeteQuestion() {
        TODO("Not yet implemented")
    }

    override fun donneMoiLaReponse(): Int {
        TODO("Not yet implemented")
    }

    override fun ecritMoiLaQuestion(): String {
        TODO("Not yet implemented")
    }


    private val listeDesNombres = listOf(R.raw.lianah_1,
        R.raw.lianah_2,
        R.raw.lianah_3,
        R.raw.lianah_4,
        R.raw.lianah_5,
        R.raw.lianah_6,
        R.raw.lianah_7,
        R.raw.lianah_8,
        R.raw.lianah_9,
        R.raw.lianah_10
    )

    private val listeDesOperations = listOf(R.raw.lianah_plus, R.raw.lianah_moins)

    private val player : MediaPlayer? = null


}
