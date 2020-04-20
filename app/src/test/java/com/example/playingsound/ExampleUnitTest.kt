package com.example.playingsound

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun supprime_dernier_caractere() {

        var pouf = "Lianah ne jouera pas a OverWatch"

        println("la phrase est                : '$pouf'")
        println("la longueur de la chaine est : ${pouf.length}")
        println("le morceau de la chaine est  : '" + pouf.substring(2, 10) + "'")

        println(pouf.substring(0, pouf.length - 1))
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}
