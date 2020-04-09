package com.example.playingsound.model

interface CalculMental {
    fun nouvelleQuestion()

    fun repeteQuestion()

    fun donneMoiLaReponse() : Int

    fun ecritMoiLaQuestion() : String
}
